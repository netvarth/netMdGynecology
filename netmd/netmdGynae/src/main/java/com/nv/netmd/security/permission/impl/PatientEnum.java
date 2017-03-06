/**
* PatientEnum.java
* 
* @Author Sreeram
*
* Version 1.0 Jul 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.security.permission.impl;

import com.nv.netmd.rs.dto.Permission;





/**
 * 
 */
public enum PatientEnum implements Permission{
	createPatient("CreatePatient","Create Patient",0x00000001,"/netmd/ws/ui/patient/create");
	/**
	 * @param name
	 * @param title
	 * @param hexabit
	 * @param url
	 */
	private PatientEnum(String name, String title, Integer hexabit, String url) {
		this.name = name;
		this.title = title;
		this.hexabit = hexabit;
		this.url = url;
	}
	private final String permissionGroup="patient";
	
	private String name;
	private String title;
	private final String uid = permissionGroup+"."+name;
	private Integer hexabit;
	private String url;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the hexabit
	 */
	public Integer getHexabit() {
		return hexabit;
	}
	/**
	 * @param hexabit the hexabit to set
	 */
	public void setHexabit(Integer hexabit) {
		this.hexabit = hexabit;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the permissionGroup
	 */
	public String getPermissionGroup() {
		return permissionGroup;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.security.permission.Permission#getHexaBit()
	 */
	@Override
	public Integer getHexaBit() {
		// TODO Auto-generated method stub
		return this.hexabit;
	}
}
