/**
 * ResultServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.ResultService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
public class ResultServiceTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void viewResult() {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		try {
			resultService.viewResult(4);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

//	@Test
//	public void listResult() {
//		ResultService resultService = (ResultService) applicationContext
//				.getBean("result.Service");
//		try {
//			resultService.listResult(1);
//		}
//
//		catch (ServiceException e) {
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}
//	}
	@Test
	public void list() throws ServiceException {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("doctorId");
		exp.setOperator("eq");
		exp.setValue("16");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		ResultListResponseDTO response = resultService.list(filter);
		System.out.println(response.getResultList().size());
		for ( ViewResultDTO pat : response.getResultList()) {
			System.out.println(pat.getResult());

		}
	}
	@Test
	public void listInalidvalue() throws ServiceException {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("doctorId");
		exp.setOperator("eqn");
		exp.setValue("16");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		ResultListResponseDTO response = resultService.list(filter);
		System.out.println(response.getResultList().size());
		for ( ViewResultDTO pat : response.getResultList()) {
			System.out.println(pat.getResult());

		}
	}
	@Test
	public void resultFromYNW() {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		try {
			RetrieveResultsResponseDTO result=new RetrieveResultsResponseDTO();
			result.setResultGlobalId(2);
			result.setPatientId(72);
			result.setResult("<tr><td><tbody>");
			result.setLabBranchName("trikk");
			result.setLabName("labs");
			result.setOrderDate("2012-08-22 10:12");
			resultService.resultFromYNW(result);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void resultFromYNWWithOutresult() {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		try {
			RetrieveResultsResponseDTO result=new RetrieveResultsResponseDTO();
			result.setResultGlobalId(2);
			result.setPatientId(72);
			result.setLabBranchName("trikk");
			result.setLabName("labs");
			result.setOrderDate("2012-08-22 10:12");
			resultService.resultFromYNW(result);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void resultFromYNWWithOutPatientId() {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		try {
			RetrieveResultsResponseDTO result=new RetrieveResultsResponseDTO();
			result.setResultGlobalId(2);
			result.setLabBranchName("trikk");
			result.setLabName("labs");
			result.setOrderDate("2012-08-22 10:12");
			result.setResult("sdjujhddsd");
			resultService.resultFromYNW(result);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void resultFromYNWWithOutGid() {
		ResultService resultService = (ResultService) applicationContext
				.getBean("result.Service");
		try {
			RetrieveResultsResponseDTO result=new RetrieveResultsResponseDTO();
			result.setLabBranchName("trikk");
			result.setLabName("labs");
			result.setOrderDate("2012-08-22 10:12");
			result.setPatientId(72);
			result.setResult("sdjujhddsd");
			resultService.resultFromYNW(result);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
