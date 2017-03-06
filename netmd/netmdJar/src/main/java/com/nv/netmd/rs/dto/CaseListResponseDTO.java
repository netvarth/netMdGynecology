/**
 * CaseListResponseDTO.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class CaseListResponseDTO {
	private List<CaseDTO> caseList = new ArrayList<CaseDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public CaseListResponseDTO() {
		super();
	}

	/**
	 * @param caseList
	 * @param error
	 * @param success
	 * @param count
	 */
	public CaseListResponseDTO(List<CaseDTO> caseList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.caseList = caseList;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	/**
	 * @return the caseList
	 */
	public List<CaseDTO> getCaseList() {
		return caseList;
	}

	/**
	 * @param caseList
	 *            the caseList to set
	 */
	public void setCaseList(List<CaseDTO> caseList) {
		this.caseList = caseList;
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
