/**
 * SettingsManager.java
 * @author  Nithesh Mohanan
 *
 * Version 1.0 09-Dec-2013
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
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ListResponse;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.SettingListResponseDTO;
import com.nv.netmd.rs.dto.ViewSettingResponseDTO;
import com.nv.netmd.settings.bl.service.SettingsService;
import com.nv.netmd.settings.bl.validator.SettingsValidator;
import com.nv.netmd.settings.pl.dao.SettingsDao;
/**
 *
 *
 * @author Nithesh Mohanan
 */
public class SettingsServiceImpl implements SettingsService{
	private SettingsDao settingsDao;
	private SettingsValidator settingsValidator;
	private FilterService settingsFilterService;
	
	@Override
	public ResponseDTO create(SettingDTO setting) throws ServiceException {
		settingsValidator.validateSetting(setting);
		ResponseDTO response;
		try {
			response = settingsDao.create(setting);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	@Override
	public ViewSettingResponseDTO getByName(String name) throws ServiceException {
		ViewSettingResponseDTO response;
		try {
			response = settingsDao.getByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	@Override
	public ResponseDTO update(SettingDTO setting) throws ServiceException {
		settingsValidator.validateSetting(setting);
		ResponseDTO response;
		try {
			response = settingsDao.update(setting);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	@Override
	public SettingListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		SettingListResponseDTO settingsList = new SettingListResponseDTO();
		ErrorDTO error=settingsFilterService.validate(filterDTO);
		if (error != null) {
			settingsList.setError(error);
			settingsList.setSuccess(false);
			return settingsList;
		}
		settingsList =settingsFilterService.list(filterDTO);
		return settingsList;
	}
	@Override
	public ViewSettingResponseDTO view(int settingUid) throws ServiceException {
		ViewSettingResponseDTO response;
		try {
			response = settingsDao.view(settingUid);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}		
		return response;
	}
	@Override
	public ResponseDTO delete(int uid) throws ServiceException {
		ResponseDTO response;
		try {
			response = settingsDao.delete(uid);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**
	 * @return the settingsDao
	 */
	public SettingsDao getSettingsDao() {
		return settingsDao;
	}

	/**
	 * @param settingsDao the settingsDao to set
	 */
	public void setSettingsDao(SettingsDao settingsDao) {
		this.settingsDao = settingsDao;
	}

	/**
	 * @return the settingsValidator
	 */
	public SettingsValidator getSettingsValidator() {
		return settingsValidator;
	}

	/**
	 * @param settingsValidator the settingsValidator to set
	 */
	public void setSettingsValidator(SettingsValidator settingsValidator) {
		this.settingsValidator = settingsValidator;
	}

	
	/**
	 * @return the settingsFilterService
	 */
	public FilterService getSettingsFilterService() {
		return settingsFilterService;
	}

	/**
	 * @param settingsFilterService the settingsFilterService to set
	 */
	public void setSettingsFilterService(FilterService settingsFilterService) {
		this.settingsFilterService = settingsFilterService;
	}
	
	
}
