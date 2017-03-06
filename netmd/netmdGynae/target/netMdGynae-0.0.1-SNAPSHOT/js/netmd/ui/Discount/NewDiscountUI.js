function NewDiscountUI(discountUIStartup) {
	this.createButton = $j("#newDiscount #btnDiscountSave");
	this.cancelButton = $j('#newDiscount #btnDiscountCancel');
	this.newDiscountPage = "#newDiscount";
	this.discountModal = '#discountModal';
	this.errorHeader = $j('#discountModal #errorDivHeader');
	this.errorData = $j('#discountModal #errorDivNewDiscountData');
	this.name="#newDiscount #name";
	this.description="#newDiscount #description";
	this.discType="#newDiscount #discType";
	this.discValue="#newDiscount #discValue";
	this.calculationType="#newDiscount #calculationType";
	this.inputFields = ":input";
	this.check="#newDiscount #check";
	this.discountUIStartup = discountUIStartup;
	this.discountTypeId="#newDiscount #discountTypeId";
	this.discounttype="#newDiscount #discounttype ";
	var self=this;
	$j(self.discType).attr("disabled", "disabled");

}



NewDiscountUI.prototype.getDiscountUIStartup = function() {
	return this.discountUIStartup;
}

NewDiscountUI.prototype.getDiscountTableNavigator = function() {
	var discountUIStartup = this.getDiscountUIStartup();
	return discountUIStartup.getDiscountTableNavigator();
}

NewDiscountUI.prototype.getDiscountService = function() {
	var discountUIStartup = this.getDiscountUIStartup();
	return discountUIStartup.getDiscountService();
}
NewDiscountUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newDiscountPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewDiscountUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewDiscountUI.prototype.getDiscount = function() {
	var self=this;
	var discType;
	var discValue;
	var name = $j(self.name).val();	
	if($j("input[name='check']:checked").val()=="on"){
	    discType="Predefined";
		discval=$j(self.discounttype).val();
		discValue=parseFloat(discval).toFixed(2);
	} else {
		discType="OnDemand";
		discValue=0;
	}
	var calculationType=$j("#newDiscount input[type='radio']:checked").val()
	var description=$j(self.description).val();
	var discount = new DiscountDTO();
	discount.setName(name);
	discount.setDiscType(discType);
	discount.setCalculationType(calculationType);
	discount.setDescription(description);
	discount.setDiscValue(discValue);
	return discount;
}

NewDiscountUI.prototype.clearFields = function() {
	self = this;
	$j(self.newDiscountPage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}

NewDiscountUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newDiscountPage + " " + self.inputFields);
	
	$j(self.newDiscountPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.discountModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.discountModal + self.newDiscountPage + " input[type=text]").val("");	
		$j(self.discountModal).trigger('reveal:close');
		$j(self.discountModal).remove();
		self=self.getDiscountUIStartup();
	});
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var discount = self.getDiscount();
		var discountValidator = new DiscountValidator();
		var error  = discountValidator.validate(discount);
		if(error.errorStatus==false) {
			var discountService = self.getDiscountService();	
			var discountResponse = discountService.createDiscount(discount);
			if(discountResponse.success==true) {
				showTip(constants.DISCOUNTCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var discountTableNavigator = self.getDiscountTableNavigator();
				discountTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(discountResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.discountModal + self.newDiscountPage + " input[type=text]").val("");	
		$j(self.discountModal).trigger('reveal:close');
		$j(self.discountModal).remove();
		self=self.getDiscountUIStartup();
	});	
	$j(self.check).click(function() {
		var checked_status = this.checked;
		if (checked_status == true) {
			$j(self.discountTypeId).text('Value-Predefined');
			$j(self.discounttype).removeAttr("disabled");
	   } else {
			$j(self.discountTypeId).text('Value-Ondemand');
			$j(self.discounttype).val("");
			$j(self.discounttype).attr("disabled", "disabled");
	   }
});



	

	
}