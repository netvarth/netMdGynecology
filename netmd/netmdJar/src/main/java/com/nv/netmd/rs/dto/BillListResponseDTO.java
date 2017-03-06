/**
 * BillListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 02-Sep-2013
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
public class BillListResponseDTO {
	private List<BillDTO> billList = new ArrayList<BillDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param billList
	 * @param error
	 * @param success
	 * @param count
	 */
	public BillListResponseDTO(List<BillDTO> billList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.billList = billList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * 
	 */
	public BillListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the billList
	 */
	public List<BillDTO> getBillList() {
		return billList;
	}
	/**
	 * @param billList the billList to set
	 */
	public void setBillList(List<BillDTO> billList) {
		this.billList = billList;
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
