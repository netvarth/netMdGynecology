/**
 * CaseList.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 25, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.util.filter.list;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CaseAnswerSetTbl;
import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.CasePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class CaseList extends FilterValidator implements FilterService {
	private PatientDao patientDao;
	
	@Override
	public CaseListResponseDTO list(FilterDTO filterDto) throws ServiceException{
		CaseListResponseDTO caselist;
		try {
			caselist = patientDao.caselist(filterDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return caselist;
	}
	
	
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			
			try {
				property = CasePropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
	}


	/**
	 * @return the patientDao
	 */
	public PatientDao getPatientDao() {
		return patientDao;
	}

	/**
	 * @param patientDao the patientDao to set
	 */
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}


	
}
