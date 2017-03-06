/**
 * DoctorValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 6, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.GenderEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.UserTypeEnum;
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
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.LoginDTO;


/**
 * 
 */
public class DoctorValidator  {
	public ErrorDTO validateCreateDoctor(DoctorDetail doctor) throws ServiceException {
		if (!isValidName(doctor.getFirstName())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMailId);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$"))  {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getDateOfBirth() != null) {
			if (!doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			DateFormat df = new SimpleDateFormat(
					Constants.DATE_FORMAT_WITHOUT_TIME);
			try {
				Date dob = df.parse(doctor.getDateOfBirth());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		// Validate login details
		//validateCreateUser(doctor.getLogin());
		
		if (!doctor.getDoctorExperience().isEmpty()) {
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				// Validate doctor experience details
				validateDoctorExperience(doctorExperience);
			}
		}
		if (!doctor.getDoctorQualifications().isEmpty()) {
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {
				// Validate doctor educational qualification details
				validateDoctorQualification(doctorQualificaton);
			}
		}
		if (!doctor.getAchievement().isEmpty()) {
			for (DoctorAchievementDTO doctorAchievement : doctor
					.getAchievement()) {
				// Validate doctor Achievement details
				validateDoctorAchievement(doctorAchievement);
			}
		}
		if (!doctor.getMembership().isEmpty()) {
			for (DoctorMembershipDTO doctorMembership : doctor.getMembership()) {
				// Validate doctor membership details
				validateDoctorMembership(doctorMembership);
			}
		}
		if (!doctor.getExpertise().isEmpty()) {
			for (DoctorExpertiseDTO doctorExpertise : doctor.getExpertise()) {
				// Validate doctor membership details
				validateDoctorExpertise(doctorExpertise);
			}
		}
		ValidateCreateUser(doctor.getLogin());
		return null;
	}
	public void validateDoctorQualification(
			DoctorQualificationDTO doctorQualificaton) throws ServiceException {
		if (doctorQualificaton.getPassedOutDate() == null
				|| !doctorQualificaton.getPassedOutDate().matches(
						"\\d{4}")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (!isValidName(doctorQualificaton.getEducationalDegree())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (!isValidName(doctorQualificaton.getInstitution())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
	}

	public void validateDoctorExperience(DoctorExperienceDTO doctorExperience) throws ServiceException {

		if (doctorExperience.getFromDate() != null
				&& doctorExperience.getToDate() != null) {
			if (!doctorExperience.getFromDate().matches("\\d{4}-\\d{2}-\\d{2}")
					|| !doctorExperience.getToDate().matches(
							"\\d{4}-\\d{2}-\\d{2}")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			DateFormat df1 = new SimpleDateFormat(
					Constants.DATE_FORMAT_WITHOUT_TIME);
			Date fromDate;
			Date toDate;
			try {
				fromDate = df1.parse(doctorExperience.getFromDate());
				toDate = df1.parse(doctorExperience.getToDate());
				if (fromDate.after(toDate)) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidFromToDate);
					se.setDisplayErrMsg(true);
					throw se;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

	}

	public void validateDoctorAchievement(DoctorAchievementDTO doctorAchievement) throws ServiceException {

		if (!isValidName(doctorAchievement.getAchievement())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidAchievement);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	public void validateDoctorMembership(DoctorMembershipDTO doctorMembership) throws ServiceException {

		if (!isValidName(doctorMembership.getMembership())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMemberShip);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	public void validateDoctorExpertise(DoctorExpertiseDTO doctorExpertise) throws ServiceException {

		if (!isValidName(doctorExpertise.getExpertise())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidExpertise);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

//	public Date dateFormat(String date){
//		String d1=date;
//		String[] splitdata=d1.split("-");
//
//		int year=Integer.parseInt(splitdata[0]);		
//		int month=Integer.parseInt(splitdata[1]);
//		int day=Integer.parseInt(splitdata[2]);
//		Calendar cal=Calendar.getInstance();
//		cal.set(year,month,day);
//		Date d=cal.getTime();
//		return d; 
//	}

//	public Date dateFormatWithTime(String date){
//		String d1=date;
//		String[] splitdate=d1.split(" ");
//		String[] splitdata=splitdate[0].split("-");
//		String[] splits=splitdate[1].split(":");
//		int year=Integer.parseInt(splitdata[0]);		
//		int month=Integer.parseInt(splitdata[1]);
//		int day=Integer.parseInt(splitdata[2]);
//		int hourOfDay=Integer.parseInt(splits[0]);
//		int minute=Integer.parseInt(splits[1]);
//
//		Calendar cal=Calendar.getInstance();		
//		cal.set(year, month, day, hourOfDay, minute);
//		Date d=cal.getTime();
//		return d;
//	}
	
	
	public ErrorDTO validateUpdateDoctor(DoctorDetail doctor) throws ServiceException {
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (doctor.getId() == 0) {
			error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(doctor.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getDateOfBirth() == null
				|| !doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (doctor.getDoctorExperience() != null) {
			// Experience details not null
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				ActionNameEnum actionName = ActionNameEnum
						.getEnum(doctorExperience.getActionName());
				if (doctorExperience.getFromDate() != null
						|| doctorExperience.getToDate() != null) {
					if (doctorExperience.getFromDate() == null
							|| !doctorExperience.getFromDate().matches(
									"\\d{4}-\\d{2}-\\d{2}")) {
						error.setErrCode(ErrorCodeEnum.InvalidDateFormat
								.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if (doctorExperience.getToDate() == null
							|| !doctorExperience.getToDate().matches(
									"\\d{4}-\\d{2}-\\d{2}")) {
						error.setErrCode(ErrorCodeEnum.InvalidDateFormat
								.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					DateFormat df1 = new SimpleDateFormat(
							Constants.DATE_FORMAT_WITHOUT_TIME);
					Date fromDate;
					Date toDate;
					try {
						fromDate = df1.parse(doctorExperience.getFromDate());
						toDate = df1.parse(doctorExperience.getToDate());
						if (fromDate.after(toDate)) {
							error.setErrCode(ErrorCodeEnum.InvalidFromToDate
									.getErrCode());
							error.setDisplayErrMsg(true);
							return error;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error.setErrCode(ErrorCodeEnum.InvalidDateFormat
								.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}
		}
		if (doctor.getDoctorQualifications() != null) {
			// Qualification not null
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {
				ActionNameEnum actionName = ActionNameEnum
						.getEnum(doctorQualificaton.getActionName());
				if (doctorQualificaton.getPassedOutDate() != null) {
					if (doctorQualificaton.getPassedOutDate() == null
							|| !doctorQualificaton.getPassedOutDate().matches(
									"\\d{4}")) {
						error.setErrCode(ErrorCodeEnum.InvalidDateFormat
								.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					DateFormat df2 = new SimpleDateFormat(
							Constants.DATE_FORMAT_WITHOUT_TIME);
					try {
						Date date = df2.parse(doctor.getDateOfBirth());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error.setErrCode(ErrorCodeEnum.InvalidDateFormat
								.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}
		}

		return null;
	}

	public ErrorDTO ValidateCreateUser(LoginDTO user) throws ServiceException {
		//ErrorDTO error = new ErrorDTO();
		UserTypeEnum userType = UserTypeEnum.getEnum(user.getUserType());
		if (user.getUserName()==null || user.getUserName().equals("")) {			
			ServiceException se = new ServiceException(ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

		return null;
	}

	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}

	//	public ErrorDTO validateDoctorFilter(FilterDTO filter) {
	//		ErrorDTO error = new ErrorDTO();
	//		for (ExpressionDTO exp : filter.getExp()) {
	//			Property property = null;
	//			try {
	//				property = DoctorPropertyEnum.valueOf(exp.getName());
	//			} catch (IllegalArgumentException e) {
	//				error = getInvalidExpNameError(exp);
	//				return error;
	//			}
	//			error = validateExp(exp, property);
	//			if (error != null) {
	//				return error;
	//			}
	//		}
	//		return null;
	//	}
	public ErrorDTO validatePersonalInfo(DoctorPersonalDTO doctor) throws ServiceException {

		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if (!isValidName(doctor.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getDateOfBirth() == null
				|| !doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		ValidateCreateUser(doctor.getLogin());		
		return null;
	}

	public ErrorDTO validateUpdatePersonalInfo(DoctorPersonalDTO doctor) throws ServiceException {
		//validate update personal details
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if(doctor.getId()<=0){
			error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(doctor.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		GenderEnum gender = GenderEnum.getEnum(doctor.getGender());
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (doctor.getDateOfBirth() == null
				|| !doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date date = df.parse(doctor.getDateOfBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
	public ErrorDTO validateDoctorQualification(DoctorListQualificationDTO qualification) throws ServiceException {
		//validate doctor qualification 
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		DateFormat df = new SimpleDateFormat(Constants.YEAR);

		if(qualification.getQualification()!=null){			
			for (DoctorQualificationDTO doctorQualificationDTO : qualification.getQualification()) {
				ActionNameEnum action=ActionNameEnum.getEnum(doctorQualificationDTO.getActionName());
				if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					if(doctorQualificationDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorQualificationDTO.getEducationalDegree())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if (doctorQualificationDTO.getPassedOutDate() != null) {
						Date passoutDate = null;
						try {
							passoutDate = df.parse(doctorQualificationDTO
									.getPassedOutDate());

						} catch (ParseException e) {
							e.printStackTrace();
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
					}
				}
				if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					if(doctorQualificationDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(doctorQualificationDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorQualificationDTO.getEducationalDegree())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if (doctorQualificationDTO.getPassedOutDate() != null) {
						Date passoutDate = null;
						try {
							passoutDate = df.parse(doctorQualificationDTO
									.getPassedOutDate());

						} catch (ParseException e) {
							e.printStackTrace();
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
					}
				}
				if(doctorQualificationDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(doctorQualificationDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}

		}
		else{
			error.setErrCode(ErrorCodeEnum.EmptyRequest.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	public ErrorDTO validateDoctorAchievement(DoctorListAchievementDTO achievement) throws ServiceException {
		//validate doctor achievement
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);

		if(achievement.getAchievement()!=null){			
			for (DoctorAchievementDTO doctorAchievementDTO : achievement.getAchievement()) {
				ActionNameEnum action=ActionNameEnum.getEnum(doctorAchievementDTO.getActionName());
				if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					if(doctorAchievementDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorAchievementDTO.getAchievement())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
				if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					if(doctorAchievementDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(doctorAchievementDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorAchievementDTO.getAchievement())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
				if(doctorAchievementDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(doctorAchievementDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}

		}
		else{
			error.setErrCode(ErrorCodeEnum.EmptyRequest.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
	public ErrorDTO validateDoctorExperience(DoctorListExperienceDTO experience) throws ServiceException {
		//validate doctor experience
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date fromDate = null;
		Date toDate = null;
		if(experience.getExperience()!=null){			
			for (DoctorExperienceDTO doctorExperienceDTO : experience.getExperience()) {
				ActionNameEnum action=ActionNameEnum.getEnum(doctorExperienceDTO.getActionName());
				if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					if(doctorExperienceDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorExperienceDTO.getDesignation())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if (doctorExperienceDTO.getFromDate() != null
							&& doctorExperienceDTO.getToDate() != null) {
						if (!doctorExperienceDTO.getFromDate().matches("\\d{4}-\\d{2}-\\d{2}")
								|| !doctorExperienceDTO.getToDate().matches(
										"\\d{4}-\\d{2}-\\d{2}")) {
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
						DateFormat df1 = new SimpleDateFormat(
								Constants.DATE_FORMAT_WITHOUT_TIME);

						try {
							fromDate = df1.parse(doctorExperienceDTO.getFromDate());
							toDate = df1.parse(doctorExperienceDTO.getToDate());
							if (fromDate.after(toDate)) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.InvalidFromToDate);
								se.setDisplayErrMsg(true);
								throw se;
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
					}

				}
				if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					if(doctorExperienceDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(doctorExperienceDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorExperienceDTO.getDesignation())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if (doctorExperienceDTO.getFromDate() != null
							&& doctorExperienceDTO.getToDate() != null) {
						if (!doctorExperienceDTO.getFromDate().matches("\\d{4}-\\d{2}-\\d{2}")
								|| !doctorExperienceDTO.getToDate().matches(
										"\\d{4}-\\d{2}-\\d{2}")) {
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
						DateFormat df1 = new SimpleDateFormat(
								Constants.DATE_FORMAT_WITHOUT_TIME);

						try {
							fromDate = df1.parse(doctorExperienceDTO.getFromDate());
							toDate = df1.parse(doctorExperienceDTO.getToDate());
							if (fromDate.after(toDate)) {
								ServiceException se = new ServiceException(
										ErrorCodeEnum.InvalidFromToDate);
								se.setDisplayErrMsg(true);
								throw se;
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidDateFormat);
							se.setDisplayErrMsg(true);
							throw se;
						}
					}
				}
				if(doctorExperienceDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(doctorExperienceDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}

		}
		else{
			error.setErrCode(ErrorCodeEnum.EmptyRequest.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
	public ErrorDTO validateDoctorMembership(DoctorListMembershipDTO membership) throws ServiceException {
		//validate doctor expertise
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if(membership.getMembership()!=null){			
			for (DoctorMembershipDTO doctorMembershipDTO : membership.getMembership()) {
				ActionNameEnum action=ActionNameEnum.getEnum(doctorMembershipDTO.getActionName());
				if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					if(doctorMembershipDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorMembershipDTO.getMembership())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}

				}
				if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					if(doctorMembershipDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(doctorMembershipDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorMembershipDTO.getMembership())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
				if(doctorMembershipDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(doctorMembershipDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}

		}
		else{
			error.setErrCode(ErrorCodeEnum.EmptyRequest.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	public ErrorDTO validateDoctorExpertise(DoctorListExpertiseDTO expertise) throws ServiceException {
		//validate doctor membership
		ErrorDTO error = new ErrorDTO();
//		List<Parameter> parameters = new ArrayList<Parameter>();
		if(expertise.getExpertise()!=null){			
			for (DoctorExpertiseDTO doctorExpertiseDTO : expertise.getExpertise()) {
				ActionNameEnum action=ActionNameEnum.getEnum(doctorExpertiseDTO.getActionName());
				if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					if(doctorExpertiseDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorExpertiseDTO.getExpertise())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}

				}
				if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					if(doctorExpertiseDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(doctorExpertiseDTO.getDoctorId()<=0){
						error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
					if(!isValidName(doctorExpertiseDTO.getExpertise())){
						error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
				if(doctorExpertiseDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(doctorExpertiseDTO.getId()<=0){
						error.setErrCode(ErrorCodeEnum.InvalidId.getErrCode());
						error.setDisplayErrMsg(true);
						return error;
					}
				}
			}

		}
		else{
			error.setErrCode(ErrorCodeEnum.EmptyRequest.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	//validate doctor from YNW
	public ErrorDTO validateDoctorFromYNW(DoctorDetail doctor) throws ServiceException {
		StatusEnum status=StatusEnum.getEnum(doctor.getStatus());
		if(doctor.getGlobalId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.GlobalIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(doctor.getFirstName())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMailId);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$"))  {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getDateOfBirth() != null) {
			if (!doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			DateFormat df = new SimpleDateFormat(
					Constants.DATE_FORMAT_WITHOUT_TIME);
			try {
				Date dob = df.parse(doctor.getDateOfBirth());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		// Validate login details
		//validateCreateUser(doctor.getLogin());

		if (!doctor.getDoctorExperience().isEmpty()) {
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				// Validate doctor experience details
				validateDoctorExperience(doctorExperience);
			}
		}
		if (!doctor.getDoctorQualifications().isEmpty()) {
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {
				// Validate doctor educational qualification details
				validateDoctorQualification(doctorQualificaton);
			}
		}
		if (!doctor.getAchievement().isEmpty()) {
			for (DoctorAchievementDTO doctorAchievement : doctor
					.getAchievement()) {
				// Validate doctor Achievement details
				validateDoctorAchievement(doctorAchievement);
			}
		}
		if (!doctor.getMembership().isEmpty()) {
			for (DoctorMembershipDTO doctorMembership : doctor.getMembership()) {
				// Validate doctor membership details
				validateDoctorMembership(doctorMembership);
			}
		}
		if (!doctor.getExpertise().isEmpty()) {
			for (DoctorExpertiseDTO doctorExpertise : doctor.getExpertise()) {
				// Validate doctor membership details
				validateDoctorExpertise(doctorExpertise);
			}
		}
		ValidateCreateUser(doctor.getLogin());
		return null;

	}
	//validate doctor login
	public void validateDoctorLogin(DoctorLoginDTO doctorLoginDTO) throws ServiceException{
		
//	if (!isValidName(doctorLoginDTO.getPassword())){ 
//			ServiceException se = new ServiceException(
//					ErrorCodeEnum.PasswordNull);	
//			se.setDisplayErrMsg(true);
//			throw se;
//		}	
//	
		
		
	if(doctorLoginDTO.getDoctorGlobalId()<=0){
		ServiceException se = new ServiceException(
				ErrorCodeEnum.GlobalIdNull);
		se.setDisplayErrMsg(true);
		throw se;
	}
	if (!isValidName(doctorLoginDTO.getEmail())) {

		ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
		se.setDisplayErrMsg(true);
		throw se;
	}
	if (doctorLoginDTO.getEmail() != null && !doctorLoginDTO.getEmail().equals("")) {
		if (!doctorLoginDTO
				.getEmail()
				.matches(
						"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMailId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	}
}
