 /**
* HeadTypeEnum.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 14, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.pl.entity;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.exception.ServiceException;

/**
 * @author Nithesh Mohanan
 *
 */
public enum HeadTypeEnum implements EnumDisplay{
	Head("Head"), SubHead("SubHead");

	private String displayName;

	/**
	 * @param displayName
	 */
	private HeadTypeEnum(String displayName) {
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

	public static HeadTypeEnum getEnum(String value)  {
		if(value != null){

			for(HeadTypeEnum v : values()){        	
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
