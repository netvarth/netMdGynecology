function MedicalRecordValidator() {
	
	this.validate = function(medicalRecord) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		if(medicalRecord.doctorName=="") {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.DOCTORNEEDED,constants.DOCTORREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(medicalRecord.type=="select"){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.MEDICALRECORDTYPE,constants.MEDICALRECORDTYPEREQUIRED);
			errorMsgs.push(errorMessage);
		}
		if(medicalRecord.medicalRecord=="") {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.MEDICALRECORD,constants.MEDICALRECORDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
