function SettingValidator() {
	this.validate = function(setting, source) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(setting.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} /* else if(validator.validateName(setting.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		} */
	 	//List Validation
		if(validator.isEmptyList(setting.getSettingList())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.autoField,constants.LISTREQUIRED);
			errorMsgs.push(errorMessage);
			} 
		error.setErrorMsgs(errorMsgs);
		return error;

	}

}
