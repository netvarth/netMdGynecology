/**
 * OwnerServiceImpl.java
 * @author Leo
 *
 * Version 1.0 Oct 9, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.OwnerService;
import com.nv.netmd.business.pl.dao.OwnerDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.OwnerDetailDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public class OwnerServiceImpl implements OwnerService{
	private  OwnerDao ownerDao;

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.OwnerService#view(int)
	 */
	@Override
	public OwnerDetailDTO view(int id) throws ServiceException {
		OwnerDetailDTO owner;
		try {
			owner = ownerDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return owner;
	}

	public OwnerDao getOwnerDao() {
		return ownerDao;
	}

	public void setOwnerDao(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}
	

}
