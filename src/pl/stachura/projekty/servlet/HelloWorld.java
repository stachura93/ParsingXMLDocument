package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;

public class HelloWorld extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("text/plain"); // sets the content type
//		
//		PrintWriter printWriter = resp.getWriter();
//
//		PersonDAO personDAO = new PersonDAO();
//		
//		//List<Person> persons = personDAO.select();
//		
//		printWriter.write("persons");
////		 resp.setContentType("text/plain");
//
		
		
		
	}
	
	
	public static void main(String[] args) {
		PersonDAO personDAO = new PersonDAO();
		personDAO.select();
	}

}
