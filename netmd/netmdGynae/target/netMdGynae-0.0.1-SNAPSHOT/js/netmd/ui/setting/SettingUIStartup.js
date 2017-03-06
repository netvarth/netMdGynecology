function SettingUIStartup() {
	this.pgTableName = "#setting";
	this.pgTableContainer="#settingListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#settingPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#settingPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#settingPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#settingPTBContainer #btn_home_ptb_id');
	this.ptbSetup=$j('#settingPTBContainer #btn_setup_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.settingIdCol';
	this.exp = new ExpressionListDTO();
	this.settingService = new SettingServiceImpl();
	this.listUrl = constants.SETTINGLISTURL;
	this.settingTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.settingService,this.exp);
	this.ftbToolBar = '#setting-filter-toolbar';
	this.filter = '#filter';
	this.filterBench=$j('#filterWorkBench');
	this.ftbContainer=$j('#filterToolBar-Container');
	this.ptbContainer=$j('#pageToolBar-Container');
	this.viewSettingUI = new ViewSettingUI(this);
	this.filterActionButton = '#btnGo';
}
SettingUIStartup.prototype.getViewSettingUI = function() {
	return this.viewSettingUI;
}
SettingUIStartup.prototype.getSettingService = function() {
	return this.settingService;
}
SettingUIStartup.prototype.getSettingTableNavigator = function() {
	return this.settingTableNavigator;
}
SettingUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle=value;
}
SettingUIStartup.prototype.reset = function() {
	var self=this;
	$j(self.filter).hide();
	self.filterBench.hide();
	self.ftbContainer.empty().html("");
	self.ptbContainer.empty().html("");
}
SettingUIStartup.prototype.init = function() {
	var self = this;
	var settingTableNavigator = self.getSettingTableNavigator();
	self.setPageTitle(constants.SETTINGTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.SETTING,constants.SETTINGPAGETOOLBAR); //Create the Page tool Bar for Specimen
	//var ftbProcessor = new FilterToolBarProcessor();
	//ftbProcessor.create(constants.SETTING,constants.SETTINGFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.SETTINGLISTTABLEURL);//Create Table for Listing Specimen
	dataTableProcessor.setCustomTable(self.pgTableName);
	settingTableNavigator.list();
	self.bindEvents();
	//pageHandler.setActivePage(self); 
	
}
SettingUIStartup.prototype.createPackageModal = function(obj) {
 var self=this;
	commonMethodInvoker.removeErrors();
	createModal(constants.CREATESETTINGUI,constants.SETTINGMODAL);	
	openModalBox(obj,constants.SETTINGMODAL);
	var newSettingUI = new NewSettingUI(self);
	newSettingUI.bindEvents();
	return newSettingUI;
}
SettingUIStartup.prototype.createSetupModal = function(obj) {
 	var self=this
	commonMethodInvoker.removeErrors();
	var newSetupUI = new NewResultSettingUI(self);
	createGeneralModal(newSetupUI.getResultSetupPage(),"setUpModal","Page Setup","80%","90%");	
	openModalBox(obj,'setUpModal');
	newSetupUI.bindEvents();
	newSetupUI.initValues();
	return newSetupUI;
}

//Return the selected Specimen Id from the list table
SettingUIStartup.prototype.getSelectedSettingId = function () {
	var settingId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selSettings = $j(this.pgTableName + ' tbody tr[selected]');
		if(selSettings.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONESETTING);
		} else if(selSettings.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONESETTINGONLY);
		else
			settingId=selSettings.attr('id');
	}
	return settingId;
}
SettingUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	var parent = self;
	parent.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		parent.createPackageModal(obj);
	});
	parent.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	});
	parent.ptbView.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var settingId=parent.getSelectedSettingId(parent.pgTableName);
		$j('#' + constants.SETTING + '-filter-cont').hide();
		$j(parent.filter).hide();
		if(settingId!="") {
			var viewSettingUI = parent.getViewSettingUI();
			viewSettingUI.init(settingId);
		}	
	});
	 parent.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var settingId=parent.getSelectedSettingId(parent.pgTableName);
		if(settingId!="") {
			var settingService = parent.getSettingService();
			var settingResponse = settingService.viewSetting(settingId);
            var settingDetail=settingResponse.setting;
			var confirmStatus = confirm(constants.SETTINGDELETECONFIRM + settingDetail.name+"?");
			if(confirmStatus==true) {				
				var settingResponse = settingService.deleteSetting(settingId);
				if(!settingResponse.errorMessage) {
					showTip(constants.SETTINGDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(parent.errorHeader,parent.errorData,settingResponse.errorMessage);
				}
				var settingTableNavigator = parent.getSettingTableNavigator();
				settingTableNavigator.list();		
			}
		}	
	});
	/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		methodInvoker.setReportFilterValues(curObjName);
	})
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#lst'+curObjName).hide();
		$j('#txt'+curObjName).hide();
		$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	})
	
	$j(self.ftbToolBar + ' ' + self.filterActionButton).die('click').click(function(){
		removeErrors();
		var expList=new ExpressionListDTO();
		$j(self.ftbToolBar + " a[selected]").each(function(){
			var selName=$j(this).attr('name');
			var selTextValue=$j("#txt"+ selName).val();
			var selOperator = $j("#lst"+ selName).val();
			ExpressionListDTO
			var expr = new ExpressionDTO(selName,selTextValue,selOperator);
			expList.add(expr);
		});
		var settingTableNavigator = self.getSettingTableNavigator();
		settingTableNavigator.setExp(expList);
		settingTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/		
}
SettingUIStartup.prototype.bindEvents = function() {
	self = this;
	var parent = self;
	$j(parent.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(parent.pgTableRowClass).die('click').live('click',function(){
	   var settingId= $j(this).parent().attr('id');
	   $j('#' + constants.SETTING + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(settingId!="") {
			$j('#setting-filter-wb').hide();
			var viewSettingUI = parent.getViewSettingUI();
			viewSettingUI.init(settingId);
		}	
	});
}