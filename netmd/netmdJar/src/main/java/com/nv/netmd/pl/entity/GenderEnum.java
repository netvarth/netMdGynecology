/**
 * GenderEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 15, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.pl.entity;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.exception.ServiceException;




/**
 * 
 */
public enum GenderEnum implements EnumDisplay{

	Male("Male"),Female("Female");

	private String displayName;

	/**
	 * @param displayName
	 */
	private GenderEnum(String displayName) {
		this.displayName = displayName;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.impl.EnumDisplay#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public static GenderEnum getEnum(String value)  {
		if(value != null){

			for(GenderEnum v : values()){        	
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
