function ItemValidator() {
	
	this.validate = function(item) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		if(validator.isEmpty(item.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(validator.validateLimit(item.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(item.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}

		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
