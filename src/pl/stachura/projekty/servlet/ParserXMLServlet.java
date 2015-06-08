package pl.stachura.projekty.servlet;

import java.io.File;
import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.JsonObjectTable;
import pl.stachura.projekty.service.MD5;
import pl.stachura.projekty.service.ReadXMLPersonSAX;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns = "/ParserXMLServlet", asyncSupported = false)
public class ParserXMLServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Person> personsList = new ArrayList<Person>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("WEB-INF/website/table.ftl");

		requestDispatcher.forward(req, resp);

		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String sColumn = req.getParameter("iSortCol_0");
		String iSortDir = req.getParameter("sSortDir_0");
		String dir = "desc";

		System.out.println(sColumn);
		System.out.println(iSortDir);

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser;

		try {
			saxParser = saxParserFactory.newSAXParser();
			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
			saxParser
					.parse(new File(
							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
							handler);

			// List<Person> personsList = handler.getPersonsList();
			personsList = handler.getPersonsList();

			// Person person1 = new Person();
			// person1.setName("Pawel");
			// person1.setSurname("Stachura");
			// person1.setLogin("PlayPoleca");
			// Person person2 = new Person();
			// person2.setName("Bartlomiej");
			// person2.setSurname("Stachura");
			// person2.setLogin("mistrz");
			// Person person3 = new Person();
			// person3.setName("Grzesiek");
			// person3.setSurname("Czachor");
			// person3.setLogin("lama");
			// personsList.add(person1);
			// personsList.add(person3);
			// personsList.add(person2);
			//

			if (sColumn != null) {
				if (sColumn.equals("0")) {
					if (iSortDir.equals(dir)) {
						Collections.sort(personsList,
								Collections.reverseOrder(new NameComparator()));
					} else {
						Collections.sort(personsList, new NameComparator());
					}
				}
				if (sColumn.equals("1")) {
					if (iSortDir.equals(dir)) {
						Collections.sort(personsList, Collections
								.reverseOrder(new SurnameComparator()));
					} else {
						Collections.sort(personsList, new SurnameComparator());
					}
				}
				if (sColumn.equals("2")) {
					if (iSortDir.equals(dir)) {
						Collections
						.sort(personsList, Collections
								.reverseOrder(new LoginComparator()));
					} else {
						Collections.sort(personsList, new LoginComparator());
					}
				}
			}

//			ArrayList<Person> personsListMD5 = new ArrayList<Person>();
//			for (Person person : personsList) {
//				String security = (person.getSurname() + "_" + MD5.crypt(person
//						.getName()));
//				personsListMD5.add(new Person(person.getName(), security,
//						person.getLogin()));
//			}
			
		

			JsonObjectTable jObjectTable = new JsonObjectTable();

			//jObjectTable.setDraw(1);
			jObjectTable.setRecordsTotal(personsList.size());
			jObjectTable.setRecordsFiltered(personsList.size());
			jObjectTable.setData(personsList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.println(gson.toJson(jObjectTable));
			System.out.println("gotowe");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

	private class MySqlTask implements Runnable {

		@Override
		public void run() {
			PersonDAO personDAO = new PersonDAO();
			personDAO.doDelate();
			personDAO.createTable();
			// personDAO.doInsertBatch(personsList);
		}
	}

	public static class NameComparator implements Comparator<Person> {

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

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser;

		try {
			saxParser = saxParserFactory.newSAXParser();
			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
			saxParser
					.parse(new File(
							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
							handler);

			// List<Person> personsList = handler.getPersonsList();
			ArrayList<Person> personsList = handler.getPersonsList();

			ArrayList<Person> personsListMD5 = new ArrayList<Person>();
			for (Person person : personsList) {
				String security = (person.getSurname() + "_" + MD5.crypt(person
						.getName()));
				personsListMD5.add(new Person(person.getName(), security,
						person.getLogin()));
			}

			Collections.sort(personsListMD5, Collections.reverseOrder(new LoginComparator()));

			for (int j = 0; j < 5; j++) {
				System.out.println(personsListMD5.get(j).getSurname());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
