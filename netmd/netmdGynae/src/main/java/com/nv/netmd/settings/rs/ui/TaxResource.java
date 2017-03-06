/**
 * TaxResource.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
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
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.rs.dto.TaxViewResponseDTO;
import com.nv.netmd.settings.bl.service.TaxService;


/**
 *
 *
 * @author Leonora Louis
 */
@Controller
@RequestMapping("ui/tax/")
public class TaxResource {
	private TaxService taxService;
	@RequestMapping(value="create",method =RequestMethod.POST )
	@ResponseBody
	public ResponseDTO create(@RequestBody TaxDTO taxdto){
	  ResponseDTO response=new ResponseDTO();
	  try{
		  response= taxService.create(taxdto); 
	  }
	  catch(ServiceException se){
		ErrorDTO error = new ErrorDTO();
		error.setErrCode(se.getError().getErrCode());
		error.setDisplayErrMsg(se.isDisplayErrMsg());
		response.setError(error);
		response.setSuccess(false);	  
	  }
	  return response;
 	  		
	}
   public TaxService getTaxService() {
		return taxService;
	}
	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}
@RequestMapping(value="view/{id}",method=RequestMethod.GET)
   @ResponseBody
   	public TaxViewResponseDTO view(@PathVariable int id){
	   TaxViewResponseDTO response=new TaxViewResponseDTO();
	   try{
		   response=taxService.view(id);
	   }
	   catch(ServiceException e){
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
   @RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public ResponseDTO update(@RequestBody TaxDTO taxdto){
	   ResponseDTO response=new ResponseDTO();
	   try{
		   response=taxService.update(taxdto);
		   
	   }catch(ServiceException se){
		   
	   }
		return response;
	   
   }
   @RequestMapping(value="delete/{id}",method=RequestMethod.GET)
   @ResponseBody
   public ResponseDTO delete(@PathVariable int id){
	   ResponseDTO response=new ResponseDTO();
	   try{
		   response=taxService.delete(id);
		   
	   }catch(ServiceException e){
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
   @RequestMapping(value="list",method=RequestMethod.POST)
   @ResponseBody
   public TaxListResponseDTO getlist(@RequestBody FilterDTO filterDto){
	   TaxListResponseDTO response=new TaxListResponseDTO();
	   try{
		   response=taxService.getList(filterDto);
		   
	   }catch(ServiceException e){
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
  
   @RequestMapping(value="getTaxes",method=RequestMethod.GET)
   @ResponseBody
   public TaxListResponseDTO getTaxes(){
	   TaxListResponseDTO response=new TaxListResponseDTO();
	   try{
		   response=taxService.getTaxes();
		   
	   }catch(ServiceException e){
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
  
}
