/**
 * ScheduleServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.nv.netmd.business.bl.service.ScheduleService;
import com.nv.netmd.business.bl.validator.ScheduleValidator;
import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.RepeatEnum;
import com.nv.netmd.rs.dto.DoctorScheduleTimeDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleListDTO;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleDao scheduleDao;
	private ScheduleValidator scheduleValidator;

	/**
	 * create a schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override	
	public ResponseDTO create(ScheduleDTO schedule) throws ServiceException {
		Date fromDate, toDate;
		ArrayList<String> dateList = new ArrayList<String>();
		int[] weeks;
		int noOfWeeks = 0;
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = scheduleValidator.validateCreateSchedule(schedule);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		int weekSize = 0;
		// String repeatType=schedule.getRepeat();

		int seriesId;
		try {
			seriesId = scheduleDao.createSeries(schedule);
		} catch (PersistenceException e1) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
		}

		if (schedule.getRepeat().equals(RepeatEnum.NONE.getDisplayName())) {
			// The repeat type is none
			boolean sche = checkSchedule(schedule.getStartDate(),
					schedule.getStartTime(), schedule.getEndTime(),schedule.getDoctorId());
			if (sche == false) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleAlreadyExist);
				se.setDisplayErrMsg(true);
				throw se;

			}
			try {
				response = scheduleDao.createSchedule(schedule.getStartDate(),
						schedule.getStartTime(), schedule.getEndTime(),
						schedule.getStatus(), seriesId, schedule.getDoctorId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}

		} else if (schedule.getRepeat().equals(
				RepeatEnum.DAILY.getDisplayName())) {
			dateList.clear();
			if (schedule.getNoOfOccurances() != 0) {
				// Daily repeat with number of occurrences
				int count = 0;

				String start = schedule.getStartDate();
				Calendar c = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				try {
					c.setTime(df.parse(start));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (count < schedule.getNoOfOccurances()) {
					// save to doctor schedule table
					boolean sche = checkSchedule(start,
							schedule.getStartTime(), schedule.getEndTime(),schedule.getDoctorId());
					if (sche == false) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.ScheduleAlreadyExist);
						se.setDisplayErrMsg(true);
						throw se;

					}
					dateList.add(start);
					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
					count++;
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			} else {
				// Daily repeat with specified end Date
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				fromDate = stringToDate(schedule.getStartDate());
				toDate = stringToDate(schedule.getEndDate());
				while (!fromDate.after(toDate)) {
					c.setTime(fromDate);
					// save to doctor schedule table
					boolean sche = checkSchedule(start,
							schedule.getStartTime(), schedule.getEndTime(),schedule.getDoctorId());
					if (sche == false) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.ScheduleAlreadyExist);
						se.setDisplayErrMsg(true);

					}
					dateList.add(start);
					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
					fromDate = stringToDate(start);
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			}
		} else if (schedule.getRepeat().equals(
				RepeatEnum.WEEKLY.getDisplayName())) {
			dateList.clear();
			if (schedule.getNoOfOccurances() != 0) {
				// Weekly repeat with number of occurrences
				int count = 0;
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				try {
					c.setTime(df.parse(start));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				noOfWeeks = 0;
				weeks = schedule.getWeeklySunThruSatList();
				weekSize = weeks.length;
				for (int i = 0; i < weekSize; i++) {
					if (weeks[i] != 0)
						noOfWeeks++;
				}

				while (count < schedule.getNoOfOccurances() * noOfWeeks) {
					for (int i = 0; i < weekSize; i++) {
						if (c.get(Calendar.DAY_OF_WEEK) == weeks[i]) {
							boolean sche = checkSchedule(start,
									schedule.getStartTime(),
									schedule.getEndTime(),schedule.getDoctorId());
							if (sche == false) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.ScheduleAlreadyExist);
								se.setDisplayErrMsg(true);
								throw se;

							}
							dateList.add(start);
							count++;
						}
					}
					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			} else {
				// Weekly repeat with end date
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				weeks = schedule.getWeeklySunThruSatList();
				fromDate = stringToDate(schedule.getStartDate());
				toDate = stringToDate(schedule.getEndDate());
				while (!fromDate.after(toDate)) {
					c.setTime(fromDate);
					for (int i = 0; i < weeks.length; i++) {
						if (c.get(Calendar.DAY_OF_WEEK) == weeks[i]) {
							boolean sche =checkSchedule(start,
									schedule.getStartTime(),
									schedule.getEndTime(),schedule.getDoctorId());
							if (sche == false) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.ScheduleAlreadyExist);
								se.setDisplayErrMsg(true);
								throw se;

							}
							dateList.add(start);
						}
					}

					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
					fromDate = stringToDate(start);
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			}
		} else if (schedule.getRepeat().equals(
				RepeatEnum.MONTHILY_DAY_OF_THE_WEEK.getDisplayName())) {
			dateList.clear();
			if (schedule.getNoOfOccurances() != 0) {
				// Monthily day of the week with occurances
				int count = 0;
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				int day_of_week = c.get(Calendar.DAY_OF_WEEK);
				int week_of_month = c.get(Calendar.WEEK_OF_MONTH);
				while (count < schedule.getNoOfOccurances()) {
					if (week_of_month == c.get(Calendar.WEEK_OF_MONTH)) {
						if (day_of_week == c.get(Calendar.DAY_OF_WEEK)) {
							boolean sche = checkSchedule(start,
									schedule.getStartTime(),
									schedule.getEndTime(),schedule.getDoctorId());
							if (sche == false) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.ScheduleAlreadyExist);
								se.setDisplayErrMsg(true);
								throw se;

							}
							dateList.add(start);

							count++;
						}
					}
					// save to doctor schedule table
					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			} else {
				// Monthily day of the week with end date
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				Calendar c = Calendar.getInstance();
				int day_of_week = c.get(Calendar.DAY_OF_WEEK);
				int week_of_month = c.get(Calendar.WEEK_OF_MONTH);
				String start = schedule.getStartDate();
				weeks = schedule.getWeeklySunThruSatList();
				fromDate = stringToDate(schedule.getStartDate());
				toDate = stringToDate(schedule.getEndDate());
				while (!fromDate.after(toDate)) {
					c.setTime(fromDate);
					if (week_of_month == c.get(Calendar.WEEK_OF_MONTH)) {
						if (day_of_week == c.get(Calendar.DAY_OF_WEEK)) {
							boolean sche = checkSchedule(start,
									schedule.getStartTime(),
									schedule.getEndTime(),schedule.getDoctorId());
							if (sche == false) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.ScheduleAlreadyExist);
								se.setDisplayErrMsg(true);
								throw se;

							}
							dateList.add(start);
							// response=scheduleDao.createSchedule(start,schedule.getStartTime(),schedule.getEndTime(),schedule.getStatus(),seriesId);
						}
					}
					c.add(Calendar.DATE, 1);
					start = df.format(c.getTime());
					fromDate = stringToDate(start);
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			}
		} else {
			dateList.clear();
			if (schedule.getNoOfOccurances() != 0) {
				// Monthily day of the month with occurances
				int count = 0;
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);

				while (count < schedule.getNoOfOccurances()) {
					boolean sche = checkSchedule(start,
							schedule.getStartTime(), schedule.getEndTime(),schedule.getDoctorId());
					if (sche == false) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.ScheduleAlreadyExist);
						se.setDisplayErrMsg(true);
						throw se;

					}
					// response=scheduleDao.createSchedule(start,schedule.getStartTime(),schedule.getEndTime(),schedule.getStatus(),seriesId);
					dateList.add(start);
					c.add(Calendar.MONTH, 1);
					start = df.format(c.getTime());
					count++;
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			} else {
				// Monthily day of the month with end date
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITHOUT_TIME);
				Calendar c = Calendar.getInstance();
				String start = schedule.getStartDate();
				fromDate = stringToDate(schedule.getStartDate());
				toDate = stringToDate(schedule.getEndDate());
				while (!fromDate.after(toDate)) {
					c.setTime(fromDate);
					boolean sche = checkSchedule(start,
							schedule.getStartTime(), schedule.getEndTime(),schedule.getDoctorId());
					if (sche == false) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.ScheduleAlreadyExist);
						se.setDisplayErrMsg(true);
						throw se;

					}
					// respone=scheduleDao.createSchedule(start,schedule.getStartTime(),schedule.getEndTime(),schedule.getStatus(),seriesId);
					dateList.add(start);
					c.add(Calendar.MONTH, 1);
					start = df.format(c.getTime());
					fromDate = stringToDate(start);
				}
				if (!dateList.isEmpty()) {
					for (int i = 0; i < dateList.size(); i++) {
						try {
							response = scheduleDao.createSchedule(dateList.get(i),
									schedule.getStartTime(), schedule.getEndTime(),
									schedule.getStatus(), seriesId,
									schedule.getDoctorId());
						} catch (PersistenceException e) {
							throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
						}
					}
				}
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	// convert string to Date
	public Date stringToDate(String Date) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			date = df.parse(Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @return the scheduleDao
	 */
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao
	 *            the scheduleDao to set
	 */
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/**
	 * @return the scheduleValidator
	 */
	public ScheduleValidator getScheduleValidator() {
		return scheduleValidator;
	}

	/**
	 * @param scheduleValidator
	 *            the scheduleValidator to set
	 */
	public void setScheduleValidator(ScheduleValidator scheduleValidator) {
		this.scheduleValidator = scheduleValidator;
	}

	/**
	 * update schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override

	public ResponseDTO update(ScheduleDTO schedule) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = scheduleValidator.validateUpdateSchedule(schedule);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		int seriesId;
		try {
			seriesId = scheduleDao.updateSeriesTbl(schedule);
		} catch (PersistenceException e1) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
		}
		if (schedule.getRepeat().equals(RepeatEnum.NONE.getDisplayName())) {
			// The repeat type is none
			// take the list of schedule with the series id and doctor id
			// scheduleDao.schedule(schedule);
			boolean sche;
			try {
				sche = scheduleDao.checkUpdateSchedule(schedule);
			} catch (PersistenceException e1) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
			}
			if (sche == false) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleAlreadyExist);
				se.setDisplayErrMsg(true);
				throw se;

			}
			try {
				response = scheduleDao.createSchedule(schedule.getStartDate(),
						schedule.getStartTime(), schedule.getEndTime(),
						schedule.getStatus(), seriesId, schedule.getDoctorId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}

		}
		return null;
	}

	/**
	 * Delete schedule.while deleting corresponding appointment also will delete
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		try {
			response = scheduleDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * delete a single schedule.while deleting corresponding appointment also
	 * will delete
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteThisInstanceSchedule(int id) throws ServiceException{
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		try {
			response = scheduleDao.deleteThisInstanceSchedule(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * delete from the particular schedule.while deleting corresponding
	 * appointment also will delete
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deletefollowingSchedule(int id) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = scheduleDao.deleteFollowingSchedule(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * current date schedule view
	 * 
	 * @param date
	 * @return ViewScheduleListDTO
	 */
	@Override
	public ViewScheduleListDTO dayView(String date,int doctorId)throws ServiceException {
		// TODO Auto-generated method stub
		ViewScheduleListDTO response = new ViewScheduleListDTO();
		ErrorDTO error = scheduleValidator.validateView(date,doctorId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = scheduleDao.dayView(date,doctorId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * single week view
	 * 
	 * @param date
	 * @return ScheduleListDTO
	 */
	@Override
	public ScheduleListDTO weeklyView(String date,int doctorId)throws ServiceException {
		Date fromDate;
		// TODO Auto-generated method stub
		ScheduleListDTO response = new ScheduleListDTO();
		ArrayList<ViewScheduleListDTO> ViewScheduleList = new ArrayList<ViewScheduleListDTO>();
		ViewScheduleListDTO response1 = new ViewScheduleListDTO();
		ErrorDTO error = scheduleValidator.validateView(date,doctorId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Calendar c = Calendar.getInstance();
		String start = date;
		// string to date
		fromDate = stringToDate(date);
		c.setTime(fromDate);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		// get start date
		c.add(Calendar.DATE, -(day_of_week - 1));
		// System.out.println(Calendar.DATE+"calnder");
		// System.out.println(c.getTime()+"=Time");
		start = df.format(c.getTime());
		for (int i = 0; i < 7; i++) {
			try {
				response1 = scheduleDao.dayView(start,doctorId);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			ViewScheduleList.add(response1);
			response.setScheduleList(ViewScheduleList);
			c.add(Calendar.DATE, 1);
			start = df.format(c.getTime());
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Monthly view
	 * 
	 * @param startDate
	 * @param endDate
	 * @return ScheduleListDTO
	 */
	@Override
	public ScheduleListDTO monthlyView(String startDate, String endDate,int doctorId) throws ServiceException {
		Date fromDate, toDate;

		// TODO Auto-generated method stub
		ScheduleListDTO response = new ScheduleListDTO();
		ArrayList<ViewScheduleListDTO> ViewScheduleList = new ArrayList<ViewScheduleListDTO>();
		ViewScheduleListDTO response1 = new ViewScheduleListDTO();
		// ErrorDTO error1=new ErrorDTO();
		ErrorDTO error = scheduleValidator.monthilyViewValidator(startDate,
				endDate,doctorId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Calendar c = Calendar.getInstance();
		String start = startDate;
		// string to date
		fromDate = stringToDate(startDate);
		toDate = stringToDate(endDate);

		c.setTime(fromDate);

		// check start date after end date
		while (!fromDate.after(toDate)) {

			c.setTime(fromDate);
			try {
				response1 = scheduleDao.dayView(start,doctorId);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			ViewScheduleList.add(response1);
			response.setScheduleList(ViewScheduleList);
			c.add(Calendar.DATE, 1);
			start = df.format(c.getTime());
			fromDate = stringToDate(start);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	public boolean checkSchedule(String date, String fromtime, String totime,int doctorId) throws ServiceException {
		Date fromTime, toTime, newDate;
		int test = 0;

		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			newDate = df1.parse(date);
			fromTime = df2.parse(fromtime);
			toTime = df2.parse(totime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<DoctorScheduleTimeDTO> scheduleList;
		try {
			scheduleList = (ArrayList<DoctorScheduleTimeDTO>)scheduleDao.getDoctorScheduleList(newDate,doctorId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}

		if (scheduleList.isEmpty()) {
			return true;
		} else {
			for (DoctorScheduleTimeDTO doctorScheduleTimeDTO : scheduleList) {

				if(fromTime.before(doctorScheduleTimeDTO.getStartingTime())){
					if(toTime.after(doctorScheduleTimeDTO.getStartingTime())){
						test++;
					}
				}
				if(fromTime.after(doctorScheduleTimeDTO.getStartingTime())){
					if(	fromTime.before(doctorScheduleTimeDTO.getEndingTime())){
						test++;
					}
				}
			}
			if (test != 0) {
				return false;
			}

		}
		return true;
	}

	/**
	 * schedule from YNW
	 * @param schedule
	 * @return ScheduleResponse
	 * @throws ServiceException 
	 */
	@Override
	public ScheduleResponse scheduleFromYNW(ScheduleDetail schedule) throws ServiceException {
		// TODO Auto-generated method stub
		scheduleValidator.validateScheduleDetails(schedule);
		ScheduleResponse response;
		try {
			response = scheduleDao.scheduleFromYNW(schedule);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
}
