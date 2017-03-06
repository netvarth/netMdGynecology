/**
 * DiscountServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.discount.bl.service.DiscountService;
import com.nv.netmd.discount.bl.validator.DiscountValidator;
import com.nv.netmd.discount.pl.dao.DiscountDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.DiscountValueDTO;
import com.nv.netmd.rs.dto.DiscountValueResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;


/**
 *
 *
 * @author Sreeram T G
 */
public class DiscountServiceImpl implements DiscountService{
	private DiscountValidator discountValidator;
	private DiscountDao discountDao;
	private FilterService discountFilterService;
	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#create(com.nv.netmd.rs.dto.DiscountDTO)
	 */
	@Override
	public ResponseDTO create(DiscountDTO discount) throws ServiceException {
		discountValidator.validateCreateDiscount(discount);
		ResponseDTO response;
		try {
			response = discountDao.create(discount);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#update(com.nv.netmd.rs.dto.DiscountDTO)
	 */
	@Override
	public ResponseDTO update(DiscountDTO discount) throws ServiceException {
		discountValidator.validateUpdateDiscount(discount);
		ResponseDTO response;
		try {
			response = discountDao.update(discount);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#view(int)
	 */
	@Override
	public DiscountDTO view(int id)throws ServiceException {
		DiscountDTO response;
		try {
			response = discountDao.getDiscountById(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id)throws ServiceException {
		ResponseDTO response;
		try {
			response = discountDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public DiscountListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		DiscountListResponseDTO response=new DiscountListResponseDTO();
		ErrorDTO error=discountFilterService.validate(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response=discountFilterService.list(filterDTO);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.bl.service.DiscountService#getDiscounts()
	 */
	@Override
	public DiscountListResponseDTO getDiscounts() throws ServiceException {
		DiscountListResponseDTO response;
		try {
			response = discountDao.getDiscounts();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the discountValidator
	 */
	public DiscountValidator getDiscountValidator() {
		return discountValidator;
	}

	/**
	 * @param discountValidator the discountValidator to set
	 */
	public void setDiscountValidator(DiscountValidator discountValidator) {
		this.discountValidator = discountValidator;
	}

	/**
	 * @return the discountDao
	 */
	public DiscountDao getDiscountDao() {
		return discountDao;
	}

	/**
	 * @param discountDao the discountDao to set
	 */
	public void setDiscountDao(DiscountDao discountDao) {
		this.discountDao = discountDao;
	}

	/** 
	 * calculate bill discount
	 * @param billDiscount
	 * @param grandTotal
	 * @return BillDiscountDetailsDTO
	 * @throws ServiceException 
	 */
	@Override
	public BillDiscountDetailsDTO calculateBillDiscount(List<BillDiscountDTO> billDiscount,float grandTotal) throws ServiceException {
		float billAmount=0;
		float discountGrand=0;
		BillDiscountDetailsDTO response=new BillDiscountDetailsDTO();
		List<BillDiscountDTO> billDiscountList=new  ArrayList<BillDiscountDTO>();
		for (BillDiscountDTO billDiscountDTO : billDiscount) {

			DiscountDTO discount;
			try {
				discount = discountDao.getDiscountById(billDiscountDTO.getDiscountId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			
			if(discount.getDiscType().equals(DiscountTypeEnum.Predefined.getDisplayName())){
				BillDiscountDTO billDisc=getDiscount(grandTotal,discount.getCalculationType(),discount.getDiscValue(),discount.getDiscType(),billDiscountDTO.getDiscountId(),discount.getName(),discount.getDescription());
				discountGrand=discountGrand+billDisc.getDiscountAmount();
				billDisc.setActionName(billDiscountDTO.getActionName());
				billDiscountList.add(billDisc);
			}
			
			if(discount.getDiscType().equals(DiscountTypeEnum.OnDemand.getDisplayName())){

				BillDiscountDTO billDisc=getDiscount(grandTotal,discount.getCalculationType(),billDiscountDTO.getDiscountValue(),discount.getDiscType(),billDiscountDTO.getDiscountId(),discount.getName(),discount.getDescription());
				discountGrand=discountGrand+billDisc.getDiscountAmount();
				billDisc.setActionName(billDiscountDTO.getActionName());
				billDiscountList.add(billDisc);
			}

		}
		billAmount=grandTotal-discountGrand;
		if(billAmount>grandTotal){
			ServiceException se =new ServiceException(ErrorCodeEnum.DiscountGreaterThanAmount);			
			se.setDisplayErrMsg(true);			
			throw se;
		}
		response.setBillAmount(billAmount);
		response.setBillDiscountList(billDiscountList);
		return response;
	}

	/**
	 * 
	 * get the discount calculation
	 * 
	 * @param amount
	 * @param calculationType
	 * @param discountValue
	 * @param discountType
	 * @param id
	 * @param name
	 * @param description
	 * @return BillDiscountDTO
	 * @throws ServiceException 
	 */
	public static BillDiscountDTO getDiscount(float amount,String calculationType,float discountValue,String discountType,int id,String name,String description) throws ServiceException{
		BillDiscountDTO discount=new BillDiscountDTO();

		float netRate = 0;
		float discountAmount= 0;
		if(discountValue<=0){
			netRate = amount;
		}else if(calculationType.equals(CalculationTypeEnum.Fixed.getDisplayName())){
			netRate = amount-discountValue;
			discountAmount= discountValue;
		}else {
			netRate =  amount-(amount*discountValue/100);
			discountAmount =(amount*discountValue/100);
		}
		if(netRate<0){
			if(calculationType.equals(CalculationTypeEnum.Fixed.getDisplayName())){
				ServiceException se =new ServiceException(ErrorCodeEnum.DiscountGreaterThanAmount);			
				se.setDisplayErrMsg(true);			
				throw se;
			}
			else{
				ServiceException se =new ServiceException(ErrorCodeEnum.InvalidDiscountPercentage);			
				se.setDisplayErrMsg(true);			
				throw se;
			}

		}		
		discount.setDiscountId(id);
		discount.setName(name);
		discount.setDescription(description);
		discount.setCalculationType(calculationType);
		discount.setDiscountType(discountType);
		discount.setDiscountAmount(discountAmount);
		discount.setDiscountValue(discountValue);

		return discount;
	}

	/**
	 * @return the discountFilterService
	 */
	public FilterService getDiscountFilterService() {
		return discountFilterService;
	}

	/**
	 * @param discountFilterService the discountFilterService to set
	 */
	public void setDiscountFilterService(FilterService discountFilterService) {
		this.discountFilterService = discountFilterService;
	}

	/**apply discount on  grand total while update
	 * @param billDiscount
	 * @param grandTotal
	 *@return  BillDiscountDetailsDTO
	 * @throws ServiceException 
	 */
	@Override
	public BillDiscountDetailsDTO applyBillDiscount(
			List<BillDiscountDTO> billDiscount, float grandTotal) throws ServiceException {
		float billAmount=0;
		float discountGrand=0;
		BillDiscountDetailsDTO response=new BillDiscountDetailsDTO();
		List<BillDiscountDTO> billDiscountList=new  ArrayList<BillDiscountDTO>();
		for (BillDiscountDTO billDiscountDTO : billDiscount) {	

			BillDiscountDTO billDisc=getDiscount(grandTotal,billDiscountDTO.getCalculationType(),billDiscountDTO.getDiscountValue(),DiscountTypeEnum.OnDemand.getDisplayName(),billDiscountDTO.getDiscountId(),billDiscountDTO.getName(),billDiscountDTO.getDescription());
			discountGrand=discountGrand+billDisc.getDiscountAmount();			
			billDiscountList.add(billDisc);

		}
		billAmount=grandTotal-discountGrand;
		if(billAmount>grandTotal){
			ServiceException se =new ServiceException(ErrorCodeEnum.DiscountGreaterThanAmount);			
			se.setDisplayErrMsg(true);			
			throw se;
		}
		response.setBillAmount(billAmount);
		response.setBillDiscountList(billDiscountList);
		return response;

	}

	/**
	 * for getting discount value
	 * @param discount
	 * @return DiscountValueResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public DiscountValueResponseDTO getDiscountValue(DiscountValueDTO discount) throws ServiceException {
		DiscountValueResponseDTO response=new DiscountValueResponseDTO();
		BillDiscountDTO billDisc=new BillDiscountDTO();
		float netAmount=0;

		discountValidator.validateDiscount(discount.getId(),discount.getAmount());
		DiscountDTO response1;
		try {
			response1 = discountDao.getDiscountById(discount.getId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(response1!=null){
			if(response1.getDiscType().equals(DiscountTypeEnum.Predefined.getDisplayName())){
				billDisc = getDiscount(discount.getAmount(),response1.getCalculationType(),response1.getDiscValue(),response1.getDiscType(),response1.getId(),response1.getName(),response1.getDescription());
			}
			if(response1.getDiscType().equals(DiscountTypeEnum.OnDemand.getDisplayName())){
				billDisc= getDiscount(discount.getAmount(),response1.getCalculationType(),discount.getDiscValue(),response1.getDiscType(),response1.getId(),response1.getName(),response1.getDescription());
			}
		}
		netAmount= discount.getAmount()-billDisc.getDiscountAmount();
		response.setDiscountValue(billDisc.getDiscountAmount());
		response.setNetRate(netAmount);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
}
