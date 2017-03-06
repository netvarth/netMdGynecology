function AdvanceDTO() {

}

function AdvanceDTO(id,patientId,amount,name,email) {
	this.id=id;
	this.patientId=patientId;
	this.amount=amount;
	this.name=name;
	this.email=email;

}
AdvanceDTO.prototype.setId = function(id) {
	this.id=id;
}
AdvanceDTO.prototype.getId = function() {
	return this.id;
}
AdvanceDTO.prototype.setPatientId = function(patientId) {
	this.patientId=patientId;
}
AdvanceDTO.prototype.getPatientId = function() {
	return this.patientId;
}
AdvanceDTO.prototype.setName = function(name) {
	this.name=name;
}
AdvanceDTO.prototype.getName = function() {
	return this.name;
}

AdvanceDTO.prototype.setEmail = function(email) {
	this.email=email;
}
AdvanceDTO.prototype.getEmail = function() {
	return this.email;
}

AdvanceDTO.prototype.setAmount = function(amount) {
	this.amount=amount;
}
AdvanceDTO.prototype.getAmount = function() {
	return this.amount;
}

