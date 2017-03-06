function ViewHeadPTB(viewHeadUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewHeadUI = viewHeadUI;
	
	this.getViewHeadUI = function() {
		return this.viewHeadUI;
	}
		
	this.getHeadUIStartup = function() {
		var viewHeadUI = this.getViewHeadUI();
		return viewHeadUI.getHeadUIStartup();
	}
	
	this.getHeadTableNavigator = function() {
		var headUIStartup = this.getHeadUIStartup();
		return headUIStartup.getHeadTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewHeadUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var headUIStartup = self.getHeadUIStartup();
			headUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var headTableNavigator = self.getHeadTableNavigator();
			var head = source.getHead();
			var prevId = self.getPrevId(head.getId(),headTableNavigator.getPgDataList());
			viewUI.ViewHead(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var headTableNavigator = self.getHeadTableNavigator();
			var head = source.getHead();
			var prevId = self.getNextId(head.getId(),headTableNavigator.getPgDataList());
			viewUI.ViewHead(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.HEADGENERAL, constants.HEADGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}