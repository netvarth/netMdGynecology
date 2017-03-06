/**
 * OwnerDaoImpl.java
 * @author Leo
 *
 * Version 1.0 Oct 9, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.OwnerDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.NetmdTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.OwnerDetailDTO;
import com.nv.netmd.rs.dto.Parameter;

/**
 *
 *
 * @author Leonora Louis
 */
public class OwnerDaoImpl extends GenericDaoHibernateImpl implements OwnerDao {
	@PersistenceContext()
	private EntityManager em;
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.OwnerDao#view(int)
	 */
	@Override
	@Transactional(readOnly = false)
	public OwnerDetailDTO view(int id) throws PersistenceException {
		OwnerDetailDTO owner=new OwnerDetailDTO();
		NetmdTbl netmdtbl=getOwnerById(id);
		if(netmdtbl==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.OwnerNotFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;	
		}
		owner.setId(netmdtbl.getId());
		owner.setFirstName(netmdtbl.getOwnerFirstName());
		owner.setEmail(netmdtbl.getOwnerEmail());
		owner.setAddress(netmdtbl.getOwnerAddress());
		owner.setLastName(netmdtbl.getOwnerLastName());
		owner.setMobile(netmdtbl.getOwnerMobile());
		owner.setPhone(netmdtbl.getOwnerPhone());
		
		return owner;
	}

	/**
	 * @param id
	 * @return
	 * @throws PersistenceException 
	 */
	private NetmdTbl getOwnerById(int id) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_OWNER_BY_LOGINID);
		query.setParameter("param1", id);
		return executeUniqueQuery(NetmdTbl.class, query);
	
	}

}
