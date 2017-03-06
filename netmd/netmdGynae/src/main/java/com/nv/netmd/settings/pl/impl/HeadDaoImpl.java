 /**
* HeadDaoImpl.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 11, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.pl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.HeadTbl;
import com.nv.netmd.pl.entity.HeadTypeEnum;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.HeadDTO;
import com.nv.netmd.rs.dto.HeadListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.HeadDao;




/**
 * @author Nithesh Mohanan
 *
 */
public class HeadDaoImpl extends GenericDaoHibernateImpl implements HeadDao{
	
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(HeadDaoImpl.class);
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(HeadDTO head)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		String headName = head.getHeadName().trim().replaceAll(" +", " ");
		HeadTbl hed=(HeadTbl)getHeadByName(headName);
		if(hed!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.HeadWithNameExist);
			se.addParam(new Parameter(Constants.NAME,hed.getHeadName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		HeadTbl headTbl=new  HeadTbl();
		headTbl.setHeadName(head.getHeadName());
		headTbl.setDescription(head.getDescription());
		if(head.getParentHeadName()==null)
			headTbl.setHeadType(HeadTypeEnum.Head);
		else{
			headTbl.setHeadType(HeadTypeEnum.SubHead);
			headTbl.setParentHeadName(head.getParentHeadName());
			HeadTbl headtbl=(HeadTbl)getHeadByName(head.getParentHeadName());
			headTbl.setHeadTbl(headtbl);
		}
		StatusEnum status=StatusEnum.Active;
		headTbl.setStatus(status);
		save(headTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(headTbl.getId());
		return response;
	}

	public HeadTbl getHeadByName(String name) throws PersistenceException {
		String headName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_HEAD_BY_NAME);
		query.setParameter("param1",headName);		
		return executeUniqueQuery(HeadTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(HeadDTO head)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		HeadTbl headTbl=(HeadTbl)getById(HeadTbl.class, head.getId());
		if(headTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoHeadFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(head.getId())));
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		String headName = head.getHeadName().trim().replaceAll(" +", " ");
		HeadTbl hed=(HeadTbl)getHeadByName(headName);
		if(hed!=null){
			if(hed.getId()!=headTbl.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.HeadWithNameExist);
				se.addParam(new Parameter(Constants.NAME,head.getHeadName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		headTbl.setHeadName(head.getHeadName());
		headTbl.setParentHeadName(head.getParentHeadName());
		head.setHeadtype(headTbl.getHeadType().getDisplayName());
		headTbl.setDescription(head.getDescription());
		headTbl.setId(head.getId());
		update(headTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(headTbl.getId());
		return response;
	}

	@Override
	@Transactional(readOnly=false)
	public HeadDTO view(int id) throws PersistenceException {
		HeadDTO response=new HeadDTO();
		HeadTbl headTbl=(HeadTbl)getById(HeadTbl.class, id);
		if(headTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoHeadFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(headTbl.getId());
		response.setHeadName(headTbl.getHeadName());
		response.setDescription(headTbl.getDescription());
		response.setParentHeadName(headTbl.getParentHeadName());
		response.setHeadtype(headTbl.getHeadType().getDisplayName());
		if(headTbl.getHeadType().equals(HeadTypeEnum.Head.getDisplayName()))
			response.setParentHeadName(headTbl.getParentHeadName());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		HeadTbl headTbl=(HeadTbl)getById(HeadTbl.class, id);
		if(headTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoHeadFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//headTbl.setStatus(status);
		//update(headTbl);
		delete(headTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	
	@Override
	@Transactional(readOnly=false)
	public HeadListResponseDTO getHeads() throws PersistenceException {
		HeadListResponseDTO response=new HeadListResponseDTO();
		List<HeadDTO> headList=new ArrayList<HeadDTO>();
		List<HeadTbl> headTblList=(ArrayList<HeadTbl>)getHeadList();
		for (HeadTbl headTbl : headTblList) {
			HeadDTO head=new HeadDTO();
			head.setId(headTbl.getId());
			head.setHeadName(headTbl.getHeadName());
			head.setDescription(headTbl.getDescription());
			head.setParentHeadName(headTbl.getParentHeadName());
			head.setHeadtype(headTbl.getHeadType().getDisplayName());
			headList.add(head);
		   }
		response.setHeadList(headList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<HeadTbl> getHeadList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_HEAD);
		return executeQuery(HeadTbl.class, query);
	}

}
