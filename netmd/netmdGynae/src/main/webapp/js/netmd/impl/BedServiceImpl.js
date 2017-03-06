function BedServiceImpl () {
	
	this.setTableValues = function(tableObj,bedResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(bedResult.bedList) {
			if(bedResult.bedList.length>0) {			
				$j(bedResult.bedList).each(function(index, bed) {
					var id=bed.id;
					var bedNumber=bed.bedNumber
					var type=bed.bedType;
					var rowData=$j(tableObj).dataTable().fnAddData([id,bedNumber,type]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","bedIdCol Ustyle");
				});
			}		
		} 
	}
	
}

BedServiceImpl.prototype.createBed=function (bedObj) {
	ajaxProcessor.setUrl(constants.BEDCREATEURL);
	return ajaxProcessor.post(bedObj);
}
BedServiceImpl.prototype.updateBed=function(bedObj) {
	ajaxProcessor.setUrl(constants.BEDUPDATEURL);
	return ajaxProcessor.post(bedObj);
}
BedServiceImpl.prototype.deleteBed=function(bedId) {
	ajaxProcessor.setUrl(constants.BEDDELETEURL + bedId);
	return ajaxProcessor.get();
}
BedServiceImpl.prototype.viewBed=function(bedId) {
	ajaxProcessor.setUrl(constants.BEDVIEWURL + bedId);
	return ajaxProcessor.get();
}
BedServiceImpl.prototype.getBedTypes=function() {
	ajaxProcessor.setUrl(constants.GETBEDTYPES);
	return ajaxProcessor.get();
}
BedServiceImpl.prototype.getRooms=function() {
	ajaxProcessor.setUrl(constants.GETROOMS);
	return ajaxProcessor.get();
}