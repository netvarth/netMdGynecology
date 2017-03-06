/**
 * ItemValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ItemDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemValidator {

	
	/**
	 * validate create item
	 * @param item
	 * @throws ServiceException 
	 */
	public void validateCreateItem(ItemDTO item) throws ServiceException {		
		if (!isValidName(item.getName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}

	}
	/**
	 * validate update item
	 * @param item
	 * @throws ServiceException 
	 */
	public void validateUpdateItem(ItemDTO item) throws ServiceException {		
		if(item.getId()<=0){
			ServiceException se=new  ServiceException(ErrorCodeEnum.ItemIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(item.getName())) {
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
}
