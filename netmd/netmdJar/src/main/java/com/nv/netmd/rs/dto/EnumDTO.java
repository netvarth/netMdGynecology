/**
* EnumDTO.java
* 
* @Author Linu Paul
*
* Aug 22, 2012
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;



public class EnumDTO {
	private String key;
	private  List<FilterEnumValuesDTO> EnumValues = new ArrayList<FilterEnumValuesDTO>();
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the enumValues
	 */
	public List<FilterEnumValuesDTO> getEnumValues() {
		return EnumValues;
	}
	/**
	 * @param enumValues the enumValues to set
	 */
	public void setEnumValues(List<FilterEnumValuesDTO> enumValues) {
		EnumValues = enumValues;
	}
	/**
	 * @param key
	 * @param enumValues
	 */
	private EnumDTO(String key, List<FilterEnumValuesDTO> enumValues) {
		super();
		this.key = key;
		EnumValues = enumValues;
	}
	/**
	 * 
	 */
	public EnumDTO() {
	}
	
	
}
