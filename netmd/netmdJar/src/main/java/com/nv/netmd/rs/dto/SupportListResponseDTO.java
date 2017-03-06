/**
 * SupportListResponseDTO.java
 * @author Leo
 *
 * Version 1.0 Aug 20, 2013
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
public class SupportListResponseDTO {

	public SupportListResponseDTO(){
		
	}
	
	private List<SupportDTO> supportList = new ArrayList<SupportDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	
	
	private SupportListResponseDTO(List<SupportDTO> supportList,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.supportList = supportList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	

	public List<SupportDTO> getSupportList() {
		return supportList;
	}


	public void setSupportList(List<SupportDTO> supportList) {
		this.supportList = supportList;
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
