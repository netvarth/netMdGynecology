/**
* DoctorListExperienceDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 19, 2013
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
public class DoctorListExperienceDTO {
	private List<DoctorExperienceDTO> experience = new ArrayList<DoctorExperienceDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public DoctorListExperienceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param experience
	 * @param error
	 * @param success
	 * @param count
	 */
	public DoctorListExperienceDTO(List<DoctorExperienceDTO> experience,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.experience = experience;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the experience
	 */
	public List<DoctorExperienceDTO> getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(List<DoctorExperienceDTO> experience) {
		this.experience = experience;
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
