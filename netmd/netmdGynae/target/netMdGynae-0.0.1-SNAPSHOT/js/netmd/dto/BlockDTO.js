function BlockDTO() {

}

function BlockDTO(id, name,description,departmentDTO) {
	this.id=id;
	this.name=name;
	this.description=description;
	this.departmentDTO=departmentDTO;
}

BlockDTO.prototype.setId = function(id) {
	this.id=id;
}

BlockDTO.prototype.setName = function(name) {
	this.name=name;
}

BlockDTO.prototype.getId = function() {
	return this.id;
}

BlockDTO.prototype.getName = function() {
	return this.name;
}
BlockDTO.prototype.getDescription = function() {
	return this.description;
}

BlockDTO.prototype.setDescription = function(description) {
	 this.description=description;
}
BlockDTO.prototype.getDepartmentDTO = function() {
	return this.departmentDTO;
}

BlockDTO.prototype.setDepartmentDTO = function(departmentDTO) {
	 this.departmentDTO=departmentDTO;
}