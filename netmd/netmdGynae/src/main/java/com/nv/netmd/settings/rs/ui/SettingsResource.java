 /**
* settingResource.java
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
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.SettingListResponseDTO;
import com.nv.netmd.rs.dto.ViewSettingResponseDTO;
import com.nv.netmd.settings.bl.service.SettingsService;



/**
 * @author Nithesh Mohanan
 *
 */

@Controller
@RequestMapping("ui/settings/")
public class SettingsResource {
	private SettingsService settingsService;
		
	
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody SettingDTO setting){
		ResponseDTO response = new ResponseDTO();
		try {
			response = settingsService.create(setting);			

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
	@RequestMapping(value = "getByName/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ViewSettingResponseDTO getByName(@PathVariable String name){
		ViewSettingResponseDTO response = new ViewSettingResponseDTO();
		try {
			response = settingsService.getByName(name);			

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
	public SettingListResponseDTO getSettingsList(
			@RequestBody FilterDTO filterDTO) {
		SettingListResponseDTO response = new SettingListResponseDTO();
		try {
			response = settingsService.list(filterDTO);
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
	public ViewSettingResponseDTO view(@PathVariable int id) {
		ViewSettingResponseDTO response=new ViewSettingResponseDTO();
		try {
			response = settingsService.view(id);
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
	public ResponseDTO update(@RequestBody SettingDTO setting){
		ResponseDTO response = new ResponseDTO() ;
		try {		

			response = settingsService.update(setting);			

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
			response = settingsService.delete(id);
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
	 * @return the settingsService
	 */
	public SettingsService getSettingsService() {
		return settingsService;
	}

	/**
	 * @param settingsService the settingsService to set
	 */
	public void setSettingsService(SettingsService settingsService) {
		this.settingsService = settingsService;
	}

	
}

