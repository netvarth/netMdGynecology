function ViewCasePTB(viewCaseUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewCaseUI = viewCaseUI;
	
	this.getViewCaseUI = function() {
		return this.viewCaseUI;
	}
		
	this.getCaseUIStartup = function() {
		var viewCaseUI = this.getViewCaseUI();
		return viewCaseUI.getCaseUIStartup();
	}
	
	this.getCaseTableNavigator = function() {
		var caseUIStartup = this.getCaseUIStartup();
		return caseUIStartup.getCaseTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewCaseUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var caseUIStartup = self.getCaseUIStartup();
			caseUIStartup.init(viewUI.patientId);
		});
		/* $j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var caseTableNavigator = self.getCaseTableNavigator();
			var caseObj = source.getCase();
			var prevId = self.getPrevId(caseObj.getId(),caseTableNavigator.getPgDataList());
			viewUI.ViewCase(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var caseTableNavigator = self.getCaseTableNavigator();
			var caseObj = source.getCase();
			var prevId = self.getNextId(caseObj.getId(),caseTableNavigator.getPgDataList());
			viewUI.ViewCase(prevId);
		}); */
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.CASEGENERAL, constants.CASEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}