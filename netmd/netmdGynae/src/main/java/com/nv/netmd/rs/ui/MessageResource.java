/**
* MessageResource.java
* 
* @Author Sreeram
*
* Version 1.0 May 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.ui;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.business.bl.service.MessageService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;

/**
 * 
 */
@Controller
@RequestMapping("ui/message/")
public class MessageResource {
	private MessageService messageService;
	/**
	 * view messages
	 *@param id
	 * @return MessageListResponseDTO
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "viewMessage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public MessageDTO viewMessage(@PathVariable int id) throws ServiceException
	{
		MessageDTO response=new MessageDTO();
//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpServletRequest req = t.getRequest();
//		User user=(User) req.getSession().getAttribute(Constants.USER);
		response=messageService.viewMessage(id);
		return response;
	}
	/**
	 *  get new messages
	 *  @param filterDTO
	 * @return MessageListResponseDTO
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "getMessage", method = RequestMethod.POST)
	@ResponseBody
	public MessageListResponseDTO getMessage(@RequestBody FilterDTO filterDTO) throws ServiceException
	{
		MessageListResponseDTO response=new MessageListResponseDTO();
//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpServletRequest req = t.getRequest();
//		User user=(User) req.getSession().getAttribute(Constants.USER);
		response=messageService.list(filterDTO);
		return response;
	}
	/**
	 * @return the messageService
	 */
	public MessageService getMessageService() {
		return messageService;
	}
	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
