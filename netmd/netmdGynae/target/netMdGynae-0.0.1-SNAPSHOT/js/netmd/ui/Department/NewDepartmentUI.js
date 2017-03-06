function NewDepartmentUI(departmentUIStartup) {
	this.createButton = $j("#newDepartment #btnDepartmentSave");
	this.cancelButton = $j('#newDepartment #btnDepartmentCancel');
	this.newDepartmentPage = "#newDepartment";
	this.departmentModal = '#departmentModal';
	this.errorHeader = $j('#departmentModal #errorDivHeader');
	this.errorData = $j('#departmentModal #errorDivnewDepartmentData');
	this.name="#newDepartment #name";
	this.description="#newDepartment #description";
	this.inputFields = ":input";
	this.departmentUIStartup = departmentUIStartup;
}

NewDepartmentUI.prototype.getDepartmentUIStartup = function() {
	return this.departmentUIStartup;
}

NewDepartmentUI.prototype.getDepartmentTableNavigator = function() {
	var departmentUIStartup = this.getDepartmentUIStartup();
	return departmentUIStartup.getDepartmentTableNavigator();
}

NewDepartmentUI.prototype.getDepartmentService = function() {
	var departmentUIStartup = this.getDepartmentUIStartup();
	return departmentUIStartup.getDepartmentService();
}

NewDepartmentUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewDepartmentUI.prototype.getDepartment = function() {
	var self=this;
	var name = $j(self.name).val();	
	var description=$j(self.description).val();
	var department = new DepartmentDTO();
	department.setDepartmentName(name);
	department.setDescription(description);
	return department;
}



NewDepartmentUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newDepartmentPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewDepartmentUI.prototype.clearFields = function() {
	$j(this.newDepartmentPage + " input[type=text],textarea").val("");
}


NewDepartmentUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newDepartmentPage + " " + self.inputFields);
	
	$j(self.newDepartmentPage+ " input[type=text] ").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	  $j(self.departmentModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.departmentModal + self.newDepartmentPage + " input[type=text]").val("");	
		$j(self.departmentModal).trigger('reveal:close');
		$j(self.departmentModal).remove();
		self=self.getDepartmentUIStartup();
	}); 
	
    self.createButton.die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var department = self.getDepartment();
	var departmentValidator = new DepartmentValidator();
	var error  = departmentValidator.validate(department);
	if(error.errorStatus==false) {
		var departmentService = self.getDepartmentService();
		var departmentResponse = departmentService.createDepartment(department);
		if(departmentResponse.success==true) {
			showTip(constants.DEPARTMENTCREATESUCCESS);//For showing the global Tip
			self.clearFields();
			var departmentTableNavigator = self.getDepartmentTableNavigator();
			departmentTableNavigator.list();
		} else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(departmentResponse.error));
		}
	} else {
		self.createError(error);
	}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.departmentModel + self.newDepartmentPage + " input[type=text]").val("");	
		$j(self.departmentModel).trigger('reveal:close');
		$j(self.departmentModel).remove();
		self=self.getDepartmentUIStartup();
	});	



	

	
}