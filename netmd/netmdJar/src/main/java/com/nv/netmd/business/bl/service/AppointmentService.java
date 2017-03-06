/**
 * AppointmentService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public interface AppointmentService {
	public AppointmentResponseDTO create(AppointmentDTO appointment) throws ServiceException;

	public AppointmentResponseDTO update(AppointmentDTO appointment) throws ServiceException;

	public ResponseDTO delete(int id) throws ServiceException;

	public AppointmentListResponseDTO view(int doctorId, String date) throws ServiceException;
	
	public AppointmentResponse appointmentFromYNW(AppointmentDTO appointment) throws ServiceException;
	
	
}
