
function BillServiceImpl () {
	this.setTableValues = function(tableObj, billResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(billResult.billList) {
			if(billResult.billList.length>0) {			
				$j(billResult.billList).each(function(index, bill) {
					var id=bill.id;
					var billNumber=bill.uid;
					var billStat=bill.billStatus;
					var date=bill.orderDate;
					var name=bill.patientName;
					name = name.toLowerCase().replace(/\b[a-z]/g, function(letter) {
					return letter.toUpperCase();
					});
					var total=bill.billAmount;
					var amountPaid=bill.amountPaid;
					var paymentStatus=bill.paymentStatus;
					var billstatus;
					
					if(billStat==constants.OPEN)
						billstatus='<img width="18" height="18" style="float:left;" src="/netmd/images/pr1.png">';
					else if(billStat==constants.CLOSED)
						billstatus='<img width="18" height="18" style="float:left;" src="/netmd/images/pr2.png">';
					else
						billstatus='<img width="18" height="18" style="float:left;" src="/netmd/images/pr3.png">';
					billstatus += billStat;
					
					var rowData=$j(tableObj).dataTable().fnAddData([billNumber,billstatus,date,name,total.toFixed(2),amountPaid.toFixed(2),paymentStatus]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',billNumber);
					$j(row).children("td:nth-child(1)").attr("class","billIdCol Ustyle");
					$j(row).children("td:nth-child(5),td:nth-child(6)").addClass('column-2');
				});
			}		
		} 
	}
	
}


BillServiceImpl.prototype.getBills=function () {
	ajaxProcessor.setUrl(constants.GETCOMPLETEBILLDETAILS);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.createBill=function (billObj) {
	ajaxProcessor.setUrl(constants.BILLCREATEURL);
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.getlogo=function () {
	ajaxProcessor.setUrl(constants.NETMDBRANCHURL);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.updateBillHeader=function (billObj) {
	ajaxProcessor.setUrl(constants.UPDATEBILLHEADER);
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.updateBill=function(billObj) {
	ajaxProcessor.setUrl(constants.BILLUPDATEURL);
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.deleteBill=function(billId) {
	ajaxProcessor.setUrl(constants.BILLDELETEURL + billId);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.updateBillItems=function(billObj) {
	ajaxProcessor.setUrl(constants.UPDATEBILLITEMURL );
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.viewBill=function(billId) {
	ajaxProcessor.setUrl(constants.BILLVIEWURL + billId);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.dischargeBill=function(billId) {
	ajaxProcessor.setUrl(constants.DISCHARGEBILL+billId);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.getBillTypes=function() {
	ajaxProcessor.setUrl(constants.GETBILLTYPES);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.getPatientDetails=function() {
	ajaxProcessor.setUrl(constants.GETPATIENTDETAILS);
	return ajaxProcessor.get();
}
BillServiceImpl.prototype.updateBillDiscount=function(billObj) {
	ajaxProcessor.setUrl(constants.UPDATEBILLDISCOUNT);
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.updateBillPayment=function(billObj) {
	ajaxProcessor.setUrl(constants.UPDATEBILLPAYMENT);
	return ajaxProcessor.post(billObj);
}
BillServiceImpl.prototype.updateBillStatus=function(billObj) {
	ajaxProcessor.setUrl(constants.UPDATEBILLSTATUS);
	return ajaxProcessor.post(billObj);
} 
BillServiceImpl.prototype.getBillValue=function(value) {
	ajaxProcessor.setUrl(constants.VIEWSETTINGBYNAMEURL + value);
	var response = ajaxProcessor.get();
	if(!response.errorMessage) {
		if(response.setting.settingList){
			$j(response.setting.settingList).each(function(index,settingDetail){
				responseValue = settingDetail.value;
				return false;
			});
		}	
	} 
	return responseValue;
}



