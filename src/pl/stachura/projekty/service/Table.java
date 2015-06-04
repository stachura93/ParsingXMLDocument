package pl.stachura.projekty.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Table implements ITable {

	@Override
	public String createSimpleTableUseModelClass(
			HashMap<String, ArrayList<String>> tHeadAndTBody) throws IOException {
		HashMap<String, ArrayList<String>> copyTheadAndTBody = tHeadAndTBody;
		
		
		StringBuilder createTable = new StringBuilder();
		createTable.append("<table id=\"myTable\">");
		createTable.append("<thead>");
		createTable.append("<tr>");
		for (String valueKey : copyTheadAndTBody.keySet()) {
			createTable.append(("<th> " + valueKey + "</tx>"));
		}
		createTable.append("</tr>");
		createTable.append("</thead>");
		createTable.append("<tbody>");
		 
		int listLength =0;
		for (ArrayList<String> iterable_element : copyTheadAndTBody.values()) {
			if(iterable_element.size() > listLength)
				listLength = iterable_element.size();
		}	
		
		for (int i = 0; i < listLength; i++) {
			createTable.append("<tr>");
			for (ArrayList<String> string : copyTheadAndTBody.values()) {
				createTable.append(("<td>" + string.get(i) + " </td>"));
						
			}
			createTable.append("</tr>");
		}		

		createTable.append("</tbody>");
		createTable.append("</table>");
		
		
		
		return createTable.toString();
	}
	

	@Override
	public String getAllScriptAndStylesheet() {
		StringBuilder link = new StringBuilder();

		
//		link.append(
//				"<link rel=\"stylesheet\" href=\"webjars/datatables/1.10.6/css/jquery.dataTables.css\">")
//				.append(WebFormat.NEW_LINE);
		
	
//		link.append("<script type=\"text/javascript\" src=\"webjars/jquery/2.1.3/jquery.min.js\"></script>"); 
//		//link.append("<script type=\"text/javascript\" src=\"webjars/jquery/2.1.2/jquery-latest.js\"></script>");
//		link.append("<script type=\"text/javascript\" src=\"webjars/tablesorter/2.17.8/js/jquery.tablesorter.js\"></script>"); 
//		
//		
//		
//		link.append(
//				"<script type=\"text/javascript\" src=\"js/script.js\"></script>")
//				.append(WebFormat.NEW_LINE);

		return link.toString();
	}
}
