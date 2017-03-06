/**
 * DepartmentServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
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
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.DepartmentService;
import com.nv.netmd.settings.bl.validator.DepartmentValidator;
import com.nv.netmd.settings.pl.dao.DepartmentDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao;
	private DepartmentValidator departmentValidator;
	private FilterService departmentFilterService;

	/**
	 * Create department
	 * @param department
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO create(DepartmentDTO department) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=departmentValidator.validateCreateDepartment(department);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=departmentDao.create(department);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the departmentDao
	 */
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	/**
	 * @param departmentDao the departmentDao to set
	 */
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	/**
	 * @return the departmentValidator
	 */
	public DepartmentValidator getDepartmentValidator() {
		return departmentValidator;
	}

	/**
	 * @param departmentValidator the departmentValidator to set
	 */
	public void setDepartmentValidator(DepartmentValidator departmentValidator) {
		this.departmentValidator = departmentValidator;
	}

	/**
	 * Update department
	 * @param department
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(DepartmentDTO department) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=departmentValidator.validateUpdateDepartment(department);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=departmentDao.update(department);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/** 
	 * view department
	 * @param id
	 * @throws ServiceException 
	 */
	@Override
	public DepartmentDTO view(int id) throws ServiceException {
		DepartmentDTO response=new DepartmentDTO();
		try {
			response=departmentDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * delete department
	 * @param id
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		try {
			response=departmentDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * get list of department
	 * @param filterDTO
	 * @return DepartmentListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public DepartmentListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		//System.out.println(filterDTO.getCount());
		DepartmentListResponseDTO departmentList = new DepartmentListResponseDTO();
		ErrorDTO error=departmentFilterService.validate(filterDTO);
		if (error != null) {
			departmentList.setError(error);
			departmentList.setSuccess(false);
			return departmentList;
		}
		departmentList =departmentFilterService.list(filterDTO);
		return departmentList;
	}

	/**
	 * @return the departmentFilterService
	 */
	public FilterService getDepartmentFilterService() {
		return departmentFilterService;
	}

	/**
	 * @param departmentFilterService the departmentFilterService to set
	 */
	public void setDepartmentFilterService(FilterService departmentFilterService) {
		this.departmentFilterService = departmentFilterService;
	}

	/**
	 * get departments
	 * @return DepartmentListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public DepartmentListResponseDTO getDepartments() throws ServiceException {
		DepartmentListResponseDTO response;
		try {
			response = departmentDao.getDepartments();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

}
