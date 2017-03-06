function NewBillPaymentProcessor(newBillUI) {
	this.newBillUI = newBillUI;
}

NewBillPaymentProcessor.prototype.getNewBillUI=function() {
	return this.newBillUI;
}
NewBillPaymentProcessor.prototype.setOnDemandDiscount = function() {
	var self=this.getNewBillUI();
	var grandamt = parseFloat($j(self.grandTotal).text());
	var netamt=0;
	var discamt= parseFloat($j(self.discountTotal).text());
	netamt = grandamt-discamt;
	$j(self.netTotal).text(netamt.toFixed(2));	
}

NewBillPaymentProcessor.prototype.findnetTotal =function (discountInfo) {
	var self=this.getNewBillUI();
	var tmpNetTotal = 0;
	if($j( self.billTable).dataTable().fnGetData().length>0) {
		var rows = $j(self.billTable + " tr:gt(0)");
		rows.children("td:nth-child(5)").each(function() {
			tmpNetTotal+=parseFloat($j(this).text());		  
		});
	}	
	return tmpNetTotal;
}

NewBillPaymentProcessor.prototype.setNetTotal =function (discountInfo) {
var self=this.getNewBillUI();
var self_this=this;
	if(discountInfo.discType!="OnDemand") {
		$j(self.discountValue).val(discountInfo.discValue);
		$j(self.discountValue).attr("disabled","disabled");
	}
	var discountValue = parseFloat($j(self.discountValue).val()) || 0;
	var netamt=parseFloat(self_this.findnetTotal(),10 || 0);
	var result =  self_this.getNetValueAfterDiscount(netamt,discountValue,discountInfo.id);
	if(result.success==true) {
		var orderdisc = result.discountValue;
		$j(self.discount).text(orderdisc.toFixed(2));
		$j(self.netTotal).text(result.netRate.toFixed(2));
		$j(self.amountPaid).val("0.00");
		$j(self.changeBack).hide();
		$j(self.balance).hide();
	} else {
		commonMethodInvoker.createError($j(self.discountValue), commonMethodInvoker.getErrorName(result.error));
		$j(self.discountTotal).text("0.00");
		$j(self.netTotal).text("0.00");
		$j(self.amountPaid).val("0.00");
		return false;
	}
}

NewBillPaymentProcessor.prototype.getNetValueAfterDiscount= function(netamt,discountValue,discountId) {
	var self_this = this;
	var self=this.getNewBillUI();
	var disUrl = new NetBillDiscountDTO(discountId,discountValue,netamt);
	ajaxProcessor.setUrl(constants.GETDISCOUNTVALUE);
	var response = ajaxProcessor.post(disUrl);
	return response;
}
