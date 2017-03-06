function NewBlockUI(blockUIStartup) {
	this.createButton = $j("#newBlock #btnBlockSave");
	this.cancelButton = $j('#newBlock #btnBlockCancel');
	this.newBlockPage = "#newBlock";
	this.blockModal = '#blockModal';
	this.errorHeader = $j('#blockModal #errorDivHeader');
	this.errorData = $j('#blockModal #errorDivNewBlockData');
	this.name="#newBlock #name";
	this.description="#newBlock #description";
	this.inputFields = ":input";
	this.blockUIStartup = blockUIStartup;

}

NewBlockUI.prototype.getBlockUIStartup = function() {
	return this.blockUIStartup;
}

NewBlockUI.prototype.getBlockTableNavigator = function() {
	var blockUIStartup = this.getBlockUIStartup();
	return blockUIStartup.getBlockTableNavigator();
}

NewBlockUI.prototype.getBlockService = function() {
	var blockUIStartup = this.getBlockUIStartup();
	return blockUIStartup.getBlockService();
}

NewBlockUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewBlockUI.prototype.getBlock = function() {
	var self=this;
	var name = $j(self.name).val();	
	var departmentList=[];
	var description=$j(self.description).val();
	var block = new BlockDTO();
	block.setName(name);
	block.setDescription(description);
	block.setDepartmentDTO(departmentList);
	return block;
}
NewBlockUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newBlockPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewBlockUI.prototype.clearFields = function() {
	self = this;
	$j(self.newBlockPage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}

NewBlockUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newBlockPage + " " + self.inputFields);
	$j(self.newBlockPage + " input[type=text] ").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
   $j(self.blockModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.blockModal + self.newBlockPage + " input[type=text]").val("");	
		$j(self.blockModal).trigger('reveal:close');
		$j(self.blockModal).remove();
		self=self.getBlockUIStartup();
	});
	
	
    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var block = self.getBlock();
		var blockValidator = new BlockValidator();
		var error  = blockValidator.validate(block);
		if(error.errorStatus==false) {
			var blockService = self.getBlockService();
			var blockResponse = blockService.createBlock(block);
			if(blockResponse.success==true) {
				showTip(constants.BLOCKCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var blockTableNavigator = self.getBlockTableNavigator();
				blockTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(blockResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.blockModal + self.newBlockPage + " input[type=text]").val("");	
		$j(self.blockModal).trigger('reveal:close');
		$j(self.blockModal).remove();
		self=self.getBlockUIStartup();
	});	



	

	
}