/**
 * BedService.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BedService {
	/**
	 * @param bedType
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO createBedType(BedTypeDTO bedType) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return BedTypeListResponseDTO
	 * @throws ServiceException 
	 */
	public BedTypeListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @param id
	 * @return BedTypeDTO
	 * @throws ServiceException 
	 */
	public BedTypeDTO viewBedType(int id) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO deleteBedType(int id) throws ServiceException;
    /**
     * @param bedType
     * @return ResponseDTO
     * @throws ServiceException 
     */
    public ResponseDTO updateBedType(BedTypeDTO bedType) throws ServiceException;
    /**
     * @param bed
     * @return ResponseDTO
     * @throws ServiceException 
     */
    public ResponseDTO createBed(BedDTO bed) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return BedListResponseDTO
	 */
	public BedListResponseDTO listBed(FilterDTO filterDTO)throws ServiceException;
	/**
	 * @param id
	 * @return BedDTO
	 */
	public BedDTO viewBed(int id)throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO deleteBed(int id)throws ServiceException;
    /**
     * @param bed
     * @return ResponseDTO
     * @throws ServiceException 
     */
    public ResponseDTO updateBed(BedDTO bed) throws ServiceException;
    /**
     * @return BedTypeListResponseDTO
     */
    public BedTypeListResponseDTO getBedTypes()throws ServiceException;
    /**
     * @return BedListResponseDTO
     */
    public BedListResponseDTO getBeds()throws ServiceException;
}
