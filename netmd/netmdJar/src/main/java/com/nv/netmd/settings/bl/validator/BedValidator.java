/**
 * BedValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.ErrorDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class BedValidator {

	/**
	 * @param bedtype
	 * @return
	 * @throws ServiceException 
	 */
	public ErrorDTO validateCreateBedType(BedTypeDTO bedtype) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if (!isValidType(bedtype.getType())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidType);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(bedtype.getRent()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BedRentShouldNotBeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return null;
	}
	private boolean isValidType(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	public ErrorDTO validateUpdateBedType(BedTypeDTO bedType) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if(bedType.getId()<=0){
			error.setErrCode(ErrorCodeEnum.BedTypeIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidType(bedType.getType())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidType);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(bedType.getRent()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BedRentShouldNotBeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return null;
	}
	public ErrorDTO validateCreateBed(BedDTO bed) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if (!isValidType(bed.getBedNumber())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBedNumeber);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(bed.getBedTypeId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BedTypeIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(bed.getRoomId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		return null;
	}
	public void validateUpdateBed(BedDTO bed) throws ServiceException {
		
		if(bed.getId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BlockIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidType(bed.getBedNumber())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBedNumeber);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(bed.getBedTypeId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BedTypeIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(bed.getRoomId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		
	}
}
