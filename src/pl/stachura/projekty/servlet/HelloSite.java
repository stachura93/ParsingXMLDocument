package pl.stachura.projekty.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.ReadXMLPersonSAX;

public class HelloSite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// HashMap<String, ArrayList<String>> tableKeyAndValue = null;
		// tableKeyAndValue = new HashMap<String, ArrayList<String>>();
		//
		// StringBuilder stringBuilder = new StringBuilder();
		//
		// try {
		//
		// stringBuilder.append("<!DOCTYPE html>");
		// stringBuilder.append("<HTML><HEAD>");
		// stringBuilder.append("<TITLE>Wyniki z formularza</TITLE>");
		//
		// ITable table = new Table();
		// String includeLink = table.getAllScriptAndStylesheet();
		// stringBuilder.append(includeLink);
		//
		// stringBuilder.append("</HEAD><BODY>");
		//
		// ArrayList<String> name = new ArrayList<String>();
		// ArrayList<String> surname = new ArrayList<String>();
		// ArrayList<String> login = new ArrayList<String>();
		//
		// SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		// try {
		// SAXParser saxParser = saxParserFactory.newSAXParser();
		// ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
		// saxParser
		// .parse(new File(
		// "/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
		// handler);
		//
		// List<Person> personsList = handler.getPersonsList();
		//
		// for (Person person : personsList) {
		// name.add(person.getName());
		// surname.add(person.getSurname());
		// login.add(person.getLogin());
		// }
		//
		// tableKeyAndValue.put("Name", name);
		// tableKeyAndValue.put("Surname", surname);
		// tableKeyAndValue.put("Login", login);
		//
		// stringBuilder.append(table
		// .createSimpleTableUseModelClass(tableKeyAndValue));
		//
		// byte[] bytes = stringBuilder.toString().getBytes();
		// resp.setContentType("text/html");
		// resp.setContentLength(bytes.length);
		// ServletOutputStream out = resp.getOutputStream();
		//
		// out.write(bytes);
		//
		// } catch (ParserConfigurationException | SAXException | IOException e)
		// {
		// e.printStackTrace();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		HashMap<String, ArrayList<String>> tableKeyAndValue = null;
		tableKeyAndValue = new HashMap<String, ArrayList<String>>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> surname = new ArrayList<String>();
		ArrayList<String> login = new ArrayList<String>();

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		SAXParser saxParser;
		try {
			saxParser = saxParserFactory.newSAXParser();
			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
			saxParser
					.parse(new File(
							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
							handler);

			List<Person> personsList = handler.getPersonsList();
			
			
			
			
			//XSTL
			TransformerFactory factory = TransformerFactory.newInstance();
			// inputXSL
			StreamSource xslStream = new StreamSource(
					new File(
							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/testXSL.xsl"));

			try {
				Transformer transformer = factory.newTransformer(xslStream);
				StreamSource in = new StreamSource(
						new File(
								"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"));

//				StreamResult out = new StreamResult(
//						new File(
//								"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/temp.html"));

				StreamResult result = new StreamResult(resp.getOutputStream());
				transformer.transform(in, result);

				
				 
				
				 byte[] bytes = result.toString().getBytes();
				 resp.setContentType("text/html");
				 resp.setContentLength(bytes.length);
				 ServletOutputStream out = resp.getOutputStream();
				
				 out.write(bytes);
				
				
				
				
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}


			// resp.setContentType("text/plain");
			// for (Person person : personsList) {
			// resp.getWriter().println(person.getName());
			// resp.getWriter().println(person.getSurname());
			// resp.getWriter().println(person.getLogin());
			// }

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws TransformerException {

		// PersonDAO personDAO = new PersonDAO();
		// List<Person> persons = personDAO.select();


	}

}
