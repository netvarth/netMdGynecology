/**
 * QuestionnaireDaoImpl.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.business.pl.impl;



import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionEnum;
import com.nv.netmd.pl.entity.AnswerTbl;
import com.nv.netmd.pl.entity.CaseAnswerSetTbl;
import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.pl.entity.DepartmentQuestionnaireTbl;
import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.QuestionTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.SyncStatusEnum;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.AnswerDTO;
import com.nv.netmd.rs.dto.AutoSaveDTO;
import com.nv.netmd.rs.dto.AutoSaveResponseDTO;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.CommonSyncResponseDTO;
import com.nv.netmd.rs.dto.Credentials;
import com.nv.netmd.rs.dto.NetMdAnswerSetDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.QuestionAnswerDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.QuestionnaireSyncResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.SyncAnswerDTO;
import com.nv.netmd.rs.dto.SyncResponse;

/**
 *
 *
 * @author Leonora Louis
 */
public class QuestionnaireDaoImpl extends GenericDaoHibernateImpl implements QuestionnaireDao {

	@PersistenceContext()
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.business.pl.dao.QuestionnaireDao#create(com.nv.netmd.rs.dto.QuestionAnswerDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(QuestionAnswerDTO questionAnswer) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		CaseAnswerSetTbl caseAnswerSet=getById(CaseAnswerSetTbl.class,questionAnswer.getAnswerSetId());
		if(caseAnswerSet==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		CaseTbl casetbl=getById(CaseTbl.class,questionAnswer.getCaseId());
		if(casetbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		Map<String, Boolean> indexMap = new HashMap<String, Boolean>();
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(questionAnswer.getDepartmentId(),1);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
			indexMap.put(questionObj.getQuestionKey(), questionObj.getIndexed());
		}
		
		AnswerTbl answerTbl=null;
		QuestionTbl qtable =null;
		for(AnswerDTO answer: questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			String questionId=answer.getQuestionIndex();
		
				AnswerTbl aTbl=getAnswerByQuestion(answer.getQuestionKey(),questionAnswer.getAnswerSetId());
				if (aTbl!=null){
					if(!ans.isEmpty() && !ans.contains("select") && !ans.contains("Not Entered"))
						aTbl.setAnswer(answer.getAnswer());
					else
						aTbl.setAnswer("Unknown");
					update(aTbl);
				}
				else {
					answerTbl= new AnswerTbl();
					qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setIndexKey(questionId);
					answerTbl.setCaseAnswerSetTbl(caseAnswerSet);
					answerTbl.setCaseTbl(casetbl);
					answerTbl.setQuestionTbl(qtable);
					if(!ans.isEmpty() && !ans.contains("select")  && !ans.contains("Not Entered"))
						answerTbl.setAnswer(answer.getAnswer());
					else
						answerTbl.setAnswer("Unknown");
					save(answerTbl);
				}
						 
		}	
		
		
		AnswerTbl answer = (AnswerTbl)  getDelDateUsingQuestionkeyAndAnsSetId(questionAnswer.getAnswerSetId());
		if(answer.getAnswer()!=null){
			if(!answer.getAnswer().equals("Unknown"))
			caseAnswerSet.setAntenatalCreatedDate(answer.getAnswer().trim());
		}		
		save(caseAnswerSet);
		response.setSuccess(true);
		return response;
	 
	}
	

