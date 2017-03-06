/**
 * BedServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.BedService;
import com.nv.netmd.settings.bl.validator.BedValidator;
import com.nv.netmd.settings.pl.dao.BedDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class BedServiceImpl implements BedService {
	private BedDao bedDao;
	private BedValidator bedValidator;
	private FilterService bedTypeFilterService;
	private FilterService bedFilterService;
	/**
	 * create bed type
	 * @param bedtype
	 * @return BedTypeDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO createBedType(BedTypeDTO bedtype) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=bedValidator.validateCreateBedType(bedtype);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=bedDao.createBedType(bedtype);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * list of bed type
	 * @param filterDTO
	 * @return BedTypeListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BedTypeListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		BedTypeListResponseDTO bedTypeList = new BedTypeListResponseDTO();
		ErrorDTO error=bedTypeFilterService.validate(filterDTO);
		if (error != null) {
			bedTypeList.setError(error);
			bedTypeList.setSuccess(false);
			return bedTypeList;
		}
		bedTypeList =bedTypeFilterService.list(filterDTO);
		return bedTypeList;
	}
	/**
	 * view bed type
	 * @param id
	 * @return BedTypeDTO
	 * @throws ServiceException 
	 */
	@Override
	public BedTypeDTO viewBedType (int id) throws ServiceException {
		BedTypeDTO response=new BedTypeDTO();
		try {
			response=bedDao.viewBedType(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}
	/**
	 * @return bedDao
	 */
	public BedDao getBedDao() {
		return bedDao;
	}
	/**
	 * @param bedDao
	 */
	public void setBedDao(BedDao bedDao) {
		this.bedDao = bedDao;
	}
	/**
	 * @return bedValidator
	 */
	public BedValidator getBedValidator() {
		return bedValidator;
	}
	/**
	 * @param bedValidator
	 */
	public void setBedValidator(BedValidator bedValidator) {
		this.bedValidator = bedValidator;
	}
	/**
	 * @return bedTypeFilterService
	 */
	public FilterService getBedTypeFilterService() {
		return bedTypeFilterService;
	}
	/**
	 * @param bedTypeFilterService
	 */
	public void setBedTypeFilterService(FilterService bedTypeFilterService) {
		this.bedTypeFilterService = bedTypeFilterService;
	}
	/**
	 * delete bed type
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO deleteBedType(int id) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		try {
			response=bedDao.deleteBedType(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}
	/** 
	 *Update Bed type
	 *@param bedType
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateBedType(BedTypeDTO bedType) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=bedValidator.validateUpdateBedType(bedType);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=bedDao.updateBedType(bedType);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/** 
	 * create bed
	 * @param bed
	 * @return ResponseDTO
	 * @throws ServiceException 
	 * 
	 */
	@Override
	public ResponseDTO createBed(BedDTO bed) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=bedValidator.validateCreateBed(bed);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=bedDao.createBed(bed);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/** 
	 * get bed list
	 * @param filterDTO
	 * @return BedListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BedListResponseDTO listBed(FilterDTO filterDTO) throws ServiceException {
		//	System.out.println(filterDTO.getCount());
		BedListResponseDTO bedList = new BedListResponseDTO();
		ErrorDTO error=bedFilterService.validate(filterDTO);
		if (error != null) {
			bedList.setError(error);
			bedList.setSuccess(false);
			return bedList;
		}
		bedList =bedFilterService.list(filterDTO);
		return bedList;

	}
	/** 
	 * view bed
	 * @param id
	 * @return BedDTO
	 * @throws ServiceException 
	 */
	@Override
	public BedDTO viewBed(int id) throws ServiceException {
		BedDTO respnse;
		try {
			respnse = bedDao.viewBed(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return respnse;
	}
	/**
	 * delete bed
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 * 
	 */
	@Override
	public ResponseDTO deleteBed(int id) throws ServiceException {
		ResponseDTO response;
		try {
			response = bedDao.deleteBed(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * update bed
	 * @param bed
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateBed(BedDTO bed) throws ServiceException {
		bedValidator.validateUpdateBed(bed);		
		ResponseDTO response;
		try {
			response = bedDao.updateBed(bed);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * @return the bedFilterService
	 */
	public FilterService getBedFilterService() {
		return bedFilterService;
	}
	/**
	 * @param bedFilterService the bedFilterService to set
	 */
	public void setBedFilterService(FilterService bedFilterService) {
		this.bedFilterService = bedFilterService;
	}
	/**
	 * get bed types
	 * @return BedTypeListResponseDTO
	 * @throws ServiceException 
	 * 
	 */
	@Override
	public BedTypeListResponseDTO getBedTypes() throws ServiceException {
		BedTypeListResponseDTO response;
		try {
			response = bedDao.getBedTypes();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * get beds
	 * @return BedListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BedListResponseDTO getBeds() throws ServiceException {
		BedListResponseDTO response;
		try {
			response = bedDao.getBeds();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

}
