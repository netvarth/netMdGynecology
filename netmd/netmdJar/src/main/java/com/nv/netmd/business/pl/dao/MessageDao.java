/**
* MessageDao.java
* 
* @Author Sreeram
*
* Version 1.0 May 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.MessageDTO;

/**
 * 
 */
public interface MessageDao {
	public MessageDTO viewMessage(int id)throws PersistenceException;
}
