/**
 * BillSupportDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 27-Aug-2013
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
public class BillSupportDTO {
	private int id;
	private int billId;
	private float stdRate;
	private float netRate;
	private int supportId;
	private String supportName;
	private String supportDate;
	private int quantity;
	private int taxId;
	private String taxName;
	private float taxValue;
	private String taxType;
	private String actionName;
	private boolean success;
	private ErrorDTO error;
	/**
	 * 
	 */
	public BillSupportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param billId
	 * @param stdRate
	 * @param netRate
	 * @param supportId
	 * @param supportName
	 * @param quantity
	 * @param success
	 * @param error
	 */
	public BillSupportDTO(int id, int billId, float stdRate, float netRate,
			int supportId, String supportName,String supportDate, int quantity, boolean success,
			ErrorDTO error) {
		super();
		this.id = id;
		this.billId = billId;
		this.stdRate = stdRate;
		this.netRate = netRate;
		this.supportId = supportId;
		this.supportName = supportName;
		this.supportDate = supportDate;
		this.quantity = quantity;
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
	 * @return the billId
	 */
	public int getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}
	/**
	 * @return the stdRate
	 */
	public float getStdRate() {
		return stdRate;
	}
	/**
	 * @param stdRate the stdRate to set
	 */
	public void setStdRate(float stdRate) {
		this.stdRate = stdRate;
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
	 * @return the supportId
	 */
	public int getSupportId() {
		return supportId;
	}
	/**
	 * @param supportId the supportId to set
	 */
	public void setSupportId(int supportId) {
		this.supportId = supportId;
	}
	/**
	 * @return the supportName
	 */
	public String getSupportName() {
		return supportName;
	}
	/**
	 * @param supportName the supportName to set
	 */
	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the taxValue
	 */
	public float getTaxValue() {
		return taxValue;
	}
	/**
	 * @param taxValue the taxValue to set
	 */
	public void setTaxValue(float taxValue) {
		this.taxValue = taxValue;
	}
	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}
	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	/**
	 * @return the taxName
	 */
	public String getTaxName() {
		return taxName;
	}
	/**
	 * @param taxName the taxName to set
	 */
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	/**
	 * @return the taxType
	 */
	public String getTaxType() {
		return taxType;
	}
	/**
	 * @param taxType the taxType to set
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	/**
	 * @return the supportDate
	 */
	public String getSupportDate() {
		return supportDate;
	}
	/**
	 * @param supportDate the supportDate to set
	 */
	public void setSupportDate(String supportDate) {
		this.supportDate = supportDate;
	}
}
