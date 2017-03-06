/**
 * ActionNameEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Aug 18, 2012
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
public enum ActionEnum implements EnumDisplay{
	CREATE("Create"), UPDATE("Update"), DELETE("Delete");


	private String displayName;


	/**
	 * @param displayName
	 */
	private ActionEnum(String displayName) {
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

	public static ActionEnum getEnum(String value)  {
		if(value != null){

			for(ActionEnum v : values()){        	
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
