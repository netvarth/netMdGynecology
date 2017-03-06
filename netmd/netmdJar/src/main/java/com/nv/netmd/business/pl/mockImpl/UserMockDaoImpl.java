/**
* UserMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 26, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import com.nv.netmd.business.pl.dao.UserDao;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.UserDTO;

/**
 * 
 */
public class UserMockDaoImpl implements UserDao {

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.UserDao#createUser(com.nv.netmd.rs.dto.UserDTO)
	 */
	@Override
	public ResponseDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.UserDao#updateUser(com.nv.netmd.rs.dto.UserDTO)
	 */
	@Override
	public ResponseDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.UserDao#deleteUser(com.nv.netmd.rs.dto.UserDTO)
	 */
	@Override
	public ResponseDTO deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

}
