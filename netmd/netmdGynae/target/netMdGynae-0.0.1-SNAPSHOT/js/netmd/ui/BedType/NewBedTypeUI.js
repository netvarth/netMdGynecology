function NewBedTypeUI(bedTypeUIStartup) {
	this.createButton = $j("#newBedType #btnBedTypeSave");
	this.cancelButton = $j('#newBedType #btnBedTypeCancel');
	this.newBedTypePage = "#newBedType";
	this.bedTypeModal = '#bedTypeModal';
	this.errorHeader = $j('#bedTypeModal #errorDivHeader');
	this.errorData = $j('#bedTypeModal #errorDivNewBedTypeData');
	this.type="#newBedType #type";
	this.rent="#newBedType #rent";
	this.count="#newBedType #count";
	this.inputFields = ":input";
	this.tax="#newBedType #tax";
	this.bedTypeUIStartup = bedTypeUIStartup;
}

NewBedTypeUI.prototype.getBedTypeUIStartup = function() {
	return this.bedTypeUIStartup;
}

NewBedTypeUI.prototype.getBedTypeTableNavigator = function() {
	var bedTypeUIStartup = this.getBedTypeUIStartup();
	return bedTypeUIStartup.getBedTypeTableNavigator();
}

NewBedTypeUI.prototype.getBedTypeService = function() {
	var bedTypeUIStartup = this.getBedTypeUIStartup();
	return bedTypeUIStartup.getBedTypeService();
}
NewBedTypeUI.prototype.init = function() {
	var self=this;
	var bedTypeService = self.getBedTypeService();	
	var bedTypeResponse = bedTypeService.getTaxTypes();
	methodInvoker.fillTaxTypeToControl(self.tax,bedTypeResponse);
}
NewBedTypeUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewBedTypeUI.prototype.getBedType = function() {
	var self=this;
	var type = $j(self.type).val();	
	var rent=parseFloat($j(self.rent).val());
	var count=parseInt($j(self.count).val());
	var taxId=$j(self.tax).val();
	var taxName=$j(self.tax+" option:selected").text();
	var bedType = new BedTypeDTO();
	bedType.setType(type);
	bedType.setRent(rent);
	bedType.setCount(count);
	bedType.setTaxName(taxName);
	bedType.setTaxId(taxId);
	return bedType;
}


NewBedTypeUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newBedTypePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewBedTypeUI.prototype.clearFields = function() {
	$j(this.newBedTypePage + " input[type=text]").val("");
	$j(self.tax+" option[value='0']").attr('selected','selected');
}

NewBedTypeUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newBedTypePage + " " + self.inputFields);
	
	$j(self.newBedTypePage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	  $j(self.bedTypeModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.bedTypeModal + self.newBedTypePage + " input[type=text]").val("");	
		$j(self.bedTypeModal).trigger('reveal:close');
		$j(self.bedTypeModal).remove();
		self=self.getBedTypeUIStartup();
	});
	
	  self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var bedType = self.getBedType();
		var bedTypeValidator = new BedTypeValidator();
		var error  = bedTypeValidator.validate(bedType);
		if(error.errorStatus==false) {
			var bedTypeService = self.getBedTypeService();
			var bedTypeResponse = bedTypeService.createBedType(bedType);
			if(bedTypeResponse.success==true) {
				showTip(constants.BEDTYPECREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var bedTypeNavigator = self.getBedTypeTableNavigator();
				bedTypeNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(bedTypeResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.bedTypeModal + self.newBedTypePage + " input[type=text]").val("");	
		$j(self.bedTypeModal).trigger('reveal:close');
		$j(self.bedTypeModal).remove();
		self=self.getBedTypeUIStartup();
	});	
 


	

	
}