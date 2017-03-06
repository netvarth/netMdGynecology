/**
 * DiscountDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface DiscountDao {
	/**
	 * @param discount
	 * @return ResponseDTO
	 */
	public ResponseDTO create(DiscountDTO discount) throws PersistenceException;
	/**
	 * @param discount
	 * @return ResponseDTO
	 */
	public ResponseDTO update(DiscountDTO discount) throws PersistenceException;
	/**
	 * @param id
	 * @return DiscountDTO
	 */
	public DiscountDTO view(int id) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO delete(int id) throws PersistenceException;

	/**
	 * @return DiscountListResponseDTO
	 */
	public DiscountListResponseDTO getDiscounts() throws PersistenceException;
	/**
	 * @param id
	 * @return
	 */
	public DiscountDTO getDiscountById(int id) throws PersistenceException;
}
