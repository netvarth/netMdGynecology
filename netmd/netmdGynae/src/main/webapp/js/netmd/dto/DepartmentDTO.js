function DepartmentDTO() {

}

function DepartmentDTO(id, departmentName,description) {
	this.id=id;
	this.departmentName=departmentName;
	this.description=description;
}

DepartmentDTO.prototype.setId = function(id) {
	this.id=id;
}

DepartmentDTO.prototype.setDepartmentName = function(departmentName) {
	this.departmentName=departmentName;
}

DepartmentDTO.prototype.setDescription = function(description) {
	 this.description=description;
}

DepartmentDTO.prototype.getId = function() {
	return this.id;
}

DepartmentDTO.prototype.getDepartmentName = function() {
	return this.departmentName;
}
DepartmentDTO.prototype.getDescription = function() {
	return this.description;
}

