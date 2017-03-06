function BillDTO() {

}

function BillDTO(id,patientId,referralName,origin,paymentStatus,currency,billDate,grandTotal,amountPaid,billAmount,notes,item,discount,support,bed) {
	this.id=id;
	this.patientId=patientId;
	this.referralName=referralName;
	this.origin=origin;
	this.paymentStatus=paymentStatus;
	this.currency=currency;
	this.billDate=billDate;
	this.grandTotal=grandTotal;
	this.amountPaid=amountPaid;
	this.billAmount=billAmount;
	this.item=item;
	this.bed=bed;
	this.notes=notes;
	this.support=support;
	this.discount=discount;
	this.notes=notes
}

BillDTO.prototype.setId = function(id) {
	this.id=id;
}
BillDTO.prototype.getId = function() {
	return this.id;
}
BillDTO.prototype.setPatientId = function(patientId) {
	this.patientId=patientId;
}
BillDTO.prototype.getPatientId = function() {
	return this.patientId;
}
BillDTO.prototype.setOrigin = function(origin) {
	 this.origin=origin;
}
BillDTO.prototype.getOrigin = function() {
	return this.origin;
}
BillDTO.prototype.setGrandTotal = function(grandTotal) {
	 this.grandTotal=grandTotal;
}
BillDTO.prototype.getGrandTotal = function() {
	return this.grandTotal;
}
BillDTO.prototype.setAmountPaid = function(amountPaid) {
	 this.amountPaid=amountPaid;
}
BillDTO.prototype.getAmountPaid = function() {
	return this.amountPaid;
}
BillDTO.prototype.setBillAmount = function(roomName) {
	 this.roomName=roomName;
}
BillDTO.prototype.getBillAmount  = function() {
	return this.roomName;
}
BillDTO.prototype.setNotes = function(notes) {
	 this.notes=notes;
}
BillDTO.prototype.getNotes = function() {
	return this.notes;
}
BillDTO.prototype.setItem = function(item) {
	 this.item=item;
}
BillDTO.prototype.getItem = function() {
	return this.item;
}
BillDTO.prototype.setSupport = function(support) {
	 this.support=support;
}
BillDTO.prototype.getSupport = function() {
	return this.support;
}
BillDTO.prototype.setBed = function(bed) {
	 this.bed=bed;
}
BillDTO.prototype.getBed = function() {
	return this.bed;
}
BillDTO.prototype.setDiscount= function(discount) {
	 this.discount=discount;
}
BillDTO.prototype.getDiscount = function() {
	return this.discount;
}
BillDTO.prototype.setReferral= function(referralName) {
	 this.referralName=referralName;
}
BillDTO.prototype.getReferral = function() {
	return this.referralName;
}



