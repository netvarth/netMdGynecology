function BillSupportDTO() {

}

function BillSupportDTO(id,billId,stdRate,netRate,supportId,quantity,supportName,actionName) {
		this.id=id;
		this.billId=billId;
		this.stdRate=stdRate;
		this.netRate=netRate;
		this.supportId=supportId;
		this.quantity=quantity;
		this.supportName=supportName;
		this.actionName=actionName;

}

BillSupportDTO.prototype.setId = function(id) {
	this.id=id;
}
BillSupportDTO.prototype.getId = function() {
	return this.id;
}

BillSupportDTO.prototype.setBillId = function(billId) {
	this.billId=billId;
}
BillSupportDTO.prototype.getBillId = function() {
	return this.billId;
}

BillSupportDTO.prototype.setSupportName = function(supportName) {
	this.supportName=supportName;
}
BillSupportDTO.prototype.getSupportName = function() {
	return this.supportName;
}

BillSupportDTO.prototype.setStdRate = function(stdRate) {
	this.stdRate=stdRate;
}
BillSupportDTO.prototype.getStdRate = function() {
	return this.stdRate;
}

BillSupportDTO.prototype.setNetRate= function(netRate) {
	this.netRate=netRate;
}
BillSupportDTO.prototype.getNetRate = function() {
	return this.netRate;
}

BillSupportDTO.prototype.setSupportId = function(supportId) {
	this.supportId=supportId;
}
BillSupportDTO.prototype.getSupportId = function() {
	return this.supportId;
}

BillSupportDTO.prototype.setQuantity = function(quantity) {
	this.quantity=quantity;
}
BillSupportDTO.prototype.getQuantity= function() {
	return this.quantity;
}
BillSupportDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
BillSupportDTO.prototype.getActionName= function() {
	return this.actionName;
}

