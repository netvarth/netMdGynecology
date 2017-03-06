/**
 * RhEnum.java
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
public enum RhEnum implements EnumDisplay {
	
	Positive("Positive"),Negative("Negative");

	private String displayName;

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
	
	private RhEnum(String displayName) {
		this.displayName = displayName;
	}
	public static RhEnum getEnum(String value)  {
		if(value != null){

			for(RhEnum v : values()){        	
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
