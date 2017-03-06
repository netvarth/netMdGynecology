function NewHeadUI(headUIStartup) {
	this.createButton = $j("#newHead #btnHeadSave");
	this.cancelButton = $j('#newHead #btnHeadCancel');
	this.newHeadPage = "#newHead";
	this.headModal = '#headModal';
	this.errorHeader = $j('#headModal #errorDivHeader');
	this.errorData = $j('#headModal #errorDivNewHeadData');
	this.headName="#newHead #name";
	this.parentHeadName ="#newHead #parentHead";
	this.description="#newHead #description";
	this.inputFields = ":input";
	this.headUIStartup = headUIStartup;

}

NewHeadUI.prototype.getHeadUIStartup = function() {
	return this.headUIStartup;
}

NewHeadUI.prototype.getHeadTableNavigator = function() {
	var headUIStartup = this.getHeadUIStartup();
	return headUIStartup.getHeadTableNavigator();
}
NewHeadUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newHeadPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
 NewHeadUI.prototype.getHeadService = function() {
	var headUIStartup = this.getHeadUIStartup();
	return headUIStartup.getHeadService();
} 

NewHeadUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.headName);
}
NewHeadUI.prototype.getHead = function() {
	var self=this;
	var headName = $j(self.headName).val();
	var description=$j(self.description).val();
	var parentHeadName=$j(self.parentHeadName+" option:selected").text();
	var head = new HeadDTO();
	head.setHeadName(headName);
	head.setDescription(description);
	if(parentHeadName!="Select Head")
		head.setParentHeadName(parentHeadName);
	return head;
}

NewHeadUI.prototype.clearFields = function() {
	self = this;
	$j(self.newHeadPage + " input[type=text],textarea").val("");
	$j(self.parentHeadName+" option[value='0']").attr('selected','selected');
	$j(self.headName ).focus();
}
NewHeadUI.prototype.init = function() {
    var self=this;
	var headService = self.getHeadService();	
	var headResponse = headService.getParentHead();
	methodInvoker.fillParentHeadToControl(self.parentHeadName,headResponse);
}

NewHeadUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newHeadPage + " " + self.inputFields);
	
	$j(self.newHeadPage+ " input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	 $j(self.headModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.headModal + self.newHeadPage + " input[type=text]").val("");	
		$j(self.headModal).trigger('reveal:close');
		$j(self.headModal).remove();
		self=self.getHeadUIStartup();
	});

    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var head = self.getHead();
		var headValidator = new HeadValidator();
		var error  = headValidator.validate(head);
		if(error.errorStatus==false) {
			var headService = self.getHeadService();
			var headResponse = headService.createHead(head);
			if(headResponse.success==true) {
				showTip(constants.HEADCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var headTableNavigator = self.getHeadTableNavigator();
				headTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(headResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.headModal + self.newHeadPage + " input[type=text]").val("");	
		$j(self.headModal).trigger('reveal:close');
		$j(self.headModal).remove();
		self=self.getHeadUIStartup();
	});	
	
}