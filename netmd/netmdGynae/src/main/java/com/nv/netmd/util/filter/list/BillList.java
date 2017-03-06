/**
 * BillList.java
 * @author Sreeram T G 
 *
 * Version 1.0 02-Sep-2013
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
import com.nv.netmd.pl.entity.BillTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.BillPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
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
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public BillListResponseDTO  list(FilterDTO filterDTO) throws ServiceException{
		BillListResponseDTO billList=new BillListResponseDTO();
		// get queryBuilder for bill from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.BILL);
		if (queryBuilder == null) {
			return billList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<BillTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<BillTbl> billLis;
		try {
			billLis = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		billList = getBillList(billLis);
		billList.setCount(count);
		billList.setSuccess(true);
		
		return billList;

	}

	/**
	 * @param billLis
	 * @return BillListResponseDTO
	 */
	private BillListResponseDTO getBillList(List<BillTbl> billLis) {
		BillListResponseDTO response = new BillListResponseDTO();
		List<BillDTO> billDTOList=new ArrayList<BillDTO>();
		String payStatus=null;
		String origin=null;
		int patId=0;
		String patientName=null;
		String date=null;
		if (billLis == null) {
			return response;
		}		
		for (BillTbl billTbl : billLis) {
			if(billTbl.getPatientTbl()!=null){
				patId=billTbl.getPatientTbl().getId();
				patientName=billTbl.getPatientTbl().getFirstName();
			}
			if(billTbl.getCreatedOn()!=null){
				SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
				date=df.format(billTbl.getCreatedOn());
			}
			origin=	billTbl.getOrigin().getDisplayName();
			payStatus=	billTbl.getPaymentStatus().getDisplayName();
			billDTOList.add(new BillDTO(billTbl.getPatientTbl().getId(),billTbl.getPatientTbl().getFirstName()+" "+billTbl.getPatientTbl().getLastName(),origin,payStatus,billTbl.getId(),billTbl.getUid(),date,billTbl.getGrandTotal(),billTbl.getPaidAmount(),billTbl.getBillAmount(),billTbl.getBillStatus().getDisplayName()));
		}
		
		response.setBillList(billDTOList);
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
				property = BillPropertyEnum.valueOf(exp.getName());
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
