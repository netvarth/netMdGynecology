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
public class QuestionAnswerDTO extends HeaderDTO {
	private int  caseId;
	private int branchId;
	private int departmentId;
	private int departmentQuestionnaireId;
	private String department;
	private String departmentQuestionnare;
	private int answerSetId;
	private List<AnswerDTO> answerDTO=new ArrayList<AnswerDTO>();
	private List<NetMdAnswerSetDTO> answerSetDTO=new ArrayList<NetMdAnswerSetDTO>();
	
	/**
	 * @return the departmentQuestionnaireId
	 */
	public int getDepartmentQuestionnaireId() {
		return departmentQuestionnaireId;
	}
	/**
	 * @param departmentQuestionnaireId the departmentQuestionnaireId to set
	 */
	public void setDepartmentQuestionnaireId(int departmentQuestionnaireId) {
		this.departmentQuestionnaireId = departmentQuestionnaireId;
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
	public QuestionAnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	 * @return the departmentQuestionnare
	 */
	public String getQuestionnare() {
		return departmentQuestionnare;
	}
	/**
	 * @param questionnare the departmentQuestionnare to set
	 */
	public void setQuestionnare(String questionnare) {
		this.departmentQuestionnare = questionnare;
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
	 * @return the answerSetDTO
	 */
	public List<NetMdAnswerSetDTO> getAnswerSetDTO() {
		return answerSetDTO;
	}
	/**
	 * @param answerSetDTO the answerSetDTO to set
	 */
	public void setAnswerSetDTO(List<NetMdAnswerSetDTO> answerSetDTO) {
		this.answerSetDTO = answerSetDTO;
	}

}
