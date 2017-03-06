function DiscountUIStartup() {
	this.pgTableName = "#discount";
	this.pgTableContainer="#discountListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#discountPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#discountPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#discountPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#discountPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.discountIdCol';
	this.exp = new ExpressionListDTO();
	this.discountService = new DiscountServiceImpl();
	this.listUrl = constants.DISCOUNTLISTURL;
	this.discountTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.discountService,this.exp);
	this.ftbToolBar = '#discount-filter-toolbar';
	this.filter = '#filter';
	this.filterActionButton = '#btnGo';
	this.discountInfo;
	this.viewDiscountUI = new ViewDiscountUI(this);
}


DiscountUIStartup.prototype.setDiscountTableNavigator = function(discountTableNavigator) {
	this.discountTableNavigator = discountTableNavigator;
}
DiscountUIStartup.prototype.getDiscountService = function() {
	return this.discountService;
}
DiscountUIStartup.prototype.getDiscountTableNavigator = function() {
	return this.discountTableNavigator;
}
DiscountUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
DiscountUIStartup.prototype.getViewDiscountUI = function() {
	return this.viewDiscountUI;
} 

DiscountUIStartup.prototype.init = function() {
	var self = this;
	var discountTableNavigator = self.getDiscountTableNavigator();
	self.setPageTitle(constants.DISCOUNTTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.DISCOUNT,constants.DISCOUNTPAGETOOLBAR); //Create the Page tool Bar for Area
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.DISCOUNT,constants.DISCOUNTFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.DISCOUNTTABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	discountTableNavigator.list();
	self.bindEvents();
	var discountService = self.getDiscountService();
	self.discountInfo= discountService.getDiscounts();
	//alert(JSON.stringify(self.discountInfo));
}

 DiscountUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEDISCOUNTUI,constants.DISCOUNTMODEL);		
		openModalBox(obj,constants.DISCOUNTMODEL);
		var newDiscountUI = new NewDiscountUI(self);
		newDiscountUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var discountId=self.getSelectedDiscountId(self.pgTableName);
		$j('#' + constants.discount + '-filter-cont').hide();
		$j(self.filter).hide();
		if(discountId!="") {
			var viewDiscountUI = self.getViewDiscountUI();
			viewDiscountUI.init(discountId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var discountId=self.getSelectedDiscountId(self.pgTableName);
		if(discountId!="") {
			var discountService = self.getDiscountService();
			var discountResponse = discountService.viewDiscount(discountId);
			var confirmStatus = confirm(constants.DISCOUNTDELETECONFIRM + discountResponse.discount.name);
			if(confirmStatus==true) {				
				var response = discountService.deleteDiscount(discountId);
				if(response.success==true) {
					showTip(constants.DISCOUNTDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var discountTableNavigator = self.getDiscountTableNavigator();
				discountTableNavigator.list();
				
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
		self.setReportFilterValues(curObjName,self.discountInfo);
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
		var discountTableNavigator = self.getDiscountTableNavigator();
		discountTableNavigator.setExp(expList);
		discountTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	}
DiscountUIStartup.prototype.setReportFilterValues=function(billValue,discountInfo) {
		//alert(JSON.stringify(discountInfo));
		if(billValue=='name'){
			autoCompleteArray=[];
			 $j(discountInfo.discount).each(function(index,discount){  
				autoCompleteArray.push(''+discount.name+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		 if(billValue=='status'){
			autoCompleteArray=[];
			autoCompleteArray.push("Active");	
			autoCompleteArray.push("Inactive");	
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		}  
		if(billValue=='calculationType'){
			autoCompleteArray=[];
			autoCompleteArray.push("Fixed");	
			autoCompleteArray.push("Percentage");	
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='discountType'){
			autoCompleteArray=[];
			autoCompleteArray.push("Predefined");	
			autoCompleteArray.push("OnDemand");
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
}
DiscountUIStartup.prototype.getSelectedDiscountId = function () {
	var discountId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selDiscount = $j(this.pgTableName + ' tbody tr[selected]');
		if(selDiscount.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAx);
		} else if(selDiscount.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONETAxONLY);
		else
			discountId=selDiscount.attr('id');
	}
	return discountId;
}
DiscountUIStartup.prototype.bindEvents = function() {
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
	   var discountId= $j(this).parent().attr('id');
	   $j('#' + constants.DISCOUNT + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(discountId!="") {
			$j('#discount-filter-wb').hide();
			var viewDiscountUI = self.getViewDiscountUI();
			viewDiscountUI.init(discountId);
		}	
	});
} 