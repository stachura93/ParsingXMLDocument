package pl.stachura.projekty.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import pl.stachura.projekty.service.ReadXMLPersonSAX;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns = "/ParserXMLServlet", asyncSupported = false)
public class ParserXMLServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Person> personsList;

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
	
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser;

		try {
			saxParser = saxParserFactory.newSAXParser();
			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
			saxParser
					.parse(new File(
							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
							handler);

			personsList = handler.getPersonsList();

			JsonObjectTable jObjectTable = new JsonObjectTable();

			jObjectTable.setiTotalDisplayRecords(personsList.size());
			jObjectTable.setiTotalRecords(personsList.size());
			jObjectTable.setAaData(personsList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonResult = gson.toJson(jObjectTable);

			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.println(jsonResult);

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
			personDAO.doInsertBatch(personsList);
		}
	}

	public static void main(String[] args) {

	}

}
