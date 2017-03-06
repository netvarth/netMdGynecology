/**
 * DiscountService.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.bl.service;

import java.util.List;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.DiscountValueDTO;
import com.nv.netmd.rs.dto.DiscountValueResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface DiscountService {
	/**
	 * @param discount
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(DiscountDTO discount) throws ServiceException;
	/**
	 * @param discount
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(DiscountDTO discount) throws ServiceException;
	/**
	 * @param id
	 * @return DiscountDTO
	 */
	public DiscountDTO view(int id)throws ServiceException;
	/**
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO delete(int id)throws ServiceException;
	/**
	 * @param filterDTO
	 * @return DiscountListResponseDTO
	 */
	public DiscountListResponseDTO list(FilterDTO filterDTO)throws ServiceException;
	/**
	 * @return DiscountListResponseDTO
	 */
	public DiscountListResponseDTO getDiscounts()throws ServiceException;
	/**
	 * @param billDiscount
	 * @param grandTotal 
	 * @return BillDiscountDetailsDTO
	 * @throws ServiceException 
	 */
	public BillDiscountDetailsDTO calculateBillDiscount(List<BillDiscountDTO>billDiscount,float grandTotal) throws ServiceException;
	/**
	 * @param billDiscount
	 * @param grandTotal
	 * @return BillDiscountDetailsDTO
	 * @throws ServiceException 
	 */
	public BillDiscountDetailsDTO applyBillDiscount(List<BillDiscountDTO>billDiscount,float grandTotal) throws ServiceException;
	
	/**
	 * @param discount
	 * @return DiscountValueResponseDTO
	 * @throws ServiceException 
	 */
	public DiscountValueResponseDTO getDiscountValue(DiscountValueDTO discount) throws ServiceException;
}
