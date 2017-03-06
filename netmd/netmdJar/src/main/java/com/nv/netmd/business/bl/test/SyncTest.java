/**
* SyncTest.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 27, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.test;


import org.junit.Test;

import com.nv.netmd.business.bl.impl.AppointmentServiceImpl;
import com.nv.netmd.business.bl.impl.DoctorServiceImpl;
import com.nv.netmd.business.bl.impl.PatientServiceImpl;
import com.nv.netmd.business.bl.impl.ResultServiceImpl;
import com.nv.netmd.business.bl.impl.ScheduleServiceImpl;
import com.nv.netmd.business.bl.service.AppointmentService;
import com.nv.netmd.business.bl.service.DoctorService;
import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.business.bl.service.ResultService;
import com.nv.netmd.business.bl.service.ScheduleService;
import com.nv.netmd.business.bl.validator.DoctorValidator;
import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.business.pl.dao.DoctorDao;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.business.pl.mockImpl.AppointmentMockDaoImpl;
import com.nv.netmd.business.pl.mockImpl.DoctorMockDaoImpl;
import com.nv.netmd.business.pl.mockImpl.PatientMockDaoImpl;
import com.nv.netmd.business.pl.mockImpl.ScheduleMockDaoImpl;
import com.nv.netmd.business.pl.mockImpl.SyncMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.PassPhraseDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;
import com.nv.netmd.sync.bl.impl.SyncServiceImpl;
import com.nv.netmd.sync.bl.validator.SyncValidator;
import com.nv.netmd.sync.pl.dao.SyncDao;

/**
 * 
 */
public class SyncTest {
	SyncDao syncDao;
	DoctorDao doctorDao;
	SyncValidator syncValidator;
	PatientDao patientDao;
	ScheduleDao scheduleDao;
	AppointmentDao appointmentDao;
	DoctorService doctorService;
	AppointmentService appointmentService;
	PatientService patientService;
	ScheduleService scheduleService;
	ResultService resultService;
	
	@Test	
	public void activateNetMd() {
		SyncServiceImpl syncService=new SyncServiceImpl();
		syncDao=new SyncMockDaoImpl();
		syncValidator=new SyncValidator();
		syncService.setSyncDao(syncDao);
		syncService.setSyncValidator(syncValidator);
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
	public void activateNetMdNoPasPhrase() {
		SyncServiceImpl syncService=new SyncServiceImpl();
		syncDao=new SyncMockDaoImpl();
		syncValidator=new SyncValidator();
		
		syncService.setSyncDao(syncDao);
		syncService.setSyncValidator(syncValidator);
		
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		PassPhraseDTO pass=new PassPhraseDTO();
		//pass.setPassPhrase("PYHZwTx5lX8AvIBGuFyNqw==");
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
	public void syncData() {
		SyncServiceImpl syncService=new SyncServiceImpl();
		
		syncDao=new SyncMockDaoImpl();
		syncValidator=new SyncValidator();
		doctorDao=new DoctorMockDaoImpl();
		patientDao=new PatientMockDaoImpl();
		scheduleDao=new ScheduleMockDaoImpl();
		appointmentDao=new AppointmentMockDaoImpl();
		doctorService=new DoctorServiceImpl();
		appointmentService=new AppointmentServiceImpl();
		patientService=new PatientServiceImpl();
		scheduleService=new ScheduleServiceImpl();
				
//		DoctorValidator validator=new DoctorValidator();
		resultService=new ResultServiceImpl();
		syncService.setSyncDao(syncDao);
		syncService.setSyncValidator(syncValidator);
		syncService.setAppointmentDao(appointmentDao);
		syncService.setAppointmentService(appointmentService);
		syncService.setDoctorDao(doctorDao);
		syncService.setDoctorService(doctorService);
		syncService.setPatientDao(patientDao);
		syncService.setPatientService(patientService);
		syncService.setResultService(resultService);
		syncService.setScheduleDao(scheduleDao);
		syncService.setScheduleService(scheduleService);
		
		
	
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
