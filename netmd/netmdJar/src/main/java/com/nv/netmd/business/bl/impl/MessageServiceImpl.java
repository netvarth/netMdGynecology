/**
 * MessageServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 May 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.service.MessageService;
import com.nv.netmd.business.pl.dao.MessageDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;


/**
 * 
 */
public class MessageServiceImpl implements MessageService {
	private FilterService messageFilterService;
	private MessageDao messageDao;
	/**
	 * get list of messages
	 * @param filterDTO
	 * @return MessageListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public MessageListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		MessageListResponseDTO messageList=new MessageListResponseDTO();
		ErrorDTO error=messageFilterService.validate(filterDTO);
		if (error != null) {
			messageList.setError(error);
			messageList.setSuccess(false);
			return messageList;
		}
		messageList =messageFilterService.list(filterDTO);
		return messageList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.MessageService#viewMessage(int)
	 */
	@Override
	public MessageDTO viewMessage(int id) throws ServiceException{
		MessageDTO messageDTO=new MessageDTO();
		try {
			messageDTO=messageDao.viewMessage(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return messageDTO;
	}

	/**
	 * @return the messageFilterService
	 */
	public FilterService getMessageFilterService() {
		return messageFilterService;
	}

	/**
	 * @param messageFilterService the messageFilterService to set
	 */
	public void setMessageFilterService(FilterService messageFilterService) {
		this.messageFilterService = messageFilterService;
	}

	/**
	 * @return the messageDao
	 */
	public MessageDao getMessageDao() {
		return messageDao;
	}

	/**
	 * @param messageDao the messageDao to set
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
