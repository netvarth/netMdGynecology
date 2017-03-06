function AnswerDTO() {

}

function AnswerDTO(id, key,value) {
	this.id=id;
	this.key=key;
	this.value=value;
	
}
AnswerDTO.prototype.setId = function(id) {
	this.id=id;
}

AnswerDTO.prototype.getId = function() {
	return this.id;
}
AnswerDTO.prototype.setKey = function(key) {
	this.key=key;
}
AnswerDTO.prototype.getKey = function() {
	return this.key;
}
AnswerDTO.prototype.getValue = function() {
	return this.value;
}
AnswerDTO.prototype.setValue = function(value) {
	this.value=value;
}
