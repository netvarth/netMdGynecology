 /**
* AdvanceListResponseDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 23, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class AdvanceListResponseDTO {
	private List<AdvanceDTO> advanceList = new ArrayList<AdvanceDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	
	
	/**
	 * @param advanceList
	 * @param error
	 * @param success
	 * @param count
	 */
	public AdvanceListResponseDTO(List<AdvanceDTO> advanceList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.advanceList = advanceList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	
	/**
	 * 
	 */
	public AdvanceListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<AdvanceDTO> getAdvanceList() {
		return advanceList;
	}
	public void setAdvanceList(List<AdvanceDTO> advanceList) {
		this.advanceList = advanceList;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

}
