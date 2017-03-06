 /**
* HeadListResponseDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 24, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nithesh Mohanan
 *
 */
public class PaymentListResponseDTO {
	
	private List<ExpenseDTO> paymentList = new ArrayList<ExpenseDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public PaymentListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param paymentList
	 * @param error
	 * @param success
	 * @param count
	 */
	public PaymentListResponseDTO(List<ExpenseDTO> paymentList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.paymentList = paymentList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the paymentList
	 */
	public List<ExpenseDTO> getpaymentList() {
		return paymentList;
	}
	/**
	 * @param paymentList the paymentList to set
	 */
	public void setpaymentList(List<ExpenseDTO> paymentList) {
		this.paymentList = paymentList;
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