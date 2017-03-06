function BillPatientHeaderDTO() {

}

function BillPatientHeaderDTO(uid,billId,patientId,referralName,origin) {
		this.uid=uid;
		this.billId=billId;
		this.patientId=patientId;
		this.referralName=referralName;
		this.origin=origin;

}

BillPatientHeaderDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
BillPatientHeaderDTO.prototype.getUid = function() {
	return this.uid;
}

BillPatientHeaderDTO.prototype.setBillId = function(billId) {
	this.billId=billId;
}
BillPatientHeaderDTO.prototype.getBillId = function() {
	return this.billId;
}

BillPatientHeaderDTO.prototype.setPatientId = function(patientId) {
	this.patientId=patientId;
}
BillPatientHeaderDTO.prototype.getPatientId = function() {
	return this.patientId;
}

BillPatientHeaderDTO.prototype.setReferralName = function(referralName) {
	this.referralName=referralName;
}
BillPatientHeaderDTO.prototype.getReferralName = function() {
	return this.referralName;
}
BillPatientHeaderDTO.prototype.setOrigin = function(origin) {
	this.origin=origin;
}
BillPatientHeaderDTO.prototype.getOrigin = function() {
	return this.origin;
}



