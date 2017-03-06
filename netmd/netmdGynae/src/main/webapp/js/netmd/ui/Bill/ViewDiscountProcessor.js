function ViewDiscountProcessor(viewBillUI) {
	this.viewBillUI=viewBillUI;
	self=this.getViewBillUI();
}

ViewDiscountProcessor.prototype.getViewBillUI=function() {
	return this.viewBillUI;
}
ViewDiscountProcessor.prototype.getBillResponse=function() {
	return this.billResponse;
}
ViewDiscountProcessor.prototype.setBillResponse=function(billResponse) {
	this.billResponse=billResponse;

}
ViewDiscountProcessor.prototype.getBillDiscountObject = function() {
	return this.discountObject;
}
ViewDiscountProcessor.prototype.setBillDiscountObject = function(discountObject) {
	this.discountObject=discountObject;
}


ViewDiscountProcessor.prototype.init = function(bill) {
	var self_this = this;
	self=this.getViewBillUI();
	var billDiscountList=[];
	self_this.setBillResponse(bill);
	self_this.setBillDiscountObject(billDiscountList);
	if(bill.billStatus=="closed")
		$j(self.discountEditButton).hide();
	$j(self.discountTable).dataTable().fnClearTable();
	if(bill.discount.length>0) {
		$j(bill.discount).each(function(index,discount) {
			discountValue = parseFloat(discount.discountValue);
			if(discount.calculationType==constants.FIXED) 		
				discDispValue= '<a href="#"><img src="/netmd/images/rupee-symbel.png"></a>&nbsp;&nbsp;'+'<span id="discountValue">'+discountValue.toFixed(2)+'</span>';
			else 
				discDispValue ='<span id="discountValue">'+discountValue+'</span>'+'&nbsp;&nbsp;<a href="#"><img src="/netmd/images/percentage.png"></a>';						
			var myData=constants.DELETEDISCOUNTTAG;
			var rowData=$j(self.discountTable).dataTable().fnAddData([discount.name,discDispValue,myData]);
			var row=$j(self.discountTable).dataTable().fnSettings().aoData[rowData].nTr;				
			$j(row).attr('id',discount.discountId);
		   });
	}
	$j(self.discountTable).dataTable().fnSetColumnVis(2, false);
}
ViewDiscountProcessor.prototype.writable = function() {
	$j(self.discountEditButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.discountworkBench).show();
	$j(self.discountCancelButton).show();
	$j(self.discountUpdateButton).show();
	$j(self.discountTitleSection).show();
}

ViewDiscountProcessor.prototype.readable = function() {
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.discountCancelButton).hide();
	$j(self.discountUpdateButton).hide();
	$j(self.discountEditButton).show();
	$j(self.discountworkBench).hide();
	$j(self.discountTitleSection).hide();

}
ViewDiscountProcessor.prototype.checkDiscountExistsInJson = function(billResponse,discountuid) {
	var status = false;
	$j(billResponse.discount).each(function (index,discount) {
		if(discount.discountId == discountuid){
			status=true;
			return false;
		}	  
	});
	return status;
}
ViewDiscountProcessor.prototype.filterExpression=function(expr,id) {
	var result=[];
	$j(expr).each(function(index,data){
		if(data.discountId==id)
			expr.splice(index,1);
		else
			result.push(data);
	});
	return result;
}
ViewDiscountProcessor.prototype.getDiscountJSONObject = function(discountId,actionName,billResponse,discountObject) {
	var self_this=this;
	var self=this.getViewBillUI();
	var status = self_this.checkDiscountExistsInJson(billResponse,discountId);
    var row="#"+discountId;
    var rowId=$j(row);
	var discval=rowId.closest('tr').children('td:nth-child(2)').children('#discountValue').text();
	discountObject =self_this.filterExpression(discountObject,discountId);
    if(actionName==constants.ACTIONDELETE){
		if(status==true) {
			var billDiscountDTO = new BillDiscountDTO();
			billDiscountDTO.setDiscountId(discountId);
			billDiscountDTO.setDiscValue(discval);
			billDiscountDTO.setActionName(constants.ACTIONDELETE);	
			discountObject.push(billDiscountDTO);
		}	
		var currentRow = rowId.closest('tr');
		$j(self.discountTable).dataTable().fnDeleteRow(currentRow[0]);
	}else {
		var billDiscountDTO = new BillDiscountDTO();
		billDiscountDTO.setDiscountId(discountId);
		billDiscountDTO.setDiscValue(discval);
		if(status==true) 
			billDiscountDTO.setActionName(constants.ACTIONUPDATE);
		else 
			billDiscountDTO.setActionName(constants.ACTIONADD);
		discountObject.push(billDiscountDTO);
	}
	return discountObject;
}
ViewDiscountProcessor.prototype.getBillDiscount = function(discountId,discval) {
	var discountInfo=methodInvoker.getDiscountInfo(discountId);
	var discount = new DiscountDTO(discountInfo);
	discount.setDiscValue(discval);
	return discount;
}
ViewDiscountProcessor.prototype.getBillDiscount = function() {
	var self_this=this;
	var billDiscountDetailDTO = new BillDiscountDetailDTO();
	billDiscountDetailDTO.setBillUid(self.billUid);
	billDiscountDetailDTO.setBillDiscountList(self_this.getBillDiscountObject());
	return billDiscountDetailDTO;
}

