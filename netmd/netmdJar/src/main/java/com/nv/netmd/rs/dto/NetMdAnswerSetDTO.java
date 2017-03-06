/**
 * QuestionAnswerDTO.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmd.pl.entity.ActionEnum;

/**
 *
 *
 * @author Leonora Louis
 */
public class NetMdAnswerSetDTO {
	private String questionRelevantDate;
	private ActionEnum actionName;
	private int branchId;
    private int answerSetLocalId;	
	private int answerSetGlobalId;
	private List<SyncAnswerDTO> answers=new ArrayList<SyncAnswerDTO>();

	/**
	 * @return the questionRelevantDate
	 */
	public String getQuestionRelevantDate() {
		return questionRelevantDate;
	}
	/**
	 * @param questionRelevantDate the questionRelevantDate to set
	 */
	public void setQuestionRelevantDate(String questionRelevantDate) {
		this.questionRelevantDate = questionRelevantDate;
	}
	
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the answerSetLocalId
	 */
	public int getAnswerSetLocalId() {
		return answerSetLocalId;
	}
	/**
	 * @param answerSetLocalId the answerSetLocalId to set
	 */
	public void setAnswerSetLocalId(int answerSetLocalId) {
		this.answerSetLocalId = answerSetLocalId;
	}
	/**
	 * @return the answerSetGlobalId
	 */
	public int getAnswerSetGlobalId() {
		return answerSetGlobalId;
	}
	/**
	 * @param answerSetGlobalId the answerSetGlobalId to set
	 */
	public void setAnswerSetGlobalId(int answerSetGlobalId) {
		this.answerSetGlobalId = answerSetGlobalId;
	}
	/**
	 * @return the actionName
	 */
	public ActionEnum getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(ActionEnum actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the answers
	 */
	public List<SyncAnswerDTO> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<SyncAnswerDTO> answers) {
		this.answers = answers;
	}
	
}
