/**
 * BlockService.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 26, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BlockService {
	/**
	 * @param block
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(BlockDTO block) throws ServiceException;
	/**
	 * @param block
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(BlockDTO block) throws ServiceException;
	/**
	 * @param id
	 * @return BlockDTO
	 * @throws ServiceException 
	 */
	public BlockDTO view(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return BlockListResponseDTO
	 * @throws ServiceException 
	 */ 
	public BlockListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @return BlockListResponseDTO
	 * @throws ServiceException 
	 */
	public BlockListResponseDTO getBlocks() throws ServiceException;
}
