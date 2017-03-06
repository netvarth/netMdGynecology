/**
 * AvailabilityStatusEnum.java
 * @author Sreeram T G 
 *
 * Version 1.0 21-Aug-2013
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
 * @author Sreeram T G
 */
public enum AvailabilityStatusEnum implements EnumDisplay  {
	AVAILABLE("Availabile"),UNAVAILABLE("Unavailabile"),ONHOLD("On Hold"),BOOKED("Booked");
	private String displayName;

	/**
	 * @param displayName
	 */
	private AvailabilityStatusEnum(String displayName) {
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
	public static AvailabilityStatusEnum getEnum(String value)  {
		if(value != null){

			for(AvailabilityStatusEnum v : values()){        	
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
