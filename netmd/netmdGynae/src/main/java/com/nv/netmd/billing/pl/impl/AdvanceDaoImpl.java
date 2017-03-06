 /**
* AdvanceDaoImpl.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.pl.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.billing.pl.dao.AdvanceDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.AdvanceTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.impl.ItemDaoImpl;

public class AdvanceDaoImpl extends GenericDaoHibernateImpl implements AdvanceDao {
		
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(ItemDaoImpl.class);
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(AdvanceDTO advance)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		AdvanceTbl AdvanceTbl=new AdvanceTbl();
		String date=null;
		PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,advance.getPatientId());
		if(patientTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.PatientNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		AdvanceTbl.setPatientTbl(patientTbl);
		AdvanceTbl.setAmount(advance.getAmount());	
		AdvanceTbl.setAdvanceDate(new Date());
		StatusEnum status=StatusEnum.Active;
		AdvanceTbl.setStatus(status);
		save(AdvanceTbl);
		response.setSuccess(true);
		response.setId(AdvanceTbl.getId());
		return response;
	}
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(AdvanceDTO advance) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
	
		AdvanceTbl advanceTbl=(AdvanceTbl)getById(AdvanceTbl.class, advance.getId());
		if(advanceTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoAdvanceFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(advance.getId())));
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,advance.getPatientId());
		if(patientTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.PatientNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		advanceTbl.setPatientTbl(patientTbl);
		advanceTbl.setAmount(advance.getAmount());	
		advanceTbl.setAdvanceDate(new Date());
		StatusEnum status=StatusEnum.Active;
		advanceTbl.setStatus(status);
		update(advanceTbl);
		response.setSuccess(true);
		response.setError(null);
		response.setId(advanceTbl.getId());
		return response;
	}

	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		AdvanceTbl AdvanceTbl=(AdvanceTbl)getById(AdvanceTbl.class,id);
		if(AdvanceTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoAdvanceFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		StatusEnum status=StatusEnum.Inactive;
		AdvanceTbl.setStatus(status);
		update(AdvanceTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
		
	}

	@Override
	@Transactional(readOnly=false)
	public AdvanceDTO view(int id) throws PersistenceException {
		AdvanceDTO response=new AdvanceDTO();
		AdvanceTbl AdvanceTbl=(AdvanceTbl)getById(AdvanceTbl.class,id);
		if(AdvanceTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoItemFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(AdvanceTbl.getId());
		response.setPatientId(AdvanceTbl.getPatientTbl().getId());
		response.setAmount(AdvanceTbl.getAmount());
		response.setPatientName(AdvanceTbl.getPatientTbl().getFirstName());
		response.setEmail(AdvanceTbl.getPatientTbl().getEmail());
		response.setStatus(AdvanceTbl.getStatus().getDisplayName());
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		response.setDate(df.format(AdvanceTbl.getAdvanceDate()));
		response.setSuccess(true);
		response.setError(null);
		return response;	
	}
	
	
}
