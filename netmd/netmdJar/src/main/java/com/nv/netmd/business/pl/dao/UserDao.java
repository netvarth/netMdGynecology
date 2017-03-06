/**
 * UserDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 1, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.UserDTO;



/**
 * 
 */
public interface UserDao {
	public ResponseDTO createUser(UserDTO user) throws PersistenceException;
	public ResponseDTO updateUser(UserDTO user)throws PersistenceException;
	public ResponseDTO deleteUser(UserDTO user)throws PersistenceException;
}
