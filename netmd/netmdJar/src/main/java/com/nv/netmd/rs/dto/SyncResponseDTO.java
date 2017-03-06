/**
 * @Author Sreeram
*
* Version 1.0 Apr 2, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 * @author Sreeram T G
 */
public class SyncResponseDTO {
	public HeaderResponseDTO headerResponse ;
	private NetMdDTO NetmdResponse;
	private NetMdBranchDTO NetmdBranchResponse;
	private List<DoctorResponse> doctorResponse = new ArrayList<DoctorResponse>();
	private List<ScheduleResponse> scheduleResponse = new ArrayList<ScheduleResponse>();
	private List<PatientResponse> patientResponse = new ArrayList<PatientResponse>();
	private List<UserResponse> userResponse = new ArrayList<UserResponse>();
	private List<AppointmentResponse> appointmentResponse = new ArrayList<AppointmentResponse>();	
	private RetrievalDoctorResponseDTO retrievalDoctorList= new RetrievalDoctorResponseDTO();
	private RetrievalUserResponseDTO retrievalUsersList= new RetrievalUserResponseDTO();
	private RetrievalAppointmentResponseDTO retrievalAppointmentList = new RetrievalAppointmentResponseDTO();
	private RetrievalPatientResponseDTO retrievalPatientDTO = new RetrievalPatientResponseDTO();
	private RetrievalScheduleResponseDTO retrievalScheduleList= new RetrievalScheduleResponseDTO();
	private List<RetrieveResultsResponseDTO> retrieveResults= new ArrayList<RetrieveResultsResponseDTO>();
	private RetrievalAppointmentResponseDTO retrievalAppointmentListForPrimary = new RetrievalAppointmentResponseDTO();
	private List<DoctorLoginDTO> doctorLogin = new ArrayList<DoctorLoginDTO>();
	private List<BillSyncResponseDTO>billResponse=new ArrayList<BillSyncResponseDTO>();
	//private RetrievalBillResponseDTO retrievalBillList= new RetrievalBillResponseDTO();
	private List<CaseSyncResponseDTO> patientCaseResponse=new ArrayList<CaseSyncResponseDTO>();
	private List<MedicalRecordSyncResponseDTO> patientMedicalResponse= new ArrayList<MedicalRecordSyncResponseDTO>();
	//private List<QuestionnaireSyncResponseDTO> netmdQuestionAnswer=new ArrayList<QuestionnaireSyncResponseDTO>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	
	
