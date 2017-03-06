/**
 * TaxValidaor.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.TaxDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public class TaxValidator {

	/**
	 * @param taxdto
	 * @return
	 * @throws ServiceException 
	 */
	public ErrorDTO validateCreate(TaxDTO taxdto) throws ServiceException {
		ErrorDTO error=new ErrorDTO();
		if(!IsValidName(taxdto.getName())){
			ServiceException se=new ServiceException(ErrorCodeEnum.InValidName);
			se.setDisplayErrMsg(true);
			throw se;	
		}
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param name
	 * @return
	 */
	private boolean IsValidName(String name) {
		if(name!=null ||!name.equals("")) 
			return true;
		else
			return false;
			
	}

}
