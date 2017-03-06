function DepartmentValidator() {
	
	this.validate = function(department) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		if(validator.isEmpty(department.getDepartmentName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(department.getDepartmentName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}	
		else if(!validator.validateName(department.getDepartmentName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}	
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
