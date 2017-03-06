/**
* DepartmentDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Jul 20, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class DepartmentDTO {
private int id;
private String departmentName;
private String description;
private String status;
private ErrorDTO error;
private boolean success;



/**
 * @param id
 * @param departmentName
 * @param description
 * @param status
 */
public DepartmentDTO(int id, String departmentName, String description,
		String status) {
	super();
	this.id = id;
	this.departmentName = departmentName;
	this.description = description;
	this.status = status;
}
/**
 * 
 */
public DepartmentDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the departmentName
 */
public String getDepartmentName() {
	return departmentName;
}
/**
 * @param departmentName the departmentName to set
 */
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @return the error
 */
public ErrorDTO getError() {
	return error;
}
/**
 * @param error the error to set
 */
public void setError(ErrorDTO error) {
	this.error = error;
}
/**
 * @return the success
 */
public boolean isSuccess() {
	return success;
}
/**
 * @param success the success to set
 */
public void setSuccess(boolean success) {
	this.success = success;
}
/**
 * @return the status
 */
public String getStatus() {
	return status;
}
/**
 * @param status the status to set
 */
public void setStatus(String status) {
	this.status = status;
}

}
