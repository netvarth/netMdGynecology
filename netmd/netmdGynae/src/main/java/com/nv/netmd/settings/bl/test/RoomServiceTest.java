/**
 * RoomServiceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.settings.bl.service.RoomService;

/**
 *
 *
 * @author Sreeram T G
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class RoomServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void createRoomType() throws ServiceException{
		RoomService roomService=(RoomService)applicationContext.getBean("room.Service");
		RoomTypeDTO roomType=new RoomTypeDTO();
		roomType.setType("AC");
		roomType.setRent(1220);
		roomType.setNoOfBeds(2);
		roomType.setCount(1);
		roomService.createRoomType(roomType);
	}
	@Test
	public void filter() throws ServiceException{
		RoomService roomService=(RoomService)applicationContext.getBean("room.Service");
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		//			ExpressionDTO exp = new ExpressionDTO();
		//			exp.setName("name");
		//			exp.setOperator("eq");
		//			exp.setValue("sree");	
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("status");
		exp1.setOperator("eq");
		exp1.setValue("Active");	
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		//exps.add(exp);
		exps.add(exp1);
		filter.setExp(exps);
		RoomListResponseDTO response = roomService.list(filter);
		System.out.println(response.getRoom().size());
		for (RoomDTO rm : response.getRoom()) {
			System.out.println(rm.getRoomNumber());

		}
	}
}
