function DiscountVoucherProcessor() {
	this.discountVoucherModal="#discountVoucherModal";
	this.printButton = this.discountVoucherModal + " #btnPrintDiscVoucher"
	this.voucherContent = "#discountVoucherModal #discountVoucherResult";
}

DiscountVoucherProcessor.prototype.bindEvents = function() {
	var self=this;
	$j(self.printButton).die('click').live('click',function(){
		var documentContainer = $j(self.voucherContent).html();
		var billProcessor = new BillPrintProcessor();
		billProcessor.print(documentContainer);	
		$j(self.discountVoucherModal).trigger('reveal:close');
		$j(self.discountVoucherModal).remove();
	});	
}

DiscountVoucherProcessor.prototype.create = function(response) {
	var parentDiv =$j('<div>');
	var innerDiv=$j('<div id="discountVoucherResult" />');	
	var tableObj = $j('<table class="stdtable"/>');
	var thead = $j('<thead/>');
	var trTag=$j('<tr />');
	var thTag=$j('<th class="head0" colspan="2" style="font-size:17px; height:25px;"/>');
	thTag.html("Health Care");
	trTag.append(thTag);
	thead.append(trTag);
	var curDate = new Date();
	var month = curDate.getMonth()+1;
	if(month<10)
		month='0' + month;
	var day = curDate.getDate();
	if (day<10)
		day='0' + day;
	var year = curDate.getFullYear();
	var today = "" + day + '-' + month + '-' + year + "";
	trTag=$j('<tr />');
	thTag = $j('<th style="text-align:left;width:60%" />');
	thTag.html("Name :"+response.patientName );
	trTag.append(thTag);
	thTag = $j('<th style="text-align:right"/>');
	thTag.html("Date : " + today);
	trTag.append(thTag);
	thead.append(trTag);
	trTag=$j('<tr />');
	thTag = $j('<th style="text-align:left"/>');
	thTag.html("Order id : " + response.uid);
	trTag.append(thTag);
	thTag = $j('<th style="text-align:right"/>');
	thTag.html("");
	trTag.append(thTag);
	thead.append(trTag);
	trTag=$j('<tr />');
	thTag = $j('<th />');
	thTag.html("Item");
	trTag.append(thTag);
	thTag = $j('<th />');
	thTag.html("Amount (<img src='/netmd/images/rupee-symbel.png'>)");
	trTag.append(thTag);
	thead.append(trTag);
	trTag=$j('<tr />');
	thTag = $j('<td style="font-weight:normal;text-align:center"/>');
	thTag.html("Return amount");
	trTag.append(thTag);
	thTag = $j('<td style="font-weight:normal;text-align:right;"/>');
	thTag.html(response.amountToBePaid.toFixed(2));
	trTag.append(thTag);
	thead.append(trTag);
	trTag=$j('<tr style="height:50px"/>');
	thTag = $j('<td style="font-weight:normal;text-align:center;"/>');
	thTag.html("");
	trTag.append(thTag);
	thTag = $j('<td style="font-weight:normal;text-align:right;vertical-align:bottom"/>');
	thTag.html("Signature");
	trTag.append(thTag);
	thead.append(trTag);
	tableObj.append(thead);
	innerDiv.append(tableObj);
	var savediv = $j('<div style="width:98%; padding:5px 0px 5px 0px"/>'); // create div for store the save button
	var savebutton = $j('<input type="button" class="stdbtn" value="Print" id="btnPrintDiscVoucher"/>');
	savediv.html(savebutton);	
	parentDiv.append(innerDiv);
	parentDiv.append(savediv);
	return parentDiv;
}