	@Override
	@Transactional(readOnly=false)
	public ResponseDTO AntenatalQuestionnaire(QuestionAnswerDTO questionnaire) throws PersistenceException{
		ResponseDTO response = new ResponseDTO();
		CaseAnswerSetTbl caseAnsSetTbl=new CaseAnswerSetTbl();
	    DepartmentQuestionnaireTbl departmentQstnrTbl=getById(DepartmentQuestionnaireTbl.class,2);
		    if(departmentQstnrTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);

				se.setDisplayErrMsg(true);
				throw se;
		    	
		  }
		Date currentTime=new Date();
		caseAnsSetTbl.setCreatedTime(currentTime); 
		caseAnsSetTbl.setUpdatedTime(currentTime); 
		caseAnsSetTbl.setStatus(StatusEnum.Active);
		caseAnsSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
		caseAnsSetTbl.setAntenatalCreatedDate(currentTime.toString());
		caseAnsSetTbl.setDepartmentQuestionnaireTbl(departmentQstnrTbl);
		caseAnsSetTbl.setGlobalId(0);
		save(caseAnsSetTbl);
		response.setSuccess(true);
		response.setId(caseAnsSetTbl.getId());
		return response;
	}
	
	
	@Override
	@Transactional(readOnly=false)
	
	public ResponseDTO createAntenatalQuestionnaire(QuestionAnswerDTO questionnaire)throws PersistenceException {
        int count=0;
		ResponseDTO response = new ResponseDTO();
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		CaseAnswerSetTbl caseAnsSetTbl=getById(CaseAnswerSetTbl.class, questionnaire.getAnswerSetId());
		if(caseAnsSetTbl==null){
			
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseAnswerSetNotExist);
			se.addParam(new Parameter(Constants.ID, Integer.toString(questionnaire.getAnswerSetId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		DepartmentTbl department=getObstetricsRecord(Constants.OBSTETRICS);
			if(department==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(questionnaire.getAnswerSetId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(department.getId(),2);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
		}
		AnswerTbl answerTbl=null;
		QuestionTbl qtable =null;
		for(AnswerDTO answer:questionnaire.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			if(!ans.isEmpty() && !ans.contains("select")){
				answerTbl= new AnswerTbl();
				qtable =new QuestionTbl();
				qtable.setId(qMap.get(answer.getQuestionKey()));
				answerTbl.setCaseAnswerSetTbl(caseAnsSetTbl);
				answerTbl.setQuestionTbl(qtable);
				answerTbl.setAnswer(answer.getAnswer());
				
			}else{
				
				answerTbl= new AnswerTbl();
			    qtable =new QuestionTbl();
				qtable.setId(qMap.get(answer.getQuestionKey()));
				answerTbl.setCaseAnswerSetTbl(caseAnsSetTbl);
				answerTbl.setQuestionTbl(qtable);
				answerTbl.setAnswer("Unknown");
				
				 }
			    save(answerTbl); 
		}
		response.setSuccess(true);
		response.setId(questionnaire.getAnswerSetId());	
		return response;
	}
	
	private DepartmentTbl getObstetricsRecord(String obstetrics) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_OBSTETRICS_RECORD);
		query.setParameter("param1", obstetrics);
		return executeUniqueQuery(DepartmentTbl.class, query);
	}

	private List<QuestionTbl> getByDeptQuestionnaireId() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_QUESTION_TBL);
		return executeQuery(QuestionTbl.class, query);
	}

	String getMonthForInt(int m) {
	    String month = "invalid";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (m >= 0 && m <= 11 ) {
	        month = months[m];
	    }
	    return month;
	}
	
	
	

	/**
	* @param deptId
	* @param caseId
	* @return
	 * @throws PersistenceException 
	*/
	private List<QuestionTbl> getByDeptIdAndDeptQstnrId(int deptId,int deptQuestionnireId) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_BY_DEPT);
		//query.setParameter("param1", deptId);
		query.setParameter("param2",deptQuestionnireId);
		return executeQuery(QuestionTbl.class, query);
	}

	
	
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}


	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public CaseDTO view(int id,CaseDTO caseDto)throws PersistenceException {
		CaseTbl caseTbl = getById(CaseTbl.class, id);
		if (caseTbl == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		QuestionAnswerDTO questionAns=new QuestionAnswerDTO();
		List<AnswerDTO> answerDTOList = new ArrayList<AnswerDTO>();
		List<AnswerTbl>answerTblList=(ArrayList<AnswerTbl>)getAnswerList(caseTbl.getId());
		CaseAnswerSetTbl caseAnsStTble=getByIdCaseId(caseDto.getId());
		for (AnswerTbl answerTbl : answerTblList) {
				AnswerDTO answerDTO = new AnswerDTO();
				answerDTO.setQuestionKey(answerTbl.getIndexKey());
				answerDTO.setAnswer(answerTbl.getAnswer());
				answerDTOList.add(answerDTO);			
			}		
			questionAns.setCaseId(caseTbl.getId());
			questionAns.setDepartmentId(caseTbl.getDepartmentTbl().getId());
			questionAns.setAnswerDTO(answerDTOList);
			caseDto.setQuestionAnswerDTO(questionAns);
			caseDto.setAnswerSetId(caseAnsStTble.getId());
			return caseDto;
	}

	private CaseAnswerSetTbl getByIdCaseId(int caseId) throws PersistenceException {
		 javax.persistence.Query query = em.createQuery(Query.GET_ANSWER_SET_ID);
		   query.setParameter("param1", caseId);
		   return executeUniqueQuery(CaseAnswerSetTbl.class,query);
	}

	@Override
	public QuestionAnswerDTO viewQuestionnaire(int id)throws PersistenceException {
		CaseAnswerSetTbl caseAnsStTble = getById(CaseAnswerSetTbl.class, id);
		if (caseAnsStTble == null) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.QuestionnaireNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		QuestionAnswerDTO questionAns=new QuestionAnswerDTO();
		List<AnswerDTO> answerDTOList = new ArrayList<AnswerDTO>();
		List<AnswerTbl>answerTblList=(ArrayList<AnswerTbl>)getAnswersLists(caseAnsStTble.getId());
		for (AnswerTbl answerTbl : answerTblList) {
			AnswerDTO answerDTO = new AnswerDTO();
			answerDTO.setQuestionKey(answerTbl.getQuestionTbl()
			.getQuestionKey());
			answerDTO.setAnswer(answerTbl.getAnswer());
			answerDTOList.add(answerDTO);
			}
			questionAns.setAnswerDTO(answerDTOList);
			questionAns.setDepartmentQuestionnaireId(caseAnsStTble.getId());
			//questionAns.setQuestionnare(NtmdQstnrTbl.getId());
			return questionAns;
		 
	}
	

	
	/**
	 * @param id
	 * @param id2
	 * @return
	 * @throws PersistenceException 
	 */
	public List<AnswerTbl> getAnswerList(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_CASE);
		query.setParameter("param1", caseId);
		return executeQuery(AnswerTbl.class, query);
	}

	/**
	 * @param id
	 * @param id2
	 * @return
	 * @throws PersistenceException 
	 */
	public List<AnswerTbl> getAnswersLists(int ansSetId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSLIST_BY_ANSSET_ID);
		query.setParameter("param1", ansSetId);
		return executeQuery(AnswerTbl.class, query);
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseDTO update(QuestionAnswerDTO questionAnswer)throws PersistenceException {
		int count=0;
		ResponseDTO response = new ResponseDTO();
		
		CaseAnswerSetTbl caseAnswerSet=getById(CaseAnswerSetTbl.class,questionAnswer.getAnswerSetId());
		if(caseAnswerSet==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		CaseTbl caseTbl= getById(CaseTbl.class,questionAnswer.getCaseId());
		if(caseTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(questionAnswer.getCaseId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DepartmentTbl departmentTbl= getById(DepartmentTbl.class,questionAnswer.getDepartmentId());
		if(departmentTbl== null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.DepartmentNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(questionAnswer.getCaseId()==0){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}

		
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		Map<String, Boolean> indexMap = new HashMap<String, Boolean>();
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(questionAnswer.getDepartmentId(),1);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
			indexMap.put(questionObj.getQuestionKey(), questionObj.getIndexed());
		}
		AnswerTbl answerTbl=null;
		QuestionTbl qtable =null;
		for(AnswerDTO answer: questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
				AnswerTbl aTbl=getAnswerByQuestionkeyAndAnsSetId(answer.getQuestionIndex(),questionAnswer.getAnswerSetId());
				if (aTbl!=null){
					if(!ans.isEmpty() && !ans.contains("select")  && !ans.contains("Not Entered"))
						aTbl.setAnswer(answer.getAnswer());
					else
						aTbl.setAnswer("Unknown");
					update(aTbl);
				}
				else {
					answerTbl= new AnswerTbl();
					qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setIndexKey(answer.getQuestionIndex());
					answerTbl.setCaseAnswerSetTbl(caseAnswerSet);
					answerTbl.setCaseTbl(caseTbl);
					answerTbl.setQuestionTbl(qtable);
					if(!ans.isEmpty() && !ans.contains("select")  && !ans.contains("Not Entered"))
						answerTbl.setAnswer(answer.getAnswer());
					else
						answerTbl.setAnswer("Unknown");
					save(answerTbl);
				}
						 
		}
		AnswerTbl answer = (AnswerTbl)  getDelDateUsingQuestionkeyAndAnsSetId(questionAnswer.getAnswerSetId());
		if(answer.getAnswer()!=null){
			if(!answer.getAnswer().equals("Unknown"))
				caseAnswerSet.setAntenatalCreatedDate(answer.getAnswer().trim());
		}		
		update(caseAnswerSet);
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;
	}

	private AnswerTbl getAnswerByQuestion(String qid,int answerSetId) throws PersistenceException{
		   javax.persistence.Query query = em.createQuery(Query.GET_ANSWER_BY_QUESTION_INDEX_ANSWERSETID);
		   query.setParameter("param1", qid);
		   query.setParameter("param2",answerSetId);
		   return executeUniqueQuery(AnswerTbl.class,query);
	   }

	
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateQuestionnaire(QuestionAnswerDTO questionAnswer)throws PersistenceException {
		int count=0;
		ResponseDTO response = new ResponseDTO();
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		
		CaseAnswerSetTbl caseAnswerSet=getById(CaseAnswerSetTbl.class,questionAnswer.getAnswerSetId());
		if(caseAnswerSet==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<AnswerTbl> answerlist=getAnswersLists(caseAnswerSet.getId());
		for(AnswerTbl answer:answerlist){
			delete(answer);
		}
		DepartmentTbl department=getObstetricsRecord(Constants.OBSTETRICS);
			if(department==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentNotFound);
				se.addParam(new Parameter(Constants.ID, Integer.toString(department.getId())));
				se.setDisplayErrMsg(true);
				throw se;
		}
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(department.getId(),2);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
		}
			AnswerTbl answerTbl=null;
			QuestionTbl qtable =null;
			for(AnswerDTO answer:questionAnswer.getAnswerDTO()){
				String ans=answer.getAnswer().trim();
				if(!ans.isEmpty() && !ans.contains("select")){
					answerTbl= new AnswerTbl();
					qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setCaseAnswerSetTbl(caseAnswerSet);
					answerTbl.setQuestionTbl(qtable);
					answerTbl.setAnswer(answer.getAnswer());
					
				}else{
					
					answerTbl= new AnswerTbl();
				    qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setCaseAnswerSetTbl(caseAnswerSet);
					answerTbl.setQuestionTbl(qtable);
					answerTbl.setAnswer("Unknown");
					
					 }
				    save(answerTbl); 
		}
		Date currentTime=new Date();
		//SyncTbl syncTbl=getSyncTbl();
		//caseAnswerSet.setUpdatedTime(syncTbl.getUploadedTime());
		caseAnswerSet.setUpdatedTime(currentTime);
		caseAnswerSet.setSyncStatus(SyncStatusEnum.CHANGED);
		update(caseAnswerSet);
		response.setSuccess(true);
		response.setId(questionAnswer.getAnswerSetId());		
		return response;
	}

	
	
	

	@Override
	@Transactional(readOnly = false)
	public boolean autoSave(QuestionAnswerDTO questionAnswer)throws PersistenceException {

		AutoSaveResponseDTO response=new AutoSaveResponseDTO();
		CaseAnswerSetTbl caseAnswerSet=getById(CaseAnswerSetTbl.class,questionAnswer.getAnswerSetId());
		if(caseAnswerSet==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		CaseTbl casetbl=getById(CaseTbl.class,questionAnswer.getCaseId());
		if(casetbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		Map<String, Boolean> indexMap = new HashMap<String, Boolean>();
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(questionAnswer.getDepartmentId(),1);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
			indexMap.put(questionObj.getQuestionKey(), questionObj.getIndexed());
		}
		
		AnswerTbl answerTbl=null;
		QuestionTbl qtable =null;
		for(AnswerDTO answer: questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			String questionId=answer.getQuestionIndex();
		
				AnswerTbl aTbl=getAnswerByQuestion(answer.getQuestionIndex(),questionAnswer.getAnswerSetId());
				if (aTbl!=null){
					if(!ans.isEmpty() && !ans.contains("select") && !ans.contains("Not Entered"))
						aTbl.setAnswer(answer.getAnswer());
					else
						aTbl.setAnswer("Unknown");
					update(aTbl);
				}
				else {
					answerTbl= new AnswerTbl();
					qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setIndexKey(questionId);
					answerTbl.setCaseAnswerSetTbl(caseAnswerSet);
					answerTbl.setCaseTbl(casetbl);
					answerTbl.setQuestionTbl(qtable);
					if(!ans.isEmpty() && !ans.contains("select") && !ans.contains("Not Entered"))
						answerTbl.setAnswer(answer.getAnswer());
					else
						answerTbl.setAnswer("Unknown");
					save(answerTbl);
				}
						 
		}
		AnswerTbl answer = (AnswerTbl)  getDelDateUsingQuestionkeyAndAnsSetId(questionAnswer.getAnswerSetId());
		if(answer.getAnswer()!=null){
			if(!answer.getAnswer().equals("Unknown"))
			caseAnswerSet.setAntenatalCreatedDate(answer.getAnswer().trim());
		}		
		save(caseAnswerSet);
		return true; 
	}
	

	private AnswerTbl getDelDateUsingQuestionkeyAndAnsSetId(int AnswerSetId) throws PersistenceException { 
		int questionKey = 60;
		javax.persistence.Query query = em.createQuery(Query.GET_DELIVERY_DATE_FROM_ANSWER_TBL);
		query.setParameter("param1", AnswerSetId);
		query.setParameter("param2", questionKey);
		query.setMaxResults(1);
		return executeUniqueQuery(AnswerTbl.class, query);
	}
	
	
	
	/**
	 * autoSaveEachField 
	 * @param questionAnswer
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public AutoSaveResponseDTO AutoSaveEachField(AutoSaveDTO questionAnswer)throws PersistenceException {
		
		AutoSaveResponseDTO response=new AutoSaveResponseDTO();
		
		CaseAnswerSetTbl caseAnserSetTbl;
		Boolean flag=false;
		if(questionAnswer.getAnswerSetId()!=0){
			caseAnserSetTbl=getById(CaseAnswerSetTbl.class,questionAnswer.getAnswerSetId());
			 flag=true;
		}else{
			caseAnserSetTbl= new CaseAnswerSetTbl();	
		}
		
		if(caseAnserSetTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseAnswerSetNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}	
		CaseTbl casetbl=getById(CaseTbl.class,questionAnswer.getCaseId());
		if(casetbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.CaseNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		Map<String, Boolean> indexMap = new HashMap<String, Boolean>();
		List<QuestionTbl> questionList= getByDeptIdAndDeptQstnrId(questionAnswer.getDepartmentId(),1);
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
			indexMap.put(questionObj.getQuestionKey(), questionObj.getIndexed());
		}
		
		AnswerTbl answerTbl=null;
		QuestionTbl qtable =null;
		for(AnswerDTO answer: questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			String questionId=answer.getQuestionIndex();
		
				AnswerTbl aTbl=getAnswerByQuestion(answer.getQuestionIndex(),questionAnswer.getAnswerSetId());
				if (aTbl!=null){
					if(!ans.isEmpty() && !ans.contains("select") && !ans.contains("Not Entered"))
						aTbl.setAnswer(answer.getAnswer());
					else
						aTbl.setAnswer("Unknown");
					update(aTbl);
				}
				else {
					answerTbl= new AnswerTbl();
					qtable =new QuestionTbl();
					qtable.setId(qMap.get(answer.getQuestionKey()));
					answerTbl.setIndexKey(questionId);
					answerTbl.setCaseAnswerSetTbl(caseAnserSetTbl);
					answerTbl.setCaseTbl(casetbl);
					answerTbl.setQuestionTbl(qtable);
					if(!ans.isEmpty() && !ans.contains("select") && !ans.contains("Not Entered"))
						answerTbl.setAnswer(answer.getAnswer());
					else
						answerTbl.setAnswer("Unknown");
					save(answerTbl);
				}
						 
		}
		AnswerTbl answer = (AnswerTbl)  getDelDateUsingQuestionkeyAndAnsSetId(questionAnswer.getAnswerSetId());
		if(answer!=null){
			if(!answer.equals("Unknown"))
				caseAnserSetTbl.setAntenatalCreatedDate(answer.getAnswer().trim());
			}		
		 if(flag==true)
				update(caseAnserSetTbl);
		    else
		    	save(caseAnserSetTbl);
		response.setSuccess(true);
		return response;
	 
	}
	
	
	/**
	 * delete Questionnaire
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteQuestionnaire(int id)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		CaseAnswerSetTbl caseAnsSetTbl=getById(CaseAnswerSetTbl.class,id);
		if(caseAnsSetTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.QuestionnaireNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<AnswerTbl> answerlist=getAnswersLists(id);
		for(AnswerTbl answer:answerlist){
			delete(answer);
		}
		caseAnsSetTbl.setStatus(StatusEnum.Inactive);
		caseAnsSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
		update(caseAnsSetTbl);
		response.setSuccess(true);
		return response;
	}
	
//	@Override
//	@Transactional(readOnly=false)
//	public List<QuestionAnswerDTO> getNewQuestionnaire() {
//		List<CaseAnswerSetTbl>questnListTbl=(ArrayList<CaseAnswerSetTbl>)getNewlyCreatedQuestionnaire();
//		List<QuestionAnswerDTO>qstnList=new ArrayList<QuestionAnswerDTO>();
//		if(!questnListTbl.isEmpty()){
//			for (CaseAnswerSetTbl questionnaireListTbl : questnListTbl) {
//				
//				QuestionAnswerDTO qstnrDto=getQuestionnaireDetail(questionnaireListTbl);
//				
//				qstnList.add(qstnrDto);
//			}		
//		}
//		return qstnList;
//	}
//	
//	private List<CaseAnswerSetTbl> getNewlyCreatedQuestionnaire() {
//		javax.persistence.Query query = em
//				.createQuery(Query.GET_NEW_QUESTIONNAIRE);
//		query.setParameter("param1", 0);
//		return executeQuery(CaseAnswerSetTbl.class, query);
//	}
//	
//	@Override
//	@Transactional(readOnly=false)
//	public 	NetmdQuestionAnswerBundle getQuestionnaireBundle() {
//		
//		NetmdQuestionAnswerBundle bundle = getAnswerSetBundle();
//		return bundle;
//		
//	}
	
	@Override
	@Transactional(readOnly=false)
	public NetmdQuestionAnswerBundle getQuestionnaireBundle()throws PersistenceException {
		NetmdQuestionAnswerBundle bundle=new NetmdQuestionAnswerBundle();
		List<NetMdAnswerSetDTO> ansSetDto = getAnswerSetBundle();
		if(ansSetDto!=null)
			bundle.setNetMdAnswerSetList(ansSetDto);
		bundle.setCredentials(getCredentials());
		bundle.setQuestionnaire(Constants.OBSTERIC_SURVEY);
		bundle.setDepartment(Constants.OBSTETRICS);
		return bundle;
	}
	

	@Transactional(readOnly=false)
	public 	List<NetMdAnswerSetDTO>  getAnswerSetBundle()throws PersistenceException {
		
		List<CaseAnswerSetTbl>caseAnsSetList=(ArrayList<CaseAnswerSetTbl>)getQuestionnaireList();
		List<NetMdAnswerSetDTO>ansSetList=new ArrayList<NetMdAnswerSetDTO>();
		if(!caseAnsSetList.isEmpty()){
			for (CaseAnswerSetTbl questionnaireListTbl : caseAnsSetList) {
			boolean mandatory = true;
				List<QuestionTbl> mandatoryQuesList =  (ArrayList<QuestionTbl>)getMandatoryQuestionList();
				
				for(QuestionTbl qstntbl:mandatoryQuesList){
				AnswerTbl ansTbl = (AnswerTbl)  getMandatoryAnswerList(qstntbl.getQuestionKey(),questionnaireListTbl.getId());
				if(ansTbl==null ||ansTbl.getAnswer().equals(Constants.UNKNOWN))
					mandatory=false;		
				}	
		if(mandatory==true){
				NetMdAnswerSetDTO answerSet=new NetMdAnswerSetDTO();
				if(questionnaireListTbl.getGlobalId()!=0){
					
					if(questionnaireListTbl.getSyncStatus()==SyncStatusEnum.CHANGED){
						
						if(questionnaireListTbl.getStatus()==StatusEnum.Inactive){
							answerSet.setActionName(ActionEnum.DELETE);
							answerSet.setAnswerSetGlobalId(questionnaireListTbl.getGlobalId());
							 answerSet.setAnswerSetLocalId(questionnaireListTbl.getId());
						  }
						else{
							answerSet.setActionName(ActionEnum.UPDATE);	
						    answerSet.setAnswerSetGlobalId(questionnaireListTbl.getGlobalId());
						    answerSet.setAnswerSetLocalId(questionnaireListTbl.getId());
							}
					}
				}
				else{
					answerSet.setActionName(ActionEnum.CREATE);	
			        answerSet.setAnswerSetLocalId(questionnaireListTbl.getId()) ;  
					}
				 if(questionnaireListTbl.getAntenatalCreatedDate()!=null)
			         answerSet.setQuestionRelevantDate(questionnaireListTbl.getAntenatalCreatedDate().toString());
				 
					List<SyncAnswerDTO> ans=getQuestionnaireDetail(questionnaireListTbl.getId());
					answerSet.setAnswers(ans);
					Credentials credentials=this.getCredentials();
					answerSet.setBranchId(credentials.getBranchId());
					ansSetList.add(answerSet);	
				}
			}	
			changeQuestionnaireStatus(caseAnsSetList);	
	}
			
			return ansSetList;
				
		}
	
	private List<QuestionTbl> getMandatoryQuestionList() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_MANDATORY_QUESTION_LIST);
		query.setParameter("param1",true);
		return executeQuery(QuestionTbl.class, query);
	}

	private AnswerTbl getMandatoryAnswerList(String questionKey,int answerSetId) throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_MANDATORY_ANSWER_LIST);
		query.setParameter("param1",questionKey);
		query.setParameter("param2",answerSetId);
		return executeUniqueQuery(AnswerTbl.class, query);
	}
	
	private List<SyncAnswerDTO> getQuestionnaireDetail(Integer id) throws PersistenceException{
		List<AnswerTbl>answerTblList=(ArrayList<AnswerTbl>)getAnswersLists(id);
		List<SyncAnswerDTO> ansListDto=new ArrayList<SyncAnswerDTO>();
		if(answerTblList==null)
			throw new PersistenceException(ErrorCodeEnum.AnswerListNull);
		for (AnswerTbl answerTbl : answerTblList) {
			SyncAnswerDTO answerDTO = new SyncAnswerDTO();
				answerDTO.setQuestionKey(answerTbl.getQuestionTbl().getQuestionKey());
				answerDTO.setAnswer(answerTbl.getAnswer());
				if(answerTbl.getQuestionTbl().getIndexed()==true){
					String[] indexArray=answerTbl.getIndexKey().split("_");
					int index=Integer.parseInt(indexArray[1]);
					answerDTO.setIndex(index);
				}
				
				ansListDto.add(answerDTO);
			}
			return ansListDto; 
	}

	private List<CaseAnswerSetTbl> getQuestionnaireList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUESTIONNAIRE_LIST);
		query.setParameter("param1",SyncStatusEnum.CHANGED);
		query.setMaxResults(1);
		return executeQuery(CaseAnswerSetTbl.class, query);
	}
	
	@Transactional(readOnly=false)
		private void changeQuestionnaireStatus(List<CaseAnswerSetTbl> caseAnsSetListTbl) throws PersistenceException {
			for (CaseAnswerSetTbl caseAnstbl : caseAnsSetListTbl){
			caseAnstbl.setSyncStatus(SyncStatusEnum.INQUEUE);	
			update(caseAnstbl);
		}
	}
	
	@Transactional(readOnly=false)
	public void changeAnswerSetStatusOnErrorResponse(int answerSetId) throws PersistenceException{
		CaseAnswerSetTbl caseAnstbl = (CaseAnswerSetTbl) changeAnswerSetStatus(answerSetId);
		caseAnstbl.setSyncStatus(SyncStatusEnum.CHANGED);
		update(caseAnstbl);
	}
	
	@Transactional(readOnly=false)
	public void changeAnswerSetStatusOnErrorResponse() throws PersistenceException{
		List<CaseAnswerSetTbl> caseAnstList = changeStatus();
		for(CaseAnswerSetTbl caseAnstbl:caseAnstList){
			caseAnstbl.setSyncStatus(SyncStatusEnum.CHANGED);
			update(caseAnstbl);
		}	
	}
	


	private List<CaseAnswerSetTbl> changeStatus() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_WITH_INQUEUE_STATUS);
		query.setParameter("param1",SyncStatusEnum.INQUEUE);
		return executeQuery(CaseAnswerSetTbl.class, query);
		
	}


	public CaseAnswerSetTbl changeAnswerSetStatus(int answerSetId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_WITH_ID);
		query.setParameter("param1", answerSetId);
		return executeUniqueQuery(CaseAnswerSetTbl.class, query);
	}
		
	
//	@Override
//	public List<QuestionAnswerDTO> getUpdatedQuestionnaire() {
//		List<QuestionAnswerDTO>questionList=new ArrayList<QuestionAnswerDTO>();
//		SyncTbl syncTbl=getSyncTbl();
//		if(syncTbl!=null){					
//			List<CaseAnswerSetTbl>questionnaireListTbl=(ArrayList<CaseAnswerSetTbl>)getUpdatedQuestionnaireList(syncTbl.getUploadedTime());
//
//			if(!questionnaireListTbl.isEmpty()){
//				for (CaseAnswerSetTbl nmdQstnnaireTbl : questionnaireListTbl) {
//					QuestionAnswerDTO qstndto=getQuestionnaireDetail(nmdQstnnaireTbl);
//					questionList.add(qstndto);			
//				    }
//			}
//		}
//		return questionList;
//	}
//
//	private List<CaseAnswerSetTbl> getUpdatedQuestionnaireList(Date uploadedTime) {
//		
//		javax.persistence.Query query = em
//				.createQuery(Query.GET_UPDATED_QUESTIONNAIRE);
//		query.setParameter("param1", uploadedTime);
//		return executeQuery(CaseAnswerSetTbl.class, query);
//	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}
	
	/**
	 * update with the global id for newly created questionnaire 
	 * @param questionnaireResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void addQuestionnaireSyncResponse(SyncResponse questResponse) throws PersistenceException{
		if(questResponse.getStatusCode()!="500"){
			CaseAnswerSetTbl caseAnsTbl=(CaseAnswerSetTbl)getById(CaseAnswerSetTbl.class,questResponse.getLocalId());
			if (caseAnsTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.QuestionnaireNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(questResponse.getLocalId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			caseAnsTbl.setGlobalId(questResponse.getGlobalId());
			
			List<CaseAnswerSetTbl>caseAnsSetList=(ArrayList<CaseAnswerSetTbl>)getByStatus();
			
			for(CaseAnswerSetTbl caseAnsObj: caseAnsSetList){
				caseAnsObj.setSyncStatus(SyncStatusEnum.NOCHANGE);
			}
			
			update(caseAnsTbl);
		}
	}

	private List<CaseAnswerSetTbl> getByStatus() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUESTIONNAIRE_LIST);
		query.setParameter("param1", SyncStatusEnum.INQUEUE);
		return executeQuery(CaseAnswerSetTbl.class, query);
	}

	/**
	 *update the updated time of updated questionnaire if error occur in sync
	 * @param questionnaireResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateQuestionnaireSyncResponse(SyncResponse questResponse) throws PersistenceException{
		SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(questResponse.getStatusCode()!="500"){
			CaseAnswerSetTbl caseAnsTbl=(CaseAnswerSetTbl)getById(CaseAnswerSetTbl.class,questResponse.getLocalId());
			if (caseAnsTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.QuestionnaireNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(questResponse.getLocalId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			List<CaseAnswerSetTbl>caseAnsSetList=(ArrayList<CaseAnswerSetTbl>)getByStatus();
			for(CaseAnswerSetTbl caseAnsObj: caseAnsSetList){
				caseAnsObj.setSyncStatus(SyncStatusEnum.NOCHANGE);
			}
			update(caseAnsTbl);
		}

	}
	
	/**
	 *update the updated time of updated questionnaire if error occur in sync
	 * @param questionnaireResponse
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteQuestionnaireSyncResponse(SyncResponse questResponse)throws PersistenceException {
		SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		if(questResponse.getStatusCode()!="500"){
			
			List<AnswerTbl> answerlist=getAnswersLists(questResponse.getLocalId());
			for(AnswerTbl answer:answerlist){
				delete(answer);
			}
			
			CaseAnswerSetTbl caseAnsTbl=(CaseAnswerSetTbl)getById(CaseAnswerSetTbl.class,questResponse.getLocalId());
			if (caseAnsTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.QuestionnaireNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(questResponse.getLocalId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			delete(caseAnsTbl);	
			
			
			CaseTbl caseTbl=(CaseTbl)getById(CaseTbl.class,caseAnsTbl.getCaseTbl().getId());
			if (caseTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.CaseNotFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(caseAnsTbl.getCaseTbl().getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			delete(caseTbl);
			
			
		}

	}

	@Override
	@Transactional(readOnly=false)
	public AutoSaveResponseDTO createAnswerSet(int caseId, int ansSetId)throws PersistenceException {
		
		AutoSaveResponseDTO response = new AutoSaveResponseDTO();
		Date currentTime=new Date();
		CaseAnswerSetTbl caseAnswerSetTbl;
		
		CaseTbl casetbl=getById(CaseTbl.class,caseId);
		 if(casetbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.CaseNotFound);
				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		 DepartmentQuestionnaireTbl departmentQstnrTbl=getById(DepartmentQuestionnaireTbl.class,1);
		    if(departmentQstnrTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);

				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		    
		    Boolean flag=false;
			if(ansSetId!=0){
				caseAnswerSetTbl= getById(CaseAnswerSetTbl.class,ansSetId);
				 flag=true;
			}
			else{
				caseAnswerSetTbl= new CaseAnswerSetTbl();
			}
		    
		    caseAnswerSetTbl.setCreatedTime(currentTime);
		    caseAnswerSetTbl.setUpdatedTime(currentTime);
		    caseAnswerSetTbl.setAntenatalCreatedDate(null);
		    caseAnswerSetTbl.setDepartmentQuestionnaireTbl(departmentQstnrTbl);
		    caseAnswerSetTbl.setStatus(StatusEnum.Active);
		    caseAnswerSetTbl.setCaseTbl(casetbl);	 
		    caseAnswerSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
		    if(flag==true)
				update(caseAnswerSetTbl);
		    else{
		    	caseAnswerSetTbl.setGlobalId(0);
		    	save(caseAnswerSetTbl);
		    }
		    
        response.setAnsSetId(caseAnswerSetTbl.getId());
		response.setSuccess(true);	
		return response;
	}
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateAnswerSet(int caseId)throws PersistenceException {
		ResponseDTO response = new ResponseDTO();
		Date currentTime=new Date();
		
		
		CaseTbl casetbl=getById(CaseTbl.class,caseId);
		 if(casetbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.CaseNotFound);
				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		 DepartmentQuestionnaireTbl departmentQstnrTbl=getById(DepartmentQuestionnaireTbl.class,1);
		    if(departmentQstnrTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);

				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		    
		   // CaseAnswerSetTbl caseAnswerSetTbl=getAnswerSetByCaseId(caseId);
		    CaseAnswerSetTbl caseAnswerSetTbl = (CaseAnswerSetTbl)  getAnswerSetByCaseId(caseId);
		    if(caseAnswerSetTbl==null){
		    	PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DepartmentQuestionnaireNotFound);

				se.setDisplayErrMsg(true);
				throw se;
		    	
		    }
		    caseAnswerSetTbl.setUpdatedTime(currentTime);
		    caseAnswerSetTbl.setDepartmentQuestionnaireTbl(departmentQstnrTbl);
		    caseAnswerSetTbl.setCaseTbl(casetbl);	 
		    caseAnswerSetTbl.setSyncStatus(SyncStatusEnum.CHANGED);
		    update(caseAnswerSetTbl);
		    
        response.setId(caseAnswerSetTbl.getId());
		response.setSuccess(true);	
		return response;
	}

	public CaseAnswerSetTbl getAnswerSetByCaseId(int caseId) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER_SET_ID);
		query.setParameter("param1", caseId);
		return executeUniqueQuery(CaseAnswerSetTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public ResponseDTO DeleteEachRow(QuestionAnswerDTO rowDetails) throws PersistenceException {
		
		ResponseDTO response =new ResponseDTO();
		for(AnswerDTO answer: rowDetails.getAnswerDTO()){
			
		AnswerTbl aTbl=getAnswerByQuestionkeyAndCaseId(answer.getQuestionIndex(),rowDetails.getCaseId());
			if(aTbl!=null){
				aTbl.setAnswer("Deleted");
				update(aTbl);
			}
		}		
		return response;
	}

	private AnswerTbl getAnswerByQuestionkeyAndAnsSetId(String qIndex,int answerSetId) throws PersistenceException{
		   javax.persistence.Query query = em.createQuery(Query.GET_ANSWER_BY_QUESTION_INDEX_ANSWERSETID);
		   query.setParameter("param1", qIndex);
		   query.setParameter("param2",answerSetId);
		   query.setMaxResults(1);
		   return executeUniqueQuery(AnswerTbl.class,query);
	   }	
	
	private AnswerTbl getAnswerByQuestionkeyAndCaseId(String qIndex,int caseId) throws PersistenceException{
		   javax.persistence.Query query = em.createQuery(Query.GET_ANSWER_BY_QSTNINDX_CASEID);
		   query.setParameter("param1", qIndex);
		   query.setParameter("param2",caseId);
		   query.setMaxResults(1);
		   return executeUniqueQuery(AnswerTbl.class,query);
	   }	
	
}
