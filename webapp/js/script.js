function sayInformationAboutAuthor() {
	alert("author: Stachura Bartlomiej")
}

//
// $(document).ready(function() {
// $('#example').dataTable( {
// "processing": true,
// "serverSide": true,
// "ajax": {
// "url": "ParserXMLServlet",
// "type": "POST"
// },
// "columns": [
// { "data": "name" },
// { "data": "surname" },
// { "data": "login" }
// ]
// } );
// } );

$(document).ready(function() {

	$("#example").dataTable({
		"bProcessing" : false,
		"bServerSide" : false,
		"sort" : "position",
		"ajax" : {
			"url" : "ParserXMLServlet",
			"type" : "POST"
		},
		"aoColumns" : [ {
			"mData" : "name"
		}, {
			"mData" : "surname"
		}, {
			"mData" : "login"
		},

		]
	});

});

// $("#example").dataTable( {
// "bProcessing": false,
// "bServerSide": false,
// "sort": "position",
// "sAjaxSource": "ParserXMLServlet",
// "aoColumns": [
// { "mData": "name" },
// { "mData": "surname" },
// { "mData": "login" }
//         
// ]
// } );
