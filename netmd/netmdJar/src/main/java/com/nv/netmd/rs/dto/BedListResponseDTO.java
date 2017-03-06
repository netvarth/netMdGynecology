/**
 * BedListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Aug 2, 2013
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
public class BedListResponseDTO {
	private List<BedDTO> bedList = new ArrayList<BedDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param bedList
	 * @param error
	 * @param success
	 * @param count
	 */
	public BedListResponseDTO(List<BedDTO> bedList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.bedList = bedList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * 
	 */
	public BedListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the bedList
	 */
	public List<BedDTO> getBedList() {
		return bedList;
	}
	/**
	 * @param bedList the bedList to set
	 */
	public void setBedList(List<BedDTO> bedList) {
		this.bedList = bedList;
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
