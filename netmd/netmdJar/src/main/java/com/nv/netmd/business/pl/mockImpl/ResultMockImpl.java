/**
* ResultMockImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 26, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.ResultDao;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResultListResponseDTO;
import com.nv.netmd.rs.dto.RetrieveResultsResponseDTO;
import com.nv.netmd.rs.dto.ViewResultDTO;

/**
 * 
 */
public class ResultMockImpl implements ResultDao,FilterService{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ResultListResponseDTO list(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		ResultListResponseDTO resultList = new ResultListResponseDTO();
		return resultList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ResultDao#viewResult(int)
	 */
	@Override
	public ViewResultDTO viewResult(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.ResultDao#resultFromYNW(com.nv.netmd.rs.dto.RetrieveResultsResponseDTO)
	 */
	@Override
	public RetrieveResultsResponseDTO resultFromYNW(
			RetrieveResultsResponseDTO result) {
		// TODO Auto-generated method stub
		RetrieveResultsResponseDTO response=new RetrieveResultsResponseDTO();
		return response;
	}

}