	/**
	 * @return the patientMedicalResponse
	 */
	public List<MedicalRecordSyncResponseDTO> getPatientMedicalResponse() {
		return patientMedicalResponse;
	}
	/**
	 * @param patientMedicalResponse the patientMedicalResponse to set
	 */
	public void setPatientMedicalResponse(
			List<MedicalRecordSyncResponseDTO> patientMedicalResponse) {
		this.patientMedicalResponse = patientMedicalResponse;
	}
	/**
	 * @return the netmdQuestionAnswer
	 */
//	public List<QuestionnaireSyncResponseDTO> getNetmdQuestionAnswer() {
//		return netmdQuestionAnswer;
//	}
	/**
	 * @param netmdQuestionAnswer the netmdQuestionAnswer to set
	 */
//	public void setNetmdQuestionAnswer(
//			List<QuestionnaireSyncResponseDTO> netmdQuestionAnswer) {
//		this.netmdQuestionAnswer = netmdQuestionAnswer;
//	}
	
	
	/**
	 * @return the userResponse
	 */
	public List<UserResponse> getUserResponse() {
		return userResponse;
	}
	/**
	 * @param userResponse the userResponse to set
	 */
	public void setUserResponse(List<UserResponse> userResponse) {
		this.userResponse = userResponse;
	}
	/**
	 * @return the doctorResponse
	 */
	public List<DoctorResponse> getDoctorResponse() {
		return doctorResponse;
	}
	/**
	 * @param doctorResponse the doctorResponse to set
	 */
	public void setDoctorResponse(List<DoctorResponse> doctorResponse) {
		this.doctorResponse = doctorResponse;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return the patientResponse
	 */
	public List<PatientResponse> getPatientResponse() {
		return patientResponse;
	}
	/**
	 * @param patientResponse the patientResponse to set
	 */
	public void setPatientResponse(List<PatientResponse> patientResponse) {
		this.patientResponse = patientResponse;
	}
	/**
	 * 
	 */
	public SyncResponseDTO() {
		
	}
	
	/**
	 * @param headerResponse
	 * @param doctorResponse
	 * @param scheduleResponse
	 * @param patientResponse
	 * @param userResponse
	 * @param error
	 * @param success
	 */
	public SyncResponseDTO(HeaderResponseDTO headerResponse,
			List<DoctorResponse> doctorResponse,
			List<ScheduleResponse> scheduleResponse,
			List<PatientResponse> patientResponse,
			List<UserResponse> userResponse, ErrorDTO error, boolean success) {
		super();
		this.headerResponse = headerResponse;
		this.doctorResponse = doctorResponse;
		this.scheduleResponse = scheduleResponse;
		this.patientResponse = patientResponse;
		this.userResponse = userResponse;
		this.error = error;
		this.success = success;
	}
	/**
	 * @return the headerResponse
	 */
	public HeaderResponseDTO getHeaderResponse() {
		return headerResponse;
	}
	/**
	 * @param headerResponse the headerResponse to set
	 */
	public void setHeaderResponse(HeaderResponseDTO headerResponse) {
		this.headerResponse = headerResponse;
	}
	/**
	 * @return the netmdResponse
	 */
	public NetMdDTO getNetmdResponse() {
		return NetmdResponse;
	}
	/**
	 * @param netmdResponse the netmdResponse to set
	 */
	public void setNetmdResponse(NetMdDTO netmdResponse) {
		NetmdResponse = netmdResponse;
	}
	/**
	 * @return the netmdBranchResponse
	 */
	public NetMdBranchDTO getNetmdBranchResponse() {
		return NetmdBranchResponse;
	}
	/**
	 * @param netmdBranchResponse the netmdBranchResponse to set
	 */
	public void setNetmdBranchResponse(NetMdBranchDTO netmdBranchResponse) {
		NetmdBranchResponse = netmdBranchResponse;
	}
	/**
	 * @return the scheduleResponse
	 */
	public List<ScheduleResponse> getScheduleResponse() {
		return scheduleResponse;
	}
	/**
	 * @param scheduleResponse the scheduleResponse to set
	 */
	public void setScheduleResponse(List<ScheduleResponse> scheduleResponse) {
		this.scheduleResponse = scheduleResponse;
	}
	/**
	 * @return the appointmentResponse
	 */
	public List<AppointmentResponse> getAppointmentResponse() {
		return appointmentResponse;
	}
	/**
	 * @param appointmentResponse the appointmentResponse to set
	 */
	public void setAppointmentResponse(List<AppointmentResponse> appointmentResponse) {
		this.appointmentResponse = appointmentResponse;
	}
	/**
	 * @return the retrievalDoctorList
	 */
	public RetrievalDoctorResponseDTO getRetrievalDoctorList() {
		return retrievalDoctorList;
	}
	/**
	 * @param retrievalDoctorList the retrievalDoctorList to set
	 */
	public void setRetrievalDoctorList(
			RetrievalDoctorResponseDTO retrievalDoctorList) {
		this.retrievalDoctorList = retrievalDoctorList;
	}
	/**
	 * @return the retrievalUsersList
	 */
	public RetrievalUserResponseDTO getRetrievalUsersList() {
		return retrievalUsersList;
	}
	/**
	 * @param retrievalUsersList the retrievalUsersList to set
	 */
	public void setRetrievalUsersList(RetrievalUserResponseDTO retrievalUsersList) {
		this.retrievalUsersList = retrievalUsersList;
	}
	/**
	 * @return the retrievalAppointmentList
	 */
	public RetrievalAppointmentResponseDTO getRetrievalAppointmentList() {
		return retrievalAppointmentList;
	}
	/**
	 * @param retrievalAppointmentList the retrievalAppointmentList to set
	 */
	public void setRetrievalAppointmentList(
			RetrievalAppointmentResponseDTO retrievalAppointmentList) {
		this.retrievalAppointmentList = retrievalAppointmentList;
	}
	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}
	/**
	 * @param lastSynctime the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
	}
	/**
	 * @return the retrievalPatientDTO
	 */
	public RetrievalPatientResponseDTO getRetrievalPatientDTO() {
		return retrievalPatientDTO;
	}
	/**
	 * @param retrievalPatientDTO the retrievalPatientDTO to set
	 */
	public void setRetrievalPatientDTO(
			RetrievalPatientResponseDTO retrievalPatientDTO) {
		this.retrievalPatientDTO = retrievalPatientDTO;
	}
	/**
	 * @return the retrievalScheduleList
	 */
	public RetrievalScheduleResponseDTO getRetrievalScheduleList() {
		return retrievalScheduleList;
	}
	/**
	 * @param retrievalScheduleList the retrievalScheduleList to set
	 */
	public void setRetrievalScheduleList(
			RetrievalScheduleResponseDTO retrievalScheduleList) {
		this.retrievalScheduleList = retrievalScheduleList;
	}
	
