/**
 * ServiceDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 17-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
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
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SupportTbl;
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SupportDTO;
import com.nv.netmd.rs.dto.SupportListResponseDTO;
import com.nv.netmd.settings.pl.dao.SupportDao;


/**
 *
 *
 * @author Sreeram T G
 */
public class SupportDaoImpl extends GenericDaoHibernateImpl implements SupportDao{

	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(SupportDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ServiceDao#create(com.nv.netmd.rs.dto.ServiceDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create( SupportDTO support) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String servName = support.getName().trim().replaceAll(" +", " ");
		SupportTbl ser=(SupportTbl)getSupportByName(servName);
		if(ser!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ServiceWithNameExist);
			se.addParam(new Parameter(Constants.NAME,support.getName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		SupportTbl supportTbl=new SupportTbl();
		supportTbl.setName(support.getName());
		supportTbl.setDescription(support.getDescription());
		supportTbl.setPrice(support.getPrice());
		if(support.getTaxId()!=0){
		TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,support.getTaxId());
		if(taxTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoTaxFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(support.getTaxId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		supportTbl.setTaxTbl(taxTbl);
		}
		StatusEnum status=StatusEnum.Active;
		supportTbl.setStatus(status);
		save(supportTbl);
		response.setSuccess(true);
		response.setError(null);
		response.setId(supportTbl.getId());
		return response;
	}

	public SupportTbl getSupportByName(String name) throws PersistenceException{
		String supportName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_SUPPORT_BY_NAME);
		query.setParameter("param1",supportName);		
		return executeUniqueQuery(SupportTbl.class, query);
	}
	@Override
	@Transactional(readOnly=false)
	public SupportDTO view(int id) throws PersistenceException {
		SupportDTO response=new SupportDTO();
		SupportTbl support=(SupportTbl)getById(SupportTbl.class,id);
		if(support==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoServiceFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(support.getId());
		response.setName(support.getName());
		response.setDescription(support.getDescription());
		response.setPrice(support.getPrice());
		if(support.getTaxTbl()!=null){
			response.setTaxId(support.getTaxTbl().getId());
			response.setTaxName(support.getTaxTbl().getName());
		}

		response.setSuccess(true);
		response.setError(null);
		return response;
	}
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(SupportDTO support)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		SupportTbl supportTbl=getById(SupportTbl.class,support.getId());
		if(supportTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoServiceFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(support.getId())));
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		String supportName = support.getName().trim().replaceAll(" +", " ");
		SupportTbl supportTable=(SupportTbl)getSupportByName(supportName);
		if(supportTable!=null){
			if(supportTable.getId()!=support.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ServiceWithNameExist);
				se.addParam(new Parameter(Constants.NAME,support.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		supportTbl.setName(support.getName());
		supportTbl.setPrice(support.getPrice());
		supportTbl.setDescription(support.getDescription());
		//TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,support.getTaxId());
		if(support.getTaxId()!=0){
			TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,support.getTaxId());
			if(taxTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoTaxFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(support.getTaxId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			supportTbl.setTaxTbl(taxTbl);
			}
		StatusEnum status=StatusEnum.Active;		
		supportTbl.setStatus(status);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		SupportTbl supportTbl=getById(SupportTbl.class,id);
		if(supportTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoServiceFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;	
		//supportTbl.setStatus(status);
		//update(supportTbl);
		 delete(supportTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.SupportDao#getSupports()
	 */
	@Override
	@Transactional(readOnly=false)
	public SupportListResponseDTO getSupports() throws PersistenceException {
		// TODO Auto-generated method stub
		SupportListResponseDTO response=new SupportListResponseDTO();
		List<SupportDTO> supportList = new ArrayList<SupportDTO>();
		List<SupportTbl> supportTblList=(ArrayList<SupportTbl>)getSupportList();
		for (SupportTbl supportTbl : supportTblList) {
			float amount = 0;
			float stdRate=0;
			float taxVal=0;
			String taxType=null;
			SupportDTO support=new SupportDTO();
			support.setId(supportTbl.getId());
			support.setName(supportTbl.getName());
			support.setDescription(supportTbl.getDescription());
			support.setPrice(supportTbl.getPrice());
			if(supportTbl.getTaxTbl()!=null){
				support.setTaxId(supportTbl.getTaxTbl().getId());
				support.setTaxName(supportTbl.getTaxTbl().getName());
				support.setTaxValue(supportTbl.getTaxTbl().getTaxVal());
				taxVal=supportTbl.getTaxTbl().getTaxVal();
				if(supportTbl.getTaxTbl().getCalculationType()!=null){
					
					support.setTaxType(supportTbl.getTaxTbl().getCalculationType().getDisplayName());
					taxType=supportTbl.getTaxTbl().getCalculationType().getDisplayName();
					if(taxType==CalculationTypeEnum.Fixed.getDisplayName())
						amount=taxVal;
					else
						amount=supportTbl.getPrice()*taxVal/100;
				}

			}
			
			stdRate=supportTbl.getPrice()+amount;
			support.setStdRate(stdRate);
			supportList.add(support);
		}
		response.setSupportList(supportList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<SupportTbl> getSupportList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_SUPPORT);
		return executeQuery(SupportTbl.class, query);
	}
}
