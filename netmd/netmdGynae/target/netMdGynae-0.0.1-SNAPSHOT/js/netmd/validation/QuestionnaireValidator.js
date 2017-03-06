function QuestionnaireValidator() {
	
	this.validate = function(qustnObj) {
		var error = new ErrorDTO();
		var errorMsgs = [];
		/* if(validator.isEmpty(qustnObj.getCaseName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.CASE,constants.CASEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(qustnObj.getCaseName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}
		else if(qustnObj.departmentId=="select"){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.DEPARTMENT,constants.DEPARTMENTREQUIRED);
			errorMsgs.push(errorMessage);
		} */
		
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
