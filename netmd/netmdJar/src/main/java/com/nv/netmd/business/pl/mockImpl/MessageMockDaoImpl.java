/**
* MessageMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 27, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.pl.dao.MessageDao;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;

/**
 * 
 */
public class MessageMockDaoImpl implements FilterService,MessageDao{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#list(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public MessageListResponseDTO list(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		MessageListResponseDTO msgList=new MessageListResponseDTO();
		return msgList;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.FilterService#validate(com.nv.netmd.rs.dto.FilterDTO)
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterdto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.MessageDao#viewMessage(int)
	 */
	@Override
	public MessageDTO viewMessage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
