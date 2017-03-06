function DiscountDTO() {

}

function DiscountDTO(id,name,description,discType,discValue,calculationType) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.discType=discType;
		this.discValue=discValue;
		this.calculationType=calculationType;
}

DiscountDTO.prototype.setId = function(id) {
	this.id=id;
}
DiscountDTO.prototype.getId = function() {
	return this.id;
}

DiscountDTO.prototype.setName = function(name) {
	this.name=name;
}
DiscountDTO.prototype.getName = function() {
	return this.name;
}

DiscountDTO.prototype.setDescription = function(description) {
	this.description=description;
}
DiscountDTO.prototype.getDescription = function() {
	return this.description;
}

DiscountDTO.prototype.setDiscType = function(discType) {
	this.discType=discType;
}
DiscountDTO.prototype.getDiscType = function() {
	return this.discType;
}

DiscountDTO.prototype.setDiscValue = function(discValue) {
	this.discValue=discValue;
}
DiscountDTO.prototype.getDiscValue = function() {
	return this.discValue;
}

DiscountDTO.prototype.setCalculationType = function(calculationType) {
	this.calculationType=calculationType;
}
DiscountDTO.prototype.getCalculationType = function() {
	return this.calculationType;
}