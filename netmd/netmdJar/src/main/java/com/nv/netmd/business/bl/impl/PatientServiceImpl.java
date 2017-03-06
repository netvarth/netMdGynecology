/**
 * PatientServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.business.bl.service.QuestionnaireService;
import com.nv.netmd.business.bl.validator.PatientValidator;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class PatientServiceImpl implements PatientService {

	private PatientDao patientDao;
	private PatientValidator patientValidator;
	private FilterService patientFilterService;
	private FilterService caseFilterService;
	private FilterService medicalFilterService;
	private QuestionnaireService questionnaireService;

	public PatientDao getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	/**
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO create(PatientDetail patient) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		// PatientDao patientDao = new PatientDaoImpl();
		ErrorDTO error = patientValidator.validateCreatePatient(patient);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.create(patient);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * 
	 * @param patient
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(PatientDetail patient) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = patientValidator.validateUpdatePatient(patient);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.update(patient);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * patient view
	 * 
	 * @param id
	 * @return PatientDTO
	 */
	@Override
	public PatientDetail view(int id)throws ServiceException {
		// TODO Auto-generated method stub
		PatientDetail response = new PatientDetail();
		try {
			response = patientDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the patientValidator
	 */
	public PatientValidator getPatientValidator() {
		return patientValidator;
	}

	/**
	 * @param patientValidator
	 *            the patientValidator to set
	 */
	public void setPatientValidator(PatientValidator patientValidator) {
		this.patientValidator = patientValidator;
	}

	/**
	 * patient list
	 * 
	 * @param filterDTO
	 * @return PatientListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public PatientListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		PatientListResponseDTO patientList = new PatientListResponseDTO();
		ErrorDTO error = patientFilterService.validate(filterDTO);
		if (error != null) {
			patientList.setError(error);
			patientList.setSuccess(false);
			return patientList;
		}
		patientList = patientFilterService.list(filterDTO);
		return patientList;
	}

	/**
	 * case list
	 * 
	 * @param filterDTO
	 * @return CaseListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public CaseListResponseDTO listOfCase(FilterDTO filterDTO) throws ServiceException {
		CaseListResponseDTO caseList = new CaseListResponseDTO();
		ErrorDTO error = caseFilterService.validate(filterDTO);
		if (error != null) {
			caseList.setError(error);
			caseList.setSuccess(false);
			return caseList;
		}
		caseList = caseFilterService.list(filterDTO);
		return caseList;
	}

	/**
	 * list the medical record filter
	 ** 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public MedicalListResponseDTO listOfMedicalRecord(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		MedicalListResponseDTO medicalList = new MedicalListResponseDTO();
		ErrorDTO error = medicalFilterService.validate(filterDTO);
		if (error != null) {
			medicalList.setError(error);
			medicalList.setSuccess(false);
			return medicalList;
		}
		medicalList = medicalFilterService.list(filterDTO);
		return medicalList;
	}

	/**
	 * create a case
	 * 
	 * @param caseDto
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO createCase(CaseDTO caseDto) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		patientValidator.validateCase(caseDto);
		try {
			response = patientDao.createCase(caseDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(caseDto.getQuestionAnswerDTO()!=null && response.getId() != 0){
			QuestionAnswerDTO questionAnswer = new QuestionAnswerDTO();
			questionAnswer.setCaseId(response.getId());
			questionAnswer.setDepartmentId(caseDto.getDepartmentId());
			questionAnswer.setDepartment(caseDto.getDepartmentName());
			questionAnswer.setBranchId(caseDto.getBranchId());
			questionAnswer.setAnswerDTO(caseDto.getQuestionAnswerDTO()
					.getAnswerDTO());
			response = questionnaireService.create(questionAnswer);
		}
		response.setSuccess(true);
		return response;

	}

	/**
	 * @return the questionnaireService
	 */
	public QuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	/**
	 * @param questionnaireService
	 *            the questionnaireService to set
	 */
	public void setQuestionnaireService(
			QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	/**
	 * list all the cases
	 * 
	 * @return CaseListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	public CaseListResponseDTO listCase() throws ServiceException {
		// TODO Auto-generated method stub
		CaseListResponseDTO response = new CaseListResponseDTO();

		try {
			response = patientDao.listCase();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);
		}
		return response;

	}

	/**
	 * create a medical record for a patient
	 * 
	 * @param record
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO createMedicalRecord(MedicalRecordDTO record) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = patientValidator.validateCreateMedicalRecord(record);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.createMedicalRecord(record);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * list a single patients case
	 * 
	 * @param patientId
	 * @return CaseListResponseDTO
	 */
	@Override
	public CaseListResponseDTO listCaseByPatient(int patientId) throws ServiceException {
		// TODO Auto-generated method stub
		CaseListResponseDTO response = new CaseListResponseDTO();
		ErrorDTO error = patientValidator.validateListCaseByPatient(patientId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.listCaseByPatient(patientId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * view a case by giving id
	 * 
	 * @param id
	 * @return CaseDTO
	 */
	@Override
	public CaseDTO viewCase(int id)throws ServiceException {
		// TODO Auto-generated method stub
		CaseDTO response = new CaseDTO();
		try {
			response = patientDao.viewCase(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if (response.getDepartmentName().trim().equals(Constants.OBSTETRICS)) {
			response = questionnaireService.view(id, response);
		}
		return response;
	}

	/**
	 * update a created case
	 * 
	 * @param caseDto
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateCase(CaseDTO caseDto) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		patientValidator.validateCase(caseDto);
		try {
			response = patientDao.updateCase(caseDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if (caseDto.getDepartmentName().trim().equals(Constants.OBSTETRICS)&& response.getId() != 0) {
	//if(caseDto.getQuestionAnswerDTO()!=null && response.getId() != 0){
		QuestionAnswerDTO questionAnswer = new QuestionAnswerDTO();
			questionAnswer.setCaseId(response.getId());
			questionAnswer.setDepartmentId(caseDto.getDepartmentId());
			questionAnswer.setDepartment(caseDto.getDepartmentName());
			questionAnswer.setBranchId(caseDto.getBranchId());
			questionAnswer.setAnswerDTO(caseDto.getQuestionAnswerDTO()
					.getAnswerDTO());
			response = questionnaireService.update(questionAnswer);
		}
		response.setSuccess(true);
		return response;

	}

	/**
	 * list the medical record of a patient
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 */
	@Override
	public MedicalListResponseDTO listMedicalRecord(int patientId) throws ServiceException {
		// TODO Auto-generated method stub
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		ErrorDTO error = patientValidator.validateListMedicalRecord(patientId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.listMedicalRecord(patientId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * view a medical record by giving id
	 * 
	 * @param id
	 * @return MedicalRecordDTO
	 */
	@Override
	public MedicalRecordDTO viewMedicalRecord(int id) throws ServiceException {
		// TODO Auto-generated method stub
		MedicalRecordDTO response = new MedicalRecordDTO();
		try {
			response = patientDao.viewMedicalRecord(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * update a medical record for a patient
	 * 
	 * @param record
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO record) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = patientValidator.validateUpdateMedicalRecord(record);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.updateMedicalRecord(record);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 */
	@Override
	public MedicalListResponseDTO listPersonalVisit(int patientId) throws ServiceException {
		// TODO Auto-generated method stub
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		ErrorDTO error = patientValidator.validateListMedicalRecord(patientId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.listPersonalVisit(patientId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * get medical record by giving patient and case
	 * 
	 * @param patient
	 *            id,case id
	 * @return MedicalListResponseDTO
	 */
	@Override
	public MedicalListResponseDTO getMedicalRecordByCase(int patientId,
			int caseId) throws ServiceException {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		ErrorDTO error = patientValidator.validateRecordByCase(patientId,
				caseId);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.getMedicalRecordByCase(patientId, caseId);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * @param patientFilterService
	 *            the patientFilterService to set
	 */
	public void setPatientFilterService(FilterService patientFilterService) {
		this.patientFilterService = patientFilterService;
	}

	/**
	 * @param caseFilterService
	 *            the caseFilterService to set
	 */
	public void setCaseFilterService(FilterService caseFilterService) {
		this.caseFilterService = caseFilterService;
	}

	/**
	 * @param medicalFilterService
	 *            the medicalFilterService to set
	 */
	public void setMedicalFilterService(FilterService medicalFilterService) {
		this.medicalFilterService = medicalFilterService;
	}

	/**
	 * @return the medicalFilterService
	 */
	public FilterService getMedicalFilterService() {
		return medicalFilterService;
	}

	/**
	 * get patient from YNW
	 * 
	 * @param patients
	 * @return PatientResponse
	 * @throws ServiceException 
	 */
	@Override
	public PatientResponse patientFromYNW(PatientDetail patient) throws ServiceException {
		// TODO Auto-generated method stub
		PatientResponse response = new PatientResponse();
		ErrorDTO error = patientValidator.validatePatientFromYNW(patient);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = patientDao.patientFromYNW(patient);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * delete or inactive the patient when inactive also inactive corresponding
	 * appointments
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = patientDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	@Override
	public PatientListResponseDTO getPatients() throws ServiceException{
		PatientListResponseDTO response;
		try {
			response = patientDao.getPatients();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.netmd.business.bl.service.PatientService#AutoSaveCase(com.nv.netmd
	 * .rs.dto.CaseDTO)
	 */
	@Override
	public AutoSaveResponseDTO AutoSaveCase(CaseDTO caseDto) throws ServiceException {
		AutoSaveResponseDTO response = new AutoSaveResponseDTO();
		patientValidator.validateCase(caseDto);
		try {
			response = patientDao.AutoSaveCase(caseDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}

		if (caseDto.getDepartmentName().trim().equals(Constants.OBSTETRICS)
				&& response.getCaseId() != 0) {
			AutoSaveResponseDTO responseOne = new AutoSaveResponseDTO();
			QuestionAnswerDTO questionAnswer = new QuestionAnswerDTO();
			questionAnswer.setCaseId(response.getCaseId());
			questionAnswer.setDepartmentId(caseDto.getDepartmentId());
			questionAnswer.setDepartment(caseDto.getDepartmentName());
			questionAnswer.setBranchId(caseDto.getBranchId());
			questionAnswer.setAnswerSetId(caseDto.getQuestionAnswerDTO()
					.getAnswerSetId());
			questionAnswer.setAnswerDTO(caseDto.getQuestionAnswerDTO()
					.getAnswerDTO());
			responseOne = questionnaireService.autoSave(questionAnswer);
			response.setAnsSetId(responseOne.getAnsSetId());
			response.setSuccess(true);
		}
		return response;
	}
	

	
	@Override
	public ResponseDTO deleteMedicalRecord(int id) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = patientDao.deleteMedicalRecord(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	@Override
	public ResponseDTO deleteCase(int id)throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = patientDao.deleteCase(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the patientFilterService
	 */
	public FilterService getPatientFilterService() {
		return patientFilterService;
	}

	/**
	 * @return the caseFilterService
	 */
	public FilterService getCaseFilterService() {
		return caseFilterService;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.netmd.business.bl.service.PatientService#getPatients()
	 */

}
