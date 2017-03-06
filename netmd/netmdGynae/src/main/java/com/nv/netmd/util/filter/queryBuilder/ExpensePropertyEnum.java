/**
 * AccountHeadPropertyEnum.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 26-Nov-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.util.filter.core.Property;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public enum ExpensePropertyEnum  implements Property,EnumDisplay{
	expenseName("expenseName","expenseName","com.nv.netmd.pl.entity.ExpenseTbl","",""),
	id("id","id","com.nv.netmd.pl.entity.ExpenseTbl","",""),
	headName("headName","headName","com.nv.netmd.pl.entity.ExpenseTbl","",""),
	status("status","status","com.nv.netmd.pl.entity.ExpenseTbl","","");

	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	/**
	 * @param displayName
	 * @param fieldName
	 * @param entityName
	 * @param referenceName
	 * @param pathReferenceName
	 */
	private ExpensePropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
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
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}
	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the pathReferenceName
	 */
	public String getPathReferenceName() {
		return pathReferenceName;
	}
	/**
	 * @param pathReferenceName the pathReferenceName to set
	 */
	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
	}
	@Override
	public String getParameterName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
