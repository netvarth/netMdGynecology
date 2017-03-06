/**
* MessageTest.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 27, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nv.netmd.business.bl.impl.MessageServiceImpl;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.MessageDao;
import com.nv.netmd.business.pl.mockImpl.MessageMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;

/**
 * 
 */
public class MessageTest {
	FilterService messageFilterService;
	MessageDao messageDao;
	@Test
	public void list() throws ServiceException{
		MessageServiceImpl mesgService = new MessageServiceImpl();
		messageDao=new MessageMockDaoImpl();			
		messageFilterService=new MessageMockDaoImpl();
		mesgService.setMessageDao(messageDao);
		mesgService.setMessageFilterService(messageFilterService);
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("id");
		exp.setOperator("eq");
		exp.setValue("16");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		MessageListResponseDTO response = mesgService.list(filter);
		System.out.println(response.getMessageDTO().size());
		for ( MessageDTO pat : response.getMessageDTO()) {
			System.out.println(pat.getMessage());
		}
	}
	@Test
	public void listInvalidOperator() throws ServiceException{
		MessageServiceImpl mesgService = new MessageServiceImpl();
		messageDao=new MessageMockDaoImpl();			
		messageFilterService=new MessageMockDaoImpl();
		mesgService.setMessageDao(messageDao);
		mesgService.setMessageFilterService(messageFilterService);
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("id");
		exp.setOperator("eqn");
		exp.setValue("16");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		MessageListResponseDTO response = mesgService.list(filter);
		System.out.println(response.getMessageDTO().size());
		for ( MessageDTO pat : response.getMessageDTO()) {
			System.out.println(pat.getMessage());
		}
	}
	@Test
	public void view(){
		MessageServiceImpl mesgService = new MessageServiceImpl();
		messageDao=new MessageMockDaoImpl();			
		
		mesgService.setMessageDao(messageDao);
		try{
			mesgService.viewMessage(1);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
