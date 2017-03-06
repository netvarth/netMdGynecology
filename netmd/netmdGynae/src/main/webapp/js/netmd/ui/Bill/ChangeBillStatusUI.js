function  ChangeBillStatusUI(BillUIStartup) {
	this.BillUIStartup = BillUIStartup;
	this.statusModal = "#" + constants.BILLSTATUSMODAL;
	this.createButton =$j(this.statusModal + " #btnSave");
	this.cancelButton=$j(this.statusModal + " #btnCancel");
	this.errorHeader = $j(this.statusModal + " #errorDivHeader");
	this.errorData = $j(this.statusModal + " #errorDivData");
	this.note = this.statusModal + " #note";
	this.status=this.statusModal + " #selectStatus";
}
ChangeBillStatusUI.prototype.getBillUIStartup = function() {
	return this.BillUIStartup;
}
ChangeBillStatusUI.prototype.getBillTableNavigator = function() {
	var BillUIStartup = this.getBillUIStartup();
	return BillUIStartup.getBillTableNavigator();
}
ChangeBillStatusUI.prototype.getBillService = function() {
	var BillUIStartup = this.getBillUIStartup();
	return BillUIStartup.getBillService();
}
ChangeBillStatusUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
ChangeBillStatusUI.getEnumList=function() {
		ajaxProcessor.setUrl(constants.ENUMLISTURL);
		this.enumList =ajaxProcessor.get();
		return this.enumList;
	}
	
 ChangeBillStatusUI.prototype.bindEvents = function() {
	self = this;
	 self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var billService = self.getBillService();
		var statusRequest = self.getBillStatusRequest();
		//alert(JSON.stringify(statusRequest));
		var response= billService.updateBillStatus(statusRequest);
		//alert(JSON.stringify(response));
		if(response.success==true) {
			showTip(constants.BillSTATUSUPDATESUCCESS);
			self.cancelButton.trigger('click');
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
	});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.statusModal).trigger('reveal:close');
		$j(self.statusModal).remove();
		self=self.getBillUIStartup();
		self.getBillTableNavigator().list();
	})
	
	$j(self.statusModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.statusModal).trigger('reveal:close');
		$j(self.statusModal).remove();
		self=self.getBillUIStartup();
	}); 
}	
ChangeBillStatusUI.prototype.init = function() {
	var self=this;
	self.fillChangableStatusList(self.status);
	self.bindEvents();
}
ChangeBillStatusUI.prototype.fillChangableStatusList = function(controlName) {
	$j(controlName).empty();
	var statuslist = ChangeBillStatusUI.getChangableStatusList();
	for(i=0;i<statuslist.length;i++) 
		$j(controlName).append('<option value='+statuslist[i]+'>'+statuslist[i]+'</option>');
}
ChangeBillStatusUI.prototype.getBillStatusRequest = function() {
	self=this;
	var billUI = self.getBillUIStartup();
	var billId=billUI.getSelectedBillId();
	var statusReq = new BillStatusRequestDTO();
	var note = commonMethodInvoker.nl2br($j(self.note).val());
	var status = $j(self.status).val();
	statusReq.setBillstatus(status);
	statusReq.setNote(note);
	statusReq.setUid(billId);
	return statusReq;
}
 ChangeBillStatusUI.getChangableStatusList=function() {
		self=this;
		var statuslist = [];
		var enumList = this.getEnumList();
		$j(enumList.enumListDTO).each(function (index, list) {
			if(list.key==constants.BILLSTATUSENUM) {
				$j(list.enumValues).each(function (enumvalueIndex, enumvalue) {
						if(enumvalue.displayName!=constants.OPEN && enumvalue.displayName!=constants.CLOSED)
						statuslist.push(enumvalue.displayName);
				}); 
				return false;
			}
		});
		return statuslist;
	}  