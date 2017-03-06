function NewPatientUI(patientUIStartup) {


	this.createButton = $j("#newPatient #btnCreatePatient");
	this.cancelButton = $j('#newPatient #btnCancelPatient');
	this.newPatientPage = "#newPatient";
	this.patientModal = '#patientModal';
	this.errorHeader = $j('#patientModal #errorDivHeader');
	this.errorData = $j('#patientModal #errorDivNewPatient');
	this.firstName="#newPatient #firstName";
	this.lastName="#newPatient #lastName";
	this.age="#newPatient #age";
	this.dob="#newPatient #dob";
	this.email="#newPatient #email";
	this.bloodGroup="#newPatient #bloodGroup";
	this.gender="#newPatient #gender";
	this.rh="#newPatient #rh";
	this.education="#newPatient #education";
	this.phone="#newPatient #phone";
	this.mobile="#newPatient #mobile";
	this.email="#newPatient #email";
	this.address="#newPatient #address";
	this.inputFields = ":input";
	this.patientUIStartup = patientUIStartup;

}

NewPatientUI.prototype.getPatientUIStartup = function() {
	return this.patientUIStartup;
}

NewPatientUI.prototype.getPatientTableNavigator = function() {
	var patientUIStartup = this.getPatientUIStartup();
	return patientUIStartup.getPatientTableNavigator();
}

NewPatientUI.prototype.getPatientService = function() {
	var patientUIStartup = this.getPatientUIStartup();
	return patientUIStartup.getPatientService();
}


NewPatientUI.prototype.getPatient = function() {
	var self=this;
	var medicalhist=[];
	var firstName = $j(self.firstName).val();	
	var lastName=$j(self.lastName).val();
	var phone=$j(self.phone).val();
	var mobile=$j(self.mobile).val();
	var email=$j(self.email).val();
	var age=$j(self.age).val();
	var dob=$j(self.dob).val();
	var bloodGroup=$j(self.bloodGroup +" option:selected").val();

	var rh=$j(self.rh +" option:selected").val();
	var education=$j(self.education +" option:selected").val();
	var address=$j(self.address).val();
	var gender=$j(self.newPatientPage +' input[name="gender"]:checked').val();
	var patient = new PatientDTO();
	patient.setFirstName(firstName);
	patient.setLastName(lastName);
	patient.setPhone(phone);
	patient.setMobile(mobile);
	if(dob!="")
	patient.setDob(dob);
	patient.setAge(parseInt(age));
	patient.setEmail(email);
	if(education!="select")
	patient.setEducation(education);
	if(rh!="select")
	patient.setRh(rh);
	patient.setGender(gender);
	if(bloodGroup!="select")
	 patient.setBloodGroup(bloodGroup);
	patient.setAddress(address);
	patient.setMedicalHistory(medicalhist);
	return patient;
}

NewPatientUI.prototype.clearFields = function() {
	self = this;
	$j(self.newPatientPage + " input[type=text],textarea").val("");
	$j(self.education + " option[value='select']").attr("selected","selected");
	$j(self.rh + " option[value='select']").attr("selected","selected");
	$j(self.bloodGroup + " option[value='select']").attr("selected","selected");
	$j(self.firstName ).focus();
}

NewPatientUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewPatientUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newPatientPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewPatientUI.prototype.init = function() {
	self=this;
	$j(self.dob).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+0'
	});
}

NewPatientUI.prototype.bindEvents = function() {
	self = this;
	//self.removecolors(self.newPatientPage + " " + self.inputFields);
	
 
	/*  $j(self.dob).focusout(function(){
		var dob=$j(this).val();
		var yearOfBirth = dob.slice(0,4)
		alert(yearOfBirth);
		Currentyear=new Date().getFullYear();
		alert(Currentyear);
		var calculatedAge=Currentyear-yearOfBirth;
		alert(calculatedAge);
	    $j(self.age).val(calculatedAge);
	});   */

	  $j(self.dob).change(function(){
		var dob=$j(this).val();
		var yearOfBirth = dob.slice(0,4)
		Currentyear=new Date().getFullYear();
		var calculatedAge=Currentyear-yearOfBirth;
	    $j(self.age).val(calculatedAge);
	});  
	
	$j(self.newPatientPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.patientModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.patientModal + self.newPatientPage + " input[type=text]").val("");	
		$j(self.patientModal).trigger('reveal:close');
		$j(self.patientModal).remove();
		self=self.getPatientUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var patient = self.getPatient();
		var patientValidator = new PatientValidator();
		var error  = patientValidator.validate(patient);
		if(error.errorStatus==false) {
			var patientService = self.getPatientService();	
			var patientResponse = patientService.createPatient(patient);
			if(patientResponse.success==true) {
				showTip(constants.PATIENTCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var patientTableNavigator = self.getPatientTableNavigator();
				patientTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(patientResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.patientModal + self.newPatientPage + " input[type=text]").val("");	
		$j(self.patientModal).trigger('reveal:close');
		$j(self.patientModal).remove();
		self=self.getPatientUIStartup();
	});	
	
}