/**
 * BillSummaryDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 30-Sep-2013
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
public class BillSummaryDTO {
private int globalId;
private String uid;
private String payStatus;
private String billStatus;
private String patientName;
private int patientGlobalId;
private float billAmount;
private float amountPaid;
private float amountDue;
private String orderDate;
private boolean success;
private ErrorDTO error;
/**
 * 
 */
public BillSummaryDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param globalId
 * @param uid
 * @param payStatus
 * @param patientName
 * @param patientGlobalId
 * @param billAmount
 * @param amountPaid
 * @param orderDate
 * @param success
 * @param error
 */
public BillSummaryDTO(int globalId, String uid, String payStatus,String billStatus,
		String patientName, int patientGlobalId, float billAmount,
		float amountPaid, String orderDate, boolean success, ErrorDTO error) {
	super();
	this.globalId = globalId;
	this.uid = uid;
	this.payStatus = payStatus;
	this.billStatus = billStatus;
	this.patientName = patientName;
	this.patientGlobalId = patientGlobalId;
	this.billAmount = billAmount;
	this.amountPaid = amountPaid;
	this.orderDate = orderDate;
	this.success = success;
	this.error = error;
}
/**
 * @return the globalId
 */
public int getGlobalId() {
	return globalId;
}
/**
 * @param globalId the globalId to set
 */
public void setGlobalId(int globalId) {
	this.globalId = globalId;
}
/**
 * @return the uid
 */
public String getUid() {
	return uid;
}
/**
 * @param uid the uid to set
 */
public void setUid(String uid) {
	this.uid = uid;
}
/**
 * @return the payStatus
 */
public String getPayStatus() {
	return payStatus;
}
/**
 * @return the amountDue
 */
public float getAmountDue() {
	return amountDue;
}
/**
 * @param amountDue the amountDue to set
 */
public void setAmountDue(float amountDue) {
	this.amountDue = amountDue;
}
/**
 * @param payStatus the payStatus to set
 */
public void setPayStatus(String payStatus) {
	this.payStatus = payStatus;
}
/**
 * @return the patientName
 */
public String getPatientName() {
	return patientName;
}
/**
 * @param patientName the patientName to set
 */
public void setPatientName(String patientName) {
	this.patientName = patientName;
}

/**
 * @return the billAmount
 */
public float getBillAmount() {
	return billAmount;
}
/**
 * @param billAmount the billAmount to set
 */
public void setBillAmount(float billAmount) {
	this.billAmount = billAmount;
}
/**
 * @return the amountPaid
 */
public float getAmountPaid() {
	return amountPaid;
}
/**
 * @param amountPaid the amountPaid to set
 */
public void setAmountPaid(float amountPaid) {
	this.amountPaid = amountPaid;
}
/**
 * @return the orderDate
 */
public String getOrderDate() {
	return orderDate;
}
/**
 * @param orderDate the orderDate to set
 */
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
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
/**
 * @return the patientGlobalId
 */
public int getPatientGlobalId() {
	return patientGlobalId;
}
/**
 * @param patientGlobalId the patientGlobalId to set
 */
public void setPatientGlobalId(int patientGlobalId) {
	this.patientGlobalId = patientGlobalId;
}
public String getBillStatus() {
	return billStatus;
}
public void setBillStatus(String billStatus) {
	this.billStatus = billStatus;
}

}
