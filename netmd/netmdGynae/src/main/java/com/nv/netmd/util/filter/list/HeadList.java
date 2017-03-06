/**
 * headList.java
 * @author Nithesh Mohanan 
 *
 * Version 1.0 25-Aug-2013
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
import com.nv.netmd.pl.entity.HeadTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.AccountHeadPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class HeadList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	
	@Override
	@Transactional(readOnly = false)	
	public HeadListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		HeadListResponseDTO headList=new HeadListResponseDTO();
	
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.HEAD);
		if (queryBuilder == null) {
			return headList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<HeadTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<HeadTbl> headLis;
				try {
					headLis = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				headList = getHeadList(headLis);
				headList.setCount(count);
				headList.setSuccess(true);

				return headList;
	
	}

	/**
	 * @param headTypelist
	 * @return headListResponseDTO
	 */
	private HeadListResponseDTO getHeadList(List<HeadTbl> headLis) {
		HeadListResponseDTO response = new HeadListResponseDTO();
		//String headName=null;
		int headId=0;
		
		if (headLis == null) {
			return response;
		}
		List<HeadDTO> headDTOList = new ArrayList<HeadDTO>();
		for (HeadTbl headTbl : headLis) {	
			
			if(headTbl.getHeadTbl()!=null){
				headId=headTbl.getHeadTbl().getId();
				
			}
			
			headDTOList.add(new HeadDTO(headTbl.getId(),headTbl.getHeadName(),headTbl.getParentHeadName(),headId, headTbl.getDescription(),headTbl.getHeadType().getDisplayName()));
		}
		response.setHeadList(headDTOList);
		return response;
	
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filter) {
		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code

		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = AccountHeadPropertyEnum.valueOf(exp.getName());
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
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}
}
