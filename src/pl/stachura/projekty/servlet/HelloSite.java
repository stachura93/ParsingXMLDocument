package pl.stachura.projekty.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.ITable;
import pl.stachura.projekty.service.ReadXMLPersonSAX;
import pl.stachura.projekty.service.Table;

public class HelloSite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, ArrayList<String>> tableKeyAndValue = null;

	@Override
	public void init() throws ServletException {
		super.init();
		tableKeyAndValue = new HashMap<String, ArrayList<String>>();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter printWriter =  resp.getWriter();
		StringBuilder pageBuilder = new StringBuilder();

	
		resp.setContentType("text/html");
		
		pageBuilder.append("<!DOCTYPE html>");
		pageBuilder.append("<HTML><HEAD>");
		pageBuilder.append("<TITLE>Wyniki z formularza</TITLE>");

		ITable table = new Table();
		String includeLink = table.getAllScriptAndStylesheet();
		pageBuilder.append(includeLink);
		
		pageBuilder.append("</HEAD><BODY>");
		
	
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> surname = new ArrayList<String>();
		ArrayList<String> login = new ArrayList<String>();
		
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
			saxParser.parse(new File("/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"), handler);
			
	
			List<Person> personsList = handler.getPersonsList();	
			
			for (Person person : personsList) {
				name.add(person.getName());
				surname.add(person.getSurname());
				login.add(person.getLogin());
			}
			personsList = null;
			
			tableKeyAndValue.put("Name", name);
			tableKeyAndValue.put("Surname", surname);
			tableKeyAndValue.put("Login", login);			
			pageBuilder.append(table.createSimpleTableUseModelClass(tableKeyAndValue));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		
				
		pageBuilder.append("</BODY></HTML>");
		
		printWriter.print(pageBuilder.toString());
		printWriter.flush();
		printWriter.close();
}


	public static void main(String[] args) {

		// PersonDAO personDAO = new PersonDAO();
		// List<Person> persons = personDAO.select();

	}

}
