/**
 * SyncServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.sync.bl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmd.billing.pl.dao.BillDao;
import com.nv.netmd.business.bl.service.AppointmentService;
import com.nv.netmd.business.bl.service.DoctorService;
import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.business.bl.service.ResultService;
import com.nv.netmd.business.bl.service.ScheduleService;
import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.business.pl.dao.DoctorDao;
import com.nv.netmd.business.pl.dao.PatientDao;
import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentDetailsDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.BillSummaryDTO;
import com.nv.netmd.rs.dto.BillSyncResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.CaseSyncResponseDTO;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorResponse;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.HeaderDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.MedicalRecordSyncResponseDTO;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.NetMdAnswerSetDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PassPhraseDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionnaireSyncResponseDTO;
import com.nv.netmd.rs.dto.RetrievalBillResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.SyncDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;
import com.nv.netmd.rs.dto.ViewQuestionAnswerDTO;
import com.nv.netmd.sync.bl.service.SyncService;
import com.nv.netmd.sync.bl.validator.SyncValidator;
import com.nv.netmd.sync.pl.dao.SyncDao;




/**
 * 
 */
public class SyncServiceImpl implements SyncService {
	private SyncDao syncDao;
	private DoctorDao doctorDao;
	private SyncValidator syncValidator;
	private PatientDao patientDao;
	private ScheduleDao scheduleDao;
	private AppointmentDao appointmentDao;
	private DoctorService doctorService;
	private AppointmentService appointmentService;
	private PatientService patientService;
	private ScheduleService scheduleService;
	private ResultService resultService;
	private BillDao billDao;
	private QuestionnaireDao questionDao;
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
	 * check the login table 
	 * @throws PersistenceException 
	 */
	@Override
	public boolean isLoginEmpty() throws ServiceException {

		try {
			if(syncDao.isLoginEmpty()==false){
				return false;
			}
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return true;
	}

	/**
	 * @return the syncDao
	 */
	public SyncDao getSyncDao() {
		return syncDao;
	}
	/**
	 * @param syncDao the syncDao to set
	 */
	public void setSyncDao(SyncDao syncDao) {
		this.syncDao = syncDao;
	}

	/**
	 * @return the syncValidator
	 */
	public SyncValidator getSyncValidator() {
		return syncValidator;
	}

	/**
	 * @param syncValidator the syncValidator to set
	 */
	public void setSyncValidator(SyncValidator syncValidator) {
		this.syncValidator = syncValidator;
	}

	/**
	 * Activation process of netMd 
	 * @param passPhrase
	 * @throws ServiceException 
	 */
	@Override
	public NetMdActivationResponseDTO activateNetMd(PassPhraseDTO passPhrase) throws ServiceException {
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		//ResponseDTO response1 = new ResponseDTO();
		syncValidator.validatePassPhrase(passPhrase);		

		HeaderDTO header = new HeaderDTO();
		String macId;
		try {
			macId = syncDao.getLocalMacId();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}  // get the mac id of our local system		
		header.setMacId(macId.trim());
		header.setPassPhrase(passPhrase.getPassPhrase().trim());
		try {
			response=syncDao.activateNetMd(header);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(response!=null && response.getError()==null){
			//create netmd branch details
			try {
				syncDao.createNetMdDetails(response,header);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}

			if(!response.getRetrievePatients().isEmpty()){
				//patient call
				List<PatientResponse>patientResponseList=syncPatients(response.getRetrievePatients());
			}
			if(!response.getRetrieveDoctorsList().isEmpty()){
				List<DoctorResponse> retrieveCreatedDoctors =syncDoctors(response.getRetrieveDoctorsList());
			}
			if(!response.getRetrieveScheduleList().isEmpty()){
				List<ScheduleResponse>retrieveScheduleList=syncSchedule(response.getRetrieveScheduleList());
			}
			if(!response.getRetrieveAppointments().isEmpty()){
				List<AppointmentResponse>appointmentResponseList = syncAppointments(response.getRetrieveAppointments());			
			}

		}
		return response;
	}


	/** 
	 * Check whether the device already registered with YNW.If yes ask  them to install it again.
	 * @param passPhrase
	 * @return NetMdResponseDTO
	 */
	//	@Override
	//	public NetMdResponseDTO getMacStatus(PassPhraseDTO passPhrase) {
	//		// TODO Auto-generated method stub
	//		NetMdResponseDTO response=new NetMdResponseDTO();
	//		syncValidator.validatePassPhrase(passPhrase);
	//		response = syncDao.getMacStatus(passPhrase);
	//		if(response.getError()!=null){
	//			return response;
	//		}		
	//		response.setError(null);
	//		response.setSuccess(true);
	//		return response;
	//	}

	/**
	 * get data for synchronization with portal
	 * @return SyncResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public SyncResponseDTO getSyncData() throws ServiceException {
		// TODO Auto-generated method stub
		SyncResponseDTO syncResponseDTO=new SyncResponseDTO();
		SyncDTO syncDTO=new SyncDTO();
		Date date=new Date();
		String lastSyncTime;
		try {
			lastSyncTime = getLasySyncTime();
		} catch (PersistenceException e2) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e2);	
		}		
		HeaderDTO headerDTO;
		try {
			headerDTO = getHeader();
		} catch (PersistenceException e2) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e2);	
		}
		syncDTO.setHeader(headerDTO);
		syncDTO.setLastSyncTime(lastSyncTime);
		List<DoctorDetail> newdoctor= getNewDoctorList();
		syncDTO.setNewDoctorList(newdoctor);
		List<DoctorDetail> updatedDoctor= getUpdatedDoctorList();
		syncDTO.setUpdateDoctorList(updatedDoctor);
		List<DoctorDetail> deletedDoctor= getDeletedDoctorList();
		syncDTO.setDeleteDoctorList(deletedDoctor);
		List<PatientDetail> newPatients=getNewPatientList();
		syncDTO.setNewPatientList(newPatients);
		List<PatientDetail> updatedPatients=getUpdatedPatientList();
		syncDTO.setUpdatePatientList(updatedPatients);
		List<PatientDetail> deletedPatients=getDeletedPatientList();
		syncDTO.setDeletedPatientList(deletedPatients);		
		List<ScheduleDetail> newSchedule=getNewScheduleList();
		syncDTO.setNewScheduleList(newSchedule);
		List<ScheduleDetail> updatedSchedule=getUpdatedScheduleList();
		syncDTO.setUpdateScheduleList(updatedSchedule);
		List<ScheduleDetail> deletedSchedule=getDeletedScheduleList();
		syncDTO.setDeleteScheduleList(deletedSchedule);
		List<AppointmentDetailsDTO> newAppointment=getNewAppointmentList();
		syncDTO.setNewAppointmentList(newAppointment);
		List<AppointmentDetailsDTO> updatedAppointment=getUpdatedAppointmentList();
		syncDTO.setUpdatedAppointmentList(updatedAppointment);
		List<AppointmentDetailsDTO> deletedAppointment=getDeletedAppointmentList();
		syncDTO.setDeletedAppointmentList(deletedAppointment);

		List<BillSummaryDTO>newBill=getNewBillList();
		syncDTO.setNewBillList(newBill);
		List<BillSummaryDTO>updatedBill=getUpdatedBillList();
		syncDTO.setUpdateBillList(updatedBill);

		List<CaseDTO>newCaseList=getCaseList();
		syncDTO.setNewCaseList(newCaseList);
		List<CaseDTO>updateCaseList=getUpdatedCaseList();
		syncDTO.setUpdateCaseList(updateCaseList);
		List<CaseDTO>deleteCaseList=getDeletedCaseList();
		syncDTO.setDeleteCaseList(deleteCaseList);

		//	    List<QuestionAnswerDTO>newQuestionnaireList=getNewQuestionnaireList();
		//		syncDTO.setNewNetmdQuestionnaireList(newQuestionnaireList);
		//		List<QuestionAnswerDTO>updateQuestionnaireList=getUpdatedQuestionnaireList();
		//		syncDTO.setUpdateNetmdQuestionnaireList(updateQuestionnaireList);

		//	    List<NetMdAnswerSetDTO>newQuestionnaireList=getQuestionnaireList();
		//	  	syncDTO.setNetmdQuestionnaireList(newQuestionnaireList);

		List<MedicalRecordDTO>newMedicalRecordList=getNewMedicalrecordList();
		syncDTO.setNewMedicalRecordList(newMedicalRecordList);
		List<MedicalRecordDTO>updateMedicalRecordList=getUpdateMedicalrecordList();
		syncDTO.setUpdateMedicalRecordList(updateMedicalRecordList);
		List<MedicalRecordDTO>deleteMedicalRecordList=getDeleteMedicalrecordList();
		syncDTO.setDeleteMedicalRecordList(deleteMedicalRecordList);

		try {
			syncDao.updateLastUploadedTime(date);
		} catch (PersistenceException e1) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
		}
		//		try {
		//			syncResponseDTO=syncDao.getSyncData(syncDTO);
		//		} catch (PersistenceException e1) {
		//			throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
		//		}
		if(syncResponseDTO!=null && syncResponseDTO.getError()==null){
			if(syncResponseDTO.getNetmdResponse()!=null){
				NetMdDTO netmd=syncResponseDTO.getNetmdResponse();
				try {
					syncDao.updateNetmdDetails(netmd);
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}

			}
			if(!syncResponseDTO.getDoctorResponse().isEmpty()){
				List<DoctorResponse> doctorResponseList=syncResponseDTO.getDoctorResponse();
				for (DoctorResponse doctorResponse : doctorResponseList) {
					try{
						if( doctorResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							doctorDao.addDoctorSyncResponse(doctorResponse);
						}
						if( doctorResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							doctorDao.updateDoctorSyncResponse(doctorResponse);
						}
						if( doctorResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							doctorDao.deleteDoctorSyncResponse(doctorResponse);
						}

					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			if(!syncResponseDTO.getPatientCaseResponse().isEmpty()){
				List<CaseSyncResponseDTO> caseResponseList=syncResponseDTO.getPatientCaseResponse();
				for (CaseSyncResponseDTO caseResponse : caseResponseList) {
					try{
						if( caseResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							patientDao.CaseSyncResponse(caseResponse);
						}
						if( caseResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							patientDao.updateCaseSyncResponse(caseResponse);
						}
						if( caseResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							patientDao.deleteCaseSyncResponse(caseResponse);
						}

					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			if(!syncResponseDTO.getPatientMedicalResponse().isEmpty()){
				List<MedicalRecordSyncResponseDTO> medicalResponseList=syncResponseDTO.getPatientMedicalResponse();
				for (MedicalRecordSyncResponseDTO medResponse : medicalResponseList) {
					try{
						if( medResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							patientDao.addMedicalSyncResponse(medResponse);
						}
						if( medResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							patientDao.updateMedicalSyncResponse(medResponse);
						}
						if( medResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							patientDao.deleteMedicalSyncResponse(medResponse);
						}

					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			//			if(!syncResponseDTO.getNetmdQuestionAnswer().isEmpty()){
			//				List<QuestionnaireSyncResponseDTO> questionnaireResponseList=syncResponseDTO.getNetmdQuestionAnswer();
			//				for (QuestionnaireSyncResponseDTO questionnaireResponse : questionnaireResponseList) {
			//					try{
			//						if( questionnaireResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
			//							questionDao.addQuestionnaireSyncResponse(questionnaireResponse);
			//						}
			//						if( questionnaireResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
			//							questionDao.updateQuestionnaireSyncResponse(questionnaireResponse);
			//						}
			//						if( questionnaireResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
			//						patientDao.deleteCaseSyncResponse(questionnaireResponse);
			//						}
			//
			//					}catch (Exception e) {
			//						// TODO: handle exception
			//						e.printStackTrace();
			//					}
			//				}
			//			}

			if(!syncResponseDTO.getPatientResponse().isEmpty()){
				List<PatientResponse>patientResponseList=syncResponseDTO.getPatientResponse();
				for (PatientResponse patientResponse : patientResponseList) {
					try{
						if( patientResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							patientDao.addPatientSyncResponse(patientResponse);
						}
						if( patientResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							patientDao.updatePatientSyncResponse(patientResponse);
						}
						if( patientResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							patientDao.deletePatientSyncResponse(patientResponse);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			if(!syncResponseDTO.getScheduleResponse().isEmpty()){
				List<ScheduleResponse>scheduleResponseList=syncResponseDTO.getScheduleResponse();
				for (ScheduleResponse scheduleResponse : scheduleResponseList) {
					try{
						if( scheduleResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							scheduleDao.addScheduleSyncResponse(scheduleResponse);
						}
						if( scheduleResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							scheduleDao.updateScheduleSyncResponse(scheduleResponse);
						}
						if( scheduleResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							scheduleDao.deleteScheduleSyncResponse(scheduleResponse);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}	
			if(!syncResponseDTO.getAppointmentResponse().isEmpty()){
				List<AppointmentResponse>appointmentResponseList=syncResponseDTO.getAppointmentResponse();
				for (AppointmentResponse appointmentResponse : appointmentResponseList) {
					try{
						if( appointmentResponse.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							appointmentDao.addAppointmentSyncResponse(appointmentResponse);
						}
						if( appointmentResponse.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							appointmentDao.updateAppointmentSyncResponse(appointmentResponse);
						}
						if( appointmentResponse.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							appointmentDao.deleteAppointmentSyncResponse(appointmentResponse);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}


			if(!syncResponseDTO.getBillResponse().isEmpty()){
				List<BillSyncResponseDTO> billResponseList=syncResponseDTO.getBillResponse();
				for (BillSyncResponseDTO billSyncResponseDTO : billResponseList) {
					try{
						if( billSyncResponseDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							billDao.addBillSyncResponse(billSyncResponseDTO);
						}
						if( billSyncResponseDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							billDao.updateBillSyncResponse(billSyncResponseDTO);
						}
						//						if( billSyncResponseDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
						//							doctorDao.deleteDoctorSyncResponse(billSyncResponseDTO);
						//						}

					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			if(syncResponseDTO.getRetrievalDoctorList()!=null){
				//getRespose(syncResponseDTO.getRetrievalAppointmentList().getRetrieveCreatedAppointments(),syncResponseDTO.getRetrievalAppointmentList().getRetrieveUpdatedAppointments(),syncResponseDTO.getRetrievalAppointmentList().getRetrieveDeletedAppointments());
				List<DoctorResponse> retrieveCreatedDoctors =syncDoctors(syncResponseDTO.getRetrievalDoctorList().getRetrieveDoctorsList());

			}
			if(syncResponseDTO.getRetrievalPatientDTO()!=null){
				List<PatientResponse>patientResponseList=syncPatients(syncResponseDTO.getRetrievalPatientDTO().getRetrievePatients());
			}
			if(syncResponseDTO.getRetrievalScheduleList()!=null){
				List<ScheduleResponse>retrieveScheduleList=syncSchedule(syncResponseDTO.getRetrievalScheduleList().getRetrieveScheduleList());
			}
			if(syncResponseDTO.getRetrievalAppointmentList()!=null){
				List<AppointmentResponse>appointmentResponseList = syncAppointments(syncResponseDTO.getRetrievalAppointmentList().getRetrieveAppointments());			

			}
			if(syncResponseDTO.getRetrievalAppointmentListForPrimary()!=null){
				List<AppointmentResponse>appointmentListForPrimaryResponseList = syncAppointments(syncResponseDTO.getRetrievalAppointmentListForPrimary().getRetrieveAppointments());			

			}
			if(!syncResponseDTO.getRetrieveResults().isEmpty()){
				//List<RetrieveResultsResponseDTO> resultDTOList=syncResult(syncResponseDTO.getRetrieveResults());
			}
			//			if(syncResponseDTO.getRetrievalBillList()!=null){
			//				RetrievalBillResponseDTO retrievalBillList= new RetrievalBillResponseDTO();
			//			}
			if(!syncResponseDTO.getDoctorLogin().isEmpty()){
				List<DoctorLoginDTO> doctorLoginList=syncResponseDTO.getDoctorLogin();
				for (DoctorLoginDTO doctorLoginDTO : doctorLoginList) {
					try{
						doctorService.updateDoctorPassword(doctorLoginDTO);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(syncResponseDTO.getLastSynctime()!=null){
				ErrorDTO error = syncValidator.validateLastSyncTime(syncResponseDTO.getLastSynctime());
				if (error != null) {				
					return null;

				}
				try {
					syncDao.updateSyncTbl(syncResponseDTO.getLastSynctime());
				} catch (PersistenceException e) {
					throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
				}
			}

		}
		return null;
	}
	//	private void getAppointmentRespose(List<AppointmentDTO> newAppointments, List<AppointmentDTO> updatedAppointments, List<AppointmentDTO> deletedAppointments){
	//		List<AppointmentResponse> newAppointmentResponseList = syncNewAppointments(
	//				newAppointments);
	//		List<AppointmentResponse> updatedAppointmentResponseList = syncUpdatedAppointments(
	//				updatedAppointments);
	//		List<AppointmentResponse> deletedAppointmentResponseList = syncDeletedAppointments(
	//				deletedAppointments);
	//	}

	private List<MedicalRecordDTO> getDeleteMedicalrecordList() throws ServiceException {
		List<MedicalRecordDTO> response;
		try {
			response = patientDao.getDeletedMedicalRecord();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	private List<MedicalRecordDTO> getUpdateMedicalrecordList()  throws ServiceException{
		List<MedicalRecordDTO> response;
		try {
			response = patientDao.getUpdatedMedicalRecord();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	private List<MedicalRecordDTO> getNewMedicalrecordList()  throws ServiceException{
		List<MedicalRecordDTO> response;
		try {
			response = patientDao.getNewMedicalRecord();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	private List<CaseDTO> getDeletedCaseList() throws ServiceException {
		List<CaseDTO> response;
		try {
			response = patientDao.getDeletedCase();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	private List<CaseDTO> getUpdatedCaseList()  throws ServiceException{
		List<CaseDTO> response;
		try {
			response = patientDao.getUpdatedCase();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	private List<CaseDTO> getCaseList() throws ServiceException {
		List<CaseDTO> response;
		try {
			response = patientDao.getNewCase();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}
	//	private List<NetMdAnswerSetDTO> getQuestionnaireList() {
	//		List<NetMdAnswerSetDTO>response=questionDao.getQuestionnaireBundle();
	//		return response;
	//	}
	//	private List<QuestionAnswerDTO> getNewQuestionnaireList() {
	//		List<QuestionAnswerDTO>response=questionDao.getNewQuestionnaire();
	//		return response;
	//	}
	//	
	//	private List<QuestionAnswerDTO> getUpdatedQuestionnaireList() {
	//		List<QuestionAnswerDTO>response=questionDao.getUpdatedQuestionnaire();
	//		return response;
	//	}
	/**
	 * get patient from YNW
	 * @param patients
	 * @return List<PatientResponse>
	 */
	private List<PatientResponse>syncPatients( List<PatientDetail> patients){
		List<PatientResponse> patientResponseList = new ArrayList<PatientResponse>();
		//System.out.println("patient Array size"+patients.size());
		for (PatientDetail patientDetail : patients) {
			try {								
				PatientResponse response = patientService.patientFromYNW(patientDetail);
				//				if(response.getError()!=null){
				//				System.out.println(response.getError());
				//				System.out.println(response.getError().getErrCode());
				//				}
				patientResponseList.add(response);
			} catch (ServiceException se) {
				se.printStackTrace();
				if(se.getError()!=null){
					System.out.println(se.getError());
					System.out.println(se.getError().getErrMsg());
				}
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				PatientResponse response = new PatientResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(patientDetail.getId());				
				patientResponseList.add(response);
			}
		}
		return patientResponseList;
	}
	/**
	 * get doctor from YNW
	 * @param doctorsList
	 * @return List<DoctorResponse>
	 */
	public List<DoctorResponse>syncDoctors(List<DoctorDetail> doctorsList){
		List<DoctorResponse> doctorResponseList = new ArrayList<DoctorResponse>();
		//System.out.println("doctor Array size"+doctorsList.size());
		for (DoctorDetail doctorDetail : doctorsList) {
			try {	

				DoctorResponse response = doctorService.doctorFromYNW(doctorDetail);
				if(response.getError()!=null){
					System.out.println(response.getError());
					System.out.println(response.getError().getErrCode());
				}
				doctorResponseList.add(response);
			} catch (ServiceException se) {
				se.printStackTrace();
				if(se.getError()!=null){
					System.out.println(se.getError());
					System.out.println(se.getError().getErrMsg());
				}
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				DoctorResponse response = new DoctorResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(doctorDetail.getId());				
				doctorResponseList.add(response);
			}

		}
		return doctorResponseList;
	}

	/**
	 * get new appointments from YNW and save in to local netmd
	 * @param newAppointmentList
	 * @return List<AppointmentResponse>
	 */
	private List<AppointmentResponse> syncAppointments(List<AppointmentDTO> appointmentList) {
		List<AppointmentResponse> appointmentResponseList = new ArrayList<AppointmentResponse>();
		for (AppointmentDTO appointment : appointmentList) {
			try {								
				AppointmentResponse response = appointmentService.appointmentFromYNW(appointment);
				if(response.getError()!=null){
					System.out.println(response.getError());
					System.out.println(response.getError().getErrCode());
				}
				appointmentResponseList.add(response);
			} catch (ServiceException se) {
				se.printStackTrace();
				if(se.getError()!=null){
					System.out.println(se.getError());
					System.out.println(se.getError().getErrMsg());
				}
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				AppointmentResponse response = new AppointmentResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(appointment.getId());				
				appointmentResponseList.add(response);
			}
		}
		return appointmentResponseList;
	}
	/**
	 * get schedules from YNW
	 * @param scheduleList
	 * @return List<ScheduleResponse>
	 */
	private List<ScheduleResponse> syncSchedule(List<ScheduleDetail>scheduleList){
		List<ScheduleResponse> scheduleResponseList = new ArrayList<ScheduleResponse>();
		for (ScheduleDetail schedule : scheduleList) {
			try {								
				ScheduleResponse response = scheduleService.scheduleFromYNW(schedule);
				if(response.getError()!=null){
					System.out.println(response.getError());
					System.out.println(response.getError().getErrCode());
				}
				scheduleResponseList.add(response);
			} catch (ServiceException se) {
				se.printStackTrace();
				if(se.getError()!=null){
					System.out.println(se.getError());
					System.out.println(se.getError().getErrMsg());
				}
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				ScheduleResponse response = new ScheduleResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(schedule.getId());				
				scheduleResponseList.add(response);
			}
		}
		return scheduleResponseList;
	}
	private List<RetrieveResultsResponseDTO>syncResult(List<RetrieveResultsResponseDTO> resultList){
		List<RetrieveResultsResponseDTO> resultResponeList=new ArrayList<RetrieveResultsResponseDTO>();
		for (RetrieveResultsResponseDTO result : resultList) {
			try {								
				RetrieveResultsResponseDTO response = resultService.resultFromYNW(result);

				resultResponeList.add(response);
			} catch (ServiceException se) {
				se.printStackTrace();
				if(se.getError()!=null){
					System.out.println(se.getError());
					System.out.println(se.getError().getErrMsg());
				}
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				RetrieveResultsResponseDTO response = new RetrieveResultsResponseDTO();
				response.setError(error);
				response.setSuccess(false);						
				resultResponeList.add(response);
			}
		}
		return resultResponeList;
	}
	public  HeaderDTO getHeader() throws PersistenceException{
		//get the header details 
		HeaderDTO headerDto=syncDao.getHeader();
		return headerDto;
	}
	public String getLasySyncTime() throws PersistenceException{
		//get last synchronized time
		String lastSyncTime=syncDao.getLastSyncTime();
		return lastSyncTime;
	}
	public List<DoctorDetail> getNewDoctorList() throws ServiceException{
		//get newly created doctors
		List<DoctorDetail> response;
		try {
			response = doctorDao.getNewDoctor();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<DoctorDetail> getUpdatedDoctorList()throws ServiceException{
		//get updated doctors
		List<DoctorDetail> response;
		try {
			response = doctorDao.getUpdatedDoctor();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<DoctorDetail> getDeletedDoctorList()throws ServiceException{
		//get the deleted or deactivated doctors
		List<DoctorDetail> response;
		try {
			response = doctorDao.getDeletedDoctor();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	public List<PatientDetail> getNewPatientList()throws ServiceException{
		//get newly created patients
		List<PatientDetail> response;
		try {
			response = patientDao.getNewPatient();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<PatientDetail> getUpdatedPatientList()throws ServiceException{
		//get updated patient
		List<PatientDetail> response;
		try {
			response = patientDao.getUpdatedPatient();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<PatientDetail> getDeletedPatientList() throws ServiceException{
		//get the deleted or deactivated patient
		List<PatientDetail> response;
		try {
			response = patientDao.getDeletedPatient();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);
		}
		return response;
	}
	public List<ScheduleDetail> getNewScheduleList()throws ServiceException{
		//get new schedule list
		List<ScheduleDetail> response;
		try {
			response = scheduleDao.getNewSchedule();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<ScheduleDetail> getUpdatedScheduleList()throws ServiceException{
		//get new schedule list
		List<ScheduleDetail> response;
		try {
			response = scheduleDao.getUpdatedSchedule();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	public List<ScheduleDetail> getDeletedScheduleList()throws ServiceException{
		//get new schedule list
		List<ScheduleDetail> response;
		try {
			response = scheduleDao.getDeletedSchedule();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	public List<AppointmentDetailsDTO> getNewAppointmentList()throws ServiceException{
		//		get new appointment list
		List<AppointmentDetailsDTO> response;
		try {
			response = appointmentDao.getNewAppointments();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}
	public List<AppointmentDetailsDTO> getUpdatedAppointmentList()throws ServiceException{
		//		get updated appointment list
		List<AppointmentDetailsDTO> response;
		try {
			response = appointmentDao.getUpdatedAppointments();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}
	public List<AppointmentDetailsDTO> getDeletedAppointmentList()throws ServiceException{
		//		get deleted appointment list
		List<AppointmentDetailsDTO> response;
		try {
			response = appointmentDao.getDeletedAppointments();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * @return List<BillSummaryDTO>
	 */
	public List<BillSummaryDTO> getNewBillList()throws ServiceException{
		//		get new bill list

		try {
			return billDao.getNewBills();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}

	}
	/**
	 * @return List<BillSummaryDTO>
	 */
	public List<BillSummaryDTO> getUpdatedBillList()throws ServiceException{
		//		get new bill list

		try {
			return billDao.getUpdatedBills();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}

	}
	/**
	 * @return the doctorDao
	 */
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	/**
	 * @param doctorDao the doctorDao to set
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	/**
	 * @return the patientDao
	 */
	public PatientDao getPatientDao() {
		return patientDao;
	}

	/**
	 * @param patientDao the patientDao to set
	 */
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	/**
	 * @return the scheduleDao
	 */
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao the scheduleDao to set
	 */
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/**
	 * @return the appointmentDao
	 */
	public AppointmentDao getAppointmentDao() {
		return appointmentDao;
	}

	/**
	 * @param appointmentDao the appointmentDao to set
	 */
	public void setAppointmentDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	/**
	 * @return the appointmentService
	 */
	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	/**
	 * @param appointmentService the appointmentService to set
	 */
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	/**
	 * @return the doctorService
	 */
	public DoctorService getDoctorService() {
		return doctorService;
	}

	/**
	 * @param doctorService the doctorService to set
	 */
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * @return the patientService
	 */
	public PatientService getPatientService() {
		return patientService;
	}

	/**
	 * @param patientService the patientService to set
	 */
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	/**
	 * @return the scheduleService
	 */
	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService the scheduleService to set
	 */
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @return the resultService
	 */
	public ResultService getResultService() {
		return resultService;
	}

	/**
	 * @param resultService the resultService to set
	 */
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	/**
	 * @return the billDao
	 */
	public BillDao getBillDao() {
		return billDao;
	}

	/**
	 * @param billDao the billDao to set
	 */
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}


}
