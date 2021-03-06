function DataTableProcessor() {

	
	 this.create = function(tableName , tableUrl) {
		//create the table structure for doctor Table
		ajaxProcessor.setUrl(tableUrl);
		var tblData = ajaxProcessor.get();
		var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);
		});
		$j('#tabs-1').html(boxDiv.html());
		//  this.setCustomTable(tableName);
		//this.setCustomTableForCase(tableName);
		
	}
	
	
	this.setCustomTable = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "newPagination",
			"bRetrieve":true,
			"bFilter":false,
			"bInfo":false,
			"bLengthChange":false,
			"bSort":false,
			"sDom": '<"top"Hip>'
		});
	}

	this.setCustomTableForCase = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "newPagination",
			"bRetrieve":true,
			"bFilter":false,
			"bInfo":true,
			"bLengthChange":false,
			"bSort":false,
			"sDom": '<"top"Hip>'
		});
	}
	this.setCustomTableWithoutNavigator = function(tableName) {
		tableObj = jQuery(tableName);
		//alert("haiii");
		jQuery(tableObj).dataTable( {
		"sPaginationType": "newPagination",
			"bRetrieve":true,
			"bFilter":false,
			"bInfo":false,
			"bLengthChange":false,
			"bSort":false,
			"sDom": '<"top"Hip>'
		
			
		});
	} 
	
}