 /**
* AdvanceList.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 23, 2013
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
import com.nv.netmd.common.SimpleDateFormat;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.AdvanceTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.AdvanceListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.AdvancePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

public class AdvanceList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

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

	@Override
	@Transactional(readOnly = false)
	public AdvanceListResponseDTO  list(FilterDTO filterDTO) throws ServiceException {
		AdvanceListResponseDTO advanceList=new AdvanceListResponseDTO();
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.ADVANCE);
		if (queryBuilder == null) {
			return advanceList;
		}
		// get filter from filter factory by setting expression name and
		// value to filter
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
			}
		// build query
		TypedQuery<AdvanceTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		List<AdvanceTbl> advancelst;
		try {
			advancelst = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		advanceList = getAdvanceList(advancelst);
		advanceList.setCount(count);
		advanceList.setSuccess(true);
		return advanceList;

	}

	private AdvanceListResponseDTO getAdvanceList(List<AdvanceTbl> advancelst) {
		AdvanceListResponseDTO response = new AdvanceListResponseDTO();
		String advanceStatus=null;
		String date=null;
		String patientName=null;
		
		if (advancelst == null) {
			return response;
		}
		List<AdvanceDTO> advanceDTOList = new ArrayList<AdvanceDTO>();
		for (AdvanceTbl advanceTbl : advancelst) {			
			if (advanceTbl.getStatus() != null) {
				advanceStatus=advanceTbl.getStatus().getDisplayName();
			}
			if(advanceTbl.getAdvanceDate()!=null){
				SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
				date=df.format(advanceTbl.getAdvanceDate());
			}
			if(advanceTbl.getPatientTbl()!=null){
				patientName=advanceTbl.getPatientTbl().getFirstName();
			}
			AdvanceDTO advanceDTO=new AdvanceDTO(advanceTbl.getId(),advanceTbl.getPatientTbl().getId(),date,advanceTbl.getAmount(),advanceStatus,patientName);
	        advanceDTOList.add(advanceDTO);
		}
		response.setAdvanceList(advanceDTOList);
		return response;
		
	}

	@Override
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = AdvancePropertyEnum.valueOf(exp.getName());
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
	}


