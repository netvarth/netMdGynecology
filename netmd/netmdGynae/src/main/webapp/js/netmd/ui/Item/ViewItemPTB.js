function ViewItemPTB(viewItemUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewItemUI = viewItemUI;
	
	this.getViewItemUI = function() {
		return this.viewItemUI;
	}
		
	this.getItemUIStartup = function() {
		var viewItemUI = this.getViewItemUI();
		return viewItemUI.getItemUIStartup();
	}
	
	this.getItemTableNavigator = function() {
		var itemUIStartup = this.getItemUIStartup();
		return itemUIStartup.getItemTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewItemUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var itemUIStartup = self.getItemUIStartup();
			itemUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var itemTableNavigator = self.getItemTableNavigator();
			var item = source.getItem();
			var prevId = self.getPrevId(item.getId(),itemTableNavigator.getPgDataList());
			viewUI.ViewItem(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var itemTableNavigator = self.getItemTableNavigator();
			var item = source.getItem();
			var prevId = self.getNextId(item.getId(),itemTableNavigator.getPgDataList());
			viewUI.ViewItem(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ITEMGENERAL, constants.ITEMGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}