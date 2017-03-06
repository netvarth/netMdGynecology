function ViewDiscountUI(discountUIStartup) {
	this.viewDiscountPage = "#viewDiscount";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewDiscount #id label";
	this.value="#viewDiscount #value";
	this.name = "#viewDiscount #name";
	this.description = "#viewDiscount #description";
	this.viewCalculationType=" #viewDiscount #lblCalculationtype label";
	this.viewDiscountValue="#viewDiscount #lblViewDiscountValue label";
	this.lblViewDiscounttype="#viewDiscount #lblViewDiscounttype label";
	this.viewDiscountTypeId="#viewDiscount #viewDiscountTypeId";
	this.viewDiscountType="#viewDiscount #viewDiscountType";
	this.discountType="#viewDiscount #discountType";
	this.predefined="#viewDiscount #Predefined";						
	this.viewDiscType="#viewDiscount #viewdisctype";
	this.editDiscType="#viewDiscount #editDiscType";
	this.editDiscCalType="#viewDiscount #editDiscCalType";
	this.viewDiscCalType="#viewDiscount #viewDiscCalType";
	this.updateButton = '#viewDiscount #btnDiscountSave';
	this.editButton = '#viewDiscount #btnDiscountEdit';
	this.cancelButton = '#viewDiscount #btnDiscountCancel'; 
	this.ptbBack = "#discountGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#discountGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#discountGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.fixed="#viewDiscount #fixed"
	this.percentage="#viewDiscount #percentage";
	this.discount="";
	this.discountUIStartup=discountUIStartup;
	this.viewDiscountPTB = new ViewDiscountPTB(this);
}
ViewDiscountUI.prototype.getDiscountUIStartup = function() {
	return this.discountUIStartup;
}

ViewDiscountUI.prototype.setDiscountDetails = function(discount) {
	this.discount=discount;
}
ViewDiscountUI.prototype.getDiscountDetails = function() {
	return this.discount;
}

ViewDiscountUI.prototype.getViewDiscountPTB = function() {
	return this.viewDiscountPTB;
}
ViewDiscountUI.prototype.getDiscountTableNavigator = function() {
	var discountUIStartup = this.getDiscountUIStartup();
	return discountUIStartup.getDiscountTableNavigator();
}

ViewDiscountUI.prototype.getDiscountService = function() {
	var discountUIStartup = this.getDiscountUIStartup();
	return discountUIStartup.getDiscountService();
}
//Set the page title of the area ui page
ViewDiscountUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewDiscountUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewDiscountUI.prototype.init = function(discountId) {
	var self = this;
	var viewDiscountPTB = self.getViewDiscountPTB();
	viewDiscountPTB.init(self);
	pageHandler.create(constants.VIEWDISCOUNTURL);
	self.bindEvents();
	self.ViewDiscount(discountId);
}

ViewDiscountUI.prototype.ViewDiscount = function(discountId) {
	self=this;
	var header = constants.VIEWDISCOUNTTITLE;
	var discountService = self.getDiscountService();
	var discountResponse = discountService.viewDiscount(discountId);
	if(discountResponse.success==false) {
		var discount = new DiscountDTO();
		discount.setName(discountResponse.name);
		discount.setId(discountResponse.id);
		discount.setCalculationType(discountResponse.calculationType);
		discount.setDiscValue(discountResponse.discValue);
		discount.setDescription(discountResponse.description);
		discount.setDiscType(discountResponse.discType)
		self.setDiscountDetails(discount);
		self.setDiscount(discount);
		

	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(discountResponse.error));
	}
	self.setPageTitle(header);
}

ViewDiscountUI.prototype.setDiscount = function(discount) {
	var self=this;
	$j(self.id).text(discount.id);
	if(discount.calculationType=="Percentage")
		$j(self.viewCalculationType).text("%");
	else
		$j(self.viewCalculationType).html(constants.RUPEE);
	if(discount.discType=="Predefined"){
		$j(self.viewDiscountType).text('Value-Predefined');
		$j(self.discountType).text(discount.discValue.toFixed(2));
	}
	else{
		$j(self.viewDiscountType).text('Value-Ondemand');
		$j(self.predefined).removeAttr('checked');
		$j(seld.viewDiscType).text("");
	}
	$j(self.lblViewDiscounttype).text(discount.discValue);
	$j(self.discountType).text(discount.discValue);
	$j(self.name).val(discount.name);
	$j(self.description).val(discount.description);
}

