/**
 * BedTypePropertyEnum.java
 * @author Leo
 *
 * Version 1.0 Aug 2, 2013
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
 * @author Leonora Louis
 */
public enum BedTypePropertyEnum implements Property,EnumDisplay {
	type("type","type","com.nv.netmd.pl.entity.BedTypeTbl","",""),
	rent("rent","rent","com.nv.netmd.pl.entity.BedTypeTbl","",""),
	count("count","count","com.nv.netmd.pl.entity.BedTypeTbl","",""),
	status("status","status","com.nv.netmd.pl.entity.BedTypeTbl","",""),
	id("id","id","com.nv.netmd.pl.entity.BedTypeTbl","","");
	;
	
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


	private BedTypePropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getPathReferenceName() {
		return pathReferenceName;
	}
	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
	}
	@Override
	public String getParameterName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
