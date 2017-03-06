function ViewBedTypePTB(viewBedTypeUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewBedTypeUI = viewBedTypeUI;
	
	this.getViewBedTypeUI = function() {
		return this.viewBedTypeUI;
	}
		
	this.getBedTypeUIStartup = function() {
		var viewBedTypeUI = this.getViewBedTypeUI();
		return viewBedTypeUI.getBedTypeUIStartup();
	}
	
	this.getBlockTableNavigator = function() {
		var bedTypeUIStartup = this.getBedTypeUIStartup();
		return bedTypeUIStartup.getBedTypeTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewBedTypeUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedTypeUIStartup = self.getBedTypeUIStartup();
			bedTypeUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedTypeTableNavigator = self.getBedTypeTableNavigator();
			var bedType = source.getBedType();
			var prevId = self.getPrevId(bedType.getId(),bedTypeTableNavigator.getPgDataList());
			viewUI.viewBedType(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var bedTypeTableNavigator = self.getBedTypeTableNavigator();
			var bedType = source.getBedType();
			var prevId = self.getNextId(bedType.getId(),bedTypeTableNavigator.getPgDataList());
			viewUI.viewBedType(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.BEDTYPEGENERAL, constants.BEDTYPEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}