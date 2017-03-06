function HeadValidator() {
	
	this.validate = function(head) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		if(validator.isEmpty(head.getHeadName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(validator.validateLimit(head.getHeadName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(head.getHeadName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}

		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
