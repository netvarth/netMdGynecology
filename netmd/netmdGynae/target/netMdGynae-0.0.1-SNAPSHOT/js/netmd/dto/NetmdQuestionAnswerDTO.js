function NetmdQuestionAnswerDTO() {

}

function NetmdQuestionAnswerDTO(answerDTO) {
	this.answerDTO=answerDTO;
	
}

NetmdQuestionAnswerDTO.prototype.getAnswerDTO = function() {
	return this.answerDTO;
}
NetmdQuestionAnswerDTO.prototype.setAnswerDTO = function(answerDTO) {
	this.answerDTO=answerDTO;
}
NetmdQuestionAnswerDTO.prototype.getQuestionnaireId = function() {
	return this.answerSetId;
}
NetmdQuestionAnswerDTO.prototype.setQuestionnaireId = function(answerSetId) {
	this.answerSetId=answerSetId;
}
