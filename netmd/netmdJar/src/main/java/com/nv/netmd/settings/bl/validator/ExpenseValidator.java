 /**
* ExpenseValidator.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 11, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ItemDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public class ExpenseValidator {
	/**
	 * validate create Expense
	 * @param Expense
	 * @throws ServiceException 
	 */
	public static void validateCreateExpense(ExpenseDTO Expense) throws ServiceException {	
			if (!isValidExpenseName(Expense.getExpenseName())) {
			ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidExpenseName);
				se.setDisplayErrMsg(true);
				throw se;
	
			}
		}


	private static boolean isValidExpenseName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	
	private boolean isValidType(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	


	/**
	 * validate update expense
	 * @param expense
	 * @throws ServiceException 
	 */
	public void validateUpdateExpense(ExpenseDTO expense) throws ServiceException {		
		if(expense.getId()<=0){
			ServiceException se=new  ServiceException(ErrorCodeEnum.ExpenseIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidExpenseName(expense.getExpenseName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if(!isValidType(expense.getNote())){
			ServiceException se=new ServiceException(ErrorCodeEnum.NoNote);
			se.isDisplayErrMsg();
			throw se;
		}

	}
	
	}