ViewDiscountProcessor.prototype.bindEvents = function() {
	var self_this=this;
	$j(self.discountEditButton).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		self_this.writable(); 
		methodInvoker.fillDiscountToControl(self.selectDiscount);
		$j(self.discountTable).dataTable().fnSetColumnVis(2, true);
		
	});

	$j(self.discountUpdateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var discountRequest = self_this.getBillDiscount();
		var billService =self.getBillService();
		var billResponse = billService.updateBillDiscount(discountRequest);
		if(billResponse.success==true) {
			showTip(constants.DISCOUNTUPDATESUCCESS);
			self_this.readable();
			var response=self.viewBill(billResponse.uid);
			self.discountProcessor.init(response);
			self.billPaymentProcessor.init(response);
			self.setAmoutDetails(response);
			
		} else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billResponse.error));
		}
	
			
});


 	$j(self.discountCancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self_this.readable();
		var response=self.viewBill(self.billUid);
		$j(self.discountTable).dataTable().fnSetColumnVis(2, false);
	});		
	
	$j(self.selectDiscount).die('change').change(function () {
		removeErrors();
		$j(self.discountValue).val("0.00");
		$j(self.selectDiscount+ " option:selected").each(function(){
			var discountId= $j(this).attr('value');
			if(discountId!='select') {
				var discountInfo = methodInvoker.getDiscountInfo(discountId);				
				if(discountInfo.calculationType==constants.PERCENTAGE) 
					$j(self.discountType).html(constants.PERCENTAGESYMBOL);			
				else 
					$j(self.discountType).html(constants.RUPEESYMBOL);	
				if(discountInfo.discType!=constants.ONDEMAND) {
					$j(self.discountValue).val(discountInfo.discValue);
					$j(self.discountValue).attr("disabled","disabled");
				} else{ 
					$j(self.discountValue).removeAttr('disabled');
				}
				
			}else {
				$j(self.selectDiscountType).html("");			
				$j(self.discountValue).attr('disabled','disabled');
			}
				
		});

	});
	$j(self.btnAddDiscount).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		var billResponse=self_this.getBillResponse();
		var discDispValue="";
		var discountId= $j(self.selectDiscount + " option:selected").val();
		if(discountId=='select'){
			commonMethodInvoker.createError($j(self.selectDiscount),constants.SELECTDISCOUNT);
			return false;
		}
		var dupStatus = commonMethodInvoker.checkRowIdDuplication(self.discountTable,discountId);
		if(dupStatus==true){
			commonMethodInvoker.createError($j(self.selectDiscount),constants.SAMEDISCOUNTEXIST);
			return false;
		}
		var discountValue=parseFloat($j(self.discountValue).val(),10)||0;	  
		if(discountValue==""|| discountValue==0 || isNaN(discountValue)){
			commonMethodInvoker.createError($j(self.discountValue),constants.ENTERDISCOUNTVALUE);
			return false;
		}
		var discountInfo = methodInvoker.getDiscountInfo(discountId);
		if(discountInfo.calculationType==constants.FIXED) 				
			discDispValue= '<a href="#"><img src="/netmd/images/rupee-symbel.png"></a>&nbsp;&nbsp;'+'<span id="discountValue">'+discountValue.toFixed(2)+'</span>';
		else {
			if(discountValue>'100'){ 
				commonMethodInvoker.createError($j(self.discountValue),constants.DISCOUNTGREAT100PERC);
				$j(self.discountValue).val("");
				return false;
			}
			discDispValue ='<span id="discountValue">'+discountValue+'</span>'+'&nbsp;&nbsp;<a href="#"><img src="/netmd/images/percentage.png"></a>';
		}
		var name=discountInfo.name;
		var id=discountInfo.id;
		var myData=constants.DELETEDISCOUNTTAG;
		var discountTable=$j(self.discountTable).dataTable().fnAddData([name,discDispValue,myData]);
		var discountTableRow =$j(self.discountTable).dataTable().fnSettings().aoData[discountTable].nTr;
		$j(discountTableRow).attr('id',id);
		var discountObject= self_this.getDiscountJSONObject(id,constants.ACTIONADD,billResponse,self_this.getBillDiscountObject());
		self_this.setBillDiscountObject(discountObject);
		$j(self.discountValue).val("");
		$j(self.selectDiscount + " option[value='select']").attr("selected","selected");	
	}); 

	$j(self.deleteDiscount).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var billResponse=self_this.getBillResponse();
		var discountId=$j(this).parents('tr').attr('id');
		var discountObject=self_this.getDiscountJSONObject(discountId,constants.ACTIONDELETE,billResponse,self_this.getBillDiscountObject());
		self_this.setBillDiscountObject(discountObject);
	});	  
}