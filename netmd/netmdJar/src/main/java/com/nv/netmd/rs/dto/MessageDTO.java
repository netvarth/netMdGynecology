/**
 * MessageDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 May 2, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class MessageDTO {
	private int id;
	private String message;
	private boolean messageViewed;
	private int doctorId;
	private String doctorName;
	private String date;

	/**
	 * 
	 */
	public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param message
	 * @param doctorId
	 * @param doctorName
	 * @param date
	 * @param messageViewed
	 */
	public MessageDTO(int id, String message, int doctorId, String doctorName,
			String date, boolean messageViewed) {
		super();
		this.id = id;
		this.message = message;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.date = date;
		this.messageViewed = messageViewed;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the messageViewed
	 */
	public boolean isMessageViewed() {
		return messageViewed;
	}
	/**
	 * @param messageViewed the messageViewed to set
	 */
	public void setMessageViewed(boolean messageViewed) {
		this.messageViewed = messageViewed;
	}
	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

}
