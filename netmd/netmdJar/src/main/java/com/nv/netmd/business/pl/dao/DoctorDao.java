/**
 * DoctorDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 6, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.dao;

import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorListAchievementDTO;
import com.nv.netmd.rs.dto.DoctorListExperienceDTO;
import com.nv.netmd.rs.dto.DoctorListExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListMembershipDTO;
import com.nv.netmd.rs.dto.DoctorListQualificationDTO;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorPersonalDTO;
import com.nv.netmd.rs.dto.DoctorResponse;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public interface DoctorDao {
	//public ResponseDTO create(DoctorDetail referral);
	public DoctorDetail view(int id) throws PersistenceException;
	//public ResponseDTO update(DoctorDetail referral);
	public ResponseDTO createPersonalInfo(DoctorPersonalDTO doctor)throws PersistenceException;
	public ResponseDTO doctorQualification(DoctorListQualificationDTO qualification)throws PersistenceException;
	public ResponseDTO doctorAchievement(DoctorListAchievementDTO achievement)throws PersistenceException;
	public ResponseDTO doctorExperience(DoctorListExperienceDTO experience)throws PersistenceException;
	public ResponseDTO doctorExpertise(DoctorListExpertiseDTO expertise)throws PersistenceException;
	public ResponseDTO doctorMembership(DoctorListMembershipDTO membership)throws PersistenceException;
	public ResponseDTO updatePersonalInfo(DoctorPersonalDTO doctor)throws PersistenceException;
	public List<DoctorDetail> getNewDoctor()throws PersistenceException;
	public List<DoctorDetail> getUpdatedDoctor()throws PersistenceException;
	public List<DoctorDetail> getDeletedDoctor()throws PersistenceException;
	public void addDoctorSyncResponse(DoctorResponse doctorResponse)throws PersistenceException;
	public void updateDoctorSyncResponse(DoctorResponse doctorResponse)throws PersistenceException;
	public void deleteDoctorSyncResponse(DoctorResponse doctorResponse)throws PersistenceException;
	public DoctorResponse doctorFromYNW(DoctorDetail doctorDetail)throws PersistenceException;
	public ResponseDTO delete(int id)throws PersistenceException;
	public void updateDoctorPassword(DoctorLoginDTO doctorLoginDTO )throws PersistenceException;
}
