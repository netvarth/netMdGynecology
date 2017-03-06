/**
 * settingsList.java
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.pl.entity.SettingListTbl;
import com.nv.netmd.pl.entity.SettingTbl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.SettingDetail;
import com.nv.netmd.rs.dto.SettingListDTO;
import com.nv.netmd.rs.dto.SettingListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.SettingsPropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class SettingsList extends FilterValidator implements FilterService{
	@PersistenceContext()
	private EntityManager em;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	
	@Override
	@Transactional(readOnly = false)	
	public SettingListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		SettingListResponseDTO settingsList=new SettingListResponseDTO();
	
		// get queryBuilder for order from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder("settings");
		if (queryBuilder == null) {
			return settingsList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

		// get filter from filter factory by setting expression name and
		// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
				// build query
				TypedQuery<SettingTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
						filterDTO.getFrom(), filterDTO.getCount());
				Long count;
				try {
					count = queryBuilder.getCount();
				} catch (PersistenceException e1) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
				}
				// execute query
				List<SettingTbl> settngList;
				try {
					settngList = queryBuilder.executeQuery(q);
				} catch (PersistenceException e1) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
				}
				try {
					settingsList = getsettingsList(settngList);
				} catch (PersistenceException e) {
					e.printStackTrace();
				}
				settingsList.setCount(count);
				settingsList.setSuccess(true);
				return settingsList;
	
	}

	/**
	 * @param settingsTypelist
	 * @return settingsListResponseDTO
	 * @throws PersistenceException 
	 */
	private SettingListResponseDTO getsettingsList(List<SettingTbl> SettingList) throws PersistenceException {
		SettingListResponseDTO response = new SettingListResponseDTO();
		if (SettingList == null) {
			return response;
		}
		List<SettingDetail> settingsDetail = new ArrayList<SettingDetail>();
			for (SettingTbl settingTbl : SettingList) {	
		List<SettingListDTO>settingList=new ArrayList<SettingListDTO>();
			SettingDetail detail=new SettingDetail();
			detail.setGroupName(settingTbl.getGroupName());
			detail.setUid(settingTbl.getId());
			
			if(settingTbl.getSettingListTbls()!=null){
				List<SettingListTbl> stngList=getSettingListBySettingId(settingTbl.getId());
			for (SettingListTbl lst : stngList) {	
					SettingListDTO lstDTO=new SettingListDTO();
					lstDTO.setValue(lst.getValue());
					settingList.add(lstDTO);
				}
			}
			detail.setSetting(settingList);
			settingsDetail.add(detail);
			}
		
		response.setSetting(settingsDetail);
	
		return response;
	
	}
	
	public List<SettingListTbl> getSettingListBySettingId(Integer id) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_SETTING_LIST_BY_SETTING_ID);
		query.setParameter("param1", id);
		return executeQuery(SettingListTbl.class, query);
	}

	
	

//	private List<SettingListTbl> executeQuery(Class<SettingListTbl> class1,
//			javax.persistence.Query query) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	private List<SettingListTbl> executeQuery(Class<SettingListTbl> class1,
			javax.persistence.Query query) throws PersistenceException{
		List<SettingListTbl>  response=null;
		try{
			response= query.getResultList();
		}
		catch (NoResultException e) {
			return null;
		}
		catch (ClassCastException e) {
			PersistenceException se= new PersistenceException(ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se= new PersistenceException(ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		}catch (RuntimeException e) {
			PersistenceException se= new PersistenceException(ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
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
				property = SettingsPropertyEnum.valueOf(exp.getName());
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
