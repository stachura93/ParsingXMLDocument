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

import com.google.gson.Gson;

import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.ReadXMLPersonSAX;

public class ParserXMLServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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

			List<Person> personsList = handler.getPersonsList();

			req.setAttribute("person", personsList);
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("WEB-INF/website/table.ftl");
			requestDispatcher.forward(req, resp);

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
