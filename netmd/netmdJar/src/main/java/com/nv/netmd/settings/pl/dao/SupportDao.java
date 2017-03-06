/**
 * ServiceDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface SupportDao {
	public ResponseDTO create(SupportDTO service) throws PersistenceException;

	/**
	 * @param id
	 * @return
	 */
	public SupportDTO view(int id) throws PersistenceException;
	public ResponseDTO update(SupportDTO support) throws PersistenceException;
	public ResponseDTO delete(int id) throws PersistenceException;
	public SupportListResponseDTO getSupports() throws PersistenceException;
	
}
