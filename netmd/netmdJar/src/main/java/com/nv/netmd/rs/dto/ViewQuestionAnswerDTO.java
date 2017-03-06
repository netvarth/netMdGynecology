/**
 * ViewNetmdQuestionAnswerDTO.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Feb 26, 2014
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmd.common.Constants;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class ViewQuestionAnswerDTO {
	private int answerSetId;
	private String createdDate;
	private List<AnswerDTO> answerDTO=new ArrayList<AnswerDTO>();
	private ErrorDTO error;
	private boolean success;
	
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
	 * 
	 */
	public ViewQuestionAnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param questionnaireId
	 * @param createdDate
	 * @param answerDTO
	 */
	public ViewQuestionAnswerDTO(int questionnaireId, Date createdDate) {
		super();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		String createdOn = df.format(createdDate);
		this.answerSetId = answerSetId;
		this.createdDate = createdOn;
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

	

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
