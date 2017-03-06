 /**
* HeadService.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 11, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.bl.service;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public interface HeadService {
	
	/**
	 * @param head
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(HeadDTO head) throws ServiceException;	
	/**
	 * @param head
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(HeadDTO head) throws ServiceException;
	/**
	 * @param id
	 * @return HeadDTO
	 * @throws ServiceException 
	 */
	public HeadDTO view(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */ 
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return HeadListResponseDTO
	 * @throws ServiceException 
	 */
	public HeadListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	
	/**
	 * @return HeadListResponseDTO
	 * @throws ServiceException 
	 */
	public HeadListResponseDTO getHeads() throws ServiceException;

}
