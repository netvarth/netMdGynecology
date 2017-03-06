function MedicalRecordUIStartup(patientId) {
	this.pgTableName = "#medicalRecord";
	this.pgTableContainer="#medicalRecordListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#medicalRecordPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#medicalRecordPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#medicalRecordPTBContainer #btn_delete_ptb_id');
	this.ptbCaseListbackbtn=$j('#medicalRecordPTBContainer #btn_back_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.medicalRecordIdCol';
	this.patientName;
	this.caseName;
	this.caseId;
	this.patientId;
	this.exp = new ExpressionListDTO();
	this.medicalRecordService = new MedicalRecordServiceImpl();
	this.listUrl = constants.MEDICALRECORDLISTURL;
	this.medicalRecordTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.medicalRecordService,this.exp);
	this.ftbToolBar = '#medicalRecord-filter-toolbar';
	this.filter = '#filter';
	this.patientId=patientId;
	this.viewMedicalRecordUI = new ViewMedicalRecordUI(this);
}

MedicalRecordUIStartup.prototype.setMedicalRecordTableNavigator = function(medicalRecordTableNavigator) {
	this.medicalRecordTableNavigator = medicalRecordTableNavigator;
}
MedicalRecordUIStartup.prototype.getMedicalRecordService = function() {
	return this.medicalRecordService;
}
MedicalRecordUIStartup.prototype.getMedicalRecordTableNavigator = function() {
	return this.medicalRecordTableNavigator;
}
MedicalRecordUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
MedicalRecordUIStartup.prototype.getViewMedicalRecordUI = function() {
	return this.viewMedicalRecordUI;
} 

MedicalRecordUIStartup.prototype.createMedicalRecordModal = function(obj,caseId,patientId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.CREATEMEDICALRECORDUI,constants.MEDICALRECORDMODEL);		
	openModalBox(obj,constants.MEDICALRECORDMODEL);
	var newMedicalRecordUI = new NewMedicalRecordUI(self);
	newMedicalRecordUI.init(caseId,patientId);
	newMedicalRecordUI.bindEvents();
}


MedicalRecordUIStartup.prototype.init = function(caseId,patientId) {
	var self = this;
	self.caseId = caseId;
	self.patientId = patientId;
	var medicalRecordTableNavigator = self.getMedicalRecordTableNavigator();
	var medicalService=self.getMedicalRecordService();
	var patientDetail=medicalService.getPatientDetail(patientId);
	var caseDetail=medicalService.getCase(caseId);
	self.patientName=patientDetail.firstName;
	self.caseName=caseDetail.caseName;
	self.setPageTitle(constants.MEDICALRECORDTITLE+'[PatientName: '+self.patientName+', Case:'+self.caseName+']');
	var expList = new ExpressionListDTO();
	var exp1 = new ExpressionDTO("patientId",patientId,"eq");
	var exp2 = new ExpressionDTO("caseId",caseId,"eq");
	var exp3 = new ExpressionDTO("status","active","eq");
	expList.add(exp1);
	expList.add(exp2);
	expList.add(exp3);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.MEDICALRECORD,constants.MEDICALRECORDPAGETOOLBAR); //Create the Page tool Bar for Area/* 
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.MEDICALRECORD,constants.MEDICALRECORDFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents(patientId,caseId);
	dataTableProcessor.create(self.pgTableName,constants.MEDICALRECORDTABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	medicalRecordTableNavigator.setExp(expList);
	medicalRecordTableNavigator.list();
	self.bindEvents(patientId,caseId);
	
}

 MedicalRecordUIStartup.prototype.bindToolBarEvents = function(patientId,caseId) {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEMEDICALRECORDUI,constants.MEDICALRECORDMODEL);		
		openModalBox(obj,constants.MEDICALRECORDMODEL);
		var newMedicalRecordUI = new NewMedicalRecordUI(self);
		newMedicalRecordUI.init(caseId,patientId);
		newMedicalRecordUI.bindEvents();
	});
	 self.ptbCaseListbackbtn.die('click').live('click',function() {
        commonMethodInvoker.removeErrors();
		var caseUI = new CaseUIStartup(patientId);
		caseUI.init(patientId);
		
	});
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var medicalRecordId=self.getSelectedMedicalRecordId(self.pgTableName);
		$j('#' + constants.MEDICALRECORD + '-filter-cont').hide();
		$j(self.filter).hide();
		if(medicalRecordId!="") {
			var viewMedicalRecordUI = self.getViewMedicalRecordUI();
			viewMedicalRecordUI.init(medicalRecordId,caseId,patientId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var medicalRecordId=self.getSelectedMedicalRecordId(self.pgTableName);
		if(medicalRecordId!="") {
			var medicalRecordService = self.getMedicalRecordService();
			var medicalRecordResponse = medicalRecordService.viewMedicalRecord(medicalRecordId);
			var confirmStatus = confirm(constants.MEDICALRECORDDELETECONFIRM + medicalRecordResponse.id);
			if(confirmStatus==true) {				
				var response = medicalRecordService.deleteMedicalRecord(medicalRecordId);
				if(response.success==true) {
					showTip(constants.MEDICALRECORDDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var medicalRecordTableNavigator = self.getMedicalRecordTableNavigator();
				medicalRecordTableNavigator.list();
				
			}
		}	
	});
	
}
MedicalRecordUIStartup.prototype.getSelectedMedicalRecordId = function () {
	var medicalRecordId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selMedicalRecord = $j(this.pgTableName + ' tbody tr[selected]');
		if(selMedicalRecord.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEMEDICALRECORD);
		} else if(selMedicalRecord.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEMEDICALRECORDONLY);
		else
			medicalRecordId=selMedicalRecord.attr('id');
	}
	return medicalRecordId;
}
MedicalRecordUIStartup.prototype.bindEvents = function(patientId,caseId) {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(self.pgTableRowClass).die('click').live('click',function(){
	   var medicalRecordId= $j(this).parent().attr('id');
	   $j('#' + constants.MEDICALRECORD + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(medicalRecordId!="") {
			$j('#medicalRecord-filter-wb').hide();
			var viewMedicalRecordUI = self.getViewMedicalRecordUI();
			viewMedicalRecordUI.init(medicalRecordId,caseId,patientId);
		}	
	});
} 