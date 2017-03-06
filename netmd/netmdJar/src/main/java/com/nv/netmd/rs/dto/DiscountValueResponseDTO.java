/**
 * DiscountValueResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 09-Sep-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;


/**
 *
 *
 * @author Sreeram T G
 */
public class DiscountValueResponseDTO {
	private float netRate;
	private float discountValue;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @param netRate
	 * @param discountValue
	 * @param error
	 * @param success
	 */
	public DiscountValueResponseDTO(float netRate, float discountValue,
			ErrorDTO error, boolean success) {
		super();
		this.netRate = netRate;
		this.discountValue = discountValue;
		this.error = error;
		this.success = success;
	}
	/**
	 * 
	 */
	public DiscountValueResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the netRate
	 */
	public float getNetRate() {
		return netRate;
	}
	/**
	 * @param netRate the netRate to set
	 */
	public void setNetRate(float netRate) {
		this.netRate = netRate;
	}
	/**
	 * @return the discountValue
	 */
	public float getDiscountValue() {
		return discountValue;
	}
	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(float discountValue) {
		this.discountValue = discountValue;
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
}
