 /**
* ExpenseDTO.java
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

import java.util.Date;

import com.nv.netmd.pl.entity.PayStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;

/**
 * @author Nithesh Mohanan
 *
 */
public class ExpenseDTO {
	
	private int id;
	private int headId;
	private String expenseName;
	private String headName;
	private float totalAmount;
	private String description;
	private float paidAmount;
	private float balance;
	private PayStatusEnum paymentStatus;
	private String status;
	private String note;
	private Date expenseDate;
	private ErrorDTO error;
	private boolean success;
	/**
	 * 
	 */
	public ExpenseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param headId
	 * @param expenseName
	 * @param headName
	 * @param totalAmount
	 * @param description
	 * @param paidAmount
	 * @param balance
	 * @param paymentStatus
	 * @param status
	 * @param note
	 * @param expenseDate
	 * @param error
	 * @param success
	 */
	public ExpenseDTO(int id, int headId, String expenseName, String headName,
			float totalAmount, String description, float paidAmount,
			float balance, PayStatusEnum paymentStatus, String status,
			String note,Date expenseDate, ErrorDTO error, boolean success) {
		super();
		this.id = id;
		this.headId = headId;
		this.expenseName = expenseName;
		this.headName = headName;
		this.totalAmount = totalAmount;
		this.description = description;
		this.paidAmount = paidAmount;
		this.balance = balance;
		this.paymentStatus = paymentStatus;
		this.status = status;
		this.note = note;
		this.expenseDate=expenseDate;
		this.error = error;
		this.success = success;
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
	 * @return the headId
	 */
	public int getHeadId() {
		return headId;
	}
	/**
	 * @param headId the headId to set
	 */
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	/**
	 * @return the expenseName
	 */
	public String getExpenseName() {
		return expenseName;
	}
	/**
	 * @param expenseName the expenseName to set
	 */
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	/**
	 * @return the headName
	 */
	public String getHeadName() {
		return headName;
	}
	/**
	 * @param headName the headName to set
	 */
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the paidAmount
	 */
	public float getPaidAmount() {
		return paidAmount;
	}
	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}
	/**
	 * @return the paymentStatus
	 */
	public PayStatusEnum getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(PayStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
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
	 * @return the expenseDate
	 */
	public Date getExpenseDate() {
		return expenseDate;
	}
	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
