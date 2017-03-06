/**
 * ItemDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface ItemDao {
	/**
	 * @param item
	 * @return ResponseDTO
	 */
	public ResponseDTO create(ItemDTO item) throws PersistenceException;
	/**
	 * @param item
	 * @return ResponseDTO
	 */
	public ResponseDTO update(ItemDTO item) throws PersistenceException;
	/**
	 * @param id
	 * @return ItemDTO
	 */
	public ItemDTO view(int id) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */ 
	public ResponseDTO delete(int id) throws PersistenceException;
	
	/**
	 * @return ItemListResponseDTO
	 */
	public ItemListResponseDTO getItems() throws PersistenceException;
	
}
