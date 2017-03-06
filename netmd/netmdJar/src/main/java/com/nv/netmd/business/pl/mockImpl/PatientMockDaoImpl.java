/**
* PatientMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 24, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.CaseSyncResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
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
public class PatientMockDaoImpl implements PatientDao,FilterService{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#create(com.nv.netmd.rs.dto.PatientDetail)
	 */
	@Override
	public ResponseDTO create(PatientDetail patient) {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#update(com.nv.netmd.rs.dto.PatientDetail)
	 */
	@Override
	public ResponseDTO update(PatientDetail patient) {
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#view(int)
	 */
	@Override
	public PatientDetail view(int id) {
		// TODO Auto-generated method stub
		PatientDetail response=new PatientDetail();
		response.setId(id);
		response.setFirstName("sreeram");
//		response.setError(null);
		response.setSuccess(true);
	
		return response;
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#createCase(com.nv.netmd.rs.dto.CaseDTO)
	 */
	@Override
	public ResponseDTO createCase(CaseDTO caseDto) {
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#updateCase(com.nv.netmd.rs.dto.CaseDTO)
	 */
	@Override
	public ResponseDTO updateCase(CaseDTO caseDto) {
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#listCase()
	 */
	@Override
	public CaseListResponseDTO listCase() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#listCaseByPatient(int)
	 */
	@Override
	public CaseListResponseDTO listCaseByPatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#createMedicalRecord(com.nv.netmd.rs.dto.MedicalRecordDTO)
	 */
	@Override
	public ResponseDTO createMedicalRecord(MedicalRecordDTO record) {
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#updateMedicalRecord(com.nv.netmd.rs.dto.MedicalRecordDTO)
	 */
	@Override
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO record) {
		ResponseDTO response = new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#viewCase(int)
	 */
	@Override
	public CaseDTO viewCase(int id) {
		// TODO Auto-generated method stub
		CaseDTO response=new CaseDTO();
		response.setId(id);
		response.setCaseName("Sample");
		response.setError(null);
		response.setSuccess(true);
	
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#listMedicalRecord(int)
	 */
	@Override
	public MedicalListResponseDTO listMedicalRecord(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#viewMedicalRecord(int)
	 */
	@Override
	public MedicalRecordDTO viewMedicalRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#listPersonalVisit(int)
	 */
	@Override
	public MedicalListResponseDTO listPersonalVisit(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getMedicalRecordByCase(int, int)
	 */
	@Override
	public MedicalListResponseDTO getMedicalRecordByCase(int patientId,
			int caseId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getNewPatient()
	 */
	@Override
	public List<PatientDetail> getNewPatient() {
		// TODO Auto-generated method stub
		List<PatientDetail> patientList=new ArrayList<PatientDetail>();
		return patientList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getUpdatedPatient()
	 */
	@Override
	public List<PatientDetail> getUpdatedPatient() {
		// TODO Auto-generated method stub
		List<PatientDetail> patientList=new ArrayList<PatientDetail>();
		return patientList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getDeletedPatient()
	 */
	@Override
	public List<PatientDetail> getDeletedPatient() {
		// TODO Auto-generated method stub
		List<PatientDetail> patientList=new ArrayList<PatientDetail>();
		return patientList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#addPatientSyncResponse(com.nv.netmd.rs.dto.PatientResponse)
	 */
	@Override
	public void addPatientSyncResponse(PatientResponse patientResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#deletePatientSyncResponse(com.nv.netmd.rs.dto.PatientResponse)
	 */
	@Override
	public void deletePatientSyncResponse(PatientResponse patientResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#updatePatientSyncResponse(com.nv.netmd.rs.dto.PatientResponse)
	 */
	@Override
	public void updatePatientSyncResponse(PatientResponse patientResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#patientFromYNW(com.nv.netmd.rs.dto.PatientDetail)
	 */
	@Override
	public PatientResponse patientFromYNW(PatientDetail patient) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public <T> T list(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getPatients()
	 */
	@Override
	public PatientListResponseDTO getPatients() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CaseDTO> getNewCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CaseDTO> getUpdatedCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void CaseSyncResponse(CaseSyncResponseDTO CaseResponse) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateCaseSyncResponse(CaseSyncResponseDTO caseResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#AutoSaveCase(com.nv.netmd.rs.dto.CaseDTO)
	 */
	@Override
	public AutoSaveResponseDTO AutoSaveCase(CaseDTO caseDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO deleteMedicalRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO deleteCase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CaseDTO> getDeletedCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCaseSyncResponse(CaseSyncResponseDTO caseResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MedicalRecordDTO> getNewMedicalRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecordDTO> getUpdatedMedicalRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecordDTO> getDeletedMedicalRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMedicalSyncResponse(
			MedicalRecordSyncResponseDTO medResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMedicalSyncResponse(
			MedicalRecordSyncResponseDTO medResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseDTO getAnswerSetIdUsingCaseId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CaseListResponseDTO caselist(FilterDTO filterDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
