/**
 * BlockList.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
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
import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.BlockPropertyEnum;
import com.nv.netmd.util.filter.queryBuilder.DepartmentPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class BlockList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	public BlockListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		BlockListResponseDTO blockList = new BlockListResponseDTO();

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
				.getQueryBuilder(Constants.BLOCK);
		if (queryBuilder == null) {
			return blockList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<BlockTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<BlockTbl> bloc;
		try {
			bloc = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		blockList = getBlockList(bloc);
		blockList.setCount(count);
		blockList.setSuccess(true);

		return blockList;
	}
	private BlockListResponseDTO getBlockList(List<BlockTbl> blocks) {
		BlockListResponseDTO response = new BlockListResponseDTO();
		String blockStatus=null;
		if (blocks == null) {
			return response;
		}
		List<BlockDTO> blockList = new ArrayList<BlockDTO>();
		for (BlockTbl blockTbl : blocks) {			
			if (blockTbl.getStatus() != null) {
				blockStatus=blockTbl.getStatus().getDisplayName();
			}
			
			
			blockList.add(new BlockDTO(blockTbl.getId(),blockTbl.getName(),blockTbl.getDescription(),blockStatus));
			
		}
		response.setBlock(blockList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = BlockPropertyEnum.valueOf(exp.getName());
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
