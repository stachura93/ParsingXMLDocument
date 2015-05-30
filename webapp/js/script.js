
function sayInformationAboutAuthor() {
	alert("author: Stachura Bartlomiej")
}


$(function() {
	  $.extend($.tablesorter.themes.ice, {
		  
	    table        : 'ui-widget ui-widget-content ui-corner-all', // table classes
	    caption      : 'ui-widget-content',
	    
	    header       : 'ui-widget-header ui-corner-all ui-state-default', // header classes
	    sortNone     : '',
	    sortAsc      : '',
	    sortDesc     : '',
	    active       : 'ui-state-active', // applied when column is sorted
	    hover        : 'ui-state-hover',  // hover class
	    
	    icons        : 'ui-icon', // icon class added to the <i> in the header
	    iconSortNone : 'ui-icon-carat-2-n-s', // class name added to icon when column is not sorted
	    iconSortAsc  : 'ui-icon-carat-1-n', // class name added to icon when column has ascending sort
	    iconSortDesc : 'ui-icon-carat-1-s', // class name added to icon when column has descending sort
	    filterRow    : '',
	    footerRow    : '',
	    footerCells  : '',
	    even         : 'ui-widget-content', // even row zebra striping
	    odd          : 'ui-state-default'   // odd row zebra striping
	  });

	  $("table").tablesorter({
	    theme : 'jui',
	    headerTemplate : '{content} {icon}', 

	    widgets : ['uitheme', 'zebra'],

	    widgetOptions : {
	      zebra   : ["even", "odd"],
	    }
	  });  
	  
});

  
