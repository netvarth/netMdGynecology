/**
 * BillResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 27-Sep-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillResponseDTO {
	private double amountToBePaid;
	private double balanceToBePaid;
	private int id;
	private String uid;
	private String patientName;
	private String origin;
	private String billStatus;
	private boolean success;
	private ErrorDTO error;
	
	
	/**
	 * @param amountToBePaid
	 * @param balanceToBePaid
	 * @param id
	 * @param uid
	 * @param patientName
	 * @param origin
	 * @param billStatus
	 * @param success
	 * @param error
	 */
	public BillResponseDTO(double amountToBePaid, double balanceToBePaid,
			int id, String uid, String patientName, String origin,
			String billStatus, boolean success, ErrorDTO error) {
		super();
		this.amountToBePaid = amountToBePaid;
		this.balanceToBePaid = balanceToBePaid;
		this.id = id;
		this.uid = uid;
		this.patientName = patientName;
		this.origin = origin;
		this.billStatus = billStatus;
		this.success = success;
		this.error = error;
	}
	/**
	 * 
	 */
	public BillResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the amountToBePaid
	 */
	public double getAmountToBePaid() {
		return amountToBePaid;
	}
	/**
	 * @param amountToBePaid the amountToBePaid to set
	 */
	public void setAmountToBePaid(double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	/**
	 * @return the balanceToBePaid
	 */
	public double getBalanceToBePaid() {
		return balanceToBePaid;
	}
	/**
	 * @param balanceToBePaid the balanceToBePaid to set
	 */
	public void setBalanceToBePaid(double balanceToBePaid) {
		this.balanceToBePaid = balanceToBePaid;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
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
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
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
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the billStatus
	 */
	public String getBillStatus() {
		return billStatus;
	}
	/**
	 * @param billStatus the billStatus to set
	 */
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
}
