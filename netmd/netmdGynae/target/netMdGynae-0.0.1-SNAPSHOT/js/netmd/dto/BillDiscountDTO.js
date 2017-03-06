function BillDiscountDTO() {

}

function BillDiscountDTO(id,discountId,name,description,discountType,discountValue,calculationType,actionName) {
		this.id=id;
		this.discountId=discountId;
		this.name=name;
		this.description=description;
		this.discountType=discountType;
		this.discountValue=discountValue;
		this.calculationType=calculationType;
		this.actionName=actionName;
}

BillDiscountDTO.prototype.setId = function(id) {
	this.id=id;
}
BillDiscountDTO.prototype.getId = function() {
	return this.id;
}

BillDiscountDTO.prototype.setDiscountId = function(discountId) {
	this.discountId=discountId;
}
BillDiscountDTO.prototype.getDiscountId = function() {
	return this.discountId;
}
BillDiscountDTO.prototype.setName = function(name) {
	this.name=name;
}
BillDiscountDTO.prototype.getName = function() {
	return this.name;
}

BillDiscountDTO.prototype.setDescription = function(description) {
	this.description=description;
}
BillDiscountDTO.prototype.getDescription = function() {
	return this.description;
}

BillDiscountDTO.prototype.setDiscType = function(discountType) {
	this.discountType=discountType;
}
BillDiscountDTO.prototype.getDiscType = function() {
	return this.discountType;
}

BillDiscountDTO.prototype.setDiscValue = function(discountValue) {
	this.discountValue=discountValue;
}
BillDiscountDTO.prototype.getDiscValue = function() {
	return this.discountValue;
}

BillDiscountDTO.prototype.setCalculationType = function(calculationType) {
	this.calculationType=calculationType;
}
BillDiscountDTO.prototype.getCalculationType = function() {
	return this.calculationType;
}
BillDiscountDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
BillDiscountDTO.prototype.getActionName = function() {
	return this.actionName
}