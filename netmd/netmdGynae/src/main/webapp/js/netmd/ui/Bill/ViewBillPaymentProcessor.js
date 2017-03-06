function ViewBillPaymentProcessor(viewBillUI) {
	this.viewBillUI=viewBillUI;
	self=this.getViewBillUI();
	this.bal;
}

ViewBillPaymentProcessor.prototype.getViewBillUI=function() {
	return this.viewBillUI;
}
ViewBillPaymentProcessor.prototype.init = function(billResponse) {
	var self_this = this;
	var self = this.getViewBillUI();
	if(billResponse.billStatus=="closed")
			$j(self.paymentEditButton).hide();
	$j(self.amountPaid).text(billResponse.amountPaid.toFixed(2));
	$j(self.payStatus).text(billResponse.paymentStatus);
		$j(self.balanceSection).show();
		self_this.bal=(parseFloat(billResponse.billAmount,10) || 0)-(parseFloat(billResponse.amountPaid,10) || 0);
		$j(self.balance).val(self_this.bal.toFixed(2));
		if(self_this.bal<0.00){
			$j(self.balanceSection + ' span').html('Amount to Repay(<img src="/netmd/images/rupee-symbel.png"/>)');
			$j(self.balance).val(self_this.bal.toFixed(2).slice(1));
		}
		
	 	$j(self.paymentTable).dataTable().fnClearTable();
		if(billResponse.payment.length>0) {
			$j(billResponse.payment).each(function(index,payment) {
				paidAmount = parseFloat(payment.paidAmount);		
				discDispValue= '<a href="#"><img src="/netmd/images/rupee-symbel.png"></a>&nbsp;&nbsp;'+'<span id="paidAmount">'+paidAmount.toFixed(2)+'</span>';
				var rowData=$j(self.paymentTable).dataTable().fnAddData([payment.paymentDate,discDispValue]);
				var row=$j(self.paymentTable).dataTable().fnSettings().aoData[rowData].nTr;				
			  });
		} 
}

ViewBillPaymentProcessor.prototype.writable = function() {
	var self_this=this;
	$j(self.paymentEditButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.paymentSection + " input[type=text]").removeAttr('readonly');
	$j(self.paymentSection + " input[type=text]").removeClass('newBox');
	$j(self.paymentUpdateButton).show();
	$j(self.paymentCancelButton).show();
	$j(self.paymentSection + " input[type=text]").removeAttr('disabled');
	if(self_this.bal<=0.00){
		$j(self.balance).val('0.00');
		$j(self.balanceSection + ' span').html('Payment (<img src="/netmd/images/rupee-symbel.png"/>)');
	}
	else
		$j(self.balanceSection + ' span').html('Balance (<img src="/netmd/images/rupee-symbel.png"/>)');
	$j(self.viewPaymentNote).show();
}

ViewBillPaymentProcessor.prototype.readable = function() {
	var self_this = this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.paymentSection + " input[type=text]").attr('readonly',true);
	$j(self.paymentSection + " input[type=text]").addClass('newBox');
	$j(self.paymentCancelButton).hide();
	$j(self.paymentUpdateButton).hide();
	$j(self.paymentEditButton).show();
	$j(self.paymentSection + " input[type=text]").attr('disabled',true);
	if(self_this.bal<0.00){
		$j(self.balanceSection + ' span').html('Amount to Repay(<img src="/netmd/images/rupee-symbel.png"/>)');
			$j(self.balance).val(self_this.bal.toFixed(2).slice(1));
	}
	else{
			$j(self.balanceSection + ' span').html('Balance (<img src="/netmd/images/rupee-symbel.png"/>)');
			$j(self.balance).val(self_this.bal.toFixed(2));
		}
	$j(self.viewPaymentNote).hide();

}



ViewBillPaymentProcessor.prototype.getPaymentRequest = function() {
	var billPaymentDTO = new BillPaymentDTO();
	billPaymentDTO.setBillUid(self.billUid);
	billPaymentDTO.setNote($j(self.paymentNote).val());
	var amount=parseFloat($j(self.balance).val(),10) || 0;
	billPaymentDTO.setPaidAmount(amount);
	return billPaymentDTO;
}

ViewBillPaymentProcessor.prototype.bindEvents = function() {
	var self_this=this;
	$j(self.paymentEditButton).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		self_this.writable(); 	
	});
    $j(self.btnDischarge).die('click').live('click',function(){
    	var obj=$j(this);
		commonMethodInvoker.removeErrors();
		var billService=self.getBillService();
		var billresponse= billService.dischargeBill(self.billUid);
		if(billresponse.success==true) {
			if(billresponse.amountToBePaid!=0) {
				var discountVoucher = new DiscountVoucherProcessor();
				var data=discountVoucher.create(billresponse);
				createGeneralModal(data,"discountVoucherModal","Voucher","50%","50%");
				openModalBox(obj,"discountVoucherModal");
				discountVoucher.bindEvents();
			}
				showTip(constants.DISCHARGESUCCESS);
				self_this.readable();
				var response=self.viewBill(billresponse.uid);
				self.billPaymentProcessor.init(response);
				if(billresponse.billStatus=="closed"){
					$j(self.itemServiceEditButton).hide();
					$j(self.discountEditButton).hide();
					$j(self.discharge).hide();
	
				}

			}
		 else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billresponse.error));	
	});
	$j(self.paymentUpdateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var paymentRequest = self_this.getPaymentRequest();
		var billService=self.getBillService();
		var billresponse= billService.updateBillPayment(paymentRequest);
			if(billresponse.success==true) {
				showTip(constants.PAYMENTUPDATESUCCESS);
				self_this.readable();
				var response=self.viewBill(billresponse.uid);
				self.billPaymentProcessor.init(response);
			}
		 else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billresponse.error));
	
			
});

 $j(self.paymentCancelButton).die('click').live('click',function(){ 
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	self_this.readable();
	var response=self.viewBill(self.billUid);
	self.discountProcessor.init(response);
});		
	
}