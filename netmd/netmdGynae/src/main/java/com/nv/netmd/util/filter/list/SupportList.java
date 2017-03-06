/**
 * SupportList.java
 * @author Leo
 *
 * Version 1.0 Aug 20, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.util.filter.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.SupportTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.validation.FilterValidator;
import com.nv.netmd.util.filter.queryBuilder.SupportPropertyEnum;

/**
 *
 *
 * @author Leonora Louis
 */
public class SupportList  extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	@Override
	@Transactional(readOnly = false)
	public SupportListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
	
		System.out.println(filterDTO.getCount());
		SupportListResponseDTO supportList = new SupportListResponseDTO();
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.SUPPORT);
		if (queryBuilder == null) {
			return supportList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<SupportTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<SupportTbl> support;
		try {
			support = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		supportList = getSupportList(support);
		supportList.setCount(count);
		supportList.setSuccess(true);
		supportList.setError(null);
		return supportList;
	}

	/**
	 * @param support
	 * @return
	 */
	private SupportListResponseDTO getSupportList(List<SupportTbl> support) {
		SupportListResponseDTO response = new SupportListResponseDTO();
		String supportStatus=null;
		int taxId=0;
		String taxName=null;
		if (support == null) {
			return response;
		}
		List<SupportDTO> supportList = new ArrayList<SupportDTO>();
		for (SupportTbl supportTbl : support) {
			if(supportTbl.getStatus()!=null){
				supportStatus=supportTbl.getStatus().getDisplayName();
			}
			if(supportTbl.getTaxTbl()!=null){
				taxId=supportTbl.getId();
			if(supportTbl.getTaxTbl().getName()!=null)
				taxName=supportTbl.getTaxTbl().getName();
			}
			supportList.add(new SupportDTO(supportTbl.getId(),supportTbl.getName(),supportTbl.getPrice(),supportTbl.getDescription(),taxId,taxName,supportStatus));
			
		}
		response.setSupportList(supportList);
		return response;
	}

	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterDTO) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Property property = null;
			try {
				property = SupportPropertyEnum.valueOf(exp.getName());
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
	

}
