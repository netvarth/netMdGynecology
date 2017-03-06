 /**
* ReportService.java
* @author Nithesh Mohanan
*
* Version 1.0 DEC 26, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ReportDTO;
import com.nv.netmd.rs.dto.ReportDetail;




/**
 * @author Nithesh Mohanan
 *
 */
public interface ReportService {
	
	/**
	 * @param report
	 * @return ReportDTO
	 * @throws ServiceException 
	 */
	public ReportDTO dailyReportList(ReportDetail report) throws ServiceException;	
	/**
	 * @param date
	 * @return report of mentioned date
	 * @throws ServiceException 
	 */
	public ReportDTO getByDate(String date) throws ServiceException;
}
