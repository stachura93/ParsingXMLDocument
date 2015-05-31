package pl.stachura.projekty.service;

import java.util.ArrayList;
import java.util.HashMap;

public class Table implements ITable {

	@Override
	public String createSimpleTableUseModelClass(
			HashMap<String, ArrayList<String>> tHeadAndTBody) {
		HashMap<String, ArrayList<String>> copyTheadAndTBody = tHeadAndTBody;

		StringBuilder createTable = new StringBuilder();
		createTable.append("<table class=\"tablesorter\">").append(
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

		for (int i = 0; i < copyTheadAndTBody.values().size(); i++) {
			createTable.append("<tr>").append(WebFormat.NEW_LINE);
			for (ArrayList<String> string : copyTheadAndTBody.values()) {
				createTable.append("<td>" + string.get(i) + " </td>").append(
						WebFormat.NEW_LINE);
			}
			createTable.append("</tr>").append(WebFormat.NEW_LINE);
		}

		createTable.append("</tbody>").append(WebFormat.NEW_LINE);
		createTable.append("</table>").append(WebFormat.NEW_LINE);
		return createTable.toString();
	}

	@Override
	public String getAllScriptAndStylesheet() {
		StringBuilder link = new StringBuilder();

		link.append(
				"<link rel=\"stylesheet\" href=\"webjars/tablesorter/2.15.5/css/theme.jui.css\">")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<link rel=\"stylesheet\" href=\"webjars/jquery-ui/1.11.3/jquery-ui.min.css\">")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script src=\"webjars/jquery/2.1.3/jquery.min.js\"></script>")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script src=\"webjars/tablesorter/2.15.5/js/jquery.tablesorter.min.js\"></script>")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script src=\"webjars/tablesorter/2.15.5/js/jquery.tablesorter.js\"></script>")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script src=\"webjars/tablesorter/2.15.5/js/jquery.tablesorter.widgets.js\"></script>")
				.append(WebFormat.NEW_LINE);
		link.append(
				"<script type=\"text/javascript\" src=\"js/script.js\"></script>")
				.append(WebFormat.NEW_LINE);

		return link.toString();
	}
}
