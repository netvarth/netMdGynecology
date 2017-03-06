function NewItemUI(itemUIStartup) {
	this.createButton = $j("#newItem #btnItemSave");
	this.cancelButton = $j('#newItem #btnItemCancel');
	this.newItemPage = "#newItem";
	this.itemModal = '#itemModal';
	this.errorHeader = $j('#itemModal #errorDivHeader');
	this.errorData = $j('#itemModal #errorDivNewItemData');
	this.name="#newItem #name";
	this.description="#newItem #description";
	this.price = "#newItem #price";
	this.quantity = "#newItem #quantity";
	this.tax="#newItem #tax";
	this.inputFields = ":input";
	this.itemUIStartup = itemUIStartup;

}

NewItemUI.prototype.getItemUIStartup = function() {
	return this.itemUIStartup;
}

NewItemUI.prototype.getItemTableNavigator = function() {
	var itemUIStartup = this.getItemUIStartup();
	return itemUIStartup.getItemTableNavigator();
}
NewItemUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newItemPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewItemUI.prototype.getItemService = function() {
	var itemUIStartup = this.getItemUIStartup();
	return itemUIStartup.getItemService();
}

NewItemUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewItemUI.prototype.getItem = function() {
	var self=this;
	var name = $j(self.name).val();	
	var description=$j(self.description).val();
	var price=$j(self.price).val();
	var quantity=$j(self.quantity).val();
	var taxId=$j(self.tax).val();
	var taxName=$j(self.tax+" option:selected").text();
	var item = new ItemDTO();
	item.setName(name);
	item.setDescription(description);
	item.setPrice(parseFloat(price));
	item.setQuantity(parseInt(quantity));
	item.setTaxName(taxName);
	item.setTaxId(parseInt(taxId));
	return item;
}

NewItemUI.prototype.clearFields = function() {
	self = this;
	$j(self.newItemPage + " input[type=text],textarea").val("");
	$j(self.tax+" option[value='0']").attr('selected','selected');
	$j(self.name ).focus();
}
NewItemUI.prototype.init = function() {
	var self=this;
	var itemService = self.getItemService();	
	var itemResponse = itemService.getTaxTypes();
	methodInvoker.fillTaxTypeToControl(self.tax,itemResponse);
}

NewItemUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newItemPage + " " + self.inputFields);
	
	$j(self.newItemPage+ " input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	 $j(self.itemModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.itemModal + self.newItemPage + " input[type=text]").val("");	
		$j(self.itemModal).trigger('reveal:close');
		$j(self.itemModal).remove();
		self=self.getItemUIStartup();
	});

    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var item = self.getItem();
		var itemValidator = new ItemValidator();
		var error  = itemValidator.validate(item);
		if(error.errorStatus==false) {
			var itemService = self.getItemService();
			var itemResponse = itemService.createItem(item);		
			if(itemResponse.success==true) {
				showTip(constants.ITEMCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var itemTableNavigator = self.getItemTableNavigator();
				itemTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(itemResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.itemModal + self.newItemPage + " input[type=text]").val("");	
		$j(self.itemModal).trigger('reveal:close');
		$j(self.itemModal).remove();
		self=self.getItemUIStartup();
	});	



	

	
}