/**
 * AppointmentListResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 21, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class AppointmentListResponseDTO {
	private List<AppointmentDTO> appointment = new ArrayList<AppointmentDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public AppointmentListResponseDTO() {
		super();
	}

	/**
	 * @param appointment
	 * @param error
	 * @param success
	 * @param count
	 */
	public AppointmentListResponseDTO(List<AppointmentDTO> appointment,
			ErrorDTO error, boolean success, Long count) {
		super();
		this.appointment = appointment;
		this.error = error;
		this.success = success;
		this.count = count;
	}

	/**
	 * @return the appointment
	 */
	public List<AppointmentDTO> getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment
	 *            the appointment to set
	 */
	public void setAppointment(List<AppointmentDTO> appointment) {
		this.appointment = appointment;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
