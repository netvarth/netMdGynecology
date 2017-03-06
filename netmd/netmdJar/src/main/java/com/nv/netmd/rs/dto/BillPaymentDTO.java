/**
 * BillPaymentDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 11-Sep-2013
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
public class BillPaymentDTO {
private String billUid;
private float paidAmount;
private String paymentDate;
private String note;
/**
 * @param billUid
 * @param paidAmount
 *  @param paymentDate
 * @param note
 */
public BillPaymentDTO(String billUid, float paidAmount,String paymentDate,  String note) {
	super();
	this.billUid = billUid;
	this.paidAmount = paidAmount;
	this.paymentDate = paymentDate;
	this.note = note;
}
/**
 * 
 */
public BillPaymentDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @return the billUid
 */
public String getBillUid() {
	return billUid;
}
/**
 * @param billUid the billUid to set
 */
public void setBillUid(String billUid) {
	this.billUid = billUid;
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
}
