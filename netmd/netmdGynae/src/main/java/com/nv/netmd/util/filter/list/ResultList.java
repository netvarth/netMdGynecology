/**
* ResultList.java
* 
* @Author Sreeram
*
* Version 1.0 May 22, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.util.filter.list;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ResultTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.ResultPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class ResultList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	/**
	 * patient list
	 * 
	 * @param filterDTO
	 * @return ResultListResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResultListResponseDTO list(FilterDTO filterDTO) throws ServiceException{
		System.out.println(filterDTO.getCount());
		ResultListResponseDTO resultList = new ResultListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		//		ErrorDTO error = validate(filterDTO);
		//		if (error != null) {
		//			patientList.setError(error);
		//			patientList.setSuccess(false);
		//			return patientList;
		//		}

		// get queryBuilder from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.RESULT);
		if (queryBuilder == null) {
			return resultList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<ResultTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<ResultTbl> results;
		try {
			results = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		resultList = getResultList(results);
		resultList.setCount(count);
		resultList.setSuccess(true);

		return resultList;
	}

	private ResultListResponseDTO getResultList(List<ResultTbl> results) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		int doctorId=0;		
		String orderDate=null;
		String doctorName=null;
		int patientId=0;
		String patientName=null;
		String date=null;
		
		if (results == null) {
			return response;
		}
		List<ViewResultDTO> resultList = new ArrayList<ViewResultDTO>();
		for (ResultTbl rslt : results) {
			if(rslt.getPatientTbl()!=null){
				patientId=rslt.getPatientTbl().getId();
				patientName=rslt.getPatientTbl().getFirstName();
				
			}
			if(rslt.getDoctorTbl()!=null){
				doctorId=rslt.getDoctorTbl().getId();
				doctorName=rslt.getDoctorTbl().getFirstName();
				
			}
			if(rslt.getDate()!=null){
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
			date=df.format(rslt.getDate());
			orderDate=df.format(rslt.getOrderDate());
			}
			resultList.add(new ViewResultDTO(rslt.getId(),patientId,patientName,doctorId,rslt.getResult(),date,orderDate,rslt.getOrderUid(),rslt.getLabName(),rslt.getLabBranchName()));
		}
		response.setResultList(resultList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = ResultPropertyEnum.valueOf(exp.getName());
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
