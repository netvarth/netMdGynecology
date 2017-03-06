/**
 * RetrievalBillResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 07-Oct-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Sreeram T G
 */
public class RetrievalBillResponseDTO {
	private List<BillSummaryDTO> retrieveBillList = new ArrayList<BillSummaryDTO>();
	
	private String lastSyncTime;
	private ErrorDTO error;
	private boolean success;
	/**
	 * 
	 */
	public RetrievalBillResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param retrieveBillList
	 * @param lastSyncTime
	 * @param error
	 * @param success
	 */
	public RetrievalBillResponseDTO(List<BillSummaryDTO> retrieveBillList,
			String lastSyncTime, ErrorDTO error, boolean success) {
		super();
		this.retrieveBillList = retrieveBillList;
		this.lastSyncTime = lastSyncTime;
		this.error = error;
		this.success = success;
	}
	/**
	 * @return the retrieveBillList
	 */
	public List<BillSummaryDTO> getRetrieveBillList() {
		return retrieveBillList;
	}
	/**
	 * @param retrieveBillList the retrieveBillList to set
	 */
	public void setRetrieveBillList(List<BillSummaryDTO> retrieveBillList) {
		this.retrieveBillList = retrieveBillList;
	}
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
}
