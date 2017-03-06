 /**
* headDao.java
* @author Nithesh Mohanan
*
* Version 1.0 Dec 26, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ReportDTO;
import com.nv.netmd.rs.dto.ReportDetail;

/**
 * @author Nithesh Mohanan
 *
 */
public interface ReportDao {

	/**
	 * @param report
	 * @return ReportDTO
	 */
	public ReportDTO dailyReportList(ReportDetail report) throws PersistenceException;

	public ReportDTO getByDate(String date) throws PersistenceException;
}
