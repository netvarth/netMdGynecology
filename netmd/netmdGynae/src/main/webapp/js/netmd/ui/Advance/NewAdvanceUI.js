function NewAdvanceUI(advanceUIStartup) {
	this.createButton = $j("#newAdvance #btnAdvanceSave");
	this.cancelButton = $j('#newAdvance #btnAdvanceCancel');
	this.newAdvancePage = "#newAdvance";
	this.advanceModal = '#advanceModal';
	this.errorHeader = $j('#advanceModal #errorDivHeader');
	this.errorData = $j('#advanceModal #errorDivNewAdvanceData');
	this.name="#newAdvance #name";
	this.amount = "#newAdvance #amount";
	this.email = "#newAdvance #email";
	this.inputFields = ":input";
	this.patientDetails="";
	this.patientId="";
	this.advanceUIStartup = advanceUIStartup;

}

NewAdvanceUI.prototype.getAdvanceUIStartup = function() {
	return this.advanceUIStartup;
}

NewAdvanceUI.prototype.getAdvanceTableNavigator = function() {
	var advanceUIStartup = this.getAdvanceUIStartup();
	return advanceUIStartup.getAdvanceTableNavigator();
}

NewAdvanceUI.prototype.getAdvanceService = function() {
	var advanceUIStartup = this.getAdvanceUIStartup();
	return advanceUIStartup.getAdvanceService();
}

NewAdvanceUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewAdvanceUI.prototype.getAdvance = function() {
	var self=this;
	var patientId = self.patientId;	
	var amount=$j(self.amount).val();
	var advance = new AdvanceDTO();
	advance.setPatientId(parseInt(patientId));
	advance.setAmount(parseFloat(amount));
	return advance;
}
NewAdvanceUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newAdvancePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewAdvanceUI.prototype.clearFields = function() {
	self = this;
	$j(self.newAdvancePage + " input[type=text]").val("");
	$j(self.name ).focus();
}
NewAdvanceUI.prototype.init = function() {
	var self=this;
	var advanceService = self.getAdvanceService();	
	var advanceResponse = advanceService.getPatientDetails();
    self.patientDetails=advanceResponse;
	methodInvoker.fillPatientDetailsToControl(self.name,advanceResponse);
}

NewAdvanceUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newAdvancePage + " " + self.inputFields);
	
	$j(self.newAdvancePage+ " input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.name).autocomplete({
   	 select: function(e, ui){
       $j(self.name).val(ui.item.value);
        commonMethodInvoker.removeErrors();
        var patientName=$j(self.name).val();
		var newPatientName=patientName.split("[")[0];
		var email=patientName.split("[")[1];
		var originalemail=email.split("]")[0];
		var patientDetail=methodInvoker.getPatientDetails(originalemail,self.patientDetails);
		$j(self.email).val(patientDetail.email); 
		self.patientId=patientDetail.id;
	    this.value =newPatientName;
   		 return false;
    }
});

	 $j(self.advanceModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.advanceModal + self.newAdvancePage + " input[type=text]").val("");	
		$j(self.advanceModal).trigger('reveal:close');
		$j(self.advanceModal).remove();
		self=self.getAdvanceUIStartup();
	});

    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var advance = self.getAdvance();
		var advanceValidator = new AdvanceValidator();
		var error  = advanceValidator.validate(advance);
		if(error.errorStatus==false) {
			var advanceService = self.getAdvanceService();
			var advanceResponse = advanceService.createAdvance(advance);		
			if(advanceResponse.success==true) {
				showTip(constants.ADVANCECREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var advanceTableNavigator = self.getAdvanceTableNavigator();
				advanceTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(advanceResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.advanceModal + self.newAdvancePage + " input[type=text]").val("");	
		$j(self.advanceModal).trigger('reveal:close');
		$j(self.advanceModal).remove();
		self=self.getAdvanceUIStartup();
	});	



	

	
}