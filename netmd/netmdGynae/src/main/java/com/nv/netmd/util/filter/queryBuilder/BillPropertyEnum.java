/**
 * BillPropertyEnum.java
 * @author Sreeram T G 
 *
 * Version 1.0 02-Sep-2013
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
 * @author Sreeram T G
 */
public enum BillPropertyEnum implements Property,EnumDisplay {
	uid("uid","uid","com.nv.netmd.pl.entity.BillTbl","","","Bill No"),
	patientName("patientName","firstName","com.nv.netmd.pl.entity.PatientTbl","patientTbl","","Patient Name"),
	paymentStatus("paymentStatus","paymentStatus","com.nv.netmd.pl.entity.BillTbl","","","Payment Status"),
	billStatus("billStatus","billStatus","com.nv.netmd.pl.entity.BillTbl","","","Bill Status"),
	origin("origin","origin","com.nv.netmd.pl.entity.BillTbl","","","Origin"),
	orderDate("orderDate","createdDateTime","com.nv.netmd.pl.entity.BillTbl","","","Order Date"),
	referralName("referralName","referralName","com.nv.netmd.pl.entity.BillTbl","","","Doctor Name"),
	;
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	String parameterName;
	/**
	 * @param displayName
	 * @param fieldName
	 * @param entityName
	 * @param referenceName
	 * @param pathReferenceName
	 * @param parameterName;
	 */
	private BillPropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName,String  parameterName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
		this.parameterName= parameterName;
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
		
		return parameterName;
	}
	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
