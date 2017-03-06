/**
 * TaxViewResponseDTO.java
 * @author Leo
 *
 * Version 1.0 Aug 12, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.List;


/**
 *
 *
 * @author Leonora Louis
 */
public class TaxViewResponseDTO {
	private TaxDTO tax;
	private ErrorDTO error;
	private boolean success;
	
	public TaxViewResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaxViewResponseDTO(TaxDTO tax, ErrorDTO error, boolean success) {
		super();
		this.tax = tax;
		this.error = error;
		this.success = success;
	}
	public TaxDTO getTax() {
		return tax;
	}
	public void setTax(TaxDTO tax) {
		this.tax = tax;
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



}
