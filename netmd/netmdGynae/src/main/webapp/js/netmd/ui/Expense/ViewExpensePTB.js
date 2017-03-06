function ViewExpensePTB(viewExpenseUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewExpenseUI = viewExpenseUI;
	
	this.getViewExpenseUI = function() {
		return this.viewExpenseUI;
	}
		
	this.getExpenseUIStartup = function() {
		var viewExpenseUI = this.getViewExpenseUI();
		return viewExpenseUI.getExpenseUIStartup();
	}
	
	this.getExpenseTableNavigator = function() {
		var expenseUIStartup = this.getExpenseUIStartup();
		return expenseUIStartup.getExpenseTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewExpenseUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var expenseUIStartup = self.getExpenseUIStartup();
			expenseUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var expenseTableNavigator = self.getExpenseTableNavigator();
			var expense = source.getExpense();
			var prevId = self.getPrevId(expense.getId(),expenseTableNavigator.getPgDataList());
			viewUI.ViewExpense(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var expenseTableNavigator = self.getExpenseTableNavigator();
			var expense = source.getExpense();
			var prevId = self.getNextId(expense.getId(),expenseTableNavigator.getPgDataList());
			viewUI.ViewExpense(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.EXPENSEGENERAL, constants.EXPENSEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}