function ViewSupportUI(supportUIStartup) {
	this.viewSupportPage = "#viewSupport";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewSupport #id label";
	this.price="#viewSupport #price";
	this.name = "#viewSupport #name";
	this.taxlabel="#viewSupport #lbltax label";
	this.description = "#viewSupport #description";
	this.viewTaxType="#viewSupport #viewTax";
	this.editTaxType="#viewSupport #editTax";
	this.tax="#viewSupport #tax";
	this.updateButton = '#viewSupport #btnSupportSave';
	this.editButton = '#viewSupport #btnSupportEdit';
	this.cancelButton = '#viewSupport #btnSupportCancel'; 
	this.ptbBack = "#supportGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#supportGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#supportGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.supportUIStartup=supportUIStartup;
	this.viewSupportPTB = new ViewSupportPTB(this);
	this.taxId;
}
ViewSupportUI.prototype.getSupportUIStartup = function() {
	return this.supportUIStartup;
}

ViewSupportUI.prototype.getViewSupportPTB = function() {
	return this.viewSupportPTB;
}

ViewSupportUI.prototype.getSupportTableNavigator = function() {
	var supportUIStartup = this.getSupportUIStartup();
	return supportUIStartup.getSupportTableNavigator();
}

ViewSupportUI.prototype.getSupportService = function() {
	var supportUIStartup = this.getSupportUIStartup();
	return supportUIStartup.getSupportService();
}

ViewSupportUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewSupportUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewSupportUI.prototype.init = function(supportId) {
	var self = this;
	var viewSupportPTB = self.getViewSupportPTB();
	viewSupportPTB.init(self);
	pageHandler.create(constants.VIEWSUPPORTURL);
	self.bindEvents();
	self.ViewSupport(supportId);
}

ViewSupportUI.prototype.ViewSupport = function(supportId) {
	self=this;
	var header = constants.VIEWSUPPORTTITLE;
	var supportService = self.getSupportService();
	var supportResponse = supportService.viewSupport(supportId);
	if(supportResponse.success==true) {
		var support = new SupportDTO();
		support.setName(supportResponse.name);
		support.setId(supportResponse.id);
		support.setPrice(supportResponse.price.toFixed(2));
		support.setDescription(supportResponse.description);
		support.setTaxName(supportResponse.taxName);
		self.taxId=supportResponse.taxId;
		self.setSupport(support);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(supportResponse.error));
	}
	self.setPageTitle(header);
}

ViewSupportUI.prototype.setSupport = function(support) {
var self=this;
	$j(self.id).text(support.id);
	$j(self.price).val(support.price);
	$j(self.name).val(support.name);
	$j(self.description).val(support.description);
	$j(self.taxlabel).text(support.taxName);
}

ViewSupportUI.prototype.getSupport = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
    var price=parseFloat($j(self.price).val());
	var description = $j(self.description).val();
	var taxId = $j(self.tax+" option:selected").val();
	var support = new SupportDTO();
	support.setId(id);
	support.setName(name);
	support.setDescription(description); 
	support.setPrice(price);
	support.setTaxId(taxId);
	return support;
}




ViewSupportUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewSupportPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.viewTaxType).hide();
	$j(self.editTaxType).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewSupportPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewSupportPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewSupportPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewSupportUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewSupportPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewSupportPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewTaxType).show();
	$j(self.editTaxType).hide();
	$j(self.viewSupportPage + " input[type=text],textarea").attr('disabled',true);
}

ViewSupportUI.prototype.getPrevId = function(curId,supportResult) {
	var prevId;
	$j(supportResult.supportList).each(function (index, rowSupport) {
		if(curId==rowSupport.id)	{
			var arrayLength=(supportResult.supportList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=supportResult.supportList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewSupportUI.prototype.getNextId = function(curId,supportResult) {
	var nextId;
	$j(supportResult.supportList).each(function (index, rowSupport) {
		if(curId==rowSupport.id)	{
			var arrayLength=(supportResult.supportList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=supportResult.supportList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewSupportUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var supportService =self.getSupportService();
		var taxList=supportService.getTaxList();
		methodInvoker.fillTaxTypeToControl(self.tax,taxList);
		$j(self.tax+" option[value="+self.taxId+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var support = self.getSupport();
		self.ViewSupport(support.id);
		self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var support = self.getSupport();
		//alert(JSON.stringify(support));
		var supportValidator = new SupportValidator();
		var error  = supportValidator.validate(support);
		if(error.errorStatus==false) {
			var supportService =self.getSupportService();
			var supportResponse = supportService.updateSupport(support);
			if(supportResponse.success==true) {
				showTip(constants.SUPPORTUPDATESUCCESS);//For showing the global Tip
				self.ViewSupport(supportResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(supportResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	

	
}