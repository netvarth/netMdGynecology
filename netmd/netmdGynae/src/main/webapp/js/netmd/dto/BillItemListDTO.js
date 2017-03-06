function BillItemListDTO() {

}

function BillItemListDTO(billUid,item,support,bed) {
	this.billUid=billUid;
	this.item=item;
	this.bed=bed;
	this.support=support;
}

BillItemListDTO.prototype.setBillUid= function(billUid) {
	this.billUid=billUid;
}
BillItemListDTO.prototype.getBillUid = function() {
	return this.billUid;
}

BillItemListDTO.prototype.setItem = function(item) {
	 this.item=item;
}
BillItemListDTO.prototype.getItem = function() {
	return this.item;
}
BillItemListDTO.prototype.setSupport = function(support) {
	 this.support=support;
}
BillItemListDTO.prototype.getSupport = function() {
	return this.support;
}
BillItemListDTO.prototype.setBed = function(bed) {
	 this.bed=bed;
}
BillItemListDTO.prototype.getBed = function() {
	return this.bed;
}



