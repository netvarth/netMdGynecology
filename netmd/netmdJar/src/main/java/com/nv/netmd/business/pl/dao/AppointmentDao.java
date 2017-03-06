/**
 * AppointmentDao.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.pl.dao;

import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentDetailsDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;


/**
 * 
 */
public interface AppointmentDao {
	public AppointmentResponseDTO create(AppointmentDTO appointment)throws PersistenceException;
	public AppointmentResponseDTO update(AppointmentDTO appointment)throws PersistenceException;
	public ResponseDTO delete(int id)throws PersistenceException;
	public AppointmentListResponseDTO view(int doctorId, String date)throws PersistenceException;
	public List<AppointmentDetailsDTO> getNewAppointments()throws PersistenceException;	
	public List<AppointmentDetailsDTO> getUpdatedAppointments()throws PersistenceException;
	public List<AppointmentDetailsDTO> getDeletedAppointments()throws PersistenceException;
	public void addAppointmentSyncResponse(AppointmentResponse appointmentResponse)throws PersistenceException;
	public void updateAppointmentSyncResponse(AppointmentResponse appointmentResponse)throws PersistenceException;
	public void deleteAppointmentSyncResponse(AppointmentResponse appointmentResponse)throws PersistenceException;	
	public AppointmentResponse appointmentFromYNW(AppointmentDTO appointment)throws PersistenceException;

}
