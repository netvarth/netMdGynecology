/**
 * TaxServiceImpl.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.impl;

import java.util.List;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BillBedDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillSupportDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.rs.dto.TaxViewResponseDTO;
import com.nv.netmd.settings.bl.service.TaxService;
import com.nv.netmd.settings.bl.validator.TaxValidator;
import com.nv.netmd.settings.pl.dao.TaxDao;

/**
 *
 *
 * @author Leonora Louis
 */
public class TaxServiceImpl implements TaxService  {
    private TaxValidator taxValidator;
    private FilterService taxFilterService;
    private TaxDao taxDao;
 	
	/** 
	 * create tax 
	 * @param taxdto
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO create(TaxDTO taxdto) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=new ErrorDTO();
	  		 error=taxValidator.validateCreate(taxdto);
	  	if(error!=null){
	  		response.setError(error);
	  		response.setSuccess(false);
	  		return response;
	  		
	  	}
	  	try {
			response=taxDao.create(taxdto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	public TaxValidator getTaxValidator() {
		return taxValidator;
	}

	public void setTaxValidator(TaxValidator taxValidator) {
		this.taxValidator = taxValidator;
	}

	public TaxDao getTaxDao() {
		return taxDao;
	}

	public void setTaxDao(TaxDao taxDao) {
		this.taxDao = taxDao;
	}

	/** 
	 * View tax
	 * @param id
	 * @return TaxViewResponseDTO
	 */
	@Override
	public TaxViewResponseDTO view(int id) throws ServiceException {
	TaxViewResponseDTO taxdto;
	try {
		taxdto = taxDao.view(id);
	} catch (PersistenceException e) {
		throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
	}
		
		// TODO Auto-generated method stub
		return taxdto;
	}
	/** 
	 *update tax
	 *@param taxdto
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(TaxDTO taxdto) throws ServiceException {
		ResponseDTO response;
		try {
			response = taxDao.update(taxdto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// TODO Auto-generated method stub
		return 	response;
	}
	/** 
	 * Delete tax
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response;
		try {
			response = taxDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// TODO Auto-generated method stub
		return 	response;
	}

	/**
	 * get list of tax
	 * @param  filterdto
	 * @return TaxListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public TaxListResponseDTO getList(FilterDTO filterdto) throws ServiceException {
		// TODO Auto-generated method stub
		TaxListResponseDTO response=new TaxListResponseDTO();
		ErrorDTO error=taxFilterService.validate(filterdto);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
			
		}
		response=taxFilterService.list(filterdto);		
		return response;
	}

	public FilterService getTaxFilterService() {
		return taxFilterService;
	}

	public void setTaxFilterService(FilterService taxFilterService) {
		this.taxFilterService = taxFilterService;
	}

	/**
	 * Get all the taxes
	 * @return TaxListResponseDTO
	 * @throws ServiceException 
	 *
	 */
	@Override
	public TaxListResponseDTO getTaxes() throws ServiceException {
		TaxListResponseDTO response;
		try {
			response = taxDao.getTaxes();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 *calculate item tax
	 *@param billItem
	 *@return float
	 * @throws ServiceException 
	 */
	@Override
	public float calItemTax(List<BillItemDTO> billItem) throws ServiceException {
		float netAmount=0;
		float taxAmount = 0;
		for (BillItemDTO billItemDTO : billItem) {
			try {
				taxAmount=taxDao.getItemTaxAmount(billItemDTO.getItemId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			netAmount=netAmount+(billItemDTO.getQuantity()*taxAmount);
		}
		return netAmount;
	}

	/** 
	 * calculate bed tax
	 * @param billBed
	 * @return float
	 * @throws ServiceException 
	 */
	@Override
	public float calBedTax(List<BillBedDTO> billBed) throws ServiceException {
		float netAmount=0;
		float taxAmount = 0;
		for (BillBedDTO billBedDTO : billBed) {
			try {
				taxAmount=taxDao.getBedTaxAmount(billBedDTO.getBedId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			netAmount=netAmount+(billBedDTO.getQuantity()*taxAmount);
		}
		return netAmount;
	}
	/** 
	 * calculate support tax
	 * @param billSupport
	 * @return float
	 * @throws ServiceException 
	 */
	@Override
	public float calSupportTax(List<BillSupportDTO> billSupport) throws ServiceException {
		float netAmount=0;
		float taxAmount = 0;
		for (BillSupportDTO billSupportDTO : billSupport) {
			try {
				taxAmount=taxDao.getSupportTaxAmount(billSupportDTO.getSupportId());
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
			netAmount=netAmount+(billSupportDTO.getQuantity()*taxAmount);
		}
		return netAmount;
	}

}
