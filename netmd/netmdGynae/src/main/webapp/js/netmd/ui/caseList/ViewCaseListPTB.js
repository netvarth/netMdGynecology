function ViewCaseListPTB(viewCaseListUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewCaseListUI = viewCaseListUI;
	
	this.getViewCaseUI = function() {
		return this.viewCaseListUI;
	}
		
	this.getCaseUIStartup = function() {
		var viewCaseListUI = this.getViewCaseUI();
		return viewCaseListUI.getCaseUIStartup();
	}
	
	this.getCaseTableNavigator = function() {
		var caseUIStartup = this.getCaseUIStartup();
		return caseUIStartup.getCaseTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var caseUIStartup = self.getCaseUIStartup();
			caseUIStartup.init();
		});
	
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.CASEGENERAL, constants.CASEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}