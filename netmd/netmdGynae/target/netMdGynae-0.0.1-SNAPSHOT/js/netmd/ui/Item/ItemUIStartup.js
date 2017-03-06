function ItemUIStartup() {
	this.pgTableName = "#item";
	this.pgTableContainer="#itemListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#itemPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#itemPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#itemPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#itemPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.itemIdCol';
	this.exp = new ExpressionListDTO();
	this.itemInfo;
	this.itemService = new ItemServiceImpl();
	this.listUrl = constants.ITEMLISTURL;
	this.itemTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.itemService,this.exp);
	this.ftbToolBar = '#item-filter-toolbar';
	this.filter = '#filter';
	this.filterActionButton = '#btnGo';
	this.viewItemUI = new ViewItemUI(this);
	this.billService = new BillServiceImpl();
}

ItemUIStartup.prototype.setItemTableNavigator = function(itemTableNavigator) {
	this.itemTableNavigator = itemTableNavigator;
}
ItemUIStartup.prototype.getItemService = function() {
	return this.itemService;
}
ItemUIStartup.prototype.getItemTableNavigator = function() {
	return this.itemTableNavigator;
}
ItemUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
ItemUIStartup.prototype.getViewItemUI = function() {
	return this.viewItemUI;
} 

ItemUIStartup.prototype.init = function() {
	var self = this;
	var itemTableNavigator = self.getItemTableNavigator();
	self.setPageTitle(constants.ITEMTITLE);
	var expList = new ExpressionListDTO();
	var exp= new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.ITEM,constants.ITEMPAGETOOLBAR); //Create the Page tool Bar for item
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.ITEM,constants.ITEMFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ITEMTABLELIST);//Create Table for Listing item
	dataTableProcessor.setCustomTable(self.pgTableName);
	itemTableNavigator.setExp(expList);
	itemTableNavigator.list();
	self.bindEvents();
	var itemService = self.getItemService();
	self.itemInfo= itemService.getItemList();
}

 ItemUIStartup.prototype.bindToolBarEvents = function() {
		var self=this;
		self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEITEMUI,constants.ITEMMODEL);		
		openModalBox(obj,constants.ITEMMODEL);
		var newItemUI = new NewItemUI(self);
		newItemUI.init();
		newItemUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var itemId=self.getSelectedItemId(self.pgTableName);
		$j('#' + constants.ITEM + '-filter-cont').hide();
		$j(self.filter).hide();
		if(itemId!="") {
			var viewItemUI = self.getViewItemUI();
			viewItemUI.init(itemId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var itemId=self.getSelectedItemId(self.pgTableName);
		if(itemId!="") {
			var itemService = self.getItemService();
			var itemResponse = itemService.viewItem(itemId);
			var confirmStatus = confirm(constants.ITEMDELETECONFIRM + itemResponse.name);
			if(confirmStatus==true) {				
				var response = itemService.deleteItem(itemId);
				if(response.success==true) {
					showTip(constants.ITEMDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var itemTableNavigator = self.getItemTableNavigator();
				itemTableNavigator.list();
				
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
		self.setReportFilterValues(curObjName,self.itemInfo);
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
		var itemTableNavigator = self.getItemTableNavigator();
		itemTableNavigator.setExp(expList);
		itemTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
}
ItemUIStartup.prototype.setReportFilterValues=function(billValue,itemInfo) {
		if(billValue=='name'){
			autoCompleteArray=[];
			 $j(itemInfo.itemList).each(function(index,item){  
				autoCompleteArray.push(''+item.name+'');	
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
			 $j(itemInfo.itemList).each(function(index,item){  
				autoCompleteArray.push(''+item.price+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
}
ItemUIStartup.prototype.getSelectedItemId = function () {
	var itemId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selItem = $j(this.pgTableName + ' tbody tr[selected]');
		if(selItem.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTITEM);
		} else if(selItem.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTITEMONLY);
		else
			itemId=selItem.attr('id');
	}
	return itemId;
}
ItemUIStartup.prototype.bindEvents = function() {
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
	   var itemId= $j(this).parent().attr('id');
	   $j('#' + constants.ITEM + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(itemId!="") {
			$j('#item-filter-wb').hide();
			var viewItemUI = self.getViewItemUI();
			viewItemUI.init(itemId);
		}	
	});
} 