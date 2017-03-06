function SupportValidator() {
	
	this.validate = function(support) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(support.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(support.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
