/**
 * LoginResponseDTO.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.rs.dto;

public class LoginResponseDTO {
	private boolean success;
	private ErrorDTO error;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
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
	 * @param error
	 *            the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}

}
