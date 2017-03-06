function ViewTaxPTB(viewTaxUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewTaxUI = viewTaxUI;
	
	this.getViewTaxUI = function() {
		return this.viewTaxUI;
	}
		
	this.getTaxUIStartup = function() {
		var viewTaxUI = this.getViewTaxUI();
		return viewTaxUI.getTaxUIStartup();
	}
	
	this.getTaxTableNavigator = function() {
		var taxUIStartup = this.getTaxUIStartup();
		return taxUIStartup.getTaxTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewTaxUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var taxUIStartup = self.getTaxUIStartup();
			taxUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var taxTableNavigator = self.getTaxTableNavigator();
			var tax = source.getTax();
			var prevId = self.getPrevId(tax.getId(),taxTableNavigator.getPgDataList());
			viewUI.ViewTax(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var taxTableNavigator = self.getTaxTableNavigator();
			var tax = source.getTax();
			var prevId = self.getNextId(tax.getId(),taxTableNavigator.getPgDataList());
			viewUI.ViewTax(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.TAXGENERAL, constants.TAXGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}