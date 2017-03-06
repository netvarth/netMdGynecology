function NewBillDiscountProcessor(newBillUI) {
	this.newBillUI = newBillUI;
}
NewBillDiscountProcessor.prototype.getNewBillUI=function() {
	return this.newBillUI;
}
NewBillDiscountProcessor.prototype.getBillDiscountInfo=function() {
	var discountInfo=[]; 
	var selectType=$j(self.selectDiscount+ " option:selected").val();
	var discvalue=parseFloat($j(self.discountValue).val());
	var id=parseInt($j(self.selectDiscount).val());
	if(selectType!=constants.SELECTTYPE) {
		 var discountInformation=methodInvoker.getDiscountInfo(id)
		$j(discountInformation).each(function(index,disc) {
				discountDTO = new BillDiscountDTO();
				discountDTO.setDiscountId(disc.id);
				discountDTO.setName(disc.name);
				discountDTO.setDescription(disc.description);
				discountDTO.setDiscType(disc.discType)
				discountDTO.setDiscValue(discvalue);
				discountDTO.setCalculationType(disc.calculationType);
				discountInfo.push(discountDTO);
				return false;
			
		});
	}
	return discountInfo;
}
NewBillDiscountProcessor.prototype.getTestDiscountInfo=function(test_Discount_Id) {
	var discountInfo="";
	testDiscount = test_Discount_Id.split('-');
	var discountId = testDiscount[1]; 
	if(discountId!="0" || discountId!=null) {
		$j(defaultData.discount).each(function(index,disc) {
			if(disc.uid==discountId) {
				discountInfo = disc;
				return false;
			}
		});
	}
	return discountInfo;
}
NewBillDiscountProcessor.prototype.bindEvents = function() {
	var self = this.getNewBillUI();
	$j(self.selectDiscount).die('change').change(function () {
		removeErrors();
		$j(self.discount).text("0.00");
		$j(self.discountValue).val("0.00");
		$j(self.selectDiscount+ " option:selected").each(function(){
			var discountId= $j(this).attr('value');
			if(discountId!='select') {
				var discountInfo = methodInvoker.getDiscountInfo(discountId);				
				if(discountInfo.calculationType==constants.PERCENTAGE) 
					$j(self.discountTypeInfo).html(constants.PERCENTAGESYMBOL);			
				else 
					$j(self.discountTypeInfo).html(constants.RUPEESYMBOL);	
				if(discountInfo.discType!="OnDemand") 
					self.accountProcessor.setNetTotal(discountInfo);
				 else 
					$j(self.discountValue).removeAttr('disabled');
				
			}else {
				$j(self.selectDiscountType).html("");			
				$j(self.discountValue).attr('disabled','disabled');
			}
				
		});

	});
	$j(self.discountValue).focusin(function(){
		$j(self.discountValue).val("");
	});
	$j(self.discountValue).bind("keypress", function (e) {
		this.value = this.value.replace(/[^0-9\.]/g,'');
		if (e.keyCode == 13) 
			$j(self.amountPaid).focus();
	});
	$j(self.discountValue).focusout(function(){
		commonMethodInvoker.removeErrors();
		var discountValue = parseFloat($j(self.discountValue).val() || 0);
		if(discountValue!=0) {
			$j(self.discountValue).val(discountValue.toFixed(2));
			discountId=$j(self.selectDiscount).val();
			var discountInfo = methodInvoker.getDiscountInfo(discountId);
			self.accountProcessor.setNetTotal(discountInfo);
		}else 
			$j(self.discountValue).val("0.00");
	});
}