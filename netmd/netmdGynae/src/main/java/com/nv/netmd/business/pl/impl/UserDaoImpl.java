/**
 * UserDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 1, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.UserDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.LoginTbl;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.UserDTO;
import com.nv.platform.base.util.StringEncoder;


/**
 * 
 */
public class UserDaoImpl  extends GenericDaoHibernateImpl implements UserDao {
	@PersistenceContext()
	private EntityManager em;

	/**
	 * creates an user
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createUser(UserDTO user)throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		//		DateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		/*Checking whether the netmd id and passphrase exists or not*/
		//		NetmdPassphraseTbl netmdPassphrase=(NetmdPassphraseTbl)getNetmdByPassphrase(user.getHeader().getNetMdId(),user.getHeader().getPassPhrase());
		//		if(netmdPassphrase==null){
		//			ServiceException se =new ServiceException(ErrorCodeEnum.NetMdNull);
		//			se.addParam(new Parameter(Constants.ID,Integer.toString(user.getHeader().getNetMdId())));
		//			se.setDisplayErrMsg(true);
		//			throw se;
		//		}


		/*Saving username and password in login tbl*/
		//StringEncoder strencoder = new StringEncoder();
		String password = (new StringEncoder()).encryptWithKey(user.getPassword());
		LoginTbl existingUser=(LoginTbl) getUser(password,user.getUserName().trim());
		if(existingUser!=null){

			PersistenceException se =new PersistenceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}	
		LoginTbl loginTbl=new LoginTbl();
		loginTbl.setUserName(user.getUserName());
		loginTbl.setPassword(password);
		UserTypeEnum userType=UserTypeEnum.getEnum(user.getUserType());
		loginTbl.setUserType(userType);
		save(loginTbl);

		response.setSuccess(true);
		//response.setGlobalId(newUser.getId());//global id
		//response.setId(user.getId());//local id
		//response.setCreateDateTime(newUser.getCreateDateTime());
		return response;

	}


	public LoginTbl getUser( String password,String userName) throws PersistenceException
	{			
		javax.persistence.Query query=em.createQuery(Query.GET_USER_BY_PASSWORD);
		query.setParameter("param1",password);
		query.setParameter("param2",userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}



	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}
	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}


	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.UserDao#updateUser(com.nv.netmd.rs.dto.UserDTO)
	 */
	@Override
	public ResponseDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.UserDao#deleteUser(com.nv.netmd.rs.dto.UserDTO)
	 */
	@Override
	public ResponseDTO deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

}
