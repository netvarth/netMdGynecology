function PatientServiceImpl () {

	this.setTableValues = function(tableObj, patientResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(patientResult.patient) {
			if(patientResult.patient.length>0) {			
				$j(patientResult.patient).each(function(index, patient) {
					var mobile;
					var id=patient.id;
					var name=patient.firstName+" "+patient.lastName;
					name = name.toLowerCase().replace(/\b[a-z]/g, function(letter) {
					return letter.toUpperCase();
					});
					var email=patient.email;
					if(patient.mobile!="")
						mobile=patient.mobile;
						else
						mobile=patient.phone;
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,mobile,email]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","patientIdCol Ustyle");
				});
			}		
		} 
	}
	
}

PatientServiceImpl.prototype.createPatient=function (patientObj) {
	ajaxProcessor.setUrl(constants.PATIENTCREATEURL);
	return ajaxProcessor.post(patientObj);
}
PatientServiceImpl.prototype.updatePatient=function(patientObj) {
	ajaxProcessor.setUrl(constants.PATIENTUPDATEURL);
	return ajaxProcessor.post(patientObj);
}
PatientServiceImpl.prototype.deletePatient=function(patientId) {
	ajaxProcessor.setUrl(constants.PATIENTDELETEURL + patientId);
	return ajaxProcessor.get();
}
PatientServiceImpl.prototype.viewPatient=function(patientId) {
	ajaxProcessor.setUrl(constants.PATIENTVIEWURL + patientId);
	return ajaxProcessor.get();
}