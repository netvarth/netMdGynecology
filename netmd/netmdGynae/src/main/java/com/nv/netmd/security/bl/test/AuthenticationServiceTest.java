/**
 * AuthenticationServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.security.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.UserDetails;
import com.nv.netmd.security.bl.service.AuthenticationService;



@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:E:/NVWorkspace/netMdPC/resource/context.xml", "file:E:/NVWorkspace/netMdPC/resource/testDataSource.xml","file:E:/NVWorkspace/netMdPC/resource/netmd-context.xml" })
public class AuthenticationServiceTest {


	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void login() throws PersistenceException{	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("netvarth");
		login.setUserName("sreera");
		LoginResponseDTO  response = service.login(login);
		System.out.println("LoginResponseDTO "+response.isSuccess());
	}
	@Test
	public void user() throws ServiceException{	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");		
		UserDetails  response = service.getUser("Seeraj");
	}
	//		@Test
	//		public void getMessage(){	
	//			AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");	
	//			User user=new User();
	//			user.setDoctorId(0);
	//			user.setUserType(UserTypeEnum.Admin.getDisplayName());
	//			user.setId(6);
	//			try{
	//				 service.getMessage(user);
	//			}
	//			catch(ServiceException e){
	//				System.out.println(e.isDisplayErrMsg());
	//				System.out.println(e.getError());
	//				System.out.println(e.getParamList());
	//			}
	//		
	//	
	//	}
}