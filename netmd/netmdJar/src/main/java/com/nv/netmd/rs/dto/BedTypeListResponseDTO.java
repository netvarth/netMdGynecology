/**
 * BedTypeListResponseDTO.java
 * @author Leo
 *
 * Version 1.0 Jul 31, 2013
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
 * @author Leonora Louis
 */
public class BedTypeListResponseDTO {
	private List<BedTypeDTO> bedTypeList = new ArrayList<BedTypeDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @return the bedTypeList
	 */
	public List<BedTypeDTO> getBedTypeList() {
		return bedTypeList;
	}
	/**
	 * @param bedTypeList the bedTypeList to set
	 */
	public void setBedTypeList(List<BedTypeDTO> bedTypeList) {
		this.bedTypeList = bedTypeList;
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
	/**
	 * 
	 */
	public BedTypeListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param bedTypeList
	 * @param error
	 * @param success
	 * @param count
	 */
	public BedTypeListResponseDTO(List<BedTypeDTO> bedTypeList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.bedTypeList = bedTypeList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	
	
	
}
