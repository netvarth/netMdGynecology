/**
 * DepartmentResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.DepartmentService;


/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/department/")
public class DepartmentResource {
	
	private DepartmentService departmentService;
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody DepartmentDTO department){
		ResponseDTO response = new ResponseDTO();
		try {
			response = departmentService.create(department);			

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
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO update(@RequestBody DepartmentDTO department){
		ResponseDTO response = new ResponseDTO();
		try {
			response = departmentService.update(department);			

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
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	public DepartmentDTO view(@PathVariable int id) {
		DepartmentDTO response=new DepartmentDTO();
		try {
			response = departmentService.view(id);
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
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO delete(@PathVariable int id) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = departmentService.delete(id);
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
	 * department list
	 * 
	 * @param filterDTO
	 * @return DepartmentListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public DepartmentListResponseDTO getDepartmentList(
			@RequestBody FilterDTO filterDTO) {
		DepartmentListResponseDTO response = new DepartmentListResponseDTO();
		try {
			response = departmentService.list(filterDTO);
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
	 * get departments 
	 * 
	 *
	 * @return DepartmentListResponseDTO
	 */
	@RequestMapping(value = "getDepartments", method = RequestMethod.GET)
	@ResponseBody
	public DepartmentListResponseDTO getDepartments() {
		DepartmentListResponseDTO response = new DepartmentListResponseDTO();
		try {
			response = departmentService.getDepartments();
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
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	/**
	 * @param departmentService the departmentService to set
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
