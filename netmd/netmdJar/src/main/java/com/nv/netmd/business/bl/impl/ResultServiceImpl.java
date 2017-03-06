/**
 * ResultServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.service.ResultService;
import com.nv.netmd.business.bl.validator.ResultValidator;
import com.nv.netmd.business.pl.dao.ResultDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;

/**
 * 
 */
public class ResultServiceImpl implements ResultService{
	private ResultDao resultDao;
	private ResultValidator resultValidator;
	private FilterService resultFilterService;
	/**
	 * view single result
	 * @param id
	 * @return ViewResultDTO
	 */
	@Override
	public ViewResultDTO viewResult(int id) throws ServiceException {
		// TODO Auto-generated method stub
		ViewResultDTO response=new ViewResultDTO();
		try {
			response=resultDao.viewResult(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * @return the resultDao
	 */
	public ResultDao getResultDao() {
		return resultDao;
	}
	/**
	 * @param resultDao the resultDao to set
	 */
	public void setResultDao(ResultDao resultDao) {
		this.resultDao = resultDao;
	}
	/**
	 * @return the resultValidator
	 */
	public ResultValidator getResultValidator() {
		return resultValidator;
	}
	/**
	 * @param resultValidator the resultValidator to set
	 */
	public void setResultValidator(ResultValidator resultValidator) {
		this.resultValidator = resultValidator;
	}
	/**
	 *list of patient result result
	 * @param id
	 * @return ResultListResponseDTO
	 * @throws ServiceException 
	 */
	//	@Override
	//	public ResultListResponseDTO listResult(int patientId) {
	//		// TODO Auto-generated method stub
	//		ResultListResponseDTO response=new ResultListResponseDTO();
	//
	//		response =resultDao.listResult(patientId);
	//		return response;
	//
	//	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.ResultService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ResultListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		ResultListResponseDTO resultList = new ResultListResponseDTO();
		ErrorDTO error=resultFilterService.validate(filterDTO);
		if (error != null) {
			resultList.setError(error);
			resultList.setSuccess(false);
			return resultList;
		}
		resultList =resultFilterService.list(filterDTO);
		return resultList;
	}
	/**
	 * @return the resultFilterService
	 */
	public FilterService getResultFilterService() {
		return resultFilterService;
	}
	/**
	 * @param resultFilterService the resultFilterService to set
	 */
	public void setResultFilterService(FilterService resultFilterService) {
		this.resultFilterService = resultFilterService;
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.ResultService#syncResult(com.nv.netmd.rs.dto.ViewResultDTO)
	 */
	@Override
	public RetrieveResultsResponseDTO resultFromYNW(RetrieveResultsResponseDTO result) throws ServiceException{
		// TODO Auto-generated method stub
		RetrieveResultsResponseDTO response=new RetrieveResultsResponseDTO();
		ErrorDTO error = resultValidator.validateResultFromYNW(result);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = resultDao.resultFromYNW(result);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

}
