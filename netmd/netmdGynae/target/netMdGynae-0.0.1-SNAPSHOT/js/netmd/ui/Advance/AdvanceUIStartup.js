function AdvanceUIStartup() {
	this.pgTableName = "#advance";
	this.pgTableContainer="#advanceListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#advancePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#advancePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#advancePTBContainer #btn_delete_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.advanceIdCol';
	this.exp = new ExpressionListDTO();
	this.advanceService = new AdvanceServiceImpl();
	this.listUrl = constants.ADVANCELISTURL;
	this.advanceTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.advanceService,this.exp);
	this.ftbToolBar = '#advance-filter-toolbar';
	this.filter = '#filter';
	this.viewAdvanceUI = new ViewAdvanceUI(this);
}

AdvanceUIStartup.prototype.setAdvanceTableNavigator = function(advanceTableNavigator) {
	this.advanceTableNavigator = advanceTableNavigator;
}
AdvanceUIStartup.prototype.getAdvanceService = function() {
	return this.advanceService;
}
AdvanceUIStartup.prototype.getAdvanceTableNavigator = function() {
	return this.advanceTableNavigator;
}
AdvanceUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
AdvanceUIStartup.prototype.getViewAdvanceUI = function() {
	return this.viewAdvanceUI;
} 

AdvanceUIStartup.prototype.init = function() {
	var self = this;
	var advanceTableNavigator = self.getAdvanceTableNavigator();
	self.setPageTitle(constants.ADVANCETITLE);
	var expList = new ExpressionListDTO();
	//var exp = new ExpressionDTO("status","active","eq");
	//expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.ADVANCE,constants.ADVANCEPAGETOOLBAR); //Create the Page tool Bar for advance
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ADVANCETABLELIST);//Create Table for Listing advance
	//advanceTableNavigator.setExp(expList);
	advanceTableNavigator.list();
	self.bindEvents();
	
}

 AdvanceUIStartup.prototype.bindToolBarEvents = function() {
		var self=this;
		self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEADVANCEUI,constants.ADVANCEMODEL);		
		openModalBox(obj,constants.ADVANCEMODEL);
		var newAdvanceUI = new NewAdvanceUI(self);
		newAdvanceUI.init();
		newAdvanceUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var advanceId=self.getSelectedAdvanceId(self.pgTableName);
		$j('#' + constants.ADVANCE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(advanceId!="") {
			var viewAdvanceUI = self.getViewAdvanceUI();
			viewAdvanceUI.init(advanceId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var advanceId=self.getSelectedAdvanceId(self.pgTableName);
		if(advanceId!="") {
			var advanceService = self.getAdvanceService();
			var confirmStatus = confirm(constants.ADVANCEDELETECONFIRM + advanceId);
			if(confirmStatus==true) {				
				var response = advanceService.deleteAdvance(advanceId);
				if(response.success==true) {
					showTip(constants.ADVANCEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var advanceTableNavigator = self.getAdvanceTableNavigator();
				advanceTableNavigator.list();
				
			}
		}	
	});
	}

AdvanceUIStartup.prototype.getSelectedAdvanceId = function () {
	var advanceId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selAdvance = $j(this.pgTableName + ' tbody tr[selected]');
		if(selAdvance.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTADVANCE);
		} else if(selAdvance.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTADVANCEONLY);
		else
			advanceId=selAdvance.attr('id');
	}
	return advanceId;
}
AdvanceUIStartup.prototype.bindEvents = function() {
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
	   var advanceId= $j(this).parent().attr('id');
	   $j('#' + constants.ADVANCE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(advanceId!="") {
			$j('#advance-filter-wb').hide();
			var viewAdvanceUI = self.getViewAdvanceUI();
			viewAdvanceUI.init(advanceId);
		}	
	});
} 