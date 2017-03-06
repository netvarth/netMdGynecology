function AdvanceValidator(newAdvanceUI) {
	this.newAdvanceUI = newAdvanceUI;
}

/*AdvanceValidator.prototype.getNewAdvanceUI=function() {
	return this.newAdvanceUI;
}
*/AdvanceValidator.prototype.validate=function(advance){	
  var self=this.newAdvanceUI;
		 var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmptyObject(advance.patientId)) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.name,constants.PATIENTREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		error.setErrorMsgs(errorMsgs);
		return error; 
	

}
