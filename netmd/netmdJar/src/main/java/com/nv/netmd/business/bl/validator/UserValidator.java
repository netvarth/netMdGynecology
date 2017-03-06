/**
 * UserValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 1, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.validator;

import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.HeaderDTO;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
public class UserValidator {
	/**
	 * validates user details
	 * 
	 * @param user
	 * @return ErrorDTO
	 */

	public ErrorDTO ValidateCreateUser(UserDTO user) {
		ErrorDTO error = new ErrorDTO();

		// ValidateHeaderDetails(user.getHeader());
		if (!isValidName(user.getName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		if (!isValidName(user.getPassword())) {
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getUserName())) {
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getUserType())) {
			error.setErrCode(ErrorCodeEnum.UserTypeNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	/**
	 * validates user details for update
	 * 
	 * @param user
	 * @return ErrorDTO
	 */

	public ErrorDTO ValidateUpdateUser(UserDTO user) {
		ErrorDTO error = new ErrorDTO();

		// ValidateHeaderDetails(user.getHeader());

		if (user.getGlobalId() <= 0) {
			error.setErrCode(ErrorCodeEnum.InvalidGlobalId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		if (!isValidName(user.getPassword())) {
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getUserName())) {
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getUserType())) {
			error.setErrCode(ErrorCodeEnum.UserTypeNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO ValidateUserDetails(UserDTO user) {
		ErrorDTO error = new ErrorDTO();
		// ValidateHeaderDetails(user.getHeader());
		if (user.getGlobalId() <= 0) {
			error.setErrCode(ErrorCodeEnum.InvalidGlobalId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	private ErrorDTO ValidateHeaderDetails(HeaderDTO header) {
		ErrorDTO error = new ErrorDTO();
		if (header.getHeadOfficeId() <= 0) {
			error.setErrCode(ErrorCodeEnum.NetMdIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {
			error.setErrCode(ErrorCodeEnum.PassPhraseNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
}
