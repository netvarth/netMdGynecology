function GyneServiceImpl () {
	
	this.setTableValues = function(tableObj, gyneResult) {	
		$j(tableObj).dataTable().fnClearTable();
		 if(gyneResult.gyneList) {
			if(gyneResult.gyneList.length>0) {			
				$j(gyneResult.gyneList).each(function(index, gyneList) {
					var id=gyneList.id;
					var name=gyneList.patientName;
					var amount=gyneList.amount.toFixed(2);
					var date=gyneList.date;
					var rowData=$j(tableObj).dataTable().fnAddData([id,date,name,amount]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(4)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","gyneIdCol Ustyle");
				});
			}		
		} 
	}
	
}

GyneServiceImpl.prototype.createGyne=function (gyneObj) {
	ajaxProcessor.setUrl(constants.GYNECREATEURL);
	return ajaxProcessor.post(gyneObj);
}

GyneServiceImpl.prototype.updateGyne=function(gyneObj) {
	ajaxProcessor.setUrl(constants.GYNEUPDATEURL);
	return ajaxProcessor.post(gyneObj);
}
GyneServiceImpl.prototype.deleteGyne=function(gyneId) {
	ajaxProcessor.setUrl(constants.GYNEDELETEURL + gyneId);
	return ajaxProcessor.get();
}
GyneServiceImpl.prototype.viewGyne=function(gyneId) {
	ajaxProcessor.setUrl(constants.GYNEVIEWURL + gyneId);
	return ajaxProcessor.get();
}
GyneServiceImpl.prototype.getGyneQuestionnaire=function() {
	ajaxProcessor.setUrl(constants.GETGYNEQUESTIONNAIRE);
	return ajaxProcessor.get();
}