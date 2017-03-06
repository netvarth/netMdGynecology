function ViewDepartmentUI(DepartmentStartup) {
	this.viewDepartmentPage = "#viewDepartment";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewDepartment #id label";
	this.name = "#viewDepartment #name";
	this.description="#viewDepartment #description";
	this.updateButton = "#viewDepartment #btnDepartmentSave";
	this.editButton = "#viewDepartment #btnDepartmentEdit";
	this.cancelButton = "#viewDepartment #btnDepartmentCancel"; 
	this.ptbBack = "#DepartmentGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#DepartmentGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#DepartmentGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.DepartmentStartup=DepartmentStartup;
	this.viewDepartmentPTB = new ViewDepartmentPTB(this);
}
ViewDepartmentUI.prototype.getDepartmentUIStartup = function() {
	return this.DepartmentStartup;
}

ViewDepartmentUI.prototype.getViewDepartmentPTB = function() {
	return this.viewDepartmentPTB;
}

ViewDepartmentUI.prototype.getDepartmentTableNavigator = function() {
	var DepartmentUIStartup = this.getDepartmentUIStartup();
	return DepartmentUIStartup.getDepartmentTableNavigator();
}

ViewDepartmentUI.prototype.getDepartmentService = function() {
	var DepartmentUIStartup = this.getDepartmentUIStartup();
	return DepartmentUIStartup.getDepartmentService();
}
//Set the page title of the area ui page
ViewDepartmentUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewDepartmentUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}
ViewDepartmentUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewDepartmentPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

ViewDepartmentUI.prototype.init = function(departmentId) {
	var self = this;
	var viewDepartmentPTB = self.getViewDepartmentPTB();
	viewDepartmentPTB.init(self);
	pageHandler.create(constants.VIEWDEPARTMENTURL);
	
	self.bindEvents();
	self.viewDepartment(departmentId);
}

ViewDepartmentUI.prototype.viewDepartment = function(departmentId) {
	self=this;
	var header = constants.VIEWDEPARTMENTTITLE;
	var departmentService = self.getDepartmentService();
	var departmentResponse = departmentService.viewDepartment(departmentId);
	if(departmentResponse.success==true) {
		var department = new DepartmentDTO();
		department.setDepartmentName(departmentResponse.departmentName);
		department.setDescription(departmentResponse.description);
		department.setId(departmentResponse.id);
		self.setDepartment(departmentResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(departmentResponse.error));
	}
	self.setPageTitle(header);
}

ViewDepartmentUI.prototype.setDepartment = function(departmentResponse) {
	self=this;
	$j(self.id).text(departmentResponse.id);
	$j(self.name).val(departmentResponse.departmentName);
	$j(self.description).val(departmentResponse.description);
}

ViewDepartmentUI.prototype.getDepartment = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var description = $j(self.description).val();
	var Department = new DepartmentDTO();
	Department.setId(id);
	Department.setDepartmentName(name);
	Department.setDescription(description);
	return Department;
}

ViewDepartmentUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewDepartmentPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}




ViewDepartmentUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewDepartmentPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewDepartmentPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewDepartmentPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewDepartmentPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewDepartmentUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewDepartmentPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewDepartmentPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewDepartmentPage + " input[type=text],textarea").attr('disabled',true);
}

ViewDepartmentUI.prototype.getPrevId = function(curId,DepartmentResult) {
	var prevId;
	$j(DepartmentResult.department).each(function (index, rowDepartment) {
		if(curId==rowDepartment.id)	{
			var arrayLength=(DepartmentResult.department).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=DepartmentResult.department[index-1].id;
		}
	});
	return prevId;	
}
	
ViewDepartmentUI.prototype.getNextId = function(curId,DepartmentResult) {
	var nextId;
	$j(DepartmentResult.department).each(function (index, rowDepartment) {
		if(curId==rowDepartment.id)	{
			var arrayLength=(DepartmentResult.department).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=DepartmentResult.department[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewDepartmentUI.prototype.bindEvents = function() {
	self = this;

	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var Department = self.getDepartment();
		self.viewDepartment(Department.id);
		self.readable();
	});
	
	
	//Update Department Button Click Event
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var department = self.getDepartment();
		var departmentValidator = new DepartmentValidator();
		var error  = departmentValidator.validate(department);
		if(error.errorStatus==false) {
			var departmentService =self.getDepartmentService();
			var departmentResponse = departmentService.updateDepartment(department);
			if(departmentResponse.success==true) {
				showTip(constants.DEPARTMENTUPDATESUCCESS);//For showing the global Tip
				var department = self.getDepartment();
				self.viewDepartment(department.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(departmentResponse.error));
		}
		} else {
		self.createError(error);
	}		
	});
	
	
	//Edit Department Button Click Event
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
		
	});
	
	
}