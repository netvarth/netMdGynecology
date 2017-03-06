/**
 * BedDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Aug 2, 2013
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
public class BedDTO {
private int id;
private String prefix;
private String bedNumber;
private String availability;
private int bedTypeId;
private String bedType;
private String description;
private String status;
private int roomId;
private String roomName;
private ErrorDTO error;
private boolean success;

private float price;
private int taxId;
private String taxName;
private float taxValue;
private String taxType;
private float stdRate;
/**
 * @param id
 * @param prefix
 * @param bedNumber
 * @param availability
 * @param bedTypeId
 * @param bedType
 * @param description
 * @param status
 * @param roomId
 * @param roomName
 */
public BedDTO(int id, String prefix, String bedNumber, String availability,
		int bedTypeId, String bedType, String description, String status,
		int roomId, String roomName) {
	super();
	this.id = id;
	this.prefix = prefix;
	this.bedNumber = bedNumber;
	this.availability = availability;
	this.bedTypeId = bedTypeId;
	this.bedType = bedType;
	this.description = description;
	this.status = status;
	this.roomId = roomId;
	this.roomName = roomName;
}
/**
 * 
 */
public BedDTO() {
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
 * @return the bedNumber
 */
public String getBedNumber() {
	return bedNumber;
}
/**
 * @param bedNumber the bedNumber to set
 */
public void setBedNumber(String bedNumber) {
	this.bedNumber = bedNumber;
}
/**
 * @return the availability
 */
public String getAvailability() {
	return availability;
}
/**
 * @param availability the availability to set
 */
public void setAvailability(String availability) {
	this.availability = availability;
}
/**
 * @return the bedTypeId
 */
public int getBedTypeId() {
	return bedTypeId;
}
/**
 * @param bedTypeId the bedTypeId to set
 */
public void setBedTypeId(int bedTypeId) {
	this.bedTypeId = bedTypeId;
}
/**
 * @return the bedType
 */
public String getBedType() {
	return bedType;
}
/**
 * @param bedType the bedType to set
 */
public void setBedType(String bedType) {
	this.bedType = bedType;
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
/**
 * @return the roomId
 */
public int getRoomId() {
	return roomId;
}
/**
 * @param roomId the roomId to set
 */
public void setRoomId(int roomId) {
	this.roomId = roomId;
}
/**
 * @return the roomName
 */
public String getRoomName() {
	return roomName;
}
/**
 * @param roomName the roomName to set
 */
public void setRoomName(String roomName) {
	this.roomName = roomName;
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
 * @return the taxId
 */
public int getTaxId() {
	return taxId;
}
/**
 * @param taxId the taxId to set
 */
public void setTaxId(int taxId) {
	this.taxId = taxId;
}
/**
 * @return the taxName
 */
public String getTaxName() {
	return taxName;
}
/**
 * @param taxName the taxName to set
 */
public void setTaxName(String taxName) {
	this.taxName = taxName;
}
/**
 * @return the taxValue
 */
public float getTaxValue() {
	return taxValue;
}
/**
 * @param taxValue the taxValue to set
 */
public void setTaxValue(float taxValue) {
	this.taxValue = taxValue;
}
/**
 * @return the taxType
 */
public String getTaxType() {
	return taxType;
}
/**
 * @param taxType the taxType to set
 */
public void setTaxType(String taxType) {
	this.taxType = taxType;
}
/**
 * @return the stdRate
 */
public float getStdRate() {
	return stdRate;
}
/**
 * @param stdRate the stdRate to set
 */
public void setStdRate(float stdRate) {
	this.stdRate = stdRate;
}

}
