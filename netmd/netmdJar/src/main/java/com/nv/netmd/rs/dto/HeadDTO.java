 /**
* HeadDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 11, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.rs.dto;

/**
 * @author Nithesh Mohanan
 *
 */
public class HeadDTO {
	
	private int id;
	private String headName;
	private String parentHeadName;
	private int parentHeadId;
	private String description;
	private String headtype;
	private String status;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @param id
	 * @param headName
	 * @param parentHeadName
	 * @param parentHeadId
	 * @param description
	 * @param headtype
	 */
	public HeadDTO(int id, String headName, String parentHeadName,
			int parentHeadId, String description, String headtype)
	{
		super();
		this.id = id;
		this.headName = headName;
		this.parentHeadName = parentHeadName;
		this.parentHeadId = parentHeadId;
		this.description = description;
		this.headtype = headtype;
	}
	/**
	 * 
	 */
	public HeadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the headName
	 */
	public String getHeadName() {
		return headName;
	}
	/**
	 * @param headName the headName to set
	 */
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	/**
	 * @return the parentHeadName
	 */
	public String getParentHeadName() {
		return parentHeadName;
	}
	/**
	 * @param parentHeadName the parentHeadName to set
	 */
	public void setParentHeadName(String parentHeadName) {
		this.parentHeadName = parentHeadName;
	}
	/**
	 * @return the parentHeadId
	 */
	public int getParentHeadId() {
		return parentHeadId;
	}
	/**
	 * @param parentHeadId the parentHeadId to set
	 */
	public void setParentHeadId(int parentHeadId) {
		this.parentHeadId = parentHeadId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the headtype
	 */
	public String getHeadtype() {
		return headtype;
	}
	/**
	 * @param headtype the headtype to set
	 */
	public void setHeadtype(String headtype) {
		this.headtype = headtype;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
