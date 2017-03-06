/**
 * ResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Sep 26, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class AutoSaveResponseDTO extends ResponseDTO {
	private int caseId;
	private int ansSetId;
	/**
	 * @return the caseId
	 */
	public int getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the ansSetId
	 */
	public int getAnsSetId() {
		return ansSetId;
	}
	/**
	 * @param ansSetId the ansSetId to set
	 */
	public void setAnsSetId(int ansSetId) {
		this.ansSetId = ansSetId;
	}
	
	
}
