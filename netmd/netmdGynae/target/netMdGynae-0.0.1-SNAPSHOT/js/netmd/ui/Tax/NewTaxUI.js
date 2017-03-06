function NewTaxUI(taxUIStartup) {
	this.createButton = $j("#newTax #btnTaxSave");
	this.cancelButton = $j('#newTax #btnTaxCancel');
	this.newTaxPage = "#newTax";
	this.taxModal = '#taxModal';
	this.errorHeader = $j('#taxModal #errorDivHeader');
	this.errorData = $j('#taxModal #errorDivNewTaxData');
	this.name="#newTax #name";
	this.description="#newTax #description";
	this.value="#newTax #value";
	this.calculationType="#newTax #calculationType";
	this.inputFields = ":input";
	this.taxUIStartup = taxUIStartup;

}

NewTaxUI.prototype.getTaxUIStartup = function() {
	return this.taxUIStartup;
}

NewTaxUI.prototype.getTaxTableNavigator = function() {
	var taxUIStartup = this.getTaxUIStartup();
	return taxUIStartup.getTaxTableNavigator();
}
NewTaxUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newTaxPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewTaxUI.prototype.getTaxService = function() {
	var taxUIStartup = this.getTaxUIStartup();
	return taxUIStartup.getTaxService();
}

NewTaxUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewTaxUI.prototype.getTax = function() {
	var self=this;
	var name = $j(self.name).val();	
	var value=parseFloat($j(self.value).val());
	var calculationType=$j("#newTax input[type='radio']:checked").val()
	var description=$j(self.description).val();
	var tax = new TaxDTO();
	tax.setName(name);
	tax.setTaxVal(value);
	tax.setCalculationType(calculationType);
	tax.setDescription(description);
	return tax;
}

NewTaxUI.prototype.clearFields = function() {
	self = this;
	$j(self.newTaxPage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}

NewTaxUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newTaxPage + " " + self.inputFields);
	
	$j(self.newTaxPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.taxModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.taxModal + self.newTaxPage + " input[type=text]").val("");	
		$j(self.taxModal).trigger('reveal:close');
		$j(self.taxModal).remove();
		self=self.getTaxUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var tax = self.getTax();
		var taxValidator = new TaxValidator();
		var error  = taxValidator.validate(tax);
		if(error.errorStatus==false) {
			var taxService = self.getTaxService();	
			var taxResponse = taxService.createTax(tax);
			if(taxResponse.success==true) {
				showTip(constants.TAXCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var taxTableNavigator = self.getTaxTableNavigator();
				taxTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(taxResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.taxModal + self.newTaxPage + " input[type=text]").val("");	
		$j(self.taxModal).trigger('reveal:close');
		$j(self.taxModal).remove();
		self=self.getTaxUIStartup();
	});	



	

	
}