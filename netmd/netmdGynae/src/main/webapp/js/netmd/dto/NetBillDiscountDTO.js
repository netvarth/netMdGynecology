function NetBillDiscountDTO(id,discValue,amount) {
	if(arguments.length>0){
		this.id=id;
		this.discValue=discValue;
		this.amount=amount;
	}	
}
NetBillDiscountDTO.prototype.setId = function(id) {
	this.id=id;
}
NetBillDiscountDTO.prototype.getId = function() {
	return this.id;
}
NetBillDiscountDTO.prototype.setDiscValue = function(discValue) {
	this.discValue=discValue;
}
NetBillDiscountDTO.prototype.getDiscValue = function() {
	return this.discValue;
}
NetBillDiscountDTO.prototype.setAmount = function(amount) {
	this.amount=amount;
}
NetBillDiscountDTO.prototype.getAmount = function() {
	return this.amount;
}