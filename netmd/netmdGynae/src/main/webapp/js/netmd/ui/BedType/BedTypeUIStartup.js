function BedTypeUIStartup() {
	this.pgTableName = "#bedType";
	this.pgTableContainer="#bedTypeListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#bedTypePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#bedTypePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#bedTypePTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#bedTypePTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.bedTypeIdCol';
	this.exp = new ExpressionListDTO();
	this.bedTypeService = new BedTypeServiceImpl();
	this.listUrl = constants.BEDTYPELISTURL;
	this.bedTypeTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.bedTypeService,this.exp);
	this.ftbToolBar = '#bedType-filter-toolbar';
	this.filter = '#filter';
	this.viewBedTypeUI = new ViewBedTypeUI(this);
}

BedTypeUIStartup.prototype.setBedTypeTableNavigator = function(bedTypeTableNavigator) {
	this.bedTypeTableNavigator = bedTypeTableNavigator;
}

BedTypeUIStartup.prototype.getBedTypeService = function() {
	return this.bedTypeService;
}
BedTypeUIStartup.prototype.getBedTypeTableNavigator = function() {
	return this.bedTypeTableNavigator;
}
BedTypeUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
BedTypeUIStartup.prototype.getViewBedTypeUI = function() {
	return this.viewBedTypeUI;
} 

BedTypeUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var bedTypeTableNavigator = self.getBedTypeTableNavigator();
	self.setPageTitle(constants.BEDTYPETITLE);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.BEDTYPE,constants.BEDTYPEPAGETOOLBAR); //Create the Page tool Bar for Area
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.BEDTYPELISTTABLEURL);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	bedTypeTableNavigator.setExp(expList);
	bedTypeTableNavigator.list();
	self.bindEvents();
	
}

 BedTypeUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEBEDTYPEUI,constants.BEDTYPEMODAL);		
		openModalBox(obj,constants.BEDTYPEMODAL);
		var newBedTypeUI = new NewBedTypeUI(self);
		newBedTypeUI.init();
		newBedTypeUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var bedTypeId=self.getSelectedBedTypeId(self.pgTableName);
		$j('#' + constants.BLOCK + '-filter-cont').hide();
		$j(self.filter).hide();
		if(bedTypeId!="") {
			var viewBedTypeUI = self.getViewBedTypeUI();
			viewBedTypeUI.init(bedTypeId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var bedTypeId=self.getSelectedBedTypeId(self.pgTableName);
		if(bedTypeId!="") {
			var bedTypeService = self.getBedTypeService();
			var bedTypeResponse = bedTypeService.viewBedType(bedTypeId);
			var confirmStatus = confirm(constants.BEDTYPEDELETECONFIRM + bedTypeResponse.type);
			if(confirmStatus==true) {				
				var bedTypeResponse = bedTypeService.deleteBedType(bedTypeId);
				if(bedTypeResponse.success==true) {
					showTip(constants.BEDTYPEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var bedTypeTableNavigator = self.getBedTypeTableNavigator();
				bedTypeTableNavigator.list();
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

BedTypeUIStartup.prototype.getSelectedBedTypeId = function () {
	var bedTypeId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selBedType = $j(this.pgTableName + ' tbody tr[selected]');
		if(selBedType.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBEDTYPE);
		} else if(selBedType.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBLOCKONLY);
		else
			bedTypeId=selBedType.attr('id');
	}
	return bedTypeId;
}
 BedTypeUIStartup.prototype.bindEvents = function() {
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
	   var bedTypeId= $j(this).parent().attr('id');
	   $j('#' + constants.BEDTYPE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(bedTypeId!="") {
			var viewBedTypeUI = self.getViewBedTypeUI();
			viewBedTypeUI.init(bedTypeId);
		}	
	});
} 