/**
 * DiscountDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
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
public class DiscountDTO {
	private int id;
	private String name;
	private String description;
	private String discType;
	private float discValue;
	private String calculationType;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param discType
	 * @param discValue
	 * @param calculationType
	 */
	public DiscountDTO(int id, String name, String description,
			String discType, float discValue, String calculationType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.discType = discType;
		this.discValue = discValue;
		this.calculationType = calculationType;
	}
	/**
	 * 
	 */
	public DiscountDTO() {
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
	 * @return the discType
	 */
	public String getDiscType() {
		return discType;
	}
	/**
	 * @param discType the discType to set
	 */
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	/**
	 * @return the discValue
	 */
	public float getDiscValue() {
		return discValue;
	}
	/**
	 * @param discValue the discValue to set
	 */
	public void setDiscValue(float discValue) {
		this.discValue = discValue;
	}
	/**
	 * @return the calculationType
	 */
	public String getCalculationType() {
		return calculationType;
	}
	/**
	 * @param calculationType the calculationType to set
	 */
	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
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
