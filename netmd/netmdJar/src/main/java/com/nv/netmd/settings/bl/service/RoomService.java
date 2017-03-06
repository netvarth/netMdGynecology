/**
 * RoomService.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.rs.dto.RoomTypeListResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface RoomService {
	/**
	 * @param room
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(RoomDTO room) throws ServiceException;
	/**
	 * @param room
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(RoomDTO room) throws ServiceException;
	/**
	 * @param id
	 * @return RoomDTO
	 * @throws ServiceException 
	 */
	public RoomDTO view(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return RoomListResponseDTO
	 * @throws ServiceException 
	 */
	public RoomListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @param roomType
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO createRoomType(RoomTypeDTO roomType) throws ServiceException;
	/**
	 * @param roomType
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO updateRoomType(RoomTypeDTO roomType) throws ServiceException;
	/**
	 * @param id
	 * @return RoomTypeDTO
	 * @throws ServiceException 
	 */
	public RoomTypeDTO viewRoomType(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO deleteRoomType(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return RoomTypeListResponseDTO
	 * @throws ServiceException 
	 */
	public RoomTypeListResponseDTO listRoomType(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @return RoomTypeListResponseDTO
	 * @throws ServiceException 
	 */
	public RoomTypeListResponseDTO getRoomTypes() throws ServiceException;
	/** 
	 * @return RoomTypeListResponseDTO
	 * @throws ServiceException 
	 */
	public RoomListResponseDTO getRooms() throws ServiceException;
}
