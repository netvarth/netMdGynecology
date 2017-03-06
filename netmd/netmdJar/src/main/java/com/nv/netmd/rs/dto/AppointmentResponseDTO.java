/**
 * AppointmentResponseDTO.java
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
public class AppointmentResponseDTO {
	private int id;
	private boolean success;
	private ErrorDTO error;
	private String startTime;
	private  String patientFirstName;
	private  String patientLastName;

	/**
	 * 
	 */
	public AppointmentResponseDTO() {
		super();
	}

	/**
	 * @param id
	 * @param success
	 * @param error
	 * @param startTime
	 */
	public AppointmentResponseDTO(int id, boolean success, ErrorDTO error,
			String startTime) {
		super();
		this.id = id;
		this.success = success;
		this.error = error;
		this.startTime = startTime;
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
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the patientFirstName
	 */
	public String getPatientFirstName() {
		return patientFirstName;
	}

	/**
	 * @param patientFirstName the patientFirstName to set
	 */
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	/**
	 * @return the patientLastName
	 */
	public String getPatientLastName() {
		return patientLastName;
	}

	/**
	 * @param patientLastName the patientLastName to set
	 */
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
}
