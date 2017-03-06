/**
 * BlockDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 26, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BlockDao {
	public ResponseDTO create(BlockDTO block) throws PersistenceException;
	public ResponseDTO update(BlockDTO block) throws PersistenceException;
	public BlockDTO view(int id) throws PersistenceException;
	public ResponseDTO delete(int id) throws PersistenceException;
	public BlockListResponseDTO getBlocks() throws PersistenceException;
}
