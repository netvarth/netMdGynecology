/**
* MedicalMockList.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 25, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;

/**
 * 
 */
public class MedicalMockList implements FilterService{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public MedicalListResponseDTO list(FilterDTO filterDTO) {
		MedicalListResponseDTO list=new MedicalListResponseDTO();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

}
