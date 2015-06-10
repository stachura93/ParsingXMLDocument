package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.JsonObjectTable;
import pl.stachura.projekty.service.MD5;
import pl.stachura.projekty.service.InputStreamDocumentParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * This class represents XMLParser for Person object.
 * Servlet receives the file which is parsed (DOCUMENT XML Standard) and copy to JSON.
 * Persons list are there saved using JDBC connector to MySQL database.
 * 
 * @method doPost displays the page by using FREEMARKER templates and save to database
 * @method doGet JSON document for dataTables(JS-JQuery).
 * @author Stachura Bartlomiej
 */
@WebServlet(urlPatterns = "/ParserXMLServlet", asyncSupported = true)
public class ParserXMLServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Person> personsList;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		personsList = new ArrayList<Person>();

		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(req);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field
					// (type="text|radio|checkbox|etc", select, etc).
				} else {
					// Process form file field (input type="file")
					String fileName = FilenameUtils.getName(item.getName());
					InputStream fileContent = item.getInputStream();
					
					// If user don't put a file
					if (fileName == null || fileName.equals("")) {	
						throw new ServletException("File Name can't be null or empty");
					}

					// Convert InputStream file to Person type use java ducument
					Document doc = InputStreamDocumentParser
							.newDocumentFromInputStream(fileContent);
					NodeList nodeList = doc.getElementsByTagName("user");

					for (int itr = 0; itr < nodeList.getLength(); itr++) {

						Person person = new Person();
						Node node = nodeList.item(itr);

						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node;

							person.setName(eElement
									.getElementsByTagName("name").item(0)
									.getTextContent());
							person.setSurname(eElement
									.getElementsByTagName("surname").item(0)
									.getTextContent());
							person.setLogin(eElement
									.getElementsByTagName("login").item(0)
									.getTextContent());
						}
						personsList.add(person);
					}
				}
			}

		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("WEB-INF/website/table.ftl");
		requestDispatcher.forward(req, resp);

		// req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		// AsyncContext aCtx = req.startAsync(req, resp);
		// aCtx.setTimeout(9000000);
		// Executor executor = Executors.newSingleThreadExecutor();
		// executor.execute(new MySqlTask(aCtx, personsList));

		PersonDAO personDAO = new PersonDAO();
		personDAO.doDelate();
		personDAO.createTable();
		personDAO.doInsertBatch(personsList);

		doGet(req, resp);

	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sColumn = req.getParameter("iSortCol_0");
		String iSortDir = req.getParameter("sSortDir_0");
		String dir = "desc";

		// Add MD5 security code and return to new personsMD5 list
		ArrayList<Person> personsMD5 = new ArrayList<Person>();
		for (Person person : personsList) {
			String security = (person.getSurname() + "_" + MD5.crypt(person
					.getName()));
			personsMD5.add(new Person(person.getName(), security, person
					.getLogin()));
		}

		// Sorting Columns ASC/DESC
		if (sColumn != null) {
			if (sColumn.equals("0")) {
				if (iSortDir.equals(dir)) {
					Collections.sort(personsMD5,
							Collections.reverseOrder(new NameComparator()));
				} else {
					Collections.sort(personsMD5, new NameComparator());
				}
			}
			if (sColumn.equals("1")) {
				if (iSortDir.equals(dir)) {
					Collections.sort(personsMD5,
							Collections.reverseOrder(new SurnameComparator()));
				} else {
					Collections.sort(personsMD5, new SurnameComparator());
				}
			}
			if (sColumn.equals("2")) {
				if (iSortDir.equals(dir)) {
					Collections.sort(personsMD5,
							Collections.reverseOrder(new LoginComparator()));
				} else {
					Collections.sort(personsMD5, new LoginComparator());
				}
			}
		}

		// Convert object java of scheme dataTables use type JSON
		JsonObjectTable jObjectTable = new JsonObjectTable();
		jObjectTable.setRecordsTotal(personsMD5.size());
		jObjectTable.setRecordsFiltered(personsMD5.size());
		jObjectTable.setData(personsMD5);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();
		out.println(gson.toJson(jObjectTable));
	}

	// private class MySqlTask implements Runnable {
	// AsyncContext aCtx;
	// private List saveList;
	//
	// public MySqlTask(AsyncContext aCtx, List saveList) {
	// this.aCtx = aCtx;
	// this.saveList = saveList;
	// }
	//
	// @Override
	// public void run() {
	// PersonDAO personDAO = new PersonDAO();
	// personDAO.doDelate();
	// personDAO.createTable();
	// personDAO.doInsertBatch(saveList);
	// }
	// }

	private static class NameComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			String o1 = person1.getName();
			String o2 = person2.getName();
			if (o1.length() < o2.length()) {
				return -1;
			} else if (o1.length() > o2.length()) {
				return 1;
			} else {
				return o1.compareToIgnoreCase(o2);
			}
		}
	}

	private static class SurnameComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {

			String o1 = person1.getSurname();
			String o2 = person2.getSurname();
			if (o1.length() < o2.length()) {
				return -1;
			} else if (o1.length() > o2.length()) {
				return 1;
			} else {

				return o1.compareToIgnoreCase(o2);
			}
		}
	}

	private static class LoginComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			String o1 = person1.getLogin();
			String o2 = person2.getLogin();
			if (o1.length() < o2.length()) {
				return -1;
			} else if (o1.length() > o2.length()) {
				return 1;
			} else {
				return o1.compareToIgnoreCase(o2);
			}
		}
	}

}
