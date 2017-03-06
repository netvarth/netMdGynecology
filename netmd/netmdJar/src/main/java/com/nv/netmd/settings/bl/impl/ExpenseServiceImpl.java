 /**
* HeadServiceImpl.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 24, 2013
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
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.ExpenseService;
import com.nv.netmd.settings.bl.validator.ExpenseValidator;
import com.nv.netmd.settings.pl.dao.ExpenseDao;

/**
 * @author Nithesh Mohanan
 *
 */
public class ExpenseServiceImpl implements ExpenseService {
     private ExpenseValidator expenseValidator;
     private ExpenseDao expenseDao;
     private FilterService expenseFilterService;
    
	@Override
	public ResponseDTO create(ExpenseDTO expense) throws ServiceException {
		expenseValidator.validateCreateExpense(expense);
		ResponseDTO response;
		try {
			response = expenseDao.create(expense);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	@Override
	public ExpenseListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		ExpenseListResponseDTO expenseList = new ExpenseListResponseDTO();
		ErrorDTO error=expenseFilterService.validate(filterDTO);
		if (error != null) {
			expenseList.setError(error);
			expenseList.setSuccess(false);
			return expenseList;
		}
		expenseList =expenseFilterService.list(filterDTO);
		return expenseList;
	}

	@Override
	public ExpenseListResponseDTO getExpenses() throws ServiceException {
		ExpenseListResponseDTO response;
		try {
			response = expenseDao.getExpenses();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ExpenseService#view(int)
	 */
	@Override
	public ExpenseViewDTO view(int id) throws ServiceException {		
		ExpenseViewDTO response;
		try {
			response = expenseDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}		
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ExpenseService#update(com.nv.netmd.rs.dto.ExpenseDTO)
	 */
	@Override
	public ResponseDTO update(ExpenseDTO expense) throws ServiceException {
		expenseValidator.validateUpdateExpense(expense);
		ResponseDTO response;
		try {
			response = expenseDao.update(expense);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ExpenseService#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response;
		try {
			response = expenseDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/**
	 * @return the expenseValidator
	 */
	public ExpenseValidator getExpenseValidator() {
		return expenseValidator;
	}

	/**
	 * @param expenseValidator the expenseValidator to set
	 */
	public void setExpenseValidator(ExpenseValidator expenseValidator) {
		this.expenseValidator = expenseValidator;
	}

	/**
	 * @return the expenseDao
	 */
	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	/**
	 * @param expenseDao the expenseDao to set
	 */
	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	/**
	 * @return the expenseFilterService
	 */
	public FilterService getExpenseFilterService() {
		return expenseFilterService;
	}

	/**
	 * @param expenseFilterService the expenseFilterService to set
	 */
	public void setExpenseFilterService(FilterService expenseFilterService) {
		this.expenseFilterService = expenseFilterService;
	}

	

}
