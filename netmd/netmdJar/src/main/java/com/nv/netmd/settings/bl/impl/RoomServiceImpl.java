/**
 * RoomServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.rs.dto.RoomTypeListResponseDTO;
import com.nv.netmd.settings.bl.service.RoomService;
import com.nv.netmd.settings.bl.validator.RoomValidator;
import com.nv.netmd.settings.pl.dao.RoomDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomServiceImpl implements RoomService {
	private RoomValidator roomValidator;
	private FilterService roomTypeFilterService;
	private FilterService roomFilterService;
	private RoomDao roomDao;

	/**
	 * create room
	 *@param room
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO create(RoomDTO room) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=roomValidator.validateCreateRoom(room);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=roomDao.create(room);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * update room 
	 * @param room
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(RoomDTO room) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=roomValidator.validateUpdateRoom(room);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=roomDao.update(room);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * view room
	 * @param id
	 * @return RoomDTO
	 * @throws ServiceException 
	 */
	@Override
	public RoomDTO view(int id) throws ServiceException {
		
		try {
			return roomDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/** 
	 * delete a room
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 * 
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
				
		try {
			return roomDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/** 
	 * list of room
	 * @param filterDTO
	 * @return RoomListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public RoomListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		//System.out.println(filterDTO.getCount());
		RoomListResponseDTO roomList = new RoomListResponseDTO();
		System.out.println("rommList");
		ErrorDTO error=roomFilterService.validate(filterDTO);
		System.out.println("error");
		if (error != null) {
			roomList.setError(error);
			roomList.setSuccess(false);
			return roomList;
		}
		System.out.println("before");
		roomList =roomFilterService.list(filterDTO);
		System.out.println("after");
		return roomList;
	}

	/** 
	 * create room type 
	 * @param roomType
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO createRoomType(RoomTypeDTO roomType) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=roomValidator.validateCreateRoomType(roomType);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=roomDao.createRoomType(roomType);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * update room type
	 * @param roomType
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateRoomType(RoomTypeDTO roomType) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=roomValidator.validateUpdateRoomType(roomType);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=roomDao.updateRoomType(roomType);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * view room type
	 * @param id
	 * @return RoomTypeDTO
	 * @throws ServiceException 
	 */
	@Override
	public RoomTypeDTO viewRoomType(int id) throws ServiceException {
		RoomTypeDTO response=new RoomTypeDTO();
		try {
			response=roomDao.viewRoomType(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/** 
	 * delete room type
	 *@param id
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO deleteRoomType(int id) throws ServiceException {

		ResponseDTO response=new ResponseDTO();
		try {
			response=roomDao.deleteRoomType(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * list of room type
	 * @param filterDTO
	 * @return RoomTypeListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public RoomTypeListResponseDTO listRoomType(FilterDTO filterDTO) throws ServiceException {
		//System.out.println(filterDTO.getCount());
		RoomTypeListResponseDTO roomTypeList = new RoomTypeListResponseDTO();
		ErrorDTO error=roomTypeFilterService.validate(filterDTO);
		if (error != null) {
			roomTypeList.setError(error);
			roomTypeList.setSuccess(false);
			return roomTypeList;
		}
		roomTypeList =roomTypeFilterService.list(filterDTO);
		return roomTypeList;
	}

	/**
	 * @return the roomValidator
	 */
	public RoomValidator getRoomValidator() {
		return roomValidator;
	}

	/**
	 * @param roomValidator the roomValidator to set
	 */
	public void setRoomValidator(RoomValidator roomValidator) {
		this.roomValidator = roomValidator;
	}

	/**
	 * @return the roomTypeFilterService
	 */
	public FilterService getRoomTypeFilterService() {
		return roomTypeFilterService;
	}

	/**
	 * @param roomTypeFilterService the roomTypeFilterService to set
	 */
	public void setRoomTypeFilterService(FilterService roomTypeFilterService) {
		this.roomTypeFilterService = roomTypeFilterService;
	}

	/**
	 * @return the roomDao
	 */
	public RoomDao getRoomDao() {
		return roomDao;
	}

	/**
	 * @param roomDao the roomDao to set
	 */
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	/**
	 * @return the roomFilterService
	 */
	public FilterService getRoomFilterService() {
		return roomFilterService;
	}

	/**
	 * @param roomFilterService the roomFilterService to set
	 */
	public void setRoomFilterService(FilterService roomFilterService) {
		this.roomFilterService = roomFilterService;
	}

	/** 
	 * get room types
	 * 
	 * @return RoomTypeListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public RoomTypeListResponseDTO getRoomTypes() throws ServiceException {
		
		try {
			return roomDao.getRoomTypes();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/** 
	 *get rooms
	 *@return RoomListResponseDTO
	 * @throws ServiceException 
	 *
	 */
	@Override
	public RoomListResponseDTO getRooms() throws ServiceException {		
		// TODO Auto-generated method stub
		try {
			return roomDao.getRooms();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

}
