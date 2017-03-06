 /**
* headDao.java
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
package com.nv.netmd.settings.pl.dao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public interface HeadDao {

	/**
	 * @param head
	 * @return ResponseDTO
	 */
	public ResponseDTO create(HeadDTO head) throws PersistenceException;
	/**
	 * @param head
	 * @return ResponseDTO
	 */
	public ResponseDTO update(HeadDTO head) throws PersistenceException;
	/**
	 * @param id
	 * @return HeadDTO
	 */
	public HeadDTO view(int id) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO delete(int id) throws PersistenceException;
	/**
	 * @param 
	 * @return HeadListResponseDTO
	 */
	public HeadListResponseDTO getHeads() throws PersistenceException;
	
}
