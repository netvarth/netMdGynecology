/**
 * FilterDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 9, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 * 
 */
public class FilterDaoImpl extends GenericDaoHibernateImpl implements FilterDao {
	@PersistenceContext()
	private EntityManager em;

	@Override
	public EntityManager getEM() {
		return em;
	}
}
