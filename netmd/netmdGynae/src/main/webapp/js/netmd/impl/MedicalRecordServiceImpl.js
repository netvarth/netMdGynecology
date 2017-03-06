function MedicalRecordServiceImpl () {
	
	this.setTableValues = function(tableObj, medicalRecordResult) {
		//alert(JSON.stringify(medicalRecordResult));
		$j(tableObj).dataTable().fnClearTable();
		 if(medicalRecordResult) {			
				$j(medicalRecordResult.medicalList).each(function(index, medicalRecord) {
					var id=medicalRecord.id;
					var date=medicalRecord.date;
					var type=medicalRecord.type;
					var doctor=medicalRecord.doctorName
					var rowData=$j(tableObj).dataTable().fnAddData([id,date,type,doctor]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","medicalRecordIdCol Ustyle");
				});
		}		
	} 
}
	
MedicalRecordServiceImpl.prototype.createMedicalRecord=function (medicalRecordObj) {
	ajaxProcessor.setUrl(constants.MEDICALRECORDCREATEURL);
	return ajaxProcessor.post(medicalRecordObj);
}
MedicalRecordServiceImpl.prototype.updateMedicalRecord=function(medicalRecordObj) {
	ajaxProcessor.setUrl(constants.MEDICALRECORDUPDATEURL);
	return ajaxProcessor.post(medicalRecordObj);
}
MedicalRecordServiceImpl.prototype.deleteMedicalRecord=function(medicalRecordId) {
	ajaxProcessor.setUrl(constants.MEDICALRECORDDELETEURL + medicalRecordId);
	return ajaxProcessor.get();
}
MedicalRecordServiceImpl.prototype.viewMedicalRecord=function(medicalRecordId) {
	ajaxProcessor.setUrl(constants.MEDICALRECORDVIEWURL + medicalRecordId);
	return ajaxProcessor.get();
}
MedicalRecordServiceImpl.prototype.getPatientDetail=function(patientId) {
	ajaxProcessor.setUrl(constants.PATIENTVIEWURL + patientId);
	return ajaxProcessor.get();
}
MedicalRecordServiceImpl.prototype.getCase=function(caseId) {
	ajaxProcessor.setUrl(constants.CASEVIEWURL + caseId);
	return ajaxProcessor.get();
}
MedicalRecordServiceImpl.prototype.getPatientDetail=function(patientId) {
	ajaxProcessor.setUrl(constants.PATIENTVIEWURL + patientId);
	return ajaxProcessor.get();
}
MedicalRecordServiceImpl.prototype.getDoctorlist=function() {
	ajaxProcessor.setUrl(constants.CASEVIEWURL + caseId);
	return ajaxProcessor.get();
}