function AdvanceServiceImpl () {
	
	this.setTableValues = function(tableObj, advanceResult) {	
		$j(tableObj).dataTable().fnClearTable();
		 if(advanceResult.advanceList) {
			if(advanceResult.advanceList.length>0) {			
				$j(advanceResult.advanceList).each(function(index, advanceList) {
					var id=advanceList.id;
					var name=advanceList.patientName;
					var amount=advanceList.amount.toFixed(2);
					var date=advanceList.date;
					var rowData=$j(tableObj).dataTable().fnAddData([id,date,name,amount]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(4)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","advanceIdCol Ustyle");
				});
			}		
		} 
	}
	
}

AdvanceServiceImpl.prototype.createAdvance=function (advanceObj) {
	ajaxProcessor.setUrl(constants.ADVANCECREATEURL);
	return ajaxProcessor.post(advanceObj);
}

AdvanceServiceImpl.prototype.updateAdvance=function(advanceObj) {
	ajaxProcessor.setUrl(constants.ADVANCEUPDATEURL);
	return ajaxProcessor.post(advanceObj);
}
AdvanceServiceImpl.prototype.deleteAdvance=function(advanceId) {
	ajaxProcessor.setUrl(constants.ADVANCEDELETEURL + advanceId);
	return ajaxProcessor.get();
}
AdvanceServiceImpl.prototype.viewAdvance=function(advanceId) {
	ajaxProcessor.setUrl(constants.ADVANCEVIEWURL + advanceId);
	return ajaxProcessor.get();
}
AdvanceServiceImpl.prototype.getPatientDetails=function() {
	ajaxProcessor.setUrl(constants.GETPATIENTDETAILS);
	return ajaxProcessor.get();
}