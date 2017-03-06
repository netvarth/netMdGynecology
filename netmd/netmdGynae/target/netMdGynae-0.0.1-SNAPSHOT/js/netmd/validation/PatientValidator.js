function PatientValidator() {
	
	this.validate = function(patient) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(patient.getFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.FIRSTNAME,constants.FIRSTNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.isEmpty(patient.getLastName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.LASTNAME,constants.LASTNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		}	
	/* 	else if(validator.isEmpty(patient.getDob())|| validator.isEmpty(patient.getAge())){
			error.setErrorMsgs(true);
			var errorMessage = new ErrorMessageDTO(constants.AGEBIRTH,constants.DOBREQUIRED);
			errorMsgs.push(errorMessage);
		} */
		error.setErrorMsgs(errorMsgs);
		return error;
	

	}
}
