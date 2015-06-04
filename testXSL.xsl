<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
<html>
	<head>
	<title>UserXML</title>
	<!-- <link rel="stylesheet" href="webjars/datatables/1.10.6/css/jquery.dataTables.css"></link> -->
	<script type="text/javascript" src="webjars/jquery/2.1.3/jquery.js"></script>
	<script type="text/javascript" src="webjars/datatables/1.10.6/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	</head>
<body>
<table id="dataTable" class="display">
	<thead>
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>Login</th>
		</tr>
	 </thead>
	<tbody>
		<xsl:for-each select="users/user">
				<tr>
					<td><xsl:value-of select="name"/></td>
					<td><xsl:value-of select="surname"/></td>
					<td><xsl:value-of select="login"/></td>
				</tr>
		</xsl:for-each>
	</tbody>
</table>
</body>
</html>		
</xsl:template>
</xsl:stylesheet>