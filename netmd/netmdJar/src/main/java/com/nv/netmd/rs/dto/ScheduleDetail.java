/**
* ScheduleDetail.java
* 
* @Author Sreeram
*
* Version 1.0 Apr 12, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class ScheduleDetail {
	private int id;
	private int doctorGlobalId;
	private SeriesDTO series;
	private String startDate;
	private String startTime;
	private String endTime;
	private String scheduleStatus;
	private int scheduleGlobalId;
	private String status;
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
	 * @return the doctorGlobalId
	 */
	public int getDoctorGlobalId() {
		return doctorGlobalId;
	}
	/**
	 * @param doctorGlobalId the doctorGlobalId to set
	 */
	public void setDoctorGlobalId(int doctorGlobalId) {
		this.doctorGlobalId = doctorGlobalId;
	}
	/**
	 * @return the series
	 */
	public SeriesDTO getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(SeriesDTO series) {
		this.series = series;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the scheduleStatus
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	/**
	 * @param scheduleStatus the scheduleStatus to set
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	/**
	 * @return the scheduleGlobalId
	 */
	public int getScheduleGlobalId() {
		return scheduleGlobalId;
	}
	/**
	 * @param scheduleGlobalId the scheduleGlobalId to set
	 */
	public void setScheduleGlobalId(int scheduleGlobalId) {
		this.scheduleGlobalId = scheduleGlobalId;
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
