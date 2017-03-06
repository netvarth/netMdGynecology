/**
 * DiscountValidator.java
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.DiscountDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class DiscountValidator {
	/**
	 * @param discount
	 * @throws ServiceException 
	 */
	public void validateCreateDiscount(DiscountDTO discount) throws ServiceException {

		if (!isValidType(discount.getName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		CalculationTypeEnum calculation=CalculationTypeEnum.getEnum(discount.getCalculationType());
		DiscountTypeEnum discountType=DiscountTypeEnum.getEnum(discount.getDiscType());

	}
	/**
	 * @param discount
	 * @throws ServiceException 
	 */
	public void validateUpdateDiscount(DiscountDTO discount) throws ServiceException {
		if(discount.getId()<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.DiscountIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidType(discount.getName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		CalculationTypeEnum calculation=CalculationTypeEnum.getEnum(discount.getCalculationType());
		DiscountTypeEnum discountType=DiscountTypeEnum.getEnum(discount.getDiscType());

	}
	private boolean isValidType(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	
	public void validateDiscount(int id,float amount) throws ServiceException{
		

		if(id<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidDiscount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(amount<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidAmountForNetRateCalculation);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		
	}
}
