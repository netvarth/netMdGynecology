/**
 * DiscountList.java
 * @author Sreeram T G 
 *
 * Version 1.0 03-Sep-2013
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
import com.nv.netmd.pl.entity.DiscountTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.DiscountPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class DiscountList  extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	@Override
	@Transactional(readOnly = false)	
	public DiscountListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		DiscountListResponseDTO discountList=new DiscountListResponseDTO();

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.DISCOUNT);
		if (queryBuilder == null) {
			return discountList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<DiscountTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<DiscountTbl> disLis;
				try {
					disLis = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				discountList = getDiscountList(disLis);
				discountList.setCount(count);
				discountList.setSuccess(true);

				return discountList;
	
	}

	/**
	 * @param bedTypelst
	 * @return
	 */
	private DiscountListResponseDTO getDiscountList(List<DiscountTbl> disLis) {
		DiscountListResponseDTO response = new DiscountListResponseDTO();
		String discType=null;
		String calcType=null;		
		if (disLis == null) {
			return response;
		}
		List<DiscountDTO> discDTOList = new ArrayList<DiscountDTO>();
		for (DiscountTbl discTbl : disLis) {			
		if(discTbl.getDiscountType()!=null){
			discType=discTbl.getDiscountType().getDisplayName();
		}if(discTbl.getCalculationType()!=null){
			calcType=discTbl.getCalculationType().getDisplayName();
		}			
			discDTOList.add(new DiscountDTO(discTbl.getId(),discTbl.getName(),discTbl.getDescription(),discType,discTbl.getDiscountValue(),calcType));
		}
		response.setDiscount(discDTOList);
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
				property = DiscountPropertyEnum.valueOf(exp.getName());
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
