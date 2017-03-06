/**
 * ServiceResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
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
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;
import com.nv.netmd.settings.bl.service.SupportService;


/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/support/")
public class SupportResource {
	private SupportService supportService;
@RequestMapping(value="create",method=RequestMethod.POST)
@ResponseBody
public ResponseDTO create(@RequestBody SupportDTO service){
	ResponseDTO response=new ResponseDTO();
	try {
		response = supportService.create(service);			

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
@RequestMapping(value="list",method=RequestMethod.POST)
@ResponseBody
public SupportListResponseDTO getSupportlist(@RequestBody FilterDTO filterdto){
	SupportListResponseDTO response=new SupportListResponseDTO();
	try {
		response = supportService.list(filterdto);			

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

@RequestMapping(value="view/{id}",method=RequestMethod.GET)
@ResponseBody
public SupportDTO view(@PathVariable int id){
	SupportDTO response=new SupportDTO();
	try {
		response = supportService.view(id);			

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
public ResponseDTO update(@RequestBody SupportDTO support) {
	ResponseDTO response=new ResponseDTO();
	try {
		response = supportService.update(support);
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
		response = supportService.delete(id);
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

@RequestMapping(value="getSupports",method=RequestMethod.GET)
@ResponseBody
public SupportListResponseDTO getSupports(){
	SupportListResponseDTO response=new SupportListResponseDTO();
	try {
		response = supportService.getSupports();			

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

public SupportService getSupportService() {
	return supportService;
}
public void setSupportService(SupportService supportService) {
	this.supportService = supportService;
}

}
