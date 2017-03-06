 /**
* AdvanceValidator.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 22, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.billing.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AdvanceDTO;


public class AdvanceValidator {

	/**
	 * validate create advance
	 * @param advance
	 * @throws ServiceException 
	 */
		public void validateCreateAdvance(AdvanceDTO advance) throws ServiceException{
			if(advance.getPatientId()<=0){
				ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
				se.isDisplayErrMsg();
				throw se;
			}
			if(advance.getAmount()<=0){
				ServiceException se=new ServiceException(ErrorCodeEnum.InvalidAmount);
				se.isDisplayErrMsg();
				throw se;
			  }
			    	
		  }
		/**
		 * validate update advance
		 * @param advance
		 * @throws ServiceException 
		 */
			public void validateUpdateAdvance(AdvanceDTO advance) throws ServiceException{
				if(advance.getPatientId()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.PatientIdNull);
					se.isDisplayErrMsg();
					throw se;
				}
				if(advance.getAmount()<=0){
					ServiceException se=new ServiceException(ErrorCodeEnum.InvalidAmount);
					se.isDisplayErrMsg();
					throw se;
				  }
				    	
			  }
		}