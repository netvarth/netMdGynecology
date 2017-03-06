/**
* EnumListResponseDTO.java
* 
* @Author Linu Paul
*
* Sep 11, 2012
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class EnumListResponseDTO {
	
	private List<EnumDTO> enumListDTO= new ArrayList<EnumDTO>();

	/**
	 * 
	 */
	public EnumListResponseDTO() {
	}

	/**
	 * @param enumListDTO
	 */
	private EnumListResponseDTO(List<EnumDTO> enumListDTO) {
		super();
		this.enumListDTO = enumListDTO;
	}

	/**
	 * @return the enumListDTO
	 */
	public List<EnumDTO> getEnumListDTO() {
		return enumListDTO;
	}

	/**
	 * @param enumListDTO the enumListDTO to set
	 */
	public void setEnumListDTO(List<EnumDTO> enumListDTO) {
		this.enumListDTO = enumListDTO;
	}


	

	

	

	
	
	
	
	
	
	
	
}