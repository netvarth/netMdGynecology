/**
 * ItemDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
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
public class ItemDTO {
	private int id;
	private String name;
	private float price;
	private int quantity;
	private String description;
	private int taxId;
	private String taxName;
	private String status;
	private float taxValue;
	private String taxType;
	private float stdRate;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param quantity
	 * @param description
	 * @param taxId
	 * @param taxName
	 * @param status
	 */
	public ItemDTO(int id, String name, float price, int quantity,
			String description, int taxId, String taxName, String status) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.taxId = taxId;
		this.taxName = taxName;
		this.status = status;
	}
	/**
	 * 
	 */
	public ItemDTO() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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

}
