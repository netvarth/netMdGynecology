/**
* MessageService.java
* 
* @Author Sreeram
*
* Version 1.0 May 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;


/**
 * 
 */
public interface MessageService {
	public MessageListResponseDTO list(FilterDTO filter) throws ServiceException;
	public MessageDTO viewMessage(int id) throws ServiceException;
}
