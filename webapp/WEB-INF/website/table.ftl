<html>
<head>
	<title>FreeMarker Hello World</title>
	<link rel="stylesheet" type="text/css" href="css/loading.css">
</head>
<body>
	<table class="datatable">
		<tr> <th>Name</th>  <th>Surname</th> <th>Login</th> </tr>
		<#list person as person>
		<tr> <td>${person.name}</td> <td>${person.surname}</td><td>${person.login}</td> </tr>
		</#list>
	 </table>
</body>
</html>