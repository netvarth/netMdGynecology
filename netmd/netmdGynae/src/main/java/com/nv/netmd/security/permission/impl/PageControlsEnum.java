/**
* PageControls.java
* 
* @Author Sreeram
*
* Version 1.0 Dec 4, 2012
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.security.permission.impl;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;



/**
 * 
 */
public enum PageControlsEnum {
	
	globalToolBar("webapps/netmd/web/json/toolbars/globalToolbar.json"),
	leftpaneToolBar("webapps/netmd/web/json/toolbars/leftpaneToolbar.json"),
	;


	/* (non-Javadoc)
	 * @see com.nv.weblims.billing.bl.impl.EnumDisplay#getDisplayName()
	 */
	
	private String url;


	/**
	 * @param name
	 * @param url
	 */
	private PageControlsEnum( String url) {
		this.url = url;
	}


	public static PageControlsEnum getEnum(String url) throws ServiceException {
		if(url == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidPageKey);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(PageControlsEnum v : values()){        	
			if(url.equalsIgnoreCase(v.name())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidPageKey);
		se.setDisplayErrMsg(true);
		throw se;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


}
