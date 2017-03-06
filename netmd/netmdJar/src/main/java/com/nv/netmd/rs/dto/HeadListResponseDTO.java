 /**
* HeadListResponseDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 12, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nithesh Mohanan
 *
 */
public class HeadListResponseDTO {
	
	private List<HeadDTO> headList = new ArrayList<HeadDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * 
	 */
	public HeadListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param headList
	 * @param error
	 * @param success
	 * @param count
	 */
	public HeadListResponseDTO(List<HeadDTO> headList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.headList = headList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the headList
	 */
	public List<HeadDTO> getHeadList() {
		return headList;
	}
	/**
	 * @param headList the headList to set
	 */
	public void setHeadList(List<HeadDTO> headList) {
		this.headList = headList;
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