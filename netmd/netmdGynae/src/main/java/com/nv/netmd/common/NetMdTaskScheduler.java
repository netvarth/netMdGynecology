/**
 * NetMdTaskScheduler.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 2, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.common;


import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.netmd.sync.bl.service.SyncService;



/**
 * 
 */
public class NetMdTaskScheduler extends TimerTask {
	private SyncService syncService;
	private String pingUrl;
	private static final Log log = LogFactory.getLog(CoreJsonOperations.class);
	@Override
	public void run() {

		try {
			if (CoreJsonOperations.isReachable(pingUrl)){
			if(syncService.isLoginEmpty()==false){
				System.out.println("This message will print every 10 seconds");
			}
//			else{
//				syncService.getSyncData();
//				}
			}

		} catch (Exception e) {
			System.out.println("exception");
			log.error(e);
		}

	}
	/**
	 * @return the syncService
	 */
	public SyncService getSyncService() {
		return syncService;
	}
	/**
	 * @param syncService the syncService to set
	 */
	public void setSyncService(SyncService syncService) {
		this.syncService = syncService;
	}
	/**
	 * @return the pingUrl
	 */
	public String getPingUrl() {
		return pingUrl;
	}

	/**
	 * @param pingUrl the pingUrl to set
	 */
	public void setPingUrl(String pingUrl) {
		this.pingUrl = pingUrl;
	}

}
