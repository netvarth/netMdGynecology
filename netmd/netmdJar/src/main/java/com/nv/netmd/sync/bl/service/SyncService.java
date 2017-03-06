/**
* SyncService.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.sync.bl.service;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.PassPhraseDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;



/**
 * 
 */
public interface SyncService {
	public boolean isLoginEmpty() throws ServiceException;
	public NetMdActivationResponseDTO activateNetMd(PassPhraseDTO passPhrase) throws ServiceException;
	public SyncResponseDTO getSyncData() throws ServiceException;
	
}
