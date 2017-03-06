/**
 * expenseList.java
 * @author Nithesh Mohanan 
 *
 * Version 1.0 26-Aug-2013
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
import com.nv.netmd.pl.entity.ExpenseTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.ExpensePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class ExpenseList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	
	@Override
	@Transactional(readOnly = false)	
	public ExpenseListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		ExpenseListResponseDTO expenseList=new ExpenseListResponseDTO();
	
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.EXPENSE);
		if (queryBuilder == null) {
			return expenseList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<ExpenseTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				// execute query
				List<ExpenseTbl> expList;
				try {
					expList = queryBuilder.executeQuery(q);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
				expenseList = getExpenseList(expList);
				expenseList.setCount(count);
				expenseList.setSuccess(true);

				return expenseList;
	
	}

	/**
	 * @param expenseTypelist
	 * @return ExpenseListResponseDTO
	 */
	private ExpenseListResponseDTO getExpenseList(List<ExpenseTbl> expList) {
		ExpenseListResponseDTO response = new ExpenseListResponseDTO();
		//String headName=null;
		int headId=0;
		
		if (expList == null) {
			return response;
		}
		List<ExpenseDTO> expenseDTOList = new ArrayList<ExpenseDTO>();
		for (ExpenseTbl expenseTbl : expList) {	
			
			if(expenseTbl.getHeadTbl()!=null){
				headId=expenseTbl.getHeadTbl().getId();
				
			}
			
			expenseDTOList.add(new ExpenseDTO(expenseTbl.getId(),headId,expenseTbl.getExpenseName(),expenseTbl.getHeadName(),expenseTbl.getTotalAmount(),expenseTbl.getDescription(),expenseTbl.getPaidAmount(),expenseTbl.getBalance(),expenseTbl.getPaymentStatus(),null,null,null, null, true));
		}
		response.setExpenseList(expenseDTOList);
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
				property = ExpensePropertyEnum.valueOf(exp.getName());
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
