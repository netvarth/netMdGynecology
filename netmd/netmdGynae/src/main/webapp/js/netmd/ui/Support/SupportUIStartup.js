function SupportUIStartup() {
	this.pgTableName = "#support";
	this.pgTableContainer="#supportListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#supportPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#supportPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#supportPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#supportPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.supportIdCol';
	this.exp = new ExpressionListDTO();
	this.supportService = new SupportServiceImpl();
	this.listUrl = constants.SUPPORTLISTURL;
	this.supportInfo;
	this.supportTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.supportService,this.exp);
	this.ftbToolBar = '#support-filter-toolbar';
	this.filter = '#filter';
	this.filterActionButton = '#btnGo';
	this.viewSupportUI = new ViewSupportUI(this);
}

SupportUIStartup.prototype.setSupportTableNavigator = function(supportTableNavigator) {
	this.supportTableNavigator = supportTableNavigator;
}
SupportUIStartup.prototype.getSupportService = function() {
	return this.supportService;
}
SupportUIStartup.prototype.getSupportTableNavigator = function() {
	return this.supportTableNavigator;
}
SupportUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
SupportUIStartup.prototype.getViewSupportUI = function() {
	return this.viewSupportUI;
} 

SupportUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var supportTableNavigator = self.getSupportTableNavigator();
	self.setPageTitle(constants.SUPPORTTITLE);
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.SUPPORT,constants.SUPPORTPAGETOOLBAR); 
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.SUPPORT,constants.SUPPORTFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.SUPPORTTABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	supportTableNavigator.setExp(expList);
	supportTableNavigator.list();
	self.bindEvents();
	var supportService = self.getSupportService();
	self.supportInfo= supportService.getServiceList();
	
}

 SupportUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATESUPPORTUI,constants.SUPPORTMODEL);		
		openModalBox(obj,constants.SUPPORTMODEL);
		var newSupportUI = new NewSupportUI(self);
		newSupportUI.init();
		newSupportUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var supportId=self.getSelectedSupportId(self.pgTableName);
		$j('#' + constants.support + '-filter-cont').hide();
		$j(self.filter).hide();
		if(supportId!="") {
			var viewSupportUI = self.getViewSupportUI();
			viewSupportUI.init(supportId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var supportId=self.getSelectedSupportId(self.pgTableName);
		if(supportId!="") {
			var supportService = self.getSupportService();
			var supportResponse = supportService.deleteSupport(supportId);
			var confirmStatus = confirm(constants.SUPPORTDELETECONFIRM + supportResponse.support.name);
			if(confirmStatus==true) {				
				var response = supportService.deleteSupport(supportId);
				if(response.success==true) {
					showTip(constants.SUPPORTDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var supportTableNavigator = self.getSupportTableNavigator();
				supportTableNavigator.list();
				
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	
	/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		self.setReportFilterValues(curObjName,self.supportInfo);
	});
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#lst'+curObjName).hide();
		$j('#txt'+curObjName).hide();
		$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	
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
		var supportTableNavigator = self.getSupportTableNavigator();
		supportTableNavigator.setExp(expList);
		supportTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
	}
SupportUIStartup.prototype.setReportFilterValues=function(billValue,supportInfo) {
		if(billValue=='name'){
			autoCompleteArray=[];
			 $j(supportInfo.supportList).each(function(index,support){  
				autoCompleteArray.push(''+support.name+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='status'){
			autoCompleteArray=[];
			autoCompleteArray.push("Active");	
			autoCompleteArray.push("Inactive");	
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='price'){
			autoCompleteArray=[];
			 $j(supportInfo.supportList).each(function(index,support){  
				autoCompleteArray.push(''+support.price+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
}
SupportUIStartup.prototype.getSelectedSupportId = function () {
	var supportId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selSupport = $j(this.pgTableName + ' tbody tr[selected]');
		if(selSupport.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAx);
		} else if(selSupport.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAxONLY);
		else
			supportId=selSupport.attr('id');
	}
	return supportId;
}
SupportUIStartup.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(self.pgTableRowClass).die('click').live('click',function(){
	   var supportId= $j(this).parent().attr('id');
	   $j('#' + constants.SUPPORT + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(supportId!="") {
			$j('#support-filter-wb').hide();
			var viewSupportUI = self.getViewSupportUI();
			viewSupportUI.init(supportId);
		}	
	});
} 