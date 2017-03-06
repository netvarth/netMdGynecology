/**
* PatientPropertyEnum.java
* 
* @Author Sreeram
*
* Version 1.0 Jan 16, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.util.filter.core.Property;

/**
 * 
 */
public enum PatientPropertyEnum implements Property,EnumDisplay{
	
	firstName("firstName","firstName","com.nv.netmd.pl.entity.PatientTbl","","","Patient Name"),
	mobile("mobile","mobile","com.nv.netmd.pl.entity.PatientTbl","","","Mobile"),
	phone("phone","phone","com.nv.netmd.pl.entity.PatientTbl","","","Phone"),
	email("email","email","com.nv.netmd.pl.entity.PatientTbl","","","Email"),
	dob("dob","dob","com.nv.netmd.pl.entity.PatientTbl","","","Date Of Birth"),
	status("status","status","com.nv.netmd.pl.entity.PatientTbl","","","Status"),	
	age("age","age","com.nv.netmd.pl.entity.PatientTbl","","","Age"),
	bloodGroup("bloodGroup","bloodGroup","com.nv.netmd.pl.entity.PatientTbl","","","Blood Group"),
	//caseName("caseName","caseName","com.nv.netmd.pl.entity.CaseTbl","caseTbl","","Case Name"),
	//address("address","address","com.nv.netmd.pl.entity.PatientTbl","",""),
	//updatedTime("updatedTime","updatedTime","com.nv.netmd.pl.entity.PatientTbl","",""),
	//medicalRecordType("medicalRecordType","type","com.nv.netmd.pl.entity.MedicalRecordTbl","medicalRecordTbls",""),
	//medicalRecordDate("medicalRecordDate","date","com.nv.netmd.pl.entity.MedicalRecordTbl","medicalRecordTbls",""),
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
	 */
	private PatientPropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName,String parameterName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
		this.parameterName = parameterName;
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
	/**
	 * @return the parameterName
	 */
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
