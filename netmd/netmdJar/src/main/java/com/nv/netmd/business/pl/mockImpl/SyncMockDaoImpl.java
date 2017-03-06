/**
 * SyncMockDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jun 27, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.mockImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorResponse;
import com.nv.netmd.rs.dto.HeaderDTO;
import com.nv.netmd.rs.dto.NetMdActivationResponseDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.RetrievalAppointmentResponseDTO;
import com.nv.netmd.rs.dto.RetrievalDoctorResponseDTO;
import com.nv.netmd.rs.dto.RetrievalPatientResponseDTO;
import com.nv.netmd.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.SyncDTO;
import com.nv.netmd.rs.dto.SyncResponseDTO;
import com.nv.netmd.sync.pl.dao.SyncDao;

/**
 * 
 */
public class SyncMockDaoImpl implements SyncDao {

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#isLoginEmpty()
	 */
	@Override
	public boolean isLoginEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#createNetMdDetails(com.nv.netmd.rs.dto.NetMdActivationResponseDTO, com.nv.netmd.rs.dto.HeaderDTO)
	 */
	@Override
	public NetMdActivationResponseDTO createNetMdDetails(
			NetMdActivationResponseDTO netMdActivation, HeaderDTO headers) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#getHeader()
	 */
	@Override
	public HeaderDTO getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#getLastSyncTime()
	 */
	@Override
	public String getLastSyncTime() {
		// TODO Auto-generated method stub
		return "2013-06-25 08:38:01.608";
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#getSyncData(com.nv.netmd.rs.dto.SyncDTO)
	 */
	@Override
	public SyncResponseDTO getSyncData(SyncDTO syncDTO) {
		// TODO Auto-generated method stub
		SyncResponseDTO syncResponse = new SyncResponseDTO();
		List<DoctorResponse> doctorResponse = new ArrayList<DoctorResponse>();
		List<ScheduleResponse> scheduleResponse = new ArrayList<ScheduleResponse>();
		List<PatientResponse> patientResponse = new ArrayList<PatientResponse>();
		//List<UserResponse> userResponse = new ArrayList<UserResponse>();
		List<AppointmentResponse> appointmentResponse = new ArrayList<AppointmentResponse>();	
		RetrievalDoctorResponseDTO retrievalDoctorList= new RetrievalDoctorResponseDTO();
		//RetrievalUserResponseDTO retrievalUsersList= new RetrievalUserResponseDTO();
		RetrievalAppointmentResponseDTO retrievalAppointmentList = new RetrievalAppointmentResponseDTO();
		RetrievalPatientResponseDTO retrievalPatientDTO = new RetrievalPatientResponseDTO();
		RetrievalScheduleResponseDTO retrievalScheduleList= new RetrievalScheduleResponseDTO();
		List<RetrieveResultsResponseDTO> retrieveResults= new ArrayList<RetrieveResultsResponseDTO>();
		String lastSynctime="2012-03-33 10:12";
		
		List<DoctorLoginDTO> doctorLogin = new ArrayList<DoctorLoginDTO>();
		DoctorLoginDTO doctorLoginDTO=new DoctorLoginDTO();
		doctorLoginDTO.setDoctorGlobalId(1);
		doctorLoginDTO.setEmail("srr@ho.com");
		doctorLoginDTO.setPassword("asdads");
		doctorLogin.add(doctorLoginDTO);
		List<DoctorDetail> retrieveDoctorsList = new ArrayList<DoctorDetail>();
		DoctorDetail docDetail=new DoctorDetail();
		docDetail.setGlobalId(1);
		docDetail.setFirstName("sreeram");		
		docDetail.setEmail("sreeram.tikk@g.com");
		retrieveDoctorsList.add(docDetail);
		 List<PatientDetail> retrievePatients = new ArrayList<PatientDetail>();
		 PatientDetail patientDetail=new PatientDetail();
		 patientDetail.setGlobalId(1);
		 patientDetail.setFirstName("Jay");
		 patientDetail.setGender("Male");
		 retrievePatients.add(patientDetail);
		 List<ScheduleDetail> retrieveScheduleList= new ArrayList<ScheduleDetail>();
		 ScheduleDetail scheduleDetail=new ScheduleDetail();
		 scheduleDetail.setDoctorGlobalId(1);
		 scheduleDetail.setScheduleStatus("Vacation");
		 retrieveScheduleList.add(scheduleDetail);
		 List<AppointmentDTO> retrieveAppointments = new ArrayList<AppointmentDTO>();
		 AppointmentDTO appointmentDTO=new AppointmentDTO();
		 appointmentDTO.setGlobalId(1);
		 appointmentDTO.setDoctorId(1);
		 retrieveAppointments.add(appointmentDTO);
		
		
		RetrieveResultsResponseDTO resultResponse=new RetrieveResultsResponseDTO();
		resultResponse.setResultGlobalId(1);
		resultResponse.setResult("normal");
		
		retrieveResults.add(resultResponse);
		retrievalDoctorList.setRetrieveDoctorsList(retrieveDoctorsList);
		retrievalAppointmentList.setRetrieveAppointments(retrieveAppointments);
		retrievalPatientDTO.setRetrievePatients(retrievePatients);
		retrievalScheduleList.setRetrieveScheduleList(retrieveScheduleList);
		
		syncResponse.setLastSynctime(lastSynctime);
		syncResponse.setDoctorResponse(doctorResponse);
		syncResponse.setPatientResponse(patientResponse);
		syncResponse.setAppointmentResponse(appointmentResponse);
		syncResponse.setScheduleResponse(scheduleResponse);
		syncResponse.setDoctorLogin(doctorLogin);
		return syncResponse;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#getLocalMacId()
	 */
	@Override
	public String getLocalMacId() {
		// TODO Auto-generated method stub
		return "9922-2882-933";
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#activateNetMd(com.nv.netmd.rs.dto.HeaderDTO)
	 */
	@Override
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#updateSyncTbl(java.lang.String)
	 */
	@Override
	public void updateSyncTbl(String lastSyncTime) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#updateLastUploadedTime(java.util.Date)
	 */
	@Override
	public void updateLastUploadedTime(Date uploadedTime) {
		// TODO Auto-generated method stub

	}




	/* (non-Javadoc)
	 * @see com.nv.netmd.sync.pl.dao.SyncDao#updateNetmdDetails(com.nv.netmd.rs.dto.NetMdDTO)
	 */
	@Override
	public void updateNetmdDetails(NetMdDTO netmd) {
		// TODO Auto-generated method stub
		
	}

}
