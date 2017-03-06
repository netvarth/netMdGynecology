/**
 * TaxDTO.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
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
public class TaxDTO {
	private int id;
	private String name;
	private String description;
	private float taxVal;
	private String calculationType;
	
	
	
	public TaxDTO(int id, String name, String description, float taxVal,
			String calculationType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.taxVal = taxVal;
		this.calculationType = calculationType;
	}
	public TaxDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getTaxVal() {
		return taxVal;
	}
	public void setTaxVal(float taxVal) {
		this.taxVal = taxVal;
	}
	public String getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

}
