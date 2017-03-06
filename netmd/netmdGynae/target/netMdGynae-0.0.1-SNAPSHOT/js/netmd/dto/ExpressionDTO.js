function ExpressionDTO() {
}

function ExpressionDTO(name,value,operator,calculationType) {
	this.name = name;
	this.value= value;
	this.operator=operator;
	this.calculationType=calculationType;
}

ExpressionDTO.setValue = function(value) {
	this.value=value;
}

ExpressionDTO.setName = function(name) {
	this.name=name;
}

ExpressionDTO.setOperator= function(operator) {
	this.operator=operator;
}