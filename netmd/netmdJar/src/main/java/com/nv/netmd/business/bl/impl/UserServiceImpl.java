/**
 * UserServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 1, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.UserService;
import com.nv.netmd.business.bl.validator.UserValidator;
import com.nv.netmd.business.pl.dao.UserDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
public class UserServiceImpl implements UserService {

	private UserValidator userValidator;
	private UserDao userDao;

	/**
	 * creates an user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createUser(UserDTO user) throws ServiceException {

		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = userValidator.ValidateCreateUser(user);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = userDao.createUser(user);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * update an user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateUser(UserDTO user) throws ServiceException {

		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = userValidator.ValidateUpdateUser(user);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = userDao.updateUser(user);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * delete an user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteUser(UserDTO user) throws ServiceException {

		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = userValidator.ValidateUserDetails(user);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = userDao.deleteUser(user);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the userValidator
	 */
	public UserValidator getUserValidator() {
		return userValidator;
	}

	/**
	 * @param userValidator
	 *            the userValidator to set
	 */
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

}
