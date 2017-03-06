/**
* RetrievalPatientResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 May 2, 2013
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
public class RetrievalPatientResponseDTO {
	private List<PatientDetail> retrievePatients = new ArrayList<PatientDetail>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	
	
	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}
	/**
	 * @param lastSynctime the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
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
	 * @return the retrievePatients
	 */
	public List<PatientDetail> getRetrievePatients() {
		return retrievePatients;
	}
	/**
	 * @param retrievePatients the retrievePatients to set
	 */
	public void setRetrievePatients(List<PatientDetail> retrievePatients) {
		this.retrievePatients = retrievePatients;
	}
}
