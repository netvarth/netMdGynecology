function ViewTaxUI(taxUIStartup) {
	this.viewTaxPage = "#viewTax";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewTax #id label";
	this.value="#viewTax #value";
	this.name = "#viewTax #name";
	this.description = "#viewTax #description";
	this.viewType="#viewTax #viewType";
	this.viewCalculationType=" #viewTax #lblViewCalculationType label";
	this.editType="#viewTax #editType";
	this.updateButton = '#viewTax #btnTaxSave';
	this.editButton = '#viewTax #btnTaxEdit';
	this.cancelButton = '#viewTax #btnTaxCancel'; 
	this.ptbBack = "#taxGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#taxGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#taxGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.taxUIStartup=taxUIStartup;
	this.viewTaxPTB = new ViewTaxPTB(this);
}
ViewTaxUI.prototype.getTaxUIStartup = function() {
	return this.taxUIStartup;
}

ViewTaxUI.prototype.getViewTaxPTB = function() {
	return this.viewTaxPTB;
}

ViewTaxUI.prototype.getTaxTableNavigator = function() {
	var taxUIStartup = this.getTaxUIStartup();
	return taxUIStartup.getTaxTableNavigator();
}

ViewTaxUI.prototype.getTaxService = function() {
	var taxUIStartup = this.getTaxUIStartup();
	return taxUIStartup.getTaxService();
}
//Set the page title of the area ui page
ViewTaxUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewTaxUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewTaxUI.prototype.init = function(taxId) {
	var self = this;
	var viewTaxPTB = self.getViewTaxPTB();
	viewTaxPTB.init(self);
	pageHandler.create(constants.VIEWTAXURL);
	self.bindEvents();
	self.ViewTax(taxId);
}

ViewTaxUI.prototype.ViewTax = function(taxId) {
	self=this;
	var header = constants.TAXTITLE;
	var taxService = self.getTaxService();
	var taxResponse = taxService.viewTax(taxId);
	if(taxResponse.success==true) {
		var taxFields=taxResponse.tax;
		var tax = new TaxDTO();
		tax.setName(taxFields.name);
		tax.setId(taxFields.id);
		tax.setCalculationType(taxFields.calculationType);
		tax.setTaxVal(taxFields.taxVal);
		tax.setDescription(taxFields.description);
		self.setTax(tax);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(taxResponse.error));
	}
	self.setPageTitle(header);
}

ViewTaxUI.prototype.setTax = function(tax) {
var self=this;
	$j(self.id).text(tax.id);
	$j(self.viewCalculationType).text(tax.calculationType);
	$j(self.value).val(tax.taxVal);
	$j(self.name).val(tax.name);
	$j(self.description).val(tax.description);
}

ViewTaxUI.prototype.getTax = function() {
var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var calculationType=$j("#viewTax input[type='radio']:checked").val();
	var description = $j(self.description).val();
	var value=parseFloat($j(self.value).val());
	var tax = new TaxDTO();
	tax.setId(id);
	tax.setName(name);
	tax.setDescription(description); 
	tax.setCalculationType(calculationType);
	tax.setTaxVal(value);
	return tax;
}




ViewTaxUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewTaxPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.viewType).hide();
	$j(self.editType).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewTaxPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewTaxPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewTaxPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewTaxUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewTaxPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewTaxPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewType).show();
	$j(self.editType).hide();
	$j(self.viewTaxPage + " input[type=text],textarea").attr('disabled',true);
}

ViewTaxUI.prototype.getPrevId = function(curId,taxResult) {
	var prevId;
	$j(taxResult.taxlist).each(function (index, rowTax) {
		if(curId==rowTax.id)	{
			var arrayLength=(taxResult.taxlist).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=taxResult.taxlist[index-1].id;
		}
	});
	return prevId;	
}
	
ViewTaxUI.prototype.getNextId = function(curId,taxResult) {
	var nextId;
	$j(taxResult.taxlist).each(function (index, rowTax) {
		if(curId==rowTax.id)	{
			var arrayLength=(taxResult.taxlist).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=taxResult.taxlist[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewTaxUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var tax = self.getTax();
	self.ViewTax(tax.id);
	self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var tax = self.getTax();
		var taxValidator = new TaxValidator();
		var error  = taxValidator.validate(tax);
		if(error.errorStatus==false) {
			var taxService =self.getTaxService();
			var taxResponse = taxService.updateTax(tax);
			if(taxResponse.success==true) {
				showTip(constants.TAXUPDATESUCCESS);//For showing the global Tip
				self.ViewTax(taxResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(taxResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	

	
}