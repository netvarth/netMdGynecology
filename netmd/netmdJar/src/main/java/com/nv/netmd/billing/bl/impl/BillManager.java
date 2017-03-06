/**
 * BillManager.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.bl.impl;


import java.util.ArrayList;
import java.util.List;



import com.nv.netmd.billing.bl.service.BillService;
import com.nv.netmd.billing.bl.validator.BillValidator;
import com.nv.netmd.billing.pl.dao.BillDao;
import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.discount.bl.service.DiscountService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
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
import com.nv.netmd.rs.dto.BillSupportDTO;
import com.nv.netmd.rs.dto.BillViewResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetMdBranchDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.TaxService;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillManager implements BillService {
	private BillValidator billValidator;
	private BillDao billDao;
	private TaxService taxService;
	private DiscountService discountService;
	private FilterService billFilterService;
	//private float grandTotal;
	//private float amountPaid;
	/** 
	 *create bill
	 *@param bill
	 *@return ResponseDTO
	 * @throws ServiceException 
	 *
	 */
	@Override
	public ResponseDTO create(BillDTO bill) throws ServiceException {
		float netAmount=0;
		
		if(bill.getItem()!=null){
			netAmount+=taxService.calItemTax(bill.getItem());
		}
		if(bill.getSupport()!=null){
			netAmount+=taxService.calSupportTax(bill.getSupport());
		}
		if(bill.getBed()!=null){
			netAmount+=taxService.calBedTax(bill.getBed());
		}
		bill.setGrandTotal(netAmount);
		
		if(bill.getDiscount()!=null){
			//find discount from discount module and set the values

			BillDiscountDetailsDTO	discountDetails=discountService.calculateBillDiscount(bill.getDiscount(), netAmount);
			if(discountDetails!=null){

				bill.setDiscount(discountDetails.getBillDiscountList());
				bill.setBillAmount(discountDetails.getBillAmount());
			}
		}
		billValidator.createBillValidator(bill);

		try {
			return billDao.create(bill);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getError(),e);	
		}
	}

	/** view bill
	 * @param uid
	 * @return  BillViewResponseDTO
	 */
	@Override
	public BillViewResponseDTO view(String uid) throws ServiceException {		
		try {
			return billDao.view(uid);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/** delete
	 * delete a bill
	 */
	@Override
	public ResponseDTO delete(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the billValidator
	 */
	public BillValidator getBillValidator() {
		return billValidator;
	}

	/**
	 * @param billValidator the billValidator to set
	 */
	public void setBillValidator(BillValidator billValidator) {
		this.billValidator = billValidator;
	}

	/**
	 * @return the billDao
	 */
	public BillDao getBillDao() {
		return billDao;
	}

	/**
	 * @param billDao the billDao to set
	 */
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	/**
	 * @return the taxService
	 */
	public TaxService getTaxService() {
		return taxService;
	}

	/**
	 * @param taxService the taxService to set
	 */
	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}

	/**
	 * @return the discountService
	 */
	public DiscountService getDiscountService() {
		return discountService;
	}

	/**
	 * @param discountService the discountService to set
	 */
	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}

	/**
	 * list the bill
	 * @param filterDTO
	 * @return BillListResponseDTO
	 */
	@Override
	public BillListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		BillListResponseDTO response=new BillListResponseDTO();
		ErrorDTO error;
		try {
			error = billFilterService.validate(filterDTO);
		} catch (ServiceException e1) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e1);	
		}
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=billFilterService.list(filterDTO);
		} catch (ServiceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the billFilterService
	 */
	public FilterService getBillFilterService() {
		return billFilterService;
	}

	/**
	 * @param billFilterService the billFilterService to set
	 */
	public void setBillFilterService(FilterService billFilterService) {
		this.billFilterService = billFilterService;
	}

	/** 
	 * update bill header
	 * @param billHeader
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO updateBillHeader(BillHeaderDTO billHeader) throws ServiceException {
		billValidator.updateHeaderValidate(billHeader);		
		try {
			return billDao.updateBillHeader(billHeader);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}

	/** 
	 * Update bill items
	 * @param billItems
	 * @return  BillResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BillResponseDTO updateBillItems(BillItemListDTO billItems) throws ServiceException {
		float addAmount=0;
		float delAmount=0;
		float updAmount=0;
		float grdTotal=0;
		float oldAmount=0;
		float netAmount=0;
		float billAmount=0;
		billValidator.updateBillItemsValidate(billItems);
		BillViewResponseDTO bil;
		try {
			bil = billDao.getBill(billItems.getBillUid());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}//get bill details

		grdTotal=bil.getGrandTotal();
		List<BillItemDTO> billItemList=new ArrayList<BillItemDTO>();
		List<BillItemDTO> billItemDelList=new ArrayList<BillItemDTO>();
		List<BillItemDTO> billItemUpList=new ArrayList<BillItemDTO>();
		List<BillSupportDTO>billSupportList=new ArrayList<BillSupportDTO>();
		List<BillSupportDTO>billSupportDelList=new ArrayList<BillSupportDTO>();
		List<BillSupportDTO>billSupportUpList=new ArrayList<BillSupportDTO>();
		List<BillBedDTO>billBedList=new ArrayList<BillBedDTO>();
		List<BillBedDTO>billBedDelList=new ArrayList<BillBedDTO>();
		List<BillBedDTO>billBedUpList=new ArrayList<BillBedDTO>();
		if(billItems.getItem()!=null){
			//check the action name and do the bill items calculation
			for (BillItemDTO billItemDTO : billItems.getItem()) {
				if(billItemDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					billItemList.add(billItemDTO);
				}	
				if(billItemDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					billItemUpList.add(billItemDTO);
				}
				if(billItemDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					billItemDelList.add(billItemDTO);
				}	
			}
			addAmount+=taxService.calItemTax(billItemList);
			delAmount+=getBillItemsValue(billItemDelList,bil.getId());
			oldAmount+=getBillItemsValue(billItemUpList,bil.getId());
			updAmount+=taxService.calItemTax(billItemUpList);
		}
		if(billItems.getSupport()!=null){
			//check the action name and do the support calculations
			for (BillSupportDTO billSupportDTO : billItems.getSupport()) {
				if(billSupportDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					billSupportList.add(billSupportDTO);
				}
				if(billSupportDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					billSupportUpList.add(billSupportDTO);
				}
				if(billSupportDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					billSupportDelList.add(billSupportDTO);
				}	
			}
			addAmount+=taxService.calSupportTax(billSupportList);
			delAmount+=getBillSupportValue(billSupportDelList,bil.getId());
			oldAmount+=getBillSupportValue(billSupportUpList,bil.getId());
			updAmount+=taxService.calSupportTax(billSupportUpList);
		}
		if(billItems.getBed()!=null){
			//check the action name and do the bed calculations
			for(BillBedDTO billBedDTO:billItems.getBed()){
				if(billBedDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					billBedList.add(billBedDTO);
				}
				if(billBedDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					billBedUpList.add(billBedDTO);
				}
				if(billBedDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					billBedDelList.add(billBedDTO);
				}
			}
			addAmount+=taxService.calBedTax(billBedList);
			delAmount+=getBillBedValue(billBedDelList,bil.getId());
			oldAmount+=getBillBedValue(billBedUpList,bil.getId());
			updAmount+=taxService.calBedTax(billBedUpList);
		}

		grdTotal+=addAmount;
		grdTotal+=updAmount;
		grdTotal-=delAmount;
		grdTotal-=oldAmount;
		billAmount=grdTotal;
		billItems.setGrandTotal(grdTotal);
		List<BillDiscountDTO> discontList;
		try {
			discontList = billDao.getBillDiscountList(bil.getId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		BillDiscountDetailsDTO	discountDetails=discountService.applyBillDiscount(discontList, grdTotal);
		if(discountDetails!=null){

			billItems.setDiscount(discountDetails.getBillDiscountList());
			billAmount=discountDetails.getBillAmount();
		}
		billItems.setBillAmount(billAmount);


		try {
			return billDao.updateBillItems(billItems);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
	}
	/**
	 * get items value
	 * @param items
	 * @param billId 
	 * @return float
	 */
	public float getBillItemsValue(List<BillItemDTO> items,int billId) throws ServiceException{
		float oldValue=0;		
		float taxVal=0;
		float sigItemVal=0;

		for (BillItemDTO billItemDTO : items) {
			BillItemDTO billItem;
			try {
				billItem = billDao.getBillItemById(billItemDTO.getItemId(),billId);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			
			taxVal=(billItem.getStdRate()*billItem.getTaxValue())/100;
			sigItemVal=taxVal+billItem.getStdRate();;
			if(billItem.getQuantity()!=0)
				oldValue+=sigItemVal*billItem.getQuantity();
		}
		System.out.println("get bill old value=="+oldValue);
		return oldValue;
	}
	/**
	 * get bill support value
	 * @param supports
	 * @param billId 
	 * @return float
	 */
	public float getBillSupportValue(List<BillSupportDTO> supports,int billId) throws ServiceException{
		float oldValue=0;		
		float taxVal=0;
		float sigItemVal=0;


		for (BillSupportDTO billSupportDTO : supports) {
			BillSupportDTO billSupport;
			try {
				billSupport = billDao.getBillSupportById(billSupportDTO.getSupportId(),billId);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
			}
			
			taxVal=(billSupport.getStdRate()*billSupport.getTaxValue())/100;
			sigItemVal=taxVal+billSupport.getStdRate();
			if(billSupport.getQuantity()!=0)
				oldValue+=sigItemVal*billSupport.getQuantity();
		}

		return oldValue;
	}

	/**
	 * get bill bed value
	 * @param beds
	 * @param billId 
	 * @return float
	 */
	public float getBillBedValue(List<BillBedDTO> beds,int billId) throws ServiceException{
		float oldValue=0;		
		float taxVal=0;
		float sigItemVal=0;

		for (BillBedDTO billBedDTO : beds) {
			BillBedDTO billBed;
			try {
				billBed = billDao.getBillBedById(billBedDTO.getBedId(),billId);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
			}
			
			taxVal=(billBed.getStdRate()*billBed.getTaxValue())/100;
			sigItemVal=taxVal+billBed.getStdRate();
			if(billBed.getQuantity()!=0)
				oldValue+=sigItemVal*billBed.getQuantity();
		}

		return oldValue;
	}

	/** 
	 * Update bill discount
	 * @param billDiscountDetails
	 * @return BillResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BillResponseDTO updateBillDiscount(BillDiscountDetailsDTO billDiscountDetails) throws ServiceException {
		float grdTotal=0;
		List<BillDiscountDTO> billDiscountDTOList=new ArrayList<BillDiscountDTO>();
		List<BillDiscountDTO> billDiscountAddList=new ArrayList<BillDiscountDTO>();
		List<BillDiscountDTO> billDiscountUpList=new ArrayList<BillDiscountDTO>();

		billValidator.validateBillDiscount(billDiscountDetails);
		BillViewResponseDTO response;
		try {
			response = billDao.getBill(billDiscountDetails.getBillUid());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		grdTotal=response.getGrandTotal();
		for (BillDiscountDTO billDiscountDTO : billDiscountDetails.getBillDiscountList()) {
			if(billDiscountDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
				billDiscountAddList.add(billDiscountDTO);
			}
			if(!billDiscountDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
				billDiscountUpList.add(billDiscountDTO);
			}

		}
		if(!billDiscountAddList.isEmpty()){
			BillDiscountDetailsDTO	discountDetails=discountService.calculateBillDiscount(billDiscountAddList, grdTotal);
			if(discountDetails!=null){
				billDiscountDTOList.addAll(discountDetails.getBillDiscountList());			
			}
		}
		if(!billDiscountUpList.isEmpty()){
			billDiscountDTOList.addAll(billDiscountUpList);
		}

		billDiscountDetails.setBillDiscountList(billDiscountDTOList);


		try {
			return billDao.updateBillDiscount(billDiscountDetails);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
	}

	/** 
	 * update bill payment
	 * @param payment
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BillResponseDTO updateBillPayment(BillPaymentDTO payment) throws ServiceException {
		billValidator.validateBillPayment(payment);
		try {
			return billDao.updateBillPayment(payment);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
	}

	@Override
	public BillResponseDTO discharge(String uid) throws ServiceException {
		double totalAmountPaid=0; 
		double amountTobeRepaid=0;
		float billAmount=0;
		billValidator.validateBill(uid);
		BillResponseDTO response=new BillResponseDTO();
		try {
			totalAmountPaid= billDao.getTotalAmountPaid(uid);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		try {
			billAmount=billDao.getBillAmount(uid);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		if(totalAmountPaid>=billAmount){
			if(totalAmountPaid>billAmount){
				 amountTobeRepaid=totalAmountPaid-billAmount;
				
			}
			try {
				response=billDao.setBillStatus(uid,billAmount);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
			}
			
		}
		else{
			ServiceException se=new ServiceException(ErrorCodeEnum.BillShouldBeFullyPaid);
			se.isDisplayErrMsg();
			throw se;
			
		}
		response.setUid(uid);
		response.setAmountToBePaid(amountTobeRepaid);
		response.setError(null)	;
		response.setSuccess(true);
		return response;
	}

	@Override
	public ResponseDTO updateBillStatus(BillStatusDTO billStatus) throws ServiceException {
		billValidator.updateBillStatusValidation(billStatus);		
		try {
			return billDao.updateBillStatus(billStatus);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
	}

	@Override
	public NetMdDTO getlogo() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return billDao.getlogo();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
	}

	@Override
	public BillListResponseDTO getBills() throws ServiceException {
		BillListResponseDTO response;
		try {
			response = billDao.getBills();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);		
		}
		return response;
	}
	
	
}
