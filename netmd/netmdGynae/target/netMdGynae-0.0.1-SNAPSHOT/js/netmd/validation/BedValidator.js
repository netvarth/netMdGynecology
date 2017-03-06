function BedValidator() {
	
	this.validate = function(bed) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(bed.getBedNumber())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.BEDNUMBER,constants.NUMBERREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(bed.getBedNumber())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.BEDNUMBER,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}	
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
