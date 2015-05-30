package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;

import com.google.gson.Gson;

public class HelloSite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
//		
//		PersonDAO personDAO = new PersonDAO();
//		List<Person> persons = personDAO.select();	
//		
//		// set the response content-type
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		
//		Gson gson = new Gson( );
//		
//		out.write(gson.toJson(persons));
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");	
		
	}

	public static void main(String[] args) {
//		PersonDAO personDAO = new PersonDAO();
//		List<Person> persons = personDAO.select();
//		
//		
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(persons));
		
		
		
		
	}

}
