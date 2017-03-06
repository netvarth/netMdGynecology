function NewSupportUI(supportUIStartup) {
	this.createButton = $j("#newSupport #btnSupportSave");
	this.cancelButton = $j('#newSupport #btnSupportCancel');
	this.newSupportPage = "#newSupport";
	this.supportModal = '#supportModal';
	this.errorHeader = $j('#supportModal #errorDivHeader');
	this.errorData = $j('#supportModal #errorDivNewSupportData');
	this.name="#newSupport #name";
	this.price="#newSupport #price";
	this.description="#newSupport #description";
	this.inputFields = ":input";
	this.tax="#newSupport #tax";
	this.supportUIStartup = supportUIStartup;

}
NewSupportUI.prototype.getSupportService = function() {
	var supportUIStartup = this.getSupportUIStartup();
	return supportUIStartup.getSupportService();
}

NewSupportUI.prototype.getSupportUIStartup = function() {
	return this.supportUIStartup;
}
NewSupportUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newSupportPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewSupportUI.prototype.init = function() {
	var self=this;
	var supportService = self.getSupportService();
	var taxList = supportService.getTaxList();
	methodInvoker.fillTaxTypeToControl(self.tax,taxList);

}

NewSupportUI.prototype.getSupportTableNavigator = function() {
	var supportUIStartup = this.getSupportUIStartup();
	return supportUIStartup.getSupportTableNavigator();
}


NewSupportUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewSupportUI.prototype.getSupport = function() {
	var self=this;
	var name = $j(self.name).val();	
	var price=parseFloat($j(self.price).val());
	var taxname=$j(self.tax+" option:selected").text();
	var taxId=parseInt($j(self.tax+" option:selected").val());
	var description=$j(self.description).val();
	var support = new SupportDTO();
	support.setName(name);
	support.setPrice(price);
	support.setTaxId(taxId);
	support.setTaxName(taxname)
	support.setDescription(description);
	return support;
}

NewSupportUI.prototype.clearFields = function() {
	self = this;
	$j(self.newSupportPage + " input[type=text],textarea").val("");
	$j(self.tax+" option[value='0']").attr('selected','selected');
	$j(self.name ).focus();
}

NewSupportUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newSupportPage + " " + self.inputFields);
	
	$j(self.newSupportPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.supportModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.supportModal + self.newSupportPage + " input[type=text]").val("");	
		$j(self.supportModal).trigger('reveal:close');
		$j(self.supportModal).remove();
		self=self.getSupportUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var support = self.getSupport();
		var supportValidator = new SupportValidator();
		var error  = supportValidator.validate(support);
		if(error.errorStatus==false) {
			var supportService = self.getSupportService();	
			var supportResponse = supportService.createSupport(support);
			if(supportResponse.success==true) {
				showTip(constants.SUPPORTCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var supportTableNavigator = self.getSupportTableNavigator();
				supportTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(supportResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.supportModal + self.newSupportPage + " input[type=text]").val("");	
		$j(self.supportModal).trigger('reveal:close');
		$j(self.supportModal).remove();
		self=self.getSupportUIStartup();
	});	



	

	
}