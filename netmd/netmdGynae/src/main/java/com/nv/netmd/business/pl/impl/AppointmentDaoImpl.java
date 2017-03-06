/**
 * AppointmentDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.AppointmentStatusEnum;
import com.nv.netmd.pl.entity.DoctorScheduleTbl;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.entity.PatientAppointmentTbl;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.ScheduleStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentDetailsDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;



/**
 * 
 */
public class AppointmentDaoImpl extends GenericDaoHibernateImpl implements
AppointmentDao {
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(AppointmentDaoImpl.class);

	/**
	 * create an appointment
	 * 
	 * @param AppointmentDTO
	 * @return AppointmentResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponseDTO create(AppointmentDTO appointment) throws PersistenceException {
		// TODO Auto-generated method stub
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		DoctorScheduleTbl schedule = new DoctorScheduleTbl();
		int test = 0;
		PatientAppointmentTbl appointmentTbl = new PatientAppointmentTbl();
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class,
				appointment.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(patientTbl.getGlobalId()==0){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientGlobalIdNull);

			se.setDisplayErrMsg(false);
			throw se;
		}
		appointmentTbl.setPatientTbl(patientTbl);
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class,
				appointment.getDoctorId());
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getDoctorId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(doctorTbl.getGlobalId()==0){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorGlobalIdNull);

			se.setDisplayErrMsg(false);
			throw se;
		}
		appointmentTbl.setDoctorTbl(doctorTbl);
		DoctorScheduleTbl scheduleTbl = (DoctorScheduleTbl) getById(
				DoctorScheduleTbl.class, appointment.getScheduleId());
		if (scheduleTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getScheduleId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(scheduleTbl.getGlobalId()==0){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ScheduleGlobalIdNull);

			se.setDisplayErrMsg(false);
			throw se;
		}
		appointmentTbl.setDoctorScheduleTbl(scheduleTbl);
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		Date startsDate;
		Date startTime;
		try {
			startsDate = df.parse(appointment.getStartDate());
			startTime = df1.parse(appointment.getStartTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> doctorScheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleList(
				appointment.getDoctorId(), startsDate);
		if (doctorScheduleList.isEmpty()) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ScheduleNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for (DoctorScheduleTbl doctorScheduleTbl : doctorScheduleList) {
			if (!startTime.before(doctorScheduleTbl.getStartingTime())) {
				// System.out.println("End Time"+doctorScheduleTbl.getEndingTime());
				if (!startTime.after(doctorScheduleTbl.getEndingTime())) {
					if (doctorScheduleTbl.getScheduleStatus()!= null
							&& !doctorScheduleTbl.getScheduleStatus().equals(ScheduleStatusEnum.VACATION)) {
						test++;
						schedule = doctorScheduleTbl;
					}
				}
			}
		}
		if (test == 0) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidSchedule);
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientAppointmentTbl patientAppointmentTbl = (PatientAppointmentTbl) getAppointment(
				appointment.getDoctorId(), startsDate, startTime);

		if (patientAppointmentTbl != null) {

			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.AppointmentAlreadyExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setDate(startsDate);
		appointmentTbl.setStartingTime(startTime);
		appointmentTbl.setDoctorScheduleTbl(schedule);
		appointmentTbl.setAppointmentStatus(AppointmentStatusEnum.Waiting_for_approval);
		appointmentTbl.setStatus(StatusEnum.Active);
		Date currentTime=new Date();
		appointmentTbl.setCreatedTime(currentTime);
		appointmentTbl.setUpdatedTime(currentTime);
		save(appointmentTbl);
		response.setStartTime(df1.format(appointmentTbl.getStartingTime()));
		response.setError(null);
		response.setSuccess(true);
		response.setId(appointmentTbl.getId());
		response.setPatientFirstName(patientTbl.getFirstName());
		response.setPatientLastName(patientTbl.getLastName());
		return response;

	}

	/**
	 * create the appointments from YNW
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponse appointmentFromYNW(AppointmentDTO appointment)throws PersistenceException{
		AppointmentResponse response = new AppointmentResponse();
		DoctorScheduleTbl schedule = new DoctorScheduleTbl();
		int test = 0;

		PatientTbl patientTbl = (PatientTbl) getPatientByGlobalId(appointment.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}


		DoctorTbl doctorTbl = (DoctorTbl) getDoctorByGlobalId(appointment.getDoctorId());
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getDoctorId())));
			se.setDisplayErrMsg(true);
			throw se;
		}


		DoctorScheduleTbl scheduleTbl = (DoctorScheduleTbl) getScheduleByGlobalId(appointment.getScheduleId());
		if (scheduleTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getScheduleId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		DateFormat df2=new SimpleDateFormat(Constants.TIME);
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		Date startsDate;
		Date startTime;
		try {
			startsDate = df.parse(appointment.getStartDate());
			startTime = df1.parse(appointment.getStartTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> doctorScheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleList(
				doctorTbl.getId(), startsDate);
		if (doctorScheduleList.isEmpty()) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ScheduleNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for (DoctorScheduleTbl doctorScheduleTbl : doctorScheduleList) {
			if (!startTime.before(doctorScheduleTbl.getStartingTime())) {
				// System.out.println("End Time"+doctorScheduleTbl.getEndingTime());
				if (!startTime.after(doctorScheduleTbl.getEndingTime())) {
					if (doctorScheduleTbl.getScheduleStatus()!= null
							&& !doctorScheduleTbl.getScheduleStatus().equals(ScheduleStatusEnum.VACATION)) {
						test++;
						schedule = doctorScheduleTbl;
					}
				}
			}
		}
		if (test == 0) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidSchedule);
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientAppointmentTbl appointmentTbl = (PatientAppointmentTbl)getAppointmentByGlobalId(appointment.getGlobalId());
		if(appointmentTbl==null){
			PatientAppointmentTbl appointmentsTbl = new PatientAppointmentTbl();
			appointmentsTbl.setPatientTbl(patientTbl);
			appointmentsTbl.setDoctorTbl(doctorTbl);
			appointmentsTbl.setDoctorScheduleTbl(scheduleTbl);
			PatientAppointmentTbl patientAppointmentTbl = (PatientAppointmentTbl) getAppointment(
					doctorTbl.getId(), startsDate, startTime);

			if (patientAppointmentTbl != null) {
				patientAppointmentTbl.setStatus(StatusEnum.Inactive);
				update(patientAppointmentTbl);
				MessageTbl messageTbl=new MessageTbl();
				messageTbl.setMessage(patientAppointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+patientAppointmentTbl.getDoctorTbl().getFirstName()+" on "+patientAppointmentTbl.getDate()+" "+df2.format(patientAppointmentTbl.getStartingTime())+" has been deleted");					
				messageTbl.setDoctorTbl(patientAppointmentTbl.getDoctorTbl());
				messageTbl.setCreatedTime(new Date());
				messageTbl.setViewed(false);
				save(messageTbl);

			}
			appointmentsTbl.setDate(startsDate);
			appointmentsTbl.setStartingTime(startTime);
			appointmentsTbl.setDoctorScheduleTbl(schedule);
			appointmentsTbl.setGlobalId(appointment.getGlobalId());
			AppointmentStatusEnum  appointmentStatus=AppointmentStatusEnum.getEnum(appointment.getAppointmentStatus());
			appointmentsTbl.setAppointmentStatus(appointmentStatus);
			StatusEnum statusEnum=StatusEnum.getEnum(appointment.getStatus());
			appointmentsTbl.setStatus(statusEnum);
			Date currentTime=new Date();
			appointmentsTbl.setCreatedTime(currentTime);
			appointmentsTbl.setUpdatedTime(currentTime);			
			save(appointmentsTbl);
			response.setStartTime(df1.format(appointmentsTbl.getStartingTime()));
			response.setError(null);
			response.setSuccess(true);
			response.setId(appointmentsTbl.getId());
			return response;
		}
		appointmentTbl.setPatientTbl(patientTbl);
		appointmentTbl.setDoctorTbl(doctorTbl);
		appointmentTbl.setDoctorScheduleTbl(scheduleTbl);
		PatientAppointmentTbl patientAppointmentTbl = (PatientAppointmentTbl) getAppointment(
				doctorTbl.getId(), startsDate, startTime);

		if (patientAppointmentTbl != null) {
			patientAppointmentTbl.setStatus(StatusEnum.Inactive);
			update(patientAppointmentTbl);
			MessageTbl messageTbl=new MessageTbl();
			messageTbl.setMessage(patientAppointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+patientAppointmentTbl.getDoctorTbl().getFirstName()+" on "+patientAppointmentTbl.getDate()+" "+df2.format(patientAppointmentTbl.getStartingTime())+" has been deleted");					
			messageTbl.setDoctorTbl(patientAppointmentTbl.getDoctorTbl());
			messageTbl.setCreatedTime(new Date());
			messageTbl.setViewed(false);
			save(messageTbl);

		}
		appointmentTbl.setDate(startsDate);
		appointmentTbl.setStartingTime(startTime);
		appointmentTbl.setDoctorScheduleTbl(schedule);
		appointmentTbl.setGlobalId(appointment.getGlobalId());
		AppointmentStatusEnum  appointmentStatus=AppointmentStatusEnum.getEnum(appointment.getAppointmentStatus());
		appointmentTbl.setAppointmentStatus(appointmentStatus);
		StatusEnum statusEnum=StatusEnum.getEnum(appointment.getStatus());
		appointmentTbl.setStatus(statusEnum);
		Date currentTime=new Date();
		appointmentTbl.setCreatedTime(currentTime);
		appointmentTbl.setUpdatedTime(currentTime);			
		update(appointmentTbl);
		response.setStartTime(df1.format(appointmentTbl.getStartingTime()));
		response.setError(null);
		response.setSuccess(true);
		response.setId(appointmentTbl.getId());
		return response;
	}
	/**
	 * update appointment
	 * 
	 * @param AppointmentDTO
	 * @return AppointmentResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponseDTO update(AppointmentDTO appointment)throws PersistenceException {
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		// TODO Auto-generated method stub
		PatientAppointmentTbl patientAppointmentTbl = (PatientAppointmentTbl) getById(
				PatientAppointmentTbl.class, appointment.getId());
		if (patientAppointmentTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidAppointment);
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class,
				appointment.getPatientId());
		if (patientTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		patientAppointmentTbl.setPatientTbl(patientTbl);
		patientAppointmentTbl.setUpdatedTime(new Date());
		update(patientAppointmentTbl);
		response.setStartTime(df1.format(patientAppointmentTbl
				.getStartingTime()));
		response.setError(null);
		response.setSuccess(true);
		response.setId(appointment.getId());
		return response;
	}

	/**
	 * inactive appointment
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO delete(int id)throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		DateFormat df=new SimpleDateFormat(Constants.TIME);
		PatientAppointmentTbl appointmentTbl = (PatientAppointmentTbl) getById(
				PatientAppointmentTbl.class, id);
		if (appointmentTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoAppointmentFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setStatus(StatusEnum.Inactive);
		appointmentTbl.setUpdatedTime(new Date());
		update(appointmentTbl);
//		MessageTbl messageTbl=new MessageTbl();
//		messageTbl.setMessage(appointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+appointmentTbl.getDoctorTbl().getFirstName()+" on "+appointmentTbl.getDate()+" "+df.format(appointmentTbl.getStartingTime())+" has been deleted");
//		messageTbl.setDoctorTbl(appointmentTbl.getDoctorTbl());
//		messageTbl.setCreatedTime(new Date());
//		messageTbl.setViewed(false);
//		save(messageTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;
	}

	/**
	 * get list of schedules
	 * 
	 * @param doctorId
	 * @param startDate
	 * @return List<DoctorScheduleTbl>
	 * @throws PersistenceException 
	 */
	public List<DoctorScheduleTbl> getScheduleList(int doctorId, Date startDate) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_SCHEDULE);
		query.setParameter("param1", doctorId);
		query.setParameter("param2", startDate);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * get appointment
	 * 
	 * @param doctorId
	 * @param startDate
	 * @param startTime
	 * @return PatientAppointmentTbl
	 * @throws PersistenceException 
	 */
	public PatientAppointmentTbl getAppointment(int doctorId, Date startDate,
			Date startTime) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_APPOINTMENT);
		query.setParameter("param1", doctorId);
		query.setParameter("param2", startDate);
		query.setParameter("param3", startTime);
		return executeUniqueQuery(PatientAppointmentTbl.class, query);
	}

	/**
	 * view appointments
	 * 
	 * @param doctorId
	 *            ,date
	 * @return AppointmentListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentListResponseDTO view(int doctorId, String date) throws PersistenceException {
		// TODO Auto-generated method stub
		AppointmentListResponseDTO response = new AppointmentListResponseDTO();
		List<AppointmentDTO> appointmentListDTO = new ArrayList<AppointmentDTO>();
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		Date startsDate;

		try {
			startsDate = df1.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointments(
				doctorId, startsDate);
		if (!appointmentList.isEmpty()) {
			for (PatientAppointmentTbl patientAppointmentTbl : appointmentList) {
				AppointmentDTO appointmentDTO = new AppointmentDTO();
				appointmentDTO.setId(patientAppointmentTbl.getId());
				if (patientAppointmentTbl.getDoctorTbl() != null)
					appointmentDTO.setDoctorId(patientAppointmentTbl
							.getDoctorTbl().getId());
				if (patientAppointmentTbl.getPatientTbl() != null) {
					appointmentDTO.setPatientId(patientAppointmentTbl
							.getPatientTbl().getId());
					appointmentDTO.setPatientName(patientAppointmentTbl
							.getPatientTbl().getFirstName());
					appointmentDTO.setPatientLastName(patientAppointmentTbl.getPatientTbl().getLastName());
				}
				if (patientAppointmentTbl.getDoctorScheduleTbl() != null)
					appointmentDTO.setScheduleId(patientAppointmentTbl
							.getDoctorScheduleTbl().getId());
				appointmentDTO.setStartDate(df1.format(patientAppointmentTbl
						.getDate()));
				appointmentDTO.setStartTime(df.format(patientAppointmentTbl
						.getStartingTime()));
				appointmentListDTO.add(appointmentDTO);
			}
			response.setAppointment(appointmentListDTO);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * get appointments
	 * 
	 * @param doctorId
	 * @param startDate
	 * @return List<PatientAppointmentTbl>
	 * @throws PersistenceException 
	 */
	public List<PatientAppointmentTbl> getAppointments(int doctorId,
			Date startDate) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENT_LIST);
		query.setParameter("param1", doctorId);
		query.setParameter("param2", startDate);
		return executeQuery(PatientAppointmentTbl.class, query);
	}

	public List<AppointmentDetailsDTO> getNewAppointments() throws PersistenceException{
		List<AppointmentDetailsDTO> appointmentDetailList=new ArrayList<AppointmentDetailsDTO>();
		List<PatientAppointmentTbl> appointmentListTbl=(ArrayList<PatientAppointmentTbl>)getNewilyCreatedAppointment();
		if(!appointmentListTbl.isEmpty()){
			for (PatientAppointmentTbl appointmentTbl : appointmentListTbl) {
				AppointmentDetailsDTO appointmentDetails=getAppointmentDetail(appointmentTbl);
				appointmentDetailList.add(appointmentDetails);
			}		
		}
		return appointmentDetailList;	

	}

	public AppointmentDetailsDTO getAppointmentDetail(PatientAppointmentTbl appointmentTbl){
		//get appointment detail
		AppointmentDetailsDTO response=new AppointmentDetailsDTO();
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.TIMEWITHFORMAT);
		response.setId(appointmentTbl.getId());
		response.setGlobalId(appointmentTbl.getGlobalId());
		if(appointmentTbl.getDoctorTbl()!=null)
			response.setDoctorId(appointmentTbl.getDoctorTbl().getGlobalId());
		if(appointmentTbl.getPatientTbl()!=null){
			response.setPatientId(appointmentTbl.getPatientTbl().getGlobalId());
			response.setPatientName(appointmentTbl.getPatientTbl().getFirstName()+" "+appointmentTbl.getPatientTbl().getLastName());
			response.setEmailId(appointmentTbl.getPatientTbl().getEmail());
		}
		if(appointmentTbl.getDoctorScheduleTbl()!=null){
			response.setScheduleId(appointmentTbl.getDoctorScheduleTbl().getGlobalId());
		}
		response.setStartDate(df1.format(appointmentTbl.getDate()));
		response.setStartTime(df.format(appointmentTbl.getStartingTime()));

		return response;
	}
	public List<PatientAppointmentTbl> getNewilyCreatedAppointment() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_APPOINTMENT);
		query.setParameter("param1", 0);
		return executeQuery(PatientAppointmentTbl.class, query);
	}

	/**
	 * get updated appointment
	 * @return  List<AppointmentDetailsDTO> 
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<AppointmentDetailsDTO> getUpdatedAppointments() throws PersistenceException {
		List<AppointmentDetailsDTO>appointmentDetailList=new ArrayList<AppointmentDetailsDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					

			List<PatientAppointmentTbl> appointmentListTbl=(ArrayList<PatientAppointmentTbl>)getUpdatedAppointmentList(syncTbl.getUploadedTime());
			if(!appointmentListTbl.isEmpty()){
				for (PatientAppointmentTbl appointmentTbl : appointmentListTbl) {
					AppointmentDetailsDTO appointmentDetail=getAppointmentDetail(appointmentTbl);
					appointmentDetailList.add(appointmentDetail);
				}		
			}
		}
		return appointmentDetailList;
	}
	public List<PatientAppointmentTbl> getUpdatedAppointmentList(Date lastUploadedTime ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_APPOINTMENT);
		query.setParameter("param1", lastUploadedTime);
		return executeQuery(PatientAppointmentTbl.class, query);
	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}
	/**
	 * get deleted appointments
	 * @return List<AppointmentDetailsDTO>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<AppointmentDetailsDTO> getDeletedAppointments() throws PersistenceException {
		List<AppointmentDetailsDTO>appointmentDetailList=new ArrayList<AppointmentDetailsDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					

			List<PatientAppointmentTbl> appointmentListTbl=(ArrayList<PatientAppointmentTbl>)getDeletedAppointmentList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!appointmentListTbl.isEmpty()){
				for (PatientAppointmentTbl appointmentTbl : appointmentListTbl) {
					AppointmentDetailsDTO appointmentDetail=getAppointmentDetail(appointmentTbl);
					appointmentDetailList.add(appointmentDetail);
				}	
			}
		}
		return appointmentDetailList;

	}

	public List<PatientAppointmentTbl> getDeletedAppointmentList(Date lastUploadedTime,StatusEnum status) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_APPOINTMENT);
		query.setParameter("param1", lastUploadedTime);
		query.setParameter("param2", status);
		return executeQuery(PatientAppointmentTbl.class, query);
	}
	/**
	 * update with the global id for newly created appointment 
	 * @param AppointmentResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void addAppointmentSyncResponse(AppointmentResponse appointmentResponse) throws PersistenceException {
		// TODO Auto-generated method stub
		if(appointmentResponse.getError()==null){
			PatientAppointmentTbl appointmentTbl =(PatientAppointmentTbl)getById(PatientAppointmentTbl.class,appointmentResponse.getId());
			if (appointmentTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoAppointmentFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(appointmentResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			appointmentTbl.setAppointmentStatus(AppointmentStatusEnum.Confirmed);
			appointmentTbl.setGlobalId(appointmentResponse.getGlobalId());
			update(appointmentTbl);
		}

	}

	/**
	 * update the updated time of updated appointment if error occur in the sync
	 * @param AppointmentResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateAppointmentSyncResponse(AppointmentResponse appointmentResponse) throws PersistenceException {
		// TODO Auto-generated method stub
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(appointmentResponse.getError()!=null){
			PatientAppointmentTbl appointmentTbl =(PatientAppointmentTbl)getById(PatientAppointmentTbl.class,appointmentResponse.getId());
			if (appointmentTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoAppointmentFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(appointmentResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				appointmentTbl.setUpdatedTime(df.parse(appointmentResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("error while updating the appointment: ",e);
				e.printStackTrace();
			}
			update(appointmentTbl);
		}

	}

	/**
	 * update the updated time of deleted appointment if error occur in the sync
	 * @param AppointmentResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteAppointmentSyncResponse(AppointmentResponse appointmentResponse) throws PersistenceException {
		// TODO Auto-generated method stub
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(appointmentResponse.getError()!=null){
			PatientAppointmentTbl appointmentTbl =(PatientAppointmentTbl)getById(PatientAppointmentTbl.class,appointmentResponse.getId());
			if (appointmentTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoAppointmentFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(appointmentResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				appointmentTbl.setUpdatedTime(df.parse(appointmentResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("error while deleting the appointment: ",e);
				e.printStackTrace();
			}
			update(appointmentTbl);
		}

	}
	private PatientTbl getPatientByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(PatientTbl.class, query);
	}
	private DoctorTbl getDoctorByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}
	private DoctorScheduleTbl getScheduleByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorScheduleTbl.class, query);
	}
	private PatientAppointmentTbl getAppointmentByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENT_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(PatientAppointmentTbl.class, query);
	}

}
