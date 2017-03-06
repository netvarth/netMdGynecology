package com.nv.netmd.business.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.CaseStatusEnum;
import com.nv.netmd.pl.entity.MedicalRecordTypeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.PatientMedicalHistoryDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
public class PatientServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void createPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		ResponseDTO response=new ResponseDTO();
		patientDto.setFirstName("Jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.tri@ao.com");
		patientDto.setPhone("04872340181");
		patientDto.setMobile("9037012838");
		patientDto.setAge(2);
		patientDto.setAllergies("nothing");
		patientDto.setAilment("saaas");
		patientDto.setBloodGroup("o -ve");
		patientDto.setChronicDisease("nothing");
		patientDto.setEmergencyNo("8927277222");
		patientDto.setFamilyHistory("hgsa djhjhd");
		patientDto.setHeight("7 cm");
		patientDto.setWeight("98 kg");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			response=patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidmob() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul@dff.com");
		patientDto.setPhone("048723401881");
		patientDto.setMobile("768");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidaction() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientInvalidPhone() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("0487234");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithoutFirstName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithInvalidActionName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithOutGender() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatientWithoutEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		//patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWt() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);	
		patientMedHistDto.setActionName("Add");
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtInvalidMob() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setMobile("87687b");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtInvalidPh() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setPhone("87687b");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWNoGlobalId() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();

		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWtFisrtName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);

		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWwithOutEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		//patientDto.setEmail("sreeram.trikkur@rediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPatienFromYNWInvalidEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setGlobalId(57);
		patientDto.setFirstName("harey ram");
		patientDto.setAddress("trikkur madom");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkurediffmail.co.in");
		patientDto.setStatus(StatusEnum.Inactive.getDisplayName());
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.patientFromYNW(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void view() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			patientService.view(12);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void update() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDetail patientDto = new PatientDetail();
		List<PatientMedicalHistoryDTO> patientMedicalList = new ArrayList<PatientMedicalHistoryDTO>();
		PatientMedicalHistoryDTO medicalDTO = new PatientMedicalHistoryDTO();
		patientDto.setId(1);
		patientDto.setFirstName("sree");
		patientDto.setLastName("TG");
		medicalDTO.setActionName(ActionNameEnum.ADD.getDisplayName());
		medicalDTO.setPatientId(1);
		medicalDTO.setDiagonisedAge(2);
		medicalDTO.setMedicalIssue("headache");
		patientMedicalList.add(medicalDTO);
		patientDto.setMedicalHistory(patientMedicalList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updatePatientInvalidmob() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkul@dff.com");
		patientDto.setPhone("048723401881");
		patientDto.setMobile("768");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.create(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidaction() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Ad");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidPhone() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("0487234");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientInvalidEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkurol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updatePatientWithoutId() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		//		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutEmail() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		//		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutGender() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setFirstName("jayram");
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWithoutName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		PatientDetail patientDto = new PatientDetail();
		PatientMedicalHistoryDTO patientMedHistDto = new PatientMedicalHistoryDTO();
		List<PatientMedicalHistoryDTO> patientMedHistList = new ArrayList<PatientMedicalHistoryDTO>();
		patientDto.setId(1);
		patientDto.setAddress("asdfasd lkjdsfads ;lkjadfadsf ;ladfkjf");
		patientDto.setAilment("fever");
		patientDto.setGender("Male");
		patientDto.setDiagnosis("diagnosis");
		patientDto.setEmail("sreeram.trikkur@aol.com");
		patientDto.setPhone("048723401881");
		patientMedHistDto.setMedicalIssue("fever");
		patientMedHistDto.setDiagonisedAge(10);
		patientMedHistDto.setActionName("Add");			
		patientMedHistList.add(patientMedHistDto);
		patientDto.setMedicalHistory(patientMedHistList);
		try {
			patientService.update(patientDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void filter() throws ServiceException {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
//		ExpressionDTO exp = new ExpressionDTO();
//		exp.setName("firstName");
//		exp.setOperator("eq");
//		exp.setValue("sree");	
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("status");
		exp1.setOperator("eq");
		exp1.setValue("active");	
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		//exps.add(exp);
		exps.add(exp1);
		filter.setExp(exps);
		PatientListResponseDTO response = patientService.list(filter);
		System.out.println(response.getPatient().size());
		for (PatientDetail pat : response.getPatient()) {
			System.out.println(pat.getFirstName());

		}

	}

	@Test
	public void caseFilter() throws ServiceException {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("patientId");
		exp.setOperator("eq");
		exp.setValue("16");
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("status");
		exp1.setOperator("eq");
		exp1.setValue("Open");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		exps.add(exp1);
		filter.setExp(exps);
		CaseListResponseDTO response = patientService.listOfCase(filter);
		System.out.println(response.getCaseList().size());
		for (CaseDTO ca : response.getCaseList()) {
			System.out.println(ca.getCaseName());

		}

	}

	@Test
	public void mediclRecordFilter() throws ServiceException {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		ExpressionDTO exp = new ExpressionDTO();
		exp.setName("patientId");
		exp.setOperator("eq");
		exp.setValue("5");
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("type");
		exp1.setOperator("eq");
		exp1.setValue("Personal visit");
		ExpressionDTO exp2 = new ExpressionDTO();
		exp2.setName("caseId");
		exp2.setOperator("eq");
		exp2.setValue("6");
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		exps.add(exp);
		exps.add(exp1);
		exps.add(exp2);
		filter.setExp(exps);
		MedicalListResponseDTO response = patientService
				.listOfMedicalRecord(filter);
		System.out.println(response.getMedicalList().size());
		for (MedicalRecordDTO ca : response.getMedicalList()) {
			System.out.println(ca.getMedicalRecord());

		}

	}

	@Test
	public void listCase() throws ServiceException {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseListResponseDTO response = new CaseListResponseDTO();

		response = patientService.listCase();
	}

	@Test
	public void listCaseByPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseListResponseDTO response = new CaseListResponseDTO();

		try {
			response = patientService.listCaseByPatient(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO casedto = new CaseDTO();
		casedto.setCaseName("hand pain");

		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createCaseWithOutPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO casedto = new CaseDTO();
		casedto.setCaseName("hand pain");
		//casedto.setCaseStatus(CaseStatusEnum.OPEN.getDisplayName());
		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createCaseWithOutName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO casedto = new CaseDTO();
		///casedto.setCaseStatus(CaseStatusEnum.OPEN.getDisplayName());
		casedto.setPatientId(1);
		try {
			patientService.createCase(casedto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createMedicalReport() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);

		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createMedicalReportWithOutMedicalRecord() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutDoct() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalReportWithOutCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO record = new MedicalRecordDTO();
		
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createMedicalNoType() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();

		try {
			response = patientService.viewCase(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setActionName("update");
//		response.setCaseStatus("OPEN");
//		response.setLongDesc("hia  iahia");
//		response.setShortDesc("ss");
		response.setPatientId(1);
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseNoActionName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();
		response.setId(2);
		//response.setActionName("update");
		//response.setCaseStatus("OPEN");
//		RESPONSE.SETLONGDESC("HIA  IAHIA");
//		RESPONSE.SETSHORTDESC("SS");
		response.setPatientId(1);
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOutPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setActionName("update");
//		response.setCaseStatus("OPEN");
//		response.setLongDesc("hia  iahia");
//		response.setShortDesc("ss");
		
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOtName() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();
		response.setId(2);
		response.setActionName("update");
//		response.setCaseStatus("OPEN");
//		response.setLongDesc("hia  iahia");
//		response.setShortDesc("ss");
		response.setPatientId(1);
		

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateCaseWithOutId() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		CaseDTO response = new CaseDTO();
		
		response.setActionName("update");
//		response.setCaseStatus("OPEN");
//		response.setLongDesc("hia  iahia");
//		response.setShortDesc("ss");
		response.setPatientId(1);
		response.setCaseName("jambruttan");

		try {
			patientService.updateCase(response);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}


	@Test
	public void listMedicalRecord() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listMedicalRecord(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listVisit() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listPersonalVisit(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void viewMedicalRecord() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO response = new MedicalRecordDTO();
		try {
			response = patientService.viewMedicalRecord(1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReport() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType("prescription");
		record.setMedicalRecord("sample medical record");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateMedicalReportWithOutId() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO record = new MedicalRecordDTO();
	
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType("prescription");
		record.setMedicalRecord("sample medical record");
		try {
			patientService.updateMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateMedicalReportWithOutMedicalRecord() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutDoct() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setPatientId(1);
		record.setId(1);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setCaseId(1);
		record.setId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalReportWithOutCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		record.setType(MedicalRecordTypeEnum.OTHER.getDisplayName());
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateMedicalNoType() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		MedicalRecordDTO record = new MedicalRecordDTO();
		record.setId(1);
		record.setCaseId(1);
		record.setPatientId(1);
		record.setDoctorId(3);
		
		record.setMedicalRecord("sample medical record.he has taken so...");
		try {
			patientService.createMedicalRecord(record);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");

		try {
			patientService.getMedicalRecordByCase(1, 1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycaseWithOutPatient() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			patientService.getMedicalRecordByCase(0, 1);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMedicalBycaseWithOutCase() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			patientService.getMedicalRecordByCase(1,0);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void delete() {
		PatientService patientService = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			patientService.delete(6);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
