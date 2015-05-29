package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.stachura.projekty.dao.PersonDAO;

import com.google.gson.Gson;

public class HelloWorld extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String country = req.getParameter("countryname");
		Map<String, String> ind = new LinkedHashMap<String, String>();
		ind.put("1", "New delhi");
		ind.put("2", "Tamil Nadu");
		ind.put("3", "Kerala");
		ind.put("4", "Andhra Pradesh");

		Map<String, String> us = new LinkedHashMap<String, String>();
		us.put("1", "Washington");
		us.put("2", "California");
		us.put("3", "Florida");
		us.put("4", "New York");
		String json = null;
		if (country.equals("India")) {
			json = new Gson().toJson(ind);
		} else if (country.equals("US")) {
			json = new Gson().toJson(us);
		}
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);

	}

	public static void main(String[] args) {
		PersonDAO personDAO = new PersonDAO();
		personDAO.select();
	}

}
