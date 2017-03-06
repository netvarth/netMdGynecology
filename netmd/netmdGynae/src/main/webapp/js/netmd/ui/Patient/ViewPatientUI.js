function ViewPatientUI(patientUIStartup) {

	this.viewPatientPage = "#viewPatient";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.firstName = "#viewPatient #firstName";
	this.lastName = "#viewPatient #lastName";
	this.phone="#viewPatient #phone",
	this.mobile="#viewPatient #mobile",
	this.dob="#viewPatient #dob",
	this.viewGender="#viewPatient #viewGender",
	this.lblGender="#viewPatient #lblGender label",
	this.editGender="#viewPatient #editGender",
	this.lblEditGender="#viewPatient #lblEditGender",
	this.viewEducation="#viewPatient #viewEducation";
	this.editEducation="#viewPatient #editEducation";
	this.viewBloodGroup="#viewPatient #viewBloodGrp";
	this.editBloodGroup="#viewPatient #editBloodGrp";
	this.bloodGrouplbl="#viewPatient #bloodGrouplbl label";
	this.educationlbl="#viewPatient #educationlbl label"
	this.rhlbl="#viewPatient #rhlbl label"
	this.viewRh="#viewPatient #viewRh";
	this.editRh="#viewPatient #editRh";
	this.email="#viewPatient #email",
	this.age="#viewPatient #age",
	this.education="#viewPatient #education",
	this.rh="#viewPatient #rh";
	this.bloodGroup="#viewPatient #bloodGroup"
	this.address="#viewPatient #address",
	this.allergies="#viewPatient #allergies";
	this.familyHistory="#viewPatient #familyHistory";
	this.ailment="#viewPatient #ailment";
	this.diagnosis="#viewPatient #diagnosis";
	this.chronicDisease="#viewPatient #chronicDiseases";
	this.updateButton = '#viewPatient #btnPatientSave';
	this.editButton = '#viewPatient #btnPatientEdit';
	this.cancelButton = '#viewPatient #btnPatientCancel'; 
	this.ptbBack = "#patientGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#patientGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#patientGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.patientId;
	this.patientUIStartup=patientUIStartup;
	this.viewPatientPTB = new ViewPatientPTB(this);
	this.rhvalue;
	this.bloodGroupvalue;
	this.educationvalue;
	this.gender;
}
ViewPatientUI.prototype.getPatientUIStartup = function() {
	return this.patientUIStartup;
}

ViewPatientUI.prototype.getViewPatientPTB = function() {
	return this.viewPatientPTB;
}

ViewPatientUI.prototype.getPatientTableNavigator = function() {
	var patientUIStartup = this.getPatientUIStartup();
	return patientUIStartup.getPatientTableNavigator();
}

ViewPatientUI.prototype.getPatientService = function() {
	var patientUIStartup = this.getPatientUIStartup();
	return patientUIStartup.getPatientService();
}

ViewPatientUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}


ViewPatientUI.prototype.init = function(patientId) {
	var self = this;
	var viewPatientPTB = self.getViewPatientPTB();
	viewPatientPTB.init(self);
	pageHandler.create(constants.VIEWPATIENT);
	self.bindEvents();
	self.ViewPatient(patientId);
}

ViewPatientUI.prototype.ViewPatient = function(patientId) {
	var self=this;
	var header = constants.PATIENTVIEWTITLE;
	var patientService = self.getPatientService();
	var response = patientService.viewPatient(patientId);
	if(response.success==false) {
		self.setPatient(response);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
	}
	self.setPageTitle(header);
}

ViewPatientUI.prototype.setPatient = function(patient) {
	var self=this;
	self.patientId=patient.id;
	$j(self.firstName).val(patient.firstName);
	$j(self.lastName).val(patient.lastName);
	$j(self.phone).val(patient.phone);
	$j(self.mobile).val(patient.mobile);
	$j(self.email).val(patient.email);
	$j(self.dob).val(patient.dob);
	$j(self.age).val(patient.age);
	$j(self.lblGender).text(patient.gender);
	$j(self.ailment).val(patient.ailment);
	$j(self.diagnosis).val(patient.diagnosis);
	$j(self.chronicDisease).val(patient.chronicDisease);
	$j(self.address).val(patient.address);
	$j(self.description).val(patient.description);
	$j(self.educationlbl).text(patient.education);
	$j(self.bloodGrouplbl).text(patient.bloodGroup);
	$j(self.rhlbl).text(patient.rh);
	$j(self.allergies).val(patient.allergies);
	$j(self.familyHistory).val(patient.familyHistory);
	self.rhvalue=patient.rh;
	self.bloodGroupvalue=patient.bloodGroup;
	self.educationvalue=patient.education;
	self.gender=patient.gender;
}

ViewPatientUI.prototype.getPatient = function() {
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
	var gender=$j(self.viewPatientPage +' input[type="radio"]:checked').val();
	var ailment=$j(self.ailment).val();
	var chronicDisease=$j(self.chronicDisease).val();
	var diagnosis=$j(self.diagnosis).val();
	var allergies=$j(self.allergies).val();
	var familyHistory=$j(self.familyHistory).val();
	var patient = new PatientDTO();
	patient.setId(self.patientId);
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
	patient.setChronicDisease(chronicDisease);
	patient.setAllergies(allergies);
	patient.setDiagnosis(diagnosis);
	patient.setFamilyHistory(familyHistory);
	patient.setAilment(ailment);
	patient.setMedicalHistory(medicalhist);
	return patient;
}




ViewPatientUI.prototype.writable = function() {
	self=this;
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewPatientPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewPatientPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewPatientPage + " input[type=text],textarea").removeAttr('disabled');
	$j(self.dob).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+0'
	});
}

ViewPatientUI.prototype.readable = function() {
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
	$j(self.viewPatientPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewPatientPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewPatientPage + " input[type=text],textarea").attr('disabled',true);
}

ViewPatientUI.prototype.getPrevId = function(curId,patientResult) {
	var prevId;
	$j(patientResult.patientlist).each(function (index, rowPatient) {
		if(curId==rowPatient.id)	{
			var arrayLength=(patientResult.patientlist).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=patientResult.patientlist[index-1].id;
		}
	});
	return prevId;	
}
	
ViewPatientUI.prototype.getNextId = function(curId,patientResult) {
	var nextId;
	$j(patientResult.patientlist).each(function (index, rowPatient) {
		if(curId==rowPatient.id)	{
			var arrayLength=(patientResult.patientlist).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=patientResult.patientlist[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewPatientUI.prototype.bindEvents = function() {
	self = this;	
	
	  $j(self.dob).change(function(){
		var dob=$j(this).val();
		var yearOfBirth = dob.slice(0,4)
		Currentyear=new Date().getFullYear();
		var calculatedAge=Currentyear-yearOfBirth;
	    $j(self.age).val(calculatedAge);
	});  
	
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
		self.ViewPatient(self.patientId);
		self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var patient = self.getPatient();
		var patientValidator = new PatientValidator();
		var error  = patientValidator.validate(patient);
		if(error.errorStatus==false) {
			var patientService =self.getPatientService();
			var patientResponse = patientService.updatePatient(patient);
			if(patientResponse.success==true) {
				showTip(constants.PATIENTUPDATESUCCESS);//For showing the global Tip
				self.ViewPatient(patientResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(patientResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	
}