/**
 * SyncValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.sync.bl.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.PassPhraseDTO;



/**
 * 
 */
public class SyncValidator {
	public void validatePassPhrase(PassPhraseDTO passPhrase) throws ServiceException {
		if(passPhrase==null){
			ServiceException se = new ServiceException(ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(passPhrase.getPassPhrase()==null || passPhrase.getPassPhrase().equals("")){
			ServiceException se = new ServiceException(ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}		
	}
	public  ErrorDTO validateLastSyncTime(String lastSyncTime) {
		ErrorDTO error = new ErrorDTO();
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		try {
			Date date=df.parse(lastSyncTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		} 
		return null;
	}
}
