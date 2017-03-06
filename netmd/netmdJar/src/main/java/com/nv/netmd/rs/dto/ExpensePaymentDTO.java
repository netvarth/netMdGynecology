/**
 * BillItemDTO.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 04-Dec-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.Date;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class ExpensePaymentDTO {
	private int id;
	private int expenseId;
	private String paymentDate;
	private String paymentTime;
	private float AmountPaid;
	private boolean success;
	private ErrorDTO error;
	/**
	 * 
	 */
	public  ExpensePaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param expenseId
	 * @param paymentDate
	 * @param paymentTime
	 * @param amountPaid
	 * @param success
	 * @param error
	 */
	public ExpensePaymentDTO(int id, int expenseId, String paymentDate,
			String paymentTime, float amountPaid, boolean success,
			ErrorDTO error) {
		super();
		this.id = id;
		this.expenseId = expenseId;
		this.paymentDate = paymentDate;
		this.paymentTime = paymentTime;
		AmountPaid = amountPaid;
		this.success = success;
		this.error = error;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the expenseId
	 */
	public int getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return the paymentTime
	 */
	public String getPaymentTime() {
		return paymentTime;
	}
	/**
	 * @param paymentTime the paymentTime to set
	 */
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * @return the amountPaid
	 */
	public float getAmountPaid() {
		return AmountPaid;
	}
	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(float amountPaid) {
		AmountPaid = amountPaid;
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
}
