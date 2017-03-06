/**
 * DiscountValueDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 09-Sep-2013
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
public class DiscountValueDTO {
	private int id;
	private float discValue;
	private float amount;
	/**
	 * @param id
	 * @param discValue
	 * @param amount
	 */
	public DiscountValueDTO(int id, float discValue, float amount) {
		super();
		this.id = id;
		this.discValue = discValue;
		this.amount = amount;
	}
	/**
	 * 
	 */
	public DiscountValueDTO() {
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
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
}