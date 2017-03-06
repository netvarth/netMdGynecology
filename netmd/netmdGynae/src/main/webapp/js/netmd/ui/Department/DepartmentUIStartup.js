function DepartmentUIStartup() {
	this.pgTableName = "#department";
	this.pgTableContainer="#departmentListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#departmentPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#departmentPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#departmentPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#departmentPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.departmentIdCol';
	this.exp = new ExpressionListDTO();
	this.departmentService = new DepartmentServiceImpl();
	this.listUrl = constants.DEPARTMENTLISTURL;
	this.departmentTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.departmentService,this.exp);
	this.ftbToolBar = '#department-filter-toolbar';
	this.filter = '#filter';
	this.viewDepartmentUI = new ViewDepartmentUI(this);
}

DepartmentUIStartup.prototype.setDepartmentTableNavigator = function(departmentTableNavigator) {
	this.departmentTableNavigator = departmentTableNavigator;
}

DepartmentUIStartup.prototype.getDepartmentTableNavigator = function() {
	return this.departmentTableNavigator;
}
DepartmentUIStartup.prototype.getDepartmentService = function() {
	return this.departmentService;
}
DepartmentUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
  DepartmentUIStartup.prototype.getViewDepartmentUI = function() {
	return this.viewDepartmentUI;
}   

DepartmentUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var departmentTableNavigator = self.getDepartmentTableNavigator();
	self.setPageTitle(constants.DEPARTMENTTITLE);
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.DEPARTMENT,constants.DEPARTMENTPAGETOOLBAR); //Create the Page tool Bar for Department
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.DEPARTMENTTABLELIST);//Create Table for Listing Department
	dataTableProcessor.setCustomTable(self.pgTableName);
	departmentTableNavigator.setExp(expList);
	departmentTableNavigator.list();
	self.bindEvents();
	
}

DepartmentUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEDEPARTMENTUI,constants.DEPARTMENTMODEL);		
		openModalBox(obj,constants.DEPARTMENTMODEL);
		var newDepartmentUI = new NewDepartmentUI(self);
		newDepartmentUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {		
		removeErrors();
		var departmentId=self.getSelectedDepartmentId(self.pgTableName);
		$j('#' + constants.DEPARTMENTTITLE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(departmentId!="") {
			var viewDepartmentUI = self.getViewDepartmentUI();
			viewDepartmentUI.init(departmentId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var departmentId=self.getSelectedDepartmentId(self.pgTableName);
		if(departmentId!="") {
			var departmentService = self.getDepartmentService();
			var departmentResponse = departmentService.viewDepartment(departmentId);
			var confirmStatus = confirm(constants.DEPARTMENTDELETECONFIRM + departmentResponse.departmentName);
			if(confirmStatus==true) {				
				var departmentResponse = departmentService.deleteDepartment(departmentId);
				if(departmentResponse.success==true) {
					showTip(constants.DEPARTMENTDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(departmentResponse.error));
				}
				var departmentTableNavigator = self.getDepartmentTableNavigator();
				departmentTableNavigator.list();
				methodInvoker.setDefaultData();
				defaultData = methodInvoker.getDefaultData();
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	
}
DepartmentUIStartup.prototype.getSelectedDepartmentId = function () {
	var departmentId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selDepartment = $j(this.pgTableName + ' tbody tr[selected]');
		if(selDepartment.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTDEPARTMENT);
		} else if(selDepartment.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTDEPARTMENTONLY);
		else
			departmentId=selDepartment.attr('id');
	}
	return departmentId;
}
DepartmentUIStartup.prototype.bindEvents = function() {
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
	
	$j(this.pgTableRowClass).die('click').live('click',function(){
	   var departmentId= $j(this).parent().attr('id');
	   $j('#' + constants.DEPARTMENTTITLE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(departmentId!="") {
			$j('#department-filter-wb').hide();
			var viewDepartmentUI = self.getViewDepartmentUI();
			viewDepartmentUI.init(departmentId);
		}	
	});
} 