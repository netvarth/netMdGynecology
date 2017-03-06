/**
 * MedicalListResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 19, 2013
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
public class MedicalListResponseDTO {
	private List<MedicalRecordDTO> medicalList = new ArrayList<MedicalRecordDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public MedicalListResponseDTO() {
		super();
	}

	/**
	 * @param medicalList
	 * @param error
	 * @param success
	 * @param count
	 */
	public MedicalListResponseDTO(List<MedicalRecordDTO> medicalList,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.medicalList = medicalList;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	/**
	 * @return the medicalList
	 */
	public List<MedicalRecordDTO> getMedicalList() {
		return medicalList;
	}

	/**
	 * @param medicalList
	 *            the medicalList to set
	 */
	public void setMedicalList(List<MedicalRecordDTO> medicalList) {
		this.medicalList = medicalList;
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
