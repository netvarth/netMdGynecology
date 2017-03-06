function RoomDTO() {

}

function RoomDTO(id,roomNumber,roomTypeId,roomTypeName,blockId,blockName,departmentId,departmentName,description) {
	this.id=id;
	this.roomNumber=roomNumber;
	this.roomTypeId=roomTypeId;
	this.roomTypeName=roomTypeName;
	this.blockId=blockId;
	this.blockName=blockName;
	this.departmentId=departmentId;
	this.departmentName=departmentName;
	this.description=description;
}

RoomDTO.prototype.setId = function(id) {
	this.id=id;
}
RoomDTO.prototype.getId = function() {
	return this.id;
}
RoomDTO.prototype.setRoomNumber = function(roomNumber) {
	this.roomNumber=roomNumber;
}
RoomDTO.prototype.getRoomNumber = function() {
	return this.roomNumber;
}
RoomDTO.prototype.setRoomTypeId = function(roomTypeId) {
	 this.roomTypeId=roomTypeId;
}
RoomDTO.prototype.getRoomTypeId = function() {
	return this.roomTypeId;
}

RoomDTO.prototype.setRoomTypeName = function(roomTypeName) {
	 this.roomTypeName=roomTypeName;
}
RoomDTO.prototype.getRoomTypeName = function() {
	return this.roomTypeName;
}

RoomDTO.prototype.setBlockId = function(blockId) {
	 this.blockId=blockId;
}
RoomDTO.prototype.getBlockId  = function() {
	return this.blockId;
}
RoomDTO.prototype.setBlockName = function(blockName) {
	 this.blockName=blockName;
}
RoomDTO.prototype.getBlockName  = function() {
	return this.blockName;
}

RoomDTO.prototype.setDepartmentId = function(departmentId) {
	 this.departmentId=departmentId;
}
RoomDTO.prototype.getDepartmentId  = function() {
	return this.departmentId;
}

RoomDTO.prototype.setDepartmentName = function(departmentName) {
	 this.departmentName=departmentName;
}
RoomDTO.prototype.getDepartmentName  = function() {
	return this.departmentName;
}

RoomDTO.prototype.setDescription = function(description) {
	 this.description=description;
}
RoomDTO.prototype.getDescription  = function() {
	return this.description;
}


