/**
 * ReferralDetails.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 9, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;



/**
 * 
 */
public class ReferralDetails {
	private int id;
	private String firstName;
	private String address;
	private String mobile;
	private String email;
	private String lastName;
	private String specialization;
	private String qualification;

	/**
	 * 
	 */
//	public ReferralDetails(DoctorTbl doctor) {
//		this.setId(doctor.getId());
//		this.setAddress(doctor.getAddress());
//		this.setMobile(doctor.getMobile());
//		this.setFirstName(doctor.getFirstName());
//		this.setEmail(doctor.getEmail());
//	}

	public ReferralDetails() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 *  @param lastName
	 * @param address
	 * @param mobile
	 * @param email
	 * @param specialization
	 * @param qualification 
	 */
	public ReferralDetails(int id, String firstName,String lastName, String address,
			String mobile, String email,String specialization,String qualification) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName=lastName;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.specialization=specialization;
		this.qualification=qualification;
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	
}
