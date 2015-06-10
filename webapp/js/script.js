function sayInformationAboutAuthor() {
	alert("author: Stachura Bartlomiej")
}

//Plug-in to fetch page data 
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

$(document).ready(function() {

	$("#example").dataTable( {
	    "bProcessing": true,
	    "bServerSide": true,
	    "bFilter": false,
	    
	    //bStateSave variable you can use to save state on client cookies: set value "true" 
	    "bStateSave": false,
	    //Default: Page display length
	    "bLengthChange": false,
	   
	    "bPaginate": false,
	    
	    "bScrollInfinite": true,
	    "bScrollCollapse": true,
	    "sScrollY": "350px",
	         
	    "sAjaxSource": "ParserXMLServlet",
	    "sServerMethod": "GET",
	    "aoColumns": [
	        { "mData": "name" },
	        { "mData": "surname" },
	        { "mData": "login" },
	         
	    ]
	} );

} );