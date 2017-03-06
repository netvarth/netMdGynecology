function BedTypeValidator() {
	
	this.validate = function(bedType) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(bedType.getType())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.TYPEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(bedType.getType())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}	
		else if(!validator.validateName(bedType.getType())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.TYPEINVALID);
			errorMsgs.push(errorMessage);
		}	
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
