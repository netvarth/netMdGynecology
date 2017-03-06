/**
 * ScheduleResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.netmd.business.bl.service.ScheduleService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleListDTO;
import com.nv.netmd.rs.dto.User;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
@Controller
@RequestMapping("ui/schedule/")
public class ScheduleResource {
	private ScheduleService scheduleService;

	/**
	 * create a schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(readOnly = false)
	public ResponseDTO CreateSchedule(@RequestBody ScheduleDTO schedule) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = scheduleService.create(schedule);
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
	 * update schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO UpdateSchedule(@RequestBody ScheduleDTO schedule) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = scheduleService.update(schedule);
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
	 * Delete schedule.while deleting corresponding appointment also will delete
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
				response = scheduleService.delete(id);
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
	 * delete a single schedule.while deleting corresponding appointment also
	 * will delete
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteInstance/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteThisInstanceSchedule(@PathVariable int id) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = scheduleService.deleteThisInstanceSchedule(id);
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
	 * delete from the particular schedule.while deleting corresponding
	 * appointment also will delete
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteFollowing/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deletefollowingSchedule(@PathVariable int id) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = scheduleService.deletefollowingSchedule(id);
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
	 * current date schedule view
	 * 
	 * @param date
	 * @param doctorId
	 * @return ViewScheduleListDTO
	 */
	@RequestMapping(value = "dayView/{date},{doctorId}", method = RequestMethod.GET)
	@ResponseBody
	public ViewScheduleListDTO dayView(@PathVariable String date,@PathVariable int doctorId) {
		ViewScheduleListDTO response = new ViewScheduleListDTO();
		try {
			response = scheduleService.dayView(date,doctorId);
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
	 * single week view
	 * 
	 * @param date
	 * @param doctorId
	 * @return ScheduleListDTO
	 */
	@RequestMapping(value = "weekly/{date},{doctorId}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleListDTO weeklyView(@PathVariable String date,@PathVariable int doctorId) {
		ScheduleListDTO response = new ScheduleListDTO();
		try {
			response = scheduleService.weeklyView(date,doctorId);
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
	 * Monthly view
	 * 
	 * @param startDate
	 * @param endDate
	 * @param doctorId
	 * @return ScheduleListDTO
	 */
	@RequestMapping(value = "monthly/{startDate},{endDate},{doctorId}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleListDTO monthlyView(@PathVariable String startDate,
			@PathVariable String endDate,@PathVariable int doctorId) {
		ScheduleListDTO response = new ScheduleListDTO();
		try {
			response = scheduleService.monthlyView(startDate, endDate,doctorId);
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

	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
}
