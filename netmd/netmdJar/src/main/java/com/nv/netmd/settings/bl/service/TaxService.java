/**
 * TaxService.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;

import java.util.List;




import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BillBedDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillSupportDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.rs.dto.TaxViewResponseDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public interface TaxService {
	/**
	 * @param taxdto
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(TaxDTO taxdto) throws ServiceException;
	/**
	 * @param id
	 * @return TaxViewResponseDTO
	 * @throws ServiceException 
	 */
	public TaxViewResponseDTO view(int id) throws ServiceException;
	/**
	 * @param taxdto
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(TaxDTO taxdto) throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int id) throws ServiceException;
	/**
	 * @param filterdto
	 * @return TaxListResponseDTO
	 * @throws ServiceException 
	 */
	public TaxListResponseDTO getList(FilterDTO filterdto) throws ServiceException;
	/**
	 * @return TaxListResponseDTO
	 * @throws ServiceException 
	 */
	public TaxListResponseDTO getTaxes() throws ServiceException;
	/**
	 * @param billItem
	 * @return float
	 * @throws ServiceException 
	 */
	public float calItemTax(List<BillItemDTO> billItem) throws ServiceException;
	/**
	 * @param billBed
	 * @return float
	 * @throws ServiceException 
	 */
	public float calBedTax(List<BillBedDTO> billBed) throws ServiceException;
	/**
	 * @param billSupport
	 * @return float
	 * @throws ServiceException 
	 */
	public float calSupportTax(List<BillSupportDTO> billSupport) throws ServiceException ;

}
