function CaseDTO() {

}

function CaseDTO(id,patientId,patientType,admittedDate,caseName,departmentId,departmentName,height,weight,bmi,hbCount,description,questionAnswerDTO) {
	this.id=id;
	this.patientId=patientId;
	this.patientType=patientType;
	this.admittedDate=admittedDate;
	this.caseName=caseName;
	this.departmentId=departmentId;
	this.departmentName=departmentName;
	this.height=height;
	this.weight=weight;
	this.bmi=bmi;
	this.hbCount=hbCount;
	this.description=description;
	this.questionAnswerDTO=questionAnswerDTO;
	
}

CaseDTO.prototype.setId = function(id) {
	this.id=id;
}
CaseDTO.prototype.getId = function() {
	return this.id;
}

CaseDTO.prototype.setPatientId = function(patientId) {
	this.patientId=patientId;
}
CaseDTO.prototype.getPatientId = function() {
	return this.patientId;
}
CaseDTO.prototype.setPatientType = function(patientType) {
	this.patientType=patientType;
}
CaseDTO.prototype.getPatientType = function() {
	return this.patientType;
}
CaseDTO.prototype.setAdmittedDate = function(admittedDate) {
	 this.admittedDate=admittedDate;
}
CaseDTO.prototype.getAdmittedDate = function() {
	return this.admittedDate;
}
CaseDTO.prototype.getCaseName = function() {
	return this.caseName;
}

CaseDTO.prototype.setCaseName = function(caseName) {
	 this.caseName=caseName;
}
CaseDTO.prototype.getDepartmentId = function() {
	return this.departmentId;
}
CaseDTO.prototype.setDepartmentId = function(departmentId) {
	 this.departmentId=departmentId;
}
CaseDTO.prototype.getDepartmentName = function() {
	return this.departmentName;
}
CaseDTO.prototype.setDepartmentName = function(departmentName) {
	this.departmentName=departmentName;
}
CaseDTO.prototype.setHeight = function(height) {
	 this.height=height;
}
CaseDTO.prototype.getHeight  = function() {
	return this.height;
}
CaseDTO.prototype.setWeight = function(weight) {
	 this.weight=weight;
}
CaseDTO.prototype.getWeight  = function() {
	return this.weight;
}
CaseDTO.prototype.setBmi = function(bmi) {
	 this.bmi=bmi;
}
CaseDTO.prototype.getBmi = function() {
	return this.bmi;
}
CaseDTO.prototype.setHbCount = function(hbCount) {
	 this.hbCount=hbCount;
}
CaseDTO.prototype.getHbCount = function() {
	return this.hbCount;
}

CaseDTO.prototype.setDescription= function(description) {
	 this.description=description;
}
CaseDTO.prototype.getDescription  = function() {
	return this.description;
}
CaseDTO.prototype.setQuestionAnswerDTO = function(questionAnswerDTO) {
	 this.questionAnswerDTO=questionAnswerDTO;
}
CaseDTO.prototype.getQuestionAnswerDTO  = function() {
	return this.questionAnswerDTO;
}

