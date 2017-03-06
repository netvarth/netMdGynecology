function ViewSettingPTB(viewSettingUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewSettingUI = viewSettingUI;

	this.getViewSettingUI = function() {
		return this.viewSettingUI;
	}
		
	this.getSettingUIStartup = function() {
		var viewSettingUI = this.getViewSettingUI();
		return viewSettingUI.getSettingUIStartup();
	}
	
	this.getSettingTableNavigator = function() {
		var settingUIStartup = this.getSettingUIStartup();
		return settingUIStartup.getSettingTableNavigator();
	}
	
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewSettingUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var settingUIStartup = self.getSettingUIStartup();
			settingUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var settingTableNavigator = self.getSettingTableNavigator();
			var setting = source.getSettting();
			var prevId = self.getPrevId(setting.getId(),settingTableNavigator.getPgDataList());
			viewUI.viewSetting(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var settingTableNavigator = self.getSettingTableNavigator();
			var setting = source.getSetting();			
			var nextId = self.getNextId(setting.getId(),settingTableNavigator.getPgDataList());
			viewUI.viewSetting(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.SETTINGGENERAL, constants.SETTINGGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
	
}
