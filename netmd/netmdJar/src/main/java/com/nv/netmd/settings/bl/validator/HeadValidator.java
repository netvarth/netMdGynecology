 /**
* headValidator.java
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
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.HeadDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public class HeadValidator {
	/**
	 * validate create head
	 * @param head
	 * @throws ServiceException 
	 */
	public static void validateCreateHead(HeadDTO head) throws ServiceException {	
			if (!isValidHeadName(head.getHeadName())) {
			ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidHeadName);
				se.setDisplayErrMsg(true);
				throw se;
	
			}
		}

	public void validateUpdateHead(HeadDTO head) throws ServiceException {
		if(head.getId()<=0){
			ServiceException se=new  ServiceException(ErrorCodeEnum.HeadIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidHeadName(head.getHeadName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadName);
			se.setDisplayErrMsg(true);
			throw se;

		}

		
	}

	private static boolean isValidHeadName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	
	}

