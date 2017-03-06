function SupportServiceImpl () {
	
	this.setTableValues = function(tableObj, supportResult) {	
		$j(tableObj).dataTable().fnClearTable();
		if(supportResult.supportList) {
			if(supportResult.supportList.length>0) {			
				$j(supportResult.supportList).each(function(index, supportList) {
					var id=supportList.id;
					var name=supportList.name;
					var rent=supportList.price.toFixed(2);
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,rent]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(3)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","supportIdCol Ustyle");
				});
			}		
		} 
	}
	
}

SupportServiceImpl.prototype.createSupport=function (supportObj) {
	ajaxProcessor.setUrl(constants.SUPPORTCREATEURL);
	return ajaxProcessor.post(supportObj);
}

SupportServiceImpl.prototype.updateSupport=function(supportObj) {
	ajaxProcessor.setUrl(constants.SUPPORTUPDATEURL);
	return ajaxProcessor.post(supportObj);
}
SupportServiceImpl.prototype.deleteSupport=function(supportId) {
	ajaxProcessor.setUrl(constants.SUPPORTDELETEURL + supportId);
	return ajaxProcessor.get();
}
SupportServiceImpl.prototype.viewSupport=function(supportId) {
	ajaxProcessor.setUrl(constants.SUPPORTVIEWURL + supportId);
	return ajaxProcessor.get();
}
SupportServiceImpl.prototype.getTaxList=function() {
	ajaxProcessor.setUrl(constants.GETTAXES);
	return ajaxProcessor.get();
}
SupportServiceImpl.prototype.getServiceList=function() {
	ajaxProcessor.setUrl(constants.GETSERVICELIST);
    return ajaxProcessor.get();
}