/**
 * BedList.java
 * @author Sreeram T G 
 *
 * Version 1.0 Aug 7, 2013
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
import com.nv.netmd.pl.entity.BedTbl;
import com.nv.netmd.pl.entity.BedTypeTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.BedPropertyEnum;
import com.nv.netmd.util.filter.queryBuilder.BedTypePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class BedList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	
	public BedListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		BedListResponseDTO bedList=new BedListResponseDTO();

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.BED);
		if (queryBuilder == null) {
			return bedList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<BedTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<BedTbl> bedLis;
				try {
					bedLis = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				bedList = getBedList(bedLis);
				bedList.setCount(count);
				bedList.setSuccess(true);

				return bedList;
	
	}

	/**
	 * @param bedTypelst
	 * @return
	 */
	private BedListResponseDTO getBedList(List<BedTbl> bedLis) {
		BedListResponseDTO response = new BedListResponseDTO();
		String bedStatus=null;
		String available=null;
		int bedTypeId=0;
		String bedType=null;
		int roomId=0;
		String roomName=null;
		if (bedLis == null) {
			return response;
		}
		List<BedDTO> bedDTOList = new ArrayList<BedDTO>();
		for (BedTbl bedTbl : bedLis) {			
			if (bedTbl.getStatus() != null) {
				bedStatus=bedTbl.getStatus().getDisplayName();
			}
			if(bedTbl.getAvailability()!=null){
				available=bedTbl.getAvailability().getDisplayName();
			}
			if(bedTbl.getBedTypeTbl()!=null){
				bedTypeId=bedTbl.getBedTypeTbl().getId();
				bedType=bedTbl.getBedTypeTbl().getType();
			}
			if(bedTbl.getRoomTbl()!=null){
				roomId=bedTbl.getRoomTbl().getId();
				roomName=bedTbl.getRoomTbl().getRoomNumber();
			}
			bedDTOList.add(new BedDTO(bedTbl.getId(), bedTbl.getPrefix(), bedTbl.getBedNumber(), available, bedTypeId, bedType, bedTbl.getDescription(), bedStatus, roomId, roomName));
			
		}
		response.setBedList(bedDTOList);
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
				property = BedPropertyEnum.valueOf(exp.getName());
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
