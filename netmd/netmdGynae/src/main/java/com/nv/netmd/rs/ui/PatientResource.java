/**
 * PatientResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 7, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CaseListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MedicalListResponseDTO;
import com.nv.netmd.rs.dto.MedicalRecordDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.User;

/**
 * 
 */
@Controller
@RequestMapping("ui/patient/")
public class PatientResource {
	private PatientService patientService;

	/**
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody PatientDetail patient) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {			
			if(user.isPrimaryDevice()==true){
				response = patientService.create(patient);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * patient view
	 * 
	 * @param id
	 * @return PatientDTO
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	public PatientDetail view(@PathVariable int id) {
		PatientDetail response = new PatientDetail();
		try {
			response = patientService.view(id);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(true);

		}
		return response;
	}
	/**
	 * delete a patient by giving patient id
	 * @param uid
	 * @return ResponseDTO
	 */
	@RequestMapping(value =  "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseDTO delete(@PathVariable int id){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO  response=new ResponseDTO();
		try{
			if(user.isPrimaryDevice()==true){
				response=patientService.delete(id);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		}
		catch(ServiceException e){

			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}		
		return response;
	}

	/**
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO update(@RequestBody PatientDetail patient) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.update(patient);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * patient list
	 * 
	 * @param filterDTO
	 * @return PatientListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public PatientListResponseDTO getPatientList(
			@RequestBody FilterDTO filterDTO) {
		PatientListResponseDTO response = new PatientListResponseDTO();
		try {
			response = patientService.list(filterDTO);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * 
	 * @param filterDTO
	 * @return CaseListResponseDTO
	 */
	@RequestMapping(value = "listOfCase", method = RequestMethod.POST)
	@ResponseBody
	public CaseListResponseDTO listOfCase(@RequestBody FilterDTO filterDTO) {
		CaseListResponseDTO response = new CaseListResponseDTO();
		try {
			response = patientService.listOfCase(filterDTO);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * list the medical record filter
	 ** 
	 * @param filterDTO
	 * @return MedicalListResponseDTO
	 */
	@RequestMapping(value = "listOfMedicalRecord", method = RequestMethod.POST)
	@ResponseBody
	public MedicalListResponseDTO listOfMedicalRecord(
			@RequestBody FilterDTO filterDTO) {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listOfMedicalRecord(filterDTO);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
	/**
	 * Autosaving Case
	 * 
	 * @param caseDto
	 * @return ResponseDTO
	 */
	
	@RequestMapping(value = "AutoSaveCase", method = RequestMethod.POST)
	@ResponseBody
	public AutoSaveResponseDTO AutoSavingCase(@RequestBody CaseDTO caseDto) {
		AutoSaveResponseDTO response = new AutoSaveResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.AutoSaveCase(caseDto);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}


	
	/**
	 * create a case
	 * 
	 * @param caseDto
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createCase", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createCase(@RequestBody CaseDTO caseDto) {
		ResponseDTO response = new ResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.createCase(caseDto);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * update a created case
	 * 
	 * @param caseDto
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateCase", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateCase(@RequestBody CaseDTO caseDto) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.updateCase(caseDto);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * list all the cases
	 * 
	 * @return CaseListResponseDTO
	 */
	@RequestMapping(value = "listCase", method = RequestMethod.GET)
	@ResponseBody
	public CaseListResponseDTO listCase() {
		CaseListResponseDTO response = new CaseListResponseDTO();
		try {
			response = patientService.listCase();
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * create a medical record for a patient
	 * 
	 * @param record
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createMedicalRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createMedicalRecord(@RequestBody MedicalRecordDTO record) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.createMedicalRecord(record);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * update a medical record for a patient
	 * 
	 * @param record
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateMedicalRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateMedicalRecord(@RequestBody MedicalRecordDTO record) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = patientService.updateMedicalRecord(record);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * list a single patients case
	 * 
	 * @param patientId
	 * @return CaseListResponseDTO
	 */
	@RequestMapping(value = "listCaseByPatient/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public CaseListResponseDTO listCaseByPatient(@PathVariable int patientId) {
		CaseListResponseDTO response = new CaseListResponseDTO();
		try {
			response = patientService.listCaseByPatient(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	} 

	/**
	 * view a case by giving id
	 * 
	 * @param id
	 * @return CaseDTO
	 */
	@RequestMapping(value = "viewCase/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CaseDTO viewCase(@PathVariable int id) {
		CaseDTO response = new CaseDTO();
		try {
			response = patientService.viewCase(id);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(true);

		}
		return response;
	}

	/**
	 * list the medical record of a patient
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 */
	@RequestMapping(value = "listMedicalRecord/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public MedicalListResponseDTO listMedicalRecord(@PathVariable int patientId) {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listMedicalRecord(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * view a medical record by giving id
	 * 
	 * @param id
	 * @return MedicalRecordDTO
	 */
	@RequestMapping(value = "viewMedicalRecord/{id}", method = RequestMethod.GET)
	@ResponseBody
	public MedicalRecordDTO viewMedicalRecord(@PathVariable int id) {
		MedicalRecordDTO response = new MedicalRecordDTO();
		try {
			response = patientService.viewMedicalRecord(id);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * get medical record by giving patient and case
	 * 
	 * @param patientId
	 * @param caseId
	 * @return MedicalListResponseDTO
	 */
	@RequestMapping(value = "getMedicalRecordByCase/{patientId},{caseId}", method = RequestMethod.GET)
	@ResponseBody
	public MedicalListResponseDTO getMedicalRecordByCase(
			@PathVariable int patientId, @PathVariable int caseId) {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.getMedicalRecordByCase(patientId, caseId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
	
	/**
	 * delete a medical record by giving medical record id
	 * @param uid
	 * @return ResponseDTO
	 */
	@RequestMapping(value =  "deleteMedicalRecord/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseDTO deleteMedicalRecord(@PathVariable int id){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO  response=new ResponseDTO();
		try{
			if(user.isPrimaryDevice()==true){
				response=patientService.deleteMedicalRecord(id);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		}
		catch(ServiceException e){

			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}		
		return response;
	}

	/**
	 * delete a case by giving case id
	 * @param uid
	 * @return ResponseDTO
	 */
	@RequestMapping(value =  "deleteCase/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseDTO deleteCase(@PathVariable int id){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO  response=new ResponseDTO();
		try{
			if(user.isPrimaryDevice()==true){
				response=patientService.deleteCase(id);
			}
			else{
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NotPrimaryDevice);

				se.setDisplayErrMsg(true);
				throw se;
			}

		}
		catch(ServiceException e){

			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}		
		return response;
	}
	
	/**
	 * 
	 * @param patientId
	 * @return MedicalListResponseDTO
	 */
	@RequestMapping(value = "listPersonalVisit/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public MedicalListResponseDTO listPersonalVisit(@PathVariable int patientId) {
		MedicalListResponseDTO response = new MedicalListResponseDTO();
		try {
			response = patientService.listPersonalVisit(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * patient list
	 * 
	 * 
	 * @return PatientListResponseDTO
	 */
	@RequestMapping(value = "getPatients", method = RequestMethod.GET)
	@ResponseBody
	public PatientListResponseDTO getPatients() {
		PatientListResponseDTO response = new PatientListResponseDTO();
		try {
			response = patientService.getPatients();
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * @return the patientService
	 */
	public PatientService getPatientService() {
		return patientService;
	}

	/**
	 * @param patientService
	 *            the patientService to set
	 */
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
}
