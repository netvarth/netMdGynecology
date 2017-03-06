/**
* DoctorPersonalDTO.java
* 
* @Author Sreeram
*
* Version 1.0 Mar 18, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class DoctorPersonalDTO {
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
	private ErrorDTO error;
	private boolean success;
	private LoginDTO login;
	/**
	 * 
	 */
	public DoctorPersonalDTO() {
		super();
	}
	
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
	 * @param error
	 * @param success
	 * @param login
	 */
	public DoctorPersonalDTO(int id, int globalId, String firstName,
			String lastName, String dateOfBirth, String gender,
			String workHistory, String mobile, String phone, String email,
			String address, String workingPlaces, String designation,
			String specialization, String consultationInterval, ErrorDTO error,
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
		this.error = error;
		this.success = success;
		this.login = login;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}
	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
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
	 * @param dateOfBirth the dateOfBirth to set
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
	 * @param gender the gender to set
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
	 * @param workHistory the workHistory to set
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
	 * @param mobile the mobile to set
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
	 * @param phone the phone to set
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
	 * @param email the email to set
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
	 * @param address the address to set
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
	 * @param workingPlaces the workingPlaces to set
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
	 * @param designation the designation to set
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
	 * @param consultationInterval the consultationInterval to set
	 */
	public void setConsultationInterval(String consultationInterval) {
		this.consultationInterval = consultationInterval;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
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
	 * @param success the success to set
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
	 * @param login the login to set
	 */
	public void setLogin(LoginDTO login) {
		this.login = login;
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
