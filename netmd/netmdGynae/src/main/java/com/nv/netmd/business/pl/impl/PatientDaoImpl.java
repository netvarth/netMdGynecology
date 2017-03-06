/**
 * PatientDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionEnum;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.AnswerTbl;
import com.nv.netmd.pl.entity.BloodGroupEnum;
import com.nv.netmd.pl.entity.CaseAnswerSetTbl;
import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.pl.entity.DepartmentQuestionnaireTbl;
import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.EducationEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.GenderEnum;
import com.nv.netmd.pl.entity.MedicalRecordTbl;
import com.nv.netmd.pl.entity.MedicalRecordTypeEnum;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.PatientAppointmentTbl;
import com.nv.netmd.pl.entity.PatientMedicalHistoryTbl;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.RhEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncStatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.CaseSyncDTO;
import com.nv.netmd.rs.dto.CaseSyncResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.MedicalRecordSyncResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientMedicalHistoryDTO;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;

/**
 * 
 */
public class PatientDaoImpl extends GenericDaoHibernateImpl implements PatientDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext()
	private EntityManager em;
	public static final Log log=LogFactory.getLog(PatientDaoImpl.class);
	private QuestionnaireDao questionDao;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	/**
	 * create patient
	 * 
	 * @param PatientDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO create(PatientDetail patient) throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		boolean patientStatus=false;
		PatientTbl patientTbl = null;
		
		if(patient.getEmail()!=""){
		 patientTbl=(PatientTbl)getPatientByEmail(patient.getEmail(),patient.getFirstName());
		if(patientTbl!=null){
			if(patientTbl.getStatus().equals(StatusEnum.Inactive))
			{
				patientTbl.setStatus(StatusEnum.Active);
				patientStatus=true;
				update(patientTbl);

			}else{
				PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientWithEmailExist);
			se.setDisplayErrMsg(true);
			throw se;
			}
			}
		}
		
		if(!patientStatus)
			patientTbl = new PatientTbl();
		else
			 patientTbl=(PatientTbl)getPatientByEmail(patient.getEmail(),patient.getFirstName());
	
		patientTbl.setFirstName(patient.getFirstName());
		patientTbl.setLastName(patient.getLastName());
		patientTbl.setAge(patient.getAge());
		patientTbl.setAddress(patient.getAddress());
		patientTbl.setEmail(patient.getEmail());
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		patientTbl.setGender(gender);
		patientTbl.setPhone(patient.getPhone());
		patientTbl.setMobile(patient.getMobile());
		patientTbl.setAilment(patient.getAilment());
		patientTbl.setDiagnosis(patient.getDiagnosis());
		patientTbl.setAllergies(patient.getAllergies());
		if(patient.getBloodGroup()!=null){
			BloodGroupEnum bloodgrpEnum=BloodGroupEnum.getEnum(patient.getBloodGroup());
			patientTbl.setBloodGroup(bloodgrpEnum.getDisplayName());
		}
		if(patient.getRh()!=null){
			RhEnum rhenum=RhEnum.getEnum(patient.getRh());
			patientTbl.setRh(rhenum.getDisplayName());
		}
		if(patient.getEducation()!=null){
		EducationEnum education=EducationEnum.getEnum(patient.getEducation());
		patientTbl.setEducation(education.getDisplayName());
		}
		patientTbl.setChronicDisease(patient.getChronicDisease());
		patientTbl.setEmergencyNo(patient.getEmergencyNo());
		patientTbl.setFamilyHistory(patient.getFamilyHistory());
		patientTbl.setHeight(patient.getHeight());
		patientTbl.setWeight(patient.getWeight());
		patientTbl.setStatus(StatusEnum.Active);
		Date currentTime=new Date();
		patientTbl.setCreatedTime(currentTime);
		patientTbl.setUpdatedTime(currentTime);
		
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date date = null;
		try {
			if(patient.getDob()!=null){
				
				date = df.parse(patient.getDob().trim());
				
			}
			
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		patientTbl.setDob(date);
		
		if(!patientStatus)	
			save(patientTbl);
		else
			update(patientTbl);
		
		/* ____ temporarly commeneted ____  */
		
