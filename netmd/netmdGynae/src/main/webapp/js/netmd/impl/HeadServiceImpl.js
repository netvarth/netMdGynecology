function HeadServiceImpl () {
	this.setTableValues = function(tableObj, headResult) {	
		$j(tableObj).dataTable().fnClearTable();
		if(headResult.headList) {
			if(headResult.headList.length>0) {			
				$j(headResult.headList).each(function(index, headList) {
					var id=headList.id;
					var headName=headList.headName;
					var parentHead=headList.parentHeadName;
					var headType=headList.headtype;
					var rowData=$j(tableObj).dataTable().fnAddData([id,headName,parentHead,headType]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","headIdCol Ustyle");
				});
			}		
		} 
	}
	
}

HeadServiceImpl.prototype.createHead=function (headObj) {
	ajaxProcessor.setUrl(constants.HEADCREATEURL);
	return ajaxProcessor.post(headObj);
}

HeadServiceImpl.prototype.updateHead=function(headObj) {
	ajaxProcessor.setUrl(constants.HEADUPDATEURL);
	return ajaxProcessor.post(headObj);
}
HeadServiceImpl.prototype.deleteHead=function(headId) {
	ajaxProcessor.setUrl(constants.HEADDELETEURL + headId);
	return ajaxProcessor.get();
}
HeadServiceImpl.prototype.viewHead=function(headId) {
	ajaxProcessor.setUrl(constants.HEADVIEWURL + headId);
	return ajaxProcessor.get();
}
HeadServiceImpl.prototype.getParentHead=function() {
	ajaxProcessor.setUrl(constants.GETHEADSURL);
	return ajaxProcessor.get();
} 