function ReportUIStartup() {
	this.pageTitle = $j('#pageTitle');
	this.reportPage= "#viewReport";
	this.fromDate = this.reportPage + " #fromDate";
	this.toDate =  this.reportPage + " #toDate";
	this.inputFields = this.reportPage + " :input[type=text]";
	this.btnShow = this.reportPage + " #btnShow";
	this.filterContent =this.reportPage + " #filterContentDiv";
	this.horizontalMask = this.reportPage + " #horizmaskContentDiv"
	this.maskingContentDiv =this.reportPage + " #maskingContentDiv";
	this.paramList = this.reportPage + " #paramList";
	this.maskingList = this.reportPage + " #maskingList";
	this.reportName =this.reportPage + " #reportName";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.reportModal = "#collectionReportModal";
	this.btnPrint = this.reportModal + " #btnPrintReport";
	this.tabsContent = this.reportPage + " .tabsContent";
	this.horizontalMaskValue = "#txthoriz";
	this.reportService = new ReportServiceImpl();
}
ReportUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.empty().html(value);
}
ReportUIStartup.prototype.getReportInfo = function() {
	return this.reportInfo;
}
ReportUIStartup.prototype.setReportInfo = function(reportInfo) {
	this.reportInfo = reportInfo;
}
ReportUIStartup.prototype.getReportService = function() {
	return this.reportService;
}
ReportUIStartup.prototype.init = function() {
	var self = this;
	var reportService = self.getReportService();
	self.setPageTitle(constants.REPORTS);
	pageHandler.create(constants.REPORTVIEWJSONURL);
	$j(self.fromDate).datepicker();
	$j(self.toDate).datepicker();
	var response = reportService.getReportData();
	//alert(response);
	self.setReportInfo(response);
	methodInvoker.generateReportTabs(response); 
	self.bindEvents();
}
ReportUIStartup.prototype.bindEvents = function() {
	var self = this;
	$j(self.inputFields).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.btnShow).die('click').click(function() {
		removeErrors();
		var paramListValue=[];
		var maskListValue=[];
		var reportInfo = self.getReportInfo();
		$j(self.filterContent + " a[selected]").each(function(){
			var selName=$j(this).attr('name');
			var selValue="";
			var flag=false;
			$j(reportInfo.list).each(function(index,report){
				$j(report.reportNameList).each(function(index1,sub){
					$j(sub.param).each(function(index2,filter) {
						if(filter.parameterName==selName) {
							selValue=filter.value;							
							flag=true;
							return false;
						}						
					});
					if(flag==true)
						return false;
				});
				if(flag==true)
					return false;
			});
			if(flag==true) {
				var selTextValue=$j("#txt"+ selName).val();
				selTextValue = selTextValue.replace(/'/g, "''")
				paramListValue.push(selValue + "'"+selTextValue+ "'");
			}	
		});	
		$j(self.horizontalMask + " a[selected]").each(function(){
			var selName=$j(this).attr('name');
			var selValue="";
			var flag=false;
			$j(reportInfo.list).each(function(index,report){
				$j(report.reportNameList).each(function(index1,sub){
					$j(sub.param).each(function(index2,filter) {
						if(filter.horizontalMasking!=null){
							if(filter.horizontalMasking.parameterName==selName) {
								selValue=filter.horizontalMasking.value;							
								flag=true;
								return false;
							}	
						}
					});
					if(flag==true)
						return false;
				});
				if(flag==true)
					return false;
			});
			if(flag==true) {
				var selTextValue=$j(self.horizontalMaskValue + selName).val();
				var selValues = selTextValue.split(",");
				var resTextValues=[];
				for(i=0;i<selValues.length;i++) {
					resTextValues.push("'" + selValues[i]+ "'");
				}
				paramListValue.push(selValue + "("+resTextValues+ ")");
			}	
		});	
		var paramList="";
		$j(paramListValue).each(function(index, value) {
			if(paramList=="")
				paramList = value;
			else 
				paramList = paramList + ":" + value;
		});
		$j(self.paramList).attr('value',paramList);
		
		$j(self.maskingContentDiv + " input[type=checkbox]").each(function(){
			var status=false;
			if($j(this).attr('checked'))
				status=true;
			maskListValue.push($j(this).attr('value')+':'+status);
		});	
		
		$j(self.maskingList).attr('value',maskListValue);
		self.generateReport();
	});
	$j(self.tabsContent).die('click').live('click',function() {
		var obj=$j(this);
		var reportInfo = self.getReportInfo();
		$j(self.tabsContent).removeAttr('selected');
		$j(self.tabsContent).removeAttr('style');
		var selTabContent = $j(this).attr('name');
		obj.attr('selected','selected');
		obj.attr('style','background:#DCDCDC;');	
		removeErrors();
		var flag=true;
		var maskDiv = $j('<div>');
		$j(self.reportName).attr('value',selTabContent);
		var myDiv= $j('<div />');
		var horizDiv = $j('<div />');
		$j(reportInfo.list).each(function(index,report){
			$j(report.reportNameList).each(function(index,sub){
				/* Creation of ToolBar */
				if(sub.reportName==selTabContent) {
					var headTag =$j('<h1>' + constants.COLUMNMASKING + '</h1>');
					maskDiv.append(headTag);
					maskDiv.append('<hr/>');
					$j(sub.param).each(function(index1,filter) {
						if(filter.horizontalMasking==null) {
							var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft" name="'+ filter.parameterName +'" id="btn_'+filter.parameterName+'_filter_id"><span>'+filter.displayName+'</span></a>');
							var inputTag=$j('<input type="text">');
							inputTag.attr('id','txt'+filter.parameterName);
							inputTag.addClass('genTextHeight');
							inputTag.attr('style','display:none');
							myDiv.append(toolbutton);
							myDiv.append(inputTag);
						}
						if(filter.horizontalMasking!=null) {
							var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft" name="'+ filter.horizontalMasking.parameterName +'" id="btnhoriz_'+filter.horizontalMasking.parameterName+'_filter_id"><span>'+filter.horizontalMasking.displayName+'</span></a>');
							var inputTag=$j('<input type="text" class="genTextHeight">');
							inputTag.attr('id','txthoriz'+filter.horizontalMasking.parameterName);
							horizDiv.append(toolbutton);
							horizDiv.append(inputTag);
						}
					});
					var headTag =$j('<h1>' + constants.ROWMASKING + '</h1>');
					horizDiv.prepend('<hr/>');
					horizDiv.prepend(headTag);				
					$j(sub.maskingFields).each(function(index2,mask) {
						var checkBox = new CheckBox(); 	
						checkBox.setValue(mask.value);
						checkBox.setChecked('checked');
						checkBox.setData(' ' + mask.displayName + ' ');
						maskDiv.append(checkBox.create(checkBox));
						maskDiv.append('<span class="tabSpace"/>');
					});
					flag=false;
					return false;
				}
			});
			if(flag==false)
				return false;
		});		
		$j(self.filterContent).empty().html(myDiv);
		$j(self.maskingContentDiv).empty().html(maskDiv.html());
		$j(self.horizontalMask).empty().html(horizDiv.html());
	});

	$j(self.filterContent + " a:not(:selected)").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#txt'+curObjName).show();
		methodInvoker.setReportFilterValues(curObjName);
	});	
	$j(self.filterContent + " a[selected]").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
	});	

	$j(self.horizontalMask + " a:not(:selected)").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j(self.horizontalMaskValue + curObjName).show();
	});	
	$j(self.horizontalMask + " a[selected]").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j(self.horizontalMaskValue + curObjName).hide();
	});	
}
ReportUIStartup.prototype.generateReport=function() {
	var self=this;
	var report = new ReportDTO($j(self.fromDate).val(),$j(self.toDate).val(),$j(self.reportName).attr('value'));
	var reportValidator = new ReportValidator();
	var error  = reportValidator.validate(report, self);
	if(error.errorStatus==false) {
		$j(self.reportPage).attr('method','post');
		$j(self.reportPage).attr('action',constants.REPORTGENERATEURL);
		$j(self.reportPage).attr('target','_blank');
		$j(self.reportPage).submit();
	} else
		self.createError(error);	
}	
ReportUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		if(errormsg.errorField==null)
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData,errormsg.errorMessage);
		else
			commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ReportUIStartup.prototype.createReportHeader=function(title) {
	var headerDiv = $j('<div id=header/>');
	var headerLeft = $j('<div class="five_sixth" style="text-align:center"/>');
	var curDate = new Date();
	var month = curDate.getMonth()+1;
	if(month<10)
		month='0' + month;
	var day = curDate.getDate();
	if (day<10)
		day='0' + day;
	var year = curDate.getFullYear();
	var today = "" + day + '-' + month + '-' + year + "";
	headerLeft.html('<h3>' + title + ' - ' + today + '</h3><br/>');
	headerDiv.append(headerLeft);
	return headerDiv;
}
ReportUIStartup.prototype.createDailyReportTable=function(reportInfo) {
	var self=this;
	var reportDiv = $j('<div id="report">');
	var header = self.createReportHeader("Collection Report");
	reportDiv.append(header);
	var reportData = $j('<div class="five_sixth">');
	var tbl = $j('<table id="dailycollectionReport" class="stdtable"></table>');
	var theader = $j('<thead></thead>');
	var row = $j('<tr></tr>');	
	var colHead = $j('<th class="head0" style="width:6%;"></th>');
	colHead.attr("class","column-2");
	colHead.html("SL No.");
	row.append(colHead);
	var colHead = $j('<th class="head1" style="width:10%;text-align:left;"></th>');
	colHead.html("Bill No.");
	row.append(colHead);
	colHead = $j('<th class="head1" style="width:18%;text-align:left;"></th>');
	colHead.html("Patient Name");
	row.append(colHead);
	colHead = $j('<th style="width:10%"></th>');
	//colHead.attr("class","column-2");
	var amtpaid='Amt Paid (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(amtpaid);
	row.append(colHead);
	colHead = $j('<th style ="width:7%"></th>');
	//colHead.attr("class","column-2");
	var due='Due (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(due);
	row.append(colHead);
	colHead = $j('<th style="width:12%"></th>');
	//colHead.attr("class","column-2");
	var grandTotal='Grand Total (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(grandTotal);
	row.append(colHead);
	colHead = $j('<th style="width:14%"></th>');
	//colHead.attr("class","column-2");
	var othercharges='Other Charges (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(othercharges);
	row.append(colHead);
	colHead = $j('<th style="width:11%"></th>');
	//colHead.attr("class","column-2");
	var orderdisc='Order Disc (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(orderdisc);
	row.append(colHead);
	colHead = $j('<th style="width:12%"></th>');
	//colHead.attr("class","column-2");
	var netamt='Net Amt (<img src="/weblims/images/rupee-symbel.png">)';
	colHead.html(netamt);
	row.append(colHead);
	theader.append(row);
	tbl.append(theader);
	var tbody = $j('<tbody></tbody>');
	$j(reportInfo.reports).each(function (index, report) {
		row = $j('<tr></tr>')
		colData = $j('<td ></td>');
		colData.attr("class","column-2");
		colData.html(index+1);
		row.append(colData);
		colData = $j('<td></td>');
		colData.html(report.billNo);
		row.append(colData);
		colData = $j('<td></td>');
		colData.html(report.patientName);
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.amtPaid.toFixed(2));
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.due.toFixed(2));
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.netRate.toFixed(2));
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.otherCharges.toFixed(2));
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.orderDiscount.toFixed(2));
		row.append(colData);
		colData = $j('<td></td>');
		colData.attr("class","column-2");
		colData.html(report.netAmount.toFixed(2));
		row.append(colData);
		tbody.append(row);
	});
	tbl.append(tbody);
	theader = $j('<thead></thead>');
	row = $j('<tr></tr>');	
	colHead = $j('<th colspan="3" style="line-height:40px; font-weight:bold;text-align:right;"></th>');
	colHead.html("Total");
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.grandTotal.toFixed(2));
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.totalDue.toFixed(2));
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.grandNetRate.toFixed(2));
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.otherCharges.toFixed(2));
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.totalDiscount.toFixed(2));
	row.append(colHead);
	colHead = $j('<th></th>');
	colHead.attr("class","column-2");
	colHead.html(reportInfo.totalNetAmount.toFixed(2));
	row.append(colHead);
	theader.append(row);
	tbl.append(theader);
	reportData.append(tbl);
	reportDiv.append(reportData);
	return reportDiv;
}
ReportUIStartup.prototype.submitQueryForReportList=function() {
	var self=this;
	var reportService = self.getReportService();
	return reportService.getReport(self.getReportsRequest());
}
ReportUIStartup.prototype.getReportsRequest=function() {
	var self=this;
	return new ReportDTO($j(self.fromDate).val(),$j(self.toDate).val(),constants.SALESREPORT);
}
ReportUIStartup.prototype.getTodaysReport=function(tableObj) {
	var self=this;
	var reportService = self.getReportService();
	var resultTable;
	var curDate = new Date();
	var month = curDate.getMonth()+1;
	if(month<10)
		month='0' + month;
	var day = curDate.getDate();
	if (day<10)
		day='0' + day;
	var year = curDate.getFullYear();
	var today = "" + year + '-' + month + '-' + day + "";
	var response = reportService.getTodaysReport(today);
	return response;
}
ReportUIStartup.prototype.createReportModal=function(obj) {
	var self=this;
	var response = self.getTodaysReport();
	if(!response.errorMessage) {
		var report = self.createDailyReportTable(response);
		createReportModal(report,"collectionReportModal","Today's Report",'210mm','297mm');
		openModalBox(obj,'collectionReportModal');	
		self.bindReportEvents();
	}
	else
		alert(response.errorMessage);
}
ReportUIStartup.prototype.bindReportEvents=function(obj) {
	var self=this;
	$j(self.btnPrint).die('click').live('click',function() {
		removeErrors();
		var pageDiv = $j('<div/>');
		$j(self.reportModal + " .reveal-modal-content").each(function() {			
			var tbl = $j(this);
			pageDiv.append(tbl);		
		});
		var billProcessor = new BillPrintProcessor();
		billProcessor.print(billProcessor.generateHtml(pageDiv));
		$j(self.reportModal).trigger('reveal:close',"veryfast");
	});
}