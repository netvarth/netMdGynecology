 /**
* AdvanceDao.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

public interface AdvanceDao {
	
	/**
	 * @param AdvanceDTO
	 * @return ResponseDTO
	 */
	public ResponseDTO create(AdvanceDTO advance)throws PersistenceException;

	/**
	 * @param AdvanceDTO
	 * @return ResponseDTO
	 */
	public ResponseDTO update(AdvanceDTO advance)throws PersistenceException;
	
	/**
	 * @param id
	 * @return AdvanceDTO
	 */
	public AdvanceDTO view(int id)throws PersistenceException;	
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO delete(int id)throws PersistenceException;
	

	
}
