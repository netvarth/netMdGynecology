function ViewItemUI(itemUIStartup) {
	this.viewItemPage = "#viewItem";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewItem #id label";
	this.name = "#viewItem #name";
	this.description = "#viewItem #description";
	this.price = "#viewItem #price";
	this.quantity = "#viewItem #quantity";
	this.taxName = "#viewItem #tax";
	this.viewTax="#viewItem  #viewTax",
	this.editTax="#viewItem  #editTax",
	this.labelTax="#viewItem #lbltax label ";
	this.updateButton = '#viewItem #btnItemSave';
	this.taxId;
	this.editButton = '#viewItem #btnItemEdit';
	this.cancelButton = '#viewItem #btnItemCancel'; 
	this.ptbBack = "#itemGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#itemGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#itemGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.departmentListArray=[];
	this.itemUIStartup=itemUIStartup;
	this.viewItemPTB = new ViewItemPTB(this);
}
ViewItemUI.prototype.getItemUIStartup = function() {
	return this.itemUIStartup;
}

ViewItemUI.prototype.getViewItemPTB = function() {
	return this.viewItemPTB;
}

ViewItemUI.prototype.getItemTableNavigator = function() {
	var itemUIStartup = this.getItemUIStartup();
	return itemUIStartup.getItemTableNavigator();
}

ViewItemUI.prototype.getItemService = function() {
	var itemUIStartup = this.getItemUIStartup();
	return itemUIStartup.getItemService();
}
//Set the page title of the item ui page
ViewItemUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewItemUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewItemUI.prototype.init = function(itemId) {
	var self = this;
	var viewItemPTB = self.getViewItemPTB();
	viewItemPTB.init(self);
	pageHandler.create(constants.VIEWITEMURL);
	self.bindEvents();
	self.ViewItem(itemId);
}

ViewItemUI.prototype.ViewItem = function(itemId) {
	self=this;
	var header = constants.VIEWITEMTITLE;
	var itemService = self.getItemService();
	var itemResponse = itemService.viewItem(itemId);	
	if(itemResponse.success==true) {
		var item = new ItemDTO();		
		item.setName(itemResponse.name);	
		item.setId(itemResponse.id);
		item.setDescription(itemResponse.description);
		item.setPrice(itemResponse.price);
		item.setQuantity(itemResponse.quantity);
		item.setTaxName(itemResponse.taxName);
		item.setTaxId(itemResponse.taxId);
		self.setItem(item);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(itemResponse.error));
	}	
	self.setPageTitle(header);	
}

ViewItemUI.prototype.setItem = function(item) {
	var self=this;
	$j(self.id).text(item.id);	
	$j(self.name).val(item.name);	
	$j(self.price).val(item.price.toFixed(2));
	$j(self.quantity).val(item.quantity);
	$j(self.description).val(item.description);
	self.taxId=item.taxId;
	if(item.taxId==0)
		$j(self.labelTax).text(" ");
	else
		$j(self.labelTax).text(item.taxName);


}

ViewItemUI.prototype.getItem = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var description = $j(self.description).val();	
	var price=$j(self.price).val();
	var quantity=$j(self.quantity).val();
	var taxName=$j(self.taxName).text();
	var taxId=$j(self.taxName).val();

	var item = new ItemDTO();
	item.setName(name);
	item.setId(id);
	item.setDescription(description);
	item.setPrice(price);
	item.setQuantity(quantity);
	item.setTaxName(taxName);
	item.setTaxId(taxId);
	return item;
}


ViewItemUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewItemPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewTax).hide();
	$j(self.editTax).show();
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").removeAttr('readonly');
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").removeAttr('disabled');
}

ViewItemUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").attr('readonly',true);
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.viewTax).show();
	$j(self.editTax).hide();
	$j(self.editButton).show();
	$j(self.viewItemPage + " input[type=text],textarea,input[type=select]").attr('disabled',true);
}

ViewItemUI.prototype.getPrevId = function(curId,itemResult) {
	var prevId;
	$j(itemResult.itemList).each(function (index, rowItem) {
		if(curId==rowItem.id)	{
			var arrayLength=(itemResult.itemList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=itemResult.itemList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewItemUI.prototype.getNextId = function(curId,itemResult) {
	var nextId;
	$j(itemResult.itemList).each(function (index, rowItem) {
		if(curId==rowItem.id)	{
			var arrayLength=(itemResult.itemList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=itemResult.itemList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewItemUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.viewTax).show();
		$j(self.editTax).hide();
		var itemService = self.getItemService();	
		var itemResponse = itemService.getTaxTypes();
		methodInvoker.fillTaxTypeToControl(self.taxName,itemResponse);
		$j(self.taxName+" option[value="+self.taxId+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var item = self.getItem();
	self.ViewItem(item.id);
	self.readable();
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var item = self.getItem();
		var itemValidator = new ItemValidator();
		var error  = itemValidator.validate(item);
		if(error.errorStatus==false) {
			var itemService =self.getItemService();
			var itemResponse = itemService.updateItem(item);
			if(itemResponse.success==true) {
				showTip(constants.ITEMUPDATESUCCESS);//For showing the global Tip
				self.ViewItem(itemResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(itemResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	

	
}