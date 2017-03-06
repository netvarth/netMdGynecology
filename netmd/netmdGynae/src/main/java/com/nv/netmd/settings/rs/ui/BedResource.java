/**
 * BedResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
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
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.BedService;

/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/bed/")
public class BedResource {

	private BedService bedService;
	@RequestMapping(value = "createBedType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody BedTypeDTO bedType){
		ResponseDTO response = new ResponseDTO();
		try {
			response = bedService.createBedType(bedType);			

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
	public BedService getBedService() {
		return bedService;
	}
	public void setBedService(BedService bedService) {
		this.bedService = bedService;
	}
	@RequestMapping(value = "listBedType", method = RequestMethod.POST)
	@ResponseBody
	public BedTypeListResponseDTO getBedTypeList(
			@RequestBody FilterDTO filterDTO) {
		BedTypeListResponseDTO response = new BedTypeListResponseDTO();
		try {
			response = bedService.list(filterDTO);
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



	@RequestMapping(value = "viewBedType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BedTypeDTO viewBedType(@PathVariable int id){
		BedTypeDTO response=new BedTypeDTO();
		try{
			response=bedService.viewBedType(id);
		}catch(ServiceException e){

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

	@RequestMapping(value = "updateBedType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateBedType(@RequestBody BedTypeDTO bedType) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = bedService.updateBedType(bedType);
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


	@RequestMapping(value = "deleteBedType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteBedType(@PathVariable int id) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = bedService.deleteBedType(id);
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
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createBed(@RequestBody BedDTO bed){
		ResponseDTO response = new ResponseDTO();
		try {
			response = bedService.createBed(bed);		

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
	public BedDTO viewBed(@PathVariable int id){
		BedDTO response=new BedDTO();
		try{
			response=bedService.viewBed(id);
		}catch(ServiceException e){

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
	public ResponseDTO updateBed(@RequestBody BedDTO bed) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = bedService.updateBed(bed);
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


	@RequestMapping(value = "deleteBed/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteBed(@PathVariable int id) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = bedService.deleteBed(id);
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

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public BedListResponseDTO getBedList(
			@RequestBody FilterDTO filterDTO) {
		BedListResponseDTO response = new BedListResponseDTO();
		try {
			response = bedService.listBed(filterDTO);
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
	@RequestMapping(value = "getBeds", method = RequestMethod.GET)
	@ResponseBody
	public BedListResponseDTO getBeds() {
		BedListResponseDTO response = new BedListResponseDTO();
		try {
			response = bedService.getBeds();
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
	@RequestMapping(value = "getBedTypes", method = RequestMethod.GET)
	@ResponseBody
	public BedTypeListResponseDTO getBedTypes() {
		BedTypeListResponseDTO response = new BedTypeListResponseDTO();
		try {
			response = bedService.getBedTypes();
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
