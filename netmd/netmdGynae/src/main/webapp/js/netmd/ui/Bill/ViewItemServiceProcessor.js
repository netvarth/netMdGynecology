function ViewItemServiceProcessor(viewBillUI) {
	this.viewBillUI=viewBillUI;
	self=this.getViewBillUI();
	this.billResponse;
}
ViewItemServiceProcessor.prototype.getViewBillUI=function() {
	return this.viewBillUI;
}
ViewItemServiceProcessor.prototype.getBillResponse=function() {
	return this.billResponse;
}
ViewItemServiceProcessor.prototype.setBillResponse=function(billResponse) {
	this.billResponse=billResponse;
}
ViewItemServiceProcessor.prototype.getItemServiceObject = function() {
	return this.itemServiceObj;
}
ViewItemServiceProcessor.prototype.setItemServiceObject = function(itemServiceObj) {
	this.itemServiceObj=itemServiceObj;
}
ViewItemServiceProcessor.prototype.setItemObject = function(itemObj) {
	this.itemObj=itemObj;
}
ViewItemServiceProcessor.prototype.getItemObject = function() {
	return this.itemObj;
}
ViewItemServiceProcessor.prototype.setSupportObject = function(supportObj) {
	this.supportObj=supportObj;
}
ViewItemServiceProcessor.prototype.getSupportObject = function() {
	return this.supportObj;
}
ViewItemServiceProcessor.prototype.setBedObject = function(bedObj) {
	this.bedObj=bedObj;
}
ViewItemServiceProcessor.prototype.getBedObject = function() {
	return this.bedObj;
}

ViewItemServiceProcessor.prototype.init = function(billResponse) {
	var self_this = this;
	self=this.getViewBillUI();
	self_this.setBillResponse(billResponse);
	if(billResponse.billStatus=="closed")
		$j(self.itemServiceEditButton).hide();
	$j(self.itemServiceTable).dataTable().fnClearTable();
	if(billResponse.item.length>0) {
		$j(billResponse.item).each(function(index,item) {
        	var itemId="item_"+item.itemId;
			var rowData=$j(self.itemServiceTable).dataTable().fnAddData([item.itemName,item.itemDate,item.quantity,item.stdRate.toFixed(2),item.taxValue,item.netRate.toFixed(2),constants.DELETEITEMTAG]);
			var row=$j(self.itemServiceTable).dataTable().fnSettings().aoData[rowData].nTr;				
			$j(row).attr('id',itemId);
			$j(row).children("td:not(:first)").attr("class","column-2");
		   });
	}
	if(billResponse.support.length>0) {
		$j(billResponse.support).each(function(index,support) {
        	var serviceId="service_"+support.supportId;
			var rowData=$j(self.itemServiceTable).dataTable().fnAddData([support.supportName,support.supportDate,support.quantity,support.stdRate.toFixed(2),support.taxValue,support.netRate.toFixed(2),constants.DELETEITEMTAG]);
			var row=$j(self.itemServiceTable).dataTable().fnSettings().aoData[rowData].nTr;				
			$j(row).attr('id',serviceId);
			$j(row).children("td:not(:first)").attr("class","column-2");
		   });
	}
	if(billResponse.bed.length>0) {
		$j(billResponse.bed).each(function(index,bed) {
        	var bedId="bed_"+bed.bedId;
			var rowData=$j(self.itemServiceTable).dataTable().fnAddData([bed.bedName,bed.quantity,bed.stdRate.toFixed(2),bed.taxValue,bed.netRate.toFixed(2),constants.DELETEITEMTAG]);
			var row=$j(self.itemServiceTable).dataTable().fnSettings().aoData[rowData].nTr;				
			$j(row).attr('id',bedId);
			$j(row).children("td:not(:first)").attr("class","column-2");
		   });
	}
			
	$j(itemServiceTable).dataTable().fnSetColumnVis(5, false);
}
ViewItemServiceProcessor.prototype.writable = function() {
	$j(self.itemServiceEditButton).hide();
	$j(self.selectItemlbl+','+self.qtylbl).show();
	$j(self.ItemServiceWorkBench).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.itemServiceCancelButton).show();
	$j(self.itemServiceUpdateButton).show();
}

ViewItemServiceProcessor.prototype.readable = function() {
	$j(self.itemServiceEditButton).show();
	$j(self.selectItemlbl+','+self.qtylbl).hide();
	$j(self.ItemServiceWorkBench).hide();
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.itemServiceCancelButton).hide();
	$j(self.itemServiceUpdateButton).hide();
	$j(self.itemServiceEditButton).show();
}



