/**
 * TaxList.java
 * @author Leo
 *
 * Version 1.0 Aug 13, 2013
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
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.BedTypePropertyEnum;
import com.nv.netmd.util.filter.queryBuilder.TaxPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Leonora Louis
 */
public class TaxList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory; 
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public TaxListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		TaxListResponseDTO taxList=new TaxListResponseDTO();
	    QueryBuilder queryBuilder=queryBuilderFactory.getQueryBuilder(Constants.TAX);
		if (queryBuilder == null) {
			return taxList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<TaxTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<TaxTbl> tax;
				try {
					tax = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				taxList = getTaxList(tax);
				taxList.setCount(count);
				taxList.setSuccess(true);

				return taxList;
		// TODO Auto-generated method stub
	
	}

	/**
	 * @param tax
	 * @return
	 */
	private TaxListResponseDTO getTaxList(List<TaxTbl> tax) {
		TaxListResponseDTO response = new TaxListResponseDTO();
		String calType=null;
		if (tax == null) {
			return response;
		}
		List<TaxDTO> taxList = new ArrayList<TaxDTO>();
		for (TaxTbl taxTbl : tax) {	
			if(taxTbl.getCalculationType()!=null){
				calType=taxTbl.getCalculationType().getDisplayName();
			}
			taxList.add(new TaxDTO(taxTbl.getId(),taxTbl.getName(),taxTbl.getDescription(),taxTbl.getTaxVal(),calType));
			
		}
		response.setTaxlist(taxList);
		return response;
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
				property = TaxPropertyEnum.valueOf(exp.getName());
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
	

}
