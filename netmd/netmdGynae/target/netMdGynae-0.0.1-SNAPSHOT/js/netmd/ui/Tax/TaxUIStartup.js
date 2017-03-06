function TaxUIStartup() {
	this.pgTableName = "#tax";
	this.pgTableContainer="#taxListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#taxPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#taxPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#taxPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#taxPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.taxIdCol';
	this.exp = new ExpressionListDTO();
	this.taxInfo;
	this.taxService = new TaxServiceImpl();
	this.listUrl = constants.TAXLISTURL;
	this.taxTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.taxService,this.exp);
	this.ftbToolBar = '#tax-filter-toolbar';
	this.filter = '#filter';
	this.filterActionButton = '#btnGo';
	this.viewTaxUI = new ViewTaxUI(this);
}

TaxUIStartup.prototype.setTaxTableNavigator = function(taxTableNavigator) {
	this.taxTableNavigator = taxTableNavigator;
}
TaxUIStartup.prototype.getTaxService = function() {
	return this.taxService;
}
TaxUIStartup.prototype.getTaxTableNavigator = function() {
	return this.taxTableNavigator;
}
TaxUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
TaxUIStartup.prototype.getViewTaxUI = function() {
	return this.viewTaxUI;
} 

TaxUIStartup.prototype.init = function() {
	var self = this;
	var taxTableNavigator = self.getTaxTableNavigator();
	self.setPageTitle(constants.TAXTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.TAX,constants.TAXPAGETOOLBAR); //Create the Page tool Bar for Area
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.TAX,constants.TAXFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.TAXTABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	taxTableNavigator.list();
	self.bindEvents();
	var taxService = self.getTaxService();
	self.taxInfo= taxService.getTaxTypes();
}

 TaxUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATETAXUI,constants.TAXMODEL);		
		openModalBox(obj,constants.TAXMODEL);
		var newTaxUI = new NewTaxUI(self);
		newTaxUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var taxId=self.getSelectedTaxId(self.pgTableName);
		$j('#' + constants.tax + '-filter-cont').hide();
		$j(self.filter).hide();
		if(taxId!="") {
			var viewTaxUI = self.getViewTaxUI();
			viewTaxUI.init(taxId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var taxId=self.getSelectedTaxId(self.pgTableName);
		if(taxId!="") {
			var taxService = self.getTaxService();
			var taxResponse = taxService.viewTax(taxId);
			var confirmStatus = confirm(constants.TAXDELETECONFIRM + taxResponse.tax.name);
			if(confirmStatus==true) {				
				var response = taxService.deleteTax(taxId);
				if(response.success==true) {
					showTip(constants.TAXDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var taxTableNavigator = self.getTaxTableNavigator();
				taxTableNavigator.list();
				
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
		self.setReportFilterValues(curObjName,self.taxInfo);
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
		var taxTableNavigator = self.getTaxTableNavigator();
		taxTableNavigator.setExp(expList);
		taxTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
	}
TaxUIStartup.prototype.setReportFilterValues=function(billValue,taxInfo) {
		if(billValue=='name'){
			autoCompleteArray=[];
			 $j(taxInfo.taxlist).each(function(index,tax){  
				autoCompleteArray.push(''+tax.name+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		/* if(billValue=='status'){
			autoCompleteArray=[];
			autoCompleteArray.push("Active");	
			autoCompleteArray.push("Inactive");	
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		}   */
		if(billValue=='taxVal'){
			autoCompleteArray=[];
			 $j(taxInfo.taxlist).each(function(index,tax){  
				autoCompleteArray.push(''+tax.taxVal+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
}
TaxUIStartup.prototype.getSelectedTaxId = function () {
	var taxId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selTax = $j(this.pgTableName + ' tbody tr[selected]');
		if(selTax.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAx);
		} else if(selTax.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAxONLY);
		else
			taxId=selTax.attr('id');
	}
	return taxId;
}
TaxUIStartup.prototype.bindEvents = function() {
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
	   var taxId= $j(this).parent().attr('id');
	   $j('#' + constants.TAX + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(taxId!="") {
			$j('#tax-filter-wb').hide();
			var viewTaxUI = self.getViewTaxUI();
			viewTaxUI.init(taxId);
		}	
	});
} 