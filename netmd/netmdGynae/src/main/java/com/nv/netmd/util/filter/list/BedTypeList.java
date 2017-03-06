/**
 * BedTypeList.java
 * @author Leo
 *
 * Version 1.0 Jul 31, 2013
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
import com.nv.netmd.pl.entity.BedTypeTbl;


import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.validation.FilterValidator;
import com.nv.netmd.util.filter.queryBuilder.BedTypePropertyEnum;

/**
 *
 *
 * @author Leonora Louis
 */
public class BedTypeList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	
	public BedTypeListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		BedTypeListResponseDTO bedTypeList=new BedTypeListResponseDTO();
		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error =validate(filterDTO);
		if (error != null) {
			bedTypeList.setError(error);
			bedTypeList.setSuccess(false);
			return bedTypeList;
		}
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.BEDTYPE);
		if (queryBuilder == null) {
			return bedTypeList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<BedTypeTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<BedTypeTbl> bedTypelst;
				try {
					bedTypelst = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				bedTypeList = getBedTypeList(bedTypelst);
				bedTypeList.setCount(count);
				bedTypeList.setSuccess(true);

				return bedTypeList;
	
	}

	/**
	 * @param bedTypelst
	 * @return
	 */
	private BedTypeListResponseDTO getBedTypeList(List<BedTypeTbl> bedTypelst) {
		BedTypeListResponseDTO response = new BedTypeListResponseDTO();
		String bedTypeStatus=null;
		if (bedTypelst == null) {
			return response;
		}
		List<BedTypeDTO> bedtypeDTOList = new ArrayList<BedTypeDTO>();
		for (BedTypeTbl bedTypeTbl : bedTypelst) {			
			if (bedTypeTbl.getStatus() != null) {
				bedTypeStatus=bedTypeTbl.getStatus().getDisplayName();
			}
			
			bedtypeDTOList.add(new BedTypeDTO(bedTypeTbl.getId(),bedTypeTbl.getType(),bedTypeTbl.getRent(),bedTypeTbl.getCount(),bedTypeStatus));
			
		}
		response.setBedTypeList(bedtypeDTOList);
		return response;
	
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = BedTypePropertyEnum.valueOf(exp.getName());
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
