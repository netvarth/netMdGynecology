function ViewSupportPTB(viewSupportUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewSupportUI = viewSupportUI;
	
	this.getViewSupportUI = function() {
		return this.viewSupportUI;
	}
		
	this.getSupportUIStartup = function() {
		var viewSupportUI = this.getViewSupportUI();
		return viewSupportUI.getSupportUIStartup();
	}
	
	this.getSupportTableNavigator = function() {
		var supportUIStartup = this.getSupportUIStartup();
		return supportUIStartup.getSupportTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewSupportUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var supportUIStartup = self.getSupportUIStartup();
			supportUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var supportTableNavigator = self.getSupportTableNavigator();
			var support = source.getSupport();
			var prevId = self.getPrevId(support.getId(),supportTableNavigator.getPgDataList());
			viewUI.ViewSupport(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var supportTableNavigator = self.getSupportTableNavigator();
			var support = source.getSupport();
			var prevId = self.getNextId(support.getId(),supportTableNavigator.getPgDataList());
			viewUI.ViewSupport(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.SUPPORTGENERAL, constants.SUPPORTGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}