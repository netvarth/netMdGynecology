/**
 * QuestionnaireResource.java
 * 
 * @Author Nithesh Mohanan
 *
 * Version 1.0 Feb 24, 2014
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
import com.nv.netmd.business.bl.service.QuestionnaireService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.common.ServiceExceptionHandler;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AnswerDTO;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.QuestionnaireListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.User;



/**
 * 
 */
@Controller
@RequestMapping("ui/questionnaire/")
public class QuestionnaireResource extends ServiceExceptionHandler{
	private QuestionnaireService questionnaireService;

	/**
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "AntenatalQuestionnaire", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO AntenatalQuestionnaire(@RequestBody QuestionAnswerDTO questionnaire) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {			
			if(user.isPrimaryDevice()==true){
				response = questionnaireService.AntenatalQuestionnaire(questionnaire);
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
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "AutoSaveEachField", method = RequestMethod.POST)
	@ResponseBody
	public AutoSaveResponseDTO AutoSaveEachField(@RequestBody AutoSaveDTO questionnaire) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		AutoSaveResponseDTO response = new AutoSaveResponseDTO();
		try {			
			if(user.isPrimaryDevice()==true){
				response = questionnaireService.AutoSaveEachField(questionnaire);
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
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "DeleteEachRow", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO DeleteEachRow(@RequestBody QuestionAnswerDTO rowDetails) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {			
			if(user.isPrimaryDevice()==true){
				response = questionnaireService.DeleteEachRow(rowDetails);
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
	 * patient list
	 * 
	 * @param filterDTO
	 * @return PatientListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public QuestionnaireListResponseDTO getPatientList(
			@RequestBody FilterDTO filterDTO) {
		QuestionnaireListResponseDTO response = new QuestionnaireListResponseDTO();
		try {
			response = questionnaireService.list(filterDTO);
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
	 * Questionnaire view
	 * 
	 * @param id
	 * @return QuestionAnswerDTO
	 */
	@RequestMapping(value = "viewQuestionnaire/{id}", method = RequestMethod.GET)
	@ResponseBody
	public QuestionAnswerDTO viewQuestionnaire (@PathVariable int id ) throws ServiceException, RuntimeException{ 
		return questionnaireService.viewQuestionnaire(id);
	}

	/**
	 * update a created questionnaire
	 * 
	 * @param questionAnswer
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateQuestionnaire", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateQuestionnaire(@RequestBody QuestionAnswerDTO questionAnswer) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO response = new ResponseDTO();
		try {
			if(user.isPrimaryDevice()==true){
				response = questionnaireService.updateQuestionnaire(questionAnswer);
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
	 * delete a patient by giving questionnaire id
	 * @param uid
	 * @return ResponseDTO
	 */
	@RequestMapping(value =  "deleteQuestionnaire/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseDTO deleteQuestionnaire(@PathVariable int id){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user= (User) req.getSession().getAttribute(Constants.USER);
		ResponseDTO  response=new ResponseDTO();
		try{
			if(user.isPrimaryDevice()==true){
				response=questionnaireService.deleteQuestionnaire(id);
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
	 * @return the questionnaireService
	 */
	public QuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	/**
	 * @param questionnaireService the questionnaireService to set
	 */
	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	
}
