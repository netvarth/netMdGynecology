/**
 * RoomTypeList.java
 * @author Sreeram T G 
 *
 * Version 1.0 Aug 2, 2013
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
import com.nv.netmd.pl.entity.RoomTypeTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.rs.dto.RoomTypeListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.RoomTypePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomTypeList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	public RoomTypeListResponseDTO list(FilterDTO filterDTO) throws ServiceException{
		System.out.println(filterDTO.getCount());
		RoomTypeListResponseDTO roomTypeList = new RoomTypeListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
//		ErrorDTO error =validate(filterDTO);
//		if (error != null) {
//			roomTypeList.setError(error);
//			roomTypeList.setSuccess(false);
//			return roomTypeList;
//		}

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.ROOMTYPE);
		if (queryBuilder == null) {
			return roomTypeList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<RoomTypeTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<RoomTypeTbl> roomType;
		try {
			roomType = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		roomTypeList = getRoomTypeList(roomType);
		roomTypeList.setCount(count);
		roomTypeList.setSuccess(true);

		return roomTypeList;
	}
	private RoomTypeListResponseDTO getRoomTypeList(List<RoomTypeTbl> roomType) {
		RoomTypeListResponseDTO response = new RoomTypeListResponseDTO();
		String roomTypeStatus=null;
		if (roomType == null) {
			return response;
		}
		List<RoomTypeDTO> roomTypeList = new ArrayList<RoomTypeDTO>();
		for (RoomTypeTbl roomTypeTbl : roomType) {			
			if (roomTypeTbl.getStatus() != null) {
				roomTypeStatus=roomTypeTbl.getStatus().getDisplayName();
			}
			
			roomTypeList.add(new RoomTypeDTO(roomTypeTbl.getId(),roomTypeTbl.getType(),roomTypeTbl.getRent(),roomTypeTbl.getNoOfBed(),roomTypeTbl.getCount(),roomTypeStatus));
			
		}
		response.setRoomType(roomTypeList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = RoomTypePropertyEnum.valueOf(exp.getName());
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
