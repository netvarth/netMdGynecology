function ViewExpenseUI(expenseUIStartup) {
	this.viewExpensePage = "#viewExpense";
	this.errorDivHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewExpense #id label";
	this.name = "#viewExpense #expenseName";
	this.description = "#viewExpense #description";
	this.totalAmount = "#viewExpense #totalAmount";
	this.paidAmount = "#viewExpense #paidAmount label";
	this.balance="#viewExpense #paymentSection #balance";
	this.paymentStatus="#viewExpense #paymentStatus label";
	this.headName = "#viewExpense #head";
	this.viewHead="#viewExpense  #viewHead",
	this.editHead="#viewExpense  #editHead",
	this.labelHead="#viewExpense #lblhead label";
	this.viewPaymentNote="#viewExpense  #viewPaymentNote",
	this.paymentNote="#viewExpense  #paymentNote",
	this.updateButton = '#viewExpense #btnExpenseSave';
	this.paymentsTable='#paymentsTable';
	this.headId;
	this.editButton = '#viewExpense #btnExpenseEdit';
	this.cancelButton = '#viewExpense #btnExpenseCancel'; 
	this.ptbBack = "#expenseGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#expenseGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#expenseGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.departmentListArray=[];
	this.expenseUIStartup=expenseUIStartup;
	this.viewExpensePTB = new ViewExpensePTB(this);
}
ViewExpenseUI.prototype.getExpenseUIStartup = function() {
	return this.expenseUIStartup;
}

ViewExpenseUI.prototype.getViewExpensePTB = function() {
	return this.viewExpensePTB;
}

ViewExpenseUI.prototype.getExpenseTableNavigator = function() {
	var expenseUIStartup = this.getExpenseUIStartup();
	return expenseUIStartup.getExpenseTableNavigator();
}

ViewExpenseUI.prototype.getExpenseService = function() {
	var expenseUIStartup = this.getExpenseUIStartup();
	return expenseUIStartup.getExpenseService();
}
//Set the page title of the expense ui page
ViewExpenseUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewExpenseUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}

ViewExpenseUI.prototype.init = function(expenseId) {
	var self = this;
	var viewExpensePTB = self.getViewExpensePTB();
	viewExpensePTB.init(self);
	pageHandler.create(constants.VIEWEXPENSEURL);
	dataTableProcessor.setCustomTableWithoutNavigator(self.paymentsTable);
	self.bindEvents();
	self.ViewExpense(expenseId);
}

ViewExpenseUI.prototype.ViewExpense = function(expenseId) {
	self=this;
	var expenseer = constants.VIEWEXPENSETITLE;
	var expenseService = self.getExpenseService();
	var expenseResponse = expenseService.viewExpense(expenseId);	
	//alert(JSON.stringify(expenseResponse));
	if(expenseResponse.success==true) {
		var expense = new ExpenseDTO();		
		expense.setExpenseName(expenseResponse.expenseName);	
		expense.setId(expenseResponse.id);
		expense.setDescription(expenseResponse.description);
		expense.setTotalAmount(expenseResponse.totalAmount);
		expense.setPaidAmount(expenseResponse.paidAmount);
		expense.setPaymentStatus(expenseResponse.paymentStatus);
		expense.setNote(expenseResponse.paymentNote);
		expense.setBalance(expenseResponse.balance);
		expense.setHeadName(expenseResponse.headName);
		expense.setHeadId(expenseResponse.headId);
		self.setExpense(expense);
		self.getPaymentList(expenseResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorDivHeader,self.errorData, commonMethodInvoker.getErrorName(expenseResponse.error));
	}	
	self.setPageTitle(expenseer);	
}

ViewExpenseUI.prototype.getPaymentList = function(expenseResponse) {
	$j(self.paymentsTable).dataTable().fnClearTable();
	if(expenseResponse.paymentList) {
			if(expenseResponse.paymentList.length>0) {			
				$j(expenseResponse.paymentList).each(function(index, paymentList) {
					var Id=paymentList.id;
					var paymentDate=paymentList.paymentDate;
					var PaymentTime=paymentList.paymentTime;
					var amountPaid=paymentList.amountPaid;
					var rowData=$j(self.paymentsTable).dataTable().fnAddData([Id,paymentDate,PaymentTime,amountPaid]);
					var row=$j(self.paymentsTable).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(4)").addClass('column-2');
				});
			}		
		} 
}

