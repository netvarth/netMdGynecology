
function ExpenseDTO(id,expenseName,headId,headName,totalAmount,description,paidAmount,balance,paymentStatus,note) {
	this.id=id;
	this.expenseName=expenseName;
	this.headId=headId;
	this.headName=headName;
	this.totalAmount=totalAmount;
	this.description=description;
	this.paidAmount=paidAmount;
	this.balance=balance;
	this.paymentStatus=paymentStatus;
	this.note=note;
}
 
ExpenseDTO.prototype.getId = function() {
	return this.id;
}

ExpenseDTO.prototype.setId = function(id) {
	this.id=id;
}
ExpenseDTO.prototype.getExpenseName = function() {
	return this.expenseName;
}

ExpenseDTO.prototype.setExpenseName = function(expenseName) {
	this.expenseName=expenseName;
}
ExpenseDTO.prototype.getHeadId= function() {
	return this.headId;
}

ExpenseDTO.prototype.setHeadId = function(headId) {
	this.headId=headId;
}
ExpenseDTO.prototype.getHeadName= function() {
	return this.headName;
}

ExpenseDTO.prototype.setHeadName = function(headName) {
	this.headName=headName;
}
ExpenseDTO.prototype.getDescription = function() {
	return this.description;
}

ExpenseDTO.prototype.setDescription = function(description) {
	this.description=description;
}
ExpenseDTO.prototype.getTotalAmount = function() {
	return this.totalAmount;
}

ExpenseDTO.prototype.setTotalAmount = function(totalAmount) {
	this.totalAmount=totalAmount;
}
ExpenseDTO.prototype.getPaidAmount = function() {
	return this.paidAmount;
}

ExpenseDTO.prototype.setPaidAmount = function(paidAmount) {
	this.paidAmount=paidAmount;
}
ExpenseDTO.prototype.getBalance = function() {
	return this.balance;
}

ExpenseDTO.prototype.setBalance = function(balance) {
	this.balance=balance;
}
ExpenseDTO.prototype.getPaymentStatus = function() {
	return this.paymentStatus;
}

ExpenseDTO.prototype.setPaymentStatus= function(paymentStatus) {
	this.paymentStatus=paymentStatus;
}
ExpenseDTO.prototype.getNote = function() {
	return this.note;
}

ExpenseDTO.prototype.setNote= function(note) {
	this.note=note;
}