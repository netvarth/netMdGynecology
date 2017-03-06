/**
 * BillDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.pl.dao;

import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.BillBedDTO;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillHeaderDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;
import com.nv.netmd.rs.dto.BillResponseDTO;
import com.nv.netmd.rs.dto.BillStatusDTO;
import com.nv.netmd.rs.dto.BillSummaryDTO;
import com.nv.netmd.rs.dto.BillSupportDTO;
import com.nv.netmd.rs.dto.BillSyncResponseDTO;
import com.nv.netmd.rs.dto.BillViewResponseDTO;
import com.nv.netmd.rs.dto.NetMdBranchDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.PatientResponse;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface BillDao {
	/**
	 * @param bill
	 * @return ResponseDTO
	 */
	public ResponseDTO create(BillDTO bill)throws PersistenceException;
	/**
	 * @param uid
	 * @return BillViewResponseDTO
	 */
	public BillViewResponseDTO view(String uid)throws PersistenceException;
	
	/**
	 * @param billHeader
	 * @return ResponseDTO
	 */
	public BillResponseDTO discharge(String uid) throws PersistenceException;
	/**
	 * @param uid
	 * @return BillResponseDTO
	 */
	public ResponseDTO updateBillHeader(BillHeaderDTO billHeader)throws PersistenceException ;
	/**
	 * @param billItems
	 * @return BillResponseDTO
	 */
	public BillResponseDTO updateBillItems(BillItemListDTO billItems)throws PersistenceException;
	
	/**
	 * @param id
	 * @param billId 
	 * @return BillItemDTO
	 */
	public BillItemDTO getBillItemById(int id,int billId)throws PersistenceException;
	
	/**
	 * @param id
	 * @param billId 
	 * @return BillSupportDTO
	 */
	public	BillSupportDTO getBillSupportById(int id,int billId)throws PersistenceException;
	
	/**
	 * @param id 
	 * @param billId 
	 * @return BillBedDTO
	 */
	public	BillBedDTO getBillBedById(int id,int billId)throws PersistenceException;
	
	/**
	 * @param id
	 * @return List<BillDiscountDTO>
	 */
	public List<BillDiscountDTO> getBillDiscountList(int id)throws PersistenceException;
	
	/**
	 * @param uid
	 * @return BillViewResponseDTO
	 */
	public BillViewResponseDTO getBill(String uid)throws PersistenceException;
	
	/**
	 * @param billDiscountDetails
	 * @return BillResponseDTO
	 */
	public BillResponseDTO updateBillDiscount(BillDiscountDetailsDTO billDiscountDetails)throws PersistenceException;
	
	/**
	 * @param payment
	 * @return BillResponseDTO
	 */
	public BillResponseDTO updateBillPayment(BillPaymentDTO payment)throws PersistenceException;
	
	
	/**
	 * @return List<BillSummaryDTO>
	 */
	public List<BillSummaryDTO> getNewBills()throws PersistenceException;
	
	/**
	 * @return List<BillSummaryDTO>
	 */
	public List<BillSummaryDTO> getUpdatedBills()throws PersistenceException;
	
	/**
	 * @param billResponse
	 */
	public void addBillSyncResponse(BillSyncResponseDTO billResponse)throws PersistenceException;
	//public void deleteBillSyncResponse(PatientResponse patientResponse);
	/**
	 * @param billResponse
	 */
	public void updateBillSyncResponse(BillSyncResponseDTO billResponse)throws PersistenceException;
	
	public BillResponseDTO setBillStatus(String uid,float billAmount)throws PersistenceException;
	
	public float getBillAmount(String uid)throws PersistenceException;
	
	public double getTotalAmountPaid(String uid)throws PersistenceException;
	
	public ResponseDTO updateBillStatus(BillStatusDTO billStatus)throws PersistenceException;
	
	/**
	 * @param payment
	 * @return BillResponseDTO
	 */
	public NetMdDTO getlogo()throws PersistenceException;
	public BillListResponseDTO getBills()throws PersistenceException;
	
	
}
