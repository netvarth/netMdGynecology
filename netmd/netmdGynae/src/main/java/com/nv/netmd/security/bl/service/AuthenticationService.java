/**
 * AuthenticationService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.security.bl.service;


import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.EnumListResponseDTO;
import com.nv.netmd.rs.dto.ErrorCodeListResponseDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.UserDetails;




/**
 * 
 */
public interface AuthenticationService {
	public LoginResponseDTO login(LoginDTO loginDto) throws PersistenceException;
	public UserDetails getUser(String loginId) throws ServiceException;
//	public MessageListResponseDTO getMessage(User user);
//	public MessageDTO viewMessage(int id);
	public EnumListResponseDTO getEnumsList();
	public ErrorCodeListResponseDTO getErrorCodes();
}