function SupportDTO() {

}

function SupportDTO(id,name,price,taxId,taxName,description) {
	this.id=id;
	this.name=name;
	this.price=price;
	this.taxId=taxId;
	this.taxName=taxName;
	this.description=description;
}

SupportDTO.prototype.setId = function(id) {
	this.id=id;
}
SupportDTO.prototype.getId = function() {
	return this.id;
}
SupportDTO.prototype.setName = function(name) {
	this.name=name;
}
SupportDTO.prototype.getName = function() {
	return this.name;
}
SupportDTO.prototype.setPrice= function(price) {
	 this.price=price;
}
SupportDTO.prototype.getPrice = function() {
	return this.price;
}
SupportDTO.prototype.setTaxId = function(taxId) {
	 this.taxId=taxId;
}
SupportDTO.prototype.getTaxId = function() {
	return this.taxId;
}

SupportDTO.prototype.setTaxName = function(taxName) {
	 this.taxName=taxName;
}
SupportDTO.prototype.getTaxName = function() {
	return this.taxName;
}

SupportDTO.prototype.setDescription = function(description) {
	 this.description=description;
}
SupportDTO.prototype.getDescription  = function() {
	return this.description;
}

