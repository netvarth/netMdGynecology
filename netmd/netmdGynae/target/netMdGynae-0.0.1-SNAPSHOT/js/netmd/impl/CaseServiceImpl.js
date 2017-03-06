function CaseServiceImpl () {
	
	this.setTableValues = function(tableObj, caseResult) {
	//alert(JSON.stringify(caseResult));
		$j(tableObj).dataTable().fnClearTable();
		 if(caseResult) {			
				$j(caseResult.caseList).each(function(index, caseObj) {
					var id=caseObj.id;
					var firstName=caseObj.patientFirstName;
					var lastName = caseObj.patientLastName;
					var patientName = firstName+" "+ lastName;
					var caseName=caseObj.caseName;
					var departmentName=caseObj.departmentName;
					var date=caseObj.createdDate;
					var syncStatus_id=id;
					var syncStat=caseObj.syncStatus;
					if(departmentName=="Obstetrics"){
						if(syncStat==true)
							syncStatus_id+='<img width="18" height="18" style="float:right;" src="/netmd/images/icon/dashboard/ready.png">';	
					}
					var rowData=$j(tableObj).dataTable().fnAddData([syncStatus_id,patientName,departmentName,caseName,date]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","caseIdCol Ustyle");
				});
			}		
		} 
	}

CaseServiceImpl.prototype.getCaseList=function () {
	ajaxProcessor.setUrl(constants.LISTCASEURL);
	return ajaxProcessor.get();
}  	
 CaseServiceImpl.prototype.createCase=function (caseObj) {
	ajaxProcessor.setUrl(constants.CASECREATEURL);
	return ajaxProcessor.post(caseObj);
}  
CaseServiceImpl.prototype.deleteEachRow=function (caseObj) {
	ajaxProcessor.setUrl(constants.DELETEEACHROW);
	return ajaxProcessor.post(caseObj);
}  
CaseServiceImpl.prototype.createAutoCase=function (caseObj) {
	ajaxProcessor.setUrl(constants.CASEAUTOSAVEURL);
	return ajaxProcessor.post(caseObj);
}
CaseServiceImpl.prototype.autosaveRow=function (caseObj) {
	ajaxProcessor.setUrl(constants.CASEAUTOSAVEBYROWURL);
	return ajaxProcessor.post(caseObj);
}
CaseServiceImpl.prototype.updateCase=function(caseObj) {
	ajaxProcessor.setUrl(constants.CASEUPDATEURL);
	return ajaxProcessor.post(caseObj);
}
CaseServiceImpl.prototype.deleteCase=function(caseId) {
	ajaxProcessor.setUrl(constants.CASEDELETEURL + caseId);
	return ajaxProcessor.get();
}      
CaseServiceImpl.prototype.viewCase=function(caseId) {
	ajaxProcessor.setUrl(constants.CASEVIEWURL + caseId);
	return ajaxProcessor.get();
}
CaseServiceImpl.prototype.getPatientDetail=function(patientId) {
	ajaxProcessor.setUrl(constants.PATIENTVIEWURL + patientId);
	return ajaxProcessor.get();
}
CaseServiceImpl.prototype.getPatientDetails=function() {
	ajaxProcessor.setUrl(constants.GETPATIENTDETAILS);
	return ajaxProcessor.get();
}
CaseServiceImpl.prototype.getDepartments=function() {
	ajaxProcessor.setUrl(constants.GETDEPARTMENTS);
	return ajaxProcessor.get();
}
CaseServiceImpl.prototype.getGyneQuestionnaire=function() {
	ajaxProcessor.setUrl(constants.GETGYNEQUESTIONNAIRE);
	return ajaxProcessor.get();
}
CaseServiceImpl.prototype.getViewGyneQuestionnaire=function() {
	ajaxProcessor.setUrl(constants.GETVIEWGYNEQUESTIONNAIRE);
	return ajaxProcessor.get();
}