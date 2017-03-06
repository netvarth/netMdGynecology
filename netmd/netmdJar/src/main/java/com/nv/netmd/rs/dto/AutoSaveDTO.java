/**
 * QuestionAnswerDTO.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Jun 24, 2014
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class AutoSaveDTO{
	private int  caseId;
	private int answerSetId;
	private int departmentId;
	private List<AnswerDTO> answerDTO=new ArrayList<AnswerDTO>();
	
	
	/**
	 * @return the caseId
	 */
	public int getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the answerSetId
	 */
	public int getAnswerSetId() {
		return answerSetId;
	}
	/**
	 * @param answerSetId the answerSetId to set
	 */
	public void setAnswerSetId(int answerSetId) {
		this.answerSetId = answerSetId;
	}
	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the answerDTO
	 */
	public List<AnswerDTO> getAnswerDTO() {
		return answerDTO;
	}
	/**
	 * @param answerDTO the answerDTO to set
	 */
	public void setAnswerDTO(List<AnswerDTO> answerDTO) {
		this.answerDTO = answerDTO;
	}

	

}
