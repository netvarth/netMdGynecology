/**

 * 
 * 
 * 
 * 
 * 
 * SynchronizationAgent.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Apr 23, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
/**
 * 
 */
package com.nv.netmd.sync;

import java.util.List;

import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.common.CoreJsonOperations;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.RemoteCallException;
import com.nv.netmd.rs.dto.CommonSyncResponseDTO;

/**
 * @author Nithesh Mohanan
 * 
 */
public class SynchronizationAgent implements Runnable {

	private Thread thread;

	List<SyncListner> syncListeners;
	private long syncIntervel;
	private String pingUrl;
	private QuestionnaireDao questionDao;

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run()  {

		SyncBag<?,CommonSyncResponseDTO> bag;
		boolean exception =false;
		while (true) {
			
			try {
				if(exception){
					questionDao.changeAnswerSetStatusOnErrorResponse();
                    exception=false; 
				}

				Thread.sleep(syncIntervel);

				if (CoreJsonOperations.isReachable(pingUrl)){


					for (SyncListner syncListner : syncListeners) {				
						bag = syncListner.getSyncData();
						bag.getInputClass();
						String url = bag.getSyncUrl();
						Object input = bag.getInput();
						if(input!=null){
							Class<CommonSyncResponseDTO> outputClass =  bag.getOutputClass();
							CommonSyncResponseDTO response = CoreJsonOperations.call(url, input, outputClass);
							syncListner.callback(response);
						}
					}
				}
				bag=null;
					
			} 
			catch (Exception e) {
				exception=true;

			}

		}

	}

	/**
	 * @return the syncListeners
	 */
	public List<SyncListner> getSyncListeners() {
		return syncListeners;
	}

	/**
	 * @param syncListeners the syncListeners to set
	 */
	public void setSyncListeners(
			List<SyncListner> syncListeners) {
		this.syncListeners = syncListeners;
	}

	/**
	 * @return the syncIntervel
	 */
	public long getSyncIntervel() {
		return syncIntervel;
	}

	/**
	 * @param syncIntervel the syncIntervel to set
	 */
	public void setSyncIntervel(long syncIntervel) {
		this.syncIntervel = syncIntervel;
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

	/**
	 * @return the questionDao
	 */
	public QuestionnaireDao getQuestionDao() {
		return questionDao;
	}

	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(QuestionnaireDao questionDao) {
		this.questionDao = questionDao;
	}


}