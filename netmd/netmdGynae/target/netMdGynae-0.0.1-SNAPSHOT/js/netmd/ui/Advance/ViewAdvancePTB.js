function ViewAdvancePTB(viewAdvanceUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewAdvanceUI = viewAdvanceUI;
	
 	this.getViewAdvanceUI = function() {
		return this.viewAdvanceUI;
	}
		
	this.getAdvanceUIStartup = function() {
		var viewAdvanceUI = this.getViewAdvanceUI();
		return viewAdvanceUI.getAdvanceUIStartup();
	}
	
	this.getAdvanceTableNavigator = function() {
		var advanceUIStartup = this.getAdvanceUIStartup();
		return advanceUIStartup.getAdvanceTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewAdvanceUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var advanceUIStartup = self.getAdvanceUIStartup();
			advanceUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var advanceTableNavigator = self.getAdvanceTableNavigator();
			var advance = source.getAdvance();
			var prevId = self.getPrevId(advance.getId(),advanceTableNavigator.getPgDataList());
			viewUI.ViewAdvance(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var advanceTableNavigator = self.getAdvanceTableNavigator();
			var advance = source.getAdvance();
			var prevId = self.getNextId(advance.getId(),advanceTableNavigator.getPgDataList());
			viewUI.ViewAdvance(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ADVANCEGENERAL, constants.ADVANCEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}  
}