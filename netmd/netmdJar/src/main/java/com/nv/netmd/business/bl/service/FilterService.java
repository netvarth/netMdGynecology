/**
* FileterService.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 22, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;

/**
 * 
 */
public interface FilterService {

	public<T>T list(FilterDTO filterdto) throws ServiceException;
	public ErrorDTO validate(FilterDTO filterdto) throws ServiceException;
	
}
