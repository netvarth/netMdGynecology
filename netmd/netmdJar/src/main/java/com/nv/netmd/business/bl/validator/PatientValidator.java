/**
 * PatientValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.validator;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CaseStatusEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.GenderEnum;
import com.nv.netmd.pl.entity.MedicalRecordTypeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.PatientDetail;


/**
 * 
 */
public class PatientValidator  {
	public ErrorDTO validateCreatePatient(PatientDetail patient) {
		ErrorDTO error = new ErrorDTO();
		if (!isValidName(patient.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
//		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
//		
		if (!patient.getEmail().isEmpty()) {
			if (!patient
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
//		if (patient.getPhone() != null && !patient.getPhone().equals("")) {
//			if (!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
//				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
//				error.setDisplayErrMsg(false);
//				return error;
//			}
//		}
//		if (patient.getMobile() != null && !patient.getMobile().equals("")) {
//			if (!patient.getMobile().matches("\\d{10}")) {
//				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
//				error.setDisplayErrMsg(false);
//				return error;
//			}
//		}
	
		return null;
	}

	public ErrorDTO validateUpdatePatient(PatientDetail patient) throws ServiceException {

		ErrorDTO error = new ErrorDTO();
		if (patient.getId() <=0) {
			error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(patient.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		
		if (patient.getEmail() != null && !patient.getEmail().equals("")) {
			if (!patient
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
//		if (patient.getPhone() != null && !patient.getPhone().equals("")) {
//			if (!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
//				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
//				error.setDisplayErrMsg(false);
//				return error;
//			}
//		}
//		if (patient.getMobile() != null && !patient.getMobile().equals("")) {
//			if (!patient.getMobile().matches("\\d{10}")) {
//				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
//				error.setDisplayErrMsg(false);
//				return error;
//			}
//		}

		return null;
	}

	public ErrorDTO validatePatientFromYNW(PatientDetail patient) throws ServiceException {
		
		ErrorDTO error = new ErrorDTO();

		if (patient.getGlobalId() <= 0) {
			error.setErrCode(ErrorCodeEnum.GlobalIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(patient.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(patient.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		GenderEnum gender = GenderEnum.getEnum(patient.getGender());
		StatusEnum statusEnum=StatusEnum.getEnum(patient.getStatus());
		if (patient.getEmail() != null && !patient.getEmail().equals("")) {
			if (!patient
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if (patient.getPhone() != null && !patient.getPhone().equals("")) {
			if (!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (patient.getMobile() != null && !patient.getMobile().equals("")) {
			if (!patient.getMobile().matches("\\d{10}")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		
		return null;
	}
	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}


	public void validateCase(AutoSaveDTO autoSaveDto) {
//		if (!isValidName(autoSaveDto.getCaseName())) {
//			ServiceException se=new ServiceException(ErrorCodeEnum.InValidName);
//			se.isDisplayErrMsg();
//			throw se;
//		}
//		if (autoSaveDto.getPatientId() <=0) {
//			ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
//			se.isDisplayErrMsg();
//			throw se;
//		}
		
	}
	
	public void validateCase(CaseDTO caseDto) throws ServiceException {
		if (!isValidName(caseDto.getCaseName())) {
			ServiceException se=new ServiceException(ErrorCodeEnum.InValidName);
			se.isDisplayErrMsg();
			throw se;
		}
		if (caseDto.getPatientId() <=0) {
			ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
			se.isDisplayErrMsg();
			throw se;
		}
		
	}

	public ErrorDTO validateUdateCase(CaseDTO caseDto) {
		ErrorDTO error = new ErrorDTO();
		
		if (caseDto.getId() <=0) {
			error.setErrCode(ErrorCodeEnum.CaseIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(caseDto.getCaseName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (caseDto.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO validateCreateMedicalRecord(MedicalRecordDTO record) throws ServiceException {
		// validate update
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		MedicalRecordTypeEnum type = MedicalRecordTypeEnum.getEnum(record
				.getType());
		if (record.getCaseId() <=0) {
			error.setErrCode(ErrorCodeEnum.CaseIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (record.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (record.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(record.getMedicalRecord())) {
			error.setErrCode(ErrorCodeEnum.MedicalRecordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	public ErrorDTO validateUpdateMedicalRecord(MedicalRecordDTO record) throws ServiceException {
		// validate update
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		MedicalRecordTypeEnum type = MedicalRecordTypeEnum.getEnum(record
				.getType());
		if (record.getId() <=0) {
			error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (record.getCaseId() <=0) {
			error.setErrCode(ErrorCodeEnum.CaseIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (record.getDoctorId() <=0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (record.getPatientId() <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(record.getMedicalRecord())) {
			error.setErrCode(ErrorCodeEnum.MedicalRecordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	public ErrorDTO validateListCaseByPatient(int patientId) {
		// validate update
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (patientId <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO validateListMedicalRecord(int patientId) {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (patientId <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO validateRecordByCase(int patientId, int caseId) {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (patientId <=0) {
			error.setErrCode(ErrorCodeEnum.PatientIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (caseId <=0) {
			error.setErrCode(ErrorCodeEnum.CaseIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}
}
