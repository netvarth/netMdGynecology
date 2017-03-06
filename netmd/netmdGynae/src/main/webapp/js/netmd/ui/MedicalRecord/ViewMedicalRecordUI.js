function ViewMedicalRecordUI(medicalRecordUIStartup) {
	this.viewMedicalRecordPage = "#viewMedicalRecord";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
    this.patientName='#viewMedicalRecord #patientName label';
	this.caseName='#viewMedicalRecord #caseName label';
	this.editDoctor='#viewMedicalRecord #editDoctor';
	this.viewDoctor='#viewMedicalRecord #viewDoctor';
	this.medicalRecordId;
	this.caseId;
	this.patientId;
	this.medicalRecordTypeVal;
	this.doctorVal;
	this.editMedicalRecordType=' #viewMedicalRecord #editMedicalRecordType';
	this.viewMedicalRecordType=' #viewMedicalRecord #viewMedicalRecordType';
	this.medicalRecordTypelbl='#viewMedicalRecord #medicalRecordTypelbl label';
	this.doctor = '#viewMedicalRecord #doctor';
	this.doctorLabel = '#viewMedicalRecord #doctorlbl label';
	this.medicalRecordType = '#viewMedicalRecord #medicalRecordType';
	this.medicalRecord='#viewMedicalRecord #medicalRecord';
	this.updateButton = '#viewMedicalRecord #btnMedicalRecordSave';
	this.editButton = '#viewMedicalRecord #btnMedicalRecordEdit';
	this.cancelButton = '#viewMedicalRecord #btnMedicalRecordCancel'; 
	this.ptbBack = "#medicalRecordGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#medicalRecordGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#medicalRecordGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.medicalRecordUIStartup=medicalRecordUIStartup;
	this.viewMedicalRecordPTB = new ViewMedicalRecordPTB(this);
}
ViewMedicalRecordUI.prototype.getMedicalRecordUIStartup = function() {
	return this.medicalRecordUIStartup;
}

ViewMedicalRecordUI.prototype.getViewMedicalRecordPTB = function() {
	return this.viewMedicalRecordPTB;
}

ViewMedicalRecordUI.prototype.getMedicalRecordTableNavigator = function() {
	var medicalRecordUIStartup = this.getMedicalRecordUIStartup();
	return medicalRecordUIStartup.getMedicalRecordTableNavigator();
}

ViewMedicalRecordUI.prototype.getMedicalRecordService = function() {
	var medicalRecordUIStartup = this.getMedicalRecordUIStartup();
	return medicalRecordUIStartup.getMedicalRecordService();
}
ViewMedicalRecordUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewMedicalRecordPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewMedicalRecordUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}


ViewMedicalRecordUI.prototype.init = function(medicalRecordId,caseId,patientId) {
	var self = this;
	self.patientId=patientId;
	self.caseId=caseId;
	var viewMedicalRecordPTB = self.getViewMedicalRecordPTB();
	viewMedicalRecordPTB.init(self);
	pageHandler.create(constants.VIEWMEDICALRECORD);
	self.ViewMedicalRecord(medicalRecordId,caseId,patientId);
	self.bindEvents(medicalRecordId);
}

ViewMedicalRecordUI.prototype.ViewMedicalRecord = function(medicalRecordId,caseId,patientId) {
	//alert("View");
	var self=this;
	var medicalRecordService = self.getMedicalRecordService();
	var patientDetail=medicalRecordService.getPatientDetail(patientId);
	var caseDetail=medicalRecordService.getCase(caseId);
	var header = constants.VIEWMEDICALRECORDTITLE;
	var response = medicalRecordService.viewMedicalRecord(medicalRecordId);
	//alert(JSON.stringify(response));
	if(response.success==false) {
		self.setMedicalRecord(response,patientDetail.firstName,caseDetail.caseName);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
	}
	self.setPageTitle(header);
}

