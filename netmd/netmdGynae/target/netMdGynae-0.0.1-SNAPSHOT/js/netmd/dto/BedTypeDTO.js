function BedTypeDTO() {

}

function BedTypeDTO(id,type,rent,count,taxId,taxName) {
	this.id=id;
	this.type=type;
	this.rent=rent;
	this.count=count;
	this.taxId=taxId;
	this.taxName=taxName;
}

BedTypeDTO.prototype.setId = function(id) {
	this.id=id;
}
BedTypeDTO.prototype.getId = function() {
	return this.id;
}
BedTypeDTO.prototype.setType = function(type) {
	this.type=type;
}
BedTypeDTO.prototype.getType = function() {
	return this.type;
}
BedTypeDTO.prototype.setCount = function(count) {
	 this.count=count;
}
BedTypeDTO.prototype.getCount = function() {
	return this.count;
}
BedTypeDTO.prototype.setRent = function(rent) {
	 this.rent=rent;
}
BedTypeDTO.prototype.getRent = function() {
	return this.rent;
}
BedTypeDTO.prototype.setTaxId = function(taxId) {
	this.taxId=taxId;
}
BedTypeDTO.prototype.getTaxId = function() {
	return this.taxId;
}

BedTypeDTO.prototype.setTaxName = function(taxName) {
	this.taxName=taxName;
}
BedTypeDTO.prototype.getTaxName= function() {
	return this.taxName;
}

