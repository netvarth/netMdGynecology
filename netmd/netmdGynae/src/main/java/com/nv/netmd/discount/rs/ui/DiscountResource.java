/**
 * DiscountResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.discount.bl.service.DiscountService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.DiscountValueDTO;
import com.nv.netmd.rs.dto.DiscountValueResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;


/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/discount/")
public class DiscountResource {
	private DiscountService discountService;
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody DiscountDTO discount){
		ResponseDTO response = new ResponseDTO();
		try {
			response = discountService.create(discount);			

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
	public ResponseDTO update(@RequestBody DiscountDTO discount){
		ResponseDTO response = new ResponseDTO();
		try {
			response = discountService.update(discount);			

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
	public DiscountListResponseDTO list(@RequestBody FilterDTO filterDto){
		DiscountListResponseDTO response = new DiscountListResponseDTO();
		try {
			response = discountService.list(filterDto);			

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
	@RequestMapping(value = "getDiscounts", method = RequestMethod.GET)
	@ResponseBody
	public DiscountListResponseDTO getDiscounts(){
		DiscountListResponseDTO response = new DiscountListResponseDTO();
		try {
			response = discountService.getDiscounts();			

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
	public DiscountDTO view(@PathVariable int id) {
		DiscountDTO response=new DiscountDTO();
		try {
			response = discountService.view(id);			

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
	 * give grand total and find discount and net amount
	 * @param discount
	 * @return DiscountValueResponseDTO
	 */
	@RequestMapping(value = "getDiscountValue", method = RequestMethod.POST)
	@ResponseBody
	public DiscountValueResponseDTO getDiscountValue(@RequestBody DiscountValueDTO discount) {

		DiscountValueResponseDTO  response=new DiscountValueResponseDTO();		
		try
		{
			response=discountService.getDiscountValue(discount);
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
	 * @return the discountService
	 */
	public DiscountService getDiscountService() {
		return discountService;
	}
	/**
	 * @param discountService the discountService to set
	 */
	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}
}
