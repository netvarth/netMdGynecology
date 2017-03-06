function ViewHeadUI(headUIStartup) {
	this.viewHeadPage = "#viewHead";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewHead #id label";
	this.headName = "#viewHead #headName";
	this.parentHeadName="#viewHead #parentHeadname label";
	this.parentHeadId="#viewHead #parentHeadId";
	this.headType="#viewHead #headType";
	this.lblHeadType="#viewHead #lblHeadType label";
	this.lblHeadName="#viewHead #lblHeadName label";
	this.description = "#viewHead #description";
	this.viewHeadType="#viewHead  #viewHeadType",
	this.editHeadType="#viewHead  #editHeadType",
	this.viewHeadName="#viewHead  #viewHeadName",
	this.editHeadName="#viewHead  #editHeadName",
	this.updateButton = '#viewHead #btnHeadSave';
	this.editButton = '#viewHead #btnHeadEdit';
	this.cancelButton = '#viewHead #btnHeadCancel';
	this.headId;	
	this.ptbBack = "#headGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#headGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#headGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.headUIStartup=headUIStartup;
	this.viewHeadPTB = new ViewHeadPTB(this);
}
ViewHeadUI.prototype.getHeadUIStartup = function() {
	return this.headUIStartup;
}

ViewHeadUI.prototype.getViewHeadPTB = function() {
	return this.viewHeadPTB;
}

ViewHeadUI.prototype.getHeadTableNavigator = function() {
	var headUIStartup = this.getHeadUIStartup();
	return headUIStartup.getHeadTableNavigator();
}

ViewHeadUI.prototype.getHeadService = function() {
	var headUIStartup = this.getHeadUIStartup();
	return headUIStartup.getHeadService();
}
//Set the page title of the head ui page
ViewHeadUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewHeadUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.headName);
}


ViewHeadUI.prototype.init = function(headId) {
	var self = this;
	var viewHeadPTB = self.getViewHeadPTB();
	viewHeadPTB.init(self);
	pageHandler.create(constants.VIEWHEADURL);
	self.bindEvents();
	self.ViewHead(headId);
}

ViewHeadUI.prototype.ViewHead = function(headId) {
	self=this;
	var header = constants.VIEWHEADTITLE;
	var headService = self.getHeadService();
	var headResponse = headService.viewHead(headId);
	//alert(JSON.stringify(headResponse));
	if(headResponse.success==true) {
		var head = new HeadDTO();		
		self.setHead(headResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(headResponse.error));
	}	
	self.setPageTitle(header);	
}

ViewHeadUI.prototype.setHead = function(headResponse) {
	var self=this;
	$j(self.id).text(headResponse.id);	
	$j(self.headName).val(headResponse.headName);	
	$j(self.parentHeadName).text(headResponse.parentHeadName);	
	$j(self.lblHeadType).text(headResponse.headtype);	
	//$j(self.lblHeadName).text(headResponse.headName);
	$j(self.description).val(headResponse.description);
	//$j(self.parentHeadId).val(headResponse.parentHeadId);
}

ViewHeadUI.prototype.getHead = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var headName = $j(self.headName).val();
	var parentHeadName = $j(self.parentHeadName).text();
	var headType =$j(self.headType).val();
	var description = $j(self.description).val();	
	var head = new HeadDTO();
	head.setHeadName(headName);
	head.setParentHeadName(parentHeadName);
	head.setHeadType(headType);
	head.setId(id);
	head.setDescription(description);
	return head;
}


ViewHeadUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewHeadPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	//$j(self.viewHeadType).hide();
	//$j(self.editHeadType).show();
	//$j(self.viewHeadName).hide();
	//$j(self.editHeadName).show();
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").removeAttr('readonly');
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").removeAttr('disabled');
}

ViewHeadUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	//$j(self.viewHeadType).show();
	//$j(self.editHeadType).hide();
	//$j(self.viewHeadName).show();
	//$j(self.editHeadName).hide();
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").attr('readonly',true);
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewHeadPage + " input[type=text],textarea,input[type=select]").attr('disabled',true);
}

ViewHeadUI.prototype.getPrevId = function(curId,headResult) {
	var prevId;
	$j(headResult.headList).each(function (index, rowHead) {
		if(curId==rowHead.id)	{
			var arrayLength=(headResult.headList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=headResult.headList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewHeadUI.prototype.getNextId = function(curId,headResult) {
	var nextId;
	$j(headResult.headList).each(function (index, rowHead) {
		if(curId==rowHead.id)	{
			var arrayLength=(headResult.headList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=headResult.headList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewHeadUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		//$j(self.viewHeadType).show();
		//$j(self.editHeadType).hide();
		//$j(self.viewHeadName).show();
		//$j(self.editHeadName).hide();
		var headService = self.getHeadService();	
		var headResponse = headService.getParentHead();
		methodInvoker.fillHeadTypeToControl(self.headType,headResponse);
		//$j(self.headType+" option[value="+self.headType+"]").attr('selected','selected');
		//methodInvoker.fillParentHeadToControl(self.parentHead,headResponse);
		self.writable(); 	
	});
	
	$j(self.cancelButton).die('click').live('click',function(){
		self.this;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var head = self.getHead();
		self.ViewHead(head.id);
		self.readable();	
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.this;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var head = self.getHead();
		var headValidator = new HeadValidator();
		var error  = headValidator.validate(head);
		if(error.errorStatus==false) {
			var headService =self.getHeadService();
			var headResponse = headService.updateHead(head);
			//alert(JSON.stringify(headResponse));
			if(headResponse.success==true) {
				showTip(constants.HEADUPDATESUCCESS);//For showing the global Tip
				self.ViewHead(headResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(headResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	

	
}