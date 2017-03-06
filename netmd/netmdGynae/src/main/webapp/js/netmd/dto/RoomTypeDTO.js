function RoomTypeDTO() {

}

function RoomTypeDTO(id,type,rent,count,noOfBeds) {
	this.id=id;
	this.type=type;
	this.rent=rent;
	this.count=count;
	this.noOfBeds=noOfBeds;
}

RoomTypeDTO.prototype.setId = function(id) {
	this.id=id;
}
RoomTypeDTO.prototype.getId = function() {
	return this.id;
}
RoomTypeDTO.prototype.setType = function(type) {
	this.type=type;
}
RoomTypeDTO.prototype.getType = function() {
	return this.type;
}
RoomTypeDTO.prototype.setCount = function(count) {
	 this.count=count;
}
RoomTypeDTO.prototype.getCount = function() {
	return this.count;
}

RoomTypeDTO.prototype.setRent = function(rent) {
	 this.rent=rent;
}
RoomTypeDTO.prototype.getRent = function() {
	return this.count;
}

RoomTypeDTO.prototype.setNoOfBeds = function(noOfBeds) {
	 this.noOfBeds=noOfBeds;
}
RoomTypeDTO.prototype.getNoOfBeds  = function() {
	return this.noOfBeds;
}

