/**
 * SettingsDaoImpl.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 09-Dec-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.ViewSettingResponseDTO;
/**
 *
 *
 * @author Nithesh Mohanan
 */
public interface SettingsDao {
	/**
	 * @param name
	 * @return ViewSettingResponseDTO
	 */
	public ViewSettingResponseDTO getByName(String name) throws PersistenceException;

	/**
	 * @param setting
	 * @return ResponseDTO
	 */
	public ResponseDTO update(SettingDTO setting) throws PersistenceException;

	/**
	 * @param setting
	 * @return ResponseDTO
	 */
	ResponseDTO create(SettingDTO setting) throws PersistenceException;

	/**
	 * @param settingUid
	 * @return ViewSettingResponseDTO
	 */
	ViewSettingResponseDTO view(int settingUid) throws PersistenceException;

	/**
	 * @param uid
	 * @return ResponseDTO
	 */
	ResponseDTO delete(int uid) throws PersistenceException;
}
