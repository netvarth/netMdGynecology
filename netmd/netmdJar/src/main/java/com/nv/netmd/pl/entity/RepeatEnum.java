/**
 * RepeatEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
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
public enum RepeatEnum implements EnumDisplay{
	NONE("None"),DAILY("Daily"),WEEKLY("Weekly"),MONTHILY_DAY_OF_THE_MONTH("Monthily day of the month"),MONTHILY_DAY_OF_THE_WEEK("Monthily day of the week");
	private String displayName;
	/**
	 * @param displayName
	 */
	private RepeatEnum(String displayName) {
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
	public static RepeatEnum getEnum(String value)  {
		if(value != null){

			for(RepeatEnum v : values()){        	
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