 /**
* BillStatusEnum.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 28, 2013
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
* @author Nithesh Mohanan 
*/
public enum BillStatusEnum implements EnumDisplay{
	Open("open"), Closed("closed"),Cancelled("cancelled");
	
	private String displayName;
	
	private BillStatusEnum(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}
	public static BillStatusEnum getEnum(String value)  {
		if(value != null){

			for(BillStatusEnum v : values()){        	
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
