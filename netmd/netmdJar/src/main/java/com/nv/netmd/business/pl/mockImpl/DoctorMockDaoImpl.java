/**
* DoctorMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 25, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.DoctorDao;
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
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class DoctorMockDaoImpl implements DoctorDao,FilterService{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#view(int)
	 */
	@Override
	public DoctorDetail view(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#createPersonalInfo(com.nv.netmd.rs.dto.DoctorPersonalDTO)
	 */
	@Override
	public ResponseDTO createPersonalInfo(DoctorPersonalDTO doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorQualification(com.nv.netmd.rs.dto.DoctorListQualificationDTO)
	 */
	@Override
	public ResponseDTO doctorQualification(
			DoctorListQualificationDTO qualification) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorAchievement(com.nv.netmd.rs.dto.DoctorListAchievementDTO)
	 */
	@Override
	public ResponseDTO doctorAchievement(DoctorListAchievementDTO achievement) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorExperience(com.nv.netmd.rs.dto.DoctorListExperienceDTO)
	 */
	@Override
	public ResponseDTO doctorExperience(DoctorListExperienceDTO experience) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorExpertise(com.nv.netmd.rs.dto.DoctorListExpertiseDTO)
	 */
	@Override
	public ResponseDTO doctorExpertise(DoctorListExpertiseDTO expertise) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorMembership(com.nv.netmd.rs.dto.DoctorListMembershipDTO)
	 */
	@Override
	public ResponseDTO doctorMembership(DoctorListMembershipDTO membership) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#updatePersonalInfo(com.nv.netmd.rs.dto.DoctorPersonalDTO)
	 */
	@Override
	public ResponseDTO updatePersonalInfo(DoctorPersonalDTO doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#getNewDoctor()
	 */
	@Override
	public List<DoctorDetail> getNewDoctor() {
		// TODO Auto-generated method stub
		List<DoctorDetail> response=new ArrayList<DoctorDetail>();
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#getUpdatedDoctor()
	 */
	@Override
	public List<DoctorDetail> getUpdatedDoctor() {
		// TODO Auto-generated method stub
		List<DoctorDetail> response=new ArrayList<DoctorDetail>();
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#getDeletedDoctor()
	 */
	@Override
	public List<DoctorDetail> getDeletedDoctor() {
		// TODO Auto-generated method stub
		List<DoctorDetail> response=new ArrayList<DoctorDetail>();
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#addDoctorSyncResponse(com.nv.netmd.rs.dto.DoctorResponse)
	 */
	@Override
	public void addDoctorSyncResponse(DoctorResponse doctorResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#updateDoctorSyncResponse(com.nv.netmd.rs.dto.DoctorResponse)
	 */
	@Override
	public void updateDoctorSyncResponse(DoctorResponse doctorResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#deleteDoctorSyncResponse(com.nv.netmd.rs.dto.DoctorResponse)
	 */
	@Override
	public void deleteDoctorSyncResponse(DoctorResponse doctorResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#doctorFromYNW(com.nv.netmd.rs.dto.DoctorDetail)
	 */
	@Override
	public DoctorResponse doctorFromYNW(DoctorDetail doctorDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.DoctorDao#updateDoctorPassword(com.nv.netmd.rs.dto.DoctorLoginDTO)
	 */
	@Override
	public void updateDoctorPassword(DoctorLoginDTO doctorLoginDTO) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public DoctorListResponseDTO list(FilterDTO filterdto) {
		DoctorListResponseDTO doctorList=new DoctorListResponseDTO();
		return doctorList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

}
