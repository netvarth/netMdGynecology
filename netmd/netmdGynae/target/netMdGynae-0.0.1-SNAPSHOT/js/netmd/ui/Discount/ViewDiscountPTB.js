function ViewDiscountPTB(viewDiscountUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewDiscountUI = viewDiscountUI;
	
	this.getViewDiscountUI = function() {
		return this.viewDiscountUI;
	}
		
	this.getDiscountUIStartup = function() {
		var viewDiscountUI = this.getViewDiscountUI();
		return viewDiscountUI.getDiscountUIStartup();
	}
	
	this.getDiscountTableNavigator = function() {
		var discountUIStartup = this.getDiscountUIStartup();
		return discountUIStartup.getDiscountTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewDiscountUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var discountUIStartup = self.getDiscountUIStartup();
			discountUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var discountTableNavigator = self.getDiscountTableNavigator();
			var discount = source.getDiscount();
			var prevId = self.getPrevId(discount.getId(),discountTableNavigator.getPgDataList());
			viewUI.ViewDiscount(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var discountTableNavigator = self.getDiscountTableNavigator();
			var discount = source.getDiscount();
			var nextId = self.getNextId(discount.getId(),discountTableNavigator.getPgDataList());
			viewUI.ViewDiscount(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.DISCOUNTGENERAL, constants.DISCOUNTGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}