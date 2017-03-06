/**
 * DoctorService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 5, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorListAchievementDTO;
import com.nv.netmd.rs.dto.DoctorListExperienceDTO;
import com.nv.netmd.rs.dto.DoctorListExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListMembershipDTO;
import com.nv.netmd.rs.dto.DoctorListQualificationDTO;
import com.nv.netmd.rs.dto.DoctorListResponseDTO;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorPersonalDTO;
import com.nv.netmd.rs.dto.DoctorResponse;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public interface DoctorService {
	//public ResponseDTO create(DoctorDetail doctor);

	//public ResponseDTO update(DoctorDetail doctor);

	public DoctorDetail view(int id) throws ServiceException;

	public DoctorListResponseDTO list(FilterDTO filterDTO) throws ServiceException;

	public ResponseDTO createPersonalInfo(DoctorPersonalDTO doctor) throws ServiceException;
	
	public ResponseDTO updatePersonalInfo(DoctorPersonalDTO doctor) throws ServiceException;

	public ResponseDTO doctorQualification(DoctorListQualificationDTO qualification) throws ServiceException;

	public ResponseDTO doctorAchievement(DoctorListAchievementDTO achievement) throws ServiceException;
	
	public ResponseDTO doctorExperience(DoctorListExperienceDTO experience) throws ServiceException;
	
	public ResponseDTO doctorExpertise(DoctorListExpertiseDTO expertise) throws ServiceException;
	
	public ResponseDTO doctorMembership(DoctorListMembershipDTO membership) throws ServiceException;
	
	public DoctorResponse doctorFromYNW(DoctorDetail doctorDetail) throws ServiceException;
	
	public ResponseDTO delete(int id) throws ServiceException;
	
	public void updateDoctorPassword(DoctorLoginDTO doctorLoginDTO ) throws ServiceException;
}
