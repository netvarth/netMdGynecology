/**
 * AuthenticationDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.security.pl.dao;

/**
 * 
 */


import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.dao.GenericDao;
import com.nv.netmd.rs.dto.ErrorCodeListResponseDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;
import com.nv.netmd.rs.dto.User;
import com.nv.netmd.rs.dto.UserDetails;
import com.nv.netmd.rs.dto.UserPermission;



public interface AuthenticationDao extends GenericDao{
	public LoginResponseDTO login(LoginDTO loginDto) throws PersistenceException;
	public UserDetails getUser(String loginId) throws PersistenceException;
	public List<UserPermission>getUserPermissions(Integer roleId);
	public MessageListResponseDTO getMessage(User user) throws PersistenceException;
	public ErrorCodeListResponseDTO getErrorCodes();

}
