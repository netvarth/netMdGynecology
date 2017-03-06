/**
 * ServiceService.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface SupportService {
	/**
	 * @param service
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(SupportDTO service) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return SupportListResponseDTO
	 * @throws ServiceException 
	 */
	public SupportListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @param id
	 * @return SupportDTO
	 * @throws ServiceException 
	 */
	public SupportDTO view(int id) throws ServiceException;
	/**
	 * @param service
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(SupportDTO service) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @return SupportListResponseDTO
	 * @throws ServiceException 
	 */
	public SupportListResponseDTO getSupports() throws ServiceException;
}
