/**
 * MedicalRecordDTO.java
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
public class MedicalRecordDTO {
	private int id;
	private int globalId;
	private int doctorId;
	private int patientId;
	private int caseId;
	private String caseName;
	private String doctorName;
	private String type;
	private String date;
	private String medicalRecord;
	private String actionName;
	private ErrorDTO error;
	private boolean success;

	/**
	 * 
	 */
	public MedicalRecordDTO() {
		super();
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**


	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}


	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param doctorId
	 * @param patientId
	 * @param caseId
	 * @param caseName
	 * @param doctorName
	 * @param type
	 * @param date
	 * @param medicalRecord
	 */
	public MedicalRecordDTO(int id, int doctorId, int patientId, int caseId,
			String caseName, String doctorName, String type, String date,
			String medicalRecord) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.caseId = caseId;
		this.caseName = caseName;
		this.doctorName = doctorName;
		this.type = type;
		this.date = date;
		this.medicalRecord = medicalRecord;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the caseId
	 */
	public int getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the medicalRecord
	 */
	public String getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * @param medicalRecord
	 *            the medicalRecord to set
	 */
	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName
	 *            the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the caseName
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * @param caseName
	 *            the caseName to set
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
}
