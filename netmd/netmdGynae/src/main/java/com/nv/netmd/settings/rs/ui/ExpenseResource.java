 /**
* expenseResource.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 11, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
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


import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.ExpenseService;



/**
 * @author Nithesh Mohanan
 *
 */

@Controller
@RequestMapping("ui/expense/")
public class ExpenseResource {
	private ExpenseService expenseService;
		
	
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody ExpenseDTO expense){
		ResponseDTO response = new ResponseDTO();
		try {
			response = expenseService.create(expense);			

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
	public ExpenseListResponseDTO getExpenseList(
			@RequestBody FilterDTO filterDTO) {
		ExpenseListResponseDTO response = new ExpenseListResponseDTO();
		try {
			response = expenseService.list(filterDTO);
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
	public ExpenseViewDTO view(@PathVariable int id) {
		ExpenseViewDTO response=new ExpenseViewDTO();
		try {
			response = expenseService.view(id);
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
	public ResponseDTO update(@RequestBody ExpenseDTO expense){
		ResponseDTO response = new ResponseDTO() ;
		try {		

			response = expenseService.update(expense);			

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
			response = expenseService.delete(id);
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
	 * @return the expenseService
	 */
	public ExpenseService getExpenseService() {
		return expenseService;
	}



	/**
	 * @param expenseService the expenseService to set
	 */
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
}

