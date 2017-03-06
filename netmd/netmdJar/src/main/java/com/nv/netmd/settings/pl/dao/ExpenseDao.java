 /**
* headDao.java
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
package com.nv.netmd.settings.pl.dao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public interface ExpenseDao {

	/**
	 * @param expense
	 * @return ResponseDTO
	 */
	public ResponseDTO create(ExpenseDTO expense) throws PersistenceException;
	
	/**
	 * @param id
	 * @return ExpenseViewDTO
	 */
	public ExpenseViewDTO view(int id) throws PersistenceException;
	/**
	 * @param expense
	 * @return ResponseDTO
	 */
	public  ResponseDTO update(ExpenseDTO expense) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO delete(int id) throws PersistenceException;
	/**
	 * @return ExpenseListResponseDTO
	 */
	public ExpenseListResponseDTO getExpenses() throws PersistenceException;

}
