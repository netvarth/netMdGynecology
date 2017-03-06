  function PatientUIStartup() {

	this.pgTableName = "#patient";
	this.pgTableContainer="#patientListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#patientPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#patientPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#patientPTBContainer #btn_delete_ptb_id');
	this.ptbCreateCase=$j('#patientPTBContainer #btn_createcase_ptb_id');
	this.ptbCaseList=$j('#patientPTBContainer #btn_caselist_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.patientIdCol';
	this.exp = new ExpressionListDTO();
	this.patientService = new PatientServiceImpl();
	this.listUrl = constants.PATIENTLISTURL;
	this.patientInfo;
	this.patientTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.patientService,this.exp);
	this.ftbToolBar = '#patient-filter-toolbar';
	this.filter = '#filter';
	this.filterActionButton = '#btnGo';
	this.viewPatientUI = new ViewPatientUI(this);
	this.billService = new BillServiceImpl();
}

PatientUIStartup.prototype.setPatientTableNavigator = function(patientTableNavigator) {
	this.patientTableNavigator = patientTableNavigator;
}
PatientUIStartup.prototype.getPatientService = function() {
	return this.patientService;
}
PatientUIStartup.prototype.getPatientTableNavigator = function() {
	return this.patientTableNavigator;
}
PatientUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
PatientUIStartup.prototype.getViewPatientUI = function() {
	return this.viewPatientUI;
} 
PatientUIStartup.prototype.getBillService = function() {
	return this.billService;
}

PatientUIStartup.prototype.init = function() {
	var self = this;
	var patientTableNavigator = self.getPatientTableNavigator();
	self.setPageTitle(constants.PATIENTTITLE);
	var expList = new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.PATIENT,constants.PATIENTPAGETOOLBAR); //Create the Page tool Bar for Area/* 
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.PATIENT,constants.PATIENTFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.PATIENTTABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	patientTableNavigator.setExp(expList);
	patientTableNavigator.list();
	self.bindEvents();
	var billService = self.getBillService();
	self.patientInfo= billService.getPatientDetails();
	
}

 PatientUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEPATIENTUI,constants.PATIENTMODEL);		
		openModalBox(obj,constants.PATIENTMODEL);
		var newPatientUI = new NewPatientUI(self);
		newPatientUI.init();
		newPatientUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var patientId=self.getSelectedPatientId(self.pgTableName);
		$j('#' + constants.patient + '-filter-cont').hide();
		$j(self.filter).hide();
		if(patientId!="") {
			var viewPatientUI = self.getViewPatientUI();
			viewPatientUI.init(patientId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var patientId=self.getSelectedPatientId(self.pgTableName);
		if(patientId!="") {
			var patientService = self.getPatientService();
			patientResponse = patientService.viewPatient(patientId);
			
			var firstName = patientResponse.firstName;
				firstName = firstName.toLowerCase().replace(/\b[a-z]/g, function(letter) {
				return letter.toUpperCase();
			});
			
			var confirmStatus = confirm(constants.PATIENTDELETECONFIRM + firstName);
			if(confirmStatus==true) {				
				var response = patientService.deletePatient(patientId);
				if(response.success==true) {
					showTip(constants.PATIENTDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var patientTableNavigator = self.getPatientTableNavigator();
				patientTableNavigator.list();
				
			}
		}	
	});
	self.ptbCreateCase.die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		var patientId=self.getSelectedPatientId(self.pgTableName);
		if(patientId!="") {
			var obj=$j(this);
			var caseUI = new CaseUIStartup(patientId);			
			caseUI.init(patientId);
			caseUI.createCaseModal(obj,patientId);
		}		
	});
	self.ptbCaseList.die('click').live('click',function(){
		commonMethodInvoker.removeErrors();;
		var patientId=self.getSelectedPatientId(self.pgTableName);
		if(patientId!="") {
			var caseUI = new CaseUIStartup(patientId);
			caseUI.init(patientId);
		}	
	});
	
	/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		self.setReportFilterValues(curObjName,self.patientInfo);
	});
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#lst'+curObjName).hide();
		$j('#txt'+curObjName).hide();
		$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	
	$j(self.ftbToolBar + ' ' + self.filterActionButton).die('click').click(function(){
		removeErrors();
		var expList=new ExpressionListDTO();
		$j(self.ftbToolBar + " a[selected]").each(function(){
			var selName=$j(this).attr('name');
			var selTextValue=$j("#txt"+ selName).val();
			var selOperator = $j("#lst"+ selName).val();
			ExpressionListDTO
			var expr = new ExpressionDTO(selName,selTextValue,selOperator);
			expList.add(expr);
		});
		var patientTableNavigator = self.getPatientTableNavigator();
		patientTableNavigator.setExp(expList);
		patientTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
	
	}
PatientUIStartup.prototype.setReportFilterValues=function(billValue,patientInfo,billResInfo) {
		var self=this;
		if(billValue=='firstName'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.firstName+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='mobile'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.mobile+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='phone'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.phone+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='email'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.email+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='status'){
			autoCompleteArray=[];
			autoCompleteArray.push("active");
			autoCompleteArray.push("inactive");
			autoCompleteArray.push("suspended");
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='address'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.address+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='age'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.age+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='bloodGroup'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.bloodGroup+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 

}

PatientUIStartup.prototype.getSelectedPatientId = function () {
	var patientId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selPatient = $j(this.pgTableName + ' tbody tr[selected]');
		if(selPatient.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEPATIENT);
		} else if(selPatient.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEPATIENTONLY);
		else
			patientId=selPatient.attr('id');
	}
	return patientId;
}
PatientUIStartup.prototype.bindEvents = function() {
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
	   var patientId= $j(this).parent().attr('id');
	   $j('#' + constants.PATIENT + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(patientId!="") {
			$j('#patient-filter-wb').hide();
			var viewPatientUI = self.getViewPatientUI();
			viewPatientUI.init(patientId);
		}	
	});
} 