/**
 * MessageDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 May 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.MessageDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.Parameter;

/**
 * 
 */
public class MessageDaoImpl extends GenericDaoHibernateImpl implements MessageDao{
	@PersistenceContext()
	private EntityManager em;
	@Override
	@Transactional(readOnly=false)
	public MessageDTO viewMessage(int id) throws PersistenceException{
		MessageDTO response=new MessageDTO();
		MessageTbl messageTbl=(MessageTbl)getById(MessageTbl.class, id);
		if(messageTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.MessageNotExist);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(messageTbl.getViewed()==false){
			messageTbl.setViewed(true);
			update(messageTbl);
		}
		response.setId(messageTbl.getId());
		if(messageTbl.getDoctorTbl()!=null){
			response.setDoctorId(messageTbl.getDoctorTbl().getId());
			response.setDoctorName(messageTbl.getDoctorTbl().getFirstName());
		}
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(messageTbl.getCreatedTime()!=null)
			response.setDate(df.format(messageTbl.getCreatedTime()));
		response.setMessage(messageTbl.getMessage());
		response.setMessageViewed(messageTbl.getViewed());
		return response;
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
}
