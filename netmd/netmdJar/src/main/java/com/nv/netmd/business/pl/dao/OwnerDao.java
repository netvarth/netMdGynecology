/**
 * OwnerDao.java
 * @author Leo
 *
 * Version 1.0 Oct 9, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.OwnerDetailDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public interface OwnerDao {
	
	public OwnerDetailDTO view(int id)throws PersistenceException;

}
