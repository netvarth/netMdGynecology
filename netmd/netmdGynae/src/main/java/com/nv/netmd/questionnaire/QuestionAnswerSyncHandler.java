 /**
* QuestionAnswerSyncHandler.java
* @author Nithesh Mohanan
*
* Version 1.0 Apr 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.questionnaire;

import java.util.List;

import com.nv.netmd.business.pl.dao.QuestionnaireDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionEnum;
import com.nv.netmd.rs.dto.CommonSyncResponseDTO;
import com.nv.netmd.rs.dto.NetmdQuestionAnswerBundle;
import com.nv.netmd.rs.dto.SyncResponse;
import com.nv.netmd.sync.SyncBag;
import com.nv.netmd.sync.SyncListner;

/**
 * @author Nithesh Mohanan
 *
 */
public class QuestionAnswerSyncHandler implements SyncListner{ 

	

	private QuestionnaireDao questionDao;
	private String url;
	
	@SuppressWarnings("unchecked")
	@Override
	public SyncBag<NetmdQuestionAnswerBundle, CommonSyncResponseDTO> getSyncData() throws PersistenceException {
		 SyncBag<NetmdQuestionAnswerBundle, CommonSyncResponseDTO> syncBag = new SyncBag<NetmdQuestionAnswerBundle,CommonSyncResponseDTO>(NetmdQuestionAnswerBundle.class,CommonSyncResponseDTO.class);
		 
		NetmdQuestionAnswerBundle bundle = questionDao.getQuestionnaireBundle();
		if(bundle.getNetMdAnswerSetList().size()!=0)
			syncBag.setInput(bundle);
		syncBag.setSyncUrl(url+"/"+bundle.getDepartment()+"/"+bundle.getQuestionnaire());
		return syncBag;
	}

	@Override
	public  void callback(CommonSyncResponseDTO response) throws PersistenceException {
		
		if(response!=null){
			
		if(!response.getResponses().isEmpty()){
			
			List<SyncResponse> syncResponseList=response.getResponses();
			
			for (SyncResponse questionnaireResponse : syncResponseList) {
				
				if((questionnaireResponse.getStatusCode()=="200" ||questionnaireResponse.getStatusCode().contains("200")) && questionnaireResponse.getGlobalId()!=0){
					
					try{
						if( questionnaireResponse.getActionName().equals(ActionEnum.CREATE)){
							questionDao.addQuestionnaireSyncResponse(questionnaireResponse);
						}
						if( questionnaireResponse.getActionName().equals(ActionEnum.UPDATE)){
							questionDao.updateQuestionnaireSyncResponse(questionnaireResponse);
						}
						if( questionnaireResponse.getActionName().equals(ActionEnum.DELETE)){
							questionDao.deleteQuestionnaireSyncResponse(questionnaireResponse);
						}
	
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					questionDao.changeAnswerSetStatusOnErrorResponse(questionnaireResponse.getLocalId());
				   }
				
			}
		}
		
	}
		
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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

}
