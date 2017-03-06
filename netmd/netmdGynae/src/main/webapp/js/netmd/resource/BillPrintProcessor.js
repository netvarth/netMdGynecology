function BillPrintProcessor(source) {
	this.source = source;
	this.getSource = function(){
		return this.source;
	}
	this.getBillService = function() {
		var parent = this.getSource();
		return parent.getBillService();
	}
	this.generateHtml = function(documentContainer) {
		var strHtml = '<html><head>';
		strHtml += "<style>";
		strHtml += "<!--";
		strHtml += "#printDiv {page-break-inside:avoid;page-break-after:avoid;page-break-before:avoid;}";
		strHtml += "#printDiv {width:100%;font-size:80%;font-family: arial;height:100%}";
		strHtml += "#printDiv .header{margin-bottom:10px;}";
		strHtml += "#printDiv .firstSection{float:left;margin-left:1%; margin-right:4%; width: 47%}";
		strHtml += "#printDiv .secondSection{float:right; width: 47%}";
		strHtml += "#printDiv .title{font-weight:normal;font-size:25px;}";
		strHtml += "#printDiv .subtitle{font-weight:normal;font-size:12px;float:left;}";
		strHtml += "#printDiv .subParentContents{margin-top:0;margin-bottom:0;}";
		strHtml += "#printDiv .middle{text-align:center;}";
		strHtml += "#printDiv .left{float:left;}";
		strHtml += "#printDiv .right{float:right;}";
		strHtml += "#printDiv .half{width:50%}";
		strHtml += "#printDiv .value{margin-left:1%}";
		strHtml += "#printDiv .full{width:100%}";
		strHtml += "#printDiv .bottomMargin{margin-bottom:5px;}";
		strHtml += "#printDiv .underline{text-decoration:underline;}";
		strHtml += "#printDiv .rowclass{width:100%;float:left;margin-bottom:3px;}";
		strHtml += "#printDiv .dataspan{margin-left:1%;}";
		strHtml += "#printDiv .dataleft{margin-left:10%; width:55%;float:left;}";
		strHtml += "#printDiv .dataNewfirst{ width:25%;float:left;}";
		strHtml += "#printDiv .dataNewleft{ width:25%;float:left;}";
		strHtml += "#printDiv .dataNewMiddle{ width:25%;float:left;}";
		strHtml += "#printDiv .dataNewRight{ width:25%;float:right;}";
		strHtml += "#printDiv .dataleft1{margin-right:10%;}";
		strHtml += "#printDiv .dataright{margin-right:15%; float:right;text-align:right;}";
		strHtml += ".makeBold {font-weight:bold;}";
		strHtml += ".leftAlign {text-align:left}";
		strHtml += ".rightAlign {text-align:right}";
		strHtml += ".column-2 {text-align:right}";
		strHtml += ".rowDiv {width:100%}";
		strHtml += "--></style>";
		strHtml+='</head><body><div id="printDiv">';
		strHtml+= documentContainer.html() + '</div></body></html>';
		return strHtml;
	}
	
	this.print = function(htmlString) {
		var WindowObject = window.open('', 'PrintWindow', 'width=624,top=50,left=50,toolbars=yes,scrollbars=yes,status=no,resizable=yes,target=_new');
		var writestat = WindowObject.document.writeln(htmlString);
		WindowObject.focus();
		WindowObject.print();
		//WindowObject.document.close();
		self = this.getSource();
	}

	this.generatePrintableSection = function(data) {
		self=this;
		var data1=data;
		var parent = $j('<div/>');
		var firstDiv = $j('<div class="firstSection"/>');
		firstDiv.html(data.html());
		parent.append(firstDiv);
		var secondDiv = $j('<div class="secondSection"/>');
		secondDiv.html(data1.html());
		parent.append(secondDiv);
		return parent;
	}
	
	this.createPrintArea = function(billInfo,billLogo) {
		self=this;
		var billService = self.getBillService();
		var total=0;
		var printForm = $j('<div id="printForm" />');
	/*Header Section Starts Here*/
	
	    var headerDiv=$j('<div class="header"/>');
	
	    //var leftKeyDiv = $j('<div class="key left" />');]
		var span=$j('<span style="float:left"></span>');
		var fileTag = $j('<img src="'+billLogo.logo+'" id="logotext" width="100px" height="60px"/>');
        span.append(fileTag);
		var headerSecDiv = $j('<div class="middle align" style="width:100%;float:middle;"/>');
		headerSecDiv.append(span);

		
		
		var billTopHeader = $j('<div class="title rowclass" style=width:70%/>');
		var mainHeader = billService.getBillValue(constants.BILLHEADER1);
		billTopHeader.empty().html(mainHeader);
		headerSecDiv.append(billTopHeader);
		
	
		
		billTopHeader = $j('<div class="subtitle rowclass" style=width:70%/>');
		var mainsubHeader = billService.getBillValue(constants.BILLHEADER2);
		billTopHeader.empty().html(mainsubHeader);
		headerSecDiv.append(billTopHeader);
		
		
		billTopHeader = $j('<div class="subtitleInvoice rowclass  underline "style=font-size:15px;">');
		var invoiceHeader = billService.getBillValue(constants.BILLHEADER3);
		billTopHeader.empty().html(invoiceHeader);
		headerSecDiv.append(billTopHeader);
		 
      
		headerDiv.append(headerSecDiv);
		printForm.append(headerDiv);
		
		
		
	/*Header Section Ends Here*/
	
	
		//Name & Ref No
		//headerDiv = $j('<div class="rowclass makeBold"/>');
		var nameDiv = $j('<div class="left half "/>');
		var nameKeyDiv = $j('<div class="key left" />');
		nameKeyDiv.append('Name :');
		nameDiv.append(nameKeyDiv);
		var nameValueDiv = $j('<div class="value" id="printPatientName"/>');
		nameValueDiv.append(billInfo.patient.firstName);
		nameDiv.append(nameValueDiv);
		headerDiv.append(nameDiv);
		
		var refSpaceDiv = $j('<div class="full"/>');
		var refDiv = $j('<div class="right half"/>');
		var refValueDiv = $j('<div class="value right" id="printrefNo"/>');
		refValueDiv.append(billInfo.uid);
		
		refDiv.append(refValueDiv);
		var refKeyDiv = $j('<div class="key right" />');
		refKeyDiv.append('Bill. No :');
		refDiv.append(refKeyDiv);
		refSpaceDiv.append(refDiv);
		headerDiv.append(refSpaceDiv);
		printForm.append(headerDiv);
		//Name & Ref No Ends
	
		//Age, Sex, Date & Time 
		headerDiv = $j('<div class="rowclass"/>');
		var agesexDiv = $j('<div class="left half"/>');
		var ageDiv = $j('<div class="left half"/>');
		var ageKeyDiv = $j('<div class="key left makeBold" />');
		ageKeyDiv.append('Age :');
		ageDiv.append(ageKeyDiv);
		var ageValueDiv = $j('<div class="value" id="printPatientAge"/>');
		if(billInfo.patient.age!=0)
			ageValueDiv.append(billInfo.patient.age);
		else
			ageValueDiv.append(" ");
		ageDiv.append(ageValueDiv);
		agesexDiv.append(ageDiv);
		var sexDiv = $j('<div class="left half"/>');
		var sexKeyDiv = $j('<div class="key left makeBold" />');
		sexKeyDiv.append('Sex :');
		sexDiv.append(sexKeyDiv);
		var sexValueDiv = $j('<div class="value left" id="printPatientSex"/>');
		sexValueDiv.append(billInfo.patient.gender);
		sexDiv.append(sexValueDiv);
		agesexDiv.append(sexDiv);
		headerDiv.append(agesexDiv);	
		var datetimeDiv = $j('<div class="right half"/>');
		var dateDiv = $j('<div class="left half"/>');
		var dateKeyDiv = $j('<div class="key left makeBold" />');
		dateKeyDiv.append('Bill Date :');
		dateDiv.append(dateKeyDiv);
		var dateValueDiv = $j('<div class="value" id="printorderDate"/>');
		if(billInfo.billDate){
			dateValueDiv.append(billInfo.billDate);
		}
		dateDiv.append(dateValueDiv);
		datetimeDiv.append(dateDiv);	
		var timeDiv = $j('<div class="left half"/>');
		var timeValueDiv = $j('<div class="value right" id="printorderTime"/>');
		timeValueDiv.append(billInfo.billTime);
		timeDiv.append(timeValueDiv);
		var timeKeyDiv = $j('<div class="key right makeBold" />');
		timeKeyDiv.append('Time :');
		timeDiv.append(timeKeyDiv);
		datetimeDiv.append(timeDiv);
		headerDiv.append(datetimeDiv);	
		printForm.append(headerDiv);
		//Age, Sex, Date & Time Ends
		
		headerDiv = $j('<div class="rowclass"/>');
		refDiv = $j('<div class="full makeBold bottomMargin"/>');
		var refKeyDiv = $j('<div class="key left" />');
		refKeyDiv.append('Ref. By :');
		refDiv.append(refKeyDiv);
		var refValueDiv = $j('<div class="value" id="printrefBy"/>');
		var ref="Dr. ";
			ref+=billInfo.referralName;
			refValueDiv.append(ref);
		    refDiv.append(refValueDiv);
		    headerDiv.append(refDiv);
		    printForm.append(headerDiv);
		//Ref By	
		
		//BillItems & Rate Ends
		//BillItems & Rate Values
		if(billInfo.item.length==0 && billInfo.support.length==0 && billInfo.bed.length==0){
			var advance=self.fillFields("&nbsp;", "&nbsp;");
			printForm.append(advance);
			var advance=self.fillFields("&nbsp;", "&nbsp;");
			printForm.append(advance);
			var advance=self.fillFields("Advance Payment", billInfo.amountPaid+'(' +'<img src="/netmd/images/rupee-symbel.png"/> )');
			printForm.append(advance);
			 headerDiv = $j('<div class="rowclass"/>');
		    var investDiv = $j('<div class="dataNewFirst"/>');
		    investDiv.append("&nbsp;");
		     headerDiv.append(investDiv);
		
		var investDiv = $j('<div class="dataNewleft "/>');
		investDiv.append("Payments:");
		headerDiv.append(investDiv);
		
		var dateDiv = $j('<div class="dataNewMiddle underline bold"/>');
		dateDiv.append('Date');
		headerDiv.append(dateDiv);
		
		var rateDiv = $j('<div class="dataNewRight underline bold"/>');
		rateDiv.append('Amount Paid( ' + '<img src="/netmd/images/rupee-symbel.png"/> )');
		headerDiv.append(rateDiv);
		
		printForm.append(headerDiv);
	
	         $j(billInfo.payment).each(function (index,payment) {
				var paymentDate=payment.paymentDate;
				var amtPaid=payment.paidAmount;
	            if(amtPaid>0)
				var payments = self.fillFields('&nbsp;',paymentDate,amtPaid);
				printForm.append(payments);
			});	
		  }
	else{
		     //BillItems & Rate Title
	    headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataNewFirst"/>');
		investDiv.append("&nbsp;");
		headerDiv.append(investDiv);
		
		var investDiv = $j('<div class="dataNewleft underline"/>');
		investDiv.append('BillItems');
		headerDiv.append(investDiv);
		
		var dateDiv = $j('<div class="dataNewMiddle underline"/>');
		dateDiv.append('Date');
		headerDiv.append(dateDiv);
		
		var rateDiv = $j('<div class="dataNewRight underline"/>');
		rateDiv.append('Rate ( ' + '<img src="/netmd/images/rupee-symbel.png"/> )');
		headerDiv.append(rateDiv);
		printForm.append(headerDiv);
		$j(billInfo.item).each(function (index,item) {
				var itemName=item.itemName;
				var rate=item.netRate.toFixed(2);
				var date=item.itemDate;
				var itemValues = self.fillFields(itemName,date,rate);
				printForm.append(itemValues);
			});			 
			$j(billInfo.support).each(function (index,support) {
				var supportName=support.supportName;
				var rate=support.netRate.toFixed(2);
				var date=support.supportDate;
				var supportValues = self.fillFields(supportName,date,rate);
				printForm.append(supportValues);
			});	
			$j(billInfo.bed).each(function (index,bed) {
				var bedName=bed.bedName;
				var rate=bed.netRate.toFixed(2);
				var bedValues = self.fillFields(bedName,date,rate);
				printForm.append(bedValues);
			});	
	//Investigation & Rate Values Ends
		
		var balanceAmt;
		var grandTotal = billInfo.grandTotal;
		var totalDiscount = billInfo.billDiscount;
		var amountPaid = billInfo.amountPaid;
		var billAmount = billInfo.billAmount;
		var netTotalAmount = grandTotal-totalDiscount;
	
		
		//GrandTotal Calculation	
		itemValues=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(itemValues);
		itemValues=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(itemValues);	
		if(billInfo.currency) {
			var res = self.fillOtherChargeFields(billInfo.currency," ");
			printForm.append(res);
		}	
		//GrandTotal Display
		if(totalDiscount!=0 ) {
			var res = self.fillTotalFields("Grand Total", grandTotal.toFixed(2));
			printForm.append(res);
		}
		//Discount Total Display
		if(totalDiscount!=0){
			var res = self.fillTotalFields("Discount Amount", totalDiscount.toFixed(2));
			printForm.append(res);
		}
	
		//GrandTotal Display
		var res = self.fillTotalFields("Net Total", netTotalAmount.toFixed(2),"makeBold");
		printForm.append(res);
		
		blnkSpace=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(blnkSpace);
		blnkSpace=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(blnkSpace);	
		/*paymentList Table*/
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataNewFirst"/>');
		investDiv.append("&nbsp;");
		headerDiv.append(investDiv);
		
		var investDiv = $j('<div class="dataNewleft "/>');
		investDiv.append("Payments:");
		headerDiv.append(investDiv);
		
		var dateDiv = $j('<div class="dataNewMiddle underline bold"/>');
		dateDiv.append('Date');
		headerDiv.append(dateDiv);
		
		var rateDiv = $j('<div class="dataNewRight underline bold"/>');
		rateDiv.append('Amount Paid( ' + '<img src="/netmd/images/rupee-symbel.png"/> )');
		headerDiv.append(rateDiv);
		
		printForm.append(headerDiv);
	
	         $j(billInfo.payment).each(function (index,payment) {
				var paymentDate=payment.paymentDate;
				var amtPaid=payment.paidAmount;
	            if(amtPaid>0)
				var payments = self.fillFields('&nbsp;',paymentDate,amtPaid);
				printForm.append(payments);
			});	
			
		blnkSpace=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(blnkSpace);
		blnkSpace=self.fillFields("&nbsp;","&nbsp;", "&nbsp;");
		printForm.append(blnkSpace);
		//Showing the balance div
		if(billInfo.paymentStatus!='Fully Paid') { 
			var res = self.fillTotalFields("Total Amount Paid",amountPaid.toFixed(2),"makeBold");
			printForm.append(res);
			//balance = total - billInfo.paidAmount;
			
		}
		if(billAmount>amountPaid){
			balanceAmt = billAmount - amountPaid;
			var res = self.fillTotalFields("Balance",balanceAmt.toFixed(2),"makeBold");
			printForm.append(res);
		}
		else{
			var repay =  amountPaid-billAmount;
			var res = self.fillTotalFields("Amount to Repay",repay.toFixed(2),"makeBold");
			printForm.append(res);
		}
}		
			

		res=self.fillFields("&nbsp;","&nbsp;", "&nbsp;") ;
		printForm.append(res);
		res=self.fillFields("&nbsp;","&nbsp;", "&nbsp;") ;
		printForm.append(res);
		headerDiv=$j('<div class="footer rowclass subtitle"/>');
		var billTopHeader = $j('<div class="middle rowclass"/>');
		var footer1 = constants.BILLFOOTER1;
		billTopHeader.append(footer1);
		billTopHeader.append('<br/>');
		var footer2 = constants.BILLFOOTER2;
		billTopHeader.append(footer2);
		headerDiv.append(billTopHeader);
		printForm.append(headerDiv);
		self = self.getSource();
		return printForm;
	}
	
	this.fillFields = function(name,date,value) {
		headerDiv = $j('<div class="rowclass"/>');
		var firstdiv = $j('<div class="dataNewFirst"/>');
		firstdiv.append("&nbsp;");
		headerDiv.append(firstdiv);
		
		var investDiv = $j('<div class="dataNewleft"/>');
		investDiv.append(name);
		headerDiv.append(investDiv);
		
		var investDiv = $j('<div class="dataNewMiddle"/>');
		investDiv.append(date)
		headerDiv.append(investDiv);
		
		var rateDiv = $j('<div class="dataNewRight"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	
	this.fillTotalFields=function(name,value,styleTag) {
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft rightAlign underline"/>');
		investDiv.append(name);
		if(styleTag!=undefined)
			investDiv.addClass(styleTag);
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright rightAlign"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	
	this.fillOtherChargeFields=function(name,value) {
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft middle"/>');
		investDiv.append(name);
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright rightAlign"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	this.bindEvents = function(source) {
		
	}
	
}