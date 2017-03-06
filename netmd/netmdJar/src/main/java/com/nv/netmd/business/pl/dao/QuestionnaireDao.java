/**
 * QuestionnaireDao.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.pl.dao;

import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SyncResponse;

/**
 *
 *
 * @author Leonora Louis
 */
public interface QuestionnaireDao {
	public boolean autoSave(QuestionAnswerDTO questionAnswer)throws PersistenceException;
	
	public ResponseDTO create(QuestionAnswerDTO questionAnswer)throws PersistenceException;
	
	public CaseDTO view(int id,CaseDTO caseDto)throws PersistenceException;
	
	public ResponseDTO update(QuestionAnswerDTO questionAnswer)throws PersistenceException;
	
	public ResponseDTO AntenatalQuestionnaire(QuestionAnswerDTO questionAnswerDTO)throws PersistenceException;

	public ResponseDTO createAntenatalQuestionnaire(QuestionAnswerDTO questionnaire)throws PersistenceException;

	public QuestionAnswerDTO viewQuestionnaire(int id)throws PersistenceException;
	
	//public NetMdAnswerSetDTO getQuestionnaireDetails(int id);
	
	public ResponseDTO updateQuestionnaire(QuestionAnswerDTO questionAnswer)throws PersistenceException;

	public ResponseDTO deleteQuestionnaire(int id)throws PersistenceException;

	public NetmdQuestionAnswerBundle getQuestionnaireBundle()throws PersistenceException;
	
	//public List<QuestionAnswerDTO> getNewQuestionnaire();

	//public List<QuestionAnswerDTO> getUpdatedQuestionnaire();

	public void addQuestionnaireSyncResponse(SyncResponse questionnaireResponse)throws PersistenceException;

	public void updateQuestionnaireSyncResponse(SyncResponse questionnaireResponse)throws PersistenceException;
	
	public void deleteQuestionnaireSyncResponse(SyncResponse questionnaireResponse)throws PersistenceException;
	
	public AutoSaveResponseDTO createAnswerSet(int caseId, int ansSetId)throws PersistenceException;
	

	public ResponseDTO updateAnswerSet(int caseId)throws PersistenceException;
	
	public void changeAnswerSetStatusOnErrorResponse(int answerSetId)throws PersistenceException;
	
	public void changeAnswerSetStatusOnErrorResponse()throws PersistenceException;

	public AutoSaveResponseDTO AutoSaveEachField(AutoSaveDTO questionnaire)throws PersistenceException;

	public ResponseDTO DeleteEachRow(QuestionAnswerDTO rowDetails)throws PersistenceException;
	
}
