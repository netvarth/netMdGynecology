function ViewAdvanceUI(advanceUIStartup) {
	this.viewAdvancePage = "#viewAdvance";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewAdvance #id label";
	this.name = "#viewAdvance #name";
	this.email="#viewAdvance #email";
	this.amount = "#viewAdvance #amount";
	this.updateButton = '#viewAdvance #btnAdvanceSave';
	this.editButton = '#viewAdvance #btnAdvanceEdit';
	this.cancelButton = '#viewAdvance #btnAdvanceCancel'; 
	this.ptbBack = "#advanceGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#advanceGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#advanceGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.patientId="";
	this.departmentListArray=[];
	this.advanceUIStartup=advanceUIStartup;
	this.viewAdvancePTB = new ViewAdvancePTB(this);

}
 ViewAdvanceUI.prototype.getAdvanceUIStartup = function() {
	return this.advanceUIStartup;
}

ViewAdvanceUI.prototype.getViewAdvancePTB = function() {
	return this.viewAdvancePTB;
}

ViewAdvanceUI.prototype.getAdvanceTableNavigator = function() {
	var advanceUIStartup = this.getAdvanceUIStartup();
	return advanceUIStartup.getAdvanceTableNavigator();
}

ViewAdvanceUI.prototype.getAdvanceService = function() {
	var advanceUIStartup = this.getAdvanceUIStartup();
	return advanceUIStartup.getAdvanceService();
}
//Set the page title of the advance ui page
ViewAdvanceUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewAdvanceUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}
ViewAdvanceUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewAdvancePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

ViewAdvanceUI.prototype.init = function(advanceId) {
	var self = this;
	var viewAdvancePTB = self.getViewAdvancePTB();
	viewAdvancePTB.init(self);
	pageHandler.create(constants.VIEWADVANCEURL);
	self.bindEvents();
	self.ViewAdvance(advanceId);
}

ViewAdvanceUI.prototype.ViewAdvance = function(advanceId) {
	self=this;
	var header = constants.VIEWADVANCETITLE;
	var advanceService = self.getAdvanceService();
	var advanceResponse = advanceService.viewAdvance(advanceId);
	if(advanceResponse.success==true) {
		var advance = new AdvanceDTO();		
		advance.setName(advanceResponse.patientName);	
		advance.setPatientId(advanceResponse.patientId);
		advance.setId(advanceResponse.id);
		advance.setAmount(advanceResponse.amount);
		advance.setEmail(advanceResponse.email);
		self.patientId=advanceResponse.patientId;
		self.setAdvance(advance);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(advanceResponse.error));
	}	
	self.setPageTitle(header);	
}

ViewAdvanceUI.prototype.setPatientList = function() {
	var advanceService=self.getAdvanceService();
    self.patientDetails=advanceService.getPatientDetails();
    methodInvoker.fillPatientDetailsToControl(self.name,self.patientDetails);
}


ViewAdvanceUI.prototype.fillOtherPatientDetails= function(patientDetail) {
	self.patientId=patientDetail.id;
	$j(self.email).text(patientDetail.email);
	
}
ViewAdvanceUI.prototype.setAdvance = function(advance) {
	var self=this;
	$j(self.id).text(advance.id);	
	$j(self.name).val(advance.name);	
	$j(self.amount).val(advance.amount.toFixed(2));
    $j(self.email).val(advance.email);
}

ViewAdvanceUI.prototype.getAdvance = function() {
	var self=this;
	var patientId = parseInt(self.patientId);
	var advanceId= parseInt($j(self.id).text());
	var amount=parseFloat($j(self.amount).val());
	var advance = new AdvanceDTO();
	advance.setPatientId(patientId);
	advance.setId(advanceId);
	advance.setAmount(amount);
	return advance;
}


ViewAdvanceUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewAdvancePage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").removeAttr('readonly');
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").removeAttr('disabled');
}

ViewAdvanceUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").attr('readonly',true);
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewAdvancePage + " input[type=text],textarea,input[type=select]").attr('disabled',true);
}

ViewAdvanceUI.prototype.getPrevId = function(curId,advanceResult) {
	var prevId;
	$j(advanceResult.advanceList).each(function (index, rowAdvance) {
		if(curId==rowAdvance.id)	{
			var arrayLength=(advanceResult.advanceList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=advanceResult.advanceList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewAdvanceUI.prototype.getNextId = function(curId,advanceResult) {
	var nextId;
	$j(advanceResult.advanceList).each(function (index, rowAdvance) {
		if(curId==rowAdvance.id)	{
			var arrayLength=(advanceResult.advanceList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=advanceResult.advanceList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewAdvanceUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		self.setPatientList();
		self.writable(); 	
	});


	$j(self.name).autocomplete({
   	 select: function(e, ui){
       $j(self.name).val(ui.item.value);
        commonMethodInvoker.removeErrors();
        var patientName=$j(self.name).val();
		var newPatientName=patientName.split("[")[0];
		var email=patientName.split("[")[1];
		var originalemail=email.split("]")[0];
		var patientDetail=methodInvoker.getPatientDetails(originalemail,self.patientDetails);
		$j(self.email).val(patientDetail.email); 
		self.patientId=patientDetail.id;
	    this.value =newPatientName;
   		 return false;
    }
});
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var advance = self.getAdvance();
	self.ViewAdvance(advance.id);
	self.readable();
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var advance = self.getAdvance();
				alert(JSON.stringify(advance));
		var advanceValidator = new AdvanceValidator();
		var error  = advanceValidator.validate(advance);
		if(error.errorStatus==false) {
			var advanceService =self.getAdvanceService();
			var advanceResponse = advanceService.updateAdvance(advance);
			if(advanceResponse.success==true) {
				showTip(constants.ADVANCEUPDATESUCCESS);//For showing the global Tip
				self.ViewAdvance(advanceResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(advanceResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	}
	 

	
