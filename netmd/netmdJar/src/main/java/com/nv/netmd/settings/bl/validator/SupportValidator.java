/**
 * ServiceValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.SupportDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class SupportValidator {
	/**
	 * validate create service
	 * @param service 
	 * @throws ServiceException 
	 * 
	 */
	public void validateCreate(SupportDTO service) throws ServiceException {		
		if (!isValidName(service.getName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		

	}
	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	/**
	 * @param support
	 * @return
	 * @throws ServiceException 
	 */
	public ErrorDTO validateUpdate(SupportDTO support) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if(support.getId()<=0){
			error.setErrCode(ErrorCodeEnum.ServiceIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
	
		if(support.getPrice()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PriceShouldNotBeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return null;
	}
}
