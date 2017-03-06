
function NewBillUI(billUIStartup) {
	this.createButton = $j("#newBill #btnSaveNewBill");
	this.cancelButton = $j('#newBill #btnCancelNewBill');
	this.createPrintButton = $j('#newBill #btnSavePrintNewBill');
	this.newBillUIPage = "#newBill";
	this.billModal = '#billModal';
	this.errorHeader = $j('#billModal #errorDivHeader');
	this.errorData = $j('#billModal #errorDivNewBillData');
    this.billAutocomplete="#newBill #txtnewBill";
    this.quantity="#newBill #quantity";
	this.billTable="#newBill #billTable";
	this.patientAutocomplete="#newBill #patientName";
	this.patientEmail="#newBill #email";
	this.referral="#newBill #referral";
	this.discountTypeInfo="#newBill #discountTypeInfo";
	this.type="#newBill #billtype";
	this.description="#newBill #description";
	this.phone="#newBill #patientPhone";
	this.mobile="#newBill #mobile";
	this.age="#newBill #age";
	this.gender="#newBill #gender label";
	this.email="#newBill #email";
	this.add="#newBill #btnNewBillAdd";
	this.inputFields = ":input";
	this.notes="#newBill #notes";
	this.curreny="#newBill #currency";
	this.amountPaid="#newBill #amountPaid";
	this.netTotal="#newBill #netTotal label";
	this.grandTotal="#newBill #grandTotal label";
	this.deleteRow="#newBill .delete";
	this.billUIStartup = billUIStartup;
	this.selectDiscount="#newBill #selectDiscount";
	this.discount="#newBill #discount label";
	this.discountValue="#newBill #discountValue";
	this.changeBack="#newBill #changeBack";
	this.changeBackDue="#newBill #changeBackDue label";
	this.balance="#newBill #balance";
	this.balanceDue="#newBill #balanceDue label";
	this.patientDetails="";
	this.patientId="";
	this.discountProcessor = new NewBillDiscountProcessor(this);
	this.accountProcessor=new NewBillPaymentProcessor(this);
	this.billValidator=new BillValidator(this);
}

NewBillUI.prototype.getBillUIStartup = function() {
	return this.billUIStartup;
}

NewBillUI.prototype.getBillUITableNavigator = function() {
	var billUIStartup = this.getBillUIStartup();
	return billUIStartup.getBillTableNavigator();
}


NewBillUI.prototype.getBillService = function() {
	var billUIStartup = this.getBillUIStartup();
	return billUIStartup.getBillService();
}
NewBillUI.prototype.init = function() {
	var self=this;
	dataTableProcessor.setCustomTableWithoutNavigator(self.billTable);
	methodInvoker.fillListToControl(self.billAutocomplete);
	var billService=self.getBillService();
    self.patientDetails=billService.getPatientDetails();
	methodInvoker.fillPatientDetailsToControl(self.patientAutocomplete,self.patientDetails);
	methodInvoker.fillDiscountToControl(self.selectDiscount);
	$j(self.amountPaid).val("0.00");	
}

NewBillUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewBillUI.prototype.getBill = function() {
	var self=this;
	var patientType=$j('input[name="patientType"]:checked').val();
	var currency=$j(self.currency).val();
	var notes=$j(self.notes).val();
	var amountPaid=$j(self.amountPaid).val();
	var grandTotal=$j(self.grandTotal).text();
	var referralName=$j(self.referral).val();
	var itemlist=[];
	var supportList=[];
	var bedlist=[];
	var discount=[];
	if($j(self.billTable).dataTable().fnGetData().length>0) {
		var rows = $j(self.billTable +' tr:gt(0)');
	  	rows.each(function(index, row) { 
			var itemServiceId=$j(row).attr('id');
			isItem=itemServiceId.split('_')[0];
			var qty=$j(row).children('td:nth-child(2)').text();
			var netRate=$j(row).children('td:nth-child(5)').text();
			id=itemServiceId.split('_')[1];
			if(isItem=="item") {
				var billItemDTO = new BillItemDTO();
				billItemDTO.setItemId(parseInt(id));
				billItemDTO.setQuantity(parseInt(qty));
				billItemDTO.setNetRate(parseFloat(netRate));
				itemlist.push(billItemDTO);
		} else if(isItem=="service") {
			var billSupportDTO = new BillSupportDTO();
			billSupportDTO.setSupportId(parseInt(id));
			billSupportDTO.setNetRate(parseFloat(netRate));
			billSupportDTO.setQuantity(parseInt(qty));
			supportList.push(billSupportDTO);
		}
		else if(isItem=="bed") {
			var billBedDTO = new BillBedDTO();
			billBedDTO.setBedId(parseInt(id));
			billBedDTO.setNetRate(parseFloat(netRate));
			billBedDTO.setQuantity(parseInt(qty));
			bedlist.push(billBedDTO);
		}
          
	}); 
} 
	var discount=self.discountProcessor.getBillDiscountInfo();
	var bill = new BillDTO();
	bill.setPatientId(parseInt(self.patientId));
	bill.setReferral(referralName);
	if(discount.length!=0){
		bill.setDiscount(discount);
	}
    bill.setItem(itemlist);
    bill.setSupport(supportList);
    bill.setBed(bedlist);
    bill.setNotes(notes);
    bill.setGrandTotal(grandTotal);
    bill.setAmountPaid(amountPaid);
    bill.setOrigin(patientType);
	return bill;
}

