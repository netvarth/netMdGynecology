 /**
* AdvanceManager.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.bl.impl;

import com.nv.netmd.billing.bl.service.AdvanceService;
import com.nv.netmd.billing.bl.validator.AdvanceValidator;
import com.nv.netmd.billing.pl.dao.AdvanceDao;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.AdvanceListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

public class AdvanceManager implements AdvanceService {
	private AdvanceValidator validator;
	private AdvanceDao advanceDao;
	private FilterService advanceFilterService;
	/** 
	 *create advance
	 *@param advance
	 *@return ResponseDTO
	 * @throws ServiceException 
	 *
	 */
	@Override
	public ResponseDTO create(AdvanceDTO advance) throws ServiceException {
		validator.validateCreateAdvance(advance);
		ResponseDTO response;
		try {
			response = advanceDao.create(advance);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		return response;
	}	
	
	/** 
	 *update advance
	 *@param advance
	 *@return ResponseDTO
	 * @throws ServiceException 
	 *
	 */
	@Override
	public ResponseDTO update(AdvanceDTO advance) throws ServiceException {
		validator.validateUpdateAdvance(advance);
		ResponseDTO response;
		try {
			response = advanceDao.update(advance);
		} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		return response;
	}

	@Override
	public AdvanceDTO view(int id) throws ServiceException {
		AdvanceDTO advance;
		try {
			advance = advanceDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			
		}
		return advance;
	}

	@Override
	public ResponseDTO delete(int id)throws ServiceException {
		ResponseDTO response;
		try {
			response = advanceDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		return response;
	}
	
	

	@Override
	public AdvanceListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		AdvanceListResponseDTO advanceList = new AdvanceListResponseDTO();
		advanceFilterService.validate(filterDTO);
		advanceList =advanceFilterService.list(filterDTO);
		return advanceList;
	}

	public AdvanceValidator getValidator() {
		return validator;
	}

	public void setValidator(AdvanceValidator validator) {
		this.validator = validator;
	}

	public AdvanceDao getAdvanceDao() {
		return advanceDao;
	}

	public void setAdvanceDao(AdvanceDao advanceDao) {
		this.advanceDao = advanceDao;
	}

	public FilterService getAdvanceFilterService() {
		return advanceFilterService;
	}

	public void setAdvanceFilterService(FilterService advanceFilterService) {
		this.advanceFilterService = advanceFilterService;
	}	

	
	
	
}
