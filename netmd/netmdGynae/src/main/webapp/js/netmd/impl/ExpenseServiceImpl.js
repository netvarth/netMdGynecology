function ExpenseServiceImpl () {
	this.setTableValues = function(tableObj, expenseResult) {	
	//alert(JSON.stringify(expenseResult));
		$j(tableObj).dataTable().fnClearTable();
		if(expenseResult.expenseList) {
			if(expenseResult.expenseList.length>0) {			
				$j(expenseResult.expenseList).each(function(index, expenseList) {
					var id=expenseList.id;
					var headName=expenseList.headName;
					var totalAmount=expenseList.totalAmount;
					var paidAmount=expenseList.paidAmount;
					var balance=expenseList.balance;
					var expenseName=expenseList.expenseName;
					var description=expenseList.description;
					var status=expenseList.paymentStatus;
					var rowData=$j(tableObj).dataTable().fnAddData([id,expenseName,headName,totalAmount,paidAmount,balance,status]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(4)").addClass('column-2');
					$j(row).children("td:nth-child(5)").addClass('column-2');
					$j(row).children("td:nth-child(6)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","expenseIdCol Ustyle");
				});
			}		
		} 
	}
	
}

ExpenseServiceImpl.prototype.createExpense=function (expenseObj) {
	ajaxProcessor.setUrl(constants.EXPENSECREATEURL);
	return ajaxProcessor.post(expenseObj);
}

ExpenseServiceImpl.prototype.updateExpense=function(expenseObj) {
	ajaxProcessor.setUrl(constants.EXPENSEUPDATEURL);
	return ajaxProcessor.post(expenseObj);
}
ExpenseServiceImpl.prototype.deleteExpense=function(expenseId) {
	ajaxProcessor.setUrl(constants.EXPENSEDELETEURL + expenseId);
	return ajaxProcessor.get();
}
ExpenseServiceImpl.prototype.viewExpense=function(expenseId) {
	ajaxProcessor.setUrl(constants.EXPENSEVIEWURL + expenseId);
	return ajaxProcessor.get();
}
ExpenseServiceImpl.prototype.getParentHead=function() {
	ajaxProcessor.setUrl(constants.GETHEADSURL);
	return ajaxProcessor.get();
} 