NewBillUI.prototype.createError = function(error) {
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newBillUIPage + errormsg.errorField), errormsg.errorMessage);
	});
}

NewBillUI.prototype.clearFields = function() {
	$j(this.newBillUIPage + " input[type=text],textarea").val("");
    $j(self.netTotal).text("0.00");	
	$j(self.grandTotal).text("0.00");	
	$j(self.billTable).dataTable().fnClearTable();

}

NewBillUI.prototype.fillBillTable = function(itemService,itemOrServiceId, quantity,Rate,tax,netRate) {
	var self=this;
	var myData=constants.DELETEIMAGETAG;
	var rowData=$j(self.billTable).dataTable().fnAddData([itemService,quantity,Rate.toFixed(2),tax,netRate.toFixed(2),myData]);
	var row=$j(self.billTable).dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).children("td:not(:first)").attr("class","column-2");
	$j(row).attr('id',itemOrServiceId);
}


NewBillUI.prototype.findSumOfRates= function() {
	var netAmount=0,grandTotal=0; 
    var rows = $j(self.billTable + " tr:gt(0)"); 
	rows.children("td:nth-child(5)").each(function() {
		grandTotal += parseFloat($j(this).html(),10) || 0;  // Note: add 10 as the radix parameter to indicate you wantbase-10 integers. This will effectively skip over values that can't be parsed.                                        
    });
	$j(self.netTotal).text(grandTotal.toFixed(2));	
	$j(self.grandTotal).text(grandTotal.toFixed(2));	
	$j(self.amountPaid).val("0.00");
	$j(self.changeBack).hide();
	$j(self.balance).hide();
}
NewBillUI.prototype.checkDuplicationItemService= function(itemOrServiceId) {
	var status=false;
	var rows = $j(self.billTable +' tr:gt(0)');
	  	rows.each(function(index, row) { 
			var itemServiceId=$j(row).attr('id');
			if(itemServiceId==itemOrServiceId){
				status=true;
				return false;
				
			}
		});
   return status;
}
NewBillUI.prototype.fillOtherPatientDetails= function(patientDetail) {
	var self=this;
	self.patientId=parseInt(patientDetail.id);
	$j(self.email).val(patientDetail.email);
	$j(self.age).val(patientDetail.age);
	$j(self.gender).text(patientDetail.gender);
	$j(self.phone).val(patientDetail.phone);
	$j(self.mobile).val(patientDetail.mobile);
	
}
NewBillUI.prototype.resetDiscountFields= function() {
	var self=this;
	$j(self.discount).text("0.00");
	$j( self.discountValue).val('');
	$j(self.selectDiscount + " option[value='select']").attr("selected","selected");
    $j(self.discountValue).attr("disabled","disabled");	
	return;  
	
}

NewBillUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newBillUIPage + " " + self.inputFields);
	
	$j(self.newBillUIPage + "input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});

	$j(self.patientAutocomplete).autocomplete({
   	 select: function(e, ui){
	 var newPatientName;
	 var originaldata;
       $j(self.patientAutocomplete).val(ui.item.value);
        commonMethodInvoker.removeErrors();
        var patientName=$j(self.patientAutocomplete).val();
		if(patientName.indexOf('[')==-1){
			newPatientName=patientName;
			originaldata=patientName;	
		}
		else{
			newPatientName=patientName.split("[")[0];
			newPatientName=$j.trim(newPatientName);
			var data=patientName.split("[")[1];
			originaldata=data.split("]")[0];
		}
	
		
		var patientDetail=methodInvoker.getPatientDetails(originaldata,newPatientName,self.patientDetails);
	   	self.fillOtherPatientDetails(patientDetail);
	    this.value =newPatientName;
   		 return false;
    }
});


	$j(self.deleteRow).die('click').live('click',function(){
		var currow = $j(this).closest('tr');
		var testId = currow.attr('id');
		$j(self.billTable).dataTable().fnDeleteRow(currow[0]);
		self.findSumOfRates();
		var totalgrand=parseFloat($j(self.grandTotal).text(),10) || 0;
		if(totalgrand==0){
			self.resetDiscountFields();
		}	

	});
	$j(self.quantity).bind("focusin",function (e) {
		if($j(self.billAutocomplete).val()!="") {
			var itemOrService=$j(self.billAutocomplete).val();
			var itemOrServiceId=methodInvoker.checkItemServiceExists(itemOrService);
			itemServiceId=itemOrServiceId.split("_")[1];
			$j(self.quantity).val("1");
			if(itemOrServiceId==""){
				commonMethodInvoker.createError($j(self.billAutocomplete), constants.ENTERVALIDITEMORSERVICE);
				return false;
			}
			var isitem=itemOrServiceId.split("_")[0];
			if(isitem=="service"){
                //$j(self.quantity).attr('disabled','disabled');
   	 	    }		
		}
	});
    $j(self.amountPaid).bind("focusout",function (e) {
		var amtPaid=parseFloat($j(self.amountPaid).val(),10) || 0;
		var nettotal= parseFloat($j(self.netTotal).text());
	if(amtPaid=='0'|| amtPaid==''){
		$j(self.amountPaid).val("0.00");
	} else if(amtPaid<nettotal){
		var due = nettotal-amtPaid;
		$j(self.balance).show();
		$j(self.changeBack).hide();
		$j(self.balanceDue).text((due.toFixed(2)));

		} else{
		var due = amtPaid-nettotal;
		$j(self.changeBack).show();
		$j(self.balance).hide();
		$j(self.changeBackDue).text((due.toFixed(2)));
		
	}		
	});
    
	$j(self.add).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		var quantity;
		var netRate="";
		var itemService=$j(self.billAutocomplete).val();
		var itemOrServiceId=methodInvoker.checkItemServiceExists(itemService);
		if(itemOrServiceId==""){
			commonMethodInvoker.createError($j(self.billAutocomplete), constants.ENTERVALIDITEMORSERVICE);
			return false;
		}
		if(itemOrServiceId!=""){
			var dupStatus = self.checkDuplicationItemService(itemOrServiceId);		
		    if(dupStatus==true){
				commonMethodInvoker.createError($j(self.billAutocomplete),constants.ITEMSERVICEALREADYEXIST);
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
		if(quantity==""||quantity==null)
            quantity=1; 
		if(isitem=="item"||isitem=="service"){
        	netRate=parseFloat(stdRate*quantity);
		    self.fillBillTable(itemService,itemOrServiceId,quantity,rate,tax,netRate);
   	 	}
   	 	/* else if(isitem=="service"){
        	netRate=stdRate;
          	self.fillBillTable(itemService,itemOrServiceId,quantity,rate,tax,netRate);	
	   } */
	   else if(isitem=="bed"){
	   		netRate=parseFloat(stdRate*quantity);
          	self.fillBillTable(itemService,itemOrServiceId,quantity,rate,tax,netRate);
	   }

	  $j(self.billAutocomplete).val("");
	  $j(self.quantity).val("");
	  $j(self.quantity).removeAttr('disabled');
	 self.findSumOfRates();
	});

	$j(self.billModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.billModal + self.newBillUIPage + " input[type=text]").val("");	
		$j(self.billModal).trigger('reveal:close');
		$j(self.billModal).remove();
		self=self.getBillUIStartup();
	});

	self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var patientName=$j(self.patientAutocomplete).val();
		var bill = self.getBill();
		var error  = self.billValidator.validate(bill,patientName);
		if(error.errorStatus==false) {
			var billService = self.getBillService();
			var billResponse = billService.createBill(bill);
			if(billResponse.success==true) {
				showTip(constants.BILLCREATESUCCESS);//For showing the global Tip
				self.resetDiscountFields();
				self.clearFields();
				var billTableNavigator = self.getBillUITableNavigator();
				billTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billResponse.error));
			}
		} else {
			self.createError(error);
		
		}

	});
	self.createPrintButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var bill = self.getBill();
		var billValidator = new BillValidator();
		var billService = self.getBillService();
		var billResponse = billService.createBill(bill);
		if(billResponse.success==true) {
			showTip(constants.BILLCREATESUCCESS);
			self.resetDiscountFields();
			self.clearFields();					
			var billInfo = billService.viewBill(billResponse.uid);
			var billLogo=billService.getlogo();
			var billProcessor = new BillPrintProcessor(self);
			var result = billProcessor.createPrintArea(billInfo,billLogo);
			billProcessor.print(billProcessor.generateHtml(result));			
			var billTableNavigator = self.getBillUITableNavigator();
			billTableNavigator.list();
		} else 
		 commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billResponse.error));
	});

	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.billModal + self.newBillPage + " input[type=text]").val("");	
		$j(self.billModal).trigger('reveal:close');
		$j(self.billModal).remove();
		self=self.getBillUIStartup();
	});	
     self.discountProcessor.bindEvents();

	
}