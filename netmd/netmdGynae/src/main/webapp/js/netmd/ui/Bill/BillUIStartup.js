function BillUIStartup() {
	this.pgTableName = "#bill";
	this.pgTableContainer="#billListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#billPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#billPTBContainer #btn_view_ptb_id');
	this.ptbBillPrint=$j('#billPTBContainer #btn_print_ptb_id');
	this.ptbBillStatus=$j('#billPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.billIdCol';
	this.exp = new ExpressionListDTO();
	this.billService = new BillServiceImpl();
	this.listUrl = constants.BILLLISTURL;
	this.billTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.billService,this.exp);
	this.ftbToolBar = '#bill-filter-toolbar';
	this.filter = '#filter';
	this.billResInfo;
	this.patientInfo;
	this.filterActionButton = '#btnGo';
	this.viewBillUI = new ViewBillUI(this);
}

BillUIStartup.prototype.setBillTableNavigator = function(billTableNavigator) {
	this.billTableNavigator = billTableNavigator;
}
BillUIStartup.prototype.getBillService = function() {
	return this.billService;
}
BillUIStartup.prototype.getBillTableNavigator = function() {
	return this.billTableNavigator;
}
BillUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
BillUIStartup.prototype.getViewBillUI = function() {
	return this.viewBillUI;
} 
BillUIStartup.prototype.getBillIds = function() {
	var self=this;
	var billIds = [];
	$j(self.pgTableName + ' tbody tr[selected]').each(function() {
		billIds.push($j(this).attr('id'));
	});
	return billIds;
}
BillUIStartup.prototype.getSelectedBillId = function () {
	var billId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selBill= $j(this.pgTableName + ' tbody tr[selected]');
		if(selBill.length==0){
			commonMethodInvoker.createServerError(self.errorExpenseer,self.errorData, constants.SELECTEXPENSE);
		} else if(selBill.length>1) 
			commonMethodInvoker.createServerError(self.errorExpenseer,self.errorData, constants.SELECTEXPENSEONLY);
		else
			billId=selBill.attr('id');
	}
	return billId;
}
BillUIStartup.prototype.createBillModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.CREATEBILLUI,constants.BILLMODEL);		
	openModalBox(obj,constants.BILLMODEL);
	var newBillUI = new NewBillUI(self);
	newBillUI.init();
	newBillUI.bindEvents();
}

BillUIStartup.prototype.createStatusModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.CREATEBILLSTATUSUI,constants.BILLSTATUSMODAL);		
	openModalBox(obj,constants.BILLSTATUSMODAL);
	var newBillStatusUI = new ChangeBillStatusUI(self);
	newBillStatusUI.init();
	newBillStatusUI.bindEvents();
}
BillUIStartup.prototype.init = function() {
	var self = this;
	var billTableNavigator = self.getBillTableNavigator();
	self.setPageTitle(constants.BILLTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.BILL,constants.BILLPAGETOOLBAR); //Create the Page tool Bar for billtype
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.BILL,constants.BILLFILTERKEY,constants.ENUMLISTURL);
	//self.hideFilters();
	//$j(self.filter).hide();
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.BILLTABLELIST);//Create Table for Listing billtype
	dataTableProcessor.setCustomTable(self.pgTableName);
	billTableNavigator.list();
	self.bindEvents();
	var billService = self.getBillService();
	self.billResInfo= billService.getBills();
	self.patientInfo= billService.getPatientDetails();
}

 BillUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEBILLUI,constants.BILLMODEL);		
		openModalBox(obj,constants.BILLMODEL);
		var newBillUI = new NewBillUI(self);
		newBillUI.init();
		newBillUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var billId=self.getSelectedBillId(self.pgTableName);
		$j('#' + constants.BILL + '-filter-cont').hide();
		$j(self.filter).hide();
		if(billId!="") {
			var viewBillUI = self.getViewBillUI();
			viewBillUI.init(billId);
		}	
	});
	
	self.ptbBillPrint.die('click').live('click',function() {
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		var billId=self.getSelectedBillId(self.pgTableName);
		var billService = self.getBillService();
		if(billId!="") {
			var billInfo = billService.viewBill(billId);
			var billLogo=billService.getlogo();
				if(billInfo.billStatus=="open"||billInfo.billStatus=="closed"){
				var billProcessor = new BillPrintProcessor(self);
				var result = billProcessor.createPrintArea(billInfo,billLogo);
				//var resuldiv = billProcessor.generatePrintableSection(result);
				billProcessor.print(billProcessor.generateHtml(result));
			}
			else
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.CANCELLEDBILL);
		}	
	});
	 self.ptbBillStatus.die('click').live('click',function() {
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		var billIds=self.getBillIds(self.pgTableName);
		if(billIds.length==0)
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDER);
		self.createStatusModal(obj);
	}); 
	
/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		self.setReportFilterValues(curObjName,self.patientInfo,self.billResInfo);
	});
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j('#txt'+curObjName).datepicker('hide');
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
		var billTableNavigator = self.getBillTableNavigator();
		billTableNavigator.setExp(expList);
		billTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
}
BillUIStartup.prototype.setReportFilterValues=function(billValue,patientInfo,billResInfo) {
		var self=this;
		if(billValue=='patientName'){
			autoCompleteArray=[];
			 $j(patientInfo.patient).each(function(index,patient){  
				autoCompleteArray.push(''+patient.firstName+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='referralName'){
			autoCompleteArray=[];
			 $j(billResInfo.billList).each(function(index,bill){  
				
				autoCompleteArray.push(''+bill.referralName+'');	
			}); 
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='billStatus'){
			autoCompleteArray=[];
			autoCompleteArray.push("open");	
			autoCompleteArray.push("closed");	
			autoCompleteArray.push("canceled");	
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='paymentStatus'){
			autoCompleteArray=[];
			autoCompleteArray.push("FullyPaid");
			autoCompleteArray.push("PartiallyPaid");
			autoCompleteArray.push("NotPaid");
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 
		if(billValue=='orderDate'){
			$j("#txt"+billValue).datepicker().datepicker("show");
		}
		if(billValue=='origin'){
			autoCompleteArray=[];
			autoCompleteArray.push("InPatient");
			autoCompleteArray.push("OutPatient");
			commonMethodInvoker.makeautoComplete("#txt"+billValue, autoCompleteArray);
		} 

}
BillUIStartup.prototype.hideFilters = function() {
	var self=this;
	/* $j(self.ftbToolBar + " a[name='uid']").hide();
	$j(self.ftbToolBar + " a[name='patientName']").hide();
	$j(self.ftbToolBar + " a[name='referralName']").hide();
	$j(self.ftbToolBar + " a[name='paymentStatus']").hide();
	$j(self.ftbToolBar + " a[name='billStatus']").hide();
	$j(self.ftbToolBar + " a[name='origin']").hide();
	$j(self.ftbToolBar + " a[name='orderDate']").hide(); */
}

BillUIStartup.prototype.getSelectedBillId = function () {
	var billId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selBill = $j(this.pgTableName + ' tbody tr[selected]');
		if(selBill.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBILL);
		} else if(selBill.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBILLONLY);
		else
			billId=selBill.attr('id');
	}
	return billId;
}
 BillUIStartup.prototype.bindEvents = function() {
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
	   var billId= $j(this).parent().attr('id');
	   $j('#' + constants.BILL + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(billId!="") {
			$j('#billtype-filter-wb').hide();
			var viewBillUI = self.getViewBillUI();
			viewBillUI.init(billId);
		}	
	});
} 