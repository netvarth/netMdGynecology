/**
* DoctorListAchievementDTO.java
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
public class DoctorListAchievementDTO {
	private List<DoctorAchievementDTO> achievement = new ArrayList<DoctorAchievementDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public DoctorListAchievementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param achievement
	 * @param error
	 * @param success
	 * @param count
	 */
	public DoctorListAchievementDTO(List<DoctorAchievementDTO> achievement,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.achievement = achievement;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the achievement
	 */
	public List<DoctorAchievementDTO> getAchievement() {
		return achievement;
	}
	/**
	 * @param achievement the achievement to set
	 */
	public void setAchievement(List<DoctorAchievementDTO> achievement) {
		this.achievement = achievement;
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
