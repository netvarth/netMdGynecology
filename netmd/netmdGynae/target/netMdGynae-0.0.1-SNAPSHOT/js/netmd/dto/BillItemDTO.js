function BillItemDTO() {

}

function BillItemDTO(id,billId,stdRate,netRate,itemId,quantity,itemName,actionName) {
		this.id=id;
		this.billId=billId;
		this.stdRate=stdRate;
		this.netRate=netRate;
		this.itemId=itemId;
		this.quantity=quantity;
		this.itemName=itemName;
		this.actionName=actionName;

}

BillItemDTO.prototype.setId = function(id) {
	this.id=id;
}
BillItemDTO.prototype.getId = function() {
	return this.id;
}

BillItemDTO.prototype.setBillId = function(billId) {
	this.billId=billId;
}
BillItemDTO.prototype.getBillId = function() {
	return this.billId;
}

BillItemDTO.prototype.setItemName = function(itemName) {
	this.itemName=itemName;
}
BillItemDTO.prototype.getItemName = function() {
	return this.itemName;
}

BillItemDTO.prototype.setStdRate = function(stdRate) {
	this.stdRate=stdRate;
}
BillItemDTO.prototype.getStdRate = function() {
	return this.stdRate;
}

BillItemDTO.prototype.setNetRate= function(netRate) {
	this.netRate=netRate;
}
BillItemDTO.prototype.getNetRate = function() {
	return this.netRate;
}

BillItemDTO.prototype.setItemId = function(itemId) {
	this.itemId=itemId;
}
BillItemDTO.prototype.getItemId = function() {
	return this.itemId;
}

BillItemDTO.prototype.setQuantity = function(quantity) {
	this.quantity=quantity;
}
BillItemDTO.prototype.getQuantity= function() {
	return this.quantity;
}
BillItemDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
BillItemDTO.prototype.getActionName= function() {
	return this.actionName;
}


