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
	  				<label>Plik XML</label> 				
					<input class="form-control" id="uploadFile" placeholder="Choose File" disabled="disabled" />
	  			</div>
		<div class="fileUpload btn btn-primary">
	 		<input id="uploadBtn" type="file" name="xmlFile" accept="text/xml" class="upload">Upload</button>
	 	<div>
			</form>
		</div>		
	</div>
</body>
</html>