function BillValidator(newBillUI) {
		this.newBillUI = newBillUI;
}

BillValidator.prototype.getNewBillUI=function() {
	return this.newBillUI;
}
BillValidator.prototype.validate = function(bill,patientName) {
	var error = new ErrorDTO();
	var self=this.getNewBillUI();
	var errorMsgs = [];
	// for storing the error messages list having field,Message

/* 	var patientStatus= methodInvoker.checkValidPatient(patientName,self.patientDetails);
		/* if(patientStatus==false){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.patientAutocomplete,constants.ENTERVALIDPATIENT);
			errorMsgs.push(errorMessage);
			
		} */
	if(validator.isEmptyObject(bill.patientId)) {
		error.setErrorStatus(true);
		var errorMessage = new ErrorMessageDTO(self.patientAutocomplete,constants.PATIENTREQUIRED);
		errorMsgs.push(errorMessage);
	} 
	if(bill.origin=="OutPatient"){
		if(bill.getItem().length<=0 && bill.getSupport().length<=0 &&bill.getBed().length<=0) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.billAutocomplete,constants.ITEMSERVICEREQUIRED);
			errorMsgs.push(errorMessage);
		}
}
	error.setErrorMsgs(errorMsgs);
	return error;
}


