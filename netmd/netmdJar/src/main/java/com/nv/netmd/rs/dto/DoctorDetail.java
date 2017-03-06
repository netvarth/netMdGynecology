/**
 * DoctorDetail.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 5, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class DoctorDetail {
	private int id;
	private int globalId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String workHistory;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private String workingPlaces;
	private String designation;
	private String specialization;
	private String consultationInterval;
	private String status;
	private String createDateTime;
	private String updateDateTime;
	private List<DoctorExperienceDTO> doctorExperience = new ArrayList<DoctorExperienceDTO>();
	private List<DoctorQualificationDTO> doctorQualifications= new ArrayList<DoctorQualificationDTO>();
	private List<DoctorExpertiseDTO> expertise= new ArrayList<DoctorExpertiseDTO>();
	private List<DoctorMembershipDTO> membership= new ArrayList<DoctorMembershipDTO>();
	private List<DoctorAchievementDTO> achievement= new ArrayList<DoctorAchievementDTO>();
	private ErrorDTO error;
	private boolean success;
	private LoginDTO login;

	
	/**
	 * @param id
	 * @param globalId
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param gender
	 * @param workHistory
	 * @param mobile
	 * @param phone
	 * @param email
	 * @param address
	 * @param workingPlaces
	 * @param designation
	 * @param specialization
	 * @param consultationInterval
	 * @param status
	 * @param createDateTime
	 * @param updateDateTime
	 * @param doctorExperience
	 * @param doctorQualifications
	 * @param expertise
	 * @param membership
	 * @param achievement
	 * @param error
	 * @param success
	 * @param login
	 */
	public DoctorDetail(int id, int globalId, String firstName,
			String lastName, String dateOfBirth, String gender,
			String workHistory, String mobile, String phone, String email,
			String address, String workingPlaces, String designation,
			String specialization, String consultationInterval, String status,
			String createDateTime, String updateDateTime,
			List<DoctorExperienceDTO> doctorExperience,
			List<DoctorQualificationDTO> doctorQualifications,
			List<DoctorExpertiseDTO> expertise,
			List<DoctorMembershipDTO> membership,
			List<DoctorAchievementDTO> achievement, ErrorDTO error,
			boolean success, LoginDTO login) {
		super();
		this.id = id;
		this.globalId = globalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.workHistory = workHistory;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.workingPlaces = workingPlaces;
		this.designation = designation;
		this.specialization = specialization;
		this.consultationInterval = consultationInterval;
		this.status = status;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.doctorExperience = doctorExperience;
		this.doctorQualifications = doctorQualifications;
		this.expertise = expertise;
		this.membership = membership;
		this.achievement = achievement;
		this.error = error;
		this.success = success;
		this.login = login;
	}

	/**
	 * 
	 */
	public DoctorDetail() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the workHistory
	 */
	public String getWorkHistory() {
		return workHistory;
	}

	/**
	 * @param workHistory
	 *            the workHistory to set
	 */
	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the workingPlaces
	 */
	public String getWorkingPlaces() {
		return workingPlaces;
	}

	/**
	 * @param workingPlaces
	 *            the workingPlaces to set
	 */
	public void setWorkingPlaces(String workingPlaces) {
		this.workingPlaces = workingPlaces;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the consultationInterval
	 */
	public String getConsultationInterval() {
		return consultationInterval;
	}

	/**
	 * @param consultationInterval
	 *            the consultationInterval to set
	 */
	public void setConsultationInterval(String consultationInterval) {
		this.consultationInterval = consultationInterval;
	}

	/**
	 * @return the doctorExperience
	 */
	public List<DoctorExperienceDTO> getDoctorExperience() {
		return doctorExperience;
	}

	/**
	 * @param doctorExperience
	 *            the doctorExperience to set
	 */
	public void setDoctorExperience(List<DoctorExperienceDTO> doctorExperience) {
		this.doctorExperience = doctorExperience;
	}

	/**
	 * @return the doctorQualifications
	 */
	public List<DoctorQualificationDTO> getDoctorQualifications() {
		return doctorQualifications;
	}

	/**
	 * @param doctorQualifications
	 *            the doctorQualifications to set
	 */
	public void setDoctorQualifications(
			List<DoctorQualificationDTO> doctorQualifications) {
		this.doctorQualifications = doctorQualifications;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the login
	 */
	public LoginDTO getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(LoginDTO login) {
		this.login = login;
	}

	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId
	 *            the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}

	/**
	 * @return the expertise
	 */
	public List<DoctorExpertiseDTO> getExpertise() {
		return expertise;
	}

	/**
	 * @param expertise
	 *            the expertise to set
	 */
	public void setExpertise(List<DoctorExpertiseDTO> expertise) {
		this.expertise = expertise;
	}

	/**
	 * @return the membership
	 */
	public List<DoctorMembershipDTO> getMembership() {
		return membership;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(List<DoctorMembershipDTO> membership) {
		this.membership = membership;
	}

	/**
	 * @return the achievement
	 */
	public List<DoctorAchievementDTO> getAchievement() {
		return achievement;
	}

	/**
	 * @param achievement
	 *            the achievement to set
	 */
	public void setAchievement(List<DoctorAchievementDTO> achievement) {
		this.achievement = achievement;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

}
