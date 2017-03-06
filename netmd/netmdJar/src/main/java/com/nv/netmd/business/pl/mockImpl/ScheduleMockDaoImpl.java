/**
* ScheduleMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 25, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.rs.dto.DoctorScheduleTimeDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
public class ScheduleMockDaoImpl implements ScheduleDao{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#update(com.nv.netmd.rs.dto.ScheduleDTO)
	 */
	@Override
	public ResponseDTO update(ScheduleDTO schedule) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#createSchedule(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public ResponseDTO createSchedule(String startDate, String startTime,
			String endTime, String status, int seriesId, int doctorId) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#deleteThisInstanceSchedule(int)
	 */
	@Override
	public ResponseDTO deleteThisInstanceSchedule(int id) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#deleteFollowingSchedule(int)
	 */
	@Override
	public ResponseDTO deleteFollowingSchedule(int id) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#dayView(java.lang.String, int)
	 */
	@Override
	public ViewScheduleListDTO dayView(String date, int doctorId) {
		// TODO Auto-generated method stub
		ViewScheduleListDTO response=new ViewScheduleListDTO();
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#getNewSchedule()
	 */
	@Override
	public List<ScheduleDetail> getNewSchedule() {
		// TODO Auto-generated method stub
		List<ScheduleDetail> schedule=new ArrayList<ScheduleDetail>();
		return schedule;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#getUpdatedSchedule()
	 */
	@Override
	public List<ScheduleDetail> getUpdatedSchedule() {
		// TODO Auto-generated method stub
		List<ScheduleDetail> schedule=new ArrayList<ScheduleDetail>();
		return schedule;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#getDeletedSchedule()
	 */
	@Override
	public List<ScheduleDetail> getDeletedSchedule() {
		// TODO Auto-generated method stub
		List<ScheduleDetail> schedule=new ArrayList<ScheduleDetail>();
		return schedule;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#addScheduleSyncResponse(com.nv.netmd.rs.dto.ScheduleResponse)
	 */
	@Override
	public void addScheduleSyncResponse(ScheduleResponse scheduleResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#updateScheduleSyncResponse(com.nv.netmd.rs.dto.ScheduleResponse)
	 */
	@Override
	public void updateScheduleSyncResponse(ScheduleResponse scheduleResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#deleteScheduleSyncResponse(com.nv.netmd.rs.dto.ScheduleResponse)
	 */
	@Override
	public void deleteScheduleSyncResponse(ScheduleResponse scheduleResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#getDoctorScheduleList(java.util.Date, int)
	 */
	@Override
	public List<DoctorScheduleTimeDTO> getDoctorScheduleList(Date date,
			int doctorId) {
		// TODO Auto-generated method stub
		List<DoctorScheduleTimeDTO> schedule=new ArrayList<DoctorScheduleTimeDTO>();
		return schedule;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#createSeries(com.nv.netmd.rs.dto.ScheduleDTO)
	 */
	@Override
	public int createSeries(ScheduleDTO schedule) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#updateSeriesTbl(com.nv.netmd.rs.dto.ScheduleDTO)
	 */
	@Override
	public int updateSeriesTbl(ScheduleDTO schedule) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#updateSchedule(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public ResponseDTO updateSchedule(String startDate, String startTime,
			String endTime, String status, int seriesId, int doctorId) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#checkUpdateSchedule(com.nv.netmd.rs.dto.ScheduleDTO)
	 */
	@Override
	public boolean checkUpdateSchedule(ScheduleDTO schedule) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ScheduleDao#scheduleFromYNW(com.nv.netmd.rs.dto.ScheduleDetail)
	 */
	@Override
	public ScheduleResponse scheduleFromYNW(ScheduleDetail schedule) {
		// TODO Auto-generated method stub
		ScheduleResponse response=new ScheduleResponse();
		response.setId(1);
		return response;
	}

}
