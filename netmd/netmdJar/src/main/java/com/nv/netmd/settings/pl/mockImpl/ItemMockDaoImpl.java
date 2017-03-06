/**
 * ItemMockDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.mockImpl;

import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.ItemDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemMockDaoImpl implements ItemDao{

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.pl.dao.ItemDao#create(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	public ResponseDTO create(ItemDTO item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.pl.dao.ItemDao#update(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	public ResponseDTO update(ItemDTO item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.pl.dao.ItemDao#view(int)
	 */
	@Override
	public ItemDTO view(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.pl.dao.ItemDao#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.settings.pl.dao.ItemDao#getItems()
	 */
	@Override
	public ItemListResponseDTO getItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
