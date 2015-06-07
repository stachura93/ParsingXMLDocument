function sayInformationAboutAuthor() {
	alert("author: Stachura Bartlomiej")
}



$(document).ready(function() {

	$("#example").dataTable({
		"bPaginate" : true,
		"aaSorting": [],
		"bInfo" : true,
		"iDisplayStart" : 0,
		
		"bProcessing" : true,
		"bServerSide" : true,

		
		  "ajax": {
			    "url": "ParserXMLServlet",
			    "contentType": "application/json",
			    "type" : "POST",
			    "data": function ( d ) {
			      return JSON.stringify( d );
			    }
			  },

//		"sAjaxSource" : "ParserXMLServlet",
//		"sServerMethod": "POST",
	
//		"ajax" : {
//			"url" : "ParserXMLServlet",
//			"type" : "POST"
//		},
		"aoColumns" : [ {
			"mData" : "name"
		}, {
			"mData" : "surname"
		}, {
			"mData" : "login"
		},

		],

	     
	});

});




//$(document).ready(function() {
//
//	$("#example").dataTable({
//		"bProcessing" : true,
//		"bServerSide" : true,
//		"sort" : "position",
//		"ajax" : {
//			"url" : "ParserXMLServlet",
//			"type" : "POST"
//		},
//		"aoColumns" : [ {
//			"mData" : "name"
//		}, {
//			"mData" : "surname"
//		}, {
//			"mData" : "login"
//		},
//
//		]
//	});
//
//});
