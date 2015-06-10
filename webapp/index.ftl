<html>
<head>
	<title>XmlParser SERVLET-JDBC-SAX-JSON</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.4/css/bootstrap.min.css">
    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.js"></script>
	<link rel="stylesheet" href="css/mainStyle.css">
	<script type="text/javascript" src="js/mainButton.js"></script>
</head>
<body>	
	<div id="row">	
		<div class="col-md-8">
		</div>
  		<div class="col-md-4">
			<form class="form-inline" id="ParserXML" action="ParserXMLServlet" method="POST" enctype="multipart/form-data">		
	  			<div class="form-group"> 				
					<input class="form-control" id="uploadFile" placeholder="choose xml file" disabled="disabled" />
	  			</div>
				<div class="fileUpload btn btn-primary">
	 				<input id="uploadBtn" type="file" name="xmlFile" accept="text/xml" class="upload">Load</button> 		
	 			<div>
		</div>
	</div>
		<button type="submit" form="ParserXML" class="btn btn-default">Submit</button>
	</form>
	<script type="text/javascript">
		document.getElementById("uploadBtn").onchange = function () {
			document.getElementById("uploadFile").value = this.value;
		};
	</script>
</body>
</html>