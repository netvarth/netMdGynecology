function NewMedicalRecordUI(medicalRecordUIStartup) {
	this.newMedicalRecordPage = "#newMedicalRecord";
	this.medicalRecordModal = '#medicalRecordModal';
	this.errorHeader = $j('#medicalRecordModal #errorDivHeader');
	this.errorData = $j('#medicalRecordModal #errorDivNewMedicalRecord');
	this.createButton = $j("#newMedicalRecord #btnMedicalRecordCreate");
	this.cancelButton = $j('#newMedicalRecord #btnMedicalRecordCancel');
	this.patientName='#newMedicalRecord #patientName label';
	this.caseName='#newMedicalRecord #caseName label';
	this.doctor="#newMedicalRecord #doctor";
	this.medicalRecordType="#newMedicalRecord #medicalRecordType";
	this.medicalRecord="#newMedicalRecord #medicalRecord";
	this.patientId;
	this.caseId;
	this.doctorId;
	this.medicalRecordUIStartup = medicalRecordUIStartup;

}

NewMedicalRecordUI.prototype.getMedicalRecordUIStartup = function() {
	return this.medicalRecordUIStartup;
}

NewMedicalRecordUI.prototype.getMedicalRecordTableNavigator = function() {
	var medicalRecordUIStartup = this.getMedicalRecordUIStartup();
	return medicalRecordUIStartup.getMedicalRecordTableNavigator();
}

NewMedicalRecordUI.prototype.getMedicalRecordService = function() {
	var medicalRecordUIStartup = this.getMedicalRecordUIStartup();
	return medicalRecordUIStartup.getMedicalRecordService();
}
NewMedicalRecordUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newMedicalRecordPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewMedicalRecordUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewMedicalRecordUI.prototype.init = function(caseId,patientId) {
	var self=this;
	self.caseId=caseId;
	self.patientId=patientId;
	var medicalRecordService = self.getMedicalRecordService();	
	var patientDetail=medicalRecordService.getPatientDetail(self.patientId);
	var caseDetail=medicalRecordService.getCase(self.caseId);
	$j(self.patientName).text(patientDetail.firstName);
	$j(self.caseName).text(caseDetail.caseName);
	methodInvoker.fillDoctorAsAutoComplete(self.doctor);
	methodInvoker.fillMedicalTypesToControl(self.medicalRecordType);
}
NewMedicalRecordUI.prototype.getMedicalRecord = function() {
	var self=this;
	var patientName = $j(self.patientName).text();	
	var caseName = $j(self.caseName).text();
	var doctorName = $j(self.doctor).val();
	var doctorId = getDoctorIdUsingName(doctorName);
	var medicalRecordType=$j(self.medicalRecordType).val();
	var medicalRecord=$j(self.medicalRecord).val();
	var medicalRecordDTO= new MedicalRecordDTO();
	medicalRecordDTO.setDoctorId(doctorId);
	medicalRecordDTO.setPatientId(self.patientId);
	medicalRecordDTO.setCaseId(self.caseId);
	medicalRecordDTO.setCaseName(caseName);
	medicalRecordDTO.setDoctorName(doctorName);
	medicalRecordDTO.setMedicalRecordType(medicalRecordType);
	medicalRecordDTO.setMedicalRecord(medicalRecord);
	return medicalRecordDTO;
}

NewMedicalRecordUI.prototype.clearFields = function() {
	self = this;
	$j(self.newMedicalRecordPage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}

NewMedicalRecordUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newMedicalRecordPage + " " + self.inputFields);
	
	$j(self.newMedicalRecordPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.medicalRecordModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.medicalRecordModal + self.newMedicalRecordPage + " input[type=text]").val("");	
		$j(self.medicalRecordModal).trigger('reveal:close');
		$j(self.medicalRecordModal).remove();
		self=self.getMedicalRecordUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var medicalRecordObj = self.getMedicalRecord();
		var medicalRecordValidator = new MedicalRecordValidator();
		var error  = medicalRecordValidator.validate(medicalRecordObj);
		if(error.errorStatus==false) {
			var medicalRecordService = self.getMedicalRecordService();	
			var medicalRecordResponse = medicalRecordService.createMedicalRecord(medicalRecordObj);
			if(medicalRecordResponse.success==true) {
				showTip(constants.MEDICALRECORDCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var medicalRecordTableNavigator = self.getMedicalRecordTableNavigator();
				medicalRecordTableNavigator.list();
			} 
			else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(medicalRecordResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.medicalRecordModal + self.newMedicalRecordPage + " input[type=text]").val("");	
		$j(self.medicalRecordModal).trigger('reveal:close');
		$j(self.medicalRecordModal).remove();
		self=self.getMedicalRecordUIStartup();
	});	
}