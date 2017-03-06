/**
 * ScheduleValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.OccuranceTypeEnum;
import com.nv.netmd.pl.entity.RepeatEnum;
import com.nv.netmd.pl.entity.ScheduleStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.SeriesDTO;

/**
 * 
 */
public class ScheduleValidator {
	public ErrorDTO validateCreateSchedule(ScheduleDTO schedule) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		ScheduleStatusEnum statusEnum = ScheduleStatusEnum.getEnum(schedule
				.getStatus());
		RepeatEnum repeat = RepeatEnum.getEnum(schedule.getRepeat());
		if (schedule.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		// validate start date
		if (schedule.getStartDate() == null
				|| !schedule.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date givenDate = df.parse(schedule.getStartDate());
			String newCurentdate= df.format(new Date());
             Date currentDate=df.parse(newCurentdate);
             System.out.println("given date"+givenDate);
             System.out.println("today's date="+currentDate);
			if(givenDate.before(currentDate)){
				ServiceException se=new ServiceException(ErrorCodeEnum.ScheduleDateBefore);
				se.setDisplayErrMsg(true);
				throw se;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		// validate time format
		if (schedule.getStartTime()==null
				|| schedule.getEndTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			// converting AM/PM to 24 hour format
			Date startTime = df2.parse(schedule.getStartTime());
			Date endTime = df2.parse(schedule.getEndTime());
			String s = df1.format(startTime);
			String e = df1.format(endTime);
			Date st = df1.parse(s);
			Date ed = df1.parse(e);
			if (st.after(ed)) {
				error.setErrCode(ErrorCodeEnum.StartTimeGreater.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	public ErrorDTO validateUpdateSchedule(ScheduleDTO schedule) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		// if(!isValidName(schedule.getName())){
		// error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
		// error.setDisplayErrMsg(true);
		// return error;
		// }
		ScheduleStatusEnum statusEnum = ScheduleStatusEnum.getEnum(schedule
				.getStatus());
		RepeatEnum repeat = RepeatEnum.getEnum(schedule.getRepeat());
		if (schedule.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (schedule.getSeriesId() <=0) {
			error.setErrCode(ErrorCodeEnum.SeriesIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		// validate start date
		if (schedule.getStartDate() == null
				|| !schedule.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(schedule.getStartDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		// validate Time
		if (schedule.getStartTime()==null
				|| schedule.getEndTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			// converting AM/PM to 24 hour format
			Date startTime = df2.parse(schedule.getStartTime());
			Date endTime = df2.parse(schedule.getEndTime());
			String s = df1.format(startTime);
			String e = df1.format(endTime);
			Date st = df1.parse(s);
			Date ed = df1.parse(e);
			if (st.after(ed)) {
				error.setErrCode(ErrorCodeEnum.StartTimeGreater.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}


	public ErrorDTO validateView(String date,int doctorId) {
		ErrorDTO error = new ErrorDTO();
		if (doctorId <= 0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		validateDate(date);
		return null;
	}
	public ErrorDTO validateDate(String date) {
		ErrorDTO error = new ErrorDTO();
		if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date startDate = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO monthilyViewValidator(String startDate, String endDate,int doctorId) {
		ErrorDTO error = new ErrorDTO();
		// List<Parameter> parameters = new ArrayList<Parameter>();
		// validate start date
		Date startingDate;
		Date endingDate;
		if (doctorId <= 0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (startDate == null || !startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (endDate == null || !endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			startingDate = df.parse(startDate);
			endingDate = df.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (startingDate.after(endingDate)) {
			error.setErrCode(ErrorCodeEnum.StartDateGreater.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}
	/**
	 * Method to validate schedule details
	 * @param scheduleDetail 
	 * 
	 * @param schedule
	 * @throws ServiceException 
	 */
	public void validateScheduleDetails(ScheduleDetail scheduleDetail) throws ServiceException {
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date startDate =null;
		
		if (scheduleDetail == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.ScheduleDetailNull);
			se.setDisplayErrMsg(true);
			throw se;
		} else {
			ScheduleStatusEnum statusEnum = ScheduleStatusEnum.getEnum(scheduleDetail.getScheduleStatus());	
			StatusEnum status=StatusEnum.getEnum(scheduleDetail.getStatus());
			if (scheduleDetail.getStartDate() == null
					|| scheduleDetail.getStartDate().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleStartDateNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				 startDate = sdf.parse(scheduleDetail.getStartDate());
			} catch (ParseException e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			if (scheduleDetail.getStartTime() == null
					|| scheduleDetail.getStartTime().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleStartTimeNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getEndTime() == null
					|| scheduleDetail.getEndTime().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleEndTimeNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getStartTime() != null
					&& scheduleDetail.getEndTime() != null) {
				try {
					Date startTime = df.parse(scheduleDetail.getStartTime());
					Date endTime = df.parse(scheduleDetail.getEndTime());
					if (startTime.after(endTime)) {

						ServiceException se = new ServiceException(
								ErrorCodeEnum.StartTimeGreater);
						se.setDisplayErrMsg(true);
						throw se;

					}

				} catch (ParseException e) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidTimeFormat);
					se.setDisplayErrMsg(true);
					throw se;

				}
			}
			
			if (scheduleDetail.getDoctorGlobalId() <= 0) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.DoctorIdNotNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if(scheduleDetail.getScheduleGlobalId()<=0){
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleGlobalIdNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getSeries() == null) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.SeriesIdNotNull);
				se.setDisplayErrMsg(true);
				throw se;

			} else
				if(scheduleDetail.getSeries().getEndDate()!=null){
					try {
						Date endDate = sdf.parse(scheduleDetail.getSeries().getEndDate());
						
						if (startDate.after(endDate)) {

							ServiceException se = new ServiceException(
									ErrorCodeEnum.StartDateGreater);
							se.setDisplayErrMsg(true);
							throw se;
						}

					} catch (ParseException e) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.InvalidTimeFormat);
						se.setDisplayErrMsg(true);
						throw se;

					}
				}
				validateScheduleSeriesDetails(scheduleDetail.getSeries());
		}
		
	}

	/**
	 * Method to validate schedule series details
	 * 
	 * @param series
	 * @throws ServiceException 
	 */
	public void validateScheduleSeriesDetails(SeriesDTO series) throws ServiceException {
		if (series.getSeriesGlobalId()<= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SeriesGlobalIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		OccuranceTypeEnum occuranceEnum = OccuranceTypeEnum.getEnum(series.getOccuranceType());
		RepeatEnum repeat = RepeatEnum.getEnum(series.getRepeat());
	
	}

}