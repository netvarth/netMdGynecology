function BillPaymentDTO() {

}

BillPaymentDTO.prototype.setBillUid = function(billUid) {
	this.billUid = billUid;
}
BillPaymentDTO.prototype.setPaidAmount = function(paidAmount) {
	this.paidAmount = paidAmount;
}
BillPaymentDTO.prototype.setNote = function(note) {
	this.note = note;
}
BillPaymentDTO.prototype.getBillUid = function() {
	return this.billUid;
}
BillPaymentDTO.prototype.getPaidAmount = function() {
	return this.paidAmount;
}
BillPaymentDTO.prototype.getNote = function() {
	return this.note;
}