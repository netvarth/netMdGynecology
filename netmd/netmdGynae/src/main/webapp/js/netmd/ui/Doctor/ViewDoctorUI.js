function ViewDoctorUI(doctorUIStartup) {
	this.viewDoctorPage = "#viewDoctor";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.firstName = "#viewDoctor #firstName";
	this.lastName = "#viewDoctor #lastName";
	this.phone="#viewDoctor #phone",
	this.mobile="#viewDoctor #mobile",
	this.dob="#viewDoctor #dob",
	this.viewGender="#viewDoctor #viewGender",
	this.lblGender="#viewDoctor #lblGender label",
	this.editGender="#viewDoctor #editGender",
	this.lblEditGender="#viewDoctor #lblEditGender",
	this.viewEducation="#viewDoctor #viewEducation";
	this.editEducation="#viewDoctor #editEducation";
	this.viewBloodGroup="#viewDoctor #viewBloodGrp";
	this.editBloodGroup="#viewDoctor #editBloodGrp";
	this.bloodGrouplbl="#viewDoctor #bloodGrouplbl label";
	this.educationlbl="#viewDoctor #educationlbl label"
	this.rhlbl="#viewDoctor #rhlbl label"
	this.viewRh="#viewDoctor #viewRh";
	this.editRh="#viewDoctor #editRh";
	this.email="#viewDoctor #email",
	this.age="#viewDoctor #age",
	this.education="#viewDoctor #education",
	this.rh="#viewDoctor #rh";
	this.bloodGroup="#viewDoctor #bloodGroup"
	this.address="#viewDoctor #address",
	this.allergies="#viewDoctor #allergies";
	this.familyHistory="#viewDoctor #familyHistory";
	this.ailment="#viewDoctor #ailment";
	this.diagnosis="#viewDoctor #diagnosis";
	this.chronicDisease="#viewDoctor #chronicDiseases";
	this.updateButton = '#viewDoctor #btnDoctorSave';
	this.editButton = '#viewDoctor #btnDoctorEdit';
	this.cancelButton = '#viewDoctor #btnDoctorCancel'; 
	this.ptbBack = "#doctorGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#doctorGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#doctorGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.doctorId;
	this.doctorUIStartup=doctorUIStartup;
	this.viewDoctorPTB = new ViewDoctorPTB(this);
	this.rhvalue;
	this.bloodGroupvalue;
	this.educationvalue;
	this.gender;
}
ViewDoctorUI.prototype.getDoctorUIStartup = function() {
	return this.doctorUIStartup;
}

ViewDoctorUI.prototype.getViewDoctorPTB = function() {
	return this.viewDoctorPTB;
}

ViewDoctorUI.prototype.getDoctorTableNavigator = function() {
	var doctorUIStartup = this.getDoctorUIStartup();
	return doctorUIStartup.getDoctorTableNavigator();
}

ViewDoctorUI.prototype.getDoctorService = function() {
	var doctorUIStartup = this.getDoctorUIStartup();
	return doctorUIStartup.getDoctorService();
}

ViewDoctorUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}


ViewDoctorUI.prototype.init = function(doctorId) {
	var self = this;
	var viewDoctorPTB = self.getViewDoctorPTB();
	viewDoctorPTB.init(self);
	pageHandler.create(constants.VIEWDOCTOR);
	self.bindEvents();
	self.ViewDoctor(doctorId);
}

ViewDoctorUI.prototype.ViewDoctor = function(doctorId) {
	var self=this;
	var header = constants.DOCTORVIEWTITLE;
	var doctorService = self.getDoctorService();
	var response = doctorService.viewDoctor(doctorId);
	if(response.success==false) {
		self.setDoctor(response);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
	}
	self.setPageTitle(header);
}

ViewDoctorUI.prototype.setDoctor = function(doctor) {
	var self=this;
	self.doctorId=doctor.id;
	$j(self.firstName).val(doctor.firstName);
	$j(self.lastName).val(doctor.lastName);
	$j(self.phone).val(doctor.phone);
	$j(self.mobile).val(doctor.mobile);
	$j(self.email).val(doctor.email);
	$j(self.dob).val(doctor.dob);
	$j(self.age).val(doctor.age);
	$j(self.lblGender).text(doctor.gender);
	$j(self.ailment).val(doctor.ailment);
	$j(self.diagnosis).val(doctor.diagnosis);
	$j(self.chronicDisease).val(doctor.chronicDisease);
	$j(self.address).val(doctor.address);
	$j(self.description).val(doctor.description);
	$j(self.educationlbl).text(doctor.education);
	$j(self.bloodGrouplbl).text(doctor.bloodGroup);
	$j(self.rhlbl).text(doctor.rh);
	$j(self.allergies).val(doctor.allergies);
	$j(self.familyHistory).val(doctor.familyHistory);
	self.rhvalue=doctor.rh;
	self.bloodGroupvalue=doctor.bloodGroup;
	self.educationvalue=doctor.education;
	self.gender=doctor.gender;
}

