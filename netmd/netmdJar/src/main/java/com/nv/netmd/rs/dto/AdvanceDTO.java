 /**
* AdvanceDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.rs.dto;

public class AdvanceDTO {
	

	private int id;
	private int patientId;
	private String date;
	private float amount;
	private String status;
	private String patientName;
	private String email;
	private boolean success;
	private ErrorDTO error;
	
	/**
	 * 
	 */
	public AdvanceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param id
	 * @param patientId
	 * @param date
	 * @param amount
	 * @param status
	 * @param patientName
	 */
	public AdvanceDTO(int id, int patientId, String date, float amount,
			String status,String patientName) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.date = date;
		this.amount = amount;
		this.status = status;
		this.patientName=patientName;
		
		
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}

}
