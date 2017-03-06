/**
 * BedDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BedDao {

	/**
	 * @param bedtype
	 * @return ResponseDTO
	 */
	public ResponseDTO createBedType(BedTypeDTO bedtype) throws PersistenceException;
	/**
	 * @param id
	 * @return BedTypeDTO
	 */
	public BedTypeDTO viewBedType(int id) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO deleteBedType(int id) throws PersistenceException;
	/**
	 * @param bedtype
	 * @return ResponseDTO
	 */
	public ResponseDTO updateBedType(BedTypeDTO bedtype) throws PersistenceException;
	
	/**
	 * @param bed
	 * @return ResponseDTO
	 */
	public ResponseDTO createBed(BedDTO bed) throws PersistenceException;	
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public BedDTO viewBed(int id) throws PersistenceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO deleteBed(int id) throws PersistenceException;
	/**
	 * @param bed
	 * @return ResponseDTO
	 */
	public ResponseDTO updateBed(BedDTO bed) throws PersistenceException;
	
	/**
	 * @return BedTypeListResponseDTO
	 */
	public BedTypeListResponseDTO getBedTypes() throws PersistenceException;
	
	 /**
	 * @return BedListResponseDTO
	 */
	public BedListResponseDTO getBeds() throws PersistenceException;
	

}
