/**
 * ScheduleDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
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

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.DoctorScheduleTbl;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.entity.OccuranceTypeEnum;
import com.nv.netmd.pl.entity.PatientAppointmentTbl;
import com.nv.netmd.pl.entity.RepeatEnum;
import com.nv.netmd.pl.entity.ScheduleStatusEnum;
import com.nv.netmd.pl.entity.SeriesTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.DoctorScheduleTimeDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.ScheduleResponse;
import com.nv.netmd.rs.dto.SeriesDTO;
import com.nv.netmd.rs.dto.ViewScheduleDTO;
import com.nv.netmd.rs.dto.ViewScheduleListDTO;

/**
 * 
 */
public class ScheduleDaoImpl extends GenericDaoHibernateImpl implements
ScheduleDao {
	@PersistenceContext()
	private EntityManager em;

	/**
	 * create schedule
	 * @param startDate,startTime,endTime,status,seriesId,doctorId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO createSchedule(String startDate, String startTime,
			String endTime, String status, int seriesId, int doctorId) throws PersistenceException {

		ResponseDTO response = new ResponseDTO();
		DoctorScheduleTbl scheduleTbl = new DoctorScheduleTbl();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(startDate);
			scheduleTbl.setDate(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {

			Date startsTime = df1.parse(startTime);
			Date endsTime = df1.parse(endTime);
			if (startsTime.after(endsTime)) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.StartTimeGreater);
				se.setDisplayErrMsg(true);
				throw se;

			}
			scheduleTbl.setStartingTime(startsTime);
			scheduleTbl.setEndingTime(endsTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidTimeFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, doctorId);
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(doctorId)));
			se.setDisplayErrMsg(true);
			throw se;
		}if(doctorTbl.getGlobalId()==0){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorGlobalIdNull);			
			se.setDisplayErrMsg(false);
			throw se;
		}
		scheduleTbl.setDoctorTbl(doctorTbl);
		ScheduleStatusEnum status1 = ScheduleStatusEnum.getEnum(status);
		scheduleTbl.setScheduleStatus(status1);
		SeriesTbl seriesTbl = (SeriesTbl) getById(SeriesTbl.class, seriesId);
		scheduleTbl.setSeriesTbl(seriesTbl);
		scheduleTbl.setStatus(StatusEnum.Active);
		Date currentTime=new Date();
		scheduleTbl.setCreatedTime(currentTime);
		scheduleTbl.setUpdatedTime(currentTime);
		save(scheduleTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(scheduleTbl.getId());
		return response;

	}


	//	// get id of a series table
	//	@Override
	//	@Transactional(readOnly = false)
	//	public int getSeriesId(int scheduleId) {
	//		SeriesTbl seriesTbl = (SeriesTbl) getById(SeriesTbl.class, scheduleId);
	//		if (seriesTbl == null) {
	//			ServiceException se = new ServiceException(
	//					ErrorCodeEnum.NoScheduleFound);
	//			se.addParam(new Parameter(Constants.ID, Integer
	//					.toString(scheduleId)));
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//
	//		return seriesTbl.getId();
	//	}

	/**
	 * get List if of schedule by doctor
	 * 
	 * @param newDate and 
	 * @param doctorId
	 * @return List<DoctorScheduleTbl>
	 * @throws PersistenceException 
	 */
	public List<DoctorScheduleTbl> getDoctorSchedule(Date newDate,int doctorId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE);		
		query.setParameter("param1", doctorId);
		query.setParameter("param2", newDate);
		return executeQuery(DoctorScheduleTbl.class, query);
	}
	/**
	 * get doctor schedule list only  start date,start time,end time
	 * @param date,doctorId
	 * @return List<DoctorScheduleTimeDTO>
	 */
	public List<DoctorScheduleTimeDTO> getDoctorScheduleList(Date date,int doctorId) throws PersistenceException{
		List<DoctorScheduleTimeDTO> response=new ArrayList<DoctorScheduleTimeDTO>();		
		DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class, doctorId);
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(doctorId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> scheduleList = (ArrayList<DoctorScheduleTbl>) getDoctorSchedule(date,doctorTbl.getId());
		if(scheduleList.isEmpty()){
			response.clear();
		}
		else{
			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
				DoctorScheduleTimeDTO scheduleTimeDTO=new DoctorScheduleTimeDTO();
				scheduleTimeDTO.setStartDate(doctorScheduleTbl.getDate());
				scheduleTimeDTO.setStartingTime(doctorScheduleTbl.getStartingTime());
				scheduleTimeDTO.setEndingTime(doctorScheduleTbl.getEndingTime());
				response.add(scheduleTimeDTO);
			}
		}
		return response;
	}

	/**
	 * get List if of schedule
	 * 
	 * @param newDate
	 * @return List<DoctorScheduleTbl>
	 * @throws PersistenceException 
	 */
	public List<DoctorScheduleTbl> getDoctorScheduleByDate(Date newDate) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_DATE);
		query.setParameter("param1", newDate);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * update schedule
	 */
	@Override
	public ResponseDTO update(ScheduleDTO schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This schedule will be delete
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO delete(int id)throws PersistenceException {
		ResponseDTO response = new ResponseDTO();

		List<DoctorScheduleTbl> scheduleTblList = (ArrayList<DoctorScheduleTbl>) getScheduleBySeriesId(id);
		if (scheduleTblList.isEmpty()) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		for (DoctorScheduleTbl scheduleTbl : scheduleTblList) {
			List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointmentByScheduleId(scheduleTbl
					.getId());
			if (!appointmentList.isEmpty()) {
				DateFormat df=new SimpleDateFormat(Constants.TIME);
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
			scheduleTbl.setUpdatedTime(new Date());
			scheduleTbl.setStatus(StatusEnum.Inactive);
			update(scheduleTbl);
		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;
	}

	/**
	 * get schedule by series id
	 * 
	 * @param id
	 * @return List<DoctorScheduleTbl>
	 * @throws PersistenceException 
	 */
	public List<DoctorScheduleTbl> getScheduleBySeriesId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_SERIES_ID);
		query.setParameter("param1", id);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * get appointment by schedule id
	 * 
	 * @param id
	 * @return List<PatientAppointmentTbl>
	 * @throws PersistenceException 
	 */
	public List<PatientAppointmentTbl> getAppointmentByScheduleId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENT_BY_SCHEDULE);
		query.setParameter("param1", id);
		return executeQuery(PatientAppointmentTbl.class, query);
	}

	/**
	 * All other events in the series will be delete
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO deleteThisInstanceSchedule(int id) throws PersistenceException{
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();

		DoctorScheduleTbl scheduleTbl = (DoctorScheduleTbl) getById(
				DoctorScheduleTbl.class, id);
		if (scheduleTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointmentByScheduleId(scheduleTbl
				.getId());
		if (!appointmentList.isEmpty()) {
			DateFormat df=new SimpleDateFormat(Constants.TIME);
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
		scheduleTbl.setUpdatedTime(new Date());
		scheduleTbl.setStatus(StatusEnum.Inactive);
		update(scheduleTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;
	}

	/**
	 * All following schedule will be delete
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO deleteFollowingSchedule(int id) throws PersistenceException {
		// TODO Auto-generated method stub

		ResponseDTO response = new ResponseDTO();

		DoctorScheduleTbl scheduleTbl = (DoctorScheduleTbl) getById(
				DoctorScheduleTbl.class, id);
		if (scheduleTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		int num = scheduleTbl.getId();

		List<DoctorScheduleTbl> scheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleBySeriesId(scheduleTbl
				.getSeriesTbl().getId());
		for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
			if (doctorScheduleTbl.getId() >= num) {
				List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointmentByScheduleId(doctorScheduleTbl
						.getId());
				if (!appointmentList.isEmpty()) {
					DateFormat df=new SimpleDateFormat(Constants.TIME);
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
				doctorScheduleTbl.setUpdatedTime(new Date());
				doctorScheduleTbl.setStatus(StatusEnum.Inactive);
				update(doctorScheduleTbl);

			}

		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;

	}

	/**
	 * get day view
	 * 
	 * @param date
	 * @return ViewScheduleListDTO
	 */

	@Override
	@Transactional(readOnly = false)
	public ViewScheduleListDTO dayView(String date,int doctorId)throws PersistenceException {
		Date newDate;
		// TODO Auto-generated method stub
		ViewScheduleListDTO response = new ViewScheduleListDTO();
		String s = "";
		ArrayList<ViewScheduleDTO> viewScheduleList = new ArrayList<ViewScheduleDTO>();
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			newDate = df1.parse(date.trim());
		} catch (Exception e) {
			// TODO: handle exception
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorId);
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(doctorId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> scheduleList = (List<DoctorScheduleTbl>) getDoctorSchedule(newDate,doctorId);
		if (!scheduleList.isEmpty()) {
			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
				ViewScheduleDTO viewScheduleDTO = new ViewScheduleDTO();
				viewScheduleDTO.setId(doctorScheduleTbl.getId());
				if (doctorScheduleTbl.getDoctorTbl() != null)
					viewScheduleDTO.setDoctorId(doctorScheduleTbl
							.getDoctorTbl().getId());
				if (doctorScheduleTbl.getSeriesTbl() != null) {
					viewScheduleDTO.setSeriesId(doctorScheduleTbl
							.getSeriesTbl().getId());
					SeriesTbl seriesTbl = getById(SeriesTbl.class,
							doctorScheduleTbl.getSeriesTbl().getId());
					if (seriesTbl == null) {
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.SeriesNotExist);
						se.setDisplayErrMsg(true);
						throw se;
					}
					if (seriesTbl.getEndDate() != null) {
						viewScheduleDTO.setEndDate(df1.format(seriesTbl
								.getEndDate()));
					}
					if (seriesTbl.getOccuranceType() != null) {
						viewScheduleDTO.setOccuranceType(seriesTbl
								.getOccuranceType().getDisplayName());
					}
					viewScheduleDTO.setRepeat(seriesTbl.getRepeat().getDisplayName());
					if (seriesTbl.getRepeat().equals(
							RepeatEnum.WEEKLY)) {
						if (seriesTbl.getWeeklyType() != null) {// weekly type
							s = seriesTbl.getWeeklyType();
							s = s.trim();
							String[] items = s.split(",");
							// System.out.println("a value="+items);

							int[] results = new int[items.length];
							for (int i = 0; i < items.length; i++) {
								// giving week days
								results[i] = Integer.parseInt(items[i]);

							}
							viewScheduleDTO.setWeeklySunThruSatList(results);
						}
					}
					if (seriesTbl.getEndsOn() != null)
						viewScheduleDTO.setEndsOn(seriesTbl.getEndsOn());
				}
				viewScheduleDTO.setStartDate(df1.format(doctorScheduleTbl
						.getDate()));
				viewScheduleDTO.setStartTime(df.format(doctorScheduleTbl
						.getStartingTime()));
				viewScheduleDTO.setEndTime(df.format(doctorScheduleTbl
						.getEndingTime()));
				viewScheduleDTO.setStatus(doctorScheduleTbl.getScheduleStatus()
						.getDisplayName());
				viewScheduleList.add(viewScheduleDTO);
			}
		}
		response.setSchedule(viewScheduleList);
		response.setError(null);
		response.setSuccess(true);
		// response.setId(id);
		return response;
	}

	/**
	 * create a new series
	 * 
	 * @param ScheduleDTO
	 * @return series id
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public int createSeries(ScheduleDTO schedule) throws PersistenceException  {
		SeriesTbl seriesTbl = new SeriesTbl();
		String repeatType = schedule.getRepeat();
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		int[] weeks;
		int weekSize = 0;

		String s = "";
		if (repeatType.equals(RepeatEnum.NONE.getDisplayName())) {
			OccuranceTypeEnum occurance = OccuranceTypeEnum
					.getEnum(OccuranceTypeEnum.None.getDisplayName());
			seriesTbl.setOccuranceType(occurance);
		}
		if (!repeatType.equals(RepeatEnum.NONE.getDisplayName())) {
			if (schedule.getNoOfOccurances() != 0) {
				OccuranceTypeEnum occurance = OccuranceTypeEnum
						.getEnum(OccuranceTypeEnum.EndsOn.getDisplayName());
				seriesTbl.setOccuranceType(occurance);
				seriesTbl.setEndsOn(schedule.getNoOfOccurances());
			} else {
				OccuranceTypeEnum occurance = OccuranceTypeEnum
						.getEnum(OccuranceTypeEnum.EndsDate.getDisplayName());
				seriesTbl.setOccuranceType(occurance);
				try {
					if (schedule.getEndDate() != null)
						seriesTbl.setEndDate(df1.parse(schedule.getEndDate()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (repeatType.equals(RepeatEnum.WEEKLY.getDisplayName())) {
			// if(!schedule.getWeeklySunThruSatList().equals(null)){
			weeks = schedule.getWeeklySunThruSatList();
			weekSize = weeks.length;
			for (int i = 0; i < weekSize; i++) {// save time weeks in data base
				// as string
				if (weeks[i] != 0) {
					s = s + Integer.toString(weeks[i]);
					s = s + ",";
					// System.out.println(s+"=weeks");

				}
			}
			s = s.substring(0, s.length() - 1);
			// System.out.println(s+"s value all");
			seriesTbl.setWeeklyType(s);
			// }
		}
		RepeatEnum repeat = RepeatEnum
				.getEnum(schedule.getRepeat());
		seriesTbl.setRepeat(repeat);
		save(seriesTbl);
		return seriesTbl.getId();
	}
	//
	//	public void schedule(ScheduleDTO schedule) {
	//		Date newDate;
	//		SimpleDateFormat df1 = new SimpleDateFormat(
	//				Constants.DATE_FORMAT_WITHOUT_TIME);
	//		try {
	//			newDate = df1.parse(schedule.getStartDate());
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//			ServiceException se = new ServiceException(
	//					ErrorCodeEnum.InvalidDateFormat);
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//		List<DoctorScheduleTbl> scheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleBySeriesId(schedule
	//				.getSeriesId());
	//		if (!scheduleList.isEmpty()) {
	//			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
	//				if (doctorScheduleTbl.getDate() != newDate) {
	//					List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointmentByScheduleId(doctorScheduleTbl
	//							.getId());
	//					if (!appointmentList.isEmpty()) {
	//						for (PatientAppointmentTbl patientAppointmentTbl : appointmentList) {
	//							delete(patientAppointmentTbl);
	//						}
	//					}
	//					delete(doctorScheduleTbl);
	//				}
	//
	//			}
	//		}
	//
	//	}

	/**
	 * update series table
	 * 
	 * @param ScheduleDTO
	 * @return series id
	 */
	@Override
	@Transactional(readOnly = false)
	public int updateSeriesTbl(ScheduleDTO schedule) throws PersistenceException {
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SeriesTbl seriesTbl = getById(SeriesTbl.class, schedule.getSeriesId());
		if (seriesTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.SeriesWithIdNotExist);
			se.addParam(new Parameter(Constants.ID, Integer.toString(schedule
					.getSeriesId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String repeatType = schedule.getRepeat();
		int[] weeks;
		int weekSize = 0;

		String s = "";
		if (repeatType.equals(RepeatEnum.NONE.getDisplayName())) {
			OccuranceTypeEnum occurance = OccuranceTypeEnum
					.getEnum(OccuranceTypeEnum.None.getDisplayName());
			seriesTbl.setOccuranceType(occurance);
		}
		if (repeatType.equals(RepeatEnum.NONE.getDisplayName())) {
			if (schedule.getNoOfOccurances() != 0) {
				OccuranceTypeEnum occurance = OccuranceTypeEnum
						.getEnum(OccuranceTypeEnum.EndsOn.getDisplayName());
				seriesTbl.setOccuranceType(occurance);
				seriesTbl.setEndsOn(schedule.getNoOfOccurances());
			} else {
				OccuranceTypeEnum occurance = OccuranceTypeEnum
						.getEnum(OccuranceTypeEnum.EndsDate.getDisplayName());
				seriesTbl.setOccuranceType(occurance);
				try {
					if (schedule.getEndDate() != null)
						seriesTbl.setEndDate(df1.parse(schedule.getEndDate()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		if (repeatType.equals(RepeatEnum.WEEKLY.getDisplayName())) {
			weeks = schedule.getWeeklySunThruSatList();
			weekSize = weeks.length;
			for (int i = 0; i < weekSize; i++) {// save time weeks in data base
				// as string
				if (weeks[i] != 0) {
					s = s + Integer.toString(weeks[i]);
					s = s + ",";
					// System.out.println(s+"=weeks");

				}
			}
			s = s.substring(0, s.length() - 1);
			// System.out.println(s+"s value all");
			seriesTbl.setWeeklyType(s);
		}
		RepeatEnum repeat = RepeatEnum
				.getEnum(schedule.getRepeat());
		seriesTbl.setRepeat(repeat);
		update(seriesTbl);
		return seriesTbl.getId();
	}

	/**
	 * update schedule not completed
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateSchedule(String startDate, String startTime,
			String endTime, String status, int seriesId, int doctorId) throws PersistenceException {

		ResponseDTO response = new ResponseDTO();

		DoctorScheduleTbl scheduleTbl = new DoctorScheduleTbl();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(startDate);
			scheduleTbl.setDate(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {

			Date startsTime = df1.parse(startTime);
			Date endsTime = df1.parse(endTime);
			if (startsTime.after(endsTime)) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.StartTimeGreater);
				se.setDisplayErrMsg(true);
				throw se;

			}
			scheduleTbl.setStartingTime(startsTime);
			scheduleTbl.setEndingTime(endsTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidTimeFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, doctorId);
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(doctorId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		scheduleTbl.setDoctorTbl(doctorTbl);
		ScheduleStatusEnum status1 = ScheduleStatusEnum.getEnum(status);
		scheduleTbl.setScheduleStatus(status1);
		SeriesTbl seriesTbl = (SeriesTbl) getById(SeriesTbl.class, seriesId);
		scheduleTbl.setSeriesTbl(seriesTbl);
		save(scheduleTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(scheduleTbl.getId());
		return response;

	}

	/**
	 * check for update schedule not completed
	 * @param schedule
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean checkUpdateSchedule(ScheduleDTO schedule) throws PersistenceException{
		Date fromTime, toTime, newDate;
		int test = 0;
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			newDate = df1.parse(schedule.getStartDate());
			fromTime = df2.parse(schedule.getStartTime());
			toTime = df2.parse(schedule.getEndTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> scheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleBySeriesId(schedule
				.getSeriesId());
		if (!scheduleList.isEmpty()) {

		}
		List<DoctorScheduleTbl> scheduleLis = (ArrayList<DoctorScheduleTbl>) getDoctorScheduleByDate(newDate);

		if (scheduleLis.isEmpty()) {
			return true;
		} else {
			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
				if (!fromTime.before(doctorScheduleTbl.getStartingTime())) {
					if (!fromTime.after(doctorScheduleTbl.getEndingTime())) {
						test++;
					}
				}
				if (!toTime.before(doctorScheduleTbl.getEndingTime())) {
					if (!toTime.after(doctorScheduleTbl.getEndingTime())) {
						test++;
					}
				}
				// if(fromTime.before(doctorScheduleTbl.getStartingTime())&&toTime.before(doctorScheduleTbl.getEndingTime())){
				// test++;
				// }
				// if(fromTime.after(doctorScheduleTbl.getEndingTime())){
				// test++;
				// }

			}
			if (test != 0) {
				return false;
			}

		}
		return true;
	}

	/**
	 * get deleted schedules
	 * @return List<ScheduleDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<ScheduleDetail> getDeletedSchedule() throws PersistenceException {
		// TODO Auto-generated method stub
		List<ScheduleDetail>scheduleDetailList=new ArrayList<ScheduleDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<DoctorScheduleTbl> scheduleListTbl=(ArrayList<DoctorScheduleTbl>)getDeletedScheduleList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!scheduleListTbl.isEmpty()){
				for (DoctorScheduleTbl scheduleTbl : scheduleListTbl) {
					ScheduleDetail scheduleDetail=getScheduleDetail(scheduleTbl);
					scheduleDetailList.add(scheduleDetail);
				}
			}
		}
		return scheduleDetailList;

	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}

	public List<DoctorScheduleTbl> getDeletedScheduleList(Date lastUploadedTime,StatusEnum status) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_SCHEDULE);
		query.setParameter("param1", lastUploadedTime);
		query.setParameter("param2", status);
		return executeQuery(DoctorScheduleTbl.class, query);
	}
	/**
	 * get newly created schedule list
	 * @return List<ScheduleDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<ScheduleDetail> getNewSchedule() throws PersistenceException {
		List<ScheduleDetail>scheduleDetailList=new ArrayList<ScheduleDetail>();
		List<DoctorScheduleTbl> scheduleListTbl=(ArrayList<DoctorScheduleTbl>)getNewilyCreatedSchedule();
		if(!scheduleListTbl.isEmpty()){
			for (DoctorScheduleTbl scheduleTbl : scheduleListTbl) {
				ScheduleDetail scheduleDetail=getScheduleDetail(scheduleTbl);
				scheduleDetailList.add(scheduleDetail);
			}		
		}
		return scheduleDetailList;

	}
	/**
	 * get schedule details
	 * @param scheduleTbl
	 * @return ScheduleDetail
	 */
	public ScheduleDetail getScheduleDetail(DoctorScheduleTbl scheduleTbl){
		ScheduleDetail response=new ScheduleDetail();
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		if(scheduleTbl.getDoctorTbl()!=null)
			response.setDoctorGlobalId(scheduleTbl.getDoctorTbl().getGlobalId());
		response.setId(scheduleTbl.getId());
		response.setScheduleGlobalId(scheduleTbl.getGlobalId());
		response.setStartDate(df1.format(scheduleTbl.getDate()));
		response.setStartTime(df2.format(scheduleTbl.getStartingTime()));
		response.setEndTime(df2.format(scheduleTbl.getEndingTime()));
		if(scheduleTbl.getScheduleStatus()!=null)
			response.setScheduleStatus(scheduleTbl.getScheduleStatus().getDisplayName());
		if(scheduleTbl.getStatus()!=null)
			response.setStatus(scheduleTbl.getStatus().getDisplayName());
		if(scheduleTbl.getSeriesTbl()!=null){
			SeriesDTO series=new SeriesDTO();
			series.setSeriesId(scheduleTbl.getSeriesTbl().getId());
			if(scheduleTbl.getSeriesTbl().getRepeat()!=null)
				series.setRepeat(scheduleTbl.getSeriesTbl().getRepeat().getDisplayName());
			if(scheduleTbl.getSeriesTbl().getEndDate()!=null)
				series.setEndDate(df1.format(scheduleTbl.getSeriesTbl().getEndDate()));
			series.setEndsOn(scheduleTbl.getSeriesTbl().getEndsOn());
			if(scheduleTbl.getSeriesTbl().getOccuranceType()!=null)
				series.setOccuranceType(scheduleTbl.getSeriesTbl().getOccuranceType().getDisplayName());
			series.setWeeklyType(scheduleTbl.getSeriesTbl().getWeeklyType());

			response.setSeries(series);
		}
		return response;
	}
	public List<DoctorScheduleTbl> getNewilyCreatedSchedule() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_SCHEDULE);
		query.setParameter("param1", 0);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * get updated schedule and give to portal
	 * @return List<DoctorDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<ScheduleDetail> getUpdatedSchedule() throws PersistenceException {
		List<ScheduleDetail>doctorDetailList=new ArrayList<ScheduleDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					

			List<DoctorScheduleTbl> scheduleListTbl=(ArrayList<DoctorScheduleTbl>)getUpdatedScheduleList(syncTbl.getUploadedTime());
			if(!scheduleListTbl.isEmpty()){
				for (DoctorScheduleTbl scheduleTbl : scheduleListTbl) {
					ScheduleDetail scheduleDetail=getScheduleDetail(scheduleTbl);
					doctorDetailList.add(scheduleDetail);
				}		
			}
		}
		return doctorDetailList;
	}
	public List<DoctorScheduleTbl> getUpdatedScheduleList(Date lastUploadedTime ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_SCHEDULE);
		query.setParameter("param1", lastUploadedTime);
		return executeQuery(DoctorScheduleTbl.class, query);
	}
	/**
	 * update with the global id for newly created schedule 
	 * @param scheduleResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void addScheduleSyncResponse(ScheduleResponse scheduleResponse) throws PersistenceException {
		// TODO Auto-generated method stub
		if(scheduleResponse.getError()==null){
			DoctorScheduleTbl scheduleTbl =(DoctorScheduleTbl)getById(DoctorScheduleTbl.class,scheduleResponse.getId());
			if (scheduleTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ScheduleNotExist);
				se.addParam(new Parameter(Constants.ID,Integer.toString(scheduleResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			SeriesTbl seriesTbl=(SeriesTbl)getById(SeriesTbl.class, scheduleTbl.getSeriesTbl().getId());
			if (seriesTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.SeriesNotExist);
				se.addParam(new Parameter(Constants.ID,Integer.toString(scheduleResponse.getSeriesId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			seriesTbl.setGlobalId(scheduleResponse.getSeriesGlobalId());
			update(seriesTbl);
			scheduleTbl.setGlobalId(scheduleResponse.getGlobalId());
			update(scheduleTbl);
		}

	}
	/**
	 *update the updated time of updated schedule if error occur in the sync
	 * @param scheduleResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateScheduleSyncResponse(ScheduleResponse scheduleResponse) throws PersistenceException{
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(scheduleResponse.getError()!=null){
			DoctorScheduleTbl scheduleTbl =(DoctorScheduleTbl)getById(DoctorScheduleTbl.class,scheduleResponse.getId());
			if (scheduleTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ScheduleNotExist);
				se.addParam(new Parameter(Constants.ID,Integer.toString(scheduleResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				if(scheduleResponse.getUpdateDateTime()!=null)
					scheduleTbl.setUpdatedTime(df.parse(scheduleResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update(scheduleTbl);
		}

	}
	/**
	 *update the updated time of deleted schedule if error occur in the sync
	 * @param scheduleResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteScheduleSyncResponse(ScheduleResponse scheduleResponse) throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(scheduleResponse.getError()!=null){
			DoctorScheduleTbl scheduleTbl =(DoctorScheduleTbl)getById(DoctorScheduleTbl.class,scheduleResponse.getId());
			if (scheduleTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ScheduleNotExist);
				se.addParam(new Parameter(Constants.ID,Integer.toString(scheduleResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				scheduleTbl.setUpdatedTime(df.parse(scheduleResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update(scheduleTbl);
		}

	}


	/**
	 * schedule from YNW
	 * @param schedule
	 * @return ScheduleResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ScheduleResponse scheduleFromYNW(ScheduleDetail schedule) throws PersistenceException {
		// TODO Auto-generated method stub
		ScheduleResponse response=new ScheduleResponse();
		ResponseDTO responseDTO=new ResponseDTO();
		DoctorScheduleTbl scheduleTbl=(DoctorScheduleTbl)getScheduleByGlobalId(schedule.getScheduleGlobalId());
		if(scheduleTbl==null){
			responseDTO=createScheduleFromYNW(schedule);
			response.setError(responseDTO.getError());
			response.setSuccess(responseDTO.isSuccess());
			return response;
		}
		responseDTO=updateScheduleFromYNW(schedule);
		response.setError(responseDTO.getError());
		response.setSuccess(responseDTO.isSuccess());
		return response;

	}
	private DoctorScheduleTbl getScheduleByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorScheduleTbl.class, query);
	}
	/**
	 * Create schedule from YNW
	 * @param schedule
	 * @return ResponseDTO
	 */
	private ResponseDTO createScheduleFromYNW(ScheduleDetail schedule) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		DoctorScheduleTbl scheduleTbl=new DoctorScheduleTbl();
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		if(schedule.getSeries()==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.SeriesIdNotNull);

			se.setDisplayErrMsg(true);
			throw se;
		}
		if(schedule.getSeries()!=null){
			SeriesTbl seriesTbl=(SeriesTbl)getSeriesByGlobalId(schedule.getSeries().getSeriesGlobalId());
			if(seriesTbl==null){
				//error
				SeriesTbl series=new SeriesTbl();
				if(schedule.getSeries().getEndDate()!=null)
					try {
						series.setEndDate(df.parse(schedule.getSeries().getEndDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				series.setEndsOn(schedule.getSeries().getEndsOn());
				series.setGlobalId(schedule.getSeries().getSeriesGlobalId());
				OccuranceTypeEnum occurane=OccuranceTypeEnum.getEnum(schedule.getSeries().getOccuranceType());
				series.setOccuranceType(occurane);
				RepeatEnum repeat=RepeatEnum.getEnum(schedule.getSeries().getRepeat());
				series.setRepeat(repeat);
				series.setWeeklyType(schedule.getSeries().getWeeklyType());
				save(series);
				scheduleTbl.setSeriesTbl(series);

			}
			else
				scheduleTbl.setSeriesTbl(seriesTbl);
		}

		try {
			Date date = df.parse(schedule.getStartDate());
			scheduleTbl.setDate(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {

			Date startsTime = df1.parse(schedule.getStartTime());
			Date endsTime = df1.parse(schedule.getEndTime());
			if (startsTime.after(endsTime)) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.StartTimeGreater);
				se.setDisplayErrMsg(true);
				throw se;

			}
			scheduleTbl.setStartingTime(startsTime);
			scheduleTbl.setEndingTime(endsTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidTimeFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
		DoctorTbl doctorTbl = (DoctorTbl) getDoctorByGlobalId(schedule.getDoctorGlobalId());
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(schedule.getDoctorGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		scheduleTbl.setDoctorTbl(doctorTbl);
		scheduleTbl.setGlobalId(schedule.getScheduleGlobalId());
		ScheduleStatusEnum scheduleStatus=ScheduleStatusEnum.getEnum(schedule.getScheduleStatus());		
		scheduleTbl.setScheduleStatus(scheduleStatus);

		StatusEnum status=StatusEnum.getEnum(schedule.getStatus());
		scheduleTbl.setStatus(status);
		Date createdTime=new Date();
		scheduleTbl.setCreatedTime(createdTime);
		scheduleTbl.setUpdatedTime(createdTime);

		save(scheduleTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/**
	 * Update Schedule from YNW
	 * @param schedule
	 * @return ResponseDTO
	 */
	private ResponseDTO updateScheduleFromYNW(ScheduleDetail schedule) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		DoctorScheduleTbl scheduleTbl=(DoctorScheduleTbl)getScheduleByGlobalId(schedule.getScheduleGlobalId());
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		if(schedule.getSeries()==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.SeriesIdNotNull);

			se.setDisplayErrMsg(true);
			throw se;
		}
		if(schedule.getSeries()!=null){
			SeriesTbl seriesTbl=(SeriesTbl)getSeriesByGlobalId(schedule.getSeries().getSeriesGlobalId());
			if(seriesTbl==null){
				//create new series
				SeriesTbl series=new SeriesTbl();
				if(schedule.getSeries().getEndDate()!=null)
					try {
						series.setEndDate(df.parse(schedule.getSeries().getEndDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				series.setEndsOn(schedule.getSeries().getEndsOn());
				series.setGlobalId(schedule.getSeries().getSeriesGlobalId());
				OccuranceTypeEnum occurane=OccuranceTypeEnum.getEnum(schedule.getSeries().getOccuranceType());
				series.setOccuranceType(occurane);
				RepeatEnum repeat=RepeatEnum.getEnum(schedule.getSeries().getRepeat());
				series.setRepeat(repeat);
				series.setWeeklyType(schedule.getSeries().getWeeklyType());
				save(series);
				scheduleTbl.setSeriesTbl(series);

			}
			else
				scheduleTbl.setSeriesTbl(seriesTbl);
		}

		try {
			Date date = df.parse(schedule.getStartDate());
			scheduleTbl.setDate(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {

			Date startsTime = df1.parse(schedule.getStartTime());
			Date endsTime = df1.parse(schedule.getEndTime());
			if (startsTime.after(endsTime)) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.StartTimeGreater);
				se.setDisplayErrMsg(true);
				throw se;

			}
			scheduleTbl.setStartingTime(startsTime);
			scheduleTbl.setEndingTime(endsTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidTimeFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
		DoctorTbl doctorTbl = (DoctorTbl) getDoctorByGlobalId(schedule.getDoctorGlobalId());
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(schedule.getDoctorGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		scheduleTbl.setDoctorTbl(doctorTbl);
		scheduleTbl.setGlobalId(schedule.getScheduleGlobalId());
		ScheduleStatusEnum scheduleStatus=ScheduleStatusEnum.getEnum(schedule.getScheduleStatus());	
		StatusEnum status=StatusEnum.getEnum(schedule.getStatus());
		if(scheduleStatus.equals(ScheduleStatusEnum.VACATION)||status.equals(StatusEnum.Inactive)){
			List<PatientAppointmentTbl> appointmentList = (ArrayList<PatientAppointmentTbl>) getAppointmentByScheduleId(scheduleTbl
					.getId());
			if (!appointmentList.isEmpty()) {
				DateFormat df2=new SimpleDateFormat(Constants.TIME);
				for (PatientAppointmentTbl patientAppointmentTbl : appointmentList) {
					patientAppointmentTbl.setStatus(StatusEnum.Inactive);
					patientAppointmentTbl.setUpdatedTime(new Date());
					update(patientAppointmentTbl);
					MessageTbl messageTbl=new MessageTbl();
					messageTbl.setMessage(patientAppointmentTbl.getPatientTbl().getFirstName()+"'s appointment with Dr."+patientAppointmentTbl.getDoctorTbl().getFirstName()+" on "+patientAppointmentTbl.getDate()+" "+df2.format(patientAppointmentTbl.getStartingTime())+" has been deleted");					
					messageTbl.setDoctorTbl(patientAppointmentTbl.getDoctorTbl());
					messageTbl.setCreatedTime(new Date());
					messageTbl.setViewed(false);
					save(messageTbl);
				}
			}
		}
		scheduleTbl.setScheduleStatus(scheduleStatus);		
		scheduleTbl.setStatus(status);
		Date createdTime=new Date();
		scheduleTbl.setCreatedTime(createdTime);
		scheduleTbl.setUpdatedTime(createdTime);
		update(scheduleTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private DoctorTbl getDoctorByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}
	private SeriesTbl getSeriesByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_SERIES_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(SeriesTbl.class, query);
	}
}
