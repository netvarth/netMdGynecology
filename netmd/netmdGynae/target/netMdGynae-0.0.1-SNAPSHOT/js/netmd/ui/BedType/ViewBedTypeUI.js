function ViewBedTypeUI(bedTypeUIStartup) {
	this.viewBedTypePage = "#viewBedType";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewBedType #id label";
	this.type = "#viewBedType #type";
	this.rent="#viewBedType #rent";
	this.count="#viewBedType #count";
	this.taxName = "#viewBedType #tax";
	this.viewTax="#viewBedType  #viewTax",
	this.editTax="#viewBedType  #editTax",
	this.labelTax="#viewBedType #lbltax label";
	this.updateButton = '#viewBedType #btnBedTypeSave';
	this.editButton = '#viewBedType #btnBedTypeEdit';
	this.cancelButton = '#viewBedType #btnBedTypeCancel'; 
	this.ptbBack = "#bedTypeGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#bedTypeGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#bedTypeGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.bedTypeUIStartup=bedTypeUIStartup;
	this.viewBedTypePTB = new ViewBedTypePTB(this);
}
ViewBedTypeUI.prototype.getBedTypeUIStartup = function() {
	return this.bedTypeUIStartup;
}

ViewBedTypeUI.prototype.getViewBedTypePTB = function() {
	return this.viewBedTypePTB;
}

ViewBedTypeUI.prototype.getBedTypeTableNavigator = function() {
	var bedTypeUIStartup = this.getBedTypeUIStartup();
	return bedTypeUIStartup.getBedTypeTableNavigator();
}

ViewBedTypeUI.prototype.getBedTypeService = function() {
	var bedTypeUIStartup = this.getBedTypeUIStartup();
	return bedTypeUIStartup.getBedTypeService();
}
//Set the page title of the area ui page
ViewBedTypeUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewBedTypeUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}
ViewBedTypeUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBedTypePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

ViewBedTypeUI.prototype.init = function(bedTypeId) {
	var self = this;
	var viewBedTypePTB = self.getViewBedTypePTB();
	viewBedTypePTB.init(self);
	pageHandler.create(constants.VIEWBEDTYPEPAGEURL);
	this.bindEvents();
	this.viewBedType(bedTypeId);
}

ViewBedTypeUI.prototype.viewBedType = function(bedTypeId) {
	self=this;
	var header = constants.BEDTYPEVIEWINFO;
	var bedTypeService = self.getBedTypeService();
	var bedTypeResponse = bedTypeService.viewBedType(bedTypeId);
	if(bedTypeResponse.success==true) {
		var bedType = new BedTypeDTO();
		bedType.setType(bedTypeResponse.type);
		bedType.setId(bedTypeResponse.id);
		bedType.setRent(bedTypeResponse.rent.toFixed(2));
		bedType.setCount(bedTypeResponse.count);
		bedType.setTaxName(bedTypeResponse.taxName);
		bedType.setTaxId(bedTypeResponse.taxId);
		self.setBedType(bedType);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(bedTypeResponse.error));
	}
	self.setPageTitle(header);
}

ViewBedTypeUI.prototype.setBedType = function(bedType) {
	$j(self.id).text(bedType.id);
	$j(self.type).val(bedType.type);
	$j(self.rent).val(bedType.rent);
	$j(self.count).val(bedType.count);
	self.taxId=bedType.taxId;
	if(bedType.taxId==0)
		$j(self.labelTax).text(" ");
	else
		$j(self.labelTax).text(bedType.taxName);
}

ViewBedTypeUI.prototype.getBedType = function() {
var self=this;
	var id = parseInt($j(self.id).text());
	var type = $j(self.type).val();
	var rent = $j(self.rent).val();	
	var count = $j(self.count).val();
	var taxName=$j(self.taxName).text();
	var taxId=$j(self.taxName).val();
	var bedType=new BedTypeDTO();
	bedType.setId(id);
	bedType.setType(type);
	bedType.setRent(rent);
	bedType.setCount(count);
	bedType.setTaxName(taxName);
	bedType.setTaxId(taxId);
	return bedType;
}

ViewBedTypeUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBedTypePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}



ViewBedTypeUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewBedTypePage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewBedTypePage + " input[type=text]").removeAttr('readonly');
	$j(self.viewBedTypePage + " input[type=text]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewTax).hide();
	$j(self.editTax).show();
	$j(self.viewBedTypePage + " input[type=text]").removeAttr('disabled');
}

ViewBedTypeUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewBedTypePage + " input[type=text]").attr('readonly',true);
	$j(self.viewBedTypePage + " input[type=text]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewTax).show();
	$j(self.editTax).hide();
	$j(self.viewBedTypePage + " input[type=text]").attr('disabled',true);
}

ViewBedTypeUI.prototype.getPrevId = function(curId,bedTypeResult) {
	var prevId;
	$j(bedTypeResult.bedTypeList).each(function (index, rowbedType) {
		if(curId==rowbedType.id)	{
			var arrayLength=(bedTypeResult.bedTypeList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=bedTypeResult.bedTypeList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewBedTypeUI.prototype.getNextId = function(curId,bedTypeResult) {
	var nextId;
	$j(bedTypeResult.bedTypeList).each(function (index, rowbedType) {
		if(curId==rowbedType.id)	{
			var arrayLength=(bedTypeResult.bedTypeList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=bedTypeResult.bedTypeList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewBedTypeUI.prototype.bindEvents = function() {
	self = this;
	
	$j(self.viewbedTypePage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.viewTax).show();
		$j(self.editTax).hide();
		var bedTypeService = self.getBedTypeService();	
		var response = bedTypeService.getTaxTypes();
		methodInvoker.fillTaxTypeToControl(self.taxName,response);
		$j(self.taxName+" option[value="+self.taxId+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var bedType = self.getBedType();
		var bedTypeValidator = new BedTypeValidator();
		var error  = bedTypeValidator.validate(bedType);
		if(error.errorStatus==false) {
			var bedTypeService =self.getBedTypeService();
			var bedTypeResponse = bedTypeService.updateBedType(bedType);
			if(bedTypeResponse.success==true) {
				showTip(constants.BEDTYPEUPDATESUCCESS);//For showing the global Tip
				self.viewBedType(bedTypeResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(bedTypeResponse.error));
			}
		} else {
			self.createError(error);
		}
});

 $j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var bedType = self.getBedType();
		self.viewBedType(bedType.id);
		self.readable();
	});		
	
}