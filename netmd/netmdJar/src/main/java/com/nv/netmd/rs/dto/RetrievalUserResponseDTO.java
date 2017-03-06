/**
* RetrievalUserResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Apr 29, 2013
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
public class RetrievalUserResponseDTO {
	private List<NetMdUserDetail> retrieveUsersList= new ArrayList<NetMdUserDetail>();	
	private String lastSyncTime;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @return the lastSyncTime
	 */
	public String getLastSyncTime() {
		return lastSyncTime;
	}
	/**
	 * @param lastSyncTime the lastSyncTime to set
	 */
	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
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
	 * @return the retrieveUsersList
	 */
	public List<NetMdUserDetail> getRetrieveUsersList() {
		return retrieveUsersList;
	}
	/**
	 * @param retrieveUsersList the retrieveUsersList to set
	 */
	public void setRetrieveUsersList(List<NetMdUserDetail> retrieveUsersList) {
		this.retrieveUsersList = retrieveUsersList;
	}
}
