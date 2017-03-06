/**
 * DoctorList.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 22, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
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
import com.nv.netmd.pl.entity.DoctorEducationalQualificationTbl;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DoctorListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ReferralDetails;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.DoctorPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class DoctorList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

	@Override
	@Transactional(readOnly = false)
	public DoctorListResponseDTO list(FilterDTO filterDTO)  throws ServiceException{

		System.out.println(filterDTO.getCount());
		DoctorListResponseDTO doctorList = new DoctorListResponseDTO();

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.DOCTOR);
		if (queryBuilder == null) {
			return doctorList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<DoctorTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<DoctorTbl> doctors;
		try {
			doctors = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		doctorList = getDotcorList(doctors);
		doctorList.setCount(count);
		doctorList.setSuccess(true);

		return doctorList;
	}

	private DoctorListResponseDTO getDotcorList(List<DoctorTbl> doctors) {
		DoctorListResponseDTO response = new DoctorListResponseDTO();
		if (doctors == null) {
			return response;
		}
		List<ReferralDetails> doctorList = new ArrayList<ReferralDetails>();
		for (DoctorTbl doctor : doctors) {
			String qualification="";
			if(!doctor.getDoctorEducationalQualificationTbls().isEmpty()){
				for (DoctorEducationalQualificationTbl referralQualificationTbl : doctor.getDoctorEducationalQualificationTbls()) {
					qualification=qualification+referralQualificationTbl.getEducationalDegree();
					qualification=qualification+",";

				}
				qualification = qualification.substring(0, qualification.length() - 1);
				//System.out.println("Qualification"+qualification);
			}
			doctorList.add(new ReferralDetails(doctor.getId(),doctor.getFirstName(),doctor.getLastName(),doctor.getAddress(),doctor.getMobile(),doctor.getEmail(),doctor.getSpecialization(),qualification.trim()));
		}
		response.setReferral(doctorList);
		return response;
	}
	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = DoctorPropertyEnum.valueOf(exp.getName());
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
