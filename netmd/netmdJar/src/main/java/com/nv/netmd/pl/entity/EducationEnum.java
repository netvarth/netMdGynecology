/**
 * EducationEnum.java
 * @author Leo
 *
 * Version 1.0 Nov 28, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.pl.entity;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.exception.ServiceException;

/**
 *
 *
 * @author Leonora Louis
 */
public enum EducationEnum implements EnumDisplay{
	Primary("Primary"), Secondary("Secondary"), HigherSecondary("HigherSecondary"),Graduate("Graduate"),Others("Others"),None("None");

	private String displayName;
	
	/**
	 * @param displayName
	 */
	
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @return the displayName
	 */
	
	private EducationEnum(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public static EducationEnum getEnum(String value)  {
		if(value != null){

			for(EducationEnum  v : values()){        	
				if(value.equalsIgnoreCase(v.getDisplayName())){
					return v;}}
		}	
	return null;	
	}
	@Override
	public String getParameterName() {
		// TODO Auto-generated method stub
		return null;
	}
}


