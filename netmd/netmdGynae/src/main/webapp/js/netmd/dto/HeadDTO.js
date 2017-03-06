
function HeadDTO(id, headName,parentHeadName,description,parentHeadId,headtype) {
	this.id=id;
	this.headName=headName;
	this.parentHeadName=parentHeadName;
	this.description=description;
	this.parentHeadId=parentHeadId;
	this.headtype=headtype;
}

HeadDTO.prototype.getId = function() {
	return this.id;
}

HeadDTO.prototype.setId = function(id) {
	this.id=id;
}

HeadDTO.prototype.getHeadName = function() {
	return this.headName;
}

HeadDTO.prototype.setHeadName= function(headName) {
	this.headName=headName;
}

HeadDTO.prototype.getDescription = function() {
	return this.description;
}

HeadDTO.prototype.setDescription = function(description) {
	 this.description=description;
}
HeadDTO.prototype.getParentHeadName = function() {
	return this.parentHeadName;
}

HeadDTO.prototype.setParentHeadName = function(parentHeadName) {
	 this.parentHeadName=parentHeadName;
}
HeadDTO.prototype.getParentHeadId= function() {
	return this.parentHeadId;
}

HeadDTO.prototype.setParentHeadId = function(parentHeadId) {
	 this.parentHeadId=parentHeadId;
}
HeadDTO.prototype.getHeadType= function() {
	return this.headtype;
}

HeadDTO.prototype.setHeadType = function(headtype) {
	 this.headtype=headtype;
}

