/**
 * ResultResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.business.bl.service.ResultService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;

/**
 * 
 */
@Controller
@RequestMapping("ui/result/")
public class ResultResource {
	private ResultService resultService;

	public ResultService getResultService() {
		return resultService;
	}

	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	/**
	 * view single result
	 * 
	 * @param id
	 * @return ViewResultDTO
	 */
	@RequestMapping(value = "viewResult/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ViewResultDTO viewResult(@PathVariable int id) {
		ViewResultDTO response = new ViewResultDTO();
		try {
			response = resultService.viewResult(id);
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

//	/**
//	 * list of patient result
//	 * 
//	 * @param id
//	 * @return ResultListResponseDTO
//	 */
//	@RequestMapping(value = "list/{patientId}", method = RequestMethod.GET)
//	@ResponseBody
//	public ResultListResponseDTO listResult(@PathVariable int patientId) {
//		ResultListResponseDTO response = new ResultListResponseDTO();
//		try {
//			response = resultService.listResult(patientId);
//		} catch (ServiceException e) {
//			List<Parameter> parameters = e.getParamList();
//			ErrorDTO error = new ErrorDTO();
//			error.setErrCode(e.getError().getErrCode());
//			error.setParams(parameters);
//			error.setDisplayErrMsg(e.isDisplayErrMsg());
//			response.setError(error);
//			response.setSuccess(false);
//		}
//		return response;
//	}
	/**
	 * list of patient result by filter
	 * 
	 * @param filterDTO
	 * @return ResultListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody 
	public ResultListResponseDTO list(@RequestBody FilterDTO filterDTO){
		ResultListResponseDTO response = new ResultListResponseDTO();
		try {
			response = resultService.list(filterDTO);
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
	
}
