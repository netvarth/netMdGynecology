/**
 * DoctorResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 5, 2012
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

import com.nv.netmd.business.bl.service.DoctorService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorListAchievementDTO;
import com.nv.netmd.rs.dto.DoctorListExperienceDTO;
import com.nv.netmd.rs.dto.DoctorListExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListMembershipDTO;
import com.nv.netmd.rs.dto.DoctorListQualificationDTO;
import com.nv.netmd.rs.dto.DoctorListResponseDTO;
import com.nv.netmd.rs.dto.DoctorPersonalDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.User;


/**
 * 
 */
@Controller
@RequestMapping("ui/doctor/")
public class DoctorResource {
	private DoctorService doctorService;

	
	
	/**
	 * create doctor
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	//	@RequestMapping(value = "create", method = RequestMethod.POST)
	//	@ResponseBody
	//	public ResponseDTO create(@RequestBody DoctorDetail doctor) {
	//		// DoctorService doctorService=new DoctorServiceImpl();
	//		ResponseDTO response = new ResponseDTO();
	//		try {
	//			response = doctorService.create(doctor);
	//		} catch (ServiceException e) {
	//
	//			List<Parameter> parameters = e.getParamList();
	//			ErrorDTO error = new ErrorDTO();
	//			error.setErrCode(e.getError().getErrCode());
	//			error.setParams(parameters);
	//			error.setDisplayErrMsg(e.isDisplayErrMsg());
	//			response.setError(error);
	//			response.setSuccess(false);
	//
	//		}
	//		return response;
	//
	//	}

	/**
	 * View a doctor by giving input as id
	 * 
	 * @param id
	 * @return DoctorDetail
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	public DoctorDetail view(@PathVariable int id) {
		DoctorDetail response = new DoctorDetail();
		try {
			response = doctorService.view(id);
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
	 * delete a doctor by giving doctor id
	 * @param uid
	 * @return ResponseDTO
	 */
	@RequestMapping(value =  "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseDTO delete(@PathVariable int id){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO  response=new ResponseDTO();
		try{			
			if(user.isPrimaryDevice()==true){
				response=doctorService.delete(id);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		catch(ServiceException e){

			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}		
		return response;
	}

	/**
	 * Update doctor details
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	//	@RequestMapping(value = "update", method = RequestMethod.POST)
	//	@ResponseBody
	//	public ResponseDTO update(@RequestBody DoctorDetail doctor) {
	//		// DoctorService doctorService=new DoctorServiceImpl();
	//		ResponseDTO response = new ResponseDTO();
	//		try {
	//			response = doctorService.update(doctor);
	//		} catch (ServiceException e) {
	//
	//			List<Parameter> parameters = e.getParamList();
	//			ErrorDTO error = new ErrorDTO();
	//			error.setErrCode(e.getError().getErrCode());
	//			error.setParams(parameters);
	//			error.setDisplayErrMsg(e.isDisplayErrMsg());
	//			response.setError(error);
	//			response.setSuccess(false);
	//
	//		}
	//		return response;
	//
	//	}

	/**
	 * list of doctor
	 * 
	 * @param filterDTO
	 * @return DoctorListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public DoctorListResponseDTO getDoctorList(@RequestBody FilterDTO filterDTO) {
		DoctorListResponseDTO response = new DoctorListResponseDTO();
		try {
			response = doctorService.list(filterDTO);
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
	 * create personal details
	 * @param doctor
	 * @return 
	 */
	@RequestMapping(value = "createPersonalInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createPersonalInfo(@RequestBody DoctorPersonalDTO doctor) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);

		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.createPersonalInfo(doctor);
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
	 * update personal information of a doctor
	 * @param doctor
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updatePersonalInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updatePersonalInfo(@RequestBody DoctorPersonalDTO doctor) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.updatePersonalInfo(doctor);
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
	 * doctor qualification 
	 * @param qualification
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "doctorQualification", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO doctorQualification(@RequestBody DoctorListQualificationDTO qualification) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.doctorQualification(qualification);
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
	 * doctor achievement
	 * @param achievement
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "doctorAchievement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO doctorAchievement(@RequestBody DoctorListAchievementDTO achievement) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.doctorAchievement(achievement);
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
	 * Doctor experience
	 * @param experience
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "doctorExperience", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO doctorExperience(@RequestBody DoctorListExperienceDTO experience) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.doctorExperience(experience);
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
	 * Doctor expertise
	 * @param expertise
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "doctorExpertise", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO doctorExpertise(@RequestBody DoctorListExpertiseDTO expertise) {
		// DoctorService doctorService=new DoctorServiceImpl();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.doctorExpertise(expertise);
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
	 * doctor membership
	 * @param membership
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "doctorMemberShip", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO doctorMembership(@RequestBody DoctorListMembershipDTO membership) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = doctorService.doctorMembership(membership);
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
	 * @return the doctorService
	 */
	public DoctorService getDoctorService() {
		return doctorService;
	}

	/**
	 * @param doctorService
	 *            the doctorService to set
	 */
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

}