	/**
	 * @return the doctorLogin
	 */
	public List<DoctorLoginDTO> getDoctorLogin() {
		return doctorLogin;
	}
	/**
	 * @param doctorLogin the doctorLogin to set
	 */
	public void setDoctorLogin(List<DoctorLoginDTO> doctorLogin) {
		this.doctorLogin = doctorLogin;
	}
	/**
	 * @return the retrieveResults
	 */
	public List<RetrieveResultsResponseDTO> getRetrieveResults() {
		return retrieveResults;
	}
	/**
	 * @param retrieveResults the retrieveResults to set
	 */
	public void setRetrieveResults(List<RetrieveResultsResponseDTO> retrieveResults) {
		this.retrieveResults = retrieveResults;
	}
	/**
	 * @return the retrievalAppointmentListForPrimary
	 */
	public RetrievalAppointmentResponseDTO getRetrievalAppointmentListForPrimary() {
		return retrievalAppointmentListForPrimary;
	}
	/**
	 * @param retrievalAppointmentListForPrimary the retrievalAppointmentListForPrimary to set
	 */
	public void setRetrievalAppointmentListForPrimary(
			RetrievalAppointmentResponseDTO retrievalAppointmentListForPrimary) {
		this.retrievalAppointmentListForPrimary = retrievalAppointmentListForPrimary;
	}
	/**
	 * @return the billResponse
	 */
	public List<BillSyncResponseDTO> getBillResponse() {
		return billResponse;
	}
	/**
	 * @param billResponse the billResponse to set
	 */
	public void setBillResponse(List<BillSyncResponseDTO> billResponse) {
		this.billResponse = billResponse;
	}
//	/**
//	 * @return the retrievalBillList
//	 */
//	public RetrievalBillResponseDTO getRetrievalBillList() {
//		return retrievalBillList;
//	}
//	/**
//	 * @param retrievalBillList the retrievalBillList to set
//	 */
//	public void setRetrievalBillList(RetrievalBillResponseDTO retrievalBillList) {
//		this.retrievalBillList = retrievalBillList;
//	}
	/**
	 * @return the patientCaseResponse
	 */
	public List<CaseSyncResponseDTO> getPatientCaseResponse() {
		return patientCaseResponse;
	}
	/**
	 * @param patientCaseResponse the patientCaseResponse to set
	 */
	public void setPatientCaseResponse(List<CaseSyncResponseDTO> patientCaseResponse) {
		this.patientCaseResponse = patientCaseResponse;
	}
	
	

}
