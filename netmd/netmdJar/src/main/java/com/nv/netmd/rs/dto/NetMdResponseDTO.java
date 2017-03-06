/**
* NetMdResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 11, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;


/**
 * 
 */
public class NetMdResponseDTO {
	private boolean success;
	private ErrorDTO error;
	private boolean existMac;
	/**
	 * 
	 */
	public NetMdResponseDTO() {
		super();
	}
	/**
	 * @param success
	 * @param error
	 * @param existMac
	 */
	public NetMdResponseDTO(boolean success, ErrorDTO error, boolean existMac) {
		super();
		this.success = success;
		this.error = error;
		this.existMac = existMac;
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
	 * @return the existMac
	 */
	public boolean isExistMac() {
		return existMac;
	}
	/**
	 * @param existMac the existMac to set
	 */
	public void setExistMac(boolean existMac) {
		this.existMac = existMac;
	}
}
