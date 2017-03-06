/**
 * BillDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.pl.mockImpl;

import java.util.List;

import com.nv.netmd.billing.pl.dao.BillDao;
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
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillMockDaoImpl implements BillDao{

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#create(com.nv.netmd.rs.dto.BillDTO)
	 */
	@Override
	public ResponseDTO create(BillDTO bill) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#view(java.lang.String)
	 */
	@Override
	public BillViewResponseDTO view(String uid) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillHeader(com.nv.netmd.rs.dto.BillHeaderDTO)
	 */
	@Override
	public ResponseDTO updateBillHeader(BillHeaderDTO billHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillItems(com.nv.netmd.rs.dto.BillItemListDTO)
	 */
	@Override
	public BillResponseDTO updateBillItems(BillItemListDTO billItems) {
		// TODO Auto-generated method stub
		return null;
	}

	

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getBillDiscountList(int)
	 */
	@Override
	public List<BillDiscountDTO> getBillDiscountList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getBill(java.lang.String)
	 */
	@Override
	public BillViewResponseDTO getBill(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillDiscount(com.nv.netmd.rs.dto.BillDiscountDetailsDTO)
	 */
	@Override
	public BillResponseDTO updateBillDiscount(
			BillDiscountDetailsDTO billDiscountDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillPayment(com.nv.netmd.rs.dto.BillPaymentDTO)
	 */
	@Override
	public BillResponseDTO updateBillPayment(BillPaymentDTO payment) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getBillItemById(int, int)
	 */
	@Override
	public BillItemDTO getBillItemById(int id, int billId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getBillSupportById(int, int)
	 */
	@Override
	public BillSupportDTO getBillSupportById(int id, int billId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getBillBedById(int, int)
	 */
	@Override
	public BillBedDTO getBillBedById(int id, int billId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getNewBills()
	 */
	@Override
	public List<BillSummaryDTO> getNewBills() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getUpdatedBills()
	 */
	@Override
	public List<BillSummaryDTO> getUpdatedBills() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#addBillSyncResponse(com.nv.netmd.rs.dto.BillSyncResponseDTO)
	 */
	@Override
	public void addBillSyncResponse(BillSyncResponseDTO billResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillSyncResponse(com.nv.netmd.rs.dto.BillSyncResponseDTO)
	 */
	@Override
	public void updateBillSyncResponse(BillSyncResponseDTO billResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BillResponseDTO discharge(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillResponseDTO setBillStatus(String uid,float billAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getBillAmount(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalAmountPaid(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseDTO updateBillStatus(BillStatusDTO billStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetMdDTO getlogo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillListResponseDTO getBills() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
