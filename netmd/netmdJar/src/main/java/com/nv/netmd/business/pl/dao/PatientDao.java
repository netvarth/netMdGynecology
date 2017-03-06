/**
 * PatientDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.dao;
import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.CaseSyncResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.MedicalRecordSyncResponseDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public interface PatientDao {
	
	public ResponseDTO create(PatientDetail patient)throws PersistenceException;
	public ResponseDTO update(PatientDetail patient)throws PersistenceException;
	public PatientDetail view(int id)throws PersistenceException;
	public ResponseDTO delete(int id)throws PersistenceException;
	public PatientListResponseDTO getPatients()throws PersistenceException;
	public AutoSaveResponseDTO AutoSaveCase(CaseDTO caseDto)throws PersistenceException;
	public ResponseDTO createCase(CaseDTO caseDto)throws PersistenceException;
	public ResponseDTO updateCase(CaseDTO caseDto)throws PersistenceException;
	public ResponseDTO deleteCase(int id)throws PersistenceException;
	public CaseDTO viewCase(int id)throws PersistenceException;
	public CaseListResponseDTO listCase()throws PersistenceException;
	public CaseListResponseDTO listCaseByPatient(int patientId)throws PersistenceException;
	public ResponseDTO createMedicalRecord(MedicalRecordDTO record)throws PersistenceException;
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO record)throws PersistenceException;
	public MedicalListResponseDTO listMedicalRecord(int patientId)throws PersistenceException;
	public MedicalRecordDTO viewMedicalRecord(int id)throws PersistenceException;
	public MedicalListResponseDTO listPersonalVisit(int patientId)throws PersistenceException;
	public MedicalListResponseDTO getMedicalRecordByCase(int patientId,int caseId)throws PersistenceException;
	public List<PatientDetail> getNewPatient()throws PersistenceException;	
	public List<PatientDetail> getUpdatedPatient()throws PersistenceException;
	public List<PatientDetail> getDeletedPatient()throws PersistenceException;
	public void addPatientSyncResponse(PatientResponse patientResponse)throws PersistenceException;
	public void deletePatientSyncResponse(PatientResponse patientResponse)throws PersistenceException;
	public void updatePatientSyncResponse(PatientResponse patientResponse)throws PersistenceException;
	public PatientResponse patientFromYNW(PatientDetail patient)throws PersistenceException;
	public List<CaseDTO> getNewCase()throws PersistenceException;
	public void CaseSyncResponse(CaseSyncResponseDTO CaseResponse)throws PersistenceException;
	public List<CaseDTO> getUpdatedCase()throws PersistenceException;
	public void updateCaseSyncResponse(CaseSyncResponseDTO caseResponse)throws PersistenceException;
	public List<CaseDTO> getDeletedCase()throws PersistenceException;
	public void deleteCaseSyncResponse(CaseSyncResponseDTO caseResponse)throws PersistenceException;
	public List<MedicalRecordDTO> getNewMedicalRecord()throws PersistenceException;
	public List<MedicalRecordDTO> getUpdatedMedicalRecord()throws PersistenceException;
	public List<MedicalRecordDTO> getDeletedMedicalRecord()throws PersistenceException;
	public ResponseDTO deleteMedicalRecord(int id)throws PersistenceException;
	public void addMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse)throws PersistenceException;
	public void updateMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse)throws PersistenceException;
	public void deleteMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse)throws PersistenceException;
	public ResponseDTO getAnswerSetIdUsingCaseId(int id)throws PersistenceException;
	public CaseListResponseDTO caselist(FilterDTO filterDTO)throws PersistenceException;
	
		
}
