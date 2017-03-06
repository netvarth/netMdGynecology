/**
 * BillDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 26-Aug-2013
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
public class BillDTO {
	private int patientId;
	private String patientName;
	private String referralName;
	private String origin;
	private String paymentStatus;
	private String currency;
	private int id;
	private String uid;
	private List<BillItemDTO> item=new ArrayList<BillItemDTO>();		
    private List<BillDiscountDTO> discount=new ArrayList<BillDiscountDTO>();
	private List<BillSupportDTO> support=new ArrayList<BillSupportDTO>();	
	private List<BillBedDTO> bed=new ArrayList<BillBedDTO>();	
	private String notes;
	private String orderDate;
	private String collectedAt;
	private float grandTotal;
	private float amountPaid;
	private float billAmount;
	private float billDiscount;
	private String billStatus;
	private boolean success;
	private ErrorDTO error;
	
	
	/**
	 * @param patientId
	 * @param patientName
	 * @param origin
	 * @param paymentStatus
	 * @param id
	 * @param uid
	 * @param orderDate	
	 * @param grandTotal	
	 * @param amountPaid 
	 * @param billAmount
	 */
	public BillDTO(int patientId, String patientName, String origin,
			String paymentStatus, int id, String uid, String orderDate,
			 float grandTotal,float amountPaid,
			float billAmount,String billStatus) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.origin = origin;
		this.paymentStatus = paymentStatus;
		this.id = id;
		this.uid = uid;
		this.orderDate = orderDate;
		this.grandTotal = grandTotal;	
		this.amountPaid=amountPaid;
		this.billAmount = billAmount;
		this.billStatus=billStatus;
	}
	/**
	 * 
	 */
	public BillDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
	 * @return the collectedAt
	 */
	public String getCollectedAt() {
		return collectedAt;
	}
	/**
	 * @param collectedAt the collectedAt to set
	 */
	public void setCollectedAt(String collectedAt) {
		this.collectedAt = collectedAt;
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
	
	
}
