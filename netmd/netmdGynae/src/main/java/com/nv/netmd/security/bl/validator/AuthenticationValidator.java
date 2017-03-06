/**
 * AuthenticationValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
/**
 * AuthenticationValidator.java
 * 
 * @Author Linu Paul
 *
 * Version 1.0 aug 22, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.security.bl.validator;

import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.LoginDTO;



public class AuthenticationValidator {
	/**
	 * Check validity of username and password 
	 * @param login
	 * @return ErrorDTO
	 */
	public ErrorDTO validateLoginDTO(LoginDTO login){
		ErrorDTO error=new ErrorDTO();
		if(!isValidExpValue(login.getUserName())){
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(!isValidExpValue(login.getPassword())){
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		else
			error=null;

		return error;
	}
	/**
	 * Check validity of expression value
	 * @param value
	 * @return boolean
	 *
	 */
	private boolean isValidExpValue(String value) {
		if(value!=null && !value.equals("")){
			return true;
		}
		return false;
	}
}
