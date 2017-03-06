/**
 * DepartmentList.java
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
import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.DepartmentPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class DepartmentList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	@Override
	@Transactional(readOnly = false)
	public DepartmentListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		DepartmentListResponseDTO departmentList = new DepartmentListResponseDTO();
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.DEPARTMENT);
		if (queryBuilder == null) {
			return departmentList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<DepartmentTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<DepartmentTbl> dep;
		try {
			dep = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		departmentList = getDepartmentList(dep);
		departmentList.setCount(count);
		departmentList.setSuccess(true);

		return departmentList;
	}
	private DepartmentListResponseDTO getDepartmentList(List<DepartmentTbl> departments) {
		DepartmentListResponseDTO response = new DepartmentListResponseDTO();
		String departmentStatus=null;
		if (departments == null) {
			return response;
		}
		List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
		for (DepartmentTbl departmentTbl : departments) {			
			if (departmentTbl.getStatus() != null) {
				departmentStatus=departmentTbl.getStatus().getDisplayName();
			}
			
			departmentList.add(new DepartmentDTO(departmentTbl.getId(),departmentTbl.getName(),departmentTbl.getDescription(),departmentStatus));
			
		}
		response.setDepartment(departmentList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = DepartmentPropertyEnum.valueOf(exp.getName());
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
