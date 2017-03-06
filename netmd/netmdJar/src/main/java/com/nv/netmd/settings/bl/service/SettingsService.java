/**
 * SettingsService.java
 * @author Mani E.V 
 *
 * Version 1.0 06-Nov-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.service;


import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ListResponse;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.SettingListResponseDTO;
import com.nv.netmd.rs.dto.ViewSettingResponseDTO;


/**
 *
 *
 * @author Mani E.V
 */
public interface SettingsService {
	/**
	 * @param setting
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO create(SettingDTO setting) throws ServiceException;
	/**
	 * @param name
	 * @return ViewSettingResponseDTO
	 * @throws ServiceException 
	 */
	public ViewSettingResponseDTO getByName(String name) throws ServiceException;
	/**
	 * @param setting
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO update(SettingDTO setting) throws ServiceException;
	/**
	 * @param filterDTO
	 * @return SettingListResponseDTO
	 * @throws ServiceException 
	 */
	public SettingListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	/**
	 * @param settingUid
	 * @return ViewSettingResponseDTO
	 * @throws ServiceException 
	 */
	public ViewSettingResponseDTO view(int settingUid) throws ServiceException;
	/**
	 * @param uid
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	public ResponseDTO delete(int uid) throws ServiceException;
}
