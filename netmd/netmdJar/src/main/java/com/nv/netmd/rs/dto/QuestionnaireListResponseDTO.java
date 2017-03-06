/**
 * QuestionnaireListResponseDTO.java
 * 
 * @Author Nithesh Mohanan
 *
 * Version 1.0 Feb 26, 2014
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class QuestionnaireListResponseDTO {
	private List<NetmdQuestionAnswerDTO> QuestionnaireList = new ArrayList<NetmdQuestionAnswerDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;

	/**
	 * 
	 */
	public QuestionnaireListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the questionnaireList
	 */
	public List<NetmdQuestionAnswerDTO> getQuestionnaireList() {
		return QuestionnaireList;
	}

	/**
	 * @param questionnaireList the questionnaireList to set
	 */
	public void setQuestionnaireList(List<NetmdQuestionAnswerDTO> questionnaireList) {
		QuestionnaireList = questionnaireList;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
