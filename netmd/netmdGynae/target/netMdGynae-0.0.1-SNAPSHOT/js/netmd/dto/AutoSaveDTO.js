function AutoSaveDTO() {

}

function AutoSaveDTO(caseId,answerSetId,departmentId,answerDTO) {
	this.caseId=caseId;
	this.answerSetId=answerSetId;
	this.departmentId=departmentId;
	this.answerDTO=answerDTO;
}
AutoSaveDTO.prototype.setCaseId = function(caseId) {
	this.caseId=caseId;
}

AutoSaveDTO.prototype.getCaseId = function() {
	return this.caseId;
}
AutoSaveDTO.prototype.setAnswerSetId = function(answerSetId) {
	this.answerSetId=answerSetId;
}

AutoSaveDTO.prototype.getAnswerSetId = function() {
	return this.answerSetId;
}
AutoSaveDTO.prototype.setDepartmentId = function(departmentId) {
	this.departmentId=departmentId;
}

AutoSaveDTO.prototype.getDepartmentId = function() {
	return this.departmentId;
}
AutoSaveDTO.prototype.getAnswerDTO = function() {
	return this.answerDTO;
}
AutoSaveDTO.prototype.setAnswerDTO = function(answerDTO) {
	this.answerDTO=answerDTO;
}
