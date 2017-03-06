/**
 * BillValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.bl.validator;


import java.util.List;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.BillStatusEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.rs.dto.BillBedDTO;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillHeaderDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;
import com.nv.netmd.rs.dto.BillStatus;
import com.nv.netmd.rs.dto.BillStatusDTO;
import com.nv.netmd.rs.dto.BillSupportDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillValidator {
	public void createBillValidator(BillDTO bill) throws ServiceException{
		if(bill.getPatientId()<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
			se.isDisplayErrMsg();
			throw se;
		}
		OriginEnum origin=OriginEnum.getEnum(bill.getOrigin());
		if(bill.getOrigin().equals(OriginEnum.OutPatient.getDisplayName())){
		if (bill.getAmountPaid()< bill.getBillAmount()){
				ServiceException se=new ServiceException(ErrorCodeEnum.BillShouldBeFullyPaid);
				se.isDisplayErrMsg();
				throw se;
			}
			
				
			
		}	

		//PayStatusEnum payStaus=PayStatusEnum.getEnum(bill.getPaymentStatus());
		if(!bill.getItem().isEmpty()){
			for (BillItemDTO billItem : bill.getItem()) {
				if(billItem.getItemId()<0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillItemIdShouldNotBeNull);
					se.isDisplayErrMsg();
					throw se;
				}
				if(billItem.getQuantity()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
					se.isDisplayErrMsg();
					throw se;
				}
			}

		}
		if(!bill.getBed().isEmpty()){
			for (BillBedDTO billbed : bill.getBed()) {
				if(billbed.getBedId()<0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillBedIdShouldNotBeNull);
					se.isDisplayErrMsg();
					throw se;
				}
				if(billbed.getQuantity()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
					se.isDisplayErrMsg();
					throw se;
				}
			}

		}
		if(!bill.getSupport().isEmpty()){
			for (BillSupportDTO billSupport : bill.getSupport()) {
				if(billSupport.getSupportId()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillSupportIdShouldNotBeNull);
					se.isDisplayErrMsg();
					throw se;
				}
				if(billSupport.getQuantity()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
					se.isDisplayErrMsg();
					throw se;
				}
			}

		}
		if(!bill.getDiscount().isEmpty()){
			for (BillDiscountDTO billDiscountDTO : bill.getDiscount()) {
				if(billDiscountDTO.getDiscountId()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.DiscountIdNull);
					se.isDisplayErrMsg();
					throw se;
				}
				//CalculationTypeEnum cal=CalculationTypeEnum.getEnum(billDiscountDTO.getCalculationType());
				//DiscountTypeEnum discType=DiscountTypeEnum.getEnum(billDiscountDTO.getDiscountType());
			}
		}
	}

	/**
	 * validate header of bill
	 * @param billHeader
	 * @throws ServiceException 
	 */
	public void updateHeaderValidate(BillHeaderDTO billHeader) throws ServiceException{
		if(!isValidType(billHeader.getUid())){
			//error
			ServiceException se=new ServiceException(ErrorCodeEnum.BillUidNull);
			se.isDisplayErrMsg();
			throw se;
		}
		if(billHeader.getPatientId()<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
			se.isDisplayErrMsg();
			throw se;
		}
		OriginEnum origin=OriginEnum.getEnum(billHeader.getOrigin());
	}
	private boolean isValidType(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	
	public void validateBill(String uid) throws ServiceException{
		if(!isValidType(uid)){
			//error
			ServiceException se=new ServiceException(ErrorCodeEnum.BillUidNull);
			se.isDisplayErrMsg();
			throw se;
		}
	}
	

	/**
	 * @param bill 
	 * @throws ServiceException 
	 *
	 */
	public void updateBillItemsValidate(BillItemListDTO bill) throws ServiceException{
		if(!isValidType(bill.getBillUid())){
			//error
			ServiceException se=new ServiceException(ErrorCodeEnum.BillUidNull);
			se.isDisplayErrMsg();
			throw se;
		}
		if(!bill.getItem().isEmpty()){
			for (BillItemDTO billItem : bill.getItem()) {
				ActionNameEnum action=ActionNameEnum.getEnum(billItem.getActionName());
				if(!billItem.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){

					if(billItem.getItemId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.ItemIdNull);
						se.isDisplayErrMsg();
						throw se;
					}
					if(billItem.getQuantity()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
						se.isDisplayErrMsg();
						throw se;
					}
				}

				if(billItem.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(billItem.getItemId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.ItemIdNull);
						se.isDisplayErrMsg();
						throw se;
					}
				}
			}

		}
		if(!bill.getBed().isEmpty()){
			for (BillBedDTO billbed : bill.getBed()) {
				ActionNameEnum action=ActionNameEnum.getEnum(billbed.getActionName());
				if(!billbed.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(billbed.getBedId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BedIdNull);
						se.isDisplayErrMsg();
						throw se;
					}
					if(billbed.getQuantity()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
						se.isDisplayErrMsg();
						throw se;
					}
				}

				if(billbed.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(billbed.getBedId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BedIdNull);
						se.isDisplayErrMsg();
						throw se;
					}
				}
			}

		}
		if(!bill.getSupport().isEmpty()){
			for (BillSupportDTO billSupport : bill.getSupport()) {
				ActionNameEnum action=ActionNameEnum.getEnum(billSupport.getActionName());
				if(!billSupport.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(billSupport.getSupportId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BillSupportIdShouldNotBeNull);
						se.isDisplayErrMsg();
						throw se;
					}
					if(billSupport.getQuantity()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BillQuantity);
						se.isDisplayErrMsg();
						throw se;
					}
				}

				if(billSupport.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					if(billSupport.getSupportId()<=0){
						ServiceException se=new ServiceException(ErrorCodeEnum.BillSupportIdShouldNotBeNull);
						se.isDisplayErrMsg();
						throw se;
					}
				}
			}

		}
	}
	/**
	 * validate bill discount 
	 * @param bill
	 * @throws ServiceException 
	 */
	public void validateBillDiscount(BillDiscountDetailsDTO bill) throws ServiceException{
		if(!isValidType(bill.getBillUid())){
			//error
			ServiceException se=new ServiceException(ErrorCodeEnum.BillUidNull);
			se.isDisplayErrMsg();
			throw se;
		}
		for (BillDiscountDTO billDiscountDTO : bill.getBillDiscountList()) {
			ActionNameEnum action=ActionNameEnum.getEnum(billDiscountDTO.getActionName());

			if(billDiscountDTO.getDiscountId()<=0){
				ServiceException se=new ServiceException(ErrorCodeEnum.DiscountIdNull);
				se.isDisplayErrMsg();
				throw se;
			}



		}
	}

	/**
	 * validate bill payment
	 * @param payment
	 * @throws ServiceException 
	 */
	public void validateBillPayment(BillPaymentDTO payment) throws ServiceException{
		if(!isValidType(payment.getBillUid())){
			//error
			ServiceException se=new ServiceException(ErrorCodeEnum.BillUidNull);
			se.isDisplayErrMsg();
			throw se;
		}
		if(payment.getPaidAmount()<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.PaymentShouldNotZero);
			se.isDisplayErrMsg();
			throw se;
		}
		if(!isValidType(payment.getNote())){
			ServiceException se=new ServiceException(ErrorCodeEnum.NoNote);
			se.isDisplayErrMsg();
			throw se;
		}
	}

	public void updateBillStatusValidation(BillStatusDTO billStatus) throws ServiceException {
		
		if(billStatus.getBillstatus().isEmpty())
			throw new ServiceException(ErrorCodeEnum.BillstatusListEmpty);
		
		//String billStatusValues=billStatus.getBillstatus();
		
			if(billStatus.getBillstatus()==null)
				throw new ServiceException(ErrorCodeEnum.Billstatus);
			
			if(!billStatus.getBillstatus().equals(BillStatusEnum.Open.getDisplayName()) &&  !billStatus.getBillstatus().equals(BillStatusEnum.Closed.getDisplayName())&&
					!billStatus.getBillstatus().equals(BillStatusEnum.Cancelled.getDisplayName()))
				throw new ServiceException(ErrorCodeEnum.BillstatusInvalid);
			
		
			if(billStatus.getUid()==null || billStatus.getUid()=="")
				throw new ServiceException(ErrorCodeEnum.BillstatusUid);
	
		if(billStatus.getNote()==null || billStatus.getNote()=="")
			throw new ServiceException(ErrorCodeEnum.BillstatusNotNull);
	}
	
}

