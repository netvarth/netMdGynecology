function BlockValidator() {
	
	this.validate = function(block) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		if(validator.isEmpty(block.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(block.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}	
		else if(!validator.validateName(block.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}	
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
