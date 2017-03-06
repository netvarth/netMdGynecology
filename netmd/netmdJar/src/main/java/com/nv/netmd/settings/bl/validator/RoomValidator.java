/**
 * RoomValidator.java
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
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomValidator {
	public ErrorDTO validateCreateRoom(RoomDTO room) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if (!isValidName(room.getRoomNumber())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}if(room.getBlockId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BlockIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(room.getDepartmentId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DepartmentIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(room.getRoomTypeId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomTypeIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		return null;
	}
	
	public ErrorDTO validateUpdateRoom(RoomDTO room) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if(room.getId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(room.getRoomNumber())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}if(room.getBlockId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BlockIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(room.getDepartmentId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DepartmentIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(room.getRoomTypeId()<=0){
			//error
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomTypeIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		return null;
	}
	public ErrorDTO validateCreateRoomType(RoomTypeDTO roomType) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if (!isValidName(roomType.getType())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(roomType.getRent()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomRentShouldNotBeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return null;
	}
	public ErrorDTO validateUpdateRoomType(RoomTypeDTO roomType) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
		if(roomType.getId()<=0){
			error.setErrCode(ErrorCodeEnum.RoomTypeIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(roomType.getType())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(roomType.getRent()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.RoomRentShouldNotBeNull);
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
