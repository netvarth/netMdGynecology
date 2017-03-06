/**
 * DepartmentListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
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
public class DepartmentListResponseDTO {
	private List<DepartmentDTO> department = new ArrayList<DepartmentDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public DepartmentListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param department
	 * @param error
	 * @param success
	 * @param count
	 */
	public DepartmentListResponseDTO(List<DepartmentDTO> department,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.department = department;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the department
	 */
	public List<DepartmentDTO> getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(List<DepartmentDTO> department) {
		this.department = department;
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
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
