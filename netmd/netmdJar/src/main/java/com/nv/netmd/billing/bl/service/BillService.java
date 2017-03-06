/**
 * BillService.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.bl.service;




import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillHeaderDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;
import com.nv.netmd.rs.dto.BillResponseDTO;
import com.nv.netmd.rs.dto.BillStatusDTO;
import com.nv.netmd.rs.dto.BillViewResponseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BillService {
	/**
	 * @param bill
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(BillDTO bill) throws ServiceException;

	/**
	 * @param uid
	 * @return BillViewResponseDTO
	 * @throws ServiceException 
	 */
	public BillViewResponseDTO view(String uid) throws ServiceException;
	/**
	 * @param uid
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public BillResponseDTO discharge(String uid) throws ServiceException;
	/**
	 * @param uid
	 * @return BillResponseDTO
	 */
	public ResponseDTO delete(String uid) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return BillListResponseDTO
	 */
	public BillListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	
	/**
	 * @param billHeader
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO updateBillHeader( BillHeaderDTO billHeader) throws ServiceException;
	/**
	 * @param billStatus
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO updateBillStatus( BillStatusDTO billStatus) throws ServiceException;
	/**
	 * @param billItems
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	public BillResponseDTO updateBillItems(BillItemListDTO billItems) throws ServiceException;
	
	/**
	 * @param billDiscountDetails
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	public BillResponseDTO updateBillDiscount (BillDiscountDetailsDTO billDiscountDetails) throws ServiceException;
	/**
	 * @param payment
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	public BillResponseDTO updateBillPayment (BillPaymentDTO payment) throws ServiceException;
	/**
	 * @param bill list
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	public BillListResponseDTO getBills() throws ServiceException;
	
	/**
	 * @param payment
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	public NetMdDTO getlogo() throws ServiceException;
}
