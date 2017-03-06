/**
* DoctorListMembershipDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 21, 2013
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
public class DoctorListMembershipDTO {
	private List<DoctorMembershipDTO> membership = new ArrayList<DoctorMembershipDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public DoctorListMembershipDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param membership
	 * @param error
	 * @param success
	 * @param count
	 */
	public DoctorListMembershipDTO(List<DoctorMembershipDTO> membership,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.membership = membership;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the membership
	 */
	public List<DoctorMembershipDTO> getMembership() {
		return membership;
	}
	/**
	 * @param membership the membership to set
	 */
	public void setMembership(List<DoctorMembershipDTO> membership) {
		this.membership = membership;
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
