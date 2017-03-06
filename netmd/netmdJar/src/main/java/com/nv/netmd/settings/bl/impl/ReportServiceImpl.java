 /**
* HeadServiceImpl.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 24, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ReportDTO;
import com.nv.netmd.rs.dto.ReportDetail;
import com.nv.netmd.settings.bl.service.ReportService;
import com.nv.netmd.settings.bl.validator.ReportValidator;
import com.nv.netmd.settings.pl.dao.ReportDao;




/**
 * @author Nithesh Mohanan
 *
 */
public class ReportServiceImpl implements ReportService {
     private ReportValidator reportValidator;
     private ReportDao reportDao;
     
	/**
	 * @param report 
	 * @return
	 * @throws ServiceException 
	 */
    @Override
	public ReportDTO dailyReportList(ReportDetail report) throws ServiceException {
		reportValidator.validateReport(report);
		ReportDTO response;
		try {
			response = reportDao.dailyReportList(report);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		 return response;
	}
    
    public ReportDTO getByDate(String date) throws ServiceException {
    	reportValidator.validateDate(date);
    	ReportDTO response;
		try {
			response = reportDao.getByDate(date);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the reportValidator
	 */
	public ReportValidator getReportValidator() {
		return reportValidator;
	}

	/**
	 * @param reportValidator the reportValidator to set
	 */
	public void setReportValidator(ReportValidator reportValidator) {
		this.reportValidator = reportValidator;
	}

	/**
	 * @return the reportDao
	 */
	public ReportDao getReportDao() {
		return reportDao;
	}

	/**
	 * @param reportDao the reportDao to set
	 */
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

}
