/**
* ErrorCodeListResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 May 7, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;
import com.nv.netmd.rs.dto.Error;

/**
 * 
 */
public class ErrorCodeListResponseDTO {
	private List<Error> error= new ArrayList<Error>();

	/**
	 * @return the error
	 */
	public List<Error> getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(List<Error> error) {
		this.error = error;
	}
}
