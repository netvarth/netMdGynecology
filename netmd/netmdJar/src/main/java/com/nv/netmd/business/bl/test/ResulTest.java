/**
* ResulTest.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 26, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nv.netmd.business.bl.impl.ResultServiceImpl;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.validator.ResultValidator;
import com.nv.netmd.business.pl.dao.ResultDao;
import com.nv.netmd.business.pl.mockImpl.ResultMockImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;

/**
 * 
 */
public class ResulTest {
	ResultDao resultDao;
	ResultValidator resultValidator;
	FilterService resultFilterService;
	
	@Test
	public void viewResult() {
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
		try {
			resultService.viewResult(4);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void list() throws ServiceException {
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultFilterService=new ResultMockImpl();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
		resultService.setResultFilterService(resultFilterService);
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
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultFilterService=new ResultMockImpl();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
		resultService.setResultFilterService(resultFilterService);
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
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
		try {
			RetrieveResultsResponseDTO result=new RetrieveResultsResponseDTO();
			result.setResultGlobalId(2);
			result.setPatientId(72);
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
	public void resultFromYNWWithOutresult() {
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
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
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
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
		ResultServiceImpl resultService = new ResultServiceImpl();
		resultDao=new ResultMockImpl();
		resultValidator=new ResultValidator();
		resultService.setResultDao(resultDao);
		resultService.setResultValidator(resultValidator);
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
