function ViewBedPTB(viewBedUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewBedUI = viewBedUI;
	
	this.getViewBedUI = function() {
		return this.viewBedUI;
	}
		
	this.getBedUIStartup = function() {
		var viewBedUI = this.getViewBedUI();
		return viewBedUI.getBedUIStartup();
	}
	
	this.getBedTableNavigator = function() {
		var bedUIStartup = this.getBedUIStartup();
		return bedUIStartup.getBedTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewBedUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedUIStartup = self.getBedUIStartup();
			bedUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedTableNavigator = self.getBedTableNavigator();
			var bed = source.getBed();
			var prevId = self.getPrevId(bed.getId(),bedTableNavigator.getPgDataList());
			viewUI.viewBed(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedTableNavigator = self.getBedTableNavigator();
			var bed = source.getBed();
			var nextId = self.getNextId(bed.getId(),bedTableNavigator.getPgDataList());
			viewUI.viewBed(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.BEDGENERAL, constants.BEDGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}