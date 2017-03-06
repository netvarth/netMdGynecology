/**
 * ResultService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;


/**
 * 
 */
public interface ResultService {
	public ViewResultDTO viewResult(int id) throws ServiceException;

	//public ResultListResponseDTO listResult(int patientId);
	public ResultListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	public RetrieveResultsResponseDTO resultFromYNW(RetrieveResultsResponseDTO result) throws ServiceException;
}
