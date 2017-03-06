/**
 * DoctorMembershipDTO.java
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
public class DoctorMembershipDTO {
	private int id;
	private int doctorId;
	private String membership;
	private String actionName;

	/**
	 * 
	 */
	public DoctorMembershipDTO() {
		super();
	}

	

	/**
	 * @param id
	 * @param doctorId
	 * @param membership
	 * @param actionName
	 */
	public DoctorMembershipDTO(int id, int doctorId, String membership,
			String actionName) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.membership = membership;
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
	 * @return the membership
	 */
	public String getMembership() {
		return membership;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(String membership) {
		this.membership = membership;
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
