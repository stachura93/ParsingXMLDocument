package pl.stachura.projekty.service;

import java.util.ArrayList;
import java.util.HashMap;

public class Table implements ITable {

	@Override
	public String createSimpleTableUseModelClass(
			HashMap<String, ArrayList<String>> tHeadAndTBody) {
		HashMap<String, ArrayList<String>> copyTheadAndTBody = tHeadAndTBody;

		StringBuilder createTable = new StringBuilder();
		createTable.append("<table id=\"tableSorter\" class=\"display\">").append(
				WebFormat.NEW_LINE);
		createTable.append("<thead>").append(WebFormat.NEW_LINE);
		createTable.append("<tr>").append(WebFormat.NEW_LINE);
		for (String valueKey : copyTheadAndTBody.keySet()) {
			createTable.append("<th> " + valueKey + "</tx>").append(
					WebFormat.NEW_LINE);
		}
		createTable.append("</tr>").append(WebFormat.NEW_LINE);
		createTable.append("</thead>").append(WebFormat.NEW_LINE);
		createTable.append("<tbody>").append(WebFormat.NEW_LINE);
		 
		int listLength =0;
		for (ArrayList<String> iterable_element : copyTheadAndTBody.values()) {
			if(iterable_element.size() > listLength)
				listLength = iterable_element.size();
		}	
		
		for (int i = 0; i < listLength; i++) {
			createTable.append("<tr>").append(WebFormat.NEW_LINE);
			for (ArrayList<String> string : copyTheadAndTBody.values()) {
				createTable.append("<td>" + string.get(i) + " </td>").append(
						WebFormat.NEW_LINE);
			}
			createTable.append("</tr>").append(WebFormat.NEW_LINE);
		}		

		createTable.append("</tbody>").append(WebFormat.NEW_LINE);
		createTable.append("</table>").append(WebFormat.NEW_LINE);
		copyTheadAndTBody = null;
		return createTable.toString();
	}

	@Override
	public String getAllScriptAndStylesheet() {
		StringBuilder link = new StringBuilder();

		link.append(
				"<script src=\"webjars/jquery/2.1.3/jquery.js\"></script>")
				.append(WebFormat.NEW_LINE);
		
		link.append(
				"<link rel=\"stylesheet\" href=\"webjars/datatables/1.10.6/css/jquery.dataTables.css\">")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script src=\"webjars/datatables/1.10.6/js/jquery.dataTables.js\"></script>")
				.append(WebFormat.NEW_LINE);
		
		link.append(
				"<script type=\"text/javascript\" src=\"js/script.js\"></script>")
				.append(WebFormat.NEW_LINE);

		return link.toString();
	}
}
