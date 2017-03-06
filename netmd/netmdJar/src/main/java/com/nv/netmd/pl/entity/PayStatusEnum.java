/**
 * PayStatusEnum.java
 * @author Sreeram T G 
 *
 * Version 1.0 27-Aug-2013
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
public enum PayStatusEnum implements EnumDisplay{

	FullyPaid("FullyPaid"), PartiallyPaid("PartiallyPaid"), NotPaid("NotPaid");

	private String displayName;

	/**
	 * @param displayName
	 */
	private PayStatusEnum(String displayName) {
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

	public static PayStatusEnum getEnum(String value) {
		if(value != null){
			
		for(PayStatusEnum v : values()){        	
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
