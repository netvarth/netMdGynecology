/**
 * DoctorQualificationDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 10, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

public class DoctorQualificationDTO {
	private int id;
	private int doctorId;
	private String educationalDegree;
	private String passedOutDate;
	private String institution;
	private String actionName;

	public DoctorQualificationDTO() {
		super();
	}

	
	/**
	 * @param id
	 * @param doctorId
	 * @param educationalDegree
	 * @param passedOutDate
	 * @param institution
	 * @param actionName
	 */
	public DoctorQualificationDTO(int id, int doctorId,
			String educationalDegree, String passedOutDate, String institution,
			String actionName) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.educationalDegree = educationalDegree;
		this.passedOutDate = passedOutDate;
		this.institution = institution;
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
	 * @return the educationalDegree
	 */
	public String getEducationalDegree() {
		return educationalDegree;
	}

	/**
	 * @param educationalDegree
	 *            the educationalDegree to set
	 */
	public void setEducationalDegree(String educationalDegree) {
		this.educationalDegree = educationalDegree;
	}

	/**
	 * @return the passedOutDate
	 */
	public String getPassedOutDate() {
		return passedOutDate;
	}

	/**
	 * @param passedOutDate
	 *            the passedOutDate to set
	 */
	public void setPassedOutDate(String passedOutDate) {
		this.passedOutDate = passedOutDate;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param institution
	 *            the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
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
