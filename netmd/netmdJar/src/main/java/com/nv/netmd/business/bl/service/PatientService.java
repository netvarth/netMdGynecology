/**
 * PatientService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import java.util.List;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public interface PatientService {
	public ResponseDTO create(PatientDetail patient) throws ServiceException;

	public ResponseDTO update(PatientDetail patient) throws ServiceException;

	public PatientDetail view(int id) throws ServiceException;

	public PatientListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	
	public AutoSaveResponseDTO AutoSaveCase(CaseDTO caseDto) throws ServiceException;
	
	public ResponseDTO createCase(CaseDTO caseDto) throws ServiceException;

	public ResponseDTO updateCase(CaseDTO caseDto) throws ServiceException;

	public CaseListResponseDTO listCase() throws ServiceException;

	public CaseListResponseDTO listCaseByPatient(int patientId) throws ServiceException;

	public ResponseDTO createMedicalRecord(MedicalRecordDTO record) throws ServiceException;

	public ResponseDTO updateMedicalRecord(MedicalRecordDTO record) throws ServiceException;

	public CaseDTO viewCase(int id) throws ServiceException;

	public MedicalListResponseDTO listMedicalRecord(int patientId) throws ServiceException;

	public MedicalRecordDTO viewMedicalRecord(int id) throws ServiceException;
	
	public ResponseDTO deleteMedicalRecord(int id) throws ServiceException;
	
	public ResponseDTO deleteCase(int id) throws ServiceException;

	public MedicalListResponseDTO listPersonalVisit(int patientId) throws ServiceException;

	public CaseListResponseDTO listOfCase(FilterDTO filterDTO) throws ServiceException;

	public MedicalListResponseDTO listOfMedicalRecord(FilterDTO filterDTO) throws ServiceException;

	public MedicalListResponseDTO getMedicalRecordByCase(int patientId,
			int caseId) throws ServiceException;

	public PatientResponse patientFromYNW(PatientDetail patientDetail) throws ServiceException;

	public ResponseDTO delete(int id) throws ServiceException;
	
	public PatientListResponseDTO getPatients() throws ServiceException;
	
}
