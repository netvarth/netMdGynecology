/**
 * DoctorDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 6, 2012
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

import com.nv.netmd.business.pl.dao.DoctorDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.DoctorAchievementTbl;
import com.nv.netmd.pl.entity.DoctorEducationalQualificationTbl;
import com.nv.netmd.pl.entity.DoctorExpertiseTbl;
import com.nv.netmd.pl.entity.DoctorMembershipTbl;
import com.nv.netmd.pl.entity.DoctorPracticeExperienceTbl;
import com.nv.netmd.pl.entity.DoctorScheduleTbl;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.GenderEnum;
import com.nv.netmd.pl.entity.LoginTbl;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.entity.PatientAppointmentTbl;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncStatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.DoctorAchievementDTO;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorExperienceDTO;
import com.nv.netmd.rs.dto.DoctorExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListAchievementDTO;
import com.nv.netmd.rs.dto.DoctorListExperienceDTO;
import com.nv.netmd.rs.dto.DoctorListExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListMembershipDTO;
import com.nv.netmd.rs.dto.DoctorListQualificationDTO;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorMembershipDTO;
import com.nv.netmd.rs.dto.DoctorPersonalDTO;
import com.nv.netmd.rs.dto.DoctorQualificationDTO;
import com.nv.netmd.rs.dto.DoctorResponse;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class DoctorDaoImpl extends GenericDaoHibernateImpl implements DoctorDao {
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(DoctorDaoImpl.class);

	/**
	 * create doctor
	 * 
	 * @param DoctorDetail
	 * @return ResponseDTO
	 */
	//	@Override
	//	@Transactional(readOnly = false)
	//	public ResponseDTO create(DoctorDetail doctor) {
	//		ResponseDTO response = new ResponseDTO();
	//		/* Checking whether the netmd id exists or not */
	//		// NetmdTbl netId=(NetmdTbl)getById(NetmdTbl.class, netMdId);
	//		// if(netId==null){
	//		// PersistenceException se =new PersistenceException(ErrorCodeEnum.NetMdNull);
	//		// se.addParam(new Parameter(Constants.ID,Integer.toString(netMdId)));
	//		// se.setDisplayErrMsg(true);
	//		// throw se;
	//		// }
	//		/* Saving username and password in login tbl */
	//		//		StringEncoder strencoder = new StringEncoder();
	//		//		String password = strencoder.encryptWithKey(doctor.getLogin()
	//		//				.getPassword());
	//		LoginTbl existingUser = (LoginTbl) getUser(doctor.getLogin()
	//				.getUserName().trim());
	//		if (existingUser != null) {
	//
	//			PersistenceException se = new PersistenceException(ErrorCodeEnum.UserExists);
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//		LoginTbl loginTbl = new LoginTbl();
	//		loginTbl.setUserName(doctor.getLogin().getUserName().trim());
	//		//loginTbl.setPassword(password);
	//		UserTypeEnum userType = UserTypeEnum.getEnum(doctor.getLogin()
	//				.getUserType());
	//		loginTbl.setUserType(userType);
	//		save(loginTbl);
	//
	//		/* Saving doctor info and pass phrase in doctor tbl */
	//		DoctorTbl doctorTbl = new DoctorTbl();
	//		doctorTbl.setFirstName(doctor.getFirstName());
	//		doctorTbl.setLastName(doctor.getLastName());
	//		doctorTbl.setAddress(doctor.getAddress());
	//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
	//		Date date = null;
	//		try {
	//			date = df.parse(doctor.getDateOfBirth());
	//		} catch (ParseException e) {
	//			PersistenceException se = new PersistenceException(
	//					ErrorCodeEnum.InvalidDateFormat);
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//
	//		doctorTbl.setDateOfBirth(date);
	//		doctorTbl.setDesignation(doctor.getDesignation());
	//		doctorTbl.setSpecialization(doctor.getSpecialization());
	//		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
	//		doctorTbl.setGender(gender);
	//		doctorTbl.setEmail(doctor.getEmail());
	//		doctorTbl.setMobile(doctor.getMobile());
	//		doctorTbl.setPhone(doctor.getPhone());
	//		doctorTbl.setCreatedTime(new Date());
	//		doctorTbl.setLoginTbl(loginTbl);
	//		save(doctorTbl);
	//		if (doctor.getDoctorExperience() != null) {
	//			for (DoctorExperienceDTO doctorExperience : doctor
	//					.getDoctorExperience()) {
	//				DoctorPracticeExperienceTbl doctorExperienceTbl = new DoctorPracticeExperienceTbl();
	//				doctorExperienceTbl.setDesignation(doctorExperience
	//						.getDesignation());
	//				if (doctorExperience.getFromDate() != null
	//						|| doctorExperience.getToDate() != null) {
	//					Date fromDate = null;
	//					Date toDate = null;
	//					try {
	//						fromDate = df.parse(doctorExperience.getFromDate());
	//						toDate = df.parse(doctorExperience.getToDate());
	//						doctorExperienceTbl.setFromDate(fromDate);
	//						doctorExperienceTbl.setToDate(toDate);
	//					} catch (ParseException e) {
	//						e.printStackTrace();
	//						PersistenceException se = new PersistenceException(
	//								ErrorCodeEnum.InvalidDateFormat);
	//						se.setDisplayErrMsg(true);
	//						throw se;
	//					}
	//
	//				}
	//				doctorExperienceTbl.setWorkedAt(doctorExperience.getWorkedAt());
	//				doctorExperienceTbl.setDoctorTbl(doctorTbl);
	//				save(doctorExperienceTbl);
	//
	//			}
	//		}
	//		if (doctor.getDoctorQualifications() != null) {
	//			for (DoctorQualificationDTO doctorQualificaton : doctor
	//					.getDoctorQualifications()) {
	//				DoctorEducationalQualificationTbl QualificationTbl = new DoctorEducationalQualificationTbl();
	//
	//				QualificationTbl.setEducationalDegree(doctorQualificaton
	//						.getEducationalDegree());
	//				QualificationTbl.setInstitution(doctorQualificaton
	//						.getInstitution());
	//				QualificationTbl.setDoctorTbl(doctorTbl);
	//				if (doctorQualificaton.getPassedOutDate() != null) {
	//					Date passoutDate = null;
	//					try {
	//						passoutDate = df.parse(doctorQualificaton
	//								.getPassedOutDate());
	//						QualificationTbl.setPassedOutDate(passoutDate);
	//					} catch (ParseException e) {
	//						e.printStackTrace();
	//						PersistenceException se = new PersistenceException(
	//								ErrorCodeEnum.InvalidDateFormat);
	//						se.setDisplayErrMsg(true);
	//						throw se;
	//					}
	//				}
	//
	//				save(QualificationTbl);
	//			}
	//		}
	//		if (doctor.getAchievement() != null) {
	//			for (DoctorAchievementDTO doctorAchievementDTO : doctor
	//					.getAchievement()) {
	//				DoctorAchievementTbl achievementTbl = new DoctorAchievementTbl();
	//				achievementTbl.setAchievement(doctorAchievementDTO
	//						.getAchievement());
	//				achievementTbl.setDoctorTbl(doctorTbl);
	//				save(achievementTbl);
	//			}
	//		}
	//		if (doctor.getExpertise() != null) {
	//			for (DoctorExpertiseDTO doctorExpertiseDTO : doctor.getExpertise()) {
	//				DoctorExpertiseTbl expertiseTbl = new DoctorExpertiseTbl();
	//				expertiseTbl.setExpertise(doctorExpertiseDTO.getExpertise());
	//				expertiseTbl.setDoctorTbl(doctorTbl);
	//				save(expertiseTbl);
	//			}
	//		}
	//		if (doctor.getMembership() != null) {
	//			for (DoctorMembershipDTO doctorMembershipDTO : doctor
	//					.getMembership()) {
	//				DoctorMembershipTbl membershipTbl = new DoctorMembershipTbl();
	//				membershipTbl
	//				.setMembership(doctorMembershipDTO.getMembership());
	//				membershipTbl.setDoctorTbl(doctorTbl);
	//				save(membershipTbl);
	//			}
	//		}
	//		response.setError(null);
	//		response.setSuccess(true);
	//		response.setId(doctorTbl.getId());
	//		return response;
	//	}

	/**
	 * view a doctor
	 * 
	 * @param id
	 * @return DoctorDetail
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public DoctorDetail view(int id) throws PersistenceException{
		DoctorDetail response = new DoctorDetail();
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, id);
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(doctorTbl.getId());
		response.setFirstName(doctorTbl.getFirstName());
		response.setLastName(doctorTbl.getLastName());
		response.setAddress(doctorTbl.getAddress());
		response.setDesignation(doctorTbl.getDesignation());
		response.setSpecialization(doctorTbl.getSpecialization());
		if(doctorTbl.getGender()!=null)
			response.setGender(doctorTbl.getGender().getDisplayName());

		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat df1 = new SimpleDateFormat(Constants.YEAR);
		if(doctorTbl.getDateOfBirth()!=null)
			response.setDateOfBirth(df.format(doctorTbl.getDateOfBirth()));
		response.setEmail(doctorTbl.getEmail());
		response.setPhone(doctorTbl.getPhone());
		response.setMobile(doctorTbl.getMobile());

		List<DoctorPracticeExperienceTbl> experienceList = (List<DoctorPracticeExperienceTbl>) getExperienceByDoctorId(doctorTbl
				.getId());
		if (!experienceList.isEmpty()) {
			//get experience
			List<DoctorExperienceDTO> experienceDTOList = new ArrayList<DoctorExperienceDTO>();
			for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceList) {
				DoctorExperienceDTO experience = new DoctorExperienceDTO();
				experience.setDesignation(doctorPracticeExperienceTbl
						.getDesignation());
				if(doctorPracticeExperienceTbl.getFromDate()!=null)
					experience.setFromDate(df.format(doctorPracticeExperienceTbl
							.getFromDate()));
				if(doctorPracticeExperienceTbl.getToDate()!=null)
					experience.setToDate(df.format(doctorPracticeExperienceTbl
							.getToDate()));
				experience.setId(doctorPracticeExperienceTbl.getId());
				experience.setWorkedAt(doctorPracticeExperienceTbl
						.getWorkedAt());
				experienceDTOList.add(experience);
			}
			response.setDoctorExperience(experienceDTOList);
		}
		List<DoctorEducationalQualificationTbl> QualificationList = (List<DoctorEducationalQualificationTbl>) getQualificationByDoctor(doctorTbl
				.getId());
		if (!QualificationList.isEmpty()) {
			//get qualification
			List<DoctorQualificationDTO> qualificationDTOList = new ArrayList<DoctorQualificationDTO>();
			for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : QualificationList) {
				DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();

				doctorQualification
				.setEducationalDegree(doctorEducationalQualificationTbl
						.getEducationalDegree());
				doctorQualification.setId(doctorEducationalQualificationTbl
						.getId());
				doctorQualification
				.setInstitution(doctorEducationalQualificationTbl
						.getInstitution());
				if(doctorEducationalQualificationTbl.getPassedOutDate()!=null)
					doctorQualification.setPassedOutDate(df1.format(doctorEducationalQualificationTbl.getPassedOutDate()));
				qualificationDTOList.add(doctorQualification);
			}
			response.setDoctorQualifications(qualificationDTOList);
		}
		List<DoctorExpertiseTbl>expertiseList=(List<DoctorExpertiseTbl>)getExpertiseByDoctorId(doctorTbl.getId());
		if (!expertiseList.isEmpty()) {
			//get expertise
			List<DoctorExpertiseDTO> doctorExpertiseList=new ArrayList<DoctorExpertiseDTO>();
			for (DoctorExpertiseTbl doctorExpertiseTbl : expertiseList) {

				DoctorExpertiseDTO doctorExpertise=new DoctorExpertiseDTO();
				doctorExpertise.setId(doctorExpertiseTbl.getId());
				doctorExpertise.setExpertise(doctorExpertiseTbl.getExpertise());
				doctorExpertise.setDoctorId(doctorExpertiseTbl.getDoctorTbl().getId());
				doctorExpertiseList.add(doctorExpertise);
			}
			response.setExpertise(doctorExpertiseList);
		}
		List<DoctorMembershipTbl> membershipList=(List<DoctorMembershipTbl>)getMembershipByDoctorId(doctorTbl.getId());
		if (!membershipList.isEmpty()) {
			//get membership
			List<DoctorMembershipDTO> doctorMembershipList=new ArrayList<DoctorMembershipDTO>();
			for(DoctorMembershipTbl doctorMembershipTbl:membershipList){
				DoctorMembershipDTO doctorMembershipDTO=new DoctorMembershipDTO();
				doctorMembershipDTO.setDoctorId(doctorMembershipTbl.getDoctorTbl().getId());
				doctorMembershipDTO.setId(doctorMembershipTbl.getId());
				doctorMembershipDTO.setMembership(doctorMembershipTbl.getMembership());
				doctorMembershipList.add(doctorMembershipDTO);
			}
			response.setMembership(doctorMembershipList);
		}
		List<DoctorAchievementTbl>achievementList=(List<DoctorAchievementTbl>)getAchievementByDoctorId(doctorTbl.getId());
		if(!achievementList.isEmpty()){
			//get achievement
			List<DoctorAchievementDTO> doctorAchievementList=new ArrayList<DoctorAchievementDTO>();
			for(DoctorAchievementTbl doctorAchievementTbl:achievementList){
				DoctorAchievementDTO doctorAchievementDTO=new DoctorAchievementDTO();
				doctorAchievementDTO.setId(doctorAchievementTbl.getId());
				doctorAchievementDTO.setAchievement(doctorAchievementTbl.getAchievement());
				doctorAchievementDTO.setDoctorId(doctorAchievementTbl.getDoctorTbl().getId());
				doctorAchievementList.add(doctorAchievementDTO);
			}
			response.setAchievement(doctorAchievementList);
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * update doctor
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	//	@Override
	//	@Transactional(readOnly = false)
	//	public ResponseDTO update(DoctorDetail doctor) {
	//		ResponseDTO response = new ResponseDTO();
	//		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class,
	//				doctor.getId());
	//		if (doctorTbl == null) {
	//			PersistenceException se = new PersistenceException(
	//					ErrorCodeEnum.DoctorNotFound);
	//			se.addParam(new Parameter(Constants.ID, Integer.toString(doctor
	//					.getId())));
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//		doctorTbl.setFirstName(doctor.getFirstName());
	//		doctorTbl.setLastName(doctor.getLastName());
	//		doctorTbl.setAddress(doctor.getAddress());
	//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
	//		Date date = null;
	//		try {
	//			date = df.parse(doctor.getDateOfBirth());
	//		} catch (ParseException e) {
	//			PersistenceException se = new PersistenceException(
	//					ErrorCodeEnum.InvalidDateFormat);
	//			se.setDisplayErrMsg(true);
	//			throw se;
	//		}
	//
	//		doctorTbl.setDateOfBirth(date);
	//		doctorTbl.setDesignation(doctor.getDesignation());
	//		doctorTbl.setSpecialization(doctor.getSpecialization());
	//		doctorTbl.setEmail(doctor.getEmail());
	//		doctorTbl.setMobile(doctor.getMobile());
	//		doctorTbl.setPhone(doctor.getPhone());
	//		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
	//		doctorTbl.setGender(gender);
	//
	//		if (doctor.getDoctorExperience() != null) {
	//			List<DoctorPracticeExperienceTbl> experienceDelete = new ArrayList<DoctorPracticeExperienceTbl>();
	//			List<DoctorPracticeExperienceTbl> experienceCreate = new ArrayList<DoctorPracticeExperienceTbl>();
	//			List<DoctorPracticeExperienceTbl> experienceUpdate = new ArrayList<DoctorPracticeExperienceTbl>();
	//			//	List<DoctorPracticeExperienceTbl> experienceList = (List<DoctorPracticeExperienceTbl>) getByDoctorId(doctor
	//			//	.getId());
	//
	//			for (DoctorExperienceDTO doctorExperience : doctor
	//					.getDoctorExperience()) {
	//				if (doctorExperience.getActionName().equals(
	//						ActionNameEnum.ADD.getDisplayName())) {
	//					DoctorPracticeExperienceTbl doctorExperienceTbl = new DoctorPracticeExperienceTbl();
	//					doctorExperienceTbl.setDesignation(doctorExperience
	//							.getDesignation());
	//					if (doctorExperience.getFromDate() != null
	//							|| doctorExperience.getToDate() != null) {
	//						Date fromDate = null;
	//						Date toDate = null;
	//						try {
	//							fromDate = df.parse(doctorExperience.getFromDate());
	//							toDate = df.parse(doctorExperience.getToDate());
	//							doctorExperienceTbl.setFromDate(fromDate);
	//							doctorExperienceTbl.setToDate(toDate);
	//						} catch (ParseException e) {
	//							e.printStackTrace();
	//							PersistenceException se = new PersistenceException(
	//									ErrorCodeEnum.InvalidDateFormat);
	//							se.setDisplayErrMsg(true);
	//							throw se;
	//						}
	//
	//					}
	//					doctorExperienceTbl.setWorkedAt(doctorExperience
	//							.getWorkedAt());
	//					doctorExperienceTbl.setDoctorTbl(doctorTbl);
	//					experienceCreate.add(doctorExperienceTbl);
	//				}
	//				if (doctorExperience.getActionName().equals(
	//						ActionNameEnum.DELETE.getDisplayName())) {
	//					DoctorPracticeExperienceTbl doctorExperienceTbl = (DoctorPracticeExperienceTbl) getExperience(
	//							doctorExperience.getId(), doctor.getId());
	//					if (doctorExperienceTbl == null) {
	//						PersistenceException se = new PersistenceException(
	//								ErrorCodeEnum.ExperienceNotExist);
	//						se.setDisplayErrMsg(true);
	//						throw se;
	//					}
	//					experienceDelete.add(doctorExperienceTbl);
	//				}
	//				if (doctorExperience.getActionName().equals(
	//						ActionNameEnum.UPDATE.getDisplayName())) {
	//					DoctorPracticeExperienceTbl doctorExperienceTbl = (DoctorPracticeExperienceTbl) getExperience(
	//							doctorExperience.getId(), doctor.getId());
	//					if (doctorExperienceTbl == null) {
	//						PersistenceException se = new PersistenceException(
	//								ErrorCodeEnum.ExperienceNotExist);
	//						se.setDisplayErrMsg(true);
	//						throw se;
	//					}
	//					doctorExperienceTbl.setDesignation(doctorExperience
	//							.getDesignation());
	//					if (doctorExperience.getFromDate() != null
	//							|| doctorExperience.getToDate() != null) {
	//						Date fromDate = null;
	//						Date toDate = null;
	//						try {
	//							fromDate = df.parse(doctorExperience.getFromDate());
	//							toDate = df.parse(doctorExperience.getToDate());
	//							doctorExperienceTbl.setFromDate(fromDate);
	//							doctorExperienceTbl.setToDate(toDate);
	//						} catch (ParseException e) {
	//							e.printStackTrace();
	//							PersistenceException se = new PersistenceException(
	//									ErrorCodeEnum.InvalidDateFormat);
	//							se.setDisplayErrMsg(true);
	//							throw se;
	//						}
	//
	//					}
	//					doctorExperienceTbl.setWorkedAt(doctorExperience
	//							.getWorkedAt());
	//					doctorExperienceTbl.setDoctorTbl(doctorTbl);
	//					experienceUpdate.add(doctorExperienceTbl);
	//				}
	//			}
	//			if (!experienceCreate.isEmpty()) {
	//				for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceCreate) {
	//					save(doctorPracticeExperienceTbl);
	//				}
	//
	//			}
	//			if (!experienceUpdate.isEmpty()) {
	//				for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceUpdate) {
	//					update(doctorPracticeExperienceTbl);
	//				}
	//
	//			}
	//			if (!experienceDelete.isEmpty()) {
	//				for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceDelete) {
	//					delete(doctorPracticeExperienceTbl);
	//				}
	//
	//			}
	//		}
	//		if (doctor.getDoctorQualifications() != null) {
	//			List<DoctorEducationalQualificationTbl> qualificationDelete = new ArrayList<DoctorEducationalQualificationTbl>();
	//			List<DoctorEducationalQualificationTbl> qualificationCreate = new ArrayList<DoctorEducationalQualificationTbl>();
	//			List<DoctorEducationalQualificationTbl> qualificationUpdate = new ArrayList<DoctorEducationalQualificationTbl>();
	//			for (DoctorQualificationDTO doctorQualificaton : doctor
	//					.getDoctorQualifications()) {
	//				if (doctorQualificaton.getActionName().equals(
	//						ActionNameEnum.ADD.getDisplayName())) {
	//					DoctorEducationalQualificationTbl qualificationTbl = new DoctorEducationalQualificationTbl();
	//
	//					qualificationTbl.setEducationalDegree(doctorQualificaton
	//							.getEducationalDegree());
	//					qualificationTbl.setInstitution(doctorQualificaton
	//							.getInstitution());
	//					qualificationTbl.setDoctorTbl(doctorTbl);
	//					if (doctorQualificaton.getPassedOutDate() != null) {
	//						Date passoutDate = null;
	//						try {
	//							passoutDate = df.parse(doctorQualificaton
	//									.getPassedOutDate());
	//							qualificationTbl.setPassedOutDate(passoutDate);
	//						} catch (ParseException e) {
	//							e.printStackTrace();
	//							PersistenceException se = new PersistenceException(
	//									ErrorCodeEnum.InvalidDateFormat);
	//							se.setDisplayErrMsg(true);
	//							throw se;
	//						}
	//					}
	//
	//					qualificationCreate.add(qualificationTbl);
	//				}
	//				if (doctorQualificaton.getActionName().equals(
	//						ActionNameEnum.DELETE.getDisplayName())) {
	//					DoctorEducationalQualificationTbl doctorQualificationTbl = (DoctorEducationalQualificationTbl) getQualification(
	//							doctorQualificaton.getId(), doctor.getId());
	//					if (doctorQualificationTbl == null) {
	//						PersistenceException se = new PersistenceException(
	//								ErrorCodeEnum.QualificationNotExist);
	//						se.setDisplayErrMsg(true);
	//						throw se;
	//					}
	//					qualificationDelete.add(doctorQualificationTbl);
	//				}
	//				if (doctorQualificaton.getActionName().equals(
	//						ActionNameEnum.UPDATE.getDisplayName())) {
	//					DoctorEducationalQualificationTbl qualificationTbl = (DoctorEducationalQualificationTbl) getQualification(
	//							doctorQualificaton.getId(), doctor.getId());
	//
	//					qualificationTbl.setEducationalDegree(doctorQualificaton
	//							.getEducationalDegree());
	//					qualificationTbl.setInstitution(doctorQualificaton
	//							.getInstitution());
	//					qualificationTbl.setDoctorTbl(doctorTbl);
	//					if (doctorQualificaton.getPassedOutDate() != null) {
	//						Date passoutDate = null;
	//						try {
	//							passoutDate = df.parse(doctorQualificaton
	//									.getPassedOutDate());
	//							qualificationTbl.setPassedOutDate(passoutDate);
	//						} catch (ParseException e) {
	//							e.printStackTrace();
	//							PersistenceException se = new PersistenceException(
	//									ErrorCodeEnum.InvalidDateFormat);
	//							se.setDisplayErrMsg(true);
	//							throw se;
	//						}
	//					}
	//					qualificationUpdate.add(qualificationTbl);
	//				}
	//			}
	//			if (!qualificationCreate.isEmpty()) {
	//				for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : qualificationCreate) {
	//					save(doctorEducationalQualificationTbl);
	//				}
	//
	//			}
	//			if (!qualificationUpdate.isEmpty()) {
	//				for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : qualificationUpdate) {
	//					update(doctorEducationalQualificationTbl);
	//				}
	//
	//			}
	//			if (!qualificationDelete.isEmpty()) {
	//				for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : qualificationDelete) {
	//					delete(doctorEducationalQualificationTbl);
	//				}
	//
	//			}
	//		}
	//		update(doctorTbl);
	//		response.setError(null);
	//		response.setSuccess(true);
	//		response.setId(doctorTbl.getId());
	//		return response;
	//
	//	}

	/**
	 * get doctor by id
	 * 
	 * @param id
	 * @return List<DoctorPracticeExperienceTbl>
	 * @throws PersistenceException 
	 */
	public List<DoctorPracticeExperienceTbl> getExperienceByDoctorId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERIENCE_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorPracticeExperienceTbl.class, query);
	}

	/**
	 * get doctor by experience
	 * 
	 * @param id
	 * @param doctorId
	 * @return DoctorPracticeExperienceTbl
	 * @throws PersistenceException 
	 */
	public DoctorPracticeExperienceTbl getExperience(int id, int doctorId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERIENCE_BY_DOCTORID);
		query.setParameter("param1", id);
		query.setParameter("param2", doctorId);
		return executeUniqueQuery(DoctorPracticeExperienceTbl.class, query);
	}

	/**
	 * get doctor qualification
	 * 
	 * @param id
	 * @param doctorId
	 * @return DoctorEducationalQualificationTbl
	 * @throws PersistenceException 
	 */
	public DoctorEducationalQualificationTbl getQualification(int id,
			int doctorId) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_QUALIFICATION);
		query.setParameter("param1", id);
		query.setParameter("param2", doctorId);
		return executeUniqueQuery(DoctorEducationalQualificationTbl.class,
				query);
	}

	public List<DoctorEducationalQualificationTbl> getQualificationByDoctor(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUALIFICATION_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorEducationalQualificationTbl.class, query);
	}

	/**
	 * get user
	 * 
	 * @param password
	 * @param loginId
	 * @return LoginTbl
	 * @throws PersistenceException 
	 */
	public LoginTbl getUser(String loginId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ALL_USER_FROM_LOGIN);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * create personal details
	 * @param doctor
	 * @return 
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createPersonalInfo(DoctorPersonalDTO doctor) throws PersistenceException {
		boolean doctorUpdate=false;
		ResponseDTO response = new ResponseDTO();	
		DoctorTbl doctorTbl =null;
		LoginTbl loginTbl = null;
		LoginTbl existingUser = (LoginTbl) getUser(doctor.getLogin().getUserName().trim());
		if (existingUser != null ) {
			if(existingUser.getStatus().equals(StatusEnum.Active)){
					PersistenceException se = new PersistenceException(ErrorCodeEnum.UserExists);
					se.setDisplayErrMsg(true);
					throw se;
			}
			else{
				existingUser.setStatus(StatusEnum.Active);
				doctorUpdate=true;
				update(existingUser);
			}

		}
		else{
				 loginTbl = new LoginTbl();	
				loginTbl.setUserName(doctor.getLogin().getUserName().trim());
				loginTbl.setStatus(StatusEnum.Active);
				UserTypeEnum userType = UserTypeEnum.getEnum(doctor.getLogin().getUserType());
				loginTbl.setUserType(userType);
				save(loginTbl);
		}	

		if(!doctorUpdate)
			doctorTbl = new DoctorTbl();
		else
			doctorTbl=getDoctorByEmail(doctor.getEmail().trim());
		
		doctorTbl.setFirstName(doctor.getFirstName());
		doctorTbl.setLastName(doctor.getLastName());
		doctorTbl.setAddress(doctor.getAddress());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date date = null;
		try {
			if(doctor.getDateOfBirth()!=null)
				date = df.parse(doctor.getDateOfBirth().trim());
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		doctorTbl.setWorkHistory(doctor.getWorkHistory());
		doctorTbl.setWorkingPlaces(doctor.getWorkingPlaces());
		doctorTbl.setDateOfBirth(date);
		doctorTbl.setDesignation(doctor.getDesignation());
		doctorTbl.setSpecialization(doctor.getSpecialization());
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		doctorTbl.setGender(gender);
		doctorTbl.setEmail(doctor.getEmail().trim());
		doctorTbl.setMobile(doctor.getMobile());
		doctorTbl.setPhone(doctor.getPhone());
		doctorTbl.setStatus(StatusEnum.Active);
		Date currentTime=new Date();
		doctorTbl.setCreatedTime(currentTime);
		doctorTbl.setUpdatedTime(currentTime);
		doctorTbl.setLoginTbl(loginTbl);
		
		if(!doctorUpdate)
			save(doctorTbl);
		else
			update(doctorTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(doctorTbl.getId());
		return response;
	}
	private String capitalizeFirstLetter(String original){		
	    if(original==null || original=="" || original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
		
	}
	/**
	 * doctor qualification 
	 * @param qualification
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO  doctorQualification(DoctorListQualificationDTO qualification) throws PersistenceException {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		DateFormat df = new SimpleDateFormat(Constants.YEAR);
		for (DoctorQualificationDTO doctorQualificationDTO : qualification.getQualification()) {
			ActionNameEnum actionName=ActionNameEnum.getEnum(doctorQualificationDTO.getActionName());
			if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorQualificationDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorQualificationDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorEducationalQualificationTbl qualificationTbl=new DoctorEducationalQualificationTbl();
				qualificationTbl.setEducationalDegree(doctorQualificationDTO.getEducationalDegree());
				qualificationTbl.setInstitution(doctorQualificationDTO.getInstitution());
				if (doctorQualificationDTO.getPassedOutDate() != null) {
					Date passoutDate = null;
					try {
						passoutDate = df.parse(doctorQualificationDTO
								.getPassedOutDate());
						qualificationTbl.setPassedOutDate(passoutDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}
				}
				qualificationTbl.setDoctorTbl(doctorTbl);
				save(qualificationTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);				
				update(doctorTbl);
			}
			if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
				DoctorEducationalQualificationTbl qualificationTbl=(DoctorEducationalQualificationTbl)getById(DoctorEducationalQualificationTbl.class,doctorQualificationDTO.getId());
				if(qualificationTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorQualificationDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorQualificationDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				qualificationTbl.setEducationalDegree(doctorQualificationDTO.getEducationalDegree());
				qualificationTbl.setInstitution(doctorQualificationDTO.getInstitution());
				if (doctorQualificationDTO.getPassedOutDate() != null) {
					Date passoutDate = null;
					try {
						passoutDate = df.parse(doctorQualificationDTO
								.getPassedOutDate());
						qualificationTbl.setPassedOutDate(passoutDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}
				}
				qualificationTbl.setDoctorTbl(doctorTbl);
				update(qualificationTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
				DoctorEducationalQualificationTbl qualificationTbl=(DoctorEducationalQualificationTbl)getById(DoctorEducationalQualificationTbl.class,doctorQualificationDTO.getId());
				if(qualificationTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorQualificationDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorQualificationDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				delete(qualificationTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * doctor achievement
	 * @param achievement
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO doctorAchievement(DoctorListAchievementDTO achievement) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();

		for (DoctorAchievementDTO doctorAchievementDTO : achievement.getAchievement()) {

			ActionNameEnum actionName=ActionNameEnum.getEnum(doctorAchievementDTO.getActionName());
			if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){

				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorAchievementDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorAchievementDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorAchievementTbl achievementTbl=new DoctorAchievementTbl();
				achievementTbl.setAchievement(doctorAchievementDTO.getAchievement());
				achievementTbl.setDoctorTbl(doctorTbl);
				save(achievementTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){

				DoctorAchievementTbl achievementTbl=(DoctorAchievementTbl)getById(DoctorAchievementTbl.class,doctorAchievementDTO.getId());
				if(achievementTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorAchievementDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorAchievementDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				achievementTbl.setAchievement(doctorAchievementDTO.getAchievement());
				achievementTbl.setDoctorTbl(doctorTbl);				
				update(achievementTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){

				DoctorAchievementTbl achievementTbl=(DoctorAchievementTbl)getById(DoctorAchievementTbl.class,doctorAchievementDTO.getId());
				if(achievementTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorAchievementDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorAchievementDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				delete(achievementTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Doctor experience
	 * @param experience
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO doctorExperience(DoctorListExperienceDTO experience) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date fromDate=null;
		Date toDate=null;
		for (DoctorExperienceDTO doctorExperienceDTO : experience.getExperience()) {

			ActionNameEnum actionName=ActionNameEnum.getEnum(doctorExperienceDTO.getActionName());
			if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){

				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExperienceDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExperienceDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorPracticeExperienceTbl experienceTbl=new DoctorPracticeExperienceTbl();
				experienceTbl.setDoctorTbl(doctorTbl);
				experienceTbl.setDesignation(doctorExperienceDTO.getDesignation());
				experienceTbl.setWorkedAt(doctorExperienceDTO.getWorkedAt());
				try {
					if(doctorExperienceDTO.getFromDate()!=null && doctorExperienceDTO.getToDate()!=null){

						fromDate=df.parse(doctorExperienceDTO.getFromDate());
						experienceTbl.setFromDate(fromDate);

						toDate=df.parse(doctorExperienceDTO.getToDate());
						experienceTbl.setToDate(toDate);
					}

				}catch (Exception e) {
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidDateFormat);
					se.setDisplayErrMsg(true);
					throw se;
				}
				save(experienceTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){

				DoctorPracticeExperienceTbl experienceTbl=(DoctorPracticeExperienceTbl)getById(DoctorPracticeExperienceTbl.class,doctorExperienceDTO.getId());
				if(experienceTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExperienceDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExperienceDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				experienceTbl.setDoctorTbl(doctorTbl);
				experienceTbl.setDesignation(doctorExperienceDTO.getDesignation());
				experienceTbl.setWorkedAt(doctorExperienceDTO.getWorkedAt());
				try {
					if(doctorExperienceDTO.getFromDate()!=null && doctorExperienceDTO.getToDate()!=null){

						fromDate=df.parse(doctorExperienceDTO.getFromDate());
						experienceTbl.setFromDate(fromDate);

						toDate=df.parse(doctorExperienceDTO.getToDate());
						experienceTbl.setToDate(toDate);
					}

				}catch (Exception e) {
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidDateFormat);
					se.setDisplayErrMsg(true);
					throw se;
				}
				update(experienceTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){

				DoctorPracticeExperienceTbl experienceTbl=(DoctorPracticeExperienceTbl)getById(DoctorPracticeExperienceTbl.class,doctorExperienceDTO.getId());
				if(experienceTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExperienceDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExperienceDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				delete(experienceTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Doctor expertise
	 * @param expertise
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO doctorExpertise(DoctorListExpertiseDTO expertise) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();

		for (DoctorExpertiseDTO doctorExpertiseDTO : expertise.getExpertise()) {

			ActionNameEnum actionName=ActionNameEnum.getEnum(doctorExpertiseDTO.getActionName());
			if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){

				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExpertiseDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExpertiseDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorExpertiseTbl expertiseTbl=new DoctorExpertiseTbl();
				expertiseTbl.setDoctorTbl(doctorTbl);
				expertiseTbl.setExpertise(doctorExpertiseDTO.getExpertise());

				save(expertiseTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){

				DoctorExpertiseTbl expertiseTbl=(DoctorExpertiseTbl)getById(DoctorExpertiseTbl.class,doctorExpertiseDTO.getId());
				if(expertiseTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExpertiseDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExpertiseDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				expertiseTbl.setDoctorTbl(doctorTbl);
				expertiseTbl.setExpertise(doctorExpertiseDTO.getExpertise());
				update(expertiseTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){

				DoctorExpertiseTbl expertiseTbl=(DoctorExpertiseTbl)getById(DoctorExpertiseTbl.class,doctorExpertiseDTO.getId());
				if(expertiseTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorExpertiseDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorExpertiseDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				delete(expertiseTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * doctor membership
	 * @param membership
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO doctorMembership(DoctorListMembershipDTO membership) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();

		for (DoctorMembershipDTO doctorMembershipDTO : membership.getMembership()) {

			ActionNameEnum actionName=ActionNameEnum.getEnum(doctorMembershipDTO.getActionName());
			if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){

				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorMembershipDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorMembershipDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorMembershipTbl membershipTbl=new DoctorMembershipTbl();
				membershipTbl.setDoctorTbl(doctorTbl);
				membershipTbl.setMembership(doctorMembershipDTO.getMembership());

				save(membershipTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){

				DoctorMembershipTbl membershipTbl=(DoctorMembershipTbl)getById(DoctorMembershipTbl.class,doctorMembershipDTO.getId());
				if(membershipTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorMembershipDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorMembershipDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}

				membershipTbl.setDoctorTbl(doctorTbl);
				membershipTbl.setMembership(doctorMembershipDTO.getMembership());
				update(membershipTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
			if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){

				DoctorExpertiseTbl expertiseTbl=(DoctorExpertiseTbl)getById(DoctorExpertiseTbl.class,doctorMembershipDTO.getId());
				if(expertiseTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.InvalidId);

					se.setDisplayErrMsg(true);
					throw se;
				}
				DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorMembershipDTO.getDoctorId());
				if(doctorTbl==null){
					PersistenceException se = new PersistenceException(
							ErrorCodeEnum.DoctorNotFound);
					se.addParam(new Parameter(Constants.ID, Integer.toString(doctorMembershipDTO.getDoctorId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				delete(expertiseTbl);
				Date currentTime=new Date();				
				doctorTbl.setUpdatedTime(currentTime);	
				update(doctorTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * update personal information of a doctor
	 * @param doctor
	 * @return ResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updatePersonalInfo(DoctorPersonalDTO doctor) throws PersistenceException {
		ResponseDTO response = new ResponseDTO();

		/*update doctor info in doctor table */
		DoctorTbl doctorTbl = (DoctorTbl)getById(DoctorTbl.class, doctor.getId());
		if(doctorTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(doctor.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String firstName=capitalizeFirstLetter(doctor.getFirstName());
		doctorTbl.setFirstName(doctor.getFirstName());
		//String lastName=capitalizeFirstLetter(doctor.getLastName());
		doctorTbl.setLastName(doctor.getLastName());
//		doctorTbl.setFirstName(doctor.getFirstName());
//		doctorTbl.setLastName(doctor.getLastName());
		doctorTbl.setAddress(doctor.getAddress());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date date = null;
		try {
			date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		doctorTbl.setWorkHistory(doctor.getWorkHistory());
		doctorTbl.setWorkingPlaces(doctor.getWorkingPlaces());
		doctorTbl.setDateOfBirth(date);
		doctorTbl.setDesignation(doctor.getDesignation());
		doctorTbl.setSpecialization(doctor.getSpecialization());
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		doctorTbl.setGender(gender);
		doctorTbl.setEmail(doctor.getEmail());
		doctorTbl.setMobile(doctor.getMobile());
		doctorTbl.setPhone(doctor.getPhone());	
		Date currentTime=new Date();				
		doctorTbl.setUpdatedTime(currentTime);	
		update(doctorTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(doctorTbl.getId());
		return response;
	}

	public List<DoctorExpertiseTbl> getExpertiseByDoctorId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERTISE_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorExpertiseTbl.class, query);
	}
	public List<DoctorMembershipTbl> getMembershipByDoctorId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEMBERSHIP_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorMembershipTbl.class, query);
	}
	public List<DoctorAchievementTbl> getAchievementByDoctorId(int id) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ACHIEVEMENT_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorAchievementTbl.class, query);
	}

	/**
	 * get newly created doctor for sync
	 * @return List<DoctorDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<DoctorDetail> getNewDoctor() throws PersistenceException {
		// TODO Auto-generated method stub
		//String lastSyncTime=getLastSyncTime();
		List<DoctorTbl> doctorListTbl=(ArrayList<DoctorTbl>)getNewilyCreatedDoctor();
		List<DoctorDetail>doctorDetailList=new ArrayList<DoctorDetail>();
		if(!doctorListTbl.isEmpty()){
			for (DoctorTbl doctorTbl : doctorListTbl) {
				DoctorDetail doctorDetail=getDoctorDetails(doctorTbl);
				doctorDetailList.add(doctorDetail);
			}		
		}
		return doctorDetailList;
	}
	public List<DoctorTbl> getNewilyCreatedDoctor( ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_DOCTOR);
		query.setParameter("param1", 0);
		return executeQuery(DoctorTbl.class, query);
	}

	/**
	 * get doctor details
	 * @param doctorTbl
	 * @return DoctorDetail
	 * @throws PersistenceException 
	 */
	public DoctorDetail getDoctorDetails(DoctorTbl doctorTbl) throws PersistenceException{
		DoctorDetail response = new DoctorDetail();		
		response.setId(doctorTbl.getId());
		response.setGlobalId(doctorTbl.getGlobalId());
		response.setFirstName(doctorTbl.getFirstName());
		response.setLastName(doctorTbl.getLastName());
		response.setAddress(doctorTbl.getAddress());
		response.setDesignation(doctorTbl.getDesignation());
		response.setSpecialization(doctorTbl.getSpecialization());
		if(doctorTbl.getGender()!=null)
			response.setGender(doctorTbl.getGender().getDisplayName());
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT);
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		if(doctorTbl.getDateOfBirth()!=null)
			response.setDateOfBirth(df.format(doctorTbl.getDateOfBirth()));
		response.setEmail(doctorTbl.getEmail());
		response.setPhone(doctorTbl.getPhone());
		response.setMobile(doctorTbl.getMobile());
		if(doctorTbl.getStatus()!=null)
			response.setStatus(doctorTbl.getStatus().getDisplayName());
		if(doctorTbl.getCreatedTime()!=null)
			response.setCreateDateTime(df1.format(doctorTbl.getCreatedTime()));
		if(doctorTbl.getUpdatedTime()!=null)
			response.setUpdateDateTime(df1.format(doctorTbl.getUpdatedTime()));
		List<DoctorPracticeExperienceTbl> experienceList = (List<DoctorPracticeExperienceTbl>) getExperienceByDoctorId(doctorTbl
				.getId());
		if (!experienceList.isEmpty()) {
			//get experience
			List<DoctorExperienceDTO> experienceDTOList = new ArrayList<DoctorExperienceDTO>();
			for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceList) {
				DoctorExperienceDTO experience = new DoctorExperienceDTO();
				experience.setDesignation(doctorPracticeExperienceTbl
						.getDesignation());
				if(doctorPracticeExperienceTbl.getFromDate()!=null)
					experience.setFromDate(df.format(doctorPracticeExperienceTbl
							.getFromDate()));
				if(doctorPracticeExperienceTbl.getToDate()!=null)
					experience.setToDate(df.format(doctorPracticeExperienceTbl
							.getToDate()));
				experience.setId(doctorPracticeExperienceTbl.getId());
				experience.setWorkedAt(doctorPracticeExperienceTbl
						.getWorkedAt());
				experienceDTOList.add(experience);
			}
			response.setDoctorExperience(experienceDTOList);
		}
		List<DoctorEducationalQualificationTbl> QualificationList = (List<DoctorEducationalQualificationTbl>) getQualificationByDoctor(doctorTbl
				.getId());
		if (!QualificationList.isEmpty()) {
			//get qualification
			List<DoctorQualificationDTO> qualificationDTOList = new ArrayList<DoctorQualificationDTO>();
			for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : QualificationList) {
				DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();

				doctorQualification
				.setEducationalDegree(doctorEducationalQualificationTbl
						.getEducationalDegree());
				doctorQualification.setId(doctorEducationalQualificationTbl
						.getId());
				doctorQualification
				.setInstitution(doctorEducationalQualificationTbl
						.getInstitution());
				if(doctorEducationalQualificationTbl.getPassedOutDate()!=null)
					doctorQualification.setPassedOutDate(df.format(doctorEducationalQualificationTbl.getPassedOutDate()));
				qualificationDTOList.add(doctorQualification);
			}
			response.setDoctorQualifications(qualificationDTOList);
		}
		List<DoctorExpertiseTbl>expertiseList=(List<DoctorExpertiseTbl>)getExpertiseByDoctorId(doctorTbl.getId());
		if (!expertiseList.isEmpty()) {
			//get expertise
			List<DoctorExpertiseDTO> doctorExpertiseList=new ArrayList<DoctorExpertiseDTO>();
			for (DoctorExpertiseTbl doctorExpertiseTbl : expertiseList) {

				DoctorExpertiseDTO doctorExpertise=new DoctorExpertiseDTO();
				doctorExpertise.setId(doctorExpertiseTbl.getId());
				doctorExpertise.setExpertise(doctorExpertiseTbl.getExpertise());
				doctorExpertise.setDoctorId(doctorExpertiseTbl.getDoctorTbl().getId());
				doctorExpertiseList.add(doctorExpertise);
			}
			response.setExpertise(doctorExpertiseList);
		}
		List<DoctorMembershipTbl> membershipList=(List<DoctorMembershipTbl>)getMembershipByDoctorId(doctorTbl.getId());
		if (!membershipList.isEmpty()) {
			//get membership
			List<DoctorMembershipDTO> doctorMembershipList=new ArrayList<DoctorMembershipDTO>();
			for(DoctorMembershipTbl doctorMembershipTbl:membershipList){
				DoctorMembershipDTO doctorMembershipDTO=new DoctorMembershipDTO();
				doctorMembershipDTO.setDoctorId(doctorMembershipTbl.getDoctorTbl().getId());
				doctorMembershipDTO.setId(doctorMembershipTbl.getId());
				doctorMembershipDTO.setMembership(doctorMembershipTbl.getMembership());
				doctorMembershipList.add(doctorMembershipDTO);
			}
			response.setMembership(doctorMembershipList);
		}
		List<DoctorAchievementTbl>achievementList=(List<DoctorAchievementTbl>)getAchievementByDoctorId(doctorTbl.getId());
		if(!achievementList.isEmpty()){
			//get achievement
			List<DoctorAchievementDTO> doctorAchievementList=new ArrayList<DoctorAchievementDTO>();
			for(DoctorAchievementTbl doctorAchievementTbl:achievementList){
				DoctorAchievementDTO doctorAchievementDTO=new DoctorAchievementDTO();
				doctorAchievementDTO.setId(doctorAchievementTbl.getId());
				doctorAchievementDTO.setAchievement(doctorAchievementTbl.getAchievement());
				doctorAchievementDTO.setDoctorId(doctorAchievementTbl.getDoctorTbl().getId());
				doctorAchievementList.add(doctorAchievementDTO);
			}
			response.setAchievement(doctorAchievementList);
		}
		return response;
	}
	/**
	 * get deleted doctor and give to portal
	 * @return List<DoctorDetail>
	 * @throws PersistenceException 
	 */

	@Override
	@Transactional(readOnly=false)
	public List<DoctorDetail> getDeletedDoctor() throws PersistenceException {
		List<DoctorDetail>doctorDetailList=new ArrayList<DoctorDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<DoctorTbl> doctorListTbl=(ArrayList<DoctorTbl>)getDeletedDoctorList(syncTbl.getUploadedTime(),StatusEnum.Inactive);

			if(!doctorListTbl.isEmpty()){
				for (DoctorTbl doctorTbl : doctorListTbl) {
					DoctorDetail doctorDetail=getDoctorDetails(doctorTbl);
					doctorDetailList.add(doctorDetail);
				}
			}
		}
		return doctorDetailList;
	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}
	public List<DoctorTbl> getDeletedDoctorList(Date lastSyncTime,StatusEnum status ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DELETED_DOCTOR);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", status);
		return executeQuery(DoctorTbl.class, query);
	}

	/**
	 * update with the global id for newly created doctor 
	 * @param doctorResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void addDoctorSyncResponse(DoctorResponse doctorResponse)throws PersistenceException {
		// TODO Auto-generated method stub
		if(doctorResponse.getError()==null){
			DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorResponse.getId());
			if (doctorTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DoctorNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(doctorResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			doctorTbl.setGlobalId(doctorResponse.getGlobalId());
			update(doctorTbl);
		}
	}

	/**
	 * update the details of updated doctor 
	 * @param doctorResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateDoctorSyncResponse(DoctorResponse doctorResponse) throws PersistenceException{
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(doctorResponse.getError()!=null){
			DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorResponse.getId());
			if (doctorTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DoctorNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(doctorResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				if(doctorResponse.getUpdateDateTime()!=null)
					doctorTbl.setUpdatedTime(df.parse(doctorResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("Error while updating the doctor",e);
				e.printStackTrace();
			}
			update(doctorTbl);
		}

	}
	/**
	 * update the details of deleted doctor 
	 * @param doctorResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteDoctorSyncResponse(DoctorResponse doctorResponse)throws PersistenceException {
		// TODO Auto-generated method stub
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(doctorResponse.getError()!=null){
			DoctorTbl doctorTbl=(DoctorTbl)getById(DoctorTbl.class,doctorResponse.getId());
			if (doctorTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DoctorNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(doctorResponse.getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				if(doctorResponse.getUpdateDateTime()!=null)
					doctorTbl.setUpdatedTime(df.parse(doctorResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("Error while deleting the doctor",e);
				e.printStackTrace();
			}
			update(doctorTbl);
		}

	}
	/**
	 * get updated doctor and give to portal
	 * @return List<DoctorDetail>
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<DoctorDetail> getUpdatedDoctor() throws PersistenceException {
		// TODO Auto-generated method stub
		//Date lastSyncTime = null;
		List<DoctorDetail>doctorDetailList=new ArrayList<DoctorDetail>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){					
			List<DoctorTbl> doctorListTbl=(ArrayList<DoctorTbl>)getUpdatedDoctorList(syncTbl.getUploadedTime());

			if(!doctorListTbl.isEmpty()){
				for (DoctorTbl doctorTbl : doctorListTbl) {
					DoctorDetail doctorDetail=getDoctorDetails(doctorTbl);
					doctorDetailList.add(doctorDetail);
				}
			}
		}
		return doctorDetailList;

	}
	public List<DoctorTbl> getUpdatedDoctorList(Date lastUploadedTime ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_DOCTOR);
		query.setParameter("param1", lastUploadedTime);
		return executeQuery(DoctorTbl.class, query);
	}

	/**
	 * doctor from YNW
	 * @param doctorDetail
	 * @return DoctorResponse
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public DoctorResponse doctorFromYNW(DoctorDetail doctorDetail) throws PersistenceException {
		DoctorResponse response=new DoctorResponse();
		ResponseDTO responseDTO=new ResponseDTO();
		DoctorTbl doctorTbl = (DoctorTbl) getDoctorByGlobalId(doctorDetail.getGlobalId());
		if (doctorTbl == null) {
			responseDTO=doctorCreateFromYNW(doctorDetail);
			response.setError(responseDTO.getError());
			response.setSuccess(responseDTO.isSuccess());
			return response;

		}
		responseDTO=doctorUpdateromYNW(doctorDetail);
		response.setError(responseDTO.getError());
		response.setSuccess(responseDTO.isSuccess());
		return response;
	}
	private ResponseDTO doctorCreateFromYNW(DoctorDetail doctor ) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();
		//System.out.println("first name"+doctor.getFirstName());
		/* Saving username and password in login tbl */
		//		StringEncoder strencoder = new StringEncoder();
		//		String password = strencoder.encryptWithKey(doctor.getLogin()
		//				.getPassword());
		LoginTbl existingUser = (LoginTbl) getUser(doctor.getLogin()
				.getUserName().trim());
		if (existingUser != null) {

			PersistenceException se = new PersistenceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LoginTbl loginTbl = new LoginTbl();
		loginTbl.setUserName(doctor.getLogin().getUserName().trim());
		loginTbl.setPassword(doctor.getLogin().getPassword());
		UserTypeEnum userType = UserTypeEnum.getEnum(doctor.getLogin().getUserType());
		loginTbl.setUserType(userType);
		save(loginTbl);

		/* Saving doctor info in doctor tbl */
		DoctorTbl doctorTbl = new DoctorTbl();
		doctorTbl.setGlobalId(doctor.getGlobalId());
		String firstName=capitalizeFirstLetter(doctor.getFirstName());
		doctorTbl.setFirstName(firstName.trim());
		String lastName=capitalizeFirstLetter(doctor.getLastName());
		doctorTbl.setLastName(lastName);
//		doctorTbl.setFirstName(doctor.getFirstName());
//		doctorTbl.setLastName(doctor.getLastName());
		doctorTbl.setAddress(doctor.getAddress());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat df1 = new SimpleDateFormat(Constants.YEAR);
		Date date = null;
		try {
			date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		doctorTbl.setWorkHistory(doctor.getWorkHistory());
		doctorTbl.setWorkingPlaces(doctor.getWorkingPlaces());
		doctorTbl.setDateOfBirth(date);
		doctorTbl.setDesignation(doctor.getDesignation());
		doctorTbl.setSpecialization(doctor.getSpecialization());
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		doctorTbl.setGender(gender);
		doctorTbl.setEmail(doctor.getEmail());
		doctorTbl.setMobile(doctor.getMobile());
		doctorTbl.setPhone(doctor.getPhone());
		StatusEnum status=StatusEnum.getEnum(doctor.getStatus());
		doctorTbl.setStatus(status);
		Date currentTime=new Date();
		doctorTbl.setCreatedTime(currentTime);
		doctorTbl.setUpdatedTime(currentTime);
		doctorTbl.setLoginTbl(loginTbl);
		save(doctorTbl);
		if (doctor.getDoctorExperience() != null) {
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				DoctorPracticeExperienceTbl doctorExperienceTbl = new DoctorPracticeExperienceTbl();
				doctorExperienceTbl.setDesignation(doctorExperience
						.getDesignation());
				if (doctorExperience.getFromDate() != null
						|| doctorExperience.getToDate() != null) {
					Date fromDate = null;
					Date toDate = null;
					try {
						fromDate = df.parse(doctorExperience.getFromDate());
						toDate = df.parse(doctorExperience.getToDate());
						doctorExperienceTbl.setFromDate(fromDate);
						doctorExperienceTbl.setToDate(toDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}

				}
				doctorExperienceTbl.setWorkedAt(doctorExperience.getWorkedAt());
				doctorExperienceTbl.setDoctorTbl(doctorTbl);
				save(doctorExperienceTbl);

			}
		}
		if (doctor.getDoctorQualifications() != null) {
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {
				DoctorEducationalQualificationTbl QualificationTbl = new DoctorEducationalQualificationTbl();

				QualificationTbl.setEducationalDegree(doctorQualificaton
						.getEducationalDegree());
				QualificationTbl.setInstitution(doctorQualificaton
						.getInstitution());
				QualificationTbl.setDoctorTbl(doctorTbl);
				if (doctorQualificaton.getPassedOutDate() != null) {
					Date passoutDate = null;
					try {
						passoutDate = df1.parse(doctorQualificaton
								.getPassedOutDate());
						QualificationTbl.setPassedOutDate(passoutDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}
				}

				save(QualificationTbl);
			}
		}
		if (doctor.getAchievement() != null) {
			for (DoctorAchievementDTO doctorAchievementDTO : doctor
					.getAchievement()) {
				DoctorAchievementTbl achievementTbl = new DoctorAchievementTbl();
				achievementTbl.setAchievement(doctorAchievementDTO
						.getAchievement());
				achievementTbl.setDoctorTbl(doctorTbl);
				save(achievementTbl);
			}
		}
		if (doctor.getExpertise() != null) {
			for (DoctorExpertiseDTO doctorExpertiseDTO : doctor.getExpertise()) {
				DoctorExpertiseTbl expertiseTbl = new DoctorExpertiseTbl();
				expertiseTbl.setExpertise(doctorExpertiseDTO.getExpertise());
				expertiseTbl.setDoctorTbl(doctorTbl);
				save(expertiseTbl);
			}
		}
		if (doctor.getMembership() != null) {
			for (DoctorMembershipDTO doctorMembershipDTO : doctor
					.getMembership()) {
				DoctorMembershipTbl membershipTbl = new DoctorMembershipTbl();
				membershipTbl
				.setMembership(doctorMembershipDTO.getMembership());
				membershipTbl.setDoctorTbl(doctorTbl);
				save(membershipTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(doctorTbl.getId());
		return response;
	}
	private ResponseDTO doctorUpdateromYNW(DoctorDetail doctor ) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();



		/* Saving doctor info in doctor tbl */
		DoctorTbl doctorTbl = (DoctorTbl) getDoctorByGlobalId(doctor.getGlobalId());
		if (doctorTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(doctor.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Saving username and password in login tbl */
		if(doctorTbl.getLoginTbl()!=null){
			LoginTbl loginTbl = (LoginTbl)getById(LoginTbl.class, doctorTbl.getLoginTbl().getId());
			if (loginTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.LoginWithIdNotExist);
				se.addParam(new Parameter(Constants.ID, Integer
						.toString(doctorTbl.getLoginTbl().getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}

			loginTbl.setPassword(doctor.getLogin().getPassword());
			loginTbl.setUserName(doctor.getLogin().getUserName().trim());

			UserTypeEnum userType = UserTypeEnum.getEnum(doctor.getLogin().getUserType());
			loginTbl.setUserType(userType);
			update(loginTbl);
		}
		//doctorTbl.setGlobalId(doctor.getGlobalId());
		String firstName=capitalizeFirstLetter(doctor.getFirstName());
		doctorTbl.setFirstName(firstName.trim());
		String lastName=capitalizeFirstLetter(doctor.getLastName());
		doctorTbl.setLastName(lastName);
//		doctorTbl.setFirstName(doctor.getFirstName());
//		doctorTbl.setLastName(doctor.getLastName());
		doctorTbl.setAddress(doctor.getAddress());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat df2=new SimpleDateFormat(Constants.TIME);
		DateFormat df1 = new SimpleDateFormat(Constants.YEAR);
		Date date = null;
		try {
			date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		doctorTbl.setWorkHistory(doctor.getWorkHistory());
		doctorTbl.setWorkingPlaces(doctor.getWorkingPlaces());
		doctorTbl.setDateOfBirth(date);
		doctorTbl.setDesignation(doctor.getDesignation());
		doctorTbl.setSpecialization(doctor.getSpecialization());
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		doctorTbl.setGender(gender);
		doctorTbl.setEmail(doctor.getEmail());
		doctorTbl.setMobile(doctor.getMobile());
		doctorTbl.setPhone(doctor.getPhone());
		StatusEnum status=StatusEnum.getEnum(doctor.getStatus());
		if(status.equals(StatusEnum.Inactive)){
			List<DoctorScheduleTbl> doctorScheduleList=(ArrayList<DoctorScheduleTbl>)getScheduleByDoctor(doctorTbl.getId());
			if(!doctorScheduleList.isEmpty()){
				for (DoctorScheduleTbl doctorScheduleTbl : doctorScheduleList) {
					List<PatientAppointmentTbl> appointmentList=(ArrayList<PatientAppointmentTbl>)getAppointmentByScheduleId(doctorScheduleTbl.getId());
					if (!appointmentList.isEmpty()) {
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
					doctorScheduleTbl.setUpdatedTime(new Date());
					doctorScheduleTbl.setStatus(StatusEnum.Inactive);
					update(doctorScheduleTbl);
					//inactivate schedule
				}
			}
		}
		doctorTbl.setStatus(status);
		Date currentTime=new Date();
		doctorTbl.setCreatedTime(currentTime);
		doctorTbl.setUpdatedTime(currentTime);
		//doctorTbl.setLoginTbl(loginTbl);
		update(doctorTbl);
		if (doctor.getDoctorExperience() != null) {
			List<DoctorPracticeExperienceTbl> experienceList = (ArrayList<DoctorPracticeExperienceTbl>) getExperienceByDoctorId(doctor
					.getId());
			if(!experienceList.isEmpty()){
				for (DoctorPracticeExperienceTbl doctorPracticeExperienceTbl : experienceList) {
					delete(doctorPracticeExperienceTbl);
				}
			}
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				DoctorPracticeExperienceTbl doctorExperienceTbl = new DoctorPracticeExperienceTbl();
				doctorExperienceTbl.setDesignation(doctorExperience
						.getDesignation());
				if (doctorExperience.getFromDate() != null
						|| doctorExperience.getToDate() != null) {
					Date fromDate = null;
					Date toDate = null;
					try {
						fromDate = df.parse(doctorExperience.getFromDate());
						toDate = df.parse(doctorExperience.getToDate());
						doctorExperienceTbl.setFromDate(fromDate);
						doctorExperienceTbl.setToDate(toDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}

				}
				doctorExperienceTbl.setWorkedAt(doctorExperience.getWorkedAt());
				doctorExperienceTbl.setDoctorTbl(doctorTbl);
				save(doctorExperienceTbl);

			}
		}
		if (doctor.getDoctorQualifications() != null) {
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {
				List<DoctorEducationalQualificationTbl> qualificationList = (ArrayList<DoctorEducationalQualificationTbl>) getQualificationByDoctor(doctorTbl
						.getId());
				if(!qualificationList.isEmpty()){
					for (DoctorEducationalQualificationTbl doctorEducationalQualificationTbl : qualificationList) {
						delete(doctorEducationalQualificationTbl);
					}
				}
				DoctorEducationalQualificationTbl QualificationTbl = new DoctorEducationalQualificationTbl();

				QualificationTbl.setEducationalDegree(doctorQualificaton
						.getEducationalDegree());
				QualificationTbl.setInstitution(doctorQualificaton
						.getInstitution());
				QualificationTbl.setDoctorTbl(doctorTbl);
				if (doctorQualificaton.getPassedOutDate() != null) {
					Date passoutDate = null;
					try {
						passoutDate = df1.parse(doctorQualificaton
								.getPassedOutDate());
						QualificationTbl.setPassedOutDate(passoutDate);
					} catch (ParseException e) {
						e.printStackTrace();
						PersistenceException se = new PersistenceException(
								ErrorCodeEnum.InvalidDateFormat);
						se.setDisplayErrMsg(true);
						throw se;
					}
				}

				save(QualificationTbl);
			}
		}
		if (doctor.getAchievement() != null) {
			List<DoctorAchievementTbl> achievementList = (ArrayList<DoctorAchievementTbl>) getAchievementByDoctorId(doctorTbl
					.getId());
			if(!achievementList.isEmpty()){
				for (DoctorAchievementTbl doctorAchievementTbl : achievementList) {
					delete(doctorAchievementTbl);
				}
			}
			for (DoctorAchievementDTO doctorAchievementDTO : doctor
					.getAchievement()) {
				DoctorAchievementTbl achievementTbl = new DoctorAchievementTbl();
				achievementTbl.setAchievement(doctorAchievementDTO
						.getAchievement());
				achievementTbl.setDoctorTbl(doctorTbl);
				save(achievementTbl);
			}
		}
		if (doctor.getExpertise() != null) {
			List<DoctorExpertiseTbl>expertiseList=(ArrayList<DoctorExpertiseTbl>)getExpertiseByDoctorId(doctorTbl
					.getId());
			if(!expertiseList.isEmpty()){
				for (DoctorExpertiseTbl doctorExpertiseTbl : expertiseList) {
					delete(doctorExpertiseTbl);
				}
			}
			for (DoctorExpertiseDTO doctorExpertiseDTO : doctor.getExpertise()) {
				DoctorExpertiseTbl expertiseTbl = new DoctorExpertiseTbl();
				expertiseTbl.setExpertise(doctorExpertiseDTO.getExpertise());
				expertiseTbl.setDoctorTbl(doctorTbl);
				save(expertiseTbl);
			}
		}
		if (doctor.getMembership() != null) {
			List<DoctorMembershipTbl> membershipList=(ArrayList<DoctorMembershipTbl>)getMembershipByDoctorId(doctorTbl
					.getId());
			if(!membershipList.isEmpty()){
				for (DoctorMembershipTbl doctorMembershipTbl : membershipList) {
					delete(doctorMembershipTbl);
				}
			}
			for (DoctorMembershipDTO doctorMembershipDTO : doctor
					.getMembership()) {
				DoctorMembershipTbl membershipTbl = new DoctorMembershipTbl();
				membershipTbl
				.setMembership(doctorMembershipDTO.getMembership());
				membershipTbl.setDoctorTbl(doctorTbl);
				save(membershipTbl);
			}
		}
		response.setError(null);
		response.setSuccess(true);
		response.setId(doctorTbl.getId());
		return response;
	}
	private DoctorTbl getDoctorByGlobalId(int globalId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}
	private List<DoctorScheduleTbl>getScheduleByDoctor(int doctorId) throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_DOCTOR);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorScheduleTbl.class, query);
	}
	public List<PatientAppointmentTbl> getAppointmentByScheduleId(int scheduleId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENT_BY_SCHEDULE);
		query.setParameter("param1", scheduleId);
		return executeQuery(PatientAppointmentTbl.class, query);
	}
	public DoctorTbl getDoctorByEmail(String email) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(DoctorTbl.class, query);
	}

	/**
	 * delete or inactive a doctor
	 * while doing that check schedule and appointments if the corresponding doctor will be inactive
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		DateFormat df=new SimpleDateFormat(Constants.TIME);
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, id);
		if (doctorTbl == null) {
			System.out.println("Doctor Not Found");
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString( id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> doctorScheduleList=(ArrayList<DoctorScheduleTbl>)getScheduleByDoctor(doctorTbl.getId());
		if(!doctorScheduleList.isEmpty()){
			for (DoctorScheduleTbl doctorScheduleTbl : doctorScheduleList) {
				List<PatientAppointmentTbl> appointmentList=(ArrayList<PatientAppointmentTbl>)getAppointmentByScheduleId(doctorScheduleTbl.getId());
				if (!appointmentList.isEmpty()) {
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
		
		
		LoginTbl loginTbl = (LoginTbl) getDoctorLoginTbl(doctorTbl.getLoginTbl().getId());
		loginTbl.setStatus(StatusEnum.Inactive);
		update(loginTbl);
		
		doctorTbl.setStatus(StatusEnum.Inactive);
		doctorTbl.setUpdatedTime(new Date());
		update(doctorTbl);

		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		return response;
	}

	private LoginTbl getDoctorLoginTbl(int loginId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_LOGIN);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * update loginTbl of the doctor with the new password
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateDoctorPassword(DoctorLoginDTO doctor)throws PersistenceException {
		// TODO Auto-generated method stub
		DoctorTbl doctorTbl = (DoctorTbl)getDoctorByGlobalId(doctor.getDoctorGlobalId());
		if(doctorTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(doctor.getDoctorGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(doctorTbl.getLoginTbl()!=null){
			LoginTbl loginTbl=(LoginTbl)getById(LoginTbl.class, doctorTbl.getLoginTbl().getId());			
			loginTbl.setPassword(doctor.getPassword());
			update(loginTbl);
		}

	}
}
