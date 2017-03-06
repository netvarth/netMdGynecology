/**
* ResultValidator.java
* 
* @Author Sreeram
*
* Version 1.0 Jan 5, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.validator;

import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;

/**
 * 
 */
public class ResultValidator {
	public ErrorDTO validateResultFromYNW(RetrieveResultsResponseDTO result) {
		ErrorDTO error = new ErrorDTO();
		if (result.getResultGlobalId() <= 0) {
			error.setErrCode(ErrorCodeEnum.GlobalIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(result.getResult())) {
			error.setErrCode(ErrorCodeEnum.ResultNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (result.getPatientId() <= 0) {
			error.setErrCode(ErrorCodeEnum.PatientGlobalIdNull.getErrCode());
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
