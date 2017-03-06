/**
 * NetmdQuestionAnswerDTO.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Feb 25, 2014
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
public class NetmdQuestionAnswerDTO {
	
	private int answerSetId;
	private String createdTime;
	private String updatedTime;
	private List<AnswerDTO> answerDTO=new ArrayList<AnswerDTO>();
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @param questionnaireId
	 * @param createdTime
	 * @param updatedTime
	 * @param answerDTO
	 * @param error
	 * @param success
	 */
	public NetmdQuestionAnswerDTO(int answerSetId, Date createdTime,
			Date updatedTime
			) {
	    SimpleDateFormat df=new SimpleDateFormat(Constants.NEW_DATE_FORMAT_WITHOUT_TIME);
		String createdTme = df.format(createdTime);
		String updatedTme = df.format(updatedTime);
	    this.answerSetId = answerSetId;
		this.createdTime = createdTme;
		this.updatedTime = updatedTme;
	
	}
	
	/**
	 * 
	 */
	public NetmdQuestionAnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
		
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
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * @return the updatedTime
	 */
	public String getUpdatedTime() {
		return updatedTime;
	}
	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
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
}
