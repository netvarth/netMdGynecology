/**
 * ScheduleTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jun 24, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import org.junit.Test;

import com.nv.netmd.business.bl.impl.ScheduleServiceImpl;
import com.nv.netmd.business.bl.validator.ScheduleValidator;
import com.nv.netmd.business.pl.dao.ScheduleDao;
import com.nv.netmd.business.pl.mockImpl.ScheduleMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.OccuranceTypeEnum;
import com.nv.netmd.pl.entity.RepeatEnum;
import com.nv.netmd.pl.entity.ScheduleStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ScheduleDTO;
import com.nv.netmd.rs.dto.ScheduleDetail;
import com.nv.netmd.rs.dto.SeriesDTO;

/**
 * 
 */
public class ScheduleTest {
	ScheduleValidator scheduleValidator;
	ScheduleDao scheduleDao;
	@Test
	public void createSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleInvalidSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus("wrkingHours");
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleInvalidRepeat() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat("non");
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleInvalidTimeFmt() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05m");
		scheduleDto.setEndTime("06:m");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleInvalidTime() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("07:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleInValidDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleNoDoctor() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		//scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		// scheduleDto.setEndDate("2013-02-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleDailywithOccurance() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		ResponseDTO response=new ResponseDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-04-28");
		scheduleDto.setNoOfOccurances(2);
		scheduleDto.setRepeat(RepeatEnum.DAILY.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			response=scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createScheduleDailyWithDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(2);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-29");
		scheduleDto.setEndDate("2012-12-31");
		scheduleDto.setRepeat(RepeatEnum.DAILY.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createScheduleWeeklyWithOccurance() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2013-06-04");

		int[] w = new int[7];
		w[0] = 1;
		w[1] = 2;
		w[2] = 4;
		scheduleDto.setWeeklySunThruSatList(w);
		scheduleDto.setNoOfOccurances(3);
		scheduleDto.setRepeat(RepeatEnum.WEEKLY.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createScheduleWeeklyWithDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(10);
		scheduleDto.setStartTime("01:12 PM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2013-01-01");

		int[] w = new int[25];
		w[0] = 2;
		w[1] = 1;
		w[2] = 4;
		scheduleDto.setWeeklySunThruSatList(w);
		scheduleDto.setEndDate("2013-01-10");
		scheduleDto.setRepeat(RepeatEnum.WEEKLY.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createScheduleMonthilyWithOccurance() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(2);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-02");

		int[] w = new int[25];
		w[0] = 1;
		w[1] = 2;
		w[2] = 4;

		scheduleDto.setWeeklySunThruSatList(w);
		scheduleDto.setNoOfOccurances(3);
		scheduleDto.setRepeat(RepeatEnum.MONTHILY_DAY_OF_THE_WEEK
				.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleMonthilyWithEnddate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(2);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-02");

		int[] w = new int[25];
		w[0] = 1;
		w[1] = 2;
		w[2] = 4;

		scheduleDto.setWeeklySunThruSatList(w);
		scheduleDto.setEndDate("2013-03-04");
		scheduleDto.setRepeat(RepeatEnum.MONTHILY_DAY_OF_THE_WEEK
				.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createScheduleMonthilyWithOccuranceOnDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(1);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-21");
		scheduleDto.setNoOfOccurances(3);

		scheduleDto.setRepeat(RepeatEnum.MONTHILY_DAY_OF_THE_MONTH
				.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createScheduleMonthilyWithDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(1);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-21");
		scheduleDto.setEndDate("2013-01-23");
		scheduleDto.setRepeat(RepeatEnum.MONTHILY_DAY_OF_THE_MONTH
				.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.VACATION.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleMonthilyWithDa() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(1);
		scheduleDto.setStartTime("11:12 AM");
		scheduleDto.setEndTime("02:10 PM");
		scheduleDto.setStartDate("2012-12-21");
		scheduleDto.setEndDate("2013-01-23");
		scheduleDto.setRepeat(RepeatEnum.MONTHILY_DAY_OF_THE_MONTH
				.getDisplayName());
		scheduleDto.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.create(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void deleteSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.delete(2);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void deleteThisInstanceSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.deleteThisInstanceSchedule(1156);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void deletefollowingSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.deletefollowingSchedule(1241);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void dayView() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.dayView("2013-06-10",61);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void dayViewInvalidDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.dayView("2013--10",61);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void dayViewInvaliddoc() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.dayView("2013-06-10",0);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void weeklyView() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.weeklyView("2013-03-11",10);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void weeklyViewnvalidDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.weeklyView("20103-11",10);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void weeklyViewNoDoc() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.weeklyView("2013-03-11",0);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void monthlyView() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.monthlyView("2013-01-28", "2013-02-02",10);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void monthlyViewInvaldiFDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.monthlyView("2013-028", "2013-02-02",10);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void monthlyViewInvalidToDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.monthlyView("2013-01-28", "23-02-02",10);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void monthlyViewNoDoc() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		try {
			scheduleService.monthlyView("2013-01-28", "2013-02-02",0);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDTO scheduleDto = new ScheduleDTO();
		scheduleDto.setDoctorId(2);
		scheduleDto.setSeriesId(85);
		scheduleDto.setStartTime("11:12 am");
		scheduleDto.setEndTime("02:10 pm");
		scheduleDto.setStartDate("2013-01-24");
		scheduleDto.setRepeat(RepeatEnum.NONE.getDisplayName());
		scheduleDto
		.setStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		try {
			scheduleService.update(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnw() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwWithEnd() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.EndsDate.getDisplayName());
		series.setRepeat(RepeatEnum.DAILY.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwNoSEriesGId() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		//series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwNoseries() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		//scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwNoSchedule() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		//scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwNOdoc() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		//scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwStartGEnd() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("06:12 am");
		scheduleDto.setEndTime("07:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwInvalidEndTime() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwInvalidStartTime() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwInvAlidStartDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("20133-11");
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createScheduleFromYnwNoStartDate() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		
		scheduleDto.setStatus(StatusEnum.Active.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateScheduleFromYnw() {
		ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
		scheduleValidator=new ScheduleValidator();
		scheduleDao=new ScheduleMockDaoImpl();
		scheduleService.setScheduleDao(scheduleDao);
		scheduleService.setScheduleValidator(scheduleValidator);
		ScheduleDetail scheduleDto = new ScheduleDetail();
		SeriesDTO series=new SeriesDTO();
		scheduleDto.setDoctorGlobalId(29);
		scheduleDto.setStartTime("05:12 am");
		scheduleDto.setEndTime("06:10 am");
		scheduleDto.setStartDate("2013-03-16");
		scheduleDto.setStatus(StatusEnum.Inactive.getDisplayName());
		scheduleDto.setScheduleGlobalId(31);
		scheduleDto.setScheduleStatus(ScheduleStatusEnum.WORKING_HOURS.getDisplayName());
		//series.setEndDate("2013-03-16");
		series.setOccuranceType(OccuranceTypeEnum.None.getDisplayName());
		series.setRepeat(RepeatEnum.NONE.getDisplayName());
		series.setSeriesGlobalId(35);
		scheduleDto.setSeries(series);
		try {
			scheduleService.scheduleFromYNW(scheduleDto);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
}
