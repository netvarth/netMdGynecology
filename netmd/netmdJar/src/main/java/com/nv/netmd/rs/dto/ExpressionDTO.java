/**
 * ExpressionDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 8, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class ExpressionDTO {
	private String name;
	private String value;
	private String operator;
	private String calculationType;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
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
}
