
function MedicalRecordDTO(id,doctorId,patientId,caseId,patientName,caseName,doctorName,type,medicalRecord) {
	this.id;
	this.doctorId=doctorId;
	this.patientId=patientId;
	this.caseId=caseId;
	this.patientName=patientName;
	this.caseName=caseName;
	this.doctorName=doctorName;
	this.type=type;
	this.medicalRecord=medicalRecord;
}
MedicalRecordDTO.prototype.getId = function() {
	return this.id;
}

MedicalRecordDTO.prototype.setId = function(id) {
	this.id=id;
}
MedicalRecordDTO.prototype.getDoctorId = function() {
	return this.doctorId;
}

MedicalRecordDTO.prototype.setDoctorId = function(doctorId) {
	this.doctorId=doctorId;
}
MedicalRecordDTO.prototype.getPatientId = function() {
	return this.patientId;
}

MedicalRecordDTO.prototype.setPatientId = function(patientId) {
	this.patientId=patientId;
}
MedicalRecordDTO.prototype.getCaseId = function() {
	return this.caseId;
}
MedicalRecordDTO.prototype.setCaseId = function(caseId) {
	this.caseId=caseId;
}
MedicalRecordDTO.prototype.getPatientName = function() {
	return this.patientName;
}

MedicalRecordDTO.prototype.setPatientName = function(patientName) {
	this.patientName=patientName;
}
MedicalRecordDTO.prototype.getCaseName = function() {
	return this.caseName;
}

MedicalRecordDTO.prototype.setCaseName = function(caseName) {
	this.caseName=caseName;
}
MedicalRecordDTO.prototype.getDoctorName = function() {
	return this.doctorName;
}

MedicalRecordDTO.prototype.setDoctorName = function(doctorName) {
	this.doctorName=doctorName;
}
MedicalRecordDTO.prototype.getMedicalRecordType = function() {
	return this.type;
}

MedicalRecordDTO.prototype.setMedicalRecordType = function(type) {
	this.type=type;
}
MedicalRecordDTO.prototype.getMedicalRecord = function() {
	return this.medicalRecord;
}

MedicalRecordDTO.prototype.setMedicalRecord = function(medicalRecord) {
	this.medicalRecord=medicalRecord;
}