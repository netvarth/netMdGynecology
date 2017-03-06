/**
 * ItemServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.ItemService;
import com.nv.netmd.settings.bl.validator.ItemValidator;
import com.nv.netmd.settings.pl.dao.ItemDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemServiceImpl implements ItemService{
	private ItemValidator itemValidator;	
	private FilterService itemFilterService;
	private ItemDao itemDao;

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#create(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	public ResponseDTO create(ItemDTO item) throws ServiceException {
		itemValidator.validateCreateItem(item);
		ResponseDTO response;
		try {
			response = itemDao.create(item);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#update(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	public ResponseDTO update(ItemDTO item) throws ServiceException {
		itemValidator.validateUpdateItem(item);
		ResponseDTO response;
		try {
			response = itemDao.update(item);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#view(int)
	 */
	@Override
	public ItemDTO view(int id) throws ServiceException {		
		ItemDTO response;
		try {
			response = itemDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}		
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response;
		try {
			response = itemDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ItemListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		ItemListResponseDTO itemList = new ItemListResponseDTO();
		ErrorDTO error=itemFilterService.validate(filterDTO);
		if (error != null) {
			itemList.setError(error);
			itemList.setSuccess(false);
			return itemList;
		}
		itemList =itemFilterService.list(filterDTO);
		return itemList;

	}

	/**
	 * @return the itemValidator
	 */
	public ItemValidator getItemValidator() {
		return itemValidator;
	}

	/**
	 * @param itemValidator the itemValidator to set
	 */
	public void setItemValidator(ItemValidator itemValidator) {
		this.itemValidator = itemValidator;
	}

	/**
	 * @return the itemFilterService
	 */
	public FilterService getItemFilterService() {
		return itemFilterService;
	}

	/**
	 * @param itemFilterService the itemFilterService to set
	 */
	public void setItemFilterService(FilterService itemFilterService) {
		this.itemFilterService = itemFilterService;
	}

	/**
	 * @return the itemDao
	 */
	public ItemDao getItemDao() {
		return itemDao;
	}

	/**
	 * @param itemDao the itemDao to set
	 */
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.bl.service.ItemService#getItems()
	 */
	@Override
	public ItemListResponseDTO getItems() throws ServiceException {
		ItemListResponseDTO response;
		try {
			response = itemDao.getItems();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

}
