/**
 * MedicalRecordList.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 25, 2013
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
import com.nv.netmd.pl.entity.MedicalRecordTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.MedicalPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class MedicalRecordList extends FilterValidator implements FilterService {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	/**
	 * list the medical record filter
	 ** 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public MedicalListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		MedicalListResponseDTO medicalList = new MedicalListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		//		ErrorDTO error =validate(filterDTO);
		//		if (error != null) {
		//			medicalList.setError(error);
		//			medicalList.setSuccess(false);
		//			return medicalList;
		//		}

		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.MEDICALRECORD);
		if (queryBuilder == null) {
			return medicalList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<MedicalRecordTbl> q = queryBuilder.buildQuery(
				filterDTO.isAsc(), filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<MedicalRecordTbl> record;
		try {
			record = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		medicalList = getMedicalList(record);
		medicalList.setCount(count);
		medicalList.setSuccess(true);

		return medicalList;

	}

	private MedicalListResponseDTO getMedicalList(List<MedicalRecordTbl> records) {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		int patientId=0;
		int doctorId=0;
		int caseId=0;
		String doctorName=null;
		String caseName=null;
		String recordType=null;
		String medicalRecord=null;
		String createdOn=null;
		if (records == null) {
			return response;
		}
		List<MedicalRecordDTO> medicalList = new ArrayList<MedicalRecordDTO>();
		for (MedicalRecordTbl medicalRecordTbl : records) {

			if (medicalRecordTbl.getPatientTbl() != null) {
				patientId=medicalRecordTbl.getPatientTbl().getId();
			}
			if (medicalRecordTbl.getDoctorTbl() != null) {
				doctorId=medicalRecordTbl.getDoctorTbl().getId();
				doctorName=medicalRecordTbl.getDoctorTbl().getFirstName();
			}
			if (medicalRecordTbl.getCaseTbl() != null) {
				caseId=medicalRecordTbl.getCaseTbl().getId();
				caseName=medicalRecordTbl.getCaseTbl().getCaseName();
			}
			if (medicalRecordTbl.getType() != null) {
				recordType=medicalRecordTbl.getType().getDisplayName();
			}

			DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
			if (medicalRecordTbl.getCreatedTime() != null) {
				createdOn = df.format(medicalRecordTbl.getCreatedTime());

			}
			medicalRecord=medicalRecordTbl.getMedicalRecord();
			medicalList.add(new MedicalRecordDTO(medicalRecordTbl.getId(),doctorId,patientId,caseId,caseName,doctorName,recordType,createdOn,medicalRecord));
		}
		response.setMedicalList(medicalList);
		return response;
	}

	public ErrorDTO validate(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = MedicalPropertyEnum.valueOf(exp.getName());
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
