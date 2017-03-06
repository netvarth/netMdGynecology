function DoctorUIStartup() {
	this.pgTableName = "#doctor";
	this.pgTableContainer="#doctorListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#doctorPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#doctorPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#doctorPTBContainer #btn_delete_ptb_id');
	this.ptbCreateDoctor=$j('#doctorPTBContainer #btn_createdoctor_ptb_id');
	this.ptbDoctorList=$j('#doctorPTBContainer #btn_doctorlist_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.doctorIdCol';
	this.exp = new ExpressionListDTO();
	this.doctorService = new DoctorServiceImpl();
	this.listUrl = constants.DOCTORLISTURL;
	this.doctorTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.doctorService,this.exp);
	this.ftbToolBar = '#doctor-filter-toolbar';
	this.filter = '#filter';
	this.viewDoctorUI = new ViewDoctorUI(this);
	
}

DoctorUIStartup.prototype.setDoctorTableNavigator = function(doctorTableNavigator) {
	this.doctorTableNavigator = doctorTableNavigator;
}
DoctorUIStartup.prototype.getDoctorService = function() {
	return this.doctorService;
}
DoctorUIStartup.prototype.getDoctorTableNavigator = function() {
	return this.doctorTableNavigator;
}
DoctorUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
DoctorUIStartup.prototype.getViewDoctorUI = function() {
	return this.viewDoctorUI;
} 

DoctorUIStartup.prototype.init = function() {
	var self = this;
	var doctorTableNavigator = self.getDoctorTableNavigator();
	self.setPageTitle(constants.DOCTORTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.DOCTOR,constants.DOCTORPAGETOOLBAR); //Create the Page tool Bar for Area/* 
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.DOCTOR,constants.DOCTORFILTERKEY);
	//self.hideFilters();
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.DOCTORTABLELIST);//Create Table for Listing Area
	doctorTableNavigator.list();
	self.bindEvents();
	
}

 DoctorUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEDOCTORUI,constants.DOCTORMODEL);		
		openModalBox(obj,constants.DOCTORMODEL);
		var newDoctorUI = new NewDoctorUI(self);
		newDoctorUI.init();
		newDoctorUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var doctorId=self.getSelectedDoctorId(self.pgTableName);
		$j('#' + constants.doctor + '-filter-cont').hide();
		$j(self.filter).hide();
		if(doctorId!="") {
			var viewDoctorUI = self.getViewDoctorUI();
			viewDoctorUI.init(doctorId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var doctorId=self.getSelectedDoctorId(self.pgTableName);
		if(doctorId!="") {
			var doctorService = self.getDoctorService();
			var doctorResponse = doctorService.viewDoctor(doctorId);
			var confirmStatus = confirm(constants.DOCTORDELETECONFIRM + doctorResponse.doctor.name);
			if(confirmStatus==true) {				
				var response = doctorService.deleteDoctor(doctorId);
				if(response.success==true) {
					showTip(constants.DOCTORDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var doctorTableNavigator = self.getDoctorTableNavigator();
				doctorTableNavigator.list();
				
			}
		}	
	});
	self.ptbCreateDoctor.die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		var doctorId=self.getSelectedDoctorId(self.pgTableName);
		if(doctorId!="") {
			var obj=$j(this);
			var doctorUI = new DoctorUIStartup(doctorId);			
			doctorUI.init(doctorId);
			doctorUI.createDoctorModal(obj,doctorId);
		}		
	});
	self.ptbDoctorList.die('click').live('click',function(){
		commonMethodInvoker.removeErrors();;
		var doctorId=self.getSelectedDoctorId(self.pgTableName);
		if(doctorId!="") {
			var doctorUI = new DoctorUIStartup(doctorId);
			doctorUI.init(doctorId);
		}	
	});
	}

DoctorUIStartup.prototype.getSelectedDoctorId = function () {
	var doctorId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selDoctor = $j(this.pgTableName + ' tbody tr[selected]');
		if(selDoctor.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEDOCTOR);
		} else if(selDoctor.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEDOCTORONLY);
		else
			doctorId=selDoctor.attr('id');
	}
	return doctorId;
}
DoctorUIStartup.prototype.bindEvents = function() {
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
	   var doctorId= $j(this).parent().attr('id');
	   $j('#' + constants.DOCTOR + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(doctorId!="") {
			$j('#doctor-filter-wb').hide();
			var viewDoctorUI = self.getViewDoctorUI();
			viewDoctorUI.init(doctorId);
		}	
	});
} 