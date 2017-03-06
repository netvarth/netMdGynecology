/**
 * SyncServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.sync.bl.test;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.PassPhraseDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;
import com.nv.netmd.sync.bl.service.SyncService;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:E:/NVWorkspace/netMdPC/resource/context.xml",
		"file:E:/NVWorkspace/netMdPC/resource/testDataSource.xml", "file:E:/NVWorkspace/netMdPC/resource/netmd-context.xml" })
public class SyncServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

//	@Test
//	public void getMacStatus() {
//		SyncService syncService=(SyncService)applicationContext.getBean("sync.service");
//		NetMdResponseDTO response=new NetMdResponseDTO();
//		PassPhraseDTO pass=new PassPhraseDTO();
//		pass.setPassPhrase("1gqRLiIO3L9m1t7fjJvDKg==");
//		try {
//			response=syncService.getMacStatus(pass);
//		}
//
//		catch (ServiceException e) {
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}
//	}
	@Test
	public void activateNetMd() {
		SyncService syncService=(SyncService)applicationContext.getBean("sync.service");
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		PassPhraseDTO pass=new PassPhraseDTO();
		pass.setPassPhrase("PYHZwTx5lX8AvIBGuFyNqw==");
		try {
			response=syncService.activateNetMd(pass);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void syncService() {


		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				SyncService syncService=(SyncService)applicationContext.getBean("sync.service");

				System.out.println("inside service ");
				SyncResponseDTO syncResponseDTO=new SyncResponseDTO();

				try {
					System.out.println("inside try catch ");
					syncResponseDTO=syncService.getSyncData();

				}

				catch (ServiceException e) {
					System.out.println(e.isDisplayErrMsg());
					System.out.println(e.getError());
					System.out.println(e.getParamList());
				} 
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	@Test
	public void syncData() {
		SyncService syncService=(SyncService)applicationContext.getBean("sync.service");

		System.out.println("inside service ");
		SyncResponseDTO syncResponseDTO=new SyncResponseDTO();

		try {
			System.out.println("inside try catch ");
			syncResponseDTO=syncService.getSyncData();

		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		} 
	}


}
