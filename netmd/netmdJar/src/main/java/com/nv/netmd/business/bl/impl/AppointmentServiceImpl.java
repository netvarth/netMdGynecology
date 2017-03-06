/**
 * AppointmentServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.AppointmentService;
import com.nv.netmd.business.bl.validator.AppointmentValidator;
import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class AppointmentServiceImpl implements AppointmentService {
	private AppointmentDao appointmentDao;
	private AppointmentValidator appointmentValidator;

	/**
	 * @return the appointmentDao
	 */
	public AppointmentDao getAppointmentDao() {
		return appointmentDao;
	}

	/**
	 * @param appointmentDao
	 *            the appointmentDao to set
	 */
	public void setAppointmentDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	/**
	 * @return the appointmentValidator
	 */
	public AppointmentValidator getAppointmentValidator() {
		return appointmentValidator;
	}

	/**
	 * @param appointmentValidator
	 *            the appointmentValidator to set
	 */
	public void setAppointmentValidator(
			AppointmentValidator appointmentValidator) {
		this.appointmentValidator = appointmentValidator;
	}

	/**
	 * create appointment
	 * 
	 * @param appointment
	 * @return AppointmentResponseDTO
	 */
	@Override
	public AppointmentResponseDTO create(AppointmentDTO appointment) throws ServiceException {
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = appointmentDao.create(appointment);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * update created appointments
	 * 
	 * @param appointment
	 * @return AppointmentResponseDTO
	 */
	@Override
	public AppointmentResponseDTO update(AppointmentDTO appointment) throws ServiceException {
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		ErrorDTO error = appointmentValidator
				.validateUpdateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = appointmentDao.update(appointment);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * delete created appointment
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = appointmentDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * view appointment
	 * 
	 * @param doctroId
	 *            ,date
	 * @return AppointmentListResponseDTO
	 */
	@Override
	public AppointmentListResponseDTO view(int doctorId, String date) throws ServiceException{
		// TODO Auto-generated method stub
		AppointmentListResponseDTO response = new AppointmentListResponseDTO();
		ErrorDTO error = appointmentValidator.validateView(doctorId, date);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = appointmentDao.view(doctorId, date);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.AppointmentService#createAppointment(com.nv.netmd.rs.dto.AppointmentDTO)
	 */
	@Override
	public AppointmentResponse appointmentFromYNW(AppointmentDTO appointment) throws ServiceException {
		// TODO Auto-generated method stub
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateAppointmentFromYNW(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = appointmentDao.appointmentFromYNW(appointment);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	//	/* (non-Javadoc)
	//	 * @see com.nv.netmd.business.bl.service.AppointmentService#updateAppointment(com.nv.netmd.rs.dto.AppointmentDTO)
	//	 */
	//	@Override
	//	public AppointmentResponse updateAppointmentFromYNW(AppointmentDTO appointment) {
	//		// TODO Auto-generated method stub
	//		AppointmentResponse response = new AppointmentResponse();
	//		ErrorDTO error = appointmentValidator
	//				.validateAppointmentFromYNW(appointment);
	//		if (error != null) {
	//			response.setError(error);
	//			response.setSuccess(false);
	//			return response;
	//		}
	//		response = appointmentDao.updateAppointmentFromYNW(appointment);
	//		return response;
	//	
	//	}
	//	
	//	@Override
	//	public AppointmentResponse deleteAppointmentFromYNW(AppointmentDTO appointment) {
	//		// TODO Auto-generated method stub
	//		AppointmentResponse response = new AppointmentResponse();
	//		ErrorDTO error = appointmentValidator
	//				.validateAppointmentFromYNW(appointment);
	//		if (error != null) {
	//			response.setError(error);
	//			response.setSuccess(false);
	//			return response;
	//		}
	//		response = appointmentDao.deleteAppointmentFromYNW(appointment);
	//		return response;
	//	
	//	}
}
