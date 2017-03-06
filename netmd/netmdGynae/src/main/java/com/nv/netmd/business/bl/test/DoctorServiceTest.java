/**
 * DoctorServiceTest.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.DoctorService;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.UserTypeEnum;
import com.nv.netmd.rs.dto.DoctorAchievementDTO;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.DoctorExperienceDTO;
import com.nv.netmd.rs.dto.DoctorExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListAchievementDTO;
import com.nv.netmd.rs.dto.DoctorListExperienceDTO;
import com.nv.netmd.rs.dto.DoctorListExpertiseDTO;
import com.nv.netmd.rs.dto.DoctorListMembershipDTO;
import com.nv.netmd.rs.dto.DoctorListQualificationDTO;
import com.nv.netmd.rs.dto.DoctorListResponseDTO;
import com.nv.netmd.rs.dto.DoctorLoginDTO;
import com.nv.netmd.rs.dto.DoctorMembershipDTO;
import com.nv.netmd.rs.dto.DoctorPersonalDTO;
import com.nv.netmd.rs.dto.DoctorQualificationDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.LoginDTO;
import com.nv.netmd.rs.dto.ReferralDetails;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
public class DoctorServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

//	@Test
//	public void createDoctor() {
//		// DoctorService doctorService=new DoctorServiceImpl();
//		DoctorService doctorService = (DoctorService) applicationContext
//				.getBean("doctor.service");
//
//		DoctorDetail doctor = new DoctorDetail();
//		doctor.setFirstName("ramarama");
//		doctor.setLastName("TG");
//		doctor.setAddress("Trikkur");
//		doctor.setDateOfBirth("1986-04-17");
//		doctor.setGender("Male");
//		doctor.setWorkHistory("asdf");
//
//		LoginDTO login = new LoginDTO();
//		login.setPassword("netvarth");
//		login.setUserName("ramarama");
//		login.setUserType("Doctor");
//		doctor.setLogin(login);
//
//		try {
//			doctorService.create(doctor);
//		} catch (ServiceException e) {
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}
//	}
	@Test
	public void createPersonalInfo() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreehari");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		doctor.setWorkingPlaces("Indiana");
		doctor.setMobile("9037012848");
		doctor.setPhone("04678234655");
		doctor.setSpecialization("Nuro");		
		doctor.setEmail("sreeram.trikkurmadom@netvarth.com");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikku@netvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPersonalInfoWIthoutEmail() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikkurmadom@netvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPersonalInfoWIthoutGender() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setEmail("sreeram.trikkurmadom@netvarth.com");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikkurmadom@netvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPersonalInfoWIthoutUserName() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setEmail("sreeram.trikkurmadom@netvarth.com");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
	//	login.setUserName("sreeram.trikkurmadom@netvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void view() throws ServiceException {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		doctorService.view(16);
	}
	@Test
	public void createPersonalInfoInvalidDB() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		doctor.setEmail("sreeram.trikkurmadom@netvarth.com");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikkurmadom@netvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createPersonalInfoInvalidUserType() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		doctor.setEmail("sreeram.trikkurmadom@netvarth.com");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikkurmadom@netvarth.com");
		login.setUserType("Doctr");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPersonalInfoInvalidMail() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("rama");
		doctor.setLastName("TG");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf");
		doctor.setEmail("sreeram.trikkurmadotvarth.com");
		LoginDTO login = new LoginDTO();
		//login.setPassword("netvarth");
		login.setUserName("sreeram.trikkurmadotvarth.com");
		login.setUserType("Doctor");
		doctor.setLogin(login);

		try {
			doctorService.createPersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	

//	@Test
//	public void update() {
//		// DoctorService doctorService=new DoctorServiceImpl();
//		DoctorService doctorService = (DoctorService) applicationContext
//				.getBean("doctor.service");
//
//		DoctorDetail doctor = new DoctorDetail();
//		List<DoctorExperienceDTO> doctorExperienceList = new ArrayList<DoctorExperienceDTO>();
//		DoctorExperienceDTO doctorExperience = new DoctorExperienceDTO();
//		List<DoctorQualificationDTO> doctorQualificationList = new ArrayList<DoctorQualificationDTO>();
//		DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();
//		doctor.setId(2);
//		doctor.setFirstName("sree");
//		doctor.setLastName("TG");
//		doctor.setAddress("Trikkur");
//		doctor.setDateOfBirth("1976-04-17");
//		doctor.setGender("Male");
//		doctor.setPhone("04872340181");
//		doctor.setEmail("sreeram@gmail.com");
//		doctor.setWorkHistory("asdf");
//		doctorExperience.setActionName(ActionNameEnum.ADD.getDisplayName());
//		doctorExperience.setDesignation("doctor");
//		doctorExperience.setFromDate("1986-09-11");
//		doctorExperience.setWorkedAt("aluva");
//		doctorExperienceList.add(doctorExperience);
//		doctor.setDoctorExperience(doctorExperienceList);
//		doctorQualification.setActionName(ActionNameEnum.ADD.getDisplayName());
//
//		doctorQualificationList.add(doctorQualification);
//		doctor.setDoctorQualifications(doctorQualificationList);
//		try {
//			doctorService.update(doctor);
//		} catch (ServiceException e) {
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}
//	}

//	@Test
//	public void updateWithoutActionName() {
//		// DoctorService doctorService=new DoctorServiceImpl();
//		DoctorService doctorService = (DoctorService) applicationContext
//				.getBean("doctor.service");
//
//		DoctorDetail doctor = new DoctorDetail();
//		List<DoctorExperienceDTO> doctorExperienceList = new ArrayList<DoctorExperienceDTO>();
//		DoctorExperienceDTO doctorExperience = new DoctorExperienceDTO();
//		List<DoctorQualificationDTO> doctorQualificationList = new ArrayList<DoctorQualificationDTO>();
//		DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();
//		doctor.setId(2);
//		doctor.setFirstName("sree");
//		doctor.setLastName("TG");
//		doctor.setAddress("Trikkur");
//		doctor.setDateOfBirth("1976-04-17");
//		doctor.setGender("Male");
//
//		doctor.setWorkHistory("asdf");
//		doctorExperience.setDesignation("doctor");
//		doctorExperience.setFromDate("1986-09-11");
//		doctorExperience.setWorkedAt("aluva");
//		doctorExperienceList.add(doctorExperience);
//		doctor.setDoctorExperience(doctorExperienceList);
//		doctorQualification.setActionName(ActionNameEnum.ADD.getDisplayName());
//		doctorQualificationList.add(doctorQualification);
//		doctor.setDoctorQualifications(doctorQualificationList);
//		try {
//			doctorService.update(doctor);
//		} catch (ServiceException e) {
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}
//	}

	@Test
	public void filter() throws ServiceException {
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("status");
		exp.setOperator("eq");
		exp.setValue("active");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		filter.setExp(exps);
		DoctorListResponseDTO response = doctorService.list(filter);
		System.out.println(response.getReferral().size());
		for (ReferralDetails ref : response.getReferral()) {
			System.out.println(ref.getFirstName());
		}

	}
	@Test
	public void doctorQualification() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListQualificationDTO listquali=new DoctorListQualificationDTO();

		List<DoctorQualificationDTO> doctorQualificationList = new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();
		doctorQualification.setActionName(ActionNameEnum.ADD.getDisplayName());
		doctorQualification.setEducationalDegree("sssdadas casds");
		doctorQualification.setDoctorId(16);
		doctorQualification.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		doctorQualification.setEducationalDegree("sssdadas casds");
		doctorQualification.setDoctorId(16);
		doctorQualification.setId(1);
		doctorQualificationList.add(doctorQualification);
		listquali.setQualification(doctorQualificationList);

		try {
			doctorService.doctorQualification(listquali);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorQualificationInvalidAction() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListQualificationDTO listquali=new DoctorListQualificationDTO();

		List<DoctorQualificationDTO> doctorQualificationList = new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();

		doctorQualification.setActionName("Update");
		doctorQualification.setEducationalDegree("sssdadas casds");
		doctorQualification.setDoctorId(3);
		doctorQualification.setId(1);
		doctorQualificationList.add(doctorQualification);
		listquali.setQualification(doctorQualificationList);

		try {
			doctorService.doctorQualification(listquali);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorQualificationWithOutDocid() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");


		DoctorListQualificationDTO listquali=new DoctorListQualificationDTO();

		List<DoctorQualificationDTO> doctorQualificationList = new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();

		doctorQualification.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		doctorQualification.setEducationalDegree("sssdadas casds");
		//doctorQualification.setDoctorId(3);
		doctorQualification.setId(1);
		doctorQualificationList.add(doctorQualification);
		listquali.setQualification(doctorQualificationList);

		try {
			doctorService.doctorQualification(listquali);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorAchievement() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListAchievementDTO achievementlist=new DoctorListAchievementDTO();

		List<DoctorAchievementDTO> doctorachievementlist = new ArrayList<DoctorAchievementDTO>();
		DoctorAchievementDTO achievement = new DoctorAchievementDTO();

		achievement.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		achievement.setAchievement("Gold medal ");
		achievement.setDoctorId(3);
		achievement.setId(1);
		doctorachievementlist.add(achievement);
		achievementlist.setAchievement(doctorachievementlist);

		try {
			doctorService.doctorAchievement(achievementlist);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorAchievementNoDocId() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListAchievementDTO achievementlist=new DoctorListAchievementDTO();

		List<DoctorAchievementDTO> doctorachievementlist = new ArrayList<DoctorAchievementDTO>();
		DoctorAchievementDTO achievement = new DoctorAchievementDTO();

		achievement.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		achievement.setAchievement("Gold medal ");
		//achievement.setDoctorId(3);
		achievement.setId(1);
		doctorachievementlist.add(achievement);
		achievementlist.setAchievement(doctorachievementlist);

		try {
			doctorService.doctorAchievement(achievementlist);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorAchievementInvalidAction() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListAchievementDTO achievementlist=new DoctorListAchievementDTO();

		List<DoctorAchievementDTO> doctorachievementlist = new ArrayList<DoctorAchievementDTO>();
		DoctorAchievementDTO achievement = new DoctorAchievementDTO();

		achievement.setActionName("Update");
		achievement.setAchievement("Gold medal ");
		achievement.setDoctorId(3);
		achievement.setId(1);
		doctorachievementlist.add(achievement);
		achievementlist.setAchievement(doctorachievementlist);

		try {
			doctorService.doctorAchievement(achievementlist);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void doctorExperience() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListExperienceDTO experienceListDTO=new DoctorListExperienceDTO();

		List<DoctorExperienceDTO> experienceList = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();

		experience.setActionName(ActionNameEnum.ADD.getDisplayName());
		experience.setDesignation("doctor");
		experience.setWorkedAt("elite");
		experience.setFromDate("2011-01-11");
		experience.setToDate("2012-01-11");

		experience.setDoctorId(3);
		experienceList.add(experience);

		experienceListDTO.setExperience(experienceList);
		try {
			doctorService.doctorExperience(experienceListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExperienceNoDoc() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExperienceDTO experienceListDTO=new DoctorListExperienceDTO();

		List<DoctorExperienceDTO> experienceList = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();

		experience.setActionName(ActionNameEnum.ADD.getDisplayName());
		experience.setDesignation("doctor");
		experience.setWorkedAt("elite");
		experience.setFromDate("2011-01-11");
		experience.setToDate("2012-01-11");

		//experience.setDoctorId(3);
		experienceList.add(experience);

		experienceListDTO.setExperience(experienceList);
		try {
			doctorService.doctorExperience(experienceListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExperienceInvalidFromDate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExperienceDTO experienceListDTO=new DoctorListExperienceDTO();

		List<DoctorExperienceDTO> experienceList = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();

		experience.setActionName(ActionNameEnum.ADD.getDisplayName());
		experience.setDesignation("doctor");
		experience.setWorkedAt("elite");
		experience.setFromDate("2011-01");
		experience.setToDate("2012-01-11");

		experience.setDoctorId(3);
		experienceList.add(experience);

		experienceListDTO.setExperience(experienceList);
		try {
			doctorService.doctorExperience(experienceListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExperienceInvalidTodate() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExperienceDTO experienceListDTO=new DoctorListExperienceDTO();

		List<DoctorExperienceDTO> experienceList = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();

		experience.setActionName(ActionNameEnum.ADD.getDisplayName());
		experience.setDesignation("doctor");
		experience.setWorkedAt("elite");
		experience.setFromDate("2011-01-11");
		experience.setToDate("2012-01");

		experience.setDoctorId(3);
		experienceList.add(experience);

		experienceListDTO.setExperience(experienceList);
		try {
			doctorService.doctorExperience(experienceListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExperienceInvalidActionName() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExperienceDTO experienceListDTO=new DoctorListExperienceDTO();

		List<DoctorExperienceDTO> experienceList = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();

		experience.setActionName("ad");
		experience.setDesignation("doctor");
		experience.setWorkedAt("elite");
		experience.setFromDate("2011-01-11");
		experience.setToDate("2012-01-11");

		experience.setDoctorId(3);
		experienceList.add(experience);

		experienceListDTO.setExperience(experienceList);
		try {
			doctorService.doctorExperience(experienceListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExpertise() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListExpertiseDTO expertiseListDto=new DoctorListExpertiseDTO();

		List<DoctorExpertiseDTO>  expertiseList = new ArrayList<DoctorExpertiseDTO>();
		DoctorExpertiseDTO expertise = new DoctorExpertiseDTO();


		expertise.setActionName(ActionNameEnum.ADD.getDisplayName());
		expertise.setExpertise("Cancer");
		expertise.setDoctorId(3);
		expertiseList.add(expertise);
		expertiseListDto.setExpertise(expertiseList);

		try {
			doctorService.doctorExpertise(expertiseListDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExpertiseNoDocId() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExpertiseDTO expertiseListDto=new DoctorListExpertiseDTO();

		List<DoctorExpertiseDTO>  expertiseList = new ArrayList<DoctorExpertiseDTO>();
		DoctorExpertiseDTO expertise = new DoctorExpertiseDTO();


		expertise.setActionName(ActionNameEnum.ADD.getDisplayName());
		expertise.setExpertise("Cancer");
		//expertise.setDoctorId(3);
		expertiseList.add(expertise);
		expertiseListDto.setExpertise(expertiseList);

		try {
			doctorService.doctorExpertise(expertiseListDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExpertiseInvalidAction() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExpertiseDTO expertiseListDto=new DoctorListExpertiseDTO();

		List<DoctorExpertiseDTO>  expertiseList = new ArrayList<DoctorExpertiseDTO>();
		DoctorExpertiseDTO expertise = new DoctorExpertiseDTO();


		expertise.setActionName("Ad");
		expertise.setExpertise("Cancer");
		expertise.setDoctorId(3);
		expertiseList.add(expertise);
		expertiseListDto.setExpertise(expertiseList);

		try {
			doctorService.doctorExpertise(expertiseListDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorExpertiseWithOutName() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListExpertiseDTO expertiseListDto=new DoctorListExpertiseDTO();

		List<DoctorExpertiseDTO>  expertiseList = new ArrayList<DoctorExpertiseDTO>();
		DoctorExpertiseDTO expertise = new DoctorExpertiseDTO();


		expertise.setActionName(ActionNameEnum.ADD.getDisplayName());

		expertise.setDoctorId(3);
		expertiseList.add(expertise);
		expertiseListDto.setExpertise(expertiseList);

		try {
			doctorService.doctorExpertise(expertiseListDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorMembership() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorListMembershipDTO membershipListDTO=new DoctorListMembershipDTO();
		List<DoctorMembershipDTO>  membershipList = new ArrayList<DoctorMembershipDTO>();
		DoctorMembershipDTO membership = new DoctorMembershipDTO();

		membership.setActionName(ActionNameEnum.ADD.getDisplayName());
		membership.setMembership("All India medical ");
		membership.setDoctorId(3);
		membershipList.add(membership);
		membershipListDTO.setMembership(membershipList);

		try {
			doctorService.doctorMembership(membershipListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorMembershipNoDocId() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListMembershipDTO membershipListDTO=new DoctorListMembershipDTO();
		List<DoctorMembershipDTO>  membershipList = new ArrayList<DoctorMembershipDTO>();
		DoctorMembershipDTO membership = new DoctorMembershipDTO();

		membership.setActionName(ActionNameEnum.ADD.getDisplayName());
		membership.setMembership("All India medical ");
		//membership.setDoctorId(3);
		membershipList.add(membership);
		membershipListDTO.setMembership(membershipList);

		try {
			doctorService.doctorMembership(membershipListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorMembershipInvalidAction() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListMembershipDTO membershipListDTO=new DoctorListMembershipDTO();
		List<DoctorMembershipDTO>  membershipList = new ArrayList<DoctorMembershipDTO>();
		DoctorMembershipDTO membership = new DoctorMembershipDTO();

		membership.setActionName("Ad");
		membership.setMembership("All India medical ");
		membership.setDoctorId(3);
		membershipList.add(membership);
		membershipListDTO.setMembership(membershipList);

		try {
			doctorService.doctorMembership(membershipListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void doctorMembershipNomem() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorListMembershipDTO membershipListDTO=new DoctorListMembershipDTO();
		List<DoctorMembershipDTO>  membershipList = new ArrayList<DoctorMembershipDTO>();
		DoctorMembershipDTO membership = new DoctorMembershipDTO();

		membership.setActionName(ActionNameEnum.ADD.getDisplayName());

		membership.setDoctorId(3);
		membershipList.add(membership);
		membershipListDTO.setMembership(membershipList);

		try {
			doctorService.doctorMembership(membershipListDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfo() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreeram");
		doctor.setId(3);
		doctor.setLastName("TG");
		doctor.setPhone("0487-2340181");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfoWithOutDoc() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreeram");

		doctor.setLastName("TG");
		doctor.setPhone("0487-2340181");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfoWithOutFirstName() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();

		doctor.setId(3);
		doctor.setLastName("TG");
		doctor.setPhone("0487-2340181");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfoInvalidPh() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreeram");
		doctor.setId(3);
		doctor.setLastName("TG");
		doctor.setPhone("048740181");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfoInvaliddDb() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");

		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreeram");
		doctor.setId(3);
		doctor.setLastName("TG");
		doctor.setPhone("04872340181");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-0");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePersonalInfoInvalidMob() {
		// DoctorService doctorService=new DoctorServiceImpl();
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorPersonalDTO doctor=new DoctorPersonalDTO();
		doctor.setFirstName("sreeram");
		doctor.setId(3);
		doctor.setLastName("TG");
		doctor.setPhone("0487-2340181");
		doctor.setMobile("63763638332");
		doctor.setAddress("Trikkur");
		doctor.setDateOfBirth("1986-04-17");
		doctor.setGender("Male");
		doctor.setWorkHistory("asdf and ddkd jdkj");


		try {
			doctorService.updatePersonalInfo(doctor);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
//	@Test
//	public void dateFormat(){
//		try{
//			String d1="2012-03-04";
//			String[] splitdata=d1.split("-");
//
//			int year=Integer.parseInt(splitdata[0]);		
//			int month=Integer.parseInt(splitdata[1]);
//			int day=Integer.parseInt(splitdata[2]);
//			Calendar cal=Calendar.getInstance();
//			cal.set(year,month,day);
//			Date d=cal.getTime();
//			System.out.println("Date="+d);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//	}
	@Test
	public void updateFromYNW(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		List<DoctorExperienceDTO> doctorExperience = new ArrayList<DoctorExperienceDTO>();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(25);
		doctorDetail.setEmail("sreeram.trikkur@hotmail.com");
		doctorDetail.setFirstName("sreeram");
		doctorDetail.setLastName("TG");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setStatus(StatusEnum.Inactive.getDisplayName());
		doctorDetail.setMobile("9937012848");
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqQ==");
		login.setUserName("Hareymmam");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		DoctorExperienceDTO exDto=new DoctorExperienceDTO();
		exDto.setDesignation("sss");
		exDto.setWorkedAt("chumma");
		exDto.setFromDate("1996-04-01");
		exDto.setToDate("1996-04-20");
		doctorExperience.add(exDto);
		doctorDetail.setDoctorExperience(doctorExperience);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createFromYNW(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWInvalidgender(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("ale");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWInvalidUserType(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType("sa");
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWINVALIDph(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setPhone("das993");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createFromYNWInvalidMb(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037w848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createFromYNWInvalidDB(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createFromYNWWithOutEmail(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);

		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWWithoutStatus(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");

		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWNoGlobalId(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		//doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWWithOutFirstname(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		//		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void createFromYNWInvalidExperience(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorExperienceDTO> expList=new ArrayList<DoctorExperienceDTO>();

		DoctorExperienceDTO experienceDto=new DoctorExperienceDTO();
		experienceDto.setFromDate("12-02");
		expList.add(experienceDto);
		doctorDetail.setDoctorExperience(expList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createFromYNWInvalidExperienceTodate(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorExperienceDTO> expList=new ArrayList<DoctorExperienceDTO>();

		DoctorExperienceDTO experienceDto=new DoctorExperienceDTO();
		experienceDto.setFromDate("12-02-2012");
		experienceDto.setToDate("12-3");
		expList.add(experienceDto);
		doctorDetail.setDoctorExperience(expList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createFromYNWInvalidExpertise(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorExpertiseDTO>expertiseList=new ArrayList<DoctorExpertiseDTO>();
		DoctorExpertiseDTO expertise=new DoctorExpertiseDTO();
		expertise.setDoctorId(1);
		expertiseList.add(expertise);
		doctorDetail.setExpertise(expertiseList);

		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWAchievementwithOutDoctor(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorAchievementDTO>achievementList=new ArrayList<DoctorAchievementDTO>();
		DoctorAchievementDTO achievement=new DoctorAchievementDTO();
		achievement.setAchievement("Highest md");
		achievementList.add(achievement);
		doctorDetail.setAchievement(achievementList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWAchievement(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorAchievementDTO>achievementList=new ArrayList<DoctorAchievementDTO>();
		DoctorAchievementDTO achievement=new DoctorAchievementDTO();

		achievement.setDoctorId(1);
		achievementList.add(achievement);
		doctorDetail.setAchievement(achievementList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWInvalidQuali(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorQualificationDTO>qualificList=new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO quali=new DoctorQualificationDTO();
		quali.setEducationalDegree("neww");
		qualificList.add(quali);
		doctorDetail.setDoctorQualifications(qualificList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWInvalidQualiNoInsti(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorQualificationDTO>qualificList=new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO quali=new DoctorQualificationDTO();
		quali.setEducationalDegree("neww");

		quali.setPassedOutDate("2012");
		qualificList.add(quali);
		doctorDetail.setDoctorQualifications(qualificList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWvalidQuali(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorQualificationDTO>qualificList=new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO quali=new DoctorQualificationDTO();
		quali.setEducationalDegree("neww");
		quali.setInstitution("vidya");
		quali.setPassedOutDate("2012");
		qualificList.add(quali);
		doctorDetail.setDoctorQualifications(qualificList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWInvalidQualiInvalidPassoutYear(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorQualificationDTO>qualificList=new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO quali=new DoctorQualificationDTO();
		quali.setEducationalDegree("neww");
		quali.setPassedOutDate("5252-22-2");
		quali.setInstitution("vidya");
		qualificList.add(quali);
		doctorDetail.setDoctorQualifications(qualificList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createFromYNWMember(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDetail doctorDetail=new DoctorDetail();
		LoginDTO login=new LoginDTO();
		doctorDetail.setGlobalId(29);
		doctorDetail.setEmail("sreeram.trikkur@yahoo.co.in");
		doctorDetail.setFirstName("ramarama");
		doctorDetail.setLastName("t");
		doctorDetail.setAddress("trikkur madom,urakam");
		doctorDetail.setDateOfBirth("1986-04-17");
		doctorDetail.setGender("Male");
		doctorDetail.setMobile("9037012848");
		doctorDetail.setStatus(StatusEnum.Active.getDisplayName());
		login.setPassword("aW8oFWDMOJUrIV3l7R7hqa==");
		login.setUserName("sreees");
		login.setUserType(UserTypeEnum.Admin.getDisplayName());
		doctorDetail.setLogin(login);
		List<DoctorMembershipDTO>memList=new ArrayList<DoctorMembershipDTO>();
		DoctorMembershipDTO member=new DoctorMembershipDTO();
		memList.add(member);
		doctorDetail.setMembership(memList);
		try {
			doctorService.doctorFromYNW(doctorDetail);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void updateDoctorPassword(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorLoginDTO loginDto=new DoctorLoginDTO();
		loginDto.setDoctorGlobalId(1);
		loginDto.setEmail("sreeram.trikkur@gm.com");
		loginDto.setPassword("dfuigcfu8y8");

		try {
			doctorService.updateDoctorPassword(loginDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateDoctorPasswordWithoutPswd(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorLoginDTO loginDto=new DoctorLoginDTO();
		loginDto.setDoctorGlobalId(1);
		loginDto.setEmail("sreeram.trikkur@gm.com");
		//loginDto.setPassword("dfuigcfu8y8");

		try {
			doctorService.updateDoctorPassword(loginDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateDoctorPasswordWithOutEmail(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorLoginDTO loginDto=new DoctorLoginDTO();
		loginDto.setDoctorGlobalId(1);
		//loginDto.setEmail("sreeram.trikkur@gm.com");
		loginDto.setPassword("dfuigcfu8y8");

		try {
			doctorService.updateDoctorPassword(loginDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateDoctorPasswordInvalidEmail(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorLoginDTO loginDto=new DoctorLoginDTO();
		loginDto.setDoctorGlobalId(1);
		loginDto.setEmail("sreeram.trikm.com");
		loginDto.setPassword("dfuigcfu8y8");

		try {
			doctorService.updateDoctorPassword(loginDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateDoctorPasswordWithOutGId(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorLoginDTO loginDto=new DoctorLoginDTO();
		//loginDto.setDoctorGlobalId(1);
		loginDto.setEmail("sreeram.trikkur@gm.com");
		loginDto.setPassword("dfuigcfu8y8");

		try {
			doctorService.updateDoctorPassword(loginDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void delete(){
		DoctorService doctorService = (DoctorService) applicationContext
				.getBean("doctor.service");
		try {
			doctorService.delete(18);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
}
