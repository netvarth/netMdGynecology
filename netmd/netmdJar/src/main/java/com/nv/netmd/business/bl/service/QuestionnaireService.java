/**
 * QuestionnaireService.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.bl.service;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.QuestionnaireListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.ViewQuestionAnswerDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public interface QuestionnaireService {
	
	public ResponseDTO create(QuestionAnswerDTO questionAnswer) throws ServiceException;
	
	public AutoSaveResponseDTO autoSave(QuestionAnswerDTO questionAnswer) throws ServiceException;
	
	public CaseDTO view(int id,CaseDTO casedto) throws ServiceException;
	
	public ResponseDTO update(QuestionAnswerDTO questionAnswer) throws ServiceException;

	public ResponseDTO AntenatalQuestionnaire(QuestionAnswerDTO questionnaire) throws ServiceException;
	
	public QuestionnaireListResponseDTO list(FilterDTO filterDTO) throws ServiceException;
	
	public QuestionAnswerDTO viewQuestionnaire(int id) throws ServiceException;
	
	public ResponseDTO updateQuestionnaire(QuestionAnswerDTO questionAnswer) throws ServiceException;
	
	public ResponseDTO deleteQuestionnaire(int id) throws ServiceException;
	
	public AutoSaveResponseDTO AutoSaveEachField(AutoSaveDTO questionnaire) throws ServiceException;
	
	public ResponseDTO DeleteEachRow(QuestionAnswerDTO rowDetails) throws ServiceException;
}
