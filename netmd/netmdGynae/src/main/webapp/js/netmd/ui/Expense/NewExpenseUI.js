function NewExpenseUI(expenseUIStartup) {
	this.createButton = $j("#newExpense #btnExpenseSave");
	this.cancelButton = $j('#newExpense #btnExpenseCancel');
	this.newExpensePage = "#newExpense";
	this.expenseModal = '#expenseModal';
	this.errorHeader = $j('#expenseModal #errorDivExpenseer');
	this.errorData = $j('#expenseModal #errorDivNewExpenseData');
	this.name="#newExpense #name";
	this.description="#newExpense #description";
	this.totalAmount = "#newExpense #totalAmount";
	this.paidAmount = "#newExpense #amountPaid";
	this.head="#newExpense #head";
	this.inputFields = ":input";
	this.expenseUIStartup = expenseUIStartup;

}

NewExpenseUI.prototype.getExpenseUIStartup = function() {
	return this.expenseUIStartup;
}

NewExpenseUI.prototype.getExpenseTableNavigator = function() {
	var expenseUIStartup = this.getExpenseUIStartup();
	return expenseUIStartup.getExpenseTableNavigator();
}

NewExpenseUI.prototype.getExpenseService = function() {
	var expenseUIStartup = this.getExpenseUIStartup();
	return expenseUIStartup.getExpenseService();
}

NewExpenseUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewExpenseUI.prototype.getExpense = function() {
	var self=this;
	var expenseName = $j(self.name).val();
	var description=$j(self.description).val();
	var totalAmount=$j(self.totalAmount).val();
	var paidAmount=$j(self.paidAmount).val();
	var headName=$j(self.head+" option:selected").text();
	var headId=$j(self.head+" option:selected").val();
	var expense = new ExpenseDTO();
	expense.setExpenseName(expenseName);
	expense.setDescription(description);
	expense.setTotalAmount(parseFloat(totalAmount));
	expense.setPaidAmount(parseInt(paidAmount));
	expense.setHeadId(parseInt(headId));
	expense.setHeadName(headName);
	return expense;
}

NewExpenseUI.prototype.clearFields = function() {
	self = this;
	$j(self.newExpensePage + " input[type=text],textarea,select").val("");
	$j(self.name ).focus();
}
NewExpenseUI.prototype.init = function() {
	var self=this;
	var expenseService = self.getExpenseService();	
	var expenseResponse = expenseService.getParentHead();
	//alert(JSON.stringify(expenseResponse));
	methodInvoker.fillParentHeadToControl(self.head,expenseResponse);
}

NewExpenseUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newExpensePage + " " + self.inputFields);
	
	$j(self.newExpensePage+ " input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	 $j(self.expenseModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.expenseModal + self.newExpensePage + " input[type=text]").val("");	
		$j(self.expenseModal).trigger('reveal:close');
		$j(self.expenseModal).remove();
		self=self.getExpenseUIStartup();
	});

    self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var expense = self.getExpense();
		//alert(JSON.stringify(expense));
		var expenseValidator = new ExpenseValidator();
		var error  = expenseValidator.validate(expense);
		//alert(JSON.stringify(error));
		if(error.errorStatus==false) {
			var expenseService = self.getExpenseService();
			var expenseResponse = expenseService.createExpense(expense);
			//alert(JSON.stringify(expenseResponse));			
			if(expenseResponse.success==true) {
				showTip(constants.EXPENSECREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var expenseTableNavigator = self.getExpenseTableNavigator();
				expenseTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(expenseResponse.error));
			}
		} else {
			self.createError(error);
		}	
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.expenseModal + self.newExpensePage + " input[type=text]").val("");	
		$j(self.expenseModal).trigger('reveal:close');
		$j(self.expenseModal).remove();
		self=self.getExpenseUIStartup();
	});	



	

	
}