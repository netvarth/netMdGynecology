 /**
* HeadServiceImpl.java
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
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.HeadService;
import com.nv.netmd.settings.bl.validator.HeadValidator;
import com.nv.netmd.settings.pl.dao.HeadDao;

/**
 * @author Nithesh Mohanan
 *
 */
public class HeadServiceImpl implements HeadService {
     private HeadValidator headValidator;
     private HeadDao headDao;
     private FilterService headFilterService;
    
	@Override
	public ResponseDTO create(HeadDTO head) throws ServiceException {
		headValidator.validateCreateHead(head);
		ResponseDTO response;
		try {
			response = headDao.create(head);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	@Override
	public HeadListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		HeadListResponseDTO headList = new HeadListResponseDTO();
		ErrorDTO error=headFilterService.validate(filterDTO);
		if (error != null) {
			headList.setError(error);
			headList.setSuccess(false);
			return headList;
		}
		headList =headFilterService.list(filterDTO);
		return headList;
	}

	@Override
	public ResponseDTO update(HeadDTO item) throws ServiceException {
		headValidator.validateUpdateHead(item);
		ResponseDTO response;
		try {
			response = headDao.update(item);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	@Override
	public HeadDTO view(int id) throws ServiceException {
		HeadDTO response;
		try {
			response = headDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}	
		return response;
	}

	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response;
		try {
			response = headDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	@Override
	public HeadListResponseDTO getHeads() throws ServiceException {
		HeadListResponseDTO response;
		try {
			response = headDao.getHeads();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	
	/**
	 * @return the headValidator
	 */
	public HeadValidator getHeadValidator() {
		return headValidator;
	}
	/**
	 * @param headValidator the headValidator to set
	 */
	public void setHeadValidator(HeadValidator headValidator) {
		this.headValidator = headValidator;
	}
	/**
	 * @return the headDao
	 */
	public HeadDao getHeadDao() {
		return headDao;
	}
	/**
	 * @param headDao the headDao to set
	 */
	public void setHeadDao(HeadDao headDao) {
		this.headDao = headDao;
	}
	/**
	 * @return the headFilterService
	 */
	public FilterService getHeadFilterService() {
		return headFilterService;
	}
	/**
	 * @param headFilterService the headFilterService to set
	 */
	public void setHeadFilterService(FilterService headFilterService) {
		this.headFilterService = headFilterService;
	}


	

	

	
	
	
	

}
