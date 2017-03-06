/**
 * StatusEnum.java
 * 
 * @Author Nithesh Mohanan
 *
 * Version 1.0 Mar 7, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.pl.entity;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.exception.ServiceException;

/**
 * @author Nithesh Mohanan
 *
 */
public enum SyncStatusEnum implements EnumDisplay{
	NOCHANGE("NOCHANGE"), CHANGED("CHANGED"), INQUEUE("INQUEUE");
	private String displayName;

	/**
	 * @param displayName
	 */
	private SyncStatusEnum(String displayName) {
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
	public static SyncStatusEnum getEnum(String value)  {
		if(value != null){

			for(SyncStatusEnum v : values()){        	
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


