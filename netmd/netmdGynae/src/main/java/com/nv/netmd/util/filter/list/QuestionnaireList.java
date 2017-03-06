/**
 * QuestionnaireList.java
 * 
 * @Author Nithesh Mohanan
 *
 * Version 1.0 Feb 26, 2014
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
import com.nv.netmd.pl.entity.CaseAnswerSetTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerDTO;
import com.nv.netmd.rs.dto.QuestionnaireListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.AntenatalPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class QuestionnaireList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

	@Override
	@Transactional(readOnly = false)
	public QuestionnaireListResponseDTO list(FilterDTO filterDTO)throws ServiceException {
		QuestionnaireListResponseDTO qstnList = new QuestionnaireListResponseDTO();
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.QUESTIONNAIRE);
		if (queryBuilder == null) {
			return qstnList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<CaseAnswerSetTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		
		List<CaseAnswerSetTbl> qstns;
		try {
			qstns = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		qstnList = getQuestionnaireList(qstns);
		qstnList.setCount(count);
		qstnList.setSuccess(true);

		return qstnList;
	}

	private QuestionnaireListResponseDTO getQuestionnaireList(List<CaseAnswerSetTbl> qstns) {
		QuestionnaireListResponseDTO response = new QuestionnaireListResponseDTO();
		//String createdOn=null;
		if (qstns == null) {
			return response;
		}
		List<NetmdQuestionAnswerDTO> questnList = new ArrayList<NetmdQuestionAnswerDTO>();
		for (CaseAnswerSetTbl caseAnsSetTbl : qstns) {			
			questnList.add(new NetmdQuestionAnswerDTO(caseAnsSetTbl.getId(),caseAnsSetTbl.getCreatedTime(),caseAnsSetTbl.getUpdatedTime()));
		}
		response.setQuestionnaireList(questnList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			
			try {
				property = AntenatalPropertyEnum.valueOf(exp.getName());
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
