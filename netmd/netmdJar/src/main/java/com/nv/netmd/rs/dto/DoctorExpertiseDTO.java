/**
 * DoctorExpertiseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 15, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class DoctorExpertiseDTO {
	private int id;
	private int doctorId;
	private String expertise;
	private String actionName;

	/**
	 * 
	 */
	public DoctorExpertiseDTO() {
		super();
	}

	

	/**
	 * @param id
	 * @param doctorId
	 * @param expertise
	 * @param actionName
	 */
	public DoctorExpertiseDTO(int id, int doctorId, String expertise,
			String actionName) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.expertise = expertise;
		this.actionName = actionName;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}

	/**
	 * @param expertise
	 *            the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}



	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}



	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
}
