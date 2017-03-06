/**
 * SettingsDaoImpl.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 09-Dec-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.SettingListTbl;
import com.nv.netmd.pl.entity.SettingTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.SettingListDTO;
import com.nv.netmd.rs.dto.ViewSettingResponseDTO;
import com.nv.netmd.settings.pl.dao.SettingsDao;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
/**
 *
 *
 * @author Nithesh Mohanan
 */
public class SettingsDaoImpl extends GenericDaoHibernateImpl implements SettingsDao {
	@PersistenceContext()
	private EntityManager em;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	


	/* (non-Javadoc)
	 * @see com.nv.weblims.settings.pl.SettingsDao#getByName(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=false)
	public ViewSettingResponseDTO getByName(String name) throws PersistenceException {
		ViewSettingResponseDTO response=new ViewSettingResponseDTO();
		SettingTbl settingTbl = getSettingByName(name); 
		if(settingTbl!=null){
			SettingDTO settings = new SettingDTO();
			settings.setId(settingTbl.getId());
			settings.setName(settingTbl.getGroupName());
			List<SettingListDTO> settingLists = new ArrayList<SettingListDTO>();
			List<SettingListTbl> settingsTblList=new ArrayList<SettingListTbl>(); 
			settingsTblList.addAll(settingTbl.getSettingListTbls());
			Collections.sort(settingsTblList,new Comparator(){
				@Override
				public int compare(Object obj1, Object obj2) {
					SettingListTbl test1 = (SettingListTbl) obj1;
					SettingListTbl test2 = (SettingListTbl) obj2;
					return test1.getItemId().compareTo(test2.getItemId());
				}
			});

			for(SettingListTbl settingList:settingsTblList){
				SettingListDTO settingListDTO = new SettingListDTO();
				settingListDTO.setUid(settingList.getId());
				settingListDTO.setItemId(settingList.getItemId());
				settingListDTO.setValue(settingList.getValue());
				settingLists.add(settingListDTO);
			}
			settings.setSettingList(settingLists);
			response.setSetting(settings);
			response.setSuccess(true);
		} else 
			response.setSuccess(false);
		response.setError(null);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.weblims.settings.pl.SettingsDao#updateSetting(com.nv.weblims.rs.dto.SettingDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(SettingDTO setting) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		SettingTbl settingTable=getById(SettingTbl.class, setting.getId());
		if(settingTable==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.SettingNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<SettingListTbl> settingListAddList=new ArrayList<SettingListTbl>();
		List<SettingListTbl> settingListDeleteList=new ArrayList<SettingListTbl>();
		Iterator<SettingListDTO> it = setting.getSettingList().iterator(); 
		while(it.hasNext()){	
			SettingListDTO settingList = it.next();
			List<SettingListTbl>settinglisttbl = getSettingListById(setting.getId());
			if(settinglisttbl==null){
				PersistenceException se =new PersistenceException(ErrorCodeEnum.SettingListNull);
				se.setDisplayErrMsg(true);
				throw se;				
			}
			if(settingList.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){  // if action name is add		
				SettingListTbl settingListTable = new SettingListTbl();
				settingListTable.setValue(settingList.getValue());
				int maxId = getMaxItemIdForSetting(setting.getId());
				settingListTable.setItemId(maxId);
				settingListTable.setSettingTbl(settingTable);
				settingListAddList.add(settingListTable);
			}
			SettingListTbl settingListTable= getSettingList(setting.getId(),settingList.getValue());
			if(settingList.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){ // if action name is delete
				settingListDeleteList.add(settingListTable);
			}			
		}
		for(SettingListTbl settingListTbl:settingListDeleteList){
			delete(settingListTbl);
		}	
		for(SettingListTbl settingListTbl:settingListAddList){
			save(settingListTbl);
		}

		settingTable.setGroupName(setting.getName());
		update(settingTable);
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * @param id
	 * @return
	 */
	private int getMaxItemIdForSetting(int id) {
		javax.persistence.Query query=em.createQuery(Query.GET_MAXITEMID_SETTING);
		query.setParameter("param1", id);
		return (Integer) query.getSingleResult();
	}

	@Override
	@Transactional(readOnly=false)
	public ViewSettingResponseDTO view(int settingUid) throws PersistenceException {
		ViewSettingResponseDTO response = new ViewSettingResponseDTO();
		SettingTbl settingTbl = getById(SettingTbl.class,settingUid);
		if(settingTbl==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.SettingNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		SettingDTO settingDTO = new SettingDTO();
		settingDTO.setId(settingUid);
		settingDTO.setName(settingTbl.getGroupName());

		List<SettingListTbl> settingList = getSettingListById(settingUid);
		List<SettingListDTO> settingDetail = new ArrayList<SettingListDTO>();
		if(settingList.size()>0){
			for(SettingListTbl settingListTbl:settingList){
				SettingListDTO settingLst=new SettingListDTO();
				settingLst.setUid(settingListTbl.getId());
				settingLst.setItemId(settingListTbl.getItemId());
				settingLst.setValue(settingListTbl.getValue());
				settingDetail.add(settingLst);
			}

		}
		settingDTO.setSettingList(settingDetail);


		response.setSetting(settingDTO);
		response.setSuccess(true);
		return response;
	}
	private SettingTbl getSettingByUid(int uid) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_SETTING_BY_ID);
		query.setParameter("param1", uid);
		return executeUniqueQuery(SettingTbl.class, query);
	}
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int uid) throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		SettingTbl settingTbl = getSettingByUid(uid);
		if(settingTbl==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.InvalidSettingUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<SettingListTbl> settingListtbl=getSettingListById(uid);
		for(SettingListTbl settinglistTbl:settingListtbl){
			delete(settinglistTbl);
		}
		delete(settingTbl);
		response.setSuccess(true);
		return response;
	}
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(SettingDTO setting) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String groupName=setting.getName().trim();
		SettingTbl settingTbl=getSettingByName(groupName.toUpperCase());
		if(settingTbl!=null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.InvalidSettingName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		SettingTbl newSettingTbl = new SettingTbl();
		newSettingTbl.setGroupName(groupName);
		save(newSettingTbl);
		List<SettingListDTO> settingList= setting.getSettingList();

		if(!settingList.isEmpty()){			
			for (SettingListDTO settinglst: settingList) {
				SettingListTbl settingListTbl=new SettingListTbl();
				SettingTbl settingId = getById(SettingTbl.class,newSettingTbl.getId());
				settingListTbl.setValue(settinglst.getValue());
				settingListTbl.setSettingTbl(settingId);
				settingListTbl.setItemId(settinglst.getItemId());	
				save(settingListTbl); 
			}//end of for loop
		}


		response.setId(newSettingTbl.getId());
		response.setSuccess(true);
		return response;

	}
	private SettingListTbl getSettingList(int settingId ,String value ) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_SETTING_LIST);
		query.setParameter("param1", settingId);
		query.setParameter("param2", value);
		return executeUniqueQuery(SettingListTbl.class, query);	
	}
	private List<SettingListTbl> getSettingListById(int uid) throws PersistenceException {	
		javax.persistence.Query query=em.createQuery(Query.GET_SETTING_LIST_BY_SETTING_ID);
		query.setParameter("param1", uid);
		return executeQuery(SettingListTbl.class, query);
	}
	private SettingTbl getSettingByName(String groupName) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_SETTINGS_BY_NAME);
		query.setParameter("param1", groupName);
		return executeUniqueQuery(SettingTbl.class, query);
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
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
