package pl.stachura.projekty.servlet;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Generated;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import pl.stachura.projekty.service.AplicationAsyncListener;
import pl.stachura.projekty.service.AsyncRequestProcessor;

@WebServlet(urlPatterns = "/ParserXMLServlet", asyncSupported = true)
public class ParserXMLServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		long startTime = System.currentTimeMillis();
		System.out.println("AsyncLongRunningServlet Start::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId());

		req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

		String time = req.getParameter("time");
		int secs = Integer.valueOf(time);
		// max 10 seconds
		if (secs > 10000)
			secs = 10000;

		AsyncContext asyncCtx = req.startAsync();
		asyncCtx.addListener(new AplicationAsyncListener());
		asyncCtx.setTimeout(9000);

		ThreadPoolExecutor executor = (ThreadPoolExecutor) req
				.getServletContext().getAttribute("executor");

		executor.execute(new AsyncRequestProcessor(asyncCtx, secs));
		long endTime = System.currentTimeMillis();
		System.out.println("AsyncLongRunningServlet End::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId() + "::Time Taken="
				+ (endTime - startTime) + " ms.");
		
		
		
		
		
		  
		
		
		
		
//		RequestDispatcher requestDispatcherLoading = req
//				.getRequestDispatcher("WEB-INF/website/loadingSite.ftl");
//		requestDispatcherLoading.forward(req, resp);
//		
//		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
//		SAXParser saxParser;
//		try {
//			saxParser = saxParserFactory.newSAXParser();
//			ReadXMLPersonSAX handler = new ReadXMLPersonSAX();
//			saxParser
//					.parse(new File(
//							"/Users/bartlomiejstachura/GitHub/Servlet-JDBC-JS---XML/test1.xml"),
//							handler);
//
//			List<Person> personsList = handler.getPersonsList();
//
//			// Save to database 
//			PersonDAO personDAO = new PersonDAO();
//			personDAO.doDelate();
//			personDAO.createTable();
//			personDAO.doInsertBatch(personsList);
//			
//			req.setAttribute("person", personsList);
//			RequestDispatcher requestDispatcher = req
//					.getRequestDispatcher("WEB-INF/website/table.ftl");
//			requestDispatcher.forward(req, resp);
//
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		}
//
	}

	public static void main(String[] args) throws TransformerException {

		// PersonDAO personDAO = new PersonDAO();
		// List<Person> persons = personDAO.select();

	}

}
