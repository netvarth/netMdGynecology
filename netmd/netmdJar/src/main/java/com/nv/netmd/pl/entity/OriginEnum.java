/**
 * OrginEnum.java
 * @author Sreeram T G 
 *
 * Version 1.0 28-Aug-2013
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
public enum OriginEnum implements EnumDisplay {
	InPatient("InPatient"), OutPatient("OutPatient");

	private String displayName;

	/**
	 * @param displayName
	 */
	private OriginEnum(String displayName) {
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
	public static OriginEnum getEnum(String value)  {
		if(value != null){

			for(OriginEnum v : values()){        	
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
