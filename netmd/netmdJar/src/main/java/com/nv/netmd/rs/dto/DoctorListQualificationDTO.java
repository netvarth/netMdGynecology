/**
* DoctorListQualificationDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 18, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class DoctorListQualificationDTO {
	private List<DoctorQualificationDTO> qualification = new ArrayList<DoctorQualificationDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public DoctorListQualificationDTO() {
		super();
	}
	/**
	 * @param qualification
	 * @param error
	 * @param success
	 * @param count
	 */
	public DoctorListQualificationDTO(
			List<DoctorQualificationDTO> qualification, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.qualification = qualification;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the qualification
	 */
	public List<DoctorQualificationDTO> getQualification() {
		return qualification;
	}
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(List<DoctorQualificationDTO> qualification) {
		this.qualification = qualification;
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
