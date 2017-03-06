/**
 * SyncDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 8, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.sync.pl.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.common.CoreJsonOperations;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.RemoteCallException;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.LoginTbl;
import com.nv.netmd.pl.entity.NetmdBranchTbl;
import com.nv.netmd.pl.entity.NetmdPassphraseTbl;
import com.nv.netmd.pl.entity.NetmdTbl;
import com.nv.netmd.pl.entity.NetmdUserTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.HeaderDTO;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.NetMdUserDetail;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.SyncDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;
import com.nv.netmd.sync.pl.dao.SyncDao;

/**
 * 
 */

public class SyncDaoImpl extends GenericDaoHibernateImpl implements SyncDao {
	@PersistenceContext()
	private EntityManager em;
	private String serverUrl;
	private static final Log log = LogFactory.getLog(SyncDaoImpl.class);
	@Override
	@Transactional(readOnly = false)
	public boolean isLoginEmpty() throws PersistenceException {
		// TODO Auto-generated method stub
		List<LoginTbl> loginList = (ArrayList<LoginTbl>) getLoginTblList();
		if (loginList.isEmpty()) {
			return false;
		}
		return true;
	}

	public String getLocalMacId() throws PersistenceException {
		//get mac id of system
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		String macId = null;
		try {	 
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());	 
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);	 
			byte[] mac = network.getHardwareAddress();	 
			System.out.print("Current MAC address : ");			
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			System.out.println(sb.toString());
			macId = sb.toString();
		} catch (UnknownHostException e) {	 
			log.error("Unknown host name:",e);
			PersistenceException se = new PersistenceException(ErrorCodeEnum.HostUnknown);
			se.setDisplayErrMsg(true);
			throw se;	 
		} catch (SocketException e){	
			log.error("Socket exception:",e);
			PersistenceException se = new PersistenceException(ErrorCodeEnum.HostUnknown);
			se.setDisplayErrMsg(true);
			throw se;
		
	} catch (Exception e){	
		log.error("Exception:",e);
		PersistenceException se = new PersistenceException(ErrorCodeEnum.ActivationFailed);
		se.setDisplayErrMsg(true);
		throw se;
	}
		return macId;
	}

	public SyncResponseDTO getSyncData(SyncDTO syncDTO) throws PersistenceException{
		SyncResponseDTO syncResponseDTO=new SyncResponseDTO();
		try {
			syncResponseDTO = (SyncResponseDTO) CoreJsonOperations.call(serverUrl+"youNeverWait/netmd/ui/sync/syncData",syncDTO,SyncResponseDTO.class);
		} catch (RemoteCallException e) {
			e.printStackTrace();
		}
		return syncResponseDTO;
	}
	public List<LoginTbl> getLoginTblList() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_LOGIN_TBL);
		return executeQuery(LoginTbl.class, query);
	}
	/**
	 * Activation process of netmd 
	 * @param netMdActivation
	 * @return ResponseDTO
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public NetMdActivationResponseDTO createNetMdDetails(NetMdActivationResponseDTO netMdActivation,HeaderDTO headers) throws PersistenceException {
		// TODO Auto-generated method stub
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		NetmdTbl netMdTbl = new NetmdTbl();
		NetmdBranchTbl netMdBranchTbl = new NetmdBranchTbl();
		NetmdPassphraseTbl  netmdPassphraseTbl=new NetmdPassphraseTbl();
//		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date=new Date();
		List<NetmdPassphraseTbl>  netmdPassphr=getPassPhraseTbles();
			if(!netmdPassphr.isEmpty()){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.AlreadyRegistred);
				se.setDisplayErrMsg(true);
				throw se;
			}
		
		if (netMdActivation.getNetmd() != null) {
			// save to netmd table and login table

			LoginTbl loginTbl = new LoginTbl();
			netMdTbl.setGlodalId(netMdActivation.getNetmd().getGlobalId());
			netMdTbl.setOwnerFirstName(netMdActivation.getNetmd()
					.getOwnerFirstName());
			netMdTbl.setOwnerLastName(netMdActivation.getNetmd()
					.getOwnerLastName());
			netMdTbl.setName(netMdActivation.getNetmd().getName());
			netMdTbl.setOwnerAddress(netMdActivation.getNetmd()
					.getOwnerAddress());
			netMdTbl.setOwnerEmail(netMdActivation.getNetmd().getOwnerEmail());
			netMdTbl.setOwnerPhone(netMdActivation.getNetmd().getOwnerPhone());
			netMdTbl.setOwnerMobile(netMdActivation.getNetmd().getOwnerMobile());
			netMdTbl.setHeadOfficeName(netMdActivation.getNetmd()
					.getHeadOfficeName());
			netMdTbl.setHeadOfficeAddress(netMdActivation.getNetmd()
					.getHeadOfficeAddress());
			netMdTbl.setHeadOfficeEmail(netMdActivation.getNetmd()
					.getHeadOfficeEmail());
			netMdTbl.setHeadOfficeMobile(netMdActivation.getNetmd()
					.getHeadOfficeMobile());
			netMdTbl.setHeadOfficePhone(netMdActivation.getNetmd()
					.getHeadOfficePhone());
			netMdTbl.setLogo(netMdActivation.getNetmd().getLogo());
			StatusEnum status = StatusEnum.getEnum(netMdActivation.getNetmd().getStatus());
			netMdTbl.setStatus(status);
			netMdTbl.setCreateDateTime(date);
			loginTbl.setPassword(netMdActivation.getNetmd().getPassword());
			loginTbl.setUserName(netMdActivation.getNetmd().getUserName());
			loginTbl.setStatus(StatusEnum.Active);
			UserTypeEnum userType = UserTypeEnum.getEnum(netMdActivation.getNetmd().getUserType());
			loginTbl.setUserType(userType);
			
			save(loginTbl);
			netMdTbl.setLoginTbl(loginTbl);
			save(netMdTbl);
		}

		if (netMdActivation.getBranch() != null) {
			// save the details of branch

			netMdBranchTbl.setName(netMdActivation.getBranch().getName());
			netMdBranchTbl.setAddress(netMdActivation.getBranch().getAddress());
			netMdBranchTbl.setPhone(netMdActivation.getBranch().getPhone());
			netMdBranchTbl.setMobile(netMdActivation.getBranch().getMobile());
			netMdBranchTbl.setGlobalId(netMdActivation.getBranch().getGlobalId());
			StatusEnum status = StatusEnum.getEnum(netMdActivation.getBranch().getStatus());
			netMdBranchTbl.setStatus(status);
			netMdBranchTbl.setCreateDateTime(date);
			netMdBranchTbl.setNetmdTbl(netMdTbl);
			save(netMdBranchTbl);

		}
		if (!netMdActivation.getUser().isEmpty()) {
			for (NetMdUserDetail netMdUserDetail : netMdActivation.getUser()) {				
				// save to netmd user table

				LoginTbl loginTbl = new LoginTbl();
				NetmdUserTbl netmdUserTbl = new NetmdUserTbl();
				loginTbl.setPassword(netMdUserDetail.getPassword());
				loginTbl.setUserName(netMdUserDetail.getUserName());
				UserTypeEnum userType = UserTypeEnum.getEnum(netMdUserDetail.getUserType());
				loginTbl.setUserType(userType);
				save(loginTbl);
				netmdUserTbl.setFirstName(netMdUserDetail.getFirstName());
				netmdUserTbl.setLastName(netMdUserDetail.getLastName());
				netmdUserTbl.setAddress(netMdUserDetail.getAddress());
				netmdUserTbl.setGlobalId(netMdUserDetail.getGlobalId());
				netmdUserTbl.setEmail(netMdUserDetail.getEmail());
				netmdUserTbl.setMobile(netMdUserDetail.getMobile());
				netmdUserTbl.setPhone(netMdUserDetail.getPhone());
				netmdUserTbl.setNetmdBranchTbl(netMdBranchTbl);
				StatusEnum status = StatusEnum.getEnum(netMdUserDetail.getStatus());
				netmdUserTbl.setStatus(status);
				netmdUserTbl.setCreateDateTime(date);
				save(netmdUserTbl);
			}
		}
		netmdPassphraseTbl.setMacId(headers.getMacId());
		netmdPassphraseTbl.setPassPhrase(headers.getPassPhrase());
		netmdPassphraseTbl.setNetmdTbl(netMdTbl);
		netmdPassphraseTbl.setNetmdBranchTbl(netMdBranchTbl);
		netmdPassphraseTbl.setPrimaryKey(netMdActivation.isPrimary());
		save(netmdPassphraseTbl);
		SyncTbl syncTbl=new SyncTbl();
		syncTbl.setUploadedTime(new Date());
		//		String downloadedTime=df1.format(new Date());
		//		syncTbl.setDownloadedTime(downloadedTime);
		save(syncTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<NetmdPassphraseTbl>getPassPhraseTbles() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_PASSPHRASE);
		return executeQuery(NetmdPassphraseTbl.class, query);
	}
	/**
	 * get last synchronized time from sync table
	 * @throws PersistenceException 
	 */

	@Override
	@Transactional(readOnly=false)
	public String getLastSyncTime() throws PersistenceException{		
		String lastSyncTime = null;
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){
			if(syncTbl.getDownloadedTime()!=null && !syncTbl.getDownloadedTime().equals(""))
				lastSyncTime=syncTbl.getDownloadedTime().trim();
		}
		return lastSyncTime;
	}
	/**
	 * get syncTbl
	 * @return SyncTbl
	 * @throws PersistenceException 
	 */
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}

	/**
	 * get header details from the netmd  pass phrase table
	 * @throws PersistenceException 
	 */

	@Override
	@Transactional(readOnly=false)
	public HeaderDTO getHeader() throws PersistenceException {
		HeaderDTO response=new HeaderDTO();
		NetmdPassphraseTbl passphrase=getPassPhrase();
		if(passphrase!=null){
			response.setMacId(passphrase.getMacId());
			if(passphrase.getNetmdBranchTbl()!=null)
				response.setBranchId(passphrase.getNetmdBranchTbl().getGlobalId());
			if(passphrase.getNetmdTbl()!=null)
				response.setHeadOfficeId(passphrase.getNetmdTbl().getGlodalId());
			response.setPassPhrase(passphrase.getPassPhrase().trim());
		}
		return response;
	}
	public NetmdPassphraseTbl getPassPhrase() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_PASSPHRASE);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}
	/**
	 * @return the serverUrl
	 */
	public String getServerUrl() {
		return serverUrl;
	}



	/**
	 * @param serverUrl the serverUrl to set
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * Activation of netMd call to YNW
	 */
	@Override
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header)throws PersistenceException {
		// TODO Auto-generated method stub
		NetMdActivationResponseDTO response=new NetMdActivationResponseDTO();
		try {
			response = (NetMdActivationResponseDTO) CoreJsonOperations.call(serverUrl+"youNeverWait/netmd/ui/netMd/activateNetMd", header, NetMdActivationResponseDTO.class);
		} catch (RemoteCallException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * get Mac status
	 */
//	@Override
//	public NetMdResponseDTO getMacStatus(PassPhraseDTO passPhrase) {
//		NetMdResponseDTO response=new NetMdResponseDTO();		
//		response = (NetMdResponseDTO) CoreJsonOperations.call(serverUrl+"youNeverWait/ws/ui/netMd/getMacStatus/"+passPhrase.getPassPhrase(),NetMdResponseDTO.class);
//		return response;
//	}
	/**
	 * update syncTbl with last sync time as downloaded time
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateSyncTbl(String lastSyncTime) throws PersistenceException{			

		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){
			syncTbl.setDownloadedTime(lastSyncTime);
			update(syncTbl);
		}

	}
	/**
	 * Update syncTbl by that uploaded time
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateLastUploadedTime(Date uploadedTime) throws PersistenceException{	
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){
			syncTbl.setUploadedTime(uploadedTime);
			update(syncTbl);
		}

	}


	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#updateNetmdDetails(com.nv.netmd.rs.dto.NetMdDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateNetmdDetails(NetMdDTO netmd) throws PersistenceException {
		
		NetmdTbl netmdTbl=(NetmdTbl)getByGlobalId(netmd.getGlobalId());
		if(netmdTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NetmdNotFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(netmd.getId())));
			se.setDisplayErrMsg(true);
			throw se;
			
		}
		netmdTbl.setName(netmd.getName());
		netmdTbl.setOwnerFirstName(netmd.getOwnerFirstName());
		netmdTbl.setOwnerLastName(netmd.getOwnerLastName());
		netmdTbl.setOwnerAddress(netmd.getOwnerAddress());
		netmdTbl.setOwnerEmail(netmd.getOwnerEmail());
		netmdTbl.setOwnerMobile(netmd.getOwnerMobile());
		netmdTbl.setOwnerPhone(netmd.getOwnerPhone());
		netmdTbl.setHeadOfficeName(netmd.getHeadOfficeName());
		netmdTbl.setHeadOfficeAddress(netmd.getHeadOfficeAddress());
		netmdTbl.setHeadOfficeEmail(netmd.getHeadOfficeEmail());
		netmdTbl.setHeadOfficeMobile(netmd.getHeadOfficeMobile());
		netmdTbl.setHeadOfficePhone(netmd.getHeadOfficePhone());
		netmdTbl.setLogo(netmd.getLogo());
		update(netmdTbl);
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param class1
	 * @param globalId
	 * @return
	 * @throws PersistenceException 
	 */
	private NetmdTbl getByGlobalId( int globalId) throws PersistenceException {
		
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(NetmdTbl.class, query);
		// TODO Auto-generated method stub
		
	}

}
