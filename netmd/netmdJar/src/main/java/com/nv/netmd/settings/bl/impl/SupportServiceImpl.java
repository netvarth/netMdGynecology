/**
 * ServiceServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
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
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;
import com.nv.netmd.settings.bl.service.SupportService;
import com.nv.netmd.settings.bl.validator.SupportValidator;
import com.nv.netmd.settings.pl.dao.SupportDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class SupportServiceImpl implements SupportService {
	private SupportValidator supportValidator;
	private SupportDao supportDao;
	private FilterService supportFilterService;
	
	/** 
	 * create support
	 * @param service
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO create(SupportDTO service) throws ServiceException {
		supportValidator.validateCreate(service);
		ResponseDTO response;
		try {
			response = supportDao.create(service);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	/** 
	 * support list
	 * @param filterDTO
	 * @return SupportListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public SupportListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		// TODO Auto-generated method stub
		SupportListResponseDTO response=new SupportListResponseDTO();
		ErrorDTO error=supportFilterService.validate(filterDTO);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response=supportFilterService.list(filterDTO);
		return response;
	}

	public SupportValidator getSupportValidator() {
		return supportValidator;
	}

	public void setSupportValidator(SupportValidator supportValidator) {
		this.supportValidator = supportValidator;
	}

	public SupportDao getSupportDao() {
		return supportDao;
	}

	public void setSupportDao(SupportDao supportDao) {
		this.supportDao = supportDao;
	}

	public FilterService getSupportFilterService() {
		return supportFilterService;
	}

	public void setSupportFilterService(FilterService supportFilterService) {
		this.supportFilterService = supportFilterService;
	}

	/** 
	 *view support
	 *@param id
	 *@return SupportDTO
	 * @throws ServiceException 
	 */
	@Override
	public SupportDTO view(int id) throws ServiceException {
		SupportDTO response;
		try {
			response = supportDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 *update support
	 *@param support
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(SupportDTO support) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=supportValidator.validateUpdate(support);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=supportDao.update(support);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
		// TODO Auto-generated method stub

	}

	/** 
	 * delete support
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response;
		try {
			response = supportDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * get supports
	 * @return SupportListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public SupportListResponseDTO getSupports() throws ServiceException {
		SupportListResponseDTO response;
		try {
			response = supportDao.getSupports();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	

}
