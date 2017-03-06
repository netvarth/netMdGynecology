/**
 * LoginDTO.java
 *
 * Nov 30, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class LoginDTO {

	private String password;
	//private String name;
	private String userType;
	private String userName;

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name
//	 *            the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}

}
