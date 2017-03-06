/**
 * MedicalPropertyEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 4, 2013
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
public enum MedicalPropertyEnum implements Property,EnumDisplay {
	medicalRecord("medicalRecord","medicalRecord","com.nv.netmd.pl.entity.MedicalRecordTbl","",""),
	date("date","date","com.nv.netmd.pl.entity.MedicalRecordTbl","",""),
	type("type","type","com.nv.netmd.pl.entity.MedicalRecordTbl","",""),
	patientId("patientId","id","com.nv.netmd.pl.entity.PatientTbl","patientTbl",""),
	doctorId("doctorId","id","com.nv.netmd.pl.entity.DoctorTbl","doctorTbl",""),
	caseId("caseId","id","com.nv.netmd.pl.entity.CaseTbl","caseTbl",""),
	caseName("caseName","caseName","com.nv.netmd.pl.entity.CaseTbl","caseTbl",""),
	status("status","status","com.nv.netmd.pl.entity.MedicalRecordTbl","",""),
	;
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	private MedicalPropertyEnum(String displayName, String fieldName,
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
