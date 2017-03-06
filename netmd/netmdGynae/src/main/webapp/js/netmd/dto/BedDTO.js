function BedDTO() {

}

function BedDTO(id,bedNumber,bedType,bedTypeId,roomId,roomName,description) {
	this.id=id;
	this.bedNumber=bedNumber;
	this.bedType=bedType;
	this.bedTypeId=bedTypeId;
	this.roomId=roomId;
	this.roomName=roomName;
	this.description=description;
}

BedDTO.prototype.setId = function(id) {
	this.id=id;
}
BedDTO.prototype.getId = function() {
	return this.id;
}
BedDTO.prototype.setBedNumber = function(bedNumber) {
	this.bedNumber=bedNumber;
}
BedDTO.prototype.getBedNumber = function() {
	return this.bedNumber;
}
BedDTO.prototype.setBedType = function(bedType) {
	 this.bedType=bedType;
}
BedDTO.prototype.getBedType = function() {
	return this.bedType;
}
BedDTO.prototype.setBedTypeId = function(bedTypeId) {
	 this.bedTypeId=bedTypeId;
}
BedDTO.prototype.getBedTypeId = function() {
	return this.bedTypeId;
}
BedDTO.prototype.setRoomId = function(roomId) {
	 this.roomId=roomId;
}
BedDTO.prototype.getRoomId = function() {
	return this.roomId;
}

BedDTO.prototype.setRoomName = function(roomName) {
	 this.roomName=roomName;
}
BedDTO.prototype.getRoomName  = function() {
	return this.roomName;
}
BedDTO.prototype.getDescription = function() {
	return this.description;
}

BedDTO.prototype.setDescription = function(description) {
	 this.description=description;
}

