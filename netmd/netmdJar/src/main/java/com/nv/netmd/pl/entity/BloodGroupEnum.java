/**
 * BloodGroupEnum.java
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
public enum BloodGroupEnum implements EnumDisplay{
	
	A("A"), B("B"), AB("AB"),O("O");

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
	
	private BloodGroupEnum(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public static BloodGroupEnum getEnum(String value)  {
		if(value != null){

			for(BloodGroupEnum v : values()){        	
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
