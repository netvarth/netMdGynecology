function DiscountValidator() {
	
	this.validate = function(discount) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(discount.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(discount.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.NAME,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}	
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
