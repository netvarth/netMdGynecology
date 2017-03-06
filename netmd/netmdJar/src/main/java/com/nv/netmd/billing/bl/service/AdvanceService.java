 /**
* AdvanceService.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.AdvanceDTO;
import com.nv.netmd.rs.dto.AdvanceListResponseDTO;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

public interface AdvanceService {
	
	
	public ResponseDTO create(AdvanceDTO advance) throws ServiceException;
	
	
	public ResponseDTO update(AdvanceDTO advance) throws ServiceException;
	
	
	public AdvanceDTO view(int id) throws ServiceException;
	
	public ResponseDTO delete(int id) throws ServiceException;
	
	public AdvanceListResponseDTO list(FilterDTO filterDTO) throws ServiceException;

}
