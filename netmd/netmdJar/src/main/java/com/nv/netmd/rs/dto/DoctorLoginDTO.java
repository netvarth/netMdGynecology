/**
* DoctorLoginDTO.java
* 
* @Author Sreeram
*
* Version 1.0 May 29, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.rs.dto;

/**
 * 
 */
public class DoctorLoginDTO {
		private String password;
		private int doctorGlobalId;
		private String email;
		private ErrorDTO error;
		private boolean success;
		/**
		 * 
		 */
		public DoctorLoginDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the doctorGlobalId
		 */
		public int getDoctorGlobalId() {
			return doctorGlobalId;
		}
		/**
		 * @param doctorGlobalId the doctorGlobalId to set
		 */
		public void setDoctorGlobalId(int doctorGlobalId) {
			this.doctorGlobalId = doctorGlobalId;
		}
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return the error
		 */
		public ErrorDTO getError() {
			return error;
		}
		/**
		 * @param error the error to set
		 */
		public void setError(ErrorDTO error) {
			this.error = error;
		}
		/**
		 * @return the success
		 */
		public boolean isSuccess() {
			return success;
		}
		/**
		 * @param success the success to set
		 */
		public void setSuccess(boolean success) {
			this.success = success;
		}
}