ViewDoctorUI.prototype.getDoctor = function() {
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
	var gender=$j(self.viewDoctorPage +' input[type="radio"]:checked').val();
	var ailment=$j(self.ailment).val();
	var chronicDisease=$j(self.chronicDisease).val();
	var diagnosis=$j(self.diagnosis).val();
	var allergies=$j(self.allergies).val();
	var familyHistory=$j(self.familyHistory).val();
	var doctor = new DoctorDTO();
	doctor.setId(self.doctorId);
	doctor.setFirstName(firstName);
	doctor.setLastName(lastName);
	doctor.setPhone(phone);
	doctor.setMobile(mobile);
	if(dob!="")
	doctor.setDob(dob);
	doctor.setAge(parseInt(age));
	doctor.setEmail(email);
	if(education!="select")
	doctor.setEducation(education);
	if(rh!="select")
	doctor.setRh(rh);
	doctor.setGender(gender);
	if(bloodGroup!="select")
	doctor.setBloodGroup(bloodGroup);
	doctor.setAddress(address);
	doctor.setChronicDisease(chronicDisease);
	doctor.setAllergies(allergies);
	doctor.setDiagnosis(diagnosis);
	doctor.setFamilyHistory(familyHistory);
	doctor.setAilment(ailment);
	doctor.setMedicalHistory(medicalhist);
	return doctor;
}




ViewDoctorUI.prototype.writable = function() {
	self=this;
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewDoctorPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewDoctorPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewDoctorPage + " input[type=text],textarea").removeAttr('disabled');
	$j(self.dob).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+0'
	});
}

ViewDoctorUI.prototype.readable = function() {
	var self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.editGender).hide();
	$j(self.viewGender).show();
	$j(self.editEducation).hide();
	$j(self.viewEducation).show();
	$j(self.editBloodGroup).hide();
	$j(self.viewBloodGroup).show();
	$j(self.editRh).hide();
	$j(self.viewRh).show();
	$j(self.viewDoctorPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewDoctorPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewDoctorPage + " input[type=text],textarea").attr('disabled',true);
}

ViewDoctorUI.prototype.getPrevId = function(curId,doctorResult) {
	var prevId;
	$j(doctorResult.doctorlist).each(function (index, rowDoctor) {
		if(curId==rowDoctor.id)	{
			var arrayLength=(doctorResult.doctorlist).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=doctorResult.doctorlist[index-1].id;
		}
	});
	return prevId;	
}
	
ViewDoctorUI.prototype.getNextId = function(curId,doctorResult) {
	var nextId;
	$j(doctorResult.doctorlist).each(function (index, rowDoctor) {
		if(curId==rowDoctor.id)	{
			var arrayLength=(doctorResult.doctorlist).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=doctorResult.doctorlist[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewDoctorUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.viewGender).hide();
		$j(self.editGender).show();
		$j(self.viewEducation).hide();
		$j(self.editEducation).show();
		$j(self.viewBloodGroup).hide();
		$j(self.editBloodGroup).show();
		$j(self.viewRh).hide();
		$j(self.editRh).show();
		if(self.gender=="Male")
			$j("#male").attr('checked','true');
		else
			$j("#female").attr('checked','true');
		$j(self.bloodGroup+" option[value="+self.bloodGroupvalue+"]").attr('selected','selected');
		$j(self.education+" option[value="+self.educationvalue+"]").attr('selected','selected');
		$j(self.rh+" option[value="+self.rhvalue+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.ViewDoctor(self.doctorId);
		self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var doctor = self.getDoctor();
		var doctorValidator = new DoctorValidator();
		var error  = doctorValidator.validate(doctor);
		if(error.errorStatus==false) {
			var doctorService =self.getDoctorService();
			var doctorResponse = doctorService.updateDoctor(doctor);
			if(doctorResponse.success==true) {
				showTip(constants.DOCTORUPDATESUCCESS);//For showing the global Tip
				self.ViewDoctor(doctorResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(doctorResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	
}