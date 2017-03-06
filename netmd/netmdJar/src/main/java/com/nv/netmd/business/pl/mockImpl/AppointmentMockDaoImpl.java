/**
* AppointmentMockDaoImpl.java
* 
* @Author Sreeram
*
* Version 1.0 Jun 21, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.business.pl.mockImpl;

import java.util.List;

import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentDetailsDTO;
import com.nv.netmd.rs.dto.AppointmentListResponseDTO;
import com.nv.netmd.rs.dto.AppointmentResponse;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
public class AppointmentMockDaoImpl implements AppointmentDao{

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#create(com.nv.netmd.rs.dto.AppointmentDTO)
	 */
	@Override
	public AppointmentResponseDTO create(AppointmentDTO appointment) {
		// TODO Auto-generated method stub
		AppointmentResponseDTO response=new AppointmentResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#update(com.nv.netmd.rs.dto.AppointmentDTO)
	 */
	@Override
	public AppointmentResponseDTO update(AppointmentDTO appointment) {
		// TODO Auto-generated method stub
		AppointmentResponseDTO response=new AppointmentResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#delete(int)
	 */
	@Override
	public ResponseDTO delete(int id) {
		// TODO Auto-generated method stub
		ResponseDTO response=new ResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#view(int, java.lang.String)
	 */
	@Override
	public AppointmentListResponseDTO view(int doctorId, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#getNewAppointments()
	 */
	@Override
	public List<AppointmentDetailsDTO> getNewAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#getUpdatedAppointments()
	 */
	@Override
	public List<AppointmentDetailsDTO> getUpdatedAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#getDeletedAppointments()
	 */
	@Override
	public List<AppointmentDetailsDTO> getDeletedAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#addAppointmentSyncResponse(com.nv.netmd.rs.dto.AppointmentResponse)
	 */
	@Override
	public void addAppointmentSyncResponse(
			AppointmentResponse appointmentResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#updateAppointmentSyncResponse(com.nv.netmd.rs.dto.AppointmentResponse)
	 */
	@Override
	public void updateAppointmentSyncResponse(
			AppointmentResponse appointmentResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#deleteAppointmentSyncResponse(com.nv.netmd.rs.dto.AppointmentResponse)
	 */
	@Override
	public void deleteAppointmentSyncResponse(
			AppointmentResponse appointmentResponse) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.AppointmentDao#appointmentFromYNW(com.nv.netmd.rs.dto.AppointmentDTO)
	 */
	@Override
	public AppointmentResponse appointmentFromYNW(AppointmentDTO appointment) {
		// TODO Auto-generated method stub
		AppointmentResponse response=new AppointmentResponse();
		response.setError(null);
		response.setSuccess(true);
		response.setId(1);
		return response;
		
	}

}
