/**
 * ItemService.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface ItemService {
	/**
	 * @param item
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(ItemDTO item) throws ServiceException;
	/**
	 * @param item
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(ItemDTO item) throws ServiceException;
	/**
	 * @param id
	 * @return ItemDTO
	 * @throws ServiceException 
	 */
	public ItemDTO view(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */ 
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return ItemListResponseDTO
	 * @throws ServiceException 
	 */
	public ItemListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @return ItemListResponseDTO
	 * @throws ServiceException 
	 */
	public ItemListResponseDTO getItems() throws ServiceException;
}
