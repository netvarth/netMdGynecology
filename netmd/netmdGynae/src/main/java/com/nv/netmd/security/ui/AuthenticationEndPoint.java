/**
 * AuthenticationEndPoint.java
 * 
 * @Author Linto
 *
 * Version 1.0 Jul 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.security.ui;



import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.rs.dto.ButtonList;
import com.nv.netmd.rs.dto.EnumListResponseDTO;
import com.nv.netmd.rs.dto.ErrorCodeListResponseDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.LoginResponseDTO;
import com.nv.netmd.rs.dto.PermissionService;
import com.nv.netmd.rs.dto.User;
import com.nv.netmd.rs.dto.UserDetails;
import com.nv.netmd.security.bl.service.AuthenticationService;
import com.nv.netmd.sync.bl.service.SyncService;






@Controller
@RequestMapping("ui/auth/")
public class AuthenticationEndPoint {
	private String serverPath;
	private AuthenticationService authenticationService;
	private SyncService syncService;
	private PermissionService permissionService;

	
	/**
	 * For user login in to netmd
	 * @param loginDto
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO login(@RequestBody LoginDTO loginDto) throws ServiceException{

		LoginResponseDTO response=new LoginResponseDTO();
		try {
			response=authenticationService.login(loginDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(response.isSuccess()){
			ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest req = t.getRequest();
			User user=new User();
			UserDetails userDetail =authenticationService.getUser(loginDto.getUserName());
			if(userDetail!=null){
				user.setLoginTime(new Date());
				user.setName(userDetail.getUserName());
				user.setUserName(loginDto.getUserName().trim());
				user.setUserType(userDetail.getUserType());
				user.setId(userDetail.getId());
				user.setPrimaryDevice(userDetail.isPrimaryDevice());
				if(userDetail.getUserType().equals(UserTypeEnum.Doctor.getDisplayName())||(userDetail.getUserType().equals(UserTypeEnum.Admin.getDisplayName()))){
					user.setDoctorId(userDetail.getDoctorId());
				}
				else if(userDetail.getUserType().equals(UserTypeEnum.Owner.getDisplayName())){
					user.setOwnerId(userDetail.getOwnerId());
				}
			}
			req.getSession().setAttribute(Constants.USER, user);
		}
		return response;
	}


	/***
	 * For user logout from netmd
	 * 
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseDTO logout()
	{
		LoginResponseDTO response=new LoginResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		req.getSession().setAttribute(Constants.USER,null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}

	//	/**
	//	 * view messages
	//	 *
	//	 * @return MessageListResponseDTO
	//	 */
	//	@RequestMapping(value = "viewMessage/{id}", method = RequestMethod.GET)
	//	@ResponseBody
	//	public MessageDTO viewMessage(@PathVariable int id)
	//	{
	//		MessageDTO response=new MessageDTO();
	//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	//		HttpServletRequest req = t.getRequest();
	//		User user=(User) req.getSession().getAttribute(Constants.USER);
	//		response=authenticationService.viewMessage(id);
	//		return response;
	//	}
	//	/**
	//	 *  get new messages
	//	 * @return MessageListResponseDTO
	//	 */
	//	@RequestMapping(value = "getMessage", method = RequestMethod.GET)
	//	@ResponseBody
	//	public MessageListResponseDTO getMessage()
	//	{
	//		MessageListResponseDTO response=new MessageListResponseDTO();
	//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	//		HttpServletRequest req = t.getRequest();
	//		User user=(User) req.getSession().getAttribute(Constants.USER);
	//		response=authenticationService.getMessage(user);
	//		return response;
	//	}
	/**
	 * doctor login
	 * @param loginDto
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "doctorLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO doctorLogin(@RequestBody LoginDTO loginDto) throws ServiceException{

		LoginResponseDTO response=new LoginResponseDTO();
		try {
			response=authenticationService.login(loginDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(response.isSuccess()){
			ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest req = t.getRequest();
			User user=new User();
			UserDetails userDetail =authenticationService.getUser(loginDto.getUserName());
			if(userDetail!=null){
				user.setLoginTime(new Date());
				user.setUserName(loginDto.getUserName().trim());
				user.setId(userDetail.getId());
				user.setName(userDetail.getUserType());
				user.setUserType(userDetail.getUserType());
				if(userDetail.getUserType().equals(UserTypeEnum.Doctor.getDisplayName())||userDetail.getUserType().equals(UserTypeEnum.Admin.getDisplayName())){
					user.setDoctorId(userDetail.getDoctorId());
				}
				
			}
			req.getSession().setAttribute(Constants.USER, user);
		}
		return response;
	}

	@RequestMapping(value="lForm",method=RequestMethod.GET)
	public String loginForm() throws  ServiceException{
		if(syncService.isLoginEmpty()==false){
			return "authentication";
		}
		return "login";
	}
	/**
	 * Get current user in the session
	 * 
	 * @return String
	 */
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {

		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		return user;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


	/**
	 * @return the syncService
	 */
	public SyncService getSyncService() {
		return syncService;
	}


	/**
	 * @param syncService the syncService to set
	 */
	public void setSyncService(SyncService syncService) {
		this.syncService = syncService;
	}


	
	/**
	 * Retrieves list of Enumerators
	 * 
	 * @return EnumListResponseDTO
	 */
	@RequestMapping(value = "getEnumsList", method = RequestMethod.GET)
	@ResponseBody
	public EnumListResponseDTO getEnumsList() {

		EnumListResponseDTO response = authenticationService.getEnumsList();
		return response;
	}

	/**
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	@RequestMapping(value = "getErrorCodes", method = RequestMethod.GET)
	@ResponseBody
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO response = authenticationService
				.getErrorCodes();
		return response;
	}

	/**
	 * pageControles
	 * @param pageKey
	 * @return buttonList
	 * 
	 */
	@RequestMapping(value = "pageControles/{pageKey}", method = RequestMethod.POST)
	@ResponseBody
	public ButtonList getPageControl(@PathVariable String pageKey)  {
//
//		ObjectMapper om = new ObjectMapper();
//
//		PageControlsEnum fileName=PageControlsEnum.getEnum(pageKey);			
//		ButtonList controls=null;
//
//		try {
//			File file=new File(serverPath + fileName.getUrl());			
//			InputStream inputStream = new FileInputStream(file);
//
//			controls = om.readValue(inputStream,ButtonList.class);
//
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpServletRequest req = t.getRequest();
//		List<Button> buttons = new ArrayList<Button>();
//		for(Button button:controls.getButtons()){
//			String url = button.getUrl();
//			Permission permission =permissionService.getPermission(url);
//			User user= (User) req.getSession().getAttribute("user");
//			System.out.println(permission);
//			if (permission==null || user.isPermitted(permission)){
//				buttons.add(button);
//			}
//
//		}
//
//		ButtonList buttonList = new ButtonList();
//		buttonList.setButtons(buttons);
//		return buttonList;
		return null;
	}
	/**
	 * @return the serverPath
	 */
	public String getServerPath() {
		return serverPath;
	}


	/**
	 * @param serverPath the serverPath to set
	 */
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}


	/**
	 * @return the permissionService
	 */
	public PermissionService getPermissionService() {
		return permissionService;
	}


	/**
	 * @param permissionService the permissionService to set
	 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
}
