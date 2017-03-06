/**
 * RoomList.java
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
import com.nv.netmd.pl.entity.BlockTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.RoomTbl;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.RoomPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	public RoomListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		RoomListResponseDTO roomList = new RoomListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		//		ErrorDTO error =validate(filterDTO);
		//		if (error != null) {
		//			blockList.setError(error);
		//			blockList.setSuccess(false);
		//			return blockList;
		//		}

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.ROOM);
		if (queryBuilder == null) {
			return roomList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<RoomTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<RoomTbl> room;
		try {
			room = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		roomList = getRoomList(room);
		roomList.setCount(count);
		roomList.setSuccess(true);

		return roomList;
	}
	private RoomListResponseDTO getRoomList(List<RoomTbl> room) {
		RoomListResponseDTO response = new RoomListResponseDTO();
		String roomStatus=null;
		int roomTypeId=0;
		String roomTypeName=null;
		int blockId=0;
		String blockName=null;
		int departmentId=0;
		String departmentName=null;
		if (room == null) {
			return response;
		}
		List<RoomDTO> roomList = new ArrayList<RoomDTO>();
		for (RoomTbl roomTbl : room) {			
			if (roomTbl.getStatus() != null) {
				roomStatus=roomTbl.getStatus().getDisplayName();
			}
			if(roomTbl.getRoomTypeTbl()!=null){
				roomTypeId=roomTbl.getRoomTypeTbl().getId();
				roomTypeName=roomTbl.getRoomTypeTbl().getType();
			}
			if(roomTbl.getBlockTbl()!=null){
				blockId=roomTbl.getBlockTbl().getId();
				blockName=roomTbl.getBlockTbl().getName();
			}
			if(roomTbl.getDepartmentTbl()!=null){
				departmentId=roomTbl.getDepartmentTbl().getId();
				departmentName=roomTbl.getDepartmentTbl().getName();
			}
			roomList.add(new RoomDTO(roomTbl.getId(), roomTbl.getPrefix(), roomTbl.getRoomNumber(), roomTypeId, roomTypeName, blockId, blockName, departmentId, departmentName, roomTbl.getDescription(), roomStatus));

		}
		response.setRoom(roomList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = RoomPropertyEnum.valueOf(exp.getName());
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
