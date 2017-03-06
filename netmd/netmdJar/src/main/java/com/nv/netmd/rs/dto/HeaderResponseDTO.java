/**
* HeaderResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Apr 9, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class HeaderResponseDTO {
	private boolean primaryDevice;
	private boolean success;
	private ErrorDTO error;
	/**
	 * @return the primaryDevice
	 */
	public boolean isPrimaryDevice() {
		return primaryDevice;
	}
	/**
	 * @param primaryDevice the primaryDevice to set
	 */
	public void setPrimaryDevice(boolean primaryDevice) {
		this.primaryDevice = primaryDevice;
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
}
