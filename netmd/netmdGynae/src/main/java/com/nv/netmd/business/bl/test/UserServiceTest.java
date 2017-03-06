/**
* UserServiceTest.java
* 
* @Author Sreeram
*
* Version 1.0 Jan 1, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.UserService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class UserServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void usercreate(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		
		UserDTO user=new UserDTO();
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");
		try {
			userservice.createUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userWithoutUserType(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.createUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userCreatenoUsername(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setPassword("diaui7d7t 78 d");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		user.setName("syuyu");
		try {
			userservice.createUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userCreateNoName(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		//user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.createUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void usercreateWithoutPswd(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		//user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.createUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdate(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdateWithOutName(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");

		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdateNoUserName(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);
		user.setPassword("diaui7d7t 78 d");

		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdateNoPswd(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);

		user.setUserName("sample");
		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdateNoGId(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();

		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");
		user.setUserType(UserTypeEnum.Admin.getDisplayName());
		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userUpdateWithOutUserType(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);
		user.setPassword("diaui7d7t 78 d");
		user.setUserName("sample");
		user.setName("syuyu");

		try {
			userservice.updateUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void delete(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();
		user.setGlobalId(1);
		try {
			userservice.deleteUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteNoGid(){
		UserService userservice=(UserService) applicationContext
				.getBean("user.Service");
		UserDTO user=new UserDTO();

		try {
			userservice.deleteUser(user);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
