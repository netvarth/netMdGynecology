/**
 * BedServiceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.settings.bl.service.BedService;





/**
 *
 *
 * @author Sreeram T G
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class BedServiceTest {
@Autowired 
private ApplicationContext applicationContext;
@Test
public void createBedType() throws ServiceException{
BedService bedService=(BedService)applicationContext.getBean("bed.Service");
	BedTypeDTO bedTypeDTO=new BedTypeDTO();
	bedTypeDTO.setType("sam");
	bedTypeDTO.setCount(1);
	bedTypeDTO.setRent(100);
	bedService.createBedType(bedTypeDTO);
}

}
