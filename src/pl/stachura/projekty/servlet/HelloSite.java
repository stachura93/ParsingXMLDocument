package pl.stachura.projekty.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.google.gson.Gson;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.ITable;
import pl.stachura.projekty.service.ReadXMLPersonSAX;
import pl.stachura.projekty.service.Table;
import pl.stachura.projekty.service.WebFormat;

public class HelloSite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HashMap<String, ArrayList<String>> tableKeyAndValue = null;
		tableKeyAndValue = new HashMap<String, ArrayList<String>>();

		StringBuilder stringBuilder = new StringBuilder();

		try {

			stringBuilder.append("<!DOCTYPE html>");
			stringBuilder.append("<HTML><HEAD>");
			stringBuilder.append("<TITLE>Wyniki z formularza</TITLE>");

			ITable table = new Table();
			String includeLink = table.getAllScriptAndStylesheet();
			stringBuilder.append(includeLink);

			stringBuilder.append("</HEAD><BODY>");

			ArrayList<String> name = new ArrayList<String>();
			ArrayList<String> surname = new ArrayList<String>();
			ArrayList<String> login = new ArrayList<String>();

			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			try {
				SAXParser saxParser = saxParserFactory.newSAXParser();
				ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
				saxParser
						.parse(new File(
								"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
								handler);

				List<Person> personsList = handler.getPersonsList();

				for (Person person : personsList) {
					name.add(person.getName());
					surname.add(person.getSurname());
					login.add(person.getLogin());
				}

				tableKeyAndValue.put("Name", name);
				tableKeyAndValue.put("Surname", surname);
				tableKeyAndValue.put("Login", login);

				stringBuilder.append(table
						.createSimpleTableUseModelClass(tableKeyAndValue));

				byte[] bytes = stringBuilder.toString().getBytes();
				resp.setContentType("text/html");
				resp.setContentLength(bytes.length);
				ServletOutputStream out = resp.getOutputStream();

				out.write(bytes);

			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public static void main(String[] args) {

		// PersonDAO personDAO = new PersonDAO();
		// List<Person> persons = personDAO.select();

	}

}
