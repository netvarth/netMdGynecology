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

//import static org.easymock.EasyMock.createStrictMock;
//import static org.easymock.EasyMock.eq;
//import static org.easymock.EasyMock.expect;
//import static org.easymock.EasyMock.replay;
//import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.impl.BedServiceImpl;
import com.nv.netmd.settings.bl.validator.BedValidator;
import com.nv.netmd.settings.pl.dao.BedDao;


/**
 *
 *
 * @author Sreeram T G
 */
public class BedServiceTest {
	private BedServiceImpl service;
	private BedDao mockDao;
	private BedValidator bedValidator;
	/**
	 * setUp overrides the default, empty implementation provided by
	 * JUnit's TestCase.  We will use it to instantiate our required
	 * objects so that we get a clean copy for each test.
	 */
	@Before
	public void setUp() {
		bedValidator=new BedValidator();
		service = new BedServiceImpl();
//		mockDao = createStrictMock(BedDao.class);
		service.setBedValidator(bedValidator);
		service.setBedDao(mockDao);
	}

	/**
	 * 
	 */
	@Test
	public void createBedType(){    	
//		ResponseDTO response=new ResponseDTO();
//		response.setError(null);
//		response.setSuccess(true);
//		BedTypeDTO bedTypeDTO=new BedTypeDTO();
//		bedTypeDTO.setType("sam");
//		bedTypeDTO.setCount(1);
//		bedTypeDTO.setRent(100);
//		expect(mockDao.createBedType(eq(bedTypeDTO))).andReturn(response);
//		replay(mockDao);    	 	 
//		assertEquals(response.isSuccess(), service.createBedType(bedTypeDTO).isSuccess());
//		verify(mockDao);


	}
}
