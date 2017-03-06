/**
 * AppointmentResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.netmd.business.bl.service.AppointmentService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.User;

/**
 * 
 */
@Controller
@RequestMapping("ui/appointment/")
public class AppointmentResource {
	private AppointmentService appointmentService;

	/**
	 * create appointment
	 * 
	 * @param appointment
	 * @return AppointmentResponseDTO
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponseDTO create(
			@RequestBody AppointmentDTO appointment) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = appointmentService.create(appointment);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}


		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * update created appointments
	 * 
	 * @param appointment
	 * @return AppointmentResponseDTO
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponseDTO update(
			@RequestBody AppointmentDTO appointment) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = appointmentService.update(appointment);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * delete created appointment
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO delete(@PathVariable int id) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = appointmentService.delete(id);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * view appointment
	 * 
	 * @param doctroId
	 *            @param date
	 * @return AppointmentListResponseDTO
	 */
	@RequestMapping(value = "view/{doctroId},{date}", method = RequestMethod.GET)
	@ResponseBody
	public AppointmentListResponseDTO view(@PathVariable int doctroId,
			@PathVariable String date) {
		AppointmentListResponseDTO response = new AppointmentListResponseDTO();
		try {
			response = appointmentService.view(doctroId, date);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * @return the appointmentService
	 */
	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	/**
	 * @param appointmentService
	 *            the appointmentService to set
	 */
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
}
