function CaseUIStartup(patientId) {
	this.pgTableName = "#case";
	this.pgTableContainer="#caseListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#casePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#casePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#casePTBContainer #btn_delete_ptb_id');
	this.ptbCreateMedicalRecord=$j('#casePTBContainer #btn_createMedicalRecord_ptb_id');
	this.ptblistMedicalRecord=$j('#casePTBContainer #btn_listMedicalrecord_ptb_id');
	this.ptbPatientListbackbtn=$j('#casePTBContainer #btn_back_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.caseIdCol';
	this.exp = new ExpressionListDTO();
	this.caseService = new CaseServiceImpl();
	this.listUrl = constants.CASELISTURL;
	this.caseTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.caseService,this.exp);
	this.ftbToolBar = '#case-filter-toolbar';
	this.filter = '#filter';
	this.patientId=patientId;
	this.patientName;
	this.viewCaseUI = new ViewCaseUI(this);
}

CaseUIStartup.prototype.setCaseTableNavigator = function(caseTableNavigator) {
	this.caseTableNavigator = caseTableNavigator;
}
CaseUIStartup.prototype.getCaseService = function() {
	return this.caseService;
}
CaseUIStartup.prototype.getCaseTableNavigator = function() {
	return this.caseTableNavigator;
}
CaseUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
CaseUIStartup.prototype.getViewCaseUI = function() {
	return this.viewCaseUI;
} 

 CaseUIStartup.prototype.createCaseModal = function(obj,patientId) {
	var self = this;
	self.patientId=patientId;
	commonMethodInvoker.removeErrors();
	createModal(constants.CREATECASEUI,constants.CASEMODEL);		
	openModalBox(obj,constants.CASEMODEL);
	var newCaseUI = new NewCaseUI(self);
	newCaseUI.init(patientId);
	newCaseUI.bindEvents();
}
 

CaseUIStartup.prototype.init = function(patientId) {
	var self = this;
	self.patientId=patientId;
	var caseTableNavigator = self.getCaseTableNavigator();
	if(self.patientId!=undefined){
		var caseService=self.getCaseService();
		var response=caseService.getPatientDetail(self.patientId);
	}
	 if (self.patientId!=undefined){
		self.patientName=response.firstName;
		var firstName = response.firstName;
		firstName = firstName.toLowerCase().replace(/\b[a-z]/g, function(letter) {
			return letter.toUpperCase();
		});
	} 
	self.setPageTitle(constants.CASETITLE +" "+"Of"+" "+firstName);	
	var expList = new ExpressionListDTO();
		var exp = new ExpressionDTO("patientId",patientId,"eq");
		var exp1 = new ExpressionDTO("status","active","eq");	
		expList.add(exp);
		expList.add(exp1);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.CASE,constants.CASEPAGETOOLBAR); //Create the Page tool Bar for Case/* 
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.CASE,constants.CASEFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.CASETABLELIST);//Create Table for Listing Case
	dataTableProcessor.setCustomTable(self.pgTableName);
	caseTableNavigator.setExp(expList);
	caseTableNavigator.list();
	self.bindEvents();	
}

  CaseUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATECASEUI,constants.CASEMODEL);		
		openModalBox(obj,constants.CASEMODEL);
		var newCaseUI = new NewCaseUI(self);
		newCaseUI.init(self.patientId);
		newCaseUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var caseId=self.getSelectedCaseId(self.pgTableName);
		
		var caseService = self.getCaseService();
		var caseResponse = caseService.viewCase(caseId);
		
		$j('#' + constants.CASE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(caseId!="") {
		self.patientId = caseResponse.patientId;
		self.patientName = caseResponse.patientFirstName;
			var viewCaseUI = self.getViewCaseUI();
			viewCaseUI.init(self.patientId,self.patientName,caseId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var caseId=self.getSelectedCaseId(self.pgTableName);
		if(caseId!="") {
			var caseService = self.getCaseService();
			var caseResponse = caseService.viewCase(caseId);
			var confirmStatus = confirm(constants.CASEDELETECONFIRM + caseResponse.caseName);
			if(confirmStatus==true) {				
				var response = caseService.deleteCase(caseId);
				if(response.success==true) {
					showTip(constants.CASEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var caseTableNavigator = self.getCaseTableNavigator();
				caseTableNavigator.list();
				
			}
		}	
	});
	self.ptbCreateMedicalRecord.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var caseId=self.getSelectedCaseId(self.pgTableName);
		if(caseId!="") {
				var obj=$j(this);
				var medicalRecordUI = new MedicalRecordUIStartup(caseId,self.patientId);			
				medicalRecordUI.init(caseId,self.patientId);
				medicalRecordUI.createMedicalRecordModal(obj,caseId,self.patientId);
			}
			
	});
		self.ptblistMedicalRecord.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var caseId=self.getSelectedCaseId(self.pgTableName);
		if(caseId!="") {
				var obj=$j(this);
				var medicalRecordUI = new MedicalRecordUIStartup(caseId,self.patientId);			
				medicalRecordUI.init(caseId,self.patientId);
			}
			
	});
	self.ptbPatientListbackbtn.die('click').live('click',function() {
		var patientUI = new PatientUIStartup();			
		patientUI.init();
		});
	
} 
 CaseUIStartup.prototype.getSelectedCaseId = function () {
	var caseId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selCase = $j(this.pgTableName + ' tbody tr[selected]');
		if(selCase.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONECASE);
		} else if(selCase.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONECASEONLY);
		else
			caseId=selCase.attr('id');
	}
	return caseId;
} 


 CaseUIStartup.prototype.bindEvents = function() {
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
	   var caseId= $j(this).parent().attr('id');
	   $j('#' + constants.CASE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(caseId!="") {
			$j('#case-filter-wb').hide();
			var viewCaseUI = self.getViewCaseUI();
			viewCaseUI.init(self.patientId,self.patientName,caseId);
		}	
	});
}  