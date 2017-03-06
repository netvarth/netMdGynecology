/**
 * AppointmentTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jun 21, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;



import org.junit.Assert;
import org.junit.Test;
import com.nv.netmd.business.bl.impl.AppointmentServiceImpl;
import com.nv.netmd.business.bl.validator.AppointmentValidator;
import com.nv.netmd.business.pl.dao.AppointmentDao;
import com.nv.netmd.business.pl.mockImpl.AppointmentMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.AppointmentStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;
/**
 * 
 */
public class AppointmentTest {
	AppointmentValidator appointmentValidator;
	AppointmentDao appointmentDao;
	@Test
	public void createAppointment() throws ServiceException {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//response=	appointmentService.create(appointment);
		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()==null);
		//	assert ((appointmentService.create(appointment)) != null);

	}

	@Test
	public void createAppointmentWithOutDoctor() throws ServiceException {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//=appointmentService.create(appointment);
		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);

	}
	@Test
	public void createAppointmentWithOutPatient() throws ServiceException {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//		response=	appointmentService.create(appointment);
		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);

	}
	@Test
	public void createAppointmentWithOutSchedule() throws ServiceException {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//		response=	appointmentService.create(appointment);
		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);

	}
	@Test
	public void createAppointmentInvalidTime() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:1");
		//response=	appointmentService.create(appointment);
		//Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.create(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentWithOutStartdate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartTime("11:15 am");
		//		response=	appointmentService.create(appointment);
		//	Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.create(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentWithOutStartTime() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");

		//		response=	appointmentService.create(appointment);
		//Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.create(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void viewAppointments() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);

		try {
			appointmentService.view(2, "2013-01-20");
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewAppointmentsNoid() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);

		try {
			appointmentService.view(0, "2013-01-20");
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewAppointmentsInvalidDate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);

		try {
			appointmentService.view(2, "daas");
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteAppointment() throws ServiceException {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		appointmentService.delete(74);
	}
	@Test
	public void updateAppointment() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");

		//	response=appointmentService.update(appointment);
		//		Assert.assertEquals(response, appointmentService.update(appointment));
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateAppointmentStartTimeNull() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartTime("11:15 am");

		//response=appointmentService.update(appointment);
		//		Assert.assertEquals(response, appointmentService.update(appointment));
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithoutSchedule() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");

		//response=appointmentService.update(appointment);
		//		Assert.assertEquals(response, appointmentService.update(appointment));
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithoutDoctor() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);

		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");

		//	response=appointmentService.update(appointment);
		//		Assert.assertEquals(response, appointmentService.update(appointment));
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithoutDate() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartTime("11:15 am");

		//	response=appointmentService.update(appointment);
		//		Assert.assertEquals(response, appointmentService.update(appointment));
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithOutProperTime() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-02-11");
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithOutAppointmentId() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-02-11");
		appointment.setStartTime("11:12 am");
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateAppointmentWithoutPatient() {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		try {
			appointmentService.update(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWs() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponse response=new AppointmentResponse();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(57);
		appointment.setPatientId(57);
		appointment.setScheduleId(105);
		appointment.setGlobalId(8);
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		appointment.setStartDate("2013-06-10");
		appointment.setStartTime("10:20 am");
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}


	}
	@Test
	public void createAppointmentFromYNWWithoutDoctor() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponse response=new AppointmentResponse();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setPatientId(57);
		appointment.setScheduleId(105);
		appointment.setGlobalId(8);
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		appointment.setStartDate("2013-06-10");
		appointment.setStartTime("10:20 am");

		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWIthotGlobalId() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		//		AppointmentResponse response=new AppointmentResponse();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(57);
		appointment.setPatientId(57);
		appointment.setScheduleId(105);
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		appointment.setStartDate("2013-06-10");
		appointment.setStartTime("10:20 am");

		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutDoctorGlobalId() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//=appointmentService.create(appointment);
		//Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutPatient() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//		response=	appointmentService.create(appointment);
		//		Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutSchedule() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//		response=	appointmentService.create(appointment);
		//		Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWInvalidTime() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:1");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//response=	appointmentService.create(appointment);
		//	Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//	assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutStartdate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartTime("11:15 am");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//		response=	appointmentService.create(appointment);
		//	Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutStartTime() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//		response=	appointmentService.create(appointment);
		//	Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createAppointmentFromYNWWithOutAppointmentStatus() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		appointmentValidator=new AppointmentValidator();
		appointmentDao=new AppointmentMockDaoImpl();
		appointmentService.setAppointmentValidator(appointmentValidator);
		appointmentService.setAppointmentDao(appointmentDao);
		AppointmentDTO appointment = new AppointmentDTO();
		//		AppointmentResponseDTO response=new AppointmentResponseDTO();
		appointment.setGlobalId(8);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//appointment.setAppointmentStatus(AppointmentStatusEnum.Confirmed.getDisplayName());
		appointment.setStatus(StatusEnum.Active.getDisplayName());
		//		response=	appointmentService.create(appointment);
		try {
			appointmentService.appointmentFromYNW(appointment);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

}
