/**
 * PatientTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jun 24, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import com.nv.netmd.business.bl.impl.PatientServiceImpl;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.validator.PatientValidator;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.business.pl.mockImpl.CaseMockList;
import com.nv.netmd.business.pl.mockImpl.MedicalMockList;
import com.nv.netmd.business.pl.mockImpl.PatientMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CaseStatusEnum;
import com.nv.netmd.pl.entity.MedicalRecordTypeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientMedicalHistoryDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class PatientTest {
	PatientValidator patientValidator;
	PatientDao patientDao;
	@Test
	public void createPatient() {
		ResponseDTO response = null;
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {	
			
			 response=patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
			
		}
		Assert.assertNotNull(response);
	}
	@Test (expected=ServiceException.class)	
	public void badPatientCreate() throws ServiceException{
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
	
		 ResponseDTO response=patientService.create(patientDto);
		 
	}
	@Test
	public void createPatientInvalidmob() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul@dff.com");
		patientDto.setPhone("04872340181");
		patientDto.setMobile("768");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul.com");
		patientDto.setPhone("04872340181");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidaction() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidPhone() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("0487234");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithoutFirstName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithInvalidActionName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithOutGender() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithoutEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		//patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWt() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtInvalidMob() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setMobile("87687b");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtInvalidPh() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setPhone("87687b");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWNoGlobalId() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtFisrtName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWwithOutEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		//patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWInvalidEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkurediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void view() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		try {
			patientService.view(6);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updatePatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void updatePatientInvalidmob() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul@dff.com");
		patientDto.setPhone("04872340181");
		patientDto.setMobile("768");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidaction() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("04872340181");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidPhone() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("0487234");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkurol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void updatePatientWithoutId() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
//		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutEmail() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
//		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutGender() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
//		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void filter() throws ServiceException {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		FilterService patientFilterService=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		patientService.setPatientFilterService(patientFilterService);
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("firstName");
		exp.setOperator("eq");
		exp.setValue("sree");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		PatientListResponseDTO response = patientService.list(filter);
		System.out.println(response.getPatient().size());
		for (PatientDetail pat : response.getPatient()) {
			System.out.println(pat.getFirstName());
		}

	}

	@Test
	public void caseFilter() throws ServiceException {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		FilterService caseFilterService=new CaseMockList();
		patientService.setCaseFilterService(caseFilterService);
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("patientId");
		exp.setOperator("eq");
		exp.setValue("1");
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("status");
		exp1.setOperator("eq");
		exp1.setValue("Open");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		exps.add(exp1);
		filter.setExp(exps);
		CaseListResponseDTO response = patientService.listOfCase(filter);
		System.out.println(response.getCaseList().size());
		for (CaseDTO ca : response.getCaseList()) {
			System.out.println(ca.getCaseName());

		}

	}

	@Test
	public void mediclRecordFilter() throws ServiceException {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		FilterService medicalFilterService=new MedicalMockList();
		patientService.setMedicalFilterService(medicalFilterService);
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("patientId");
		exp.setOperator("eq");
		exp.setValue("5");
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("type");
		exp1.setOperator("eq");
		exp1.setValue("Personal visit");
		ExpressionDTO exp2 = new ExpressionDTO();
		exp2.setName("caseId");
		exp2.setOperator("eq");
		exp2.setValue("6");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		exps.add(exp1);
		exps.add(exp2);
		filter.setExp(exps);
		MedicalListResponseDTO response = patientService
				.listOfMedicalRecord(filter);
		System.out.println(response.getMedicalList().size());
		for (MedicalRecordDTO ca : response.getMedicalList()) {
			System.out.println(ca.getMedicalRecord());

		}

	}

	@Test
	public void listCase() throws ServiceException {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseListResponseDTO response = new CaseListResponseDTO();

		response = patientService.listCase();
	}

	@Test
	public void listCaseByPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseListResponseDTO response = new CaseListResponseDTO();

		try {
			response = patientService.listCaseByPatient(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	
	@Test
	public void createCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO casedto = new CaseDTO();
		casedto.setCaseName("hand pain");
		casedto.setPatientId(1);
		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createCaseWithOutPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO casedto = new CaseDTO();
		casedto.setCaseName("hand pain");
		//casedto.setCaseStatus(CaseStatusEnum.OPEN.getDisplayName());
		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createCaseWithOutName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO casedto = new CaseDTO();
		//casedto.setCaseStatus(CaseStatusEnum.OPEN.getDisplayName());
		casedto.setPatientId(1);
		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createMedicalReport() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createMedicalReportWithOutMedicalRecord() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutDoct() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalNoType() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();

		try {
			response = patientService.viewCase(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setCaseName("update");
		//response.setCaseStatus("OPEN");
		//response.setLongDesc("hia  iahia");
		//response.setShortDesc("ss");
		response.setPatientId(1);
		response.setCaseName("jimbruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseNoActionName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();
		response.setId(2);
		//response.setActionName("update");
		//response.setCaseStatus("OPEN");
		//response.setLongDesc("hia  iahia");
		//response.setShortDesc("ss");
		response.setPatientId(1);
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOutPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setCaseName("update");
		//response.setCaseStatus("OPEN");
		//response.setLongDesc("hia  iahia");
		//response.setShortDesc("ss");
		
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOtName() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setCaseName("update");
		//response.setCaseStatus("OPEN");
		//response.setLongDesc("hia  iahia");
		//response.setShortDesc("ss");
		response.setPatientId(1);
		

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOutId() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		CaseDTO response = new CaseDTO();
		
		response.setCaseName("update");
		//response.setCaseStatus("OPEN");
		//response.setLongDesc("hia  iahia");
		//response.setShortDesc("ss");
		response.setPatientId(1);
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listMedicalRecord() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listMedicalRecord(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listVisit() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listPersonalVisit(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void viewMedicalRecord() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		MedicalRecordDTO response = new MedicalRecordDTO();
		try {
			response = patientService.viewMedicalRecord(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReport() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType("prescription");
		record.setMedicalRecord("sample medical record");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateMedicalReportWithOutId() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		MedicalRecordDTO record = new MedicalRecordDTO();
	
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType("prescription");
		record.setMedicalRecord("sample medical record");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateMedicalReportWithOutMedicalRecord() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutDoct() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setId(1);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalNoType() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		try {
			patientService.getMedicalRecordByCase(1, 1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycaseWithOutPatient() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		try {
			patientService.getMedicalRecordByCase(0, 1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycaseWithOutCase() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);
		try {
			patientService.getMedicalRecordByCase(1,0);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void delete() {
		PatientServiceImpl patientService = new PatientServiceImpl();
		patientValidator=new PatientValidator();
		patientDao=new PatientMockDaoImpl();
		patientService.setPatientValidator(patientValidator);
		patientService.setPatientDao(patientDao);

		try {
			patientService.delete(6);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
