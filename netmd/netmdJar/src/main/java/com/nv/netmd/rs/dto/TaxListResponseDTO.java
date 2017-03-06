/**
 * TaxListResponseDTO.java
 * @author Leo
 *
 * Version 1.0 Aug 13, 2013
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
public class TaxListResponseDTO {
	private List<TaxDTO> taxlist=new ArrayList<TaxDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	
	public TaxListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TaxListResponseDTO(List<TaxDTO> taxlist, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.taxlist = taxlist;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	public List<TaxDTO> getTaxlist() {
		return taxlist;
	}
	public void setTaxlist(List<TaxDTO> taxlist) {
		this.taxlist = taxlist;
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
