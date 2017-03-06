function CaseValidator() {
	
	this.validate = function(caseObj,response) {
		var error = new ErrorDTO();
		var errorMsgs = [];
		if(validator.isEmpty(caseObj.getCaseName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.CASE,constants.CASEREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(caseObj.getCaseName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.TYPE,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}
		else if(caseObj.departmentId=="select"){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.DEPARTMENT,constants.DEPARTMENTREQUIRED);
			errorMsgs.push(errorMessage);
		}
	 	/* else if(caseObj.departmentId=="3"){
		       $j(caseObj.questionAnswerDTO.answerDTO).each(function(index,answer){
			      if(answer.questionKey=="bookedUnbooked"){
				     if(answer.answer=="select"){
						error.setErrorStatus(true);
						var errorMessage = new ErrorMessageDTO(answer.questionKey,constants.FIELDREQUIRED);
						errorMsgs.push(errorMessage);
					 }
					  
				  }
			   });
		}  */
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
