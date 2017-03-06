function BedUIStartup() {
	this.pgTableName = "#bed";
	this.pgTableContainer="#bedListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#bedPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#bedPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#bedPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#bedPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.bedIdCol';
	this.exp = new ExpressionListDTO();
	this.bedService = new BedServiceImpl();
	this.listUrl = constants.BEDLISTURL;
	this.bedTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.bedService,this.exp);
	this.ftbToolBar = '#bed-filter-toolbar';
	this.filter = '#filter';
	this.viewBedUI = new ViewBedUI(this);
}

BedUIStartup.prototype.setBedTableNavigator = function(bedTableNavigator) {
	this.bedTableNavigator = bedTableNavigator;
}
BedUIStartup.prototype.getBedService = function() {
	return this.bedService;
}
BedUIStartup.prototype.getBedTableNavigator = function() {
	return this.bedTableNavigator;
}
BedUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
BedUIStartup.prototype.getViewBedUI = function() {
	return this.viewBedUI;
} 

BedUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var bedTableNavigator = self.getBedTableNavigator();
	self.setPageTitle(constants.BEDTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.BED,constants.BEDPAGETOOLBAR); //Create the Page tool Bar for bedtype
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.BEDTABLELIST);//Create Table for Listing bedtype
	dataTableProcessor.setCustomTable(self.pgTableName);
	bedTableNavigator.setExp(expList);
	bedTableNavigator.list();
	self.bindEvents();
	
}
BedUIStartup.prototype.reset = function() {
	var self=this;
	$j(self.filter).hide();
	self.filterBench.hide();
	self.ftbContainer.empty().html("");
	self.ptbContainer.empty().html("");
}
 BedUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEBEDUI,constants.BEDMODEL);		
		openModalBox(obj,constants.BEDMODEL);
		var newBedUI = new NewBedUI(self);
		newBedUI.init();
		newBedUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var bedId=self.getSelectedBedId(self.pgTableName);
		$j('#' + constants.BED + '-filter-cont').hide();
		$j(self.filter).hide();
		if(bedId!="") {
			var viewBedUI = self.getViewBedUI();
			viewBedUI.init(bedId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var bedId=self.getSelectedBedId(self.pgTableName);
		if(bedId!="") {
			var bedService = self.getBedService();
			var bedResponse = bedService.viewBed(bedId);
			var confirmStatus = confirm(constants.BEDDELETECONFIRM + bedResponse.bedNumber);
			if(confirmStatus==true) {				
				var bedResponse = bedService.deleteBed(bedId);
				if(bedResponse.success==true) {
					showTip(constants.BEDDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(bedResponse.error));
				}
				var bedTableNavigator = self.getBedTableNavigator();
				bedTableNavigator.list();
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

BedUIStartup.prototype.getSelectedBedId = function () {
	var bedId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selBed = $j(this.pgTableName + ' tbody tr[selected]');
		if(selBed.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBED);
		} else if(selBed.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBEDONLY);
		else
			bedId=selBed.attr('id');
	}
	return bedId;
}
 BedUIStartup.prototype.bindEvents = function() {
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
	
	$j(this.pgTableRowClass).die('click').live('click',function(){
	   var bedId= $j(this).parent().attr('id');
	   $j('#' + constants.BED + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(bedId!="") {
			$j('#bed-filter-wb').hide();
			var viewBedUI = self.getViewBedUI();
			viewBedUI.init(bedId);
		}	
	});
} 