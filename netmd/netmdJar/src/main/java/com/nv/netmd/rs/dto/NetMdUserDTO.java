/**
 * NetMdUserDTO.java
 * 
 * @author Asha Chandran
 * 
 * Mar18,2013
 */
package com.nv.netmd.rs.dto;

public class NetMdUserDTO {
	private NetMdUserDetail user;
	private HeaderDTO header;
	private boolean success;
	private ErrorDTO error;
	/**
	 * 
	 */
	private NetMdUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the user
	 */
	public NetMdUserDetail getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(NetMdUserDetail user) {
		this.user = user;
	}
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
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