ViewMedicalRecordUI.prototype.setMedicalRecord = function(medicalRecord,patientName,caseName) {
	var self=this;
	self.medicalRecordId=medicalRecord.id;
	$j(self.patientName).text(patientName);
	$j(self.caseName).text(caseName);
	$j(self.medicalRecord).val(medicalRecord.medicalRecord);
	$j(self.medicalRecordTypelbl).text(medicalRecord.type);
	$j(self.doctorLabel).text(medicalRecord.doctorName);
	self.medicalRecordTypeVal=medicalRecord.type;
	self.doctorVal=medicalRecord.doctorName;
}

ViewMedicalRecordUI.prototype.getMedicalRecord = function(medicalRecordId) {
	var self=this;
	var patientName = $j(self.patientName).text();	
	var caseName = $j(self.caseName).text();
	var doctorName = $j(self.doctor).val();
	var doctorId = getDoctorIdUsingName(doctorName);
	var medicalRecordType=$j(self.medicalRecordType).val();
	var medicalRecord=$j(self.medicalRecord).val();
	
	var medicalRecordDTO= new MedicalRecordDTO();
	medicalRecordDTO.setId(medicalRecordId);
	medicalRecordDTO.setDoctorId(doctorId);
	medicalRecordDTO.setPatientId(self.patientId);
	medicalRecordDTO.setCaseId(self.caseId);
	medicalRecordDTO.setCaseName(caseName);
	medicalRecordDTO.setDoctorName(doctorName);
	medicalRecordDTO.setMedicalRecordType(medicalRecordType);
	medicalRecordDTO.setMedicalRecord(medicalRecord);
	return medicalRecordDTO;
}
ViewMedicalRecordUI.prototype.writable = function() {
	self=this;
	$j(self.editButton).hide();
	$j(self.viewDoctor).hide();
	$j(self.editDoctor).show();
	$j(self.viewMedicalRecordType).hide();
	$j(self.editMedicalRecordType).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").removeAttr('disabled');
	methodInvoker.fillDoctorAsAutoComplete(self.doctor);
	methodInvoker.fillMedicalTypesToControl(self.medicalRecordType);
	$j(self.medicalRecordType+" option[value="+self.medicalRecordTypeVal+"]").attr('selected','selected');
	$j(self.doctor).val(self.doctorVal);
}
ViewMedicalRecordUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.editDoctor).hide();
	$j(self.viewDoctor).show();
	$j(self.editMedicalRecordType).hide();
	$j(self.viewMedicalRecordType).show();
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewMedicalRecordPage + " input[type=text],textarea").attr('disabled',true);
}

ViewMedicalRecordUI.prototype.getPrevId = function(curId,medicalRecordResult) {
	var prevId;
	$j(medicalRecordResult.medicalRecordlist).each(function (index, rowMedicalRecord) {
		if(curId==rowMedicalRecord.id)	{
			var arrayLength=(medicalRecordResult.medicalRecordlist).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=medicalRecordResult.medicalRecordlist[index-1].id;
		}
	});
	return prevId;	
}
	
ViewMedicalRecordUI.prototype.getNextId = function(curId,medicalRecordResult) {
	var nextId;
	$j(medicalRecordResult.medicalRecordlist).each(function (index, rowMedicalRecord) {
		if(curId==rowMedicalRecord.id)	{
			var arrayLength=(medicalRecordResult.medicalRecordlist).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=medicalRecordResult.medicalRecordlist[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewMedicalRecordUI.prototype.bindEvents = function(medicalRecordId) {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	self.ViewMedicalRecord(self.medicalRecordId,self.caseId,self.patientId);
	self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var medicalRecordObj = self.getMedicalRecord(medicalRecordId);
		var medicalRecordValidator = new MedicalRecordValidator();
		var error  = medicalRecordValidator.validate(medicalRecordObj);
		if(error.errorStatus==false) {
			var medicalRecordService =self.getMedicalRecordService();
			var medicalRecordResponse = medicalRecordService.updateMedicalRecord(medicalRecordObj);
			if(medicalRecordResponse.success==true) {
				showTip(constants.MEDICALRECORDUPDATESUCCESS);//For showing the global Tip
				self.readable();
				self.ViewMedicalRecord(medicalRecordResponse.id,self.caseId,self.patientId);
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(medicalRecordResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	
}