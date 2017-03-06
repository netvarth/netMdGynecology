/**
 * PassPhraseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 20, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class PassPhraseDTO {
	private String passPhrase;
	private String macId;
	private boolean isPrimary;

	/**
	 * 
	 */
	public PassPhraseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param passPhrase
	 * @param macId
	 * @param isPrimary
	 */
	public PassPhraseDTO(String passPhrase, String macId, boolean isPrimary) {
		super();
		this.passPhrase = passPhrase;
		this.macId = macId;
		this.isPrimary = isPrimary;
	}



	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}

	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}



	/**
	 * @return the macId
	 */
	public String getMacId() {
		return macId;
	}



	/**
	 * @param macId the macId to set
	 */
	public void setMacId(String macId) {
		this.macId = macId;
	}



	/**
	 * @return the isPrimary
	 */
	public boolean isPrimary() {
		return isPrimary;
	}



	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	
}
