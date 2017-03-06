/**
* ResultDao.java
* 
* @Author Sreeram
*
* Version 1.0 Jan 5, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.dao;


import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;


/**
 * 
 */
public interface ResultDao {
	public ViewResultDTO viewResult(int id)throws PersistenceException;
	//public ResultListResponseDTO listResult(int patientId);
	public RetrieveResultsResponseDTO resultFromYNW(RetrieveResultsResponseDTO result)throws PersistenceException;
}
