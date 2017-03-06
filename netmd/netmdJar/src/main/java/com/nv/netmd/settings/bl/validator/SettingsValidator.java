/**
 * SettingsValidator.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 09-Dec-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.validator;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.SettingDTO;
import com.nv.netmd.rs.dto.SettingListDTO;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class SettingsValidator {
	/**
	 * @param setting 
	 * @throws ServiceException 
	 * 
	 */
	public void validateActionName(SettingDTO setting) throws ServiceException {
		for(SettingListDTO settingList:setting.getSettingList()) {			
			int i=0;
			for (ActionNameEnum actionName : ActionNameEnum.values()) {
				if( actionName.getDisplayName().equals(settingList.getActionName()) )
					i++;
			}
			if(i==0) 
				throw new ServiceException(ErrorCodeEnum.InvalidAction);
		}
	}
	/**
	 * @param setting
	 * @throws ServiceException 
	 */
	public void validateSetting(SettingDTO setting) throws ServiceException {
		if(setting.getName().equals("")||setting.getName()==null||setting.getName().matches("//d*"))
			throw new ServiceException(ErrorCodeEnum.InvalidSettingName);
	}

	/**
	 * @param uid
	 * @throws ServiceException 
	 */
	public void validateUid(int uid) throws ServiceException {
		if(uid<=0)
			throw new ServiceException(ErrorCodeEnum.InvalidSettingUid);
	}
}
