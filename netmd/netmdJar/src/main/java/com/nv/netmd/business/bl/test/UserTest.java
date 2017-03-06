/**
 * UserTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jun 26, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import org.junit.Test;

import com.nv.netmd.business.bl.impl.UserServiceImpl;
import com.nv.netmd.business.bl.validator.UserValidator;
import com.nv.netmd.business.pl.dao.UserDao;
import com.nv.netmd.business.pl.mockImpl.UserMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
public class UserTest {
	private UserValidator userValidator;
	private UserDao userDao;
	@Test
	public void usercreate(){
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
		UserServiceImpl userservice=new UserServiceImpl();
		userValidator=new UserValidator();
		userDao=new UserMockDaoImpl();
		userservice.setUserDao(userDao);
		userservice.setUserValidator(userValidator);
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
