/**
 * ItemList.java
 * @author Sreeram T G 
 *
 * Version 1.0 16-Aug-2013
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
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.BedTypePropertyEnum;
import com.nv.netmd.util.filter.queryBuilder.ItemPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	
	@Override
	@Transactional(readOnly = false)	
	public ItemListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		ItemListResponseDTO itemList=new ItemListResponseDTO();
	
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.ITEM);
		if (queryBuilder == null) {
			return itemList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<ItemTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<ItemTbl> itemLis;
				try {
					itemLis = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				itemList = getItemList(itemLis);
				itemList.setCount(count);
				itemList.setSuccess(true);

				return itemList;
	
	}

	/**
	 * @param itemTypelist
	 * @return ItemListResponseDTO
	 */
	private ItemListResponseDTO getItemList(List<ItemTbl> itemLis) {
		ItemListResponseDTO response = new ItemListResponseDTO();
		String ItemStatus=null;
		String available=null;
		int taxId=0;
		String taxName=null;
		
		if (itemLis == null) {
			return response;
		}
		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		for (ItemTbl itemTbl : itemLis) {			
			if (itemTbl.getStatus() != null) {
				ItemStatus=itemTbl.getStatus().getDisplayName();
			}
			
			if(itemTbl.getTaxTbl()!=null){
				taxId=itemTbl.getTaxTbl().getId();
				taxName=itemTbl.getTaxTbl().getName();
			}
			
			itemDTOList.add(new ItemDTO(itemTbl.getId(),itemTbl.getName(),itemTbl.getPrice(),itemTbl.getQuantity(),itemTbl.getDescription(),taxId,taxName,ItemStatus));
		}
		response.setItemList(itemDTOList);
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
				property = ItemPropertyEnum.valueOf(exp.getName());
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
