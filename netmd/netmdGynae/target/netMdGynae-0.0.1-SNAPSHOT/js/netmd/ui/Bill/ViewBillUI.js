function ViewBillUI(billUIStartup) {
	this.viewBillPage = "#viewBill";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewBill #id label";
	this.patientName="#viewBill #name";
	this.patientId="";
	this.billUid="";
	this.age="#viewBill #age label";
	this.viewGender="#viewBill #viewGender";
	this.gender="#viewBill #gender label";
	this.editGender="#viewBill #editGender";
	this.phone="viewBill #phone label";
	this.mobile="#viewBill #mobile label";
	this.email="#viewBill #email label";
	this.patientType="#viewBill #patientType label";
	this.referral="#viewBill #referral";
	this.date="#viewBill #date label";
	this.patientNote="#viewBill #patientNote";
	this.patientHeaderNote="#viewBill #patientHeaderNote";
	this.patientUpdateButton = '#viewBill #btnPatientSave';
	this.patientEditButton = '#viewBill #btnPatientEdit';
	this.patientCancelButton = '#viewBill #btnPatientCancel'; 
	this.patientHeader="#viewBill #patientHeader";
	this.amountPaid="#viewBill #amountPaid label";
	this.payStatus="#viewBill #payStatus label";
	this.balance="#viewBill #balance";
	this.balanceSection ="#viewBill #paymentSection #viewBalance";
	this.viewPaymentNote="#viewBill #viewPaymentNote";
	this.paymentNote="#viewBill #paymentNote";
	this.paymentSection="#viewBill #paymentSection";
	this.paymentUpdateButton="#viewBill #btnPaymentSave";
	this.paymentEditButton = '#viewBill #btnPaymentEdit';
	this.paymentCancelButton = '#viewBill #btnPaymentCancel'; 
	this.selectDiscount="#viewBill #selectDiscount";
	this.discountValue="#viewBill #discountValue";
	this.btnAddDiscount="#viewBill #btnDiscountAdd";
	this.discountTable="#viewBill #discountTable";
	this.quantity="#viewBill #quantity";
	this.discountUpdateButton="#viewBill #btnDiscountSave";
	this.discountEditButton = '#viewBill #btnDiscountEdit';
	this.discountCancelButton = '#viewBill #btnDiscountCancel'; 
	this.discountworkBench="#viewBill #discountworkBench";
	this.discountTitleSection="#viewBill #discountTitleSection";
	this.selectDiscount="#viewBill #selectDiscount";
	this.discountValue="#viewBill #discountValue";
	this.discountType="#viewBill #discountType";
	this.discharge="#viewBill #discharge";
	this.btnDischarge="#viewBill #btnDischarge";
	this.itemServiceUpdateButton="#viewBill #btnItemServiceSave";
	this.itemServiceEditButton = '#viewBill #btnItemServiceEdit';
	this.itemServiceCancelButton = '#viewBill #btnItemServiceCancel'; 
	this.itemServiceTable="#viewBill #itemServiceTable";
	this.paymentTable="#viewBill #paymentTable";
	this.ItemServiceWorkBench="#viewBill #ItemServiceWorkBench";
	this.itemOrService="#viewBill #itemOrService";
	this.selectItemlbl="#viewBill #selectItemlbl";
	this.btnItemServiceAdd="#viewBill #btnItemServiceAdd";
	this.qtylbl="#viewBill #qtylbl";
	this.discount="#viewBill #discount";
	this.grandTotal="#viewBill #grandTotal";
	this.billDiscount="#viewBill #billDiscount";
	this.netTotal="#viewBill #netTotal"
	this.ptbBack = "#billGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#billGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#billGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.deleteItem='.deleteItem';
	this.deleteDiscount='.deleteDiscount';
	this.billItemListDTO= new BillItemListDTO() ;
	this.billDiscountDetailDTO=new BillDiscountDetailDTO();
	this.billItemList=[];
	this.discountList=[];
	this.billSupportList=[];
	this.billBedList=[];
	this.billUIStartup=billUIStartup;
	this.patientDetails="";
	this.viewBillPTB = new ViewBillPTB(this);
	this.billHeaderProcessor = new ViewBillHeaderProcessor(this);
	this.billPaymentProcessor=new ViewBillPaymentProcessor(this);
	this.itemServiceProcessor = new ViewItemServiceProcessor(this);
	this.discountProcessor=new ViewDiscountProcessor(this);
}
ViewBillUI.prototype.getBillUIStartup = function() {
	return this.billUIStartup;
}

