/**
 * SyncDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 8, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.sync.pl.dao;


import java.util.Date;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.HeaderDTO;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.SyncDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;



/**
 * 
 */
public interface SyncDao {
	public boolean isLoginEmpty() throws PersistenceException;
	public NetMdActivationResponseDTO createNetMdDetails(NetMdActivationResponseDTO netMdActivation,HeaderDTO headers)throws PersistenceException;
	public HeaderDTO getHeader()throws PersistenceException;
	public String getLastSyncTime()throws PersistenceException;	
	public SyncResponseDTO getSyncData(SyncDTO syncDTO)throws PersistenceException;
	public String getLocalMacId()throws PersistenceException;
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header)throws PersistenceException;
	//public NetMdResponseDTO getMacStatus(PassPhraseDTO passPhrase);
	public void updateSyncTbl(String lastSyncTime)throws PersistenceException;
	public void updateLastUploadedTime(Date uploadedTime)throws PersistenceException;
	/**
	 * @param netmd 
	 * 
	 */
	public void updateNetmdDetails(NetMdDTO netmd)throws PersistenceException;
	
	
}
