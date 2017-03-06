function ExpenseUIStartup() {
	this.pgTableName = "#expense";
	this.pgTableContainer="#expenseListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#expensePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#expensePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#expensePTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#expensePTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorExpenseer = $j('#errorDivExpenseer');
	this.pgTableRowClass = '.expenseIdCol';
	this.exp = new ExpressionListDTO();
	this.expenseService = new ExpenseServiceImpl();
	this.listUrl = constants.EXPENSELISTURL;
	this.expenseTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.expenseService,this.exp);
	this.ftbToolBar = '#expense-filter-toolbar';
	this.filter = '#filter';
	this.viewExpenseUI = new ViewExpenseUI(this);
}

ExpenseUIStartup.prototype.setExpenseTableNavigator = function(expenseTableNavigator) {
	this.expenseTableNavigator = expenseTableNavigator;
}
 ExpenseUIStartup.prototype.getExpenseService = function() {
	return this.expenseService;
} 
ExpenseUIStartup.prototype.getExpenseTableNavigator = function() {
	return this.expenseTableNavigator;
}
ExpenseUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
ExpenseUIStartup.prototype.getViewExpenseUI = function() {
	return this.viewExpenseUI;
}  

ExpenseUIStartup.prototype.init = function() {
	var self = this;
	var expenseTableNavigator = self.getExpenseTableNavigator();
	self.setPageTitle(constants.EXPENSETITLE);
	var expList = new ExpressionListDTO();
	var exp= new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.EXPENSE,constants.EXPENSEPAGETOOLBAR); //Create the Page tool Bar for expense
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.EXPENSETABLELIST);//Create Table for Listing expense
	dataTableProcessor.setCustomTable(self.pgTableName);
	expenseTableNavigator.setExp(expList);
	expenseTableNavigator.list();
	self.bindEvents();
	
}

 ExpenseUIStartup.prototype.bindToolBarEvents = function() {
		var self=this;
		self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEEXPENSEUI,constants.EXPENSEMODEL);		
		openModalBox(obj,constants.EXPENSEMODEL);
		var newExpenseUI = new NewExpenseUI(self);
		newExpenseUI.init();
		newExpenseUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var expenseId=self.getSelectedExpenseId(self.pgTableName);
		$j('#' + constants.EXPENSE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(expenseId!="") {
			var viewExpenseUI = self.getViewExpenseUI();
			viewExpenseUI.init(expenseId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var expenseId=self.getSelectedExpenseId(self.pgTableName);
		if(expenseId!="") {
			var expenseService = self.getExpenseService();
			var expenseResponse = expenseService.viewExpense(expenseId);
			var confirmStatus = confirm(constants.EXPENSEDELETECONFIRM + expenseResponse.expenseName);
			if(confirmStatus==true) {			
				var response = expenseService.deleteExpense(expenseId);
				if(response.success==true) {
					showTip(constants.EXPENSEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorExpenseer,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var expenseTableNavigator = self.getExpenseTableNavigator();
				expenseTableNavigator.list();
				
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

ExpenseUIStartup.prototype.getSelectedExpenseId = function () {
	var expenseId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selExpense = $j(this.pgTableName + ' tbody tr[selected]');
		if(selExpense.length==0){
			commonMethodInvoker.createServerError(self.errorExpenseer,self.errorData, constants.SELECTEXPENSE);
		} else if(selExpense.length>1) 
			commonMethodInvoker.createServerError(self.errorExpenseer,self.errorData, constants.SELECTEXPENSEONLY);
		else
			expenseId=selExpense.attr('id');
	}
	return expenseId;
}
ExpenseUIStartup.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(self.pgTableRowClass).die('click').live('click',function(){
	   var expenseId= $j(this).parent().attr('id');
	   $j('#' + constants.EXPENSE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(expenseId!="") {
			$j('#expense-filter-wb').hide();
			var viewExpenseUI = self.getViewExpenseUI();
			viewExpenseUI.init(expenseId);
		}	
	});
} 