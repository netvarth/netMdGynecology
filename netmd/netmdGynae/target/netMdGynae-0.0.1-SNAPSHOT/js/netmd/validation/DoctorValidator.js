function DoctorValidator() {
	
	this.validate = function(doctor) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(doctor.getFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.FIRSTNAME,constants.FIRSTNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.isEmpty(doctor.getLastName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.LASTNAME,constants.LASTNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		}	
	/* 	else if(validator.isEmpty(doctor.getDob())|| validator.isEmpty(doctor.getAge())){
			error.setErrorMsgs(true);
			var errorMessage = new ErrorMessageDTO(constants.AGEBIRTH,constants.DOBREQUIRED);
			errorMsgs.push(errorMessage);
		} */
		error.setErrorMsgs(errorMsgs);
		return error;
	

	}
}
