/**
 * DoctorAchievementDTO.java
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
public class DoctorAchievementDTO {
	private int id;
	private int doctorId;
	private String achievement;
	private String actionName;

	/**
	 * 
	 */
	public DoctorAchievementDTO() {
		super();
	}



	/**
	 * @param id
	 * @param doctorId
	 * @param achievement
	 * @param actionName
	 */
	public DoctorAchievementDTO(int id, int doctorId, String achievement,
			String actionName) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.achievement = achievement;
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
	 * @return the achievement
	 */
	public String getAchievement() {
		return achievement;
	}

	/**
	 * @param achievement
	 *            the achievement to set
	 */
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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
