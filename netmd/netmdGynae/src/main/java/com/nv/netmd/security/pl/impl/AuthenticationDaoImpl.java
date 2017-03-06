/**
 * AuthenticationDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 6, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */

package com.nv.netmd.security.pl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.DoctorTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.LoginTbl;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.pl.entity.NetmdPassphraseTbl;
import com.nv.netmd.pl.entity.NetmdTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.ErrorCodeListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.User;
import com.nv.netmd.rs.dto.UserDetails;
import com.nv.netmd.rs.dto.UserPermission;
import com.nv.netmd.security.pl.dao.AuthenticationDao;
import com.nv.netmd.rs.dto.Error;






public class AuthenticationDaoImpl extends GenericDaoHibernateImpl implements AuthenticationDao{
	@PersistenceContext()
	private EntityManager em;

	/**
	 * Check the user authenticated one and he can login to the weblims
	 * @param loginDto
	 * @return LoginResponseDTO
	 */
	/**
	 * Check the user is authenticated one and he can login to the weblims
	 * @param loginDto
	 * @return LoginResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=true)
	public LoginResponseDTO login(LoginDTO loginDTO) throws PersistenceException{
		LoginResponseDTO login=new LoginResponseDTO();
		LoginTbl loginDetails =  getUserByLoginIdAndPassword(loginDTO.getUserName(),loginDTO.getPassword());
		if(loginDetails!=null){
			login.setSuccess(true);
		}else{
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(ErrorCodeEnum.UserNull.getErrCode());
			error.setDisplayErrMsg(true);
			login.setError(error);
			login.setSuccess(false);
			return login;
		}
		return login;
	}




	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	private LoginTbl getUserByLoginIdAndPassword(String userName,
			String password) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_LOGIN);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}




	/* (non-Javadoc)
	 * @see com.nv.netmd.security.pl.dao.AuthenticationDao#getUser(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserDetails getUser(String loginId) throws PersistenceException{
		UserDetails userDetail = new UserDetails();
		LoginTbl loginTbl=getUserByUserName(loginId);
		if(loginTbl==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		userDetail.setId(loginTbl.getId());
		userDetail.setUserName(loginTbl.getUserName());		
		userDetail.setUserType(loginTbl.getUserType().getDisplayName());
		if((loginTbl.getUserType().equals(UserTypeEnum.Doctor)||loginTbl.getUserType().equals(UserTypeEnum.Admin))){
			DoctorTbl doctorTbl=getDoctorByLoginId(loginTbl.getId());
			if(doctorTbl==null){
				PersistenceException se =new PersistenceException(ErrorCodeEnum.DoctorNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(loginTbl.getId())));
				se.setDisplayErrMsg(true);
				throw se;	
			}
			userDetail.setDoctorId(doctorTbl.getId());
		}
		else if(loginTbl.getUserType().equals(UserTypeEnum.Owner)){
			 NetmdTbl netmdTbl=getOwnerByLoginId(loginTbl.getId());
				if(netmdTbl==null){
					PersistenceException se =new PersistenceException(ErrorCodeEnum.OwnerNotFound);
					se.addParam(new Parameter(Constants.ID,Integer.toString(loginTbl.getId())));
					se.setDisplayErrMsg(true);
					throw se;	
			  }
				userDetail.setOwnerId(netmdTbl.getId());
			
		}
		NetmdPassphraseTbl netmdPassTbl=(NetmdPassphraseTbl)getPassPhrseTbl();
		if(netmdPassTbl!=null){
			userDetail.setPrimaryDevice(netmdPassTbl.getPrimaryKey());
		}
		return userDetail;
	}
	/**
	 * @param id
	 * @return
	 * @throws PersistenceException 
	 */
	private NetmdTbl getOwnerByLoginId(int loginId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_OWNER_BY_LOGINID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdTbl.class, query);
	}




	private NetmdPassphraseTbl getPassPhrseTbl() throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_PASSPHRASE);	
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}
	private LoginTbl getUserByUserName(String loginId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_USER);
		query.setParameter("param1",loginId);
		//query.setParameter("param2",StatusEnum.Active);
		return executeUniqueQuery(LoginTbl.class, query);
	}
	private DoctorTbl getDoctorByLoginId(int loginId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_DOCTOR_BY_LOGINID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}
	/**
	 * get the notification
	 * @return MessageListResponseDTO
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public MessageListResponseDTO  getMessage(User user) throws PersistenceException {
		// TODO Auto-generated method stub
		MessageListResponseDTO response=new MessageListResponseDTO();
		List<MessageDTO> messageListDTO=new ArrayList<MessageDTO>();
		LoginTbl loginTbl=(LoginTbl)getById(LoginTbl.class,user.getId());
		if(loginTbl!=null){
			if(loginTbl.getUserType()==UserTypeEnum.Doctor){
				List<MessageTbl> messageList=(ArrayList<MessageTbl>)getMessageOfDoctorList(user.getDoctorId());
				for (MessageTbl messageTbl : messageList) {
					MessageDTO message=new MessageDTO();
					message.setId(messageTbl.getId());
					message.setMessage(messageTbl.getMessage());
					message.setMessageViewed(messageTbl.getViewed());
					if(messageTbl.getDoctorTbl()!=null)
						message.setDoctorId(messageTbl.getDoctorTbl().getId());
					messageListDTO.add(message);
				}
				response.setMessageDTO(messageListDTO);
				response.setCount((long) messageList.size());
				response.setSuccess(true);
				return response;
			}

			List<MessageTbl> messageList=(ArrayList<MessageTbl>)getMessageList();
			for (MessageTbl messageTbl : messageList) {
				MessageDTO message=new MessageDTO();
				message.setId(messageTbl.getId());
				message.setMessage(messageTbl.getMessage());
				message.setMessageViewed(messageTbl.getViewed());
				if(messageTbl.getDoctorTbl()!=null)
					message.setDoctorId(messageTbl.getDoctorTbl().getId());
				messageListDTO.add(message);
			}
			response.setMessageDTO(messageListDTO);
			response.setCount((long) messageList.size());
			response.setSuccess(true);
		}

		return response;

	}
	public List<MessageTbl> getMessageList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_MESSAGE);

		return executeQuery(MessageTbl.class, query);
	}
	public List<MessageTbl> getMessageOfDoctorList(int doctorId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_MESSAGE_OF_DOCTOR);
		query.setParameter("param1", doctorId);
		return executeQuery(MessageTbl.class, query);
	}


	/**
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO errorCodeList = new ErrorCodeListResponseDTO();
		List<Error> errorList = new ArrayList<Error>();
		for (ErrorCodeEnum enumval : ErrorCodeEnum.values()) {
			Error error = new Error();
			error.setErrCode(enumval.getErrCode());
			error.setErrMsg(enumval.getErrMsg());
			errorList.add(error);
		}
		errorCodeList.setError(errorList);
		return errorCodeList;
	}




	/* (non-Javadoc)
	 * @see com.nv.netmd.security.pl.dao.AuthenticationDao#getUserPermissions(java.lang.Integer)
	 */
	@Override
	public List<UserPermission> getUserPermissions(Integer roleId) {
//		javax.persistence.Query query=em.createQuery(Query.GET_PERMISSION_BY_ROLE_ID);
//		query.setParameter("param1", roleId);
//		List<RoleGroupPermTbl> permissions =query.getResultList();
//		List<UserPermission> userPermissions = new ArrayList<UserPermission>();
//		for(RoleGroupPermTbl perm :permissions){
//			UserPermission userPermission = new UserPermission(perm.getPermGroup(),perm.getRoleBitMask());   		
//			userPermissions.add(userPermission);	
//		}
		return null; 
	}
}
