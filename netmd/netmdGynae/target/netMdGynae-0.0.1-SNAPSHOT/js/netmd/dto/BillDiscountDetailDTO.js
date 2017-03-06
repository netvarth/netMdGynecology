function BillDiscountDetailDTO(){

}
BillDiscountDetailDTO.prototype.setBillUid = function(billUid) {
	this.billUid = billUid;
}
BillDiscountDetailDTO.prototype.getBillUid = function() {
	return this.billUid;
}
BillDiscountDetailDTO.prototype.setBillDiscountList = function(billDiscountList) {
	this.billDiscountList = billDiscountList;
}

BillDiscountDetailDTO.prototype.getBillDiscountList = function() {
	return this.billDiscountList;
}