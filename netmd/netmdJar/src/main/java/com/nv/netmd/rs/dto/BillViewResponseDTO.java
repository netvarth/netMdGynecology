/**
 * BillViewResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 03-Sep-2013
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
public class BillViewResponseDTO {
	private int id;
	private String uid;
	private PatientDetail patient;
	private int referralId;
	private String referralName;
	private String origin;
	private String paymentStatus;
	private String currency;
	private String notes;
	private String billDate;
	private String billTime;
	private List<BillItemDTO> item=new ArrayList<BillItemDTO>();	
	private List<BillDiscountDTO> discount=new ArrayList<BillDiscountDTO>();
	private List<BillSupportDTO> support=new ArrayList<BillSupportDTO>();	
	private List<BillBedDTO> bed=new ArrayList<BillBedDTO>();
	private List<BillPaymentDTO> payment=new ArrayList<BillPaymentDTO>();
	private float billDiscount;
	private float grandTotal;
	private float amountPaid;
	private float billAmount;
	private float balanceAmount;
	private String billStatus;
	private boolean success;
	private ErrorDTO error;
	/**
	 * 
	 */
	public BillViewResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the patient
	 */
	public PatientDetail getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PatientDetail patient) {
		this.patient = patient;
	}
	/**
	 * @return the referralId
	 */
	public int getReferralId() {
		return referralId;
	}
	/**
	 * @param referralId the referralId to set
	 */
	public void setReferralId(int referralId) {
		this.referralId = referralId;
	}
	/**
	 * @return the referralName
	 */
	public String getReferralName() {
		return referralName;
	}
	/**
	 * @param referralName the referralName to set
	 */
	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the item
	 */
	public List<BillItemDTO> getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(List<BillItemDTO> item) {
		this.item = item;
	}
	/**
	 * @return the discount
	 */
	public List<BillDiscountDTO> getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(List<BillDiscountDTO> discount) {
		this.discount = discount;
	}
	/**
	 * @return the support
	 */
	public List<BillSupportDTO> getSupport() {
		return support;
	}
	/**
	 * @param support the support to set
	 */
	public void setSupport(List<BillSupportDTO> support) {
		this.support = support;
	}
	/**
	 * @return the bed
	 */
	public List<BillBedDTO> getBed() {
		return bed;
	}
	/**
	 * @param bed the bed to set
	 */
	public void setBed(List<BillBedDTO> bed) {
		this.bed = bed;
	}
	/**
	 * @return the grandTotal
	 */
	public float getGrandTotal() {
		return grandTotal;
	}
	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
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
	 * @return the billDiscount
	 */
	public float getBillDiscount() {
		return billDiscount;
	}
	/**
	 * @param billDiscount the billDiscount to set
	 */
	public void setBillDiscount(float billDiscount) {
		this.billDiscount = billDiscount;
	}
	/**
	 * @return the balanceAmount
	 */
	public float getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	/**
	 * @return the billStatus
	 */
	public String getBillStatus() {
		return billStatus;
	}
	/**
	 * @param billStatus the billStatus to set
	 */
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the billTime
	 */
	public String getBillTime() {
		return billTime;
	}
	/**
	 * @param billTime the billTime to set
	 */
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	/**
	 * @return the payment
	 */
	public List<BillPaymentDTO> getPayment() {
		return payment;
	}
	/**
	 * @param payment the payment to set
	 */
	public void setPayment(List<BillPaymentDTO> payment) {
		this.payment = payment;
	}

}