ViewBillUI.prototype.getViewBillPTB = function() {
	return this.viewBillPTB;
}

ViewBillUI.prototype.getBillTableNavigator = function() {
	var billUIStartup = this.getBillUIStartup();
	return billUIStartup.getBillTableNavigator();
}

ViewBillUI.prototype.getBillService = function() {
	var billUIStartup = this.getBillUIStartup();
	return billUIStartup.getBillService();
}
//Set the page title of the area ui page
ViewBillUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewBillUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewBillUI.prototype.init = function(billId) {
	var self = this;
	var viewBillPTB = self.getViewBillPTB();
	viewBillPTB.init(self);
	pageHandler.create(constants.VIEWBILLPAGE);
	dataTableProcessor.setCustomTableWithoutNavigator(self.itemServiceTable);
	dataTableProcessor.setCustomTableWithoutNavigator(self.discountTable);
	dataTableProcessor.setCustomTableWithoutNavigator(self.paymentTable);
	var header = constants.BILLVIEWINFO;
	self.setPageTitle(header);
	var billResponse=self.viewBill(billId);
	if(billResponse.success==true) {
		if (billResponse.origin=="InPatient"&& billResponse.billStatus!="closed")
        	$j(self.discharge).show();
		var bill = new BillDTO();
		bill.setPatientId(billResponse.patient.id);
		bill.setOrigin(billResponse.origin);
		bill.setGrandTotal(billResponse.grandTotal);
		bill.setAmountPaid(billResponse.amountPaid);
		bill.setItem(billResponse.item);
		bill.setBed(billResponse.bed);
		bill.setDiscount(billResponse.discount)
		bill.setSupport(billResponse.support);
		bill.setBillAmount(billResponse.billAmount);
		self.billUid=billResponse.uid;
		self.itemServiceProcessor.init(billResponse);
		self.billPaymentProcessor.init(billResponse);
   		self.discountProcessor.init(billResponse);
		self.setPatientDetails(billResponse);
		self.setAmoutDetails(billResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,commonMethodInvoker.getErrorName(billResponse.error));
	}
	self.billHeaderProcessor.bindEvents();
	self.itemServiceProcessor.bindEvents();
	self.discountProcessor.bindEvents();
	self.billPaymentProcessor.bindEvents();
}

ViewBillUI.prototype.viewBill = function(billId) {
	var self=this;
	var billService = self.getBillService();
	var billResponse = billService.viewBill(billId);
	//alert(JSON.stringify(billResponse));
	return billResponse;
	
}

ViewBillUI.prototype.setPatientDetails = function(billResponse) {
	var patientDetail=billResponse.patient;
	self.patientId=patientDetail.id;
	$j(self.patientName).val(patientDetail.firstName +" "+patientDetail.lastName);
	$j(self.age).text(patientDetail.age);
	$j(self.email).text(patientDetail.email);
	$j(self.gender).text(patientDetail.gender);
	$j(self.mobile).text(patientDetail.mobile);
	$j(self.phone).text(patientDetail.phone);
	$j(self.referral).val(billResponse.referralName);
	$j(self.patientType).text(billResponse.origin);
	$j(self.date).text(billResponse.billDate);
}

ViewBillUI.prototype.setAmoutDetails = function(billResponse) {
	$j(self.grandTotal).text(billResponse.grandTotal.toFixed(2));
	$j(self.billDiscount).text(billResponse.billDiscount.toFixed(2));
	$j(self.netTotal).text(billResponse.billAmount.toFixed(2));
	
}

ViewBillUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBillPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewBillUI.prototype.getPrevId = function(curId,billResult) {
	var prevId;
	$j(billResult.billList).each(function (index, rowBill) {
		if(curId==rowBill.uid)	{
			var arrayLength=(billResult.billList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=billResult.billList[index-1].uid;
		}
	});
	return prevId;	
}
	
ViewBillUI.prototype.getNextId = function(curId,billResult) {
	var nextId;
	$j(billResult.billList).each(function (index, rowBill) {
		if(curId==rowBill.uid)	{
			var arrayLength=(billResult.billList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=billResult.billList[index+1].uid;
			}	
		}
	});	
	return nextId;	
}

