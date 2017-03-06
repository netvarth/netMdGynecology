/**
 * SyncResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 11, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PassPhraseDTO;
import com.nv.netmd.sync.bl.service.SyncService;

/**
 * 
 */
@Controller
@RequestMapping("ui/sync/")
public class SyncResource {
	private SyncService syncService;
	/**
	 * Check whether the device already registered with YNW.If yes ask  them to install it again.
	 * @param passPhrase
	 * @return NetMdResponseDTO
	 */

//	@RequestMapping(value = "getMacStatus", method = RequestMethod.POST)
//	@ResponseBody
//	public NetMdResponseDTO getMacStatus(@RequestBody PassPhraseDTO passPhrase) {
//		NetMdResponseDTO response=new NetMdResponseDTO();
//		try{
//			response=syncService.getMacStatus(passPhrase);
//		}
//		catch (ServiceException e) {
//			List<Parameter> parameters =e.getParamList();
//			ErrorDTO error=new ErrorDTO();
//			error.setErrCode(e.getError().getErrCode());
//			error.setParams(parameters);
//			error.setDisplayErrMsg(e.isDisplayErrMsg());
//			response.setError(error);
//			response.setSuccess(false);
//		}
//		return response;
//	}
	/**
	 * Activation process of netMd
	 * @param passPhrase
	 * @return NetMdActivationResponseDTO
	 */
	@RequestMapping(value = "activateNetMd", method = RequestMethod.POST)
	@ResponseBody
	public NetMdActivationResponseDTO activateNetMd(@RequestBody PassPhraseDTO passPhrase) {
		NetMdActivationResponseDTO response=new NetMdActivationResponseDTO();
		try{
			response=syncService.activateNetMd(passPhrase);
		}
		catch (ServiceException e) {
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
	 * @return the syncService
	 */
	public SyncService getSyncService() {
		return syncService;
	}
	/**
	 * @param syncService the syncService to set
	 */
	public void setSyncService(SyncService syncService) {
		this.syncService = syncService;
	}
}