ViewItemServiceProcessor.prototype.checkDuplicationItemService= function(itemOrServiceId) {
	var status=false;
	var rows = $j(self.itemServiceTable +' tr:gt(0)');
	  	rows.each(function(index, row) { 
			var itemServiceId=$j(row).attr('id');
			if(itemServiceId==itemOrServiceId){
				status=true;
				return false;
				
			}
		});
   return status;
}
ViewItemServiceProcessor.prototype.fillItemServiceTable = function(itemService,itemOrServiceId,Date,quantity,Rate,tax,netRate) {
	var myData=constants.DELETEIMAGETAG;
	var rowData=$j(self.itemServiceTable).dataTable().fnAddData([itemService,Date,quantity,Rate.toFixed(2),tax,netRate.toFixed(2),myData]);
	var row=$j(self.itemServiceTable).dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).children("td:not(:first)").attr("class","column-2");
	$j(row).attr('id',itemOrServiceId);
}

ViewItemServiceProcessor.prototype.bindEvents = function() {
	var self_this=this;
	$j(self.itemServiceEditButton).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		self_this.writable();	
		methodInvoker.fillListToControl(self.itemOrService);
		$j(self.itemServiceTable).dataTable().fnSetColumnVis(5, true);
	});

	$j(self.itemServiceUpdateButton).die('click').live('click',function(){
		var obj=$j(this);
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var itemserviceList = self_this.getItemServiceObject();
		itemserviceList.setBillUid(self.billUid);
	    var billService=self.getBillService();
		var billResponse = billService.updateBillItems(itemserviceList);
		if(billResponse.success==true) {
			var response=self.viewBill(billResponse.uid);
			self.itemServiceProcessor.init(response);	
			self.billPaymentProcessor.init(response);
			showTip(constants.ITEMSERVICEUPDATESUCCESS);
			self.billItemList=[];
			self.billSupportList=[];
			self.billBedList=[];	
			self_this.readable();
			self.setAmoutDetails(response);

			
		} else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billResponse.error));
		}		
	});

	 $j(self.itemServiceCancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self_this.readable();
		var response=self.viewBill(self.billUid);
		self.itemServiceProcessor.init(response);
		self.billItemList=[];
		self.billSupportList=[];
		self.billBedList=[];
		$j(self.itemServiceTable).dataTable().fnSetColumnVis(5, false);
	});		
	
	$j(self.btnItemServiceAdd).die('click').live('click',function(){
		var quantity;
		//var today="2014-01-28";
		var today=self_this.GetTodayDate();
		var billResponse=self_this.getBillResponse();
		var itemService=$j(self.itemOrService).val();
		var itemOrServiceId=methodInvoker.checkItemServiceExists(itemService);
		if(itemOrServiceId==""){
			commonMethodInvoker.createError($j(self.itemOrService),constants.ENTERVALIDITEMORSERVICE);
			return false;
		}
		if(itemOrServiceId!=""){
			var dupStatus = self_this.checkDuplicationItemService(itemOrServiceId);		
		    if(dupStatus==true){
				commonMethodInvoker.createError($j(self.itemOrService),constants.ITEMSERVICEALREADYEXIST);
				return false;
			}	
		}	
		itemServiceId=itemOrServiceId.split("_")[1];
		var isitem=itemOrServiceId.split("_")[0];
		var itemOrServiceDetail=methodInvoker.getItemServiceDetail(itemOrServiceId);
		var tax=itemOrServiceDetail.taxValue;
		var rate=itemOrServiceDetail.price;
		var stdRate=itemOrServiceDetail.stdRate;
		quantity=$j(self.quantity).val();
		
		if(quantity==" "||quantity==null)
			quantity=1;
		if(isitem=="item"||isitem=="service"){
	        netRate=parseFloat(stdRate*quantity);
			self_this.fillItemServiceTable(itemService,itemOrServiceId,today,quantity,rate,tax,netRate);

	   	}
	   	/* else if(isitem=="service"){
	   		netRate=stdRate;
	   		self_this.fillItemServiceTable(itemService,itemOrServiceId,quantity,rate,tax,netRate);	
		} */
		else if(isitem=="bed"){
		   	netRate=parseFloat(stdRate*quantity);
	        self_this.fillItemServiceTable(itemService,itemOrServiceId,today,quantity,rate,tax,netRate);
		}

		
	    var itemServiceReq=self_this.ItemServiceObjectCreation(itemOrServiceId,constants.ACTIONADD,billResponse,quantity,self_this.getItemServiceObject());
		itemServiceReq.setBillUid(self.billUid);
		self_this.setItemServiceObject(itemServiceReq);
		$j(self.itemOrService).val("");
		$j(self.quantity).val("");
		$j(self.quantity).removeAttr('disabled');
		 //self.findSumOfRates();*/
	});
	$j(self.quantity).bind("focusin",function (e) {
		if($j(self.itemOrService).val()!="") {
			var itemOrService=$j(self.itemOrService).val();
			var itemOrServiceId=methodInvoker.checkItemServiceExists(itemOrService);
			itemServiceId=itemOrServiceId.split("_")[1];
			$j(self.quantity).val("1");
			if(itemOrServiceId==""){
				commonMethodInvoker.createError($j(self.itemOrService), constants.ENTERVALIDITEMORSERVICE);
				return false;
			}
			var isitem=itemOrServiceId.split("_")[0];
			if(isitem=="service"){
               // $j(self.quantity).attr('disabled','disabled');
   	 	    }		
		}
	});

    $j(self.itemOrService).bind("focusin",function (e) {
		commonMethodInvoker.removeErrors();
		$j(self.quantity).val(" ");
		$j(self.quantity).removeAttr('disabled');	
		var itemService=$j(self.itemOrService).val();
		var itemOrServiceId=methodInvoker.checkItemServiceExists(itemOrService);
			itemServiceId=itemOrServiceId.split("_")[1];
			if(itemOrServiceId==""){
				commonMethodInvoker.createError($j(self.itemOrService), constants.ENTERVALIDITEMORSERVICE);
				return false;
			}			
			var isitem=itemOrServiceId.split("_")[0];
			$j(self.quantity).val("1");
			if(isitem=="service"){
                $j(self.quantity).attr('disabled','disabled');
   	 	    }
   	 	    else	
   	 	        $j(self.quantity).removeAttr('disabled');	
	});	

	$j(self.deleteItem).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var billResponse=self_this.getBillResponse();
		var currow = $j(this).closest('tr');
		quantity=currow.children('td:nth-child(2)').val();
		var itemOrServiceId = currow.attr('id');
		if($j(self.itemServiceTable).dataTable().fnGetData().length>1){
			var itemServiceReq=self_this.ItemServiceObjectCreation(itemOrServiceId,constants.ACTIONDELETE,billResponse,quantity,self_this.getItemServiceObject());
			self_this.setItemServiceObject(itemServiceReq);
		}
		else{
			commonMethodInvoker.createError($j(self.itemOrService),constants.MINIMUMITEMREQUIRED);
			return false;	
		}
	
	});

}
ViewItemServiceProcessor.prototype.GetTodayDate=function (){
    var tdate = new Date();
    var dd = tdate.getDate(); //yields day
    var MM = tdate.getMonth(); //yields month
	var LENGTH= MM.toString().length;
    var yyyy = tdate.getFullYear(); //yields year
	if(LENGTH=="1")
		var xxx = yyyy + "-" +"0"+( MM+1) + "-" + dd;
	else
		var xxx = yyyy + "-" +( MM+1) + "-" + dd;
    return xxx;  
}
ViewItemServiceProcessor.prototype.ItemServiceObjectCreation = function(itemOrServiceId,actionName,billresponse,quantity,ItemServiceData) {
	 var self_this = this;
	 var status = self_this.checkItemServiceExistsInJSONInfo(billresponse,itemOrServiceId);
     var row="#"+itemOrServiceId;
     var rowId=$j(row);
	 var itemServiceName=itemOrServiceId.split('_')[0];
	 var itemServiceId=itemOrServiceId.split('_')[1];
	 if(itemServiceName=="item")
	 	finalData = self_this.filterExpression_itemService(self_this.getItemObject(),itemServiceName,itemServiceId);
		
	if(itemServiceName=="service")
		finalData = self_this.filterExpression_itemService(self_this.getSupportObject(),itemServiceName,itemServiceId);

	if(itemServiceName=="bed")
			finalData = self_this.filterExpression_itemService(self_this.getBedObject(),itemServiceName,itemServiceId);

		
	if(actionName==constants.ACTIONDELETE){
		if(status==true && itemServiceName=="item") {
			var billItemDTO=new BillItemDTO();
			billItemDTO.setActionName(constants.ACTIONDELETE);
			billItemDTO.setItemId(parseInt(itemServiceId));
			self.billItemList.push(billItemDTO);
		}
		if(status==true && itemServiceName=="service") {
			var billSupportDTO = new BillSupportDTO();
			billSupportDTO.setActionName(constants.ACTIONDELETE);
			billSupportDTO.setSupportId(parseInt(itemServiceId));;
			self.billSupportList.push(billSupportDTO);
		}
		if(status==true && itemServiceName=="bed") {
			var billBedDTO = new BillBedDTO();
			billBedDTO.setActionName(constants.ACTIONDELETE);
			billBedDTO.setBedId(parseInt(itemServiceId));;
			self.billBedList.push(billBedDTO);
		}
		var currentRow= rowId.closest('tr');
		$j(self.itemServiceTable).dataTable().fnDeleteRow(currentRow[0]);
	}
	else if(actionName==constants.ACTIONADD && status==true) {
		if(itemServiceName=="item") {
			var billItemDTO=new BillItemDTO();
			billItemDTO.setActionName(constants.ACTIONUPDATE);
			billItemDTO.setQuantity(parseInt(quantity));
			billItemDTO.setItemId(parseInt(itemServiceId));
			self.billItemList.push(billItemDTO);
		}
		if(itemServiceName=="service") {
			var billSupportDTO = new BillSupportDTO();
			billSupportDTO.setActionName(constants.ACTIONUPDATE);
			billSupportDTO.setSupportId(parseInt(itemServiceId));
			billSupportDTO.setQuantity(parseInt(quantity));
			self.billSupportList.push(billSupportDTO);
		}
		if(itemServiceName=="bed") {
			var billBedDTO = new BillBedDTO();
			billBedDTO.setActionName(constants.ACTIONUPDATE);
			billBedDTO.setBedId(parseInt(itemServiceId));
			billBedDTO.setQuantity(parseInt(quantity));
			self.billBedList.push(billBedDTO);
		}
	}	
	else if(actionName==constants.ACTIONADD && status==false) {
		   if(itemServiceName=="item") {
			var billItemDTO=new BillItemDTO();
			billItemDTO.setActionName(constants.ACTIONADD);
			billItemDTO.setItemId(itemServiceId);
			billItemDTO.setQuantity(parseInt(quantity));
			self.billItemList.push(billItemDTO);
		}
		if(itemServiceName=="service") {
			var billSupportDTO = new BillSupportDTO();
			billSupportDTO.setActionName(constants.ACTIONADD);
			billSupportDTO.setSupportId(itemServiceId);
			billSupportDTO.setQuantity(parseInt(quantity));
			self.billSupportList.push(billSupportDTO);
		}	
		if(itemServiceName=="bed") {
			var billBedDTO = new BillBedDTO();
			billBedDTO.setActionName(constants.ACTIONADD);
			billBedDTO.setBedId(parseInt(itemServiceId));
			billBedDTO.setQuantity(parseInt(quantity));
			self.billBedList.push(billBedDTO);
		}
		

	}
	self_this.setItemObject(self.billItemList);
    self_this.setSupportObject(self.billSupportList);
    self_this.setBedObject(self.billBedList);
    self.billItemListDTO.setItem(self.billItemList);
    self.billItemListDTO.setSupport(self.billSupportList);
    self.billItemListDTO.setBed(self.billBedList);
	return self.billItemListDTO;
}	

