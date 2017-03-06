/**
 * ViewDoctorDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 6, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class ViewDoctorDTO {
	private DoctorDetail doctor;
	private ErrorDTO error;
	private boolean success;

	/**
	 * @param doctor
	 * @param error
	 * @param success
	 */
	public ViewDoctorDTO(DoctorDetail doctor, ErrorDTO error, boolean success) {
		super();
		this.doctor = doctor;
		this.error = error;
		this.success = success;
	}

	/**
	 * 
	 */
	public ViewDoctorDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the doctor
	 */
	public DoctorDetail getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 *            the doctor to set
	 */
	public void setDoctor(DoctorDetail doctor) {
		this.doctor = doctor;
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

}
