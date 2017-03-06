 /**
* HeadService.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 24, 2013
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
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public interface ExpenseService {
	
	/**
	 * @param expense
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(ExpenseDTO expense) throws ServiceException;	
	/**
	 * @return ExpenseListResponseDTO
	 * @throws ServiceException 
	 */
	public ExpenseListResponseDTO getExpenses() throws ServiceException;
	/**
	 * @param filterDTO
	 * @return ExpenseListResponseDTO
	 * @throws ServiceException 
	 */
	public ExpenseListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @param id
	 * @return ExpenseViewDTO
	 * @throws ServiceException 
	 */
	public ExpenseViewDTO view(int id) throws ServiceException;
	/**
	 * @param expense
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(ExpenseDTO expense) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */ 
	public ResponseDTO delete(int id) throws ServiceException;

}