ViewItemServiceProcessor.prototype.checkItemServiceExistsInJSONInfo = function(billResponse,itemServiceId) {
	var self_this = this;
	var itemServiceName=itemServiceId.split('_')[0];
	var itemServiceId=itemServiceId.split('_')[1];
	var status = false;
	if(itemServiceName=="item"){
		$j(billResponse.item).each(function (index,item) {
			if(itemServiceId==item.itemId){
					status=true;
					return false;
			}
	   });
	}
	else if(itemServiceName=="service"){
		$j(billResponse.support).each(function (index,support) {
			if(itemServiceId==support.supportId){
					status=true;
					return false;
			}
	   });

	}	
	else if(itemServiceName=="bed"){
		$j(billResponse.bed).each(function (index,bed) {
			if(itemServiceId==bed.bedId){
					status=true;
					return false;
			}
	   });

	}	
 	return status;
}	
ViewItemServiceProcessor.prototype.filterExpression_itemService = function(expr,itemServiceName,itemServiceId) {
	var self_this = this;
	var result=[];
	$j(expr).each(function(index,data){
	if(itemServiceName=="item"){
		if(data.itemId==itemServiceId)
			expr.splice(index,1);
         else
			result.push(data);
			
	}
	else if(itemServiceName=="service")	{
		if(data.supportId==itemServiceId)
			expr.splice(index,1);
		else
			result.push(data);
   }
    else if(itemServiceName=="bed")	{
		if(data.bedId==itemServiceId)
			expr.splice(index,1);
		 else
			result.push(data);
    }
			
	});

	return result;
}	