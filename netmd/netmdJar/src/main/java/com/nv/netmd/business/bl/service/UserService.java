/**
 * UserService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 1, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
public interface UserService {
	public ResponseDTO createUser(UserDTO user) throws ServiceException;

	public ResponseDTO updateUser(UserDTO user) throws ServiceException;

	public ResponseDTO deleteUser(UserDTO user) throws ServiceException;
}
