/**
 * AuthenticationServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */

package com.nv.netmd.security.bl.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.EnumDTO;
import com.nv.netmd.rs.dto.EnumListResponseDTO;
import com.nv.netmd.rs.dto.ErrorCodeListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterEnumValuesDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.UserDetails;
import com.nv.netmd.security.bl.service.AuthenticationService;
import com.nv.netmd.security.bl.validator.AuthenticationValidator;
import com.nv.netmd.security.pl.dao.AuthenticationDao;
import com.nv.platform.base.util.StringEncoder;





public class AuthenticationServiceImpl implements AuthenticationService{
	private AuthenticationDao authenticationDao;
	private AuthenticationValidator authenticationValidator;
	private List<Class<Enum>> enumList;
	private static final Log log = LogFactory.getLog(AuthenticationServiceImpl.class);

	/**
	 * For user login in to netmd
	 * @param loginDto
	 * @return LoginResponseDTO
	 * @throws PersistenceException 
	 */
	@Transactional(readOnly=true)
	public LoginResponseDTO login(LoginDTO loginDto) throws PersistenceException{
		LoginResponseDTO login=new LoginResponseDTO();
		ErrorDTO error=authenticationValidator.validateLoginDTO(loginDto);
		if(error!=null){
			login.setError(error);
			login.setSuccess(false);	
			return login;
		}	
		String userName = loginDto.getUserName().trim();
		loginDto.setUserName(userName);
		//StringEncoder encoder =new StringEncoder();
		String encPassword = (new StringEncoder()).encryptWithKey(loginDto.getPassword().trim());
		loginDto.setPassword(encPassword);
		System.out.println("Password"+encPassword);
		login = authenticationDao.login(loginDto);


		return login;
	}

	public AuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}
	/**
	 * @return the authenticationValidator
	 */
	public AuthenticationValidator getAuthenticationValidator() {
		return authenticationValidator;
	}
	/**
	 * @param authenticationValidator the authenticationValidator to set
	 */
	public void setAuthenticationValidator(
			AuthenticationValidator authenticationValidator) {
		this.authenticationValidator = authenticationValidator;
	}

	/**get user
	 * @param loginId
	 * @return UserDetails
	 * @throws ServiceException 
	 * 
	 */
	@Override
	@Transactional
	public UserDetails getUser(String loginId) throws ServiceException {
		UserDetails user = new UserDetails();
		try {
			user = authenticationDao.getUser(loginId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return user;
	}

	/**
	 * Method to get Enum values
	 * @param <E> 
	 * 
	 * @param elemType
	 * @return <E extends Enum<E>>
	 */
//	public <E extends Enum<E>> List<String> getEnumValues(Class<E> elemType) {
//
//		List<String> enumValList = new ArrayList<String>();
//		for (E e : java.util.EnumSet.allOf(elemType)) {
//			enumValList.add(((EnumDisplay) e).getDisplayName());
//		}
//		return enumValList;
//	}
	
	public <E extends Enum <E>> List<FilterEnumValuesDTO> getEnumValues(Class<E> elemType) {
		List<FilterEnumValuesDTO> enumValList=new ArrayList<FilterEnumValuesDTO>();
		for (E e : java.util.EnumSet.allOf(elemType)) {
			FilterEnumValuesDTO enumVal = new FilterEnumValuesDTO();
			enumVal.setDisplayName(((EnumDisplay) e).getDisplayName());
			enumVal.setParameterName(((EnumDisplay) e).getParameterName());
			enumValList.add(enumVal); 
		}
		return enumValList;
	}

	/**
	 * Retrieves list of Enumerators
	 * 
	 * @return EnumListResponseDTO
	 */
	@Override
	public EnumListResponseDTO getEnumsList() {
		EnumListResponseDTO response=new EnumListResponseDTO();
		List<EnumDTO> enumDTOList= new ArrayList<EnumDTO>();
		for ( Class<Enum> clazz  :enumList){

			EnumDTO enumDTO=new EnumDTO();
			List<FilterEnumValuesDTO> enumValues = getEnumValues(clazz);
			enumDTO.setEnumValues(enumValues);
			enumDTO.setKey(clazz.getSimpleName());
			enumDTOList.add(enumDTO);
		}	 
		response.setEnumListDTO(enumDTOList);
		return response;
	}
	//	/**
	//	 * get message 
	//	 */
	//	@Override
	//	public MessageListResponseDTO  getMessage(User user) {
	//		// TODO Auto-generated method stub
	//		MessageListResponseDTO  message=new MessageListResponseDTO();
	//		message=authenticationDao.getMessage(user);
	//		return message;
	//	}

	/**
	 * @return the enumList
	 */
	public List<Class<Enum>> getEnumList() {
		return enumList;
	}

	/**
	 * @param enumList the enumList to set
	 */
	public void setEnumList(List<Class<Enum>> enumList) {
		this.enumList = enumList;
	}

	/**
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO errorCode = authenticationDao.getErrorCodes();
		return errorCode;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.security.bl.service.AuthenticationService#viewMessage(int)
	 */
	//	@Override
	//	public MessageDTO viewMessage(int id) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
