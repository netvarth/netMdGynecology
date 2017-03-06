/**
 * DoctorPropertyEnum.java
 *
 * Sep 11, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.util.filter.core.Property;



/**
 * 
 */
public enum DoctorPropertyEnum implements Property,EnumDisplay{
	
	firstName("firstName","firstName","com.nv.netmd.pl.entity.DoctorTbl","","","Doctor Name"),
	specialization("specialization","specialization","com.nv.netmd.pl.entity.DoctorTbl","","","Specialization"),
	email("email","email","com.nv.netmd.pl.entity.DoctorTbl","","","Email"),
	mobile("mobile","mobile","com.nv.netmd.pl.entity.DoctorTbl","","","Mobile"),	
	status("status","status","com.nv.netmd.pl.entity.DoctorTbl","","","Status"),	
	address("address","address","com.nv.netmd.pl.entity.DoctorTbl","","","Address"),
	;
	
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	String parameterName;
	
	private DoctorPropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName,String parameterName) {
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
