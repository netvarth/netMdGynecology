/**
 * DoctorListResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 9, 2013
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
public class DoctorListResponseDTO {
	private List<ReferralDetails> referral = new ArrayList<ReferralDetails>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public DoctorListResponseDTO() {
		super();
	}

	/**
	 * @param referral
	 * @param error
	 * @param success
	 * @param count
	 */
	public DoctorListResponseDTO(List<ReferralDetails> referral,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.referral = referral;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	/**
	 * @return the referral
	 */
	public List<ReferralDetails> getReferral() {
		return referral;
	}

	/**
	 * @param referral
	 *            the referral to set
	 */
	public void setReferral(List<ReferralDetails> referral) {
		this.referral = referral;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
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
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
