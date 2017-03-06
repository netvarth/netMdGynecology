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
import java.util.List;

/**
 *
 *
 * @author Leonora Louis
 */
public class NetmdQuestionAnswerBundle  {
	
	private Credentials credentials;
	private String department;
	private String questionnaire;
	private List<NetMdAnswerSetDTO> netMdAnswerSetList=new ArrayList<NetMdAnswerSetDTO>();
	/**
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the questionnaire
	 */
	public String getQuestionnaire() {
		return questionnaire;
	}
	/**
	 * @param questionnaire the questionnaire to set
	 */
	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}
	/**
	 * @return the netMdAnswerSetList
	 */
	public List<NetMdAnswerSetDTO> getNetMdAnswerSetList() {
		return netMdAnswerSetList;
	}
	/**
	 * @param netMdAnswerSetList the netMdAnswerSetList to set
	 */
	public void setNetMdAnswerSetList(List<NetMdAnswerSetDTO> netMdAnswerSetList) {
		this.netMdAnswerSetList = netMdAnswerSetList;
	}
	
	
	
}
