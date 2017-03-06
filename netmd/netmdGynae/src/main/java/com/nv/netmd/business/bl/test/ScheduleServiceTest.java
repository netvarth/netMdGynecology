/**
 * ScheduleServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 18, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.ScheduleService;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
public class ScheduleServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void createSchedule() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
	public void createScheduleMonthilyWithOccuranceOnDate() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
	public void weeklyView() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
	public void monthlyView() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
	public void updateSchedule() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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
	public void updateScheduleFromYnw() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.Service");
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