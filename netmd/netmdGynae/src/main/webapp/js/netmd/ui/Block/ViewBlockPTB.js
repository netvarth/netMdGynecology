function ViewBlockPTB(viewBlockUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewBlockUI = viewBlockUI;
	
	this.getViewBlockUI = function() {
		return this.viewBlockUI;
	}
		
	this.getBlockUIStartup = function() {
		var viewBlockUI = this.getViewBlockUI();
		return viewBlockUI.getBlockUIStartup();
	}
	
	this.getBlockTableNavigator = function() {
		var blockUIStartup = this.getBlockUIStartup();
		return blockUIStartup.getBlockTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewBlockUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var blockUIStartup = self.getBlockUIStartup();
			blockUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var blockTableNavigator = self.getBlockTableNavigator();
			var block = source.getBlock();
			var prevId = self.getPrevId(block.getId(),blockTableNavigator.getPgDataList());
			viewUI.ViewBlock(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var blockTableNavigator = self.getBlockTableNavigator();
			var block = source.getBlock();
			var nextId = self.getNextId(block.getId(),blockTableNavigator.getPgDataList());
			viewUI.ViewBlock(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.BLOCKGENERAL, constants.BLOCKGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}