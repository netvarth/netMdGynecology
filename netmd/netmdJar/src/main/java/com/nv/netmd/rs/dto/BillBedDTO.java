/**
 * BillBedDTO.java
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
public class BillBedDTO {
	private int id;
	private int billId;
	private float stdRate;
	private float netRate;
	private int bedId;
	private int quantity;
	private String bedName;
	private int taxId;
	private String taxName;
	private float taxValue;
	private String taxType;
	private String actionName;
	private boolean success;
	/**
	 * @param id
	 * @param billId
	 * @param stdRate
	 * @param netRate
	 * @param bedId
	 * @param quantity
	 * @param bedName
	 * @param success
	 */
	public BillBedDTO(int id, int billId, float stdRate, float netRate,
			int bedId, int quantity, String bedName, boolean success) {
		super();
		this.id = id;
		this.billId = billId;
		this.stdRate = stdRate;
		this.netRate = netRate;
		this.bedId = bedId;
		this.quantity = quantity;
		this.bedName = bedName;
		this.success = success;
	}
	/**
	 * 
	 */
	public BillBedDTO() {
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
	 * @return the bedId
	 */
	public int getBedId() {
		return bedId;
	}
	/**
	 * @param bedId the bedId to set
	 */
	public void setBedId(int bedId) {
		this.bedId = bedId;
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
	 * @return the bedName
	 */
	public String getBedName() {
		return bedName;
	}
	/**
	 * @param bedName the bedName to set
	 */
	public void setBedName(String bedName) {
		this.bedName = bedName;
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
}
