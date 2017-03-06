/**
 * AppointmentServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.netmd.business.bl.service.AppointmentService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.AppointmentStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.AppointmentDTO;
import com.nv.netmd.rs.dto.AppointmentResponseDTO;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class AppointmentServiceTest {

	@Autowired
	private ApplicationContext applicationContext;


	@Test
	public void createAppointment() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
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
	public void createAppointmentWithOutDoctor() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
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
	public void createAppointmentWithOutPatient() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(10);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
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
	public void createAppointmentWithoutStartDate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);

		appointment.setStartTime("11:15 am");
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
	public void createAppointmentWithOutSchedule() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		//AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//		response=	appointmentService.create(appointment);
		//		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
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
	public void createAppointmentWithOutStartdate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
	//	AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartTime("11:15 am");
		//		response=	appointmentService.create(appointment);
		//		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
		//		assert ((appointmentService.create(appointment)) != null);
		//		
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		//AppointmentResponseDTO response=new AppointmentResponseDTO();

		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");

		//		response=	appointmentService.create(appointment);
		//		Assert.assertEquals(response.getError()==null, appointmentService.create(appointment).getError()!=null);
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
	public void createAppointmentFromYNWs() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
	public void createAppointmentWithOutDate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(2);
		appointment.setPatientId(1);
		appointment.setStartTime("10:14 am");
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
	public void createAppointmentAlreadyExist() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(2);
		appointment.setPatientId(1);
		appointment.setStartDate("2012-12-18");
		appointment.setStartTime("10:14 am");
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
	public void createAppointmentInvalidDate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(2);
		appointment.setPatientId(1);
		appointment.setStartDate("2012-12");
		appointment.setStartTime("10:14 am");
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
	public void createAppointmentInvalidTime() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(2);
		appointment.setPatientId(1);
		appointment.setStartDate("2012-12-18");
		appointment.setStartTime("10:4 am");
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
	public void createAppointmentNoDoctor() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		// appointment.setDoctorId(2);
		appointment.setPatientId(1);
		appointment.setStartDate("2012-12-18");
		appointment.setStartTime("10:14 am");
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
	public void createAppointmentInvalidDoctor() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(8);
		appointment.setPatientId(1);
		appointment.setStartDate("2012-12-18");
		appointment.setStartTime("10:14 am");
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
	public void createAppointmentInvalidPatient() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(8);
		appointment.setPatientId(11);
		appointment.setStartDate("2012-12-18");
		appointment.setStartTime("10:14:00");
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");

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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");

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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		appointmentService.delete(345);
	}
	@Test
	public void updateAppointment() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
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
	public void updateAppointmentStartTimeNull() {
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
	//	AppointmentResponseDTO response=new AppointmentResponseDTO();
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		//AppointmentResponseDTO response=new AppointmentResponseDTO();
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
	//	AppointmentResponseDTO response=new AppointmentResponseDTO();
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);

		appointment.setPatientId(5);
		appointment.setScheduleId(19);
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
	public void updateAppointmentWithoutDate() {
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
	//	AppointmentResponseDTO response=new AppointmentResponseDTO();
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
	public void updateAppointmentWithOutProperTime() {
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		//AppointmentResponseDTO response=new AppointmentResponseDTO();

		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-02-11");
		//response=appointmentService.update(appointment);
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
	public void updateAppointmentWithOutAppointmentId() throws ServiceException {
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
		AppointmentResponseDTO response=new AppointmentResponseDTO();

		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDoctorId(10);
		appointment.setPatientId(5);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-02-11");
		appointment.setStartTime("11:12 am");
		response=appointmentService.update(appointment);

	}
	@Test
	public void updateAppointmentWithoutPatient() {
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
//		AppointmentResponseDTO response=new AppointmentResponseDTO();

		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(2);
		appointment.setDoctorId(10);
		appointment.setScheduleId(19);
		appointment.setStartDate("2013-03-11");
		appointment.setStartTime("11:15 am");
		//response=appointmentService.update(appointment);
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
	public void createAppointmentFromYNWWithoutDoctor() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
//		AppointmentResponse response=new AppointmentResponse();

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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
//		AppointmentResponse response=new AppointmentResponse();

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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
	public void createAppointmentFromYNWWithOutPatient() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
	public void createAppointmentFromYNWWithOutSchedule() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
		//Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
	public void createAppointmentFromYNWWithOutStartdate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
		//Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
		//Assert.assertEquals(response.getError()==null, appointmentService.appointmentFromYNW(appointment).getError()!=null);
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
		AppointmentService appointmentService = (AppointmentService) applicationContext
				.getBean("appointment.Service");
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
