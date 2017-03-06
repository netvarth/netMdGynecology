function NewDoctorUI(doctorUIStartup) {
	this.createButton = $j("#newDoctor #btnCreateDoctor");
	this.cancelButton = $j('#newDoctor #btnCancelDoctor');
	this.newDoctorPage = "#newDoctor";
	this.doctorModal = '#doctorModal';
	this.errorHeader = $j('#doctorModal #errorDivHeader');
	this.errorData = $j('#doctorModal #errorDivNewDoctor');
	this.honorific = '#newDoctor #honorific';
	this.specialization="#newDoctor #specialization";
	this.firstName="#newDoctor #firstName";
	this.lastName="#newDoctor #lastName";
	this.age="#newDoctor #age";
	this.dob="#newDoctor #dob";
	this.userName="#newDoctor #username";
	this.gender="#newDoctor #gender";
	this.phone="#newDoctor #phone";
	this.mobile="#newDoctor #mobile";
	this.email="#newDoctor #email";
	this.address="#newDoctor #address";
	this.inputFields = ":input";
	this.doctorUIStartup = doctorUIStartup;
	
}

NewDoctorUI.prototype.getDoctorUIStartup = function() {
	return this.doctorUIStartup;
}

NewDoctorUI.prototype.getDoctorTableNavigator = function() {
	var doctorUIStartup = this.getDoctorUIStartup();
	return doctorUIStartup.getDoctorTableNavigator();
}

NewDoctorUI.prototype.getDoctorService = function() {
	var doctorUIStartup = this.getDoctorUIStartup();
	return doctorUIStartup.getDoctorService();
}


NewDoctorUI.prototype.getDoctor = function() {
	var self=this;
	var firstName = $j(self.firstName).val();	
	var lastName=$j(self.lastName).val();
	var phone=$j(self.phone).val();
	var mobile=$j(self.mobile).val();
	var email=$j(self.email).val();
	var dob=$j(self.dob).val();
	var address=$j(self.address).val();
	var gender=$j(self.newDoctorPage +' input[name="gender"]:checked').val();
	var doctor = new DoctorDTO();
	doctor.setFirstName(firstName);
	doctor.setLastName(lastName);
	doctor.setPhone(phone);
	doctor.setMobile(mobile);
	if(dob!="")
		doctor.setDob(dob);
	doctor.setEmail(email);
	doctor.setGender(gender);
	doctor.setAddress(address);
	var loginDTO=new LoginDTO();
	var isAdmin=$j('#check').is(':checked'); 
	alert(isAdmin);
	var userType="";
	if(isAdmin=="checked")
		userType = "admin";
	else
		userType ="doctor";
	var userName=$j('#username').val();
	loginDTO.setUserType(userType);
	loginDTO.setUserName(userName);
	doctor.setLogin(loginDTO); 
	return doctor;
}

NewDoctorUI.prototype.clearFields = function() {
	self = this;
	$j(self.newDoctorPage + " input[type=text],textarea").val("");
	$j(self.firstName ).focus();
}

NewDoctorUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewDoctorUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newDoctorPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewDoctorUI.prototype.init = function() {
	self=this;
	$j(self.dob).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+0'
	});
}

NewDoctorUI.prototype.bindEvents = function() {
	self = this;
	//self.removecolors(self.newDoctorPage + " " + self.inputFields);
	
	$j(self.newDoctorPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.email).focusout(function(){
		var email=$j(this).val();
	    $j(self.userName).val(email);
	});
	
	$j(self.doctorModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.doctorModal + self.newDoctorPage + " input[type=text]").val("");	
		$j(self.doctorModal).trigger('reveal:close');
		$j(self.doctorModal).remove();
		self=self.getDoctorUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var doctor = self.getDoctor();
		alert(JSON.stringify(doctor));
		var doctorValidator = new DoctorValidator();
		var error  = doctorValidator.validate(doctor);
		if(error.errorStatus==false) {
			var doctorService = self.getDoctorService();	
			var doctorResponse = doctorService.createDoctor(doctor);
			alert(JSON.stringify(doctorResponse));
			if(doctorResponse.success==true) {
				showTip(constants.DOCTORCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var doctorTableNavigator = self.getDoctorTableNavigator();
				doctorTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(doctorResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.doctorModal + self.newDoctorPage + " input[type=text]").val("");	
		$j(self.doctorModal).trigger('reveal:close');
		$j(self.doctorModal).remove();
		self=self.getDoctorUIStartup();
	});	
	
}