ViewExpenseUI.prototype.setExpense = function(expense) {
	var self=this;
	$j(self.id).text(expense.id);	
	$j(self.name).val(expense.expenseName);	
	$j(self.headName).val(expense.headName);
	$j(self.totalAmount).val(expense.totalAmount);
	$j(self.paidAmount).text(expense.paidAmount);
	$j(self.balance).val(expense.balance);
	$j(self.paymentStatus).text(expense.paymentStatus);
	$j(self.paymentNote).text(expense.paymentNote);
	$j(self.description).val(expense.description);
	self.headId=expense.headId;
	if(expense.headId==0)
		$j(self.labelHead).text(" ");
	else
		$j(self.labelHead).text(expense.headName); 
}

ViewExpenseUI.prototype.getExpense = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var description = $j(self.description).val();	
	var totalAmount=$j(self.totalAmount).val();
	var paidAmount=$j(self.paidAmount).text();
	var balance=$j(self.balance).val();
	var paymentStatus=$j(self.paymentStatus).text();
	var paymentNote=$j(self.paymentNote).val();
	var headName=$j(self.headName + " option:selected").text();
	var headId=$j(self.headName).val();
	
	var expense = new ExpenseDTO();
	expense.setExpenseName(name);
	expense.setId(id);
	expense.setDescription(description);
	expense.setTotalAmount(totalAmount);
	expense.setPaidAmount(paidAmount);
	expense.setBalance(balance);
	expense.setPaymentStatus(paymentStatus);
	expense.setNote(paymentNote);
	expense.setHeadName(headName);
	expense.setHeadId(headId);
	//alert(JSON.stringify(expense));
	return expense;
}


ViewExpenseUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewExpensePage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewHead).hide();
	$j(self.editHead).show();
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").removeAttr('readonly');
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").removeAttr('disabled');
	$j(self.viewPaymentNote).show();
	if($j(self.balance).val()==0)
		$j(self.balance).attr('readonly','readonly');
	}

ViewExpenseUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").attr('readonly',true);
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.viewHead).show();
	$j(self.editHead).hide();
	$j(self.editButton).show();
	$j(self.viewExpensePage + " input[type=text],textarea,input[type=select]").attr('disabled',true);
	$j(self.viewPaymentNote).hide();
	}

ViewExpenseUI.prototype.getPrevId = function(curId,expenseResult) {
	var prevId;
	$j(expenseResult.expenseList).each(function (index, rowExpense) {
		if(curId==rowExpense.id)	{
			var arrayLength=(expenseResult.expenseList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=expenseResult.expenseList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewExpenseUI.prototype.getNextId = function(curId,expenseResult) {
	var nextId;
	$j(expenseResult.expenseList).each(function (index, rowExpense) {
		if(curId==rowExpense.id)	{
			var arrayLength=(expenseResult.expenseList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=expenseResult.expenseList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewExpenseUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorDivHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.viewHead).show();
		$j(self.editHead).hide();
		var expenseService = self.getExpenseService();	
		var expenseResponse = expenseService.getParentHead();
		//alert(JSON.stringify(expenseResponse));
		methodInvoker.fillParentHeadToControl(self.headName,expenseResponse);
		$j(self.headName+" option[value="+self.headId+"]").attr('selected','selected');
		self.writable(); 
	});
	
	$j(self.cancelButton).die('click').live('click',function(){
	self.errorDivHeader.hide();
	commonMethodInvoker.removeErrors();
	var expense = self.getExpense();
	self.ViewExpense(expense.id);
	self.readable();	
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorDivHeader.hide();
		commonMethodInvoker.removeErrors();
		var expense = self.getExpense();
		var expenseValidator = new ExpenseValidator();
		var error  = expenseValidator.validate(expense);
		if(error.errorStatus==false) {
			var expenseService =self.getExpenseService();
			var expenseResponse = expenseService.updateExpense(expense);
			//alert(JSON.stringify(expenseResponse));
			if(expenseResponse.success==true) {
				showTip(constants.EXPENSEUPDATESUCCESS);//For showing the global Tip
				self.ViewExpense(expenseResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorDivHeader,self.errorData, commonMethodInvoker.getErrorName(expenseResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	

	
}