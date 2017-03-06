function QuestionAnswerDTO() {

}

function QuestionAnswerDTO(caseId, departmentId,answerSetId,answerDTO) {
	this.caseId=caseId;
	this.departmentId=departmentId;
	this.answerSetId=answerSetId;
	this.answerDTO=answerDTO;
	
}
QuestionAnswerDTO.prototype.setCaseId = function(caseId) {
	this.caseId=caseId;
}

QuestionAnswerDTO.prototype.getCaseId = function() {
	return this.caseId;
}
QuestionAnswerDTO.prototype.setDepartmentId = function(departmentId) {
	this.departmentId=departmentId;
}
QuestionAnswerDTO.prototype.getDepartmentId = function() {
	return this.departmentId;
}
QuestionAnswerDTO.prototype.setAnswerSetId = function(answerSetId) {
	this.answerSetId=answerSetId;
}
QuestionAnswerDTO.prototype.getAnswerSetId = function() {
	return this.answerSetId;
}
QuestionAnswerDTO.prototype.getAnswerDTO = function() {
	return this.answerDTO;
}
QuestionAnswerDTO.prototype.setAnswerDTO = function(answerDTO) {
	this.answerDTO=answerDTO;
}
