function ViewBillPTB(viewBillUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewBillUI = viewBillUI;
	
	this.getViewBillUI = function() {
		return this.viewBillUI;
	}
		
	this.getBillUIStartup = function() {
		var viewBillUI = this.getViewBillUI();
		return viewBillUI.getBillUIStartup();
	}
	
	this.getBillTableNavigator = function() {
		var billUIStartup = this.getBillUIStartup();
		return billUIStartup.getBillTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewBillUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var billUIStartup = self.getBillUIStartup();
			billUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var billTableNavigator = self.getBillTableNavigator();
			var billId = source.billUid;
			var prevId = self.getPrevId(billId,billTableNavigator.getPgDataList());
			viewUI.init(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var billTableNavigator = self.getBillTableNavigator();
			var billId = source.billUid;
			var nextId = self.getNextId(billId,billTableNavigator.getPgDataList());
			viewUI.init(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.BILLGENERAL, constants.BILLGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}