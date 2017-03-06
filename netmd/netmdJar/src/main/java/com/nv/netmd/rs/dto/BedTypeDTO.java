/**
 * BedTypeDTO.java
 * @author Leo
 *
 * Version 1.0 Jul 31, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

/**
 *
 *
 * @author Leonora Louis
 */
public class BedTypeDTO {
	private int id;
	private String type;
	private float rent;
	private int count;
	private String status;
	private int taxId;
	private String taxName;
	private float taxValue;
	private String taxType;
	private float stdRate;
	private ErrorDTO error;
	private boolean success;



	/**
	 * @param id
	 * @param type
	 * @param rent
	 * @param count
	 * @param status
	 */
	public BedTypeDTO(int id, String type, float rent, int count, String status
			) {
		super();
		this.id = id;
		this.type = type;
		this.rent = rent;
		this.count = count;
		this.status=status;

	}



	/**
	 * 
	 */
	public BedTypeDTO() {
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return the rent
	 */
	public float getRent() {
		return rent;
	}



	/**
	 * @param rent the rent to set
	 */
	public void setRent(float rent) {
		this.rent = rent;
	}



	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}



	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
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
}