ViewDiscountUI.prototype.getDiscount = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var calculationType=$j("#viewDiscount input[type='radio']:checked").val();
	var description = $j(self.description).val();
	var discType;
	var discValue;
	if($j("input[name='check']:checked").val()=="on"){
	    discType="Predefined";
		discval=$j(self.discountType).val();
		discValue=parseFloat(discval).toFixed(2);
	} else {
		discType="OnDemand";
		discValue=0;
	}
	var discount = new DiscountDTO();
	discount.setId(id);
	discount.setName(name);
	discount.setDiscType(discType);
	discount.setDescription(description); 
	discount.setCalculationType(calculationType);
	discount.setDiscValue(discValue);
	return discount;
}




ViewDiscountUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewDiscountPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewDiscountPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewDiscountPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewDiscType).hide();
	$j(self.viewDiscCalType).hide();
	$j(self.editDiscType).show();
	$j(self.editDiscCalType).show();
	$j(self.viewDiscountPage + " input[type=text],textarea").removeAttr('disabled');
}


ViewDiscountUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewDiscountPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewDiscountPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editDiscType).hide();
	$j(self.editDiscCalType).hide();
	$j(self.editButton).show();
	$j(self.viewDiscType).show();
	$j(self.viewDiscCalType).show();
	$j(self.viewDiscountPage + " input[type=text],textarea").attr('disabled',true);
}

ViewDiscountUI.prototype.getPrevId = function(curId,discountResult) {
	var prevId;
	$j(discountResult.discount).each(function (index, rowDiscount) {
		if(curId==rowDiscount.id)	{
			var arrayLength=(discountResult.discount).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=discountResult.discount[index-1].id;
		}
	});
	return prevId;	
}
	
ViewDiscountUI.prototype.getNextId = function(curId,discountResult) {
	var nextId;
	$j(discountResult.discount).each(function (index, rowDiscount) {
		if(curId==rowDiscount.id)	{
			var arrayLength=(discountResult.discount).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=discountResult.discount[index+1].id;
			}	
		}
	});	
	return nextId;	
}
ViewDiscountUI.prototype.setEditDetails=function(discount){
	if(discount.discType=="OnDemand"){
			$j(self.viewDiscType).attr("disabled", "disabled");
			$j(self.discountType).val("");
			$j(self.viewDiscountTypeId).text("Value-Ondemand");
			$j(self.predefined).removeAttr('checked');
	 }
	else{
		    $j(self.predefined).attr('checked','true');
		    $j(self.viewDiscountTypeId).text("Value-Predefined");
		    $j(self.discountType).val(discount.discValue);
			
        }
   if(discount.calculationType=="Fixed")
		 $j(self.fixed).attr('checked','true');
	else
		$j(self.percentage).attr('checked','true');

}
ViewDiscountUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		self.setEditDetails(self.discount);
		
	
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var discount = self.getDiscount();
		self.ViewDiscount(discount.id);
		self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var discount = self.getDiscount();
		var discountValidator = new DiscountValidator();
		var error  = discountValidator.validate(discount);
		if(error.errorStatus==false) {
			var discountService =self.getDiscountService();
			var discountResponse = discountService.updateDiscount(discount);
			if(discountResponse.success==true) {
				//For showing the global Tip
				self.ViewDiscount(discountResponse.id);
				showTip(constants.DISCOUNTUPDATESUCCESS);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(discountResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	$j(self.predefined).die('click').live('click',function(){
		 var checked_status = this.checked;
		removeErrors();
		if (checked_status == true) {
	  		$j(self.viewDiscountTypeId).text("Value-Predefined");
	  		 $j(self.discountType).removeAttr("disabled");
	    } 
	    else {
	  		$j(self.viewDiscountTypeId).text('Value-Ondemand');
	   		$j(self.discountType).val("");
      	    $j(self.discountType).attr("disabled", "disabled");
	   }
	});

	
   

	

	
}