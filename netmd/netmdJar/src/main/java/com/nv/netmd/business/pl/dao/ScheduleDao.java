/**
 * ScheduleDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.dao;

import java.util.Date;
import java.util.List;



import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.DoctorScheduleTimeDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
public interface ScheduleDao {
	public ResponseDTO update(ScheduleDTO schedule)throws PersistenceException;
	public ResponseDTO createSchedule(String startDate, String startTime, String endTime,
			String status,int seriesId,int doctorId)throws PersistenceException;


	//public boolean checkSchedule(String date, String fromtime, String totime,int doctorId);
	public ResponseDTO delete(int id)throws PersistenceException;
	public ResponseDTO deleteThisInstanceSchedule(int id)throws PersistenceException;
	public ResponseDTO deleteFollowingSchedule(int id)throws PersistenceException;
	public ViewScheduleListDTO dayView(String date,int doctorId)throws PersistenceException;	
	//	public int getSeriesId(int scheduleId);
	public List<ScheduleDetail> getNewSchedule()throws PersistenceException;	
	public List<ScheduleDetail> getUpdatedSchedule()throws PersistenceException;
	public List<ScheduleDetail> getDeletedSchedule()throws PersistenceException;
	public void addScheduleSyncResponse(ScheduleResponse scheduleResponse)throws PersistenceException;
	public void updateScheduleSyncResponse(ScheduleResponse scheduleResponse)throws PersistenceException;
	public void deleteScheduleSyncResponse(ScheduleResponse scheduleResponse)throws PersistenceException;
	public List<DoctorScheduleTimeDTO> getDoctorScheduleList(Date date,int doctorId)throws PersistenceException;
	/**
	 * @param schedule
	 * @return
	 */
	public int createSeries(ScheduleDTO schedule)throws PersistenceException;
	/**
	 * @param schedule
	 * @return
	 */
	public int updateSeriesTbl(ScheduleDTO schedule)throws PersistenceException;
	/**
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param seriesId
	 * @param doctorId
	 * @return
	 */
	public ResponseDTO updateSchedule(String startDate, String startTime,
			String endTime, String status, int seriesId, int doctorId)throws PersistenceException;
	/**
	 * @param schedule 
	 * @return
	 */
	public boolean checkUpdateSchedule(ScheduleDTO schedule)throws PersistenceException;
	public ScheduleResponse scheduleFromYNW(ScheduleDetail schedule)throws PersistenceException;
}
