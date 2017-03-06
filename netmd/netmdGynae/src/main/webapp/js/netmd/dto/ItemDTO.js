function ItemDTO() {

}

function ItemDTO(id,name,price,quantity,taxId,taxName,description) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
		this.taxId=taxId;
		this.taxName=taxName;
		this.description=description;

}

ItemDTO.prototype.setId = function(id) {
	this.id=id;
}
ItemDTO.prototype.getId = function() {
	return this.id;
}

ItemDTO.prototype.setName = function(name) {
	this.name=name;
}
ItemDTO.prototype.getName = function() {
	return this.name;
}

ItemDTO.prototype.setDescription = function(description) {
	this.description=description;
}
ItemDTO.prototype.getDescription = function() {
	return this.description;
}

ItemDTO.prototype.setPrice = function(price) {
	this.price=price;
}
ItemDTO.prototype.getPrice = function() {
	return this.price;
}

ItemDTO.prototype.setQuantity= function(quantity) {
	this.quantity=quantity;
}
ItemDTO.prototype.getQuantity = function() {
	return this.quantity;
}

ItemDTO.prototype.setTaxId = function(taxId) {
	this.taxId=taxId;
}
ItemDTO.prototype.getTaxId = function() {
	return this.taxId;
}

ItemDTO.prototype.setTaxName = function(taxName) {
	this.taxName=taxName;
}
ItemDTO.prototype.getTaxName= function() {
	return this.taxName;
}