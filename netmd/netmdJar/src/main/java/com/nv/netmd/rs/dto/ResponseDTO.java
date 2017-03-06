/**
 * ResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Sep 26, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class ResponseDTO {
	private int id;
	private String uid;
	private boolean success;
	private ErrorDTO error;

	/**
	 * 
	 */
	public ResponseDTO() {

	}

	/**
	 * @param id
	 * @param success
	 * @param error
	 */
	public ResponseDTO(int id, boolean success, ErrorDTO error) {
		super();
		this.id = id;
		this.success = success;
		this.error = error;

	}

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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

}
