/**
* RetrieveResultsResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 May 30, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class RetrieveResultsResponseDTO {
	private int resultGlobalId;
	private String result;
	private int patientId;
	private String orderDate;
	private String orderUid;
	private String labName;
	private String labBranchName;
	private boolean success;
	private ErrorDTO error;
	/**
	 * @return the resultGlobalId
	 */
	public int getResultGlobalId() {
		return resultGlobalId;
	}
	/**
	 * @param resultGlobalId the resultGlobalId to set
	 */
	public void setResultGlobalId(int resultGlobalId) {
		this.resultGlobalId = resultGlobalId;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
