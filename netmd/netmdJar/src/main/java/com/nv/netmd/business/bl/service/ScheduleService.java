/**
 * ScheduleService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleListDTO;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
public interface ScheduleService {
	public ResponseDTO create(ScheduleDTO schedule) throws ServiceException;

	public ResponseDTO update(ScheduleDTO schedule) throws ServiceException;

	public ResponseDTO delete(int id) throws ServiceException;

	public ResponseDTO deleteThisInstanceSchedule(int id) throws ServiceException;

	public ResponseDTO deletefollowingSchedule(int id) throws ServiceException;

	public ViewScheduleListDTO dayView(String date,int doctorId) throws ServiceException;

	public ScheduleListDTO weeklyView(String date,int doctorId) throws ServiceException;

	public ScheduleListDTO monthlyView(String startDate, String endDate,int doctorId) throws ServiceException;

	public ScheduleResponse scheduleFromYNW(ScheduleDetail schedule) throws ServiceException;
}
