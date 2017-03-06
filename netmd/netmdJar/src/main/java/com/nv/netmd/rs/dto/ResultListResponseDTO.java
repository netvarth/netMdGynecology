/**
 * ResultListResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 21, 2013
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
public class ResultListResponseDTO {
	private List<ViewResultDTO> resultList = new ArrayList<ViewResultDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
 * 
 */
	public ResultListResponseDTO() {
		super();
	}

	/**
	 * @param resultList
	 * @param error
	 * @param success
	 */
	public ResultListResponseDTO(List<ViewResultDTO> resultList,
			ErrorDTO error, boolean success) {
		super();
		this.resultList = resultList;
		this.error = error;
		this.success = success;
	}

	/**
	 * @return the resultList
	 */
	public List<ViewResultDTO> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<ViewResultDTO> resultList) {
		this.resultList = resultList;
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
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
