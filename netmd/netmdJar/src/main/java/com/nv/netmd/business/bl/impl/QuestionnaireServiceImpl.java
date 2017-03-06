/**
 * QuestionnaireServiceImpl.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.business.bl.service.QuestionnaireService;
import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.QuestionnaireListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;


/**
 *
 *
 * @author Leonora Louis
 */
public class QuestionnaireServiceImpl implements QuestionnaireService{
	private QuestionnaireDao questionDao;
	private FilterService questionnaireFilterService;

	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.QuestionnaireService#create(com.nv.netmd.rs.dto.QuestionAnswerDTO)
	 */
	@Override
	public ResponseDTO create(QuestionAnswerDTO questionAnswer) throws ServiceException{
		ResponseDTO answerSetResponse;
		try {
			answerSetResponse = questionDao.createAnswerSet(questionAnswer.getCaseId(),questionAnswer.getAnswerSetId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return answerSetResponse;
	}


	@Override
	public CaseDTO view(int id,CaseDTO caseDto) throws ServiceException {
		
		
		CaseDTO casedto;
		try {
			casedto = questionDao.view(id,caseDto);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return casedto;
	}

	@Override
	public ResponseDTO update(QuestionAnswerDTO questionAnswer) throws ServiceException {
		ResponseDTO answerSetResponse;
		try {
			answerSetResponse = questionDao.updateAnswerSet(questionAnswer.getCaseId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(answerSetResponse.getId()!=0)
			questionAnswer.setAnswerSetId(answerSetResponse.getId());
		ResponseDTO response;
		try {
			response = questionDao.update(questionAnswer);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	@Override
	public ResponseDTO AntenatalQuestionnaire(QuestionAnswerDTO questionAnswerDTO) throws ServiceException {
		ResponseDTO response = new ResponseDTO();
		try {
			response = questionDao.AntenatalQuestionnaire(questionAnswerDTO);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		
		if(response.getId()!=0){
			QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
			 questionAnswer.setAnswerSetId(response.getId());
			 questionAnswer.setAnswerDTO(questionAnswerDTO.getAnswerDTO());
			 try {
				questionDao.createAntenatalQuestionnaire(questionAnswer);
			} catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
		}
		response.setSuccess(true);
		return response;

	}
	
	/**
	 * patient list
	 * 
	 * @param filterDTO
	 * @return QuestionnaireListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public QuestionnaireListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		QuestionnaireListResponseDTO questionnaireList = new QuestionnaireListResponseDTO();
		ErrorDTO error=questionnaireFilterService.validate(filterDTO);
		if (error != null) {
			questionnaireList.setError(error);
			questionnaireList.setSuccess(false);
			return questionnaireList;
		}
		questionnaireList =questionnaireFilterService.list(filterDTO);
		return questionnaireList;
	}
	
	@Override
	public QuestionAnswerDTO viewQuestionnaire(int id) throws ServiceException {
		QuestionAnswerDTO ntmdQstnAnswerDTO;
		try {
			ntmdQstnAnswerDTO = questionDao.viewQuestionnaire(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return ntmdQstnAnswerDTO;
	}
	@Override
	public ResponseDTO updateQuestionnaire(QuestionAnswerDTO questionAnswer) throws ServiceException{
		ResponseDTO response;
		try {
			response = questionDao.updateQuestionnaire(questionAnswer);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	
	/**
	 * @return the questionDao
	 */
	public QuestionnaireDao getQuestionDao() {
		return questionDao;
	}

	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(QuestionnaireDao questionDao) {
		this.questionDao = questionDao;
	}
	

	public FilterService getQuestionnaireFilterService() {
		return questionnaireFilterService;
	}

	public void setQuestionnaireFilterService(FilterService questionnaireFilterService) {
		this.questionnaireFilterService = questionnaireFilterService;
	}	


	/* (non-Javadoc)
	 * @see com.nv.netmd.business.bl.service.QuestionnaireService#AutoSave(com.nv.netmd.rs.dto.QuestionAnswerDTO)
	 */
	@Override
	public AutoSaveResponseDTO autoSave(QuestionAnswerDTO questionAnswer) throws ServiceException {
		
		AutoSaveResponseDTO answerSetResponse;
		try {
			answerSetResponse = questionDao.createAnswerSet(questionAnswer.getCaseId(),questionAnswer.getAnswerSetId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		if(answerSetResponse.getAnsSetId()!=0)
		questionAnswer.setAnswerSetId(answerSetResponse.getAnsSetId());
		boolean suceess;
		try {
			suceess = questionDao.autoSave(questionAnswer);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		answerSetResponse.setSuccess(suceess);
		return answerSetResponse;
	}
	/**
	 * delete Questionnaire
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteQuestionnaire(int id) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		try {
			response=questionDao.deleteQuestionnaire(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}


	@Override
	public AutoSaveResponseDTO AutoSaveEachField(AutoSaveDTO questionnaire) throws ServiceException {
		AutoSaveResponseDTO answerSetResponse;
		try {
			answerSetResponse = questionDao.createAnswerSet(questionnaire.getCaseId(),questionnaire.getAnswerSetId());
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		questionnaire.setAnswerSetId(answerSetResponse.getAnsSetId());
		try {
			questionDao.AutoSaveEachField(questionnaire);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return answerSetResponse;
	}


	@Override
	public ResponseDTO DeleteEachRow(QuestionAnswerDTO rowDetails) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		try{
		response=questionDao.DeleteEachRow(rowDetails);
		}
		 catch (PersistenceException e) {
				throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
			}
		return response;
	}
	
}
