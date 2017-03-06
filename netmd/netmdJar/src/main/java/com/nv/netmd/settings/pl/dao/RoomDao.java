/**
 * RoomDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
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
public interface RoomDao {
	public ResponseDTO create(RoomDTO room) throws PersistenceException;
	public ResponseDTO update(RoomDTO room) throws PersistenceException;
	public RoomDTO view(int id) throws PersistenceException;
	public ResponseDTO delete(int id) throws PersistenceException;
	public ResponseDTO createRoomType(RoomTypeDTO roomType) throws PersistenceException;
	public ResponseDTO updateRoomType(RoomTypeDTO roomType) throws PersistenceException;
	public RoomTypeDTO viewRoomType(int id) throws PersistenceException;
	public ResponseDTO deleteRoomType(int id) throws PersistenceException;
	public RoomTypeListResponseDTO getRoomTypes() throws PersistenceException;
	public RoomListResponseDTO getRooms() throws PersistenceException;
	
}
