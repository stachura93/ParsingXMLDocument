package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.stachura.projekty.dao.PersonDAO;
import pl.stachura.projekty.model.Person;
import pl.stachura.projekty.service.ITable;
import pl.stachura.projekty.service.Table;

public class HelloSite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Person> persons;

	@Override
	public void init() throws ServletException {
		super.init();
		PersonDAO personDAO = new PersonDAO();
		persons = personDAO.select();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();;
		resp.setContentType("text/html");
		
		out.println("<!DOCTYPE html>");
		out.println("<HTML><HEAD>");
		out.println("<TITLE>Wyniki z formularza</TITLE>");

		ITable table = new Table();
		String includeLink = table.getAllScriptAndStylesheet();
		out.print(includeLink);
		
		out.println("</HEAD><BODY>");
		
		HashMap<String, ArrayList<String>> tableKeyAndValue = new HashMap<String, ArrayList<String>>();
	
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> surname = new ArrayList<String>();
		ArrayList<String> login = new ArrayList<String>();
		
		for (Person person : persons) {
			name.add(person.getName());
			surname.add(person.getSurname());
			login.add(person.getLogin());
		}
		
		tableKeyAndValue.put("Name", name);
		tableKeyAndValue.put("Surname", surname);
		tableKeyAndValue.put("Login", login);
		
		out.println(table.createSimpleTableUseModelClass(tableKeyAndValue));
				
		out.println("</BODY></HTML>");
		out.close();

	}
	


	

	public static void main(String[] args) {

//		PersonDAO personDAO = new PersonDAO();
//		List<Person> persons = personDAO.select();
		
	}

}
