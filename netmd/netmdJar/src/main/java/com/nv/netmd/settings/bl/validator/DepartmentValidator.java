/**
 * DepartmentValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.ErrorDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class DepartmentValidator {
	public ErrorDTO validateCreateDepartment(DepartmentDTO department) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if (!isValidName(department.getDepartmentName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		return null;
	}
	public ErrorDTO validateUpdateDepartment(DepartmentDTO department) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if(department.getId()<=0){
			error.setErrCode(ErrorCodeEnum.DepartmentIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(department.getDepartmentName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

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
