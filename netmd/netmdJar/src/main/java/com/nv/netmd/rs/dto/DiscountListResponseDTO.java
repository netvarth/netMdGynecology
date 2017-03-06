/**
 * DiscountListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
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
public class DiscountListResponseDTO {
	private List<DiscountDTO> discount = new ArrayList<DiscountDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param discount
	 * @param error
	 * @param success
	 * @param count
	 */
	public DiscountListResponseDTO(List<DiscountDTO> discount, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.discount = discount;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * 
	 */
	public DiscountListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the discount
	 */
	public List<DiscountDTO> getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(List<DiscountDTO> discount) {
		this.discount = discount;
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
