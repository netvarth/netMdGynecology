function BillStatusDTO(uid, status) {
	this.uid=uid;
	this.status=status;
}
BillStatusDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
BillStatusDTO.prototype.getUid = function() {
	return this.uid;
}
BillStatusDTO.prototype.setStatus = function(status) {
	this.status=status;
}
BillStatusDTO.prototype.getStatus = function() {
	return this.status;
}