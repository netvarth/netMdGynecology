/**
 * ResultDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.netmd.business.pl.dao.ResultDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.ResultTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;


/**
 * 
 */
public class ResultDaoImpl extends GenericDaoHibernateImpl implements ResultDao {
	@PersistenceContext()
	private EntityManager em;

	/**
	 * view a single result
	 * @param id
	 */
	@Override
	@Transactional(readOnly = false)
	public ViewResultDTO viewResult(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		ViewResultDTO response = new ViewResultDTO();
		ResultTbl resultTbl = (ResultTbl) getById(ResultTbl.class, id);
		if (resultTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ResultNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,resultTbl.getId());
		if(patientTbl!=null){
			response.setPatientId(patientTbl.getId());
			response.setPatientName(patientTbl.getFirstName());
		}
		response.setId(resultTbl.getId());
		response.setResult(resultTbl.getResult());
		response.setLabBranchName(resultTbl.getLabBranchName());
		response.setLabName(resultTbl.getLabName());

		if(resultTbl.getOrderDate()!=null){
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
			response.setOrderDate(df.format(resultTbl.getOrderDate()));
		}
		response.setOrderUid(resultTbl.getOrderUid());
		response.setError(null);
		response.setSuccess(true);
		return response;

	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * list of results for a patient
	 * @param patientId
	 * @return ResultListResponseDTO
	 * @throws PersistenceException 
	 */
	//	@Override
	//	@Transactional(readOnly = false)
	//	public ResultListResponseDTO listResult(int patientId) {
	//		// TODO Auto-generated method stub
	//		ResultListResponseDTO response = new ResultListResponseDTO();
	//		List<ViewResultDTO> resultList = new ArrayList<ViewResultDTO>();
	//		List<ResultTbl> resultListTbl = getByPatientId(patientId);
	//		if (!resultListTbl.isEmpty()) {
	//			for (ResultTbl resultTbl : resultListTbl) {
	//				ViewResultDTO viewResult = new ViewResultDTO();
	//				viewResult.setId(resultTbl.getId());
	//				if (resultTbl.getPatientTbl() != null){
	//					viewResult.setPatientId(resultTbl.getPatientTbl().getId());
	//					viewResult.setPatientName(resultTbl.getPatientTbl().getFirstName());
	//
	//				}
	//				if (resultTbl.getDoctorTbl() != null)
	//					viewResult.setDoctorId(resultTbl.getDoctorTbl().getId());
	//				viewResult.setResult(resultTbl.getResult());
	//				viewResult.setLabBranchName(resultTbl.getLabBranchName());
	//				viewResult.setLabName(resultTbl.getLabName());
	//				viewResult.setOrderUid(resultTbl.getOrderUid());
	//				if(resultTbl.getOrderDate()!=null){
	//					DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
	//					viewResult.setOrderDate(df.format(resultTbl.getOrderDate()));
	//				}
	//				resultList.add(viewResult);
	//			}
	//			response.setResultList(resultList);
	//		}
	//		response.setError(null);
	//		response.setSuccess(true);
	//		return response;
	//	}

	public List<ResultTbl> getByPatientId(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_RESULT_BY_PATIENTID);
		query.setParameter("param1", patientId);
		return executeQuery(ResultTbl.class, query);
	}

	/**
	 * Result from YNW
	 * @param result
	 * @return ViewResultResponeDTO
	 * @throws PersistenceException 
	 */
	@Override	
	@Transactional(readOnly=false)
	public RetrieveResultsResponseDTO resultFromYNW(RetrieveResultsResponseDTO result) throws PersistenceException {
		// TODO Auto-generated method stub
		RetrieveResultsResponseDTO resultResponse=new RetrieveResultsResponseDTO();
		ResultTbl resultTbl=(ResultTbl)getResultByGlobalId(result.getResultGlobalId());
		if(resultTbl==null){
			ResponseDTO response=createResultFromYNW(result);
			resultResponse.setSuccess(response.isSuccess());
			resultResponse.setError(response.getError());
			return resultResponse;
		}



		ResponseDTO response=updateResultFromYNW(result);
		resultResponse.setSuccess(response.isSuccess());
		resultResponse.setError(response.getError());
		return resultResponse;
	}
	private ResultTbl getResultByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_RESULT_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(ResultTbl.class, query);
	}
	/**
	 * create result
	 * @param result
	 * @return ResponseDTO
	 */
	private ResponseDTO createResultFromYNW(RetrieveResultsResponseDTO result) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		ResultTbl resultTbl=new ResultTbl();
		resultTbl.setGlobalId(result.getResultGlobalId());
		ObjectMapper maper = new ObjectMapper();	
		try {
			String jsonRequest = maper.writeValueAsString(result.getResult());
			resultTbl.setResult(jsonRequest);
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resultTbl.setResult(result.getResult());
		PatientTbl patientTbl=(PatientTbl)getPatientByGlobalId(result.getPatientId());
		if(patientTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(result.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		resultTbl.setPatientTbl(patientTbl);
		resultTbl.setLabBranchName(result.getLabBranchName());
		resultTbl.setLabName(result.getLabName());
		resultTbl.setOrderUid(result.getOrderUid());

		if(result.getOrderDate()!=null){
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
			Date date=new Date();
			try {
				date = df.parse(result.getOrderDate());
				resultTbl.setOrderDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		resultTbl.setDate(new Date());
		save(resultTbl);
		response.setId(resultTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/**
	 * update result
	 * @param result
	 * @return ResponseDTO
	 */
	private ResponseDTO updateResultFromYNW(RetrieveResultsResponseDTO result) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		ResultTbl resultTbl=(ResultTbl)getResultByGlobalId(result.getResultGlobalId());
		resultTbl.setResult(result.getResult());
		PatientTbl patientTbl=(PatientTbl)getPatientByGlobalId(result.getPatientId());
		if(patientTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(result.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		resultTbl.setPatientTbl(patientTbl);		
		resultTbl.setLabBranchName(result.getLabBranchName());
		resultTbl.setLabName(result.getLabName());
		resultTbl.setOrderUid(result.getOrderUid());

		if(result.getOrderDate()!=null){
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
			Date date=new Date();
			try {
				date = df.parse(result.getOrderDate());
				resultTbl.setOrderDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		resultTbl.setDate(new Date());
		update(resultTbl);
		response.setId(resultTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private PatientTbl getPatientByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(PatientTbl.class, query);
	}
}
