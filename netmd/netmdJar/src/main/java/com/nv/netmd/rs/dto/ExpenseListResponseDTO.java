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
public class ExpenseListResponseDTO {
	
	private List<ExpenseDTO> expenseList = new ArrayList<ExpenseDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public ExpenseListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param expenseList
	 * @param error
	 * @param success
	 * @param count
	 */
	public ExpenseListResponseDTO(List<ExpenseDTO> expenseList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.expenseList = expenseList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the expenseList
	 */
	public List<ExpenseDTO> getExpenseList() {
		return expenseList;
	}
	/**
	 * @param expenseList the expenseList to set
	 */
	public void setExpenseList(List<ExpenseDTO> expenseList) {
		this.expenseList = expenseList;
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