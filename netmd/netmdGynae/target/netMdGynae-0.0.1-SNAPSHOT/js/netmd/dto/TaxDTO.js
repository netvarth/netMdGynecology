function TaxDTO() {

}

function TaxDTO(id,name,calculationType,taxVal,description) {
	this.id=id;
	this.name=name;
	this.calculationType=calculationType;
	this.taxVal=taxVal;
	this.description=description;
}

TaxDTO.prototype.setId = function(id) {
	this.id=id;
}
TaxDTO.prototype.getId = function() {
	return this.id;
}
TaxDTO.prototype.setName = function(name) {
	this.name=name;
}
TaxDTO.prototype.getName = function() {
	return this.name;
}
TaxDTO.prototype.setTaxVal = function(taxVal) {
	 this.taxVal=taxVal;
}
TaxDTO.prototype.getTaxVal = function() {
	return this.taxVal;
}

TaxDTO.prototype.setCalculationType = function(calculationType) {
	 this.calculationType=calculationType;
}
TaxDTO.prototype.getCalculationType = function() {
	return this.calculationType;
}

TaxDTO.prototype.setDescription = function(description) {
	 this.description=description;
}
TaxDTO.prototype.getDescription  = function() {
	return this.description;
}

