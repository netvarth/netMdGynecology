package com.nv.netmd.rs.dto;

import java.util.Date;
import java.util.List;

public class User {
	private String name;
	private Date loginTime;
	private String userName;
	private int id;
	private int doctorId;
	private String userType;
	private boolean primaryDevice;
	private Integer ownerId;
	private List<UserPermission> userPermissions;


	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime2) {
		this.loginTime = loginTime2;

	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the primaryDevice
	 */
	public boolean isPrimaryDevice() {
		return primaryDevice;
	}

	/**
	 * @param primaryDevice the primaryDevice to set
	 */
	public void setPrimaryDevice(boolean primaryDevice) {
		this.primaryDevice = primaryDevice;
	}
	/**
	 * @return the userPermissions
	 */
	public List<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	/**
	 * @param userPermissions the userPermissions to set
	 */
	public void setUserPermissions(List<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public boolean isPermitted(Permission permission){
		Integer bitMask=Integer.parseInt(Integer.toHexString(permission.getHexaBit()));
		for(UserPermission up:userPermissions){

			if(up.getPermissionGroup().equals(permission.getPermissionGroup())){
				Integer hexaBit=up.getBitmask();
				int result=  bitMask & hexaBit;
				if(result==bitMask)	{
					System.out.println("Equals");
					return true;
				}
			}   
		}
		return false;
	}


}
