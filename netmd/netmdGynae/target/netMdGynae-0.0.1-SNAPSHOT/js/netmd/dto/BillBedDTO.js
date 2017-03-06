function BillBedDTO() {

}

function BillBedDTO(id,billId,stdRate,netRate,bedId,quantity,bedName,actionName) {
		this.id=id;
		this.billId=billId;
		this.stdRate=stdRate;
		this.netRate=netRate;
		this.bedId=bedId;
		this.quantity=quantity;
		this.bedName=bedName;
		this.actionName=actionName;

}

BillBedDTO.prototype.setId = function(id) {
	this.id=id;
}
BillBedDTO.prototype.getId = function() {
	return this.id;
}

BillBedDTO.prototype.setBillId = function(billId) {
	this.billId=billId;
}
BillBedDTO.prototype.getBillId = function() {
	return this.billId;
}

BillBedDTO.prototype.setBedName = function(bedName) {
	this.bedName=bedName;
}
BillBedDTO.prototype.getBedName = function() {
	return this.bedName;
}

BillBedDTO.prototype.setStdRate = function(stdRate) {
	this.stdRate=stdRate;
}
BillBedDTO.prototype.getStdRate = function() {
	return this.stdRate;
}

BillBedDTO.prototype.setNetRate= function(netRate) {
	this.netRate=netRate;
}
BillBedDTO.prototype.getNetRate = function() {
	return this.netRate;
}

BillBedDTO.prototype.setBedId = function(bedId) {
	this.bedId=bedId;
}
BillBedDTO.prototype.getBedId = function() {
	return this.bedId;
}

BillBedDTO.prototype.setQuantity = function(quantity) {
	this.quantity=quantity;
}
BillBedDTO.prototype.getQuantity= function() {
	return this.quantity;
}
BillBedDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
BillBedDTO.prototype.getActionName= function() {
	return this.actionName;
}

