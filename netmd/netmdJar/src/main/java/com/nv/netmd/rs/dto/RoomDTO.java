/**
 * RoomDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 30, 2013
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
public class RoomDTO {
private int id;
private String prefix;
private String roomNumber;
private int roomTypeId;
private String roomTypeName;
private int blockId;
private String blockName;
private int departmentId;
private String departmentName;
private String description;
private float price;
private String status;
private ErrorDTO error;
private boolean success;
/**
 * 
 */
public RoomDTO() {
	super();
	// TODO Auto-generated constructor stub
}



/**
 * @param id
 * @param prefix
 * @param roomNumber
 * @param roomTypeId
 * @param roomTypeName
 * @param blockId
 * @param blockName
 * @param departmentId
 * @param departmentName
 * @param description
 * @param status
 */
public RoomDTO(int id, String prefix, String roomNumber, int roomTypeId,
		String roomTypeName, int blockId, String blockName, int departmentId,
		String departmentName, String description, String status) {
	super();
	this.id = id;
	this.prefix = prefix;
	this.roomNumber = roomNumber;
	this.roomTypeId = roomTypeId;
	this.roomTypeName = roomTypeName;
	this.blockId = blockId;
	this.blockName = blockName;
	this.departmentId = departmentId;
	this.departmentName = departmentName;
	this.description = description;
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
 * @return the prefix
 */
public String getPrefix() {
	return prefix;
}
/**
 * @param prefix the prefix to set
 */
public void setPrefix(String prefix) {
	this.prefix = prefix;
}
/**
 * @return the roomNumber
 */
public String getRoomNumber() {
	return roomNumber;
}
/**
 * @param roomNumber the roomNumber to set
 */
public void setRoomNumber(String roomNumber) {
	this.roomNumber = roomNumber;
}
/**
 * @return the roomTypeId
 */
public int getRoomTypeId() {
	return roomTypeId;
}
/**
 * @param roomTypeId the roomTypeId to set
 */
public void setRoomTypeId(int roomTypeId) {
	this.roomTypeId = roomTypeId;
}
/**
 * @return the blockId
 */
public int getBlockId() {
	return blockId;
}
/**
 * @param blockId the blockId to set
 */
public void setBlockId(int blockId) {
	this.blockId = blockId;
}
/**
 * @return the departmentId
 */
public int getDepartmentId() {
	return departmentId;
}
/**
 * @param departmentId the departmentId to set
 */
public void setDepartmentId(int departmentId) {
	this.departmentId = departmentId;
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
 * @return the price
 */
public float getPrice() {
	return price;
}
/**
 * @param price the price to set
 */
public void setPrice(float price) {
	this.price = price;
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
 * @return the roomTypeName
 */
public String getRoomTypeName() {
	return roomTypeName;
}
/**
 * @param roomTypeName the roomTypeName to set
 */
public void setRoomTypeName(String roomTypeName) {
	this.roomTypeName = roomTypeName;
}
/**
 * @return the blockName
 */
public String getBlockName() {
	return blockName;
}
/**
 * @param blockName the blockName to set
 */
public void setBlockName(String blockName) {
	this.blockName = blockName;
}
/**
 * @return the departmentName
 */
public String getDepartmentName() {
	return departmentName;
}
/**
 * @param departmentName the departmentName to set
 */
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
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
