/**
 * ScheduleDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class ScheduleDTO {
	private int id;
	private int doctorId;
	private int seriesId;
	private int scheduleGlobalId;
	private String name;
	private String startDate;
	private String startTime;
	private String endTime;
	private String endDate;
	private String repeat;
	private int noOfOccurances;
	private int[] weeklySunThruSatList;
	private String status;

	/**
	 * @param id
	 * @param doctorId
	 * @param seriesId
	 * @param scheduleGlobalId
	 * @param name
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @param endDate
	 * @param repeat
	 * @param noOfOccurances
	 * @param weeklySunThruSatList
	 * @param status
	 */
	public ScheduleDTO(int id, int doctorId, int seriesId,
			int scheduleGlobalId, String name, String startDate,
			String startTime, String endTime, String endDate, String repeat,
			int noOfOccurances, int[] weeklySunThruSatList, String status) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.seriesId = seriesId;
		this.scheduleGlobalId = scheduleGlobalId;
		this.name = name;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.endDate = endDate;
		this.repeat = repeat;
		this.noOfOccurances = noOfOccurances;
		this.weeklySunThruSatList = weeklySunThruSatList;
		this.status = status;
	}

	/**
	 * 
	 */
	public ScheduleDTO() {
		super();
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
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
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
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the noOfOccurances
	 */
	public int getNoOfOccurances() {
		return noOfOccurances;
	}

	/**
	 * @param noOfOccurances
	 *            the noOfOccurances to set
	 */
	public void setNoOfOccurances(int noOfOccurances) {
		this.noOfOccurances = noOfOccurances;
	}

//	/**
//	 * @return the monthlyDayOfTheMonth
//	 */
//	public int getMonthlyDayOfTheMonth() {
//		return monthlyDayOfTheMonth;
//	}
//
//	/**
//	 * @param monthlyDayOfTheMonth
//	 *            the monthlyDayOfTheMonth to set
//	 */
//	public void setMonthlyDayOfTheMonth(int monthlyDayOfTheMonth) {
//		this.monthlyDayOfTheMonth = monthlyDayOfTheMonth;
//	}
//
//	/**
//	 * @return the monthlyDayOfTheWeek
//	 */
//	public String getMonthlyDayOfTheWeek() {
//		return monthlyDayOfTheWeek;
//	}
//
//	/**
//	 * @param monthlyDayOfTheWeek
//	 *            the monthlyDayOfTheWeek to set
//	 */
//	public void setMonthlyDayOfTheWeek(String monthlyDayOfTheWeek) {
//		this.monthlyDayOfTheWeek = monthlyDayOfTheWeek;
//	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the repeat
	 */
	public String getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat
	 *            the repeat to set
	 */
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the weeklySunThruSatList
	 */
	public int[] getWeeklySunThruSatList() {
		return weeklySunThruSatList;
	}

	/**
	 * @param weeklySunThruSatList
	 *            the weeklySunThruSatList to set
	 */
	public void setWeeklySunThruSatList(int[] weeklySunThruSatList) {
		this.weeklySunThruSatList = weeklySunThruSatList;
	}

	/**
	 * @return the seriesId
	 */
	public int getSeriesId() {
		return seriesId;
	}

	/**
	 * @param seriesId
	 *            the seriesId to set
	 */
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
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

}
