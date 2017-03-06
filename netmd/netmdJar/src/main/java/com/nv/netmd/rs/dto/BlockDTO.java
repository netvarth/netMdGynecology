/**
 * BlockDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Sreeram T G
 */
public class BlockDTO {
private int id;
private String name;
private String description;
List<DepartmentDTO> departmentDTO=new ArrayList<DepartmentDTO>();
private String status;
private ErrorDTO error;
private boolean success;


/**
 * @param id
 * @param name
 * @param description
 * @param status
 */
public BlockDTO(int id, String name, String description, String status) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.status = status;
}
/**
 * 
 */
public BlockDTO() {
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
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
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
 * @return the departmentDTO
 */
public List<DepartmentDTO> getDepartmentDTO() {
	return departmentDTO;
}
/**
 * @param departmentDTO the departmentDTO to set
 */
public void setDepartmentDTO(List<DepartmentDTO> departmentDTO) {
	this.departmentDTO = departmentDTO;
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
