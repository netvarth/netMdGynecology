/**
 * PatientListResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 16, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class PatientListResponseDTO {
	private List<PatientDetail> patient = new ArrayList<PatientDetail>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public PatientListResponseDTO() {
		super();
	}

	/**
	 * @param patient
	 * @param error
	 * @param success
	 * @param count
	 */
	public PatientListResponseDTO(List<PatientDetail> patient, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.patient = patient;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	/**
	 * @return the patient
	 */
	public List<PatientDetail> getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            the patient to set
	 */
	public void setPatient(List<PatientDetail> patient) {
		this.patient = patient;
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

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

}
