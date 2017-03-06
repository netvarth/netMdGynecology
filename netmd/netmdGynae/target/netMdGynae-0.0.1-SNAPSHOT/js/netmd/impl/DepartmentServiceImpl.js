function DepartmentServiceImpl () {
	
	this.setTableValues = function(tableObj, departmentResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(departmentResult.department) {
			if(departmentResult.department.length>0) {			
				$j(departmentResult.department).each(function(index, department) {
					var id=department.id;
					var rowData=$j(tableObj).dataTable().fnAddData([id,department.departmentName]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","departmentIdCol Ustyle");
				});
			}		
		} 
	}
	
}

DepartmentServiceImpl.prototype.createDepartment=function (departmentObj) {
	ajaxProcessor.setUrl(constants.DEPARTMENTCREATEURL);
	return ajaxProcessor.post(departmentObj);
}
DepartmentServiceImpl.prototype.updateDepartment=function(departmentObj) {
	ajaxProcessor.setUrl(constants.DEPARTMENTUPDATEURL);
	return ajaxProcessor.post(departmentObj);
}
DepartmentServiceImpl.prototype.deleteDepartment=function(departmentId) {
	ajaxProcessor.setUrl(constants.DEPARTMENTDELETEURL + departmentId);
	return ajaxProcessor.get();
}
DepartmentServiceImpl.prototype.viewDepartment=function(departmentId) {
	ajaxProcessor.setUrl(constants.DEPARTMENTVIEWURL + departmentId);
	return ajaxProcessor.get();
}