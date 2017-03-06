/**
* DoctorScheduleTimeDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Apr 19, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

import java.util.Date;


/**
 * 
 */
public class DoctorScheduleTimeDTO {
	private Date startDate;
	private Date endingTime;
	private Date startingTime;
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endingTime
	 */
	public Date getEndingTime() {
		return endingTime;
	}
	/**
	 * @param endingTime the endingTime to set
	 */
	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}
	/**
	 * @return the startingTime
	 */
	public Date getStartingTime() {
		return startingTime;
	}
	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}
	
}
