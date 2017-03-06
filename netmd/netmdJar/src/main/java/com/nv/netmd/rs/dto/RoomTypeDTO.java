/**
 * RoomTypeDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 31, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomTypeDTO {
private int id;
private String type;
private float rent;
private int noOfBeds;
private int count;
private String status;
private ErrorDTO error;
private boolean success;
/**
 * 
 */
public RoomTypeDTO() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param type
 * @param rent
 * @param noOfBeds
 * @param count
 * @param status
 */
public RoomTypeDTO(int id, String type, float rent, int noOfBeds, int count,
		String status) {
	super();
	this.id = id;
	this.type = type;
	this.rent = rent;
	this.noOfBeds = noOfBeds;
	this.count = count;
	this.status = status;
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
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the rent
 */
public float getRent() {
	return rent;
}
/**
 * @param rent the rent to set
 */
public void setRent(float rent) {
	this.rent = rent;
}
/**
 * @return the noOfBeds
 */
public int getNoOfBeds() {
	return noOfBeds;
}
/**
 * @param noOfBeds the noOfBeds to set
 */
public void setNoOfBeds(int noOfBeds) {
	this.noOfBeds = noOfBeds;
}
/**
 * @return the count
 */
public int getCount() {
	return count;
}
/**
 * @param count the count to set
 */
public void setCount(int count) {
	this.count = count;
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
