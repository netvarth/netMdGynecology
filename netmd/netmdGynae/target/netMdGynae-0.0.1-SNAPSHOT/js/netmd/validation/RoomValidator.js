function RoomValidator() {
	
	this.validate = function(room) {
		var error = new ErrorDTO();
		var errorMsgs = [];
			// for storing the error messages list having field,Message
		if(validator.isEmpty(room.getRoomNumber())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.ROOMNUMBER,constants.ROOMNUMBERREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		else if(validator.validateLimit(room.getRoomNumber())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(constants.ROOMNUMBER,constants.FIELDEXCEEDLIMIT);
			errorMsgs.push(errorMessage);
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
