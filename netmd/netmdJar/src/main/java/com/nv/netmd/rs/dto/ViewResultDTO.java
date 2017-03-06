/**
 * ViewResultDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */

public class ViewResultDTO {
	private int id;
	private int patientId;
	private String patientName;
	private int doctorId;
	private int globalId;
	private String result;
	private String date;
	private String orderDate;
	private String orderUid;
	private String labName;
	private String labBranchName;
	private boolean success;
	private ErrorDTO error;
	

	/**
	 * 
	 */
	public ViewResultDTO() {
		super();
	}

	/**
	 * @param id
	 * @param patientId
	 * @param doctorId
	 * @param result
	 * @param success
	 * @param error
	 */
	public ViewResultDTO(int id, int patientId, int doctorId, String result,
			boolean success, ErrorDTO error) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.result = result;
		this.success = success;
		this.error = error;
	}

	

	/**
	 * @param id
	 * @param patientId
	 * @param patientName 
	 * @param doctorId
	 * @param result
	 * @param date
	 */
	public ViewResultDTO(int id, int patientId,String patientName, int doctorId, String result,
			String date) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientName=patientName;
		this.doctorId = doctorId;
		this.result = result;
		this.date = date;
	}

	/**
	 * @param id
	 * @param patientId
	 * @param patientName
	 * @param doctorId
	 * @param result
	 * @param date
	 * @param orderDate
	 * @param orderUid
	 * @param labName
	 * @param labBranchName
	 */
	public ViewResultDTO(int id, int patientId, String patientName,
			int doctorId, String result, String date, String orderDate,
			String orderUid, String labName, String labBranchName) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorId = doctorId;
		this.result = result;
		this.date = date;
		this.orderDate = orderDate;
		this.orderUid = orderUid;
		this.labName = labName;
		this.labBranchName = labBranchName;
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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

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
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderUid
	 */
	public String getOrderUid() {
		return orderUid;
	}

	/**
	 * @param orderUid the orderUid to set
	 */
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}

	/**
	 * @return the labName
	 */
	public String getLabName() {
		return labName;
	}

	/**
	 * @param labName the labName to set
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	/**
	 * @return the labBranchName
	 */
	public String getLabBranchName() {
		return labBranchName;
	}

	/**
	 * @param labBranchName the labBranchName to set
	 */
	public void setLabBranchName(String labBranchName) {
		this.labBranchName = labBranchName;
	}
}
