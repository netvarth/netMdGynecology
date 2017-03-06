 /**
* AdvanceResource.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.billing.bl.service.AdvanceService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.AdvanceListResponseDTO;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;

@Controller
@RequestMapping("ui/advance/")
public class AdvanceResource {
	private AdvanceService advanceService;
	/**
	 * create advance
	 * @param advance
	 * @return ResponseDTO
	 */
 
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody AdvanceDTO advance){
		ResponseDTO response = new ResponseDTO();
		try {
			response = advanceService.create(advance);			

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
	public ResponseDTO update(@RequestBody AdvanceDTO advance){
		ResponseDTO response = new ResponseDTO() ;
		try {		

			response = advanceService.update(advance);		

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
	public AdvanceDTO view(@PathVariable int id) {
		AdvanceDTO response=new AdvanceDTO();
		try {
			response = advanceService.view(id);
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
			response = advanceService.delete(id);
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
	public AdvanceListResponseDTO getAdvanceList(
			@RequestBody FilterDTO filterDTO) {
		AdvanceListResponseDTO response = new AdvanceListResponseDTO();
		try {
			response = advanceService.list(filterDTO);
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

	public AdvanceService getAdvanceService() {
		return advanceService;
	}

	public void setAdvanceService(AdvanceService advanceService) {
		this.advanceService = advanceService;
	}
}
