/**
 * NetmdUserTypeEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 11, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.pl.entity;

import com.nv.netmd.business.bl.impl.EnumDisplay;

/**
 * 
 */
public enum NetmdUserTypeEnum implements EnumDisplay{
	Admin("admin"),Owner("owner"),Doctor("doctor"),Staff("staff"),Nurse("nurse");

	private String displayName;

	/**
	 * @param displayName
	 */
	private NetmdUserTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getParameterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
