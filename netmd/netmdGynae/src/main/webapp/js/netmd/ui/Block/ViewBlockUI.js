function ViewBlockUI(blockUIStartup) {
	this.viewBlockPage = "#viewBlock";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewBlock #id label";
	this.name = "#viewBlock #name";
	this.description = "#viewBlock #description";
	this.updateButton = '#viewBlock #btnBlockSave';
	this.editButton = '#viewBlock #btnBlockEdit';
	this.cancelButton = '#viewBlock #btnBlockCancel'; 
	this.ptbBack = "#blockGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#blockGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#blockGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.departmentListArray=[];
	this.blockUIStartup=blockUIStartup;
	this.viewBlockPTB = new ViewBlockPTB(this);
}
ViewBlockUI.prototype.getBlockUIStartup = function() {
	return this.blockUIStartup;
}

ViewBlockUI.prototype.getViewBlockPTB = function() {
	return this.viewBlockPTB;
}

ViewBlockUI.prototype.getBlockTableNavigator = function() {
	var blockUIStartup = this.getBlockUIStartup();
	return blockUIStartup.getBlockTableNavigator();
}

ViewBlockUI.prototype.getBlockService = function() {
	var blockUIStartup = this.getBlockUIStartup();
	return blockUIStartup.getBlockService();
}

ViewBlockUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewBlockUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}

ViewBlockUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBlockPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewBlockUI.prototype.init = function(blockId) {
	var self = this;
	var viewBlockPTB = self.getViewBlockPTB();
	viewBlockPTB.init(self);
	pageHandler.create(constants.VIEWBLOCKPAGEURL);
	self.bindEvents();
	self.ViewBlock(blockId);
}

ViewBlockUI.prototype.ViewBlock = function(blockId) {
	self=this;
	var header = constants.VIEWBLOCKTITLE;
	var blockService = self.getBlockService();
	var blockResponse = blockService.viewBlock(blockId);
	if(blockResponse.success==true) {
		var block = new BlockDTO();
		block.setName(blockResponse.name);
		block.setId(blockResponse.id);
		block.setDescription(blockResponse.description);
		self.setBlock(block);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(blockResponse.error));
	}
	self.setPageTitle(header);
}

ViewBlockUI.prototype.setBlock = function(block) {
	var self=this;
	$j(self.id).text(block.id);
	$j(self.name).val(block.name);
	$j(self.description).val(block.description);
}

ViewBlockUI.prototype.getBlock = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var description = $j(self.description).val();	
	var block = new BlockDTO();
	block.setId(id);
	block.setName(name);
	block.setDescription(description);
	block.setDepartmentDTO(self.departmentListArray); 
	return block;
}

ViewBlockUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewBlockPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewBlockPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewBlockPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewBlockPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewBlockUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewBlockPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewBlockPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewBlockPage + " input[type=text],textarea").attr('disabled',true);
}

ViewBlockUI.prototype.getPrevId = function(curId,blockResult) {
	var prevId;
	$j(blockResult.block).each(function (index, rowBlock) {
		if(curId==rowBlock.id)	{
			var arrayLength=(blockResult.block).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=blockResult.block[index-1].id;
		}
	});
	return prevId;	
}
	
ViewBlockUI.prototype.getNextId = function(curId,blockResult) {
	var nextId;
	$j(blockResult.block).each(function (index, rowBlock) {
		if(curId==rowBlock.id)	{
			var arrayLength=(blockResult.block).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=blockResult.block[index+1].id;
				}
			
		}
	});	
	return nextId;	
}

ViewBlockUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var block = self.getBlock();
	self.ViewBlock(block.id);
	self.readable();
		
	});

	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var block = self.getBlock();
		var blockValidator = new BlockValidator();
		var error  = blockValidator.validate(block);
		if(error.errorStatus==false) {
			var blockService =self.getBlockService();
			var blockResponse = blockService.updateBlock(block);
			if(blockResponse.success==true) {
				showTip(constants.BLOCKUPDATESUCCESS);//For showing the global Tip
				self.ViewBlock(blockResponse.id);
				self.readable();
			} else 
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(blockResponse.error));
		} else 
			self.createError(error);			
	});
	
}
	
