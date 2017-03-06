/**
 * MessageServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 May 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.MessageService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
public class MessageServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void filter() throws ServiceException {
		MessageService messageService = (MessageService) applicationContext
				.getBean("message.Service");

		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("doctorId");
		exp.setOperator("eq");
		exp.setValue("16");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		MessageListResponseDTO response = messageService.list(filter);
		System.out.println(response.getMessageDTO().size());
		for (MessageDTO msg : response.getMessageDTO()) {
			System.out.println(msg.getMessage());

		}

	}
	@Test
	public void viewMessage() throws ServiceException {
		MessageService messageService = (MessageService) applicationContext
				.getBean("message.Service");

		MessageDTO response = messageService.viewMessage(2);


	}


}