//		if (!patient.getMedicalHistory().isEmpty()) {
//			PatientMedicalHistoryTbl patientMedicalHistoryTbl = new PatientMedicalHistoryTbl();
//			for (PatientMedicalHistoryDTO patientMedicalHistoryDto : patient
//					.getMedicalHistory()) {
//
//				patientMedicalHistoryTbl
//				.setDiagonisedAge(patientMedicalHistoryDto
//						.getDiagonisedAge());
//				patientMedicalHistoryTbl.setIsCured(patientMedicalHistoryDto
//						.getIsCured());
//				patientMedicalHistoryTbl
//				.setMedicalIssue(patientMedicalHistoryDto
//						.getMedicalIssue());
//				patientMedicalHistoryTbl.setMedication(patientMedicalHistoryDto
//						.getMedication());
//				patientMedicalHistoryTbl.setPatientTbl(patientTbl);
//				patientMedicalHistoryTbl.setSurgery(patientMedicalHistoryDto
//						.getSurgery());
//				patientMedicalHistoryTbl.setTenure(patientMedicalHistoryDto
//						.getTenure());
//				patientMedicalHistoryTbl.setTreatment(patientMedicalHistoryDto
//						.getTreatment());
//			}
//			save(patientMedicalHistoryTbl);
//		}
		
		response.setError(null);
		response.setSuccess(true);
		response.setId(patientTbl.getId());
		return response;
	}
	private String capitalizeFirstLetter(String original){		
	    if(original==null || original=="" || original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
		
	}

	/**
	 * update patient
	 * 
	 * @param PatientDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO update(PatientDetail patient) throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class,
				patient.getId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(patient
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		patientTbl.setFirstName(patient.getFirstName());
		//String lastName=capitalizeFirstLetter(patient.getLastName());
		patientTbl.setLastName(patient.getLastName());
		patientTbl.setAge(patient.getAge());
		patientTbl.setAddress(patient.getAddress());
		patientTbl.setMobile(patient.getMobile());
		patientTbl.setPhone(patient.getPhone());
		patientTbl.setEmail(patient.getEmail());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date date = null;
		try {
			if(patient.getDob()!=null)
				date = df.parse(patient.getDob().trim());
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		patientTbl.setDob(date);
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		patientTbl.setGender(gender);
		patientTbl.setDiagnosis(patient.getDiagnosis());
		patientTbl.setAilment(patient.getAilment());
		patientTbl.setAllergies(patient.getAllergies());
		if(patient.getBloodGroup()!=null){
			BloodGroupEnum bloodgrpEnum=BloodGroupEnum.getEnum(patient.getBloodGroup());
			patientTbl.setBloodGroup(bloodgrpEnum.getDisplayName());
		}
		if(patient.getRh()!=null){
			RhEnum rhenum=RhEnum.getEnum(patient.getRh());
			patientTbl.setRh(rhenum.getDisplayName());
		}
		if(patient.getEducation()!=null){
		EducationEnum education=EducationEnum.getEnum(patient.getEducation());
		patientTbl.setEducation(education.getDisplayName());
		}
		patientTbl.setChronicDisease(patient.getChronicDisease());
		patientTbl.setEmergencyNo(patient.getEmergencyNo());
		patientTbl.setFamilyHistory(patient.getFamilyHistory());
		patientTbl.setHeight(patient.getHeight());
		patientTbl.setWeight(patient.getWeight());
		patientTbl.setStatus(StatusEnum.Active);
		patientTbl.setUpdatedTime(new Date());
		if (patient.getMedicalHistory() != null) {
			// medical history is not null
			List<PatientMedicalHistoryTbl> historyDelete = new ArrayList<PatientMedicalHistoryTbl>();
			List<PatientMedicalHistoryTbl> historyCreate = new ArrayList<PatientMedicalHistoryTbl>();
			List<PatientMedicalHistoryTbl> historyUpdate = new ArrayList<PatientMedicalHistoryTbl>();
			// List<PatientMedicalHistoryTbl>
			// medicalHistoryList=(ArrayList<PatientMedicalHistoryTbl>)getHistoryByPatientId(patient.getId());
			for (PatientMedicalHistoryDTO patientMedicalHistoryDTO : patient
					.getMedicalHistory()) {
				if (patientMedicalHistoryDTO.getActionName().equals(
						ActionNameEnum.ADD.getDisplayName())) {
					PatientMedicalHistoryTbl patientMedicalHistoryTbl = new PatientMedicalHistoryTbl();
					patientMedicalHistoryTbl
					.setDiagonisedAge(patientMedicalHistoryDTO
							.getDiagonisedAge());
					patientMedicalHistoryTbl
					.setIsCured(patientMedicalHistoryDTO.getIsCured());
					patientMedicalHistoryTbl
					.setMedicalIssue(patientMedicalHistoryDTO
							.getMedicalIssue());
					patientMedicalHistoryTbl
					.setMedication(patientMedicalHistoryDTO
							.getMedication());
					patientMedicalHistoryTbl.setPatientTbl(patientTbl);
					patientMedicalHistoryTbl
					.setSurgery(patientMedicalHistoryDTO.getSurgery());
					patientMedicalHistoryTbl.setTenure(patientMedicalHistoryDTO
							.getTenure());
					patientMedicalHistoryTbl
					.setTreatment(patientMedicalHistoryDTO
							.getTreatment());
					historyCreate.add(patientMedicalHistoryTbl);
				}
				if (patientMedicalHistoryDTO.getActionName().equals(
						ActionNameEnum.DELETE.getDisplayName())) {
					PatientMedicalHistoryTbl historyTbl = (PatientMedicalHistoryTbl) getPatientMedicalHistory(
							patientMedicalHistoryDTO.getId(), patient.getId());
					if (historyTbl == null) {
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.MedicalHistoryNotExist);
						se.setDisplayErrMsg(true);
						throw se;
					}
					historyDelete.add(historyTbl);
				}
				if (patientMedicalHistoryDTO.getActionName().equals(
						ActionNameEnum.UPDATE.getDisplayName())) {
					PatientMedicalHistoryTbl historyTbl = (PatientMedicalHistoryTbl) getPatientMedicalHistory(
							patientMedicalHistoryDTO.getId(), patient.getId());
					if (historyTbl == null) {
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.MedicalHistoryNotExist);
						se.setDisplayErrMsg(true);
						throw se;
					}
					historyTbl.setDiagonisedAge(patientMedicalHistoryDTO
							.getDiagonisedAge());
					historyTbl
					.setIsCured(patientMedicalHistoryDTO.getIsCured());
					historyTbl.setMedicalIssue(patientMedicalHistoryDTO
							.getMedicalIssue());
					historyTbl.setMedication(patientMedicalHistoryDTO
							.getMedication());
					historyTbl
					.setSurgery(patientMedicalHistoryDTO.getSurgery());
					historyTbl.setTenure(patientMedicalHistoryDTO.getTenure());
					historyTbl.setTreatment(patientMedicalHistoryDTO
							.getTreatment());
					historyUpdate.add(historyTbl);
				}
				if (!historyCreate.isEmpty()) {
					for (PatientMedicalHistoryTbl patientMedicalHistoryTbl : historyCreate) {
						save(patientMedicalHistoryTbl);
					}
				}
				if (!historyDelete.isEmpty()) {
					for (PatientMedicalHistoryTbl patientMedicalHistoryTbl : historyDelete) {
						delete(patientMedicalHistoryTbl);
					}
				}
				if (!historyUpdate.isEmpty()) {
					for (PatientMedicalHistoryTbl patientMedicalHistoryTbl : historyUpdate) {
						update(patientMedicalHistoryTbl);
					}
				}

			}
		}
		update(patientTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(patientTbl.getId());
		return response;

	}

	/**
	 * view patient
	 * 
	 * @param id
	 * @return PatientDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public PatientDetail view(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		PatientDetail response = new PatientDetail();
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class, id);
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(patientTbl.getId());
		response.setFirstName(patientTbl.getFirstName());
		response.setLastName(patientTbl.getLastName());
		if (patientTbl.getAge() != null)
			response.setAge(patientTbl.getAge());
		response.setAddress(patientTbl.getAddress());
		response.setEmail(patientTbl.getEmail());
		response.setGender(patientTbl.getGender().getDisplayName());
		response.setPhone(patientTbl.getPhone());
		response.setMobile(patientTbl.getMobile());
		response.setAilment(patientTbl.getAilment());
		response.setDiagnosis(patientTbl.getDiagnosis());
		response.setAllergies(patientTbl.getAllergies());
		response.setBloodGroup(patientTbl.getBloodGroup());
		response.setChronicDisease(patientTbl.getChronicDisease());
		response.setEmergencyNo(patientTbl.getEmergencyNo());
		response.setFamilyHistory(patientTbl.getFamilyHistory());
		response.setHeight(patientTbl.getHeight());
		response.setWeight(patientTbl.getWeight());
		response.setRh(patientTbl.getRh());
		if(patientTbl.getDob()!=null)
			response.setDob(patientTbl.getDob().toString());
		response.setEducation(patientTbl.getEducation());
		List<PatientMedicalHistoryTbl> medicalHistoryList = (List<PatientMedicalHistoryTbl>) getHistoryByPatientId(patientTbl
				.getId());
		if (!medicalHistoryList.isEmpty()) {
			List<PatientMedicalHistoryDTO> medicalDTOList = new ArrayList<PatientMedicalHistoryDTO>();
			for (PatientMedicalHistoryTbl medicalHistoryTbl : medicalHistoryList) {
				PatientMedicalHistoryDTO patientMedicalHistoryDTO = new PatientMedicalHistoryDTO();
				patientMedicalHistoryDTO.setId(medicalHistoryTbl.getId());
				if (medicalHistoryTbl.getDiagonisedAge() != null)
					patientMedicalHistoryDTO.setDiagonisedAge(medicalHistoryTbl
							.getDiagonisedAge());
				patientMedicalHistoryDTO.setIsCured(medicalHistoryTbl
						.getIsCured());
				patientMedicalHistoryDTO.setMedicalIssue(medicalHistoryTbl
						.getMedicalIssue());
				patientMedicalHistoryDTO.setMedication(medicalHistoryTbl
						.getMedication());
				patientMedicalHistoryDTO.setSurgery(medicalHistoryTbl
						.getSurgery());
				patientMedicalHistoryDTO.setTenure(medicalHistoryTbl
						.getTenure());
				patientMedicalHistoryDTO.setTreatment(medicalHistoryTbl
						.getTreatment());
				medicalDTOList.add(patientMedicalHistoryDTO);

			}
			response.setMedicalHistory(medicalDTOList);
		}

		return response;
	}

	public List<PatientMedicalHistoryTbl> getHistoryByPatientId(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDICALHISTORY);
		query.setParameter("param1", patientId);
		return executeQuery(PatientMedicalHistoryTbl.class, query);
	}
	public List<CaseTbl> getCaseByPatientId(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_CASE_BY_PATIENT);
		query.setParameter("param1", patientId);
		return executeQuery(CaseTbl.class, query);
	}
	public List<MedicalRecordTbl> getMedByPatientId(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_CASE_BY_PATIENT);
		query.setParameter("param1", patientId);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	public PatientMedicalHistoryTbl getPatientMedicalHistory(int historyId,
			int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_MEDICAL_HISTORY);
		query.setParameter("param1", patientId);
		return executeUniqueQuery(PatientMedicalHistoryTbl.class, query);
	}
	
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#AutoSaveCase(com.nv.netmd.rs.dto.CaseDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public AutoSaveResponseDTO AutoSaveCase(CaseDTO caseDto) throws PersistenceException {
		AutoSaveResponseDTO response = new AutoSaveResponseDTO();
		CaseTbl caseTbl;
		Boolean flag=false;
		if(caseDto.getId()!=0){
			caseTbl= getById(CaseTbl.class,caseDto.getId());
			 flag=true;
		}
		else{
			 caseTbl= new CaseTbl();	
		}
		caseTbl.setCaseName(caseDto.getCaseName());
		PatientTbl patientTbl = getById(PatientTbl.class,caseDto.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		caseTbl.setPatientTbl(patientTbl);
		caseTbl.setPatientType(caseDto.getPatientType());
	    DepartmentTbl departmentTbl=getById(DepartmentTbl.class,caseDto.getDepartmentId());
	    if(departmentTbl==null){
	    	PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getDepartmentId())));
			se.setDisplayErrMsg(true);
			throw se;
	    	
	    }
	    DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME_INDIAN_STD);
		Date date = null;
		try {
		if(caseDto.getAdmittedDate()!=null)
			date = df.parse(caseDto.getAdmittedDate().trim());
		}
		 catch (ParseException e) {
			 PersistenceException se = new PersistenceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
		}
	    caseTbl.setDepartmentTbl(departmentTbl);
	    Date currentTime=new Date();
	    caseTbl.setCreatedTime(currentTime);
	    caseTbl.setUpdatedTime(currentTime);
	    caseTbl.setBmi(caseDto.getBmi());
	    caseTbl.setHb(caseDto.getHbCount());
	    caseTbl.setHeight(caseDto.getHeight());
	    caseTbl.setWeight(caseDto.getWeight());
	    caseTbl.setAdmittedDate(date);
	    caseTbl.setDescription(caseDto.getDescription());
	    caseTbl.setStatus(StatusEnum.Active);
	    if(flag==true)
			update(caseTbl);
	    else
	    	save(caseTbl);
		
		response.setError(null);
		response.setSuccess(true);
		response.setCaseId(caseTbl.getId());
		
		return response;
		
	}
	
	
	/**
	 * create a case
	 * 
	 * @param CaseDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO createCase(CaseDTO caseDto) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();
		CaseTbl caseTbl;
		Boolean flag=false;
		if(caseDto.getId()!=0){
			caseTbl= getById(CaseTbl.class,caseDto.getId());
			 flag=true;
		}
		else{
			 caseTbl= new CaseTbl();	
		}
		caseTbl.setCaseName(caseDto.getCaseName());
		PatientTbl patientTbl = getById(PatientTbl.class,caseDto.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		caseTbl.setPatientTbl(patientTbl);
		caseTbl.setPatientType(caseDto.getPatientType());
		int deptId = caseDto.getDepartmentId();
	    DepartmentTbl departmentTbl=getById(DepartmentTbl.class,deptId);
	    if(departmentTbl==null){
	    	PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getDepartmentId())));
			se.setDisplayErrMsg(true);
			throw se;
	    	
	    }
	    DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME_INDIAN_STD);
		Date date = null;
		try {
		if(caseDto.getAdmittedDate()!=null)
			date = df.parse(caseDto.getAdmittedDate().trim());
		}
		 catch (ParseException e) {
			 PersistenceException se = new PersistenceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
		}
	    caseTbl.setDepartmentTbl(departmentTbl);
	    Date currentTime=new Date();
	    caseTbl.setCreatedTime(currentTime);
	    caseTbl.setUpdatedTime(currentTime);
	    caseTbl.setBmi(caseDto.getBmi());
	    caseTbl.setHb(caseDto.getHbCount());
	    caseTbl.setHeight(caseDto.getHeight());
	    caseTbl.setWeight(caseDto.getWeight());
	    caseTbl.setAdmittedDate(date);
	    caseTbl.setDescription(caseDto.getDescription());
	    caseTbl.setStatus(StatusEnum.Active);
	    
	    if(flag==true)
			update(caseTbl);
	    else
	    	save(caseTbl);
	    
	    
		response.setError(null);
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;
	}

	/* * List all the cases
	 * 
	 * @return CaseListResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public CaseListResponseDTO listCase() throws PersistenceException {
		// TODO Auto-generated method stub
		CaseListResponseDTO response = new CaseListResponseDTO();
		List<CaseDTO> casedtoList = new ArrayList<CaseDTO>();
		List<CaseTbl> caseTblList = (List<CaseTbl>) loadAll(CaseTbl.class);
		if (!caseTblList.isEmpty()) {
			for (CaseTbl caseTbl : caseTblList) {
				CaseDTO casedto = new CaseDTO();
				casedto.setId(caseTbl.getId());
				casedto.setCaseName(caseTbl.getCaseName());
				casedto.setPatientFirstName(caseTbl.getPatientTbl().getFirstName());
				casedto.setPatientLastName(caseTbl.getPatientTbl().getLastName());
				casedto.setStatus(caseTbl.getStatus().toString());
				casedtoList.add(casedto);
			}
			response.setCaseList(casedtoList);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * create medical record
	 * 
	 * @param MedicalRecordDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO createMedicalRecord(MedicalRecordDTO record) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();
		// TODO Auto-generated method stub
		MedicalRecordTbl reportTbl = new MedicalRecordTbl();
		CaseTbl caseTbl = getById(CaseTbl.class, record.getCaseId());
		if (caseTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getCaseId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		reportTbl.setCaseTbl(caseTbl);
		DoctorTbl doctorTbl = getById(DoctorTbl.class, record.getDoctorId());
		if (doctorTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getDoctorId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
//		if(doctorTbl.getGlobalId()==0){
//			PersistenceException se = new PersistenceException(
//					ErrorCodeEnum.DoctorGlobalIdNull);			
//			se.setDisplayErrMsg(false);
//			throw se;
//		}
		reportTbl.setDoctorTbl(doctorTbl);
		PatientTbl patientTbl = getById(PatientTbl.class, record.getPatientId());
		if (patientTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date createdTime=new Date();
		patientTbl.setUpdatedTime(createdTime);
		update(patientTbl);

		reportTbl.setPatientTbl(patientTbl);
		reportTbl.setMedicalRecord(record.getMedicalRecord());
		MedicalRecordTypeEnum type = MedicalRecordTypeEnum.getEnum(record.getType());
		reportTbl.setType(type);
		reportTbl.setStatus(StatusEnum.Active);
		reportTbl.setCreatedTime(createdTime);
		reportTbl.setUpdatedTime(createdTime);
		reportTbl.setGlobalId(0);
		save(reportTbl);
		response.setId(reportTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * list case of a patient
	 * 
	 * @param id
	 * @return CaseListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly = false)
	public CaseListResponseDTO listCaseByPatient(int patientId) throws PersistenceException {
		// TODO Auto-generated method stub
		CaseListResponseDTO response = new CaseListResponseDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		List<CaseDTO> casedtoList = new ArrayList<CaseDTO>();
		List<CaseTbl> caseTblList = (List<CaseTbl>) getCaseList(patientId);
		if (!caseTblList.isEmpty()) {
			for (CaseTbl caseTbl : caseTblList) {
				CaseDTO casedto = new CaseDTO();
				casedto.setId(caseTbl.getId());
				casedto.setCaseName(caseTbl.getCaseName());
				
				if (caseTbl.getCreatedTime() != null)
					casedto.setCreatedDate(df1.format(caseTbl.getCreatedTime()));
			
				if (caseTbl.getPatientTbl() != null)
					casedto.setPatientId(caseTbl.getPatientTbl().getId());
				casedtoList.add(casedto);
			}
			response.setCaseList(casedtoList);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;

	}
	
	
	@Override
	@Transactional(readOnly = false)
	public CaseListResponseDTO caselist(FilterDTO filterDTO) throws PersistenceException {
		CaseListResponseDTO caseList=null;
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.CASE);
		if (queryBuilder == null) {
			return caseList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<CaseTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),filterDTO.getFrom(), filterDTO.getCount());
		Long count = queryBuilder.getCount();
		// execute query
		List<CaseTbl> cases = queryBuilder.executeQuery(q);
		caseList = getCaseList(cases);
		caseList.setCount(count);
		caseList.setSuccess(true);
		return caseList;
	}

	private CaseListResponseDTO getCaseList(List<CaseTbl> cases) throws PersistenceException {
		CaseListResponseDTO response = new CaseListResponseDTO();
		int patientId=0;
		String createdOn=null;
		if (cases == null) {
			return response;
		}
		List<CaseDTO> caseList = new ArrayList<CaseDTO>();
		for (CaseTbl caseTbl : cases) {			
			DateFormat df = new SimpleDateFormat(Constants.NEW_DATE_FORMAT_WITHOUT_TIME);
			if (caseTbl.getCreatedTime() != null) {
				createdOn = df.format(caseTbl.getCreatedTime());

			}
			if (caseTbl.getPatientTbl() != null) {
				patientId=caseTbl.getPatientTbl().getId();
			}
			 boolean syncStatus=false;
			 String antenatalDate=null;
			List<CaseAnswerSetTbl> ansSetList= (ArrayList<CaseAnswerSetTbl>) getAnswerSetByCase(caseTbl.getId());
			for(CaseAnswerSetTbl ansSet:ansSetList)	{
				if(ansSet.getGlobalId()!=0)
					syncStatus=true;
				antenatalDate=ansSet.getAntenatalCreatedDate();
			}
			caseList.add(new CaseSyncDTO(caseTbl.getId(),patientId,caseTbl.getPatientTbl().getFirstName(),caseTbl.getPatientTbl().getLastName(),caseTbl.getCaseName(),caseTbl.getPatientType(),caseTbl.getDepartmentTbl().getName(),createdOn,syncStatus,antenatalDate));
		}
		response.setCaseList(caseList);
		return response;
	}
	

	public List<CaseTbl> getCaseList(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_CASE_BY_PATIENTID);
		query.setParameter("param1", patientId);
		return executeQuery(CaseTbl.class, query);
	}

		private List<CaseAnswerSetTbl> getAnswerSetByCase(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_ID);
		query.setParameter("param1", caseId);
		return executeQuery(CaseAnswerSetTbl.class, query);
	}
	
	/**
	 * view case
	 * 
	 * @param id
	 * @return CaseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public CaseDTO viewCase(int id) throws PersistenceException{
		// TODO Auto-generated method stub
		CaseDTO caseDto = new CaseDTO();
		DateFormat date = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		CaseTbl caseTbl = getById(CaseTbl.class, id);
		if (caseTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		caseDto.setId(caseTbl.getId());
		caseDto.setCaseName(caseTbl.getCaseName());
		caseDto.setPatientType(caseTbl.getPatientType());
		if(caseTbl.getPatientType().equals(OriginEnum.InPatient.getDisplayName())){
			if(caseTbl.getAdmittedDate()!=null){
			DateFormat admittedDate = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			System.out.println(admittedDate.format(caseTbl.getAdmittedDate()));
			caseDto.setAdmittedDate(admittedDate.format(caseTbl.getAdmittedDate()));
			}
		}
		if (caseTbl.getPatientTbl() != null)
			caseDto.setPatientId(caseTbl.getPatientTbl().getId());
		if (caseTbl.getCreatedTime() != null)
			caseDto.setCreatedDate(date.format(caseTbl.getCreatedTime()));
		if(caseTbl.getDepartmentTbl()!=null){
			caseDto.setDepartmentName(caseTbl.getDepartmentTbl().getName());
			caseDto.setDepartmentId(caseTbl.getDepartmentTbl().getId());
			if(!caseTbl.getDepartmentTbl().getName().equals(Constants.OBSTETRICS)){
			
				caseDto.setBmi(caseTbl.getBmi());
				caseDto.setHbCount(caseTbl.getHb());
				caseDto.setDescription(caseTbl.getDescription());
				caseDto.setHeight(caseTbl.getHeight());
				caseDto.setWeight(caseTbl.getWeight());
				
			}
			caseDto.setPatientFirstName(caseTbl.getPatientTbl().getFirstName());
			caseDto.setPatientLastName(caseTbl.getPatientTbl().getLastName());
			
		}
		
	return caseDto;
	}


	/**
	 * update a case
	 * 
	 * @param CaseDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateCase(CaseDTO caseDto) throws PersistenceException{
		
		ResponseDTO response = new ResponseDTO();
		CaseTbl caseTbl = getById(CaseTbl.class, caseDto.getId());
		if (caseTbl == null) {
			
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		caseTbl.setCaseName(caseDto.getCaseName());
	    DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME_INDIAN_STD);
		Date date = null;
		try {
		 if(!caseDto.getAdmittedDate().equals("")&& caseDto.getAdmittedDate()!=null)
		 {
			 date = df.parse(caseDto.getAdmittedDate().trim());
			caseTbl.setAdmittedDate(date);
		 }
		}
		 catch (ParseException e) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		PatientTbl patientTbl = getById(PatientTbl.class,
				caseDto.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		caseTbl.setPatientTbl(patientTbl);
		caseTbl.setPatientType(caseDto.getPatientType());
	    DepartmentTbl departmentTbl=getById(DepartmentTbl.class,caseDto.getDepartmentId());
	    if(departmentTbl==null){
	    	PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
					.getDepartmentId())));
			se.setDisplayErrMsg(true);
			throw se;	
	    }
	    caseTbl.setDepartmentTbl(departmentTbl);
	    Date currentTime=new Date();
	    caseTbl.setUpdatedTime(currentTime);
	    caseTbl.setBmi(caseDto.getBmi());
	    caseTbl.setHb(caseDto.getHbCount());
	    caseTbl.setHeight(caseDto.getHeight());
	    caseTbl.setWeight(caseDto.getWeight());
	    caseTbl.setDescription(caseDto.getDescription());
		update(caseTbl);
		
		if(caseDto.getDepartmentName().trim().equals(Constants.OBSTETRICS) && response.getId()!=0){
			
			DepartmentQuestionnaireTbl departmentQstnrTbl=getById(DepartmentQuestionnaireTbl.class,1);
		    if(departmentQstnrTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);
				se.addParam(new Parameter(Constants.ID, Integer.toString(caseDto
						.getDepartmentId())));
				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
	
		    CaseAnswerSetTbl caseAnswerSetTbl = (CaseAnswerSetTbl)  getAnswerSetByCaseId(caseDto.getId());
		    if(caseAnswerSetTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);
	
				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		  if(caseAnswerSetTbl!=null){
			    caseAnswerSetTbl.setUpdatedTime(currentTime);
			    caseAnswerSetTbl.setDepartmentQuestionnaireTbl(departmentQstnrTbl);
			    caseAnswerSetTbl.setCaseTbl(caseTbl);	 
			    caseAnswerSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
				update(caseAnswerSetTbl);
		  }
	}	
		response.setError(null);
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;

	}

	public CaseAnswerSetTbl getAnswerSetByCaseId(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_ID);
		query.setParameter("param1", caseId);
		return executeUniqueQuery(CaseAnswerSetTbl.class, query);
	}
	
	
	/**
	 * list of medical record for a patient
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly = false)
	public MedicalListResponseDTO listMedicalRecord(int patientId) throws PersistenceException {
		// TODO Auto-generated method stub
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		List<MedicalRecordDTO> medicalList = new ArrayList<MedicalRecordDTO>();
		List<MedicalRecordTbl> medicalRecordList = (ArrayList<MedicalRecordTbl>) getMedicalRecordList(patientId);
		if (!medicalRecordList.isEmpty()) {
			for (MedicalRecordTbl medicalRecordTbl : medicalRecordList) {
				MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
				medicalRecordDTO.setId(medicalRecordTbl.getId());
				if (medicalRecordTbl.getPatientTbl() != null)
					medicalRecordDTO.setPatientId(medicalRecordTbl
							.getPatientTbl().getId());
				if (medicalRecordTbl.getDoctorTbl() != null) {
					medicalRecordDTO.setDoctorId(medicalRecordTbl
							.getDoctorTbl().getId());
					medicalRecordDTO.setDoctorName(medicalRecordTbl.getDoctorTbl().getFirstName());
				}
				if (medicalRecordTbl.getCaseTbl() != null)
					medicalRecordDTO.setCaseId(medicalRecordTbl.getCaseTbl()
							.getId());
				if (medicalRecordTbl.getCreatedTime() != null)
					medicalRecordDTO.setDate(df1.format(medicalRecordTbl
							.getCreatedTime()));
				medicalRecordDTO.setMedicalRecord(medicalRecordTbl
						.getMedicalRecord());
				medicalRecordDTO.setType(medicalRecordTbl.getType()
						.getDisplayName());
				medicalList.add(medicalRecordDTO);
			}
		}
		response.setMedicalList(medicalList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	public List<MedicalRecordTbl> getMedicalRecordList(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDICALRECORD_BY_PATIENTID);
		query.setParameter("param1", patientId);
		return executeQuery(MedicalRecordTbl.class, query);
	}

	public List<MedicalRecordTbl> getVisitList(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PERSONALVISIT_BY_PATIENTID);
		query.setParameter("param1", patientId);
		query.setParameter("param2","PERSONAL_VISIT");
		return executeQuery(MedicalRecordTbl.class, query);
	}

	/**
	 * get list personal visit of a patient
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 * @throws PersistenceException 
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public MedicalListResponseDTO listPersonalVisit(int patientId) throws PersistenceException {
		// TODO Auto-generated method stub
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		List<MedicalRecordDTO> medicalList = new ArrayList<MedicalRecordDTO>();
		List<MedicalRecordTbl> medicalRecordList = (ArrayList<MedicalRecordTbl>) getVisitList(patientId);
		if (!medicalRecordList.isEmpty()) {
			for (MedicalRecordTbl medicalRecordTbl : medicalRecordList) {
				MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
				medicalRecordDTO.setId(medicalRecordTbl.getId());
				if (medicalRecordTbl.getPatientTbl() != null)
					medicalRecordDTO.setPatientId(medicalRecordTbl
							.getPatientTbl().getId());
				if (medicalRecordTbl.getDoctorTbl() != null) {
					medicalRecordDTO.setDoctorId(medicalRecordTbl
							.getDoctorTbl().getId());
					medicalRecordDTO.setDoctorName(medicalRecordTbl
							.getDoctorTbl().getFirstName());
				}
				if (medicalRecordTbl.getCaseTbl() != null)
					medicalRecordDTO.setCaseId(medicalRecordTbl.getCaseTbl()
							.getId());
				if (medicalRecordTbl.getCreatedTime() != null)
					medicalRecordDTO.setDate(df1.format(medicalRecordTbl
							.getCreatedTime()));
				medicalRecordDTO.setMedicalRecord(medicalRecordTbl
						.getMedicalRecord());
				medicalRecordDTO.setType(medicalRecordTbl.getType()
						.getDisplayName());
				medicalList.add(medicalRecordDTO);
			}
		}
		response.setMedicalList(medicalList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * view medical record
	 * 
	 * @param id
	 * @return MedicalRecordDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public MedicalRecordDTO viewMedicalRecord(int id)throws PersistenceException {
		// TODO Auto-generated method stub
		MedicalRecordDTO response = new MedicalRecordDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		MedicalRecordTbl medicalRecordTbl = getById(MedicalRecordTbl.class, id);
		if (medicalRecordTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.MedicalRecordNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(medicalRecordTbl.getId());
		if (medicalRecordTbl.getPatientTbl() != null)
			response.setPatientId(medicalRecordTbl.getPatientTbl().getId());
		if (medicalRecordTbl.getDoctorTbl() != null) {
			response.setDoctorId(medicalRecordTbl.getDoctorTbl().getId());
			response.setDoctorName(medicalRecordTbl.getDoctorTbl().getFirstName()+" "+medicalRecordTbl.getDoctorTbl().getLastName());
		}
		if (medicalRecordTbl.getCaseTbl() != null)
			response.setCaseId(medicalRecordTbl.getCaseTbl().getId());
		if (medicalRecordTbl.getCreatedTime() != null)
			response.setDate(df1.format(medicalRecordTbl.getCreatedTime()));
		response.setMedicalRecord(medicalRecordTbl.getMedicalRecord());
		response.setType(medicalRecordTbl.getType().getDisplayName());
		return response;
	}

	/**
	 * update medical record
	 * 
	 * @param MedicalRecordDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO record) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();
		// TODO Auto-generated method stub

		MedicalRecordTbl recordTbl = getById(MedicalRecordTbl.class,
				record.getId());
		if (recordTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.MedicalRecordNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaseTbl caseTbl = getById(CaseTbl.class, record.getCaseId());
		if (caseTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getCaseId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		recordTbl.setCaseTbl(caseTbl);
		DoctorTbl doctorTbl = getById(DoctorTbl.class, record.getDoctorId());
		if (doctorTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getDoctorId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		recordTbl.setDoctorTbl(doctorTbl);
		PatientTbl patientTbl = getById(PatientTbl.class, record.getPatientId());
		if (patientTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(record
					.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		recordTbl.setPatientTbl(patientTbl);
		recordTbl.setMedicalRecord(record.getMedicalRecord());
		MedicalRecordTypeEnum type = MedicalRecordTypeEnum.getEnum(record.getType());
		recordTbl.setType(type);
		// DateFormat df1 = new
		// SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);

		Date currentTime=new Date();
		recordTbl.setUpdatedTime(currentTime);
		update(recordTbl);
		response.setId(recordTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * get medical record by giving patient and case
	 * 
	 * @param patientId,caseId
	 * @return MedicalListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly = false)
	public MedicalListResponseDTO getMedicalRecordByCase(int patientId,
			int caseId) throws PersistenceException {
		// TODO Auto-generated method stub
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		List<MedicalRecordDTO> medicalList = new ArrayList<MedicalRecordDTO>();
		PatientTbl patientTbl = getById(PatientTbl.class, patientId);
		if (patientTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(patientId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaseTbl caseTbl = getById(CaseTbl.class, caseId);
		if (caseTbl == null) {
			// error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(caseId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<MedicalRecordTbl> medicalRecordList = (ArrayList<MedicalRecordTbl>) getRecordByCase(
				patientId, caseId);
		if (!medicalRecordList.isEmpty()) {
			for (MedicalRecordTbl medicalRecordTbl : medicalRecordList) {
				MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
				medicalRecordDTO.setId(medicalRecordTbl.getId());
				if (medicalRecordTbl.getPatientTbl() != null)
					medicalRecordDTO.setPatientId(medicalRecordTbl
							.getPatientTbl().getId());
				if (medicalRecordTbl.getDoctorTbl() != null) {
					medicalRecordDTO.setDoctorId(medicalRecordTbl
							.getDoctorTbl().getId());
					medicalRecordDTO.setDoctorName(medicalRecordTbl
							.getDoctorTbl().getFirstName());
				}
				if (medicalRecordTbl.getCaseTbl() != null)
					medicalRecordDTO.setCaseId(medicalRecordTbl.getCaseTbl()
							.getId());
				if (medicalRecordTbl.getCreatedTime() != null)
					medicalRecordDTO.setDate(df1.format(medicalRecordTbl
							.getCreatedTime()));
				medicalRecordDTO.setMedicalRecord(medicalRecordTbl
						.getMedicalRecord());
				medicalRecordDTO.setType(medicalRecordTbl.getType()
						.getDisplayName());
				medicalList.add(medicalRecordDTO);
			}
		}
		response.setMedicalList(medicalList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	public List<MedicalRecordTbl> getRecordByCase(int patientId, int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDICALRECORD_BY_CASEID);
		query.setParameter("param1", patientId);
		query.setParameter("param2", caseId);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	/**
	 * Get newily created patients for Sync
	 * @return List<PatientDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<PatientDetail> getNewPatient() throws PersistenceException {
		List<PatientTbl> patientListTbl=(ArrayList<PatientTbl>)getNewilyCreatedPatient();
		List<PatientDetail>patientDetailList=new ArrayList<PatientDetail>();
		if(!patientListTbl.isEmpty()){
			for (PatientTbl patientTbl : patientListTbl) {
				PatientDetail patientDetail=getPatientDetail(patientTbl);
				patientDetailList.add(patientDetail);
			}		
		}
		return patientDetailList;
	}
	@Override
	@Transactional(readOnly=false)
	public List<PatientDetail> getUpdatedPatient() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<PatientDetail>patientDetailList=new ArrayList<PatientDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<PatientTbl> patientListTbl=(ArrayList<PatientTbl>)getUpdatedPatientList(syncTbl.getUploadedTime());

			if(!patientListTbl.isEmpty()){
				for (PatientTbl patientTbl : patientListTbl) {
					PatientDetail patientDetail=getPatientDetail(patientTbl);
					patientDetailList.add(patientDetail);
				}
			}
		}
		return patientDetailList;
	}
	public List<PatientTbl> getUpdatedPatientList(Date lastUploadedTime ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_PATIENT);
		query.setParameter("param1", lastUploadedTime);
		return executeQuery(PatientTbl.class, query);
	}
	/**
	 * get deleted patient
	 * @return List<PatientDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<PatientDetail> getDeletedPatient() throws PersistenceException {
		List<PatientDetail>patientDetailList=new ArrayList<PatientDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<PatientTbl> patientListTbl=(ArrayList<PatientTbl>)getDeletedPatientList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!patientListTbl.isEmpty()){
				for (PatientTbl patientTbl : patientListTbl) {
					PatientDetail patientDetail=getPatientDetail(patientTbl);
					patientDetailList.add(patientDetail);
				}
			}
		}
		return patientDetailList;
	}
	private PatientDetail getPatientDetail(PatientTbl patientTbl) throws PersistenceException{
		PatientDetail response = new PatientDetail();
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT);
		//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		//		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class, id);
		//		if (patientTbl == null) {
		//			PersistenceException se = new PersistenceException(
		//					ErrorCodeEnum.PatientNotFound);
		//			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
		//			se.setDisplayErrMsg(true);
		//			throw se;
		//		}
		response.setId(patientTbl.getId());
		response.setFirstName(patientTbl.getFirstName());
		response.setLastName(patientTbl.getLastName());
		if (patientTbl.getAge() != null)
			response.setAge(patientTbl.getAge());
		response.setAddress(patientTbl.getAddress());
		response.setGlobalId(patientTbl.getGlobalId());
		response.setEmail(patientTbl.getEmail());
		response.setGender(patientTbl.getGender().getDisplayName());
		response.setPhone(patientTbl.getPhone());
		response.setMobile(patientTbl.getMobile());
		response.setAilment(patientTbl.getAilment());
		response.setDiagnosis(patientTbl.getDiagnosis());
		response.setAllergies(patientTbl.getAllergies());
		response.setBloodGroup(patientTbl.getBloodGroup());
		response.setChronicDisease(patientTbl.getChronicDisease());
		response.setEmergencyNo(patientTbl.getEmergencyNo());
		response.setFamilyHistory(patientTbl.getFamilyHistory());
		response.setHeight(patientTbl.getHeight());
		response.setWeight(patientTbl.getWeight());
		response.setRh(patientTbl.getRh());
		response.setStatus(patientTbl.getStatus().toString());
		//response.setDob(patientTbl.getDob().toString());
		response.setEducation(patientTbl.getEducation());
		List<PatientMedicalHistoryTbl> medicalHistoryList = (List<PatientMedicalHistoryTbl>) getHistoryByPatientId(patientTbl
				.getId());
		if (!medicalHistoryList.isEmpty()) {
			List<PatientMedicalHistoryDTO> medicalDTOList = new ArrayList<PatientMedicalHistoryDTO>();
			for (PatientMedicalHistoryTbl medicalHistoryTbl : medicalHistoryList) {
				PatientMedicalHistoryDTO patientMedicalHistoryDTO = new PatientMedicalHistoryDTO();
				patientMedicalHistoryDTO.setId(medicalHistoryTbl.getId());
				if (medicalHistoryTbl.getDiagonisedAge() != null)
					patientMedicalHistoryDTO.setDiagonisedAge(medicalHistoryTbl
							.getDiagonisedAge());
				patientMedicalHistoryDTO.setIsCured(medicalHistoryTbl
						.getIsCured());
				patientMedicalHistoryDTO.setMedicalIssue(medicalHistoryTbl
						.getMedicalIssue());
				patientMedicalHistoryDTO.setMedication(medicalHistoryTbl
						.getMedication());
				patientMedicalHistoryDTO.setSurgery(medicalHistoryTbl
						.getSurgery());
				patientMedicalHistoryDTO.setTenure(medicalHistoryTbl
						.getTenure());
				patientMedicalHistoryDTO.setTreatment(medicalHistoryTbl
						.getTreatment());
				medicalDTOList.add(patientMedicalHistoryDTO);

			}
			response.setMedicalHistory(medicalDTOList);
		}

		return response;
	}
	public List<PatientTbl> getNewilyCreatedPatient( ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_PATIENT);
		query.setParameter("param1", 0);
		return executeQuery(PatientTbl.class, query);
	}
	public List<PatientTbl> getDeletedPatientList(Date lastUploadedTime,StatusEnum status) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_PATIENT);
		query.setParameter("param1", lastUploadedTime);
		query.setParameter("param2", status);
		return executeQuery(PatientTbl.class, query);
	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}

	/**
	 * update with the global id for newly created patient 
	 * @param doctorResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void addPatientSyncResponse(PatientResponse patientResponse) throws PersistenceException {
		// TODO Auto-generated method stub
		if(patientResponse.getError()==null){
			PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,patientResponse.getId());
			if (patientTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.PatientNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(patientResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			patientTbl.setGlobalId(patientResponse.getGlobalId());
			update(patientTbl);
		}

	}

	/**
	 *update the updated time of updated patient if error occur in sync
	 * @param patientResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updatePatientSyncResponse(PatientResponse patientResponse)throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(patientResponse.getError()!=null){
			PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,patientResponse.getId());
			if (patientTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.PatientNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(patientResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				if(patientResponse.getError()!=null)
				patientTbl.setUpdatedTime(df.parse(patientResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				log.error("while updating the patient error occured",e);
				e.printStackTrace();
			}
			update(patientTbl);
		}

	}
	/**
	 * update the updated time of deleted patient if error occur in sync
	 * @param patientResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void deletePatientSyncResponse(PatientResponse patientResponse)throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(patientResponse.getError()!=null){
			PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,patientResponse.getId());
			if (patientTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.PatientNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(patientResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				patientTbl.setUpdatedTime(df.parse(patientResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("while deleting the patient error occured",e);
				e.printStackTrace();
			}
			update(patientTbl);
		}

	}

	/**
	 * sync patient from YNW 
	 * @param patient
	 * @return PatientResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public PatientResponse patientFromYNW(PatientDetail patient) throws PersistenceException {
		
		PatientResponse patientResponse=new PatientResponse();
		DateFormat df=new SimpleDateFormat(Constants.TIME);
		PatientTbl patientTbl= getPatientByGlobalId(patient.getGlobalId());
		if(patientTbl==null){
			ResponseDTO response=createPatientFromYNW(patient);
			patientResponse.setSuccess(response.isSuccess());
			patientResponse.setError(response.getError());
			patientResponse.setId(response.getId());
			return patientResponse;
		}
		String firstName=capitalizeFirstLetter(patient.getFirstName());
		patientTbl.setFirstName(firstName);
		String lastName=capitalizeFirstLetter(patient.getLastName());
		patientTbl.setLastName(lastName);
//		patientTbl.setFirstName(patient.getFirstName());
//		patientTbl.setLastName(patient.getLastName());
		patientTbl.setAge(patient.getAge());
		patientTbl.setAddress(patient.getAddress());
		patientTbl.setEmail(patient.getEmail());
		patientTbl.setMobile(patient.getMobile());
		patientTbl.setPhone(patient.getPhone());
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		patientTbl.setGender(gender);
		patientTbl.setDiagnosis(patient.getDiagnosis());
		patientTbl.setAilment(patient.getAilment());
		patientTbl.setAllergies(patient.getAllergies());
		
		patientTbl.setChronicDisease(patient.getChronicDisease());
		patientTbl.setEmergencyNo(patient.getEmergencyNo());
		patientTbl.setFamilyHistory(patient.getFamilyHistory());
		patientTbl.setHeight(patient.getHeight());
		patientTbl.setWeight(patient.getWeight());
		StatusEnum status=StatusEnum.getEnum(patient.getStatus());
		if(status.equals(StatusEnum.Inactive)){
			List<PatientAppointmentTbl>appointmentList=(ArrayList<PatientAppointmentTbl>)getAppointmentByPatient(patientTbl.getId());
			if(!appointmentList.isEmpty()){
				for (PatientAppointmentTbl patientAppointmentTbl : appointmentList) {
					patientAppointmentTbl.setStatus(StatusEnum.Inactive);
					patientAppointmentTbl.setUpdatedTime(new Date());
					update(patientAppointmentTbl);
					MessageTbl messageTbl=new MessageTbl();
					messageTbl.setMessage(patientAppointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+patientAppointmentTbl.getDoctorTbl().getFirstName()+" on "+patientAppointmentTbl.getDate()+" "+df.format(patientAppointmentTbl.getStartingTime())+" has been deleted");					
					messageTbl.setDoctorTbl(patientAppointmentTbl.getDoctorTbl());
					messageTbl.setCreatedTime(new Date());
					messageTbl.setViewed(false);
					save(messageTbl);
				}
			}
		}
		patientTbl.setStatus(status);
		Date currentTime=new Date();
		patientTbl.setCreatedTime(currentTime);
		patientTbl.setUpdatedTime(currentTime);
		if (!patient.getMedicalHistory().isEmpty()) {
			List<PatientMedicalHistoryTbl> historyTblList = (ArrayList<PatientMedicalHistoryTbl>) getHistoryByPatientId(patient.getId());
			if(!historyTblList.isEmpty()){
				for (PatientMedicalHistoryTbl patientMedicalHistoryTbl : historyTblList) {
					delete(patientMedicalHistoryTbl);
				}
			}
			PatientMedicalHistoryTbl patientMedicalHistoryTbl = new PatientMedicalHistoryTbl();
			for (PatientMedicalHistoryDTO patientMedicalHistoryDto : patient
					.getMedicalHistory()) {

				patientMedicalHistoryTbl
				.setDiagonisedAge(patientMedicalHistoryDto
						.getDiagonisedAge());
				patientMedicalHistoryTbl.setIsCured(patientMedicalHistoryDto
						.getIsCured());
				patientMedicalHistoryTbl
				.setMedicalIssue(patientMedicalHistoryDto
						.getMedicalIssue());
				patientMedicalHistoryTbl.setMedication(patientMedicalHistoryDto
						.getMedication());
				patientMedicalHistoryTbl.setPatientTbl(patientTbl);
				patientMedicalHistoryTbl.setSurgery(patientMedicalHistoryDto
						.getSurgery());
				patientMedicalHistoryTbl.setTenure(patientMedicalHistoryDto
						.getTenure());
				patientMedicalHistoryTbl.setTreatment(patientMedicalHistoryDto
						.getTreatment());
			}
			save(patientMedicalHistoryTbl);
		}
		List<CaseTbl>caseList=(ArrayList<CaseTbl>)getCaseByPatientId(patientTbl.getId());
		if(!caseList.isEmpty()){
			for (CaseTbl caseTbl : caseList) {
				delete(caseTbl);
			}
		}
//		if(!patient.getCaseResponseList().isEmpty()){
//			for(CaseResponseDTO caseResponse:patient.getCaseResponseList()){
//				CaseTbl caseTbl=new CaseTbl();
//				caseTbl.setCaseName(caseResponse.getCaseName());
//				CaseStatusEnum caseStatus=CaseStatusEnum.getEnum(caseResponse.getCaseStatus());
//				
//				caseTbl.setPatientTbl(patientTbl);
//				caseTbl.setCreatedTime(currentTime);
//				save(caseTbl);
//				if(!caseResponse.getMedicalRecord().isEmpty()){
//					for (MedicalRecordDTO medicalRecordDTO : caseResponse.getMedicalRecord()) {
//						MedicalRecordTbl medicalRecordTbl=new MedicalRecordTbl();
//						medicalRecordTbl.setCaseTbl(caseTbl);
//						medicalRecordTbl.setDate(currentTime);
//						medicalRecordTbl.setMedicalRecord(medicalRecordDTO.getMedicalRecord());
//						MedicalRecordTypeEnum typeEnum=MedicalRecordTypeEnum.getEnum(medicalRecordDTO.getType());
//						medicalRecordTbl.setType(typeEnum);
//						medicalRecordTbl.setPatientTbl(patientTbl);
//						save(medicalRecordTbl);
//					}
//				}
//			}
//		}
		update(patientTbl);
		patientResponse.setError(null);
		patientResponse.setSuccess(true);		
		return patientResponse;
	}
	private ResponseDTO createPatientFromYNW(PatientDetail patient) throws PersistenceException{
		//System.out.println("first name patient"+patient.getFirstName());
		ResponseDTO response = new ResponseDTO();
		PatientTbl patientTbl = new PatientTbl();
		patientTbl.setGlobalId(patient.getGlobalId());
		String firstName=capitalizeFirstLetter(patient.getFirstName());
		patientTbl.setFirstName(firstName);
		
		patientTbl.setLastName(patient.getLastName());
		patientTbl.setAge(patient.getAge());
		patientTbl.setAddress(patient.getAddress());
		patientTbl.setEmail(patient.getEmail());
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		patientTbl.setGender(gender);
		patientTbl.setPhone(patient.getPhone());
		patientTbl.setMobile(patient.getMobile());
		patientTbl.setAilment(patient.getAilment());
		patientTbl.setDiagnosis(patient.getDiagnosis());
		patientTbl.setAllergies(patient.getAllergies());
		BloodGroupEnum bloodgrpEnum=BloodGroupEnum.getEnum(patient.getBloodGroup());
		patientTbl.setBloodGroup(bloodgrpEnum.getDisplayName());
		patientTbl.setChronicDisease(patient.getChronicDisease());
		patientTbl.setEmergencyNo(patient.getEmergencyNo());
		patientTbl.setFamilyHistory(patient.getFamilyHistory());
		patientTbl.setHeight(patient.getHeight());
		patientTbl.setWeight(patient.getWeight());
		StatusEnum status=StatusEnum.getEnum(patient.getStatus());
		patientTbl.setStatus(status);
		Date currentTime=new Date();
		patientTbl.setCreatedTime(currentTime);
		patientTbl.setUpdatedTime(currentTime);
		save(patientTbl);
		if (!patient.getMedicalHistory().isEmpty()) {
			PatientMedicalHistoryTbl patientMedicalHistoryTbl = new PatientMedicalHistoryTbl();
			for (PatientMedicalHistoryDTO patientMedicalHistoryDto : patient
					.getMedicalHistory()) {

				patientMedicalHistoryTbl
				.setDiagonisedAge(patientMedicalHistoryDto
						.getDiagonisedAge());
				patientMedicalHistoryTbl.setIsCured(patientMedicalHistoryDto
						.getIsCured());
				patientMedicalHistoryTbl
				.setMedicalIssue(patientMedicalHistoryDto
						.getMedicalIssue());
				patientMedicalHistoryTbl.setMedication(patientMedicalHistoryDto
						.getMedication());
				patientMedicalHistoryTbl.setPatientTbl(patientTbl);
				patientMedicalHistoryTbl.setSurgery(patientMedicalHistoryDto
						.getSurgery());
				patientMedicalHistoryTbl.setTenure(patientMedicalHistoryDto
						.getTenure());
				patientMedicalHistoryTbl.setTreatment(patientMedicalHistoryDto
						.getTreatment());
			}
			save(patientMedicalHistoryTbl);
		}
//		if(!patient.getCaseResponseList().isEmpty()){
//			for(CaseResponseDTO caseResponse:patient.getCaseResponseList()){
//				CaseTbl caseTbl=new CaseTbl();
//				caseTbl.setCaseName(caseResponse.getCaseName());
//				CaseStatusEnum caseStatus=CaseStatusEnum.getEnum(caseResponse.getCaseStatus());
//				
//				caseTbl.setPatientTbl(patientTbl);
//				caseTbl.setCreatedTime(currentTime);
//				save(caseTbl);
//				if(!caseResponse.getMedicalRecord().isEmpty()){
//					for (MedicalRecordDTO medicalRecordDTO : caseResponse.getMedicalRecord()) {
//						MedicalRecordTbl medicalRecordTbl=new MedicalRecordTbl();
//						medicalRecordTbl.setCaseTbl(caseTbl);
//						medicalRecordTbl.setDate(currentTime);
//						medicalRecordTbl.setMedicalRecord(medicalRecordDTO.getMedicalRecord());
//						MedicalRecordTypeEnum typeEnum=MedicalRecordTypeEnum.getEnum(medicalRecordDTO.getType());
//						medicalRecordTbl.setType(typeEnum);
//						medicalRecordTbl.setPatientTbl(patientTbl);
//						save(medicalRecordTbl);
//					}
//				}
//			}
//		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(patientTbl.getId());
		return response;
	}

	private PatientTbl getPatientByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(PatientTbl.class, query);
	}
	public List<PatientAppointmentTbl> getAppointmentByPatient(int patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENT_BY_PATIENT);
		query.setParameter("param1", patientId);
		return executeQuery(PatientAppointmentTbl.class, query);
	}
	public PatientTbl getPatientByEmail(String email) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(PatientTbl.class, query);
	}
	public PatientTbl getPatientByEmail(String email,String firstName) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAIL_NAME);
		query.setParameter("param1", email);
		query.setParameter("param2", firstName.toUpperCase());
		return executeUniqueQuery(PatientTbl.class, query);
	}


	/**
	 * delete or inactive the patient
	 * when inactive also inactive corresponding appointments
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		DateFormat df=new SimpleDateFormat(Constants.TIME);
		PatientTbl patientTbl= (PatientTbl)getById(PatientTbl.class, id);
		if(patientTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<PatientAppointmentTbl>appointmentList=(ArrayList<PatientAppointmentTbl>)getAppointmentByPatient(patientTbl.getId());
		if(!appointmentList.isEmpty()){
			for (PatientAppointmentTbl patientAppointmentTbl : appointmentList) {
				patientAppointmentTbl.setStatus(StatusEnum.Inactive);
				patientAppointmentTbl.setUpdatedTime(new Date());
				update(patientAppointmentTbl);
				MessageTbl messageTbl=new MessageTbl();
				messageTbl.setMessage(patientAppointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+patientAppointmentTbl.getDoctorTbl().getFirstName()+" on "+patientAppointmentTbl.getDate()+" "+df.format(patientAppointmentTbl.getStartingTime())+" has been deleted");					
				messageTbl.setDoctorTbl(patientAppointmentTbl.getDoctorTbl());
				messageTbl.setCreatedTime(new Date());
				messageTbl.setViewed(false);
				save(messageTbl);
			}
		}
		
		List<CaseTbl>caseList=(ArrayList<CaseTbl>)getCaseListByPatient(patientTbl.getId());
		List<MedicalRecordTbl>medRecordList=(ArrayList<MedicalRecordTbl>)getmedRecordListByPatient(patientTbl.getId());
		//if(patientTbl.getCaseTbls()==null || patientTbl.getMedicalRecordTbls()==null){
		if(caseList.isEmpty() && medRecordList.isEmpty()){
			patientTbl.setStatus(StatusEnum.Inactive);
			patientTbl.setUpdatedTime(new Date());
			update(patientTbl);
			response.setError(null);
			response.setSuccess(true);
			response.setId(id);
			return response;
		}else{
			ErrorDTO dto = new ErrorDTO();
			dto.setErrCode(ErrorCodeEnum.PleaseDeleteCaseAnsMedRecord.getErrCode());
			response.setSuccess(false);
			response.setError(dto);
			return response;
		}
    
	}
	private List<MedicalRecordTbl> getmedRecordListByPatient(Integer patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDICALRECORD_BY_PATIENTID);
		query.setParameter("param1", patientId);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	private List<CaseTbl> getCaseListByPatient(Integer patientId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_CASE_BY_PATIENT);
		query.setParameter("param1", patientId);
		return executeQuery(CaseTbl.class, query);
	}
	/**
	 * delete or inactive the patient
	 * when inactive also inactive corresponding appointments
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteMedicalRecord(int id)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		MedicalRecordTbl medRecordTbl= (MedicalRecordTbl)getById(MedicalRecordTbl.class, id);
		if(medRecordTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.MedicalRecordNotFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		medRecordTbl.setStatus(StatusEnum.Inactive);
		medRecordTbl.setUpdatedTime(new Date());
		update(medRecordTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;

	}
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteCase(int id)throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		CaseTbl caseTbl = (CaseTbl) getById(CaseTbl.class, id);
		if (caseTbl==null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<MedicalRecordTbl> MedRecordList=(ArrayList<MedicalRecordTbl>)getMedicalRecordByCase(caseTbl.getId());
		if(!MedRecordList.isEmpty()){
			for (MedicalRecordTbl medicalRecordbl : MedRecordList) {
				medicalRecordbl.setStatus(StatusEnum.Inactive);
				update(medicalRecordbl);
			}
		}
		caseTbl.setStatus(StatusEnum.Inactive);
		update(caseTbl);
		
		CaseAnswerSetTbl caseAnswerSetTbl = (CaseAnswerSetTbl)  getAnswerSetByCaseId(id);
		if(caseAnswerSetTbl!=null){
			caseAnswerSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
			caseAnswerSetTbl.setStatus(StatusEnum.Inactive);	
			update(caseAnswerSetTbl);
		}
//		List<AnswerTbl> answerlist=(ArrayList<AnswerTbl>)getAnswersListByCaseId(id);
//		if(!answerlist.isEmpty()){
//			for(AnswerTbl answer:answerlist){
//				delete(answer);
//			}
//		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;
	}
	
	private List<AnswerTbl> getAnswersListByCaseId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_CASE);
		query.setParameter("param1", id);
		return executeQuery(AnswerTbl.class, query);
	}
	private List<MedicalRecordTbl>getMedicalRecordByCase(int caseId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDRECORD_BY_CASEID);
		query.setParameter("param1", caseId);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	
	public List<AnswerTbl> getAnswersLists(int qstnrId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSLIST_BY_ANSSET_ID);
		query.setParameter("param1", qstnrId);
		return executeQuery(AnswerTbl.class, query);
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.PatientDao#getPatients()
	 */
	@Override
	@Transactional(readOnly=false)
	public PatientListResponseDTO getPatients() throws PersistenceException {
		// TODO Auto-generated method stub
		PatientListResponseDTO response=new PatientListResponseDTO();
		List<PatientDetail>pateintDetailList=new ArrayList<PatientDetail>();
		List<PatientTbl> patientTblList=(ArrayList<PatientTbl>)getPatientList();
		if(!patientTblList.isEmpty()){
			for (PatientTbl patientTbl : patientTblList) {
				PatientDetail patientDetail=new PatientDetail();
				patientDetail.setId(patientTbl.getId());
				patientDetail.setFirstName(patientTbl.getFirstName());
				patientDetail.setLastName(patientTbl.getLastName());
				patientDetail.setEmail(patientTbl.getEmail());
				patientDetail.setPhone(patientTbl.getPhone());
				patientDetail.setMobile(patientTbl.getMobile());
				patientDetail.setAddress(patientTbl.getAddress());
				patientDetail.setUpdatedDateTime(patientTbl.getUpdatedTime().toString());
				patientDetail.setStatus(patientTbl.getStatus().toString());
				if(patientTbl.getDob()!=null)
					patientDetail.setDob(patientTbl.getDob().toString());
				patientDetail.setBloodGroup(patientTbl.getBloodGroup());
				patientDetail.setGender(patientTbl.getGender().getDisplayName());
				patientDetail.setAge(patientTbl.getAge());
				pateintDetailList.add(patientDetail);
			}
		}
		response.setError(null);
		response.setPatient(pateintDetailList);
		response.setSuccess(true);
		return response;
	}
	
	public List<PatientTbl> getPatientList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENTS);		
		return executeQuery(PatientTbl.class, query);
	}
	@Override
	public List<CaseDTO> getNewCase() throws PersistenceException {
		List<CaseTbl>caseListTbl=(ArrayList<CaseTbl>)getNewlyCreatedCaseList();
		List<CaseDTO>caseList=new ArrayList<CaseDTO>();
		if(!caseListTbl.isEmpty()){
			for (CaseTbl caseTbl : caseListTbl) {
				CaseDTO caseDto=getCaseDetail(caseTbl);
				caseDto.setActionName(ActionEnum.CREATE.toString());
				caseList.add(caseDto);
			}		
		}
		return caseList;
	}
	
	private List<CaseTbl> getNewlyCreatedCaseList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_CASE);
		query.setParameter("param1", 0);
		query.setMaxResults(10);
		return executeQuery(CaseTbl.class, query);
	}
	
	private CaseDTO getCaseDetail(CaseTbl caseTbl) throws PersistenceException {
		CaseDTO caseDto = new CaseDTO();
		DateFormat date = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		caseDto.setId(caseTbl.getId());
		caseDto.setCaseName(caseTbl.getCaseName());
		caseDto.setPatientType(caseTbl.getPatientType());
		if(caseTbl.getPatientType().equals(OriginEnum.InPatient)){
			DateFormat admittedDate = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			caseDto.setAdmittedDate(admittedDate.format(caseTbl.getAdmittedDate()));
		}
		if (caseTbl.getPatientTbl() != null)
			caseDto.setPatientId(caseTbl.getPatientTbl().getGlobalId());
		if (caseTbl.getCreatedTime() != null)
			caseDto.setCreatedDate(date.format(caseTbl.getCreatedTime()));
		if(caseTbl.getDepartmentTbl()!=null){
//			if(caseTbl.getDepartmentTbl().getName().trim().equals(Constants.OBSTETRICS)){
//				caseDto=questionDao.view(caseTbl.getId(),caseDto);
//				
//			}
//	
			
			//if(!caseTbl.getDepartmentTbl().getName().equals(Constants.OBSTETRICS)){
			
				caseDto.setBmi(caseTbl.getBmi());
				caseDto.setHbCount(caseTbl.getHb());
				caseDto.setDescription(caseTbl.getDescription());
				caseDto.setHeight(caseTbl.getHeight());
				caseDto.setWeight(caseTbl.getWeight());
				
			//}
			if(caseTbl.getDepartmentTbl().getName().equals(Constants.OBSTETRICS)){
				CaseAnswerSetTbl caseAnsSetTbl = (CaseAnswerSetTbl)  getAnswerSetByCaseId(caseTbl.getId());
				if(caseAnsSetTbl!=null)
					caseDto.setAnswerSetId(caseAnsSetTbl.getId());
			}
			caseDto.setDepartmentId(caseTbl.getDepartmentTbl().getId());
			caseDto.setDepartmentName(caseTbl.getDepartmentTbl().getName());
			caseDto.setGlobalId(caseTbl.getGlobalId());
		}
		
	return caseDto;
	}
	
	
	
	@Override
	public List<MedicalRecordDTO> getNewMedicalRecord() throws PersistenceException {
		List<MedicalRecordTbl>medicalRecordListTbl=(ArrayList<MedicalRecordTbl>)getNewilyCreatedMedicalrecord();
		List<MedicalRecordDTO> medicalList =new ArrayList<MedicalRecordDTO>();
		if(!medicalRecordListTbl.isEmpty()){
			for (MedicalRecordTbl medicalTbl : medicalRecordListTbl) {
				MedicalRecordDTO medDto=getMedicalRecordDetails(medicalTbl);
				
				medicalList.add(medDto);
			}		
		
		
	}
		return medicalList;	
	}
	private List<MedicalRecordTbl> getNewilyCreatedMedicalrecord() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_MEDICAL_RECORD);
		query.setParameter("param1", 0);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	private MedicalRecordDTO getMedicalRecordDetails(MedicalRecordTbl record) {
		MedicalRecordDTO medicalDto = new MedicalRecordDTO();
		medicalDto.setId(record.getId());
		medicalDto.setType(record.getType().toString());		
		if (record.getPatientTbl() != null)
			medicalDto.setPatientId(record.getPatientTbl().getGlobalId());
		if (record.getDoctorTbl() != null)
			medicalDto.setDoctorId(record.getDoctorTbl().getGlobalId());
		if (record.getCaseTbl() != null)
			medicalDto.setCaseId(record.getCaseTbl().getGlobalId());
		medicalDto.setDate(record.getCreatedTime().toString());
		medicalDto.setGlobalId(record.getGlobalId());
		medicalDto.setMedicalRecord(record.getMedicalRecord());

	return medicalDto;
	}
	
	
	@Override
	public List<MedicalRecordDTO> getUpdatedMedicalRecord() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<MedicalRecordDTO>medicalList=new ArrayList<MedicalRecordDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<MedicalRecordTbl>medicalListTbl=(ArrayList<MedicalRecordTbl>)getUpdatedMedicalRecordList(syncTbl.getUploadedTime());

			if(!medicalListTbl.isEmpty()){
				for (MedicalRecordTbl medTbl : medicalListTbl) {
					MedicalRecordDTO medDto=getMedicalRecordDetails(medTbl);
					medicalList.add(medDto);			
				    }
			}
		}
		return medicalList;
	}

	private List<MedicalRecordTbl> getUpdatedMedicalRecordList(Date uploadedTime) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_MEDICAL_RECORD);
		query.setParameter("param1", uploadedTime);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	
	
	
	@Override
	public List<MedicalRecordDTO> getDeletedMedicalRecord() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<MedicalRecordDTO>medicalList=new ArrayList<MedicalRecordDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<MedicalRecordTbl>medicalListTbl=(ArrayList<MedicalRecordTbl>)getDeletedMedicalRecordList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!medicalListTbl.isEmpty()){
				for (MedicalRecordTbl medTbl : medicalListTbl) {
					MedicalRecordDTO mediDto=getMedicalRecordDetails(medTbl);
					medicalList.add(mediDto);			
				    }
			}
		}
		return medicalList;
	}

	private List<MedicalRecordTbl> getDeletedMedicalRecordList(Date lastSyncTime,StatusEnum status) throws PersistenceException {
		
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_MEDICAL_REOCRD);
		query.setParameter("param1", status);
		query.setParameter("param2", lastSyncTime);
		return executeQuery(MedicalRecordTbl.class, query);
	}
	
	/**
	 * update with the global id for newly created doctor 
	 * @param doctorResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void CaseSyncResponse(CaseSyncResponseDTO caseResponse)throws PersistenceException {
		// TODO Auto-generated method stub
		if(caseResponse.getError()==null){
			CaseTbl caseTbl=(CaseTbl)getById(CaseTbl.class,caseResponse.getId());
			if (caseTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.CaseNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(caseResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			caseTbl.setGlobalId(caseResponse.getGlobalId());
			update(caseTbl);
		}
	}

	/**
	 *update the updated time of updated patient if error occur in sync
	 * @param patientResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateCaseSyncResponse(CaseSyncResponseDTO caseResponse)throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(caseResponse.getError()!=null){
			CaseTbl caseTbl=(CaseTbl)getById(CaseTbl.class,caseResponse.getId());
			if (caseTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.CaseNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(caseResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			if(caseResponse.getUpdateDateTime()!=null){
				try {
					caseTbl.setUpdatedTime(df.parse(caseResponse.getUpdateDateTime()));
				} catch (ParseException e) {
					log.error("while updating the case error occured",e);
					e.printStackTrace();
				}
			update(caseTbl);
		}
		}
	}
	
	
	/**
	 *delete the  patient case from local table if error occur in sync
	 * @param caseResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteCaseSyncResponse(CaseSyncResponseDTO caseResponse) throws PersistenceException {
		
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(caseResponse.getError()==null){
			List<MedicalRecordTbl> medicalList=getMedicalList(caseResponse.getId());
			if(!medicalList.isEmpty()){
				for(MedicalRecordTbl medical:medicalList){
					delete(medical);
				}
			}

		}
	}
	
	public List<AnswerTbl> getAnswerList(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_CASE);
		query.setParameter("param1", caseId);
		return executeQuery(AnswerTbl.class, query);
	}	
	
	public List<MedicalRecordTbl> getMedicalList(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEDICAL_RECORD_BY_CASE);
		query.setParameter("param1", caseId);
		return executeQuery(MedicalRecordTbl.class, query);
	}	

	public List<CaseAnswerSetTbl> getAnsSetList(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_ID);
		query.setParameter("param1", caseId);
		return executeQuery(CaseAnswerSetTbl.class, query);
	}	
	@Override
	public List<CaseDTO> getUpdatedCase() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<CaseDTO>caseList=new ArrayList<CaseDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<CaseTbl>caseListTbl=(ArrayList<CaseTbl>)getUpdatedCaseList(syncTbl.getUploadedTime());

			if(!caseListTbl.isEmpty()){
				for (CaseTbl caseTbl : caseListTbl) {
					CaseDTO casedto=getCaseDetail(caseTbl);
					casedto.setActionName(ActionEnum.UPDATE.getDisplayName());
				    caseList.add(casedto);			
				    }
			}
		}
		return caseList;
	}

	private List<CaseTbl> getUpdatedCaseList(Date uploadedTime) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_CASE);
		query.setParameter("param1", uploadedTime);
		return executeQuery(CaseTbl.class, query);
	}
	
	@Override
	public List<CaseDTO> getDeletedCase() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<CaseDTO>caseList=new ArrayList<CaseDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<CaseTbl>caseListTbl=(ArrayList<CaseTbl>)getDeletedCaseList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!caseListTbl.isEmpty()){
				for (CaseTbl caseTbl : caseListTbl) {
					CaseDTO casedto=getCaseDetail(caseTbl);
					casedto.setActionName(ActionEnum.DELETE.toString());
				    caseList.add(casedto);			
				    }
			}
		}
		return caseList;
	}

	private List<CaseTbl> getDeletedCaseList(Date lastSyncTime,StatusEnum status) throws PersistenceException {
		
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_CASE);
		query.setParameter("param1", status);
		//query.setParameter("param2", lastSyncTime);
		return executeQuery(CaseTbl.class, query);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void addMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse)throws PersistenceException {
		// TODO Auto-generated method stub
		if(medResponse.getError()==null){
			MedicalRecordTbl medicalTbl=(MedicalRecordTbl)getById(MedicalRecordTbl.class,medResponse.getId());
			if (medicalTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.MedicalRecordNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(medResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			medicalTbl.setGlobalId(medResponse.getGlobalId());
			update(medicalTbl);
		}
	}
	
	@Override
	@Transactional(readOnly=false)
	public void updateMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse) throws PersistenceException{
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(medResponse.getError()!=null){
			MedicalRecordTbl medicalTbl=(MedicalRecordTbl)getById(MedicalRecordTbl.class,medResponse.getId());
			if (medicalTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.MedicalRecordNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(medResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				medicalTbl.setUpdatedTime(df.parse(medResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("while updating the case error occured",e);
				e.printStackTrace();
			}
			update(medicalTbl);
		}

	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteMedicalSyncResponse(MedicalRecordSyncResponseDTO medResponse)throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(medResponse.getError()==null){
			MedicalRecordTbl medicalTbl=(MedicalRecordTbl)getById(MedicalRecordTbl.class,medResponse.getId());
			if (medicalTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.MedicalRecordNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(medResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			delete(medicalTbl);
		}

	}
	
	@Override
	public ResponseDTO getAnswerSetIdUsingCaseId(int caseId) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		CaseAnswerSetTbl caseAnswerTbl = (CaseAnswerSetTbl)  getAnswerSetId(caseId);
		if (caseAnswerTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}

		response.setId(caseAnswerTbl.getId());
		return response;
	}
	
	private CaseAnswerSetTbl getAnswerSetId(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_ID);
		query.setParameter("param1", caseId);
		return executeUniqueQuery(CaseAnswerSetTbl.class, query);
	}
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}
	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	/**
	 * @return the questionDao
	 */
	public QuestionnaireDao getQuestionDao() {
		return questionDao;
	}
	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(QuestionnaireDao questionDao) {
		this.questionDao = questionDao;
	}
	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}
	
}
