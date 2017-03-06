/**
 * AppointmentValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
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
import com.nv.netmd.pl.entity.AppointmentStatusEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.ErrorDTO;


/**
 * 
 */
public class AppointmentValidator {
	// validate appointment creation
	public ErrorDTO validateCreateAppointment(AppointmentDTO appointment) {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (appointment.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}		
		if (appointment.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getScheduleId() <=0) {
			error.setErrCode(ErrorCodeEnum.ScheduleIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartDate() == null
				|| !appointment.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			Date startTime = df1.parse(appointment.getStartTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		
		
		return null;
	}
	// validate appointment from YNW
	public ErrorDTO validateAppointmentFromYNW(AppointmentDTO appointment) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();		
		
		if (appointment.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}	
		if (appointment.getGlobalId() <=0) {
			error.setErrCode(ErrorCodeEnum.GlobalIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}		
		if (appointment.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getScheduleId() <=0) {
			error.setErrCode(ErrorCodeEnum.ScheduleIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartDate() == null
				|| !appointment.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			Date startTime = df1.parse(appointment.getStartTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		AppointmentStatusEnum appointmentStatus=AppointmentStatusEnum.getEnum(appointment.getAppointmentStatus());
		StatusEnum statusEnum=StatusEnum.getEnum(appointment.getStatus());
		
		return null;
	}
	// validate appointment update
	public ErrorDTO validateUpdateAppointment(AppointmentDTO appointment) {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (appointment.getId() <=0) {
			error.setErrCode(ErrorCodeEnum.InvalidAppointment.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}	
		if (appointment.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getScheduleId() <=0) {
			error.setErrCode(ErrorCodeEnum.ScheduleIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartDate() == null
				|| !appointment.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIME);
		try {
			Date startTime = df1.parse(appointment.getStartTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	// validate appointment view
	public ErrorDTO validateView(int doctorId, String date) {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (doctorId <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
}
