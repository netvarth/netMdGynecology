/**
 * DoctorImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 5, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.DoctorService;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.validator.DoctorValidator;
import com.nv.netmd.business.pl.dao.DoctorDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
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
public class DoctorServiceImpl implements DoctorService {

	private DoctorDao doctorDao;
	private DoctorValidator validator;
	private FilterService doctorFilterService;

	/**
	 * create doctor
	 * 
	 * @param doctor 
	 * @return ResponseDTO
	 */
	//	@Override
	//	public ResponseDTO create(DoctorDetail doctor) {
	//		// DoctorValidator validator=new DoctorValidator();
	//		ResponseDTO response = new ResponseDTO();
	//		// DoctorDao doctorDao=new DoctorDaoImpl();
	//		// return result with appropriate error code
	//		ErrorDTO error = validator.validateCreateDoctor(doctor);
	//		if (error != null) {
	//			response.setError(error);
	//			response.setSuccess(false);
	//			return response;
	//		}
	//		response = doctorDao.create(doctor);
	//		return response;
	//	}

	/**
	 * Update doctor details
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	//	@Override
	//	public ResponseDTO update(DoctorDetail doctor) {
	//		// DoctorValidator validator=new DoctorValidator();
	//		ResponseDTO response = new ResponseDTO();
	//		// DoctorDao doctorDao=new DoctorDaoImpl();
	//		// return result with appropriate error code
	//		ErrorDTO error = validator.validateUpdateDoctor(doctor);
	//		if (error != null) {
	//			response.setError(error);
	//			response.setSuccess(false);
	//			return response;
	//		}
	//		response = doctorDao.update(doctor);
	//		return response;
	//
	//	}

	/**
	 * View a doctor by giving input as id
	 * 
	 * @param id
	 * @return DoctorDetail
	 */
	@Override
	public DoctorDetail view(int id) throws ServiceException {

		DoctorDetail response = new DoctorDetail();
		try {
			response = doctorDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the doctorDao
	 */
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	/**
	 * @return the validator
	 */
	public DoctorValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(DoctorValidator validator) {
		this.validator = validator;
	}

	/**
	 * list of doctor
	 * 
	 * @param filterDTO
	 * @return DoctorListResponseDTO
	 * @throws ServiceException 
	 */
	@Override

	public DoctorListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		DoctorListResponseDTO doctorList=new DoctorListResponseDTO();
		ErrorDTO error=doctorFilterService.validate(filterDTO);
		if (error != null) {
			doctorList.setError(error);
			doctorList.setSuccess(false);
			return doctorList;
		}
		doctorList =doctorFilterService.list(filterDTO);
		return doctorList;

	}



	/**
	 * create personal details
	 * @param doctor
	 * @return  ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO createPersonalInfo(DoctorPersonalDTO doctor) throws ServiceException {
		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validatePersonalInfo(doctor);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.createPersonalInfo(doctor);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getError(),e);	
		}
		return response;

	}

	/**
	 * doctor qualification 
	 * @param qualification
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO doctorQualification(DoctorListQualificationDTO qualification) throws ServiceException {

		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorQualification(qualification);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorQualification(qualification);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;


	}

	/**
	 * doctor achievement
	 * @param achievement
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO doctorAchievement(DoctorListAchievementDTO achievement) throws ServiceException {

		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorAchievement(achievement);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorAchievement(achievement);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;	
	}

	/**
	 * Doctor experience
	 * @param experience
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO doctorExperience(DoctorListExperienceDTO experience) throws ServiceException {

		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorExperience(experience);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorExperience(experience);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;	

	}

	/**
	 * Doctor expertise
	 * @param expertise
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO doctorExpertise(DoctorListExpertiseDTO expertise) throws ServiceException {
		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorExpertise(expertise);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorExpertise(expertise);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * doctor membership
	 * @param membership
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO doctorMembership(DoctorListMembershipDTO membership) throws ServiceException {
		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorMembership(membership);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorMembership(membership);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/**
	 * update personal information of a doctor
	 * @param doctor
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updatePersonalInfo(DoctorPersonalDTO doctor) throws ServiceException {
		ResponseDTO response = new ResponseDTO();

		// return result with appropriate error code
		ErrorDTO error = validator.validateUpdatePersonalInfo(doctor);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.updatePersonalInfo(doctor);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @param doctorFilterService the doctorFilterService to set
	 */
	public void setDoctorFilterService(FilterService doctorFilterService) {
		this.doctorFilterService = doctorFilterService;
	}

	/**
	 * create doctor from YNW
	 * @param doctorDetail
	 * @return DoctorResponse
	 * @throws ServiceException 
	 */
	@Override
	public DoctorResponse doctorFromYNW(DoctorDetail doctorDetail) throws ServiceException {

		DoctorResponse response = new DoctorResponse();				
		// return result with appropriate error code
		ErrorDTO error = validator.validateDoctorFromYNW(doctorDetail);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response = doctorDao.doctorFromYNW(doctorDetail);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * delete or inactive a doctor
	 * while doing that check schedule and appointments if the corresponding doctor will be inactive
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		try {
			response=doctorDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * update loginTbl of the doctor with the new password
	 * @throws ServiceException 
	 */
	@Override
	public void updateDoctorPassword(DoctorLoginDTO doctorLoginDTO) throws ServiceException {
		// TODO Auto-generated method stub
		validator.validateDoctorLogin(doctorLoginDTO);
		try {
			doctorDao.updateDoctorPassword(doctorLoginDTO);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/**
	 * @return the doctorFilterService
	 */
	public FilterService getDoctorFilterService() {
		return doctorFilterService;
	}


}
