/**
 * DepartmentService.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface DepartmentService {
	/**
	 * @param department
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(DepartmentDTO department) throws ServiceException;
	/**
	 * @param department
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(DepartmentDTO department) throws ServiceException;
	/**
	 * @param id
	 * @return DepartmentDTO
	 * @throws ServiceException 
	 */
	public DepartmentDTO view(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return DepartmentListResponseDTO
	 * @throws ServiceException 
	 */
	public DepartmentListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @return DepartmentListResponseDTO
	 * @throws ServiceException 
	 */
	public DepartmentListResponseDTO getDepartments() throws ServiceException;
}
