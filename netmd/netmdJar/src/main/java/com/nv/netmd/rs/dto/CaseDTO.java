/**
 * CaseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 15, 2013
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
public class CaseDTO {
	private int id;
	private int patientId;
	private int branchId;
	private String patientFirstName;
	private String patientLastName;
	private String caseName;
    private String patientType;
    private String admittedDate;
    private int departmentId;
    private String departmentName;
    private float height;
    private float weight;
    private float bmi;
    private float hbCount;
	private String description;
	private String createdDate;
	private int answerSetId;
	private int globalId;
	private String status;
	private String actionName;
    private QuestionAnswerDTO questionAnswerDTO;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * 
	 */
	public CaseDTO() {
		super();
	}

	/**
	 * @param id
	 * @param patientId
	 * @param caseName
	 * @param patientType
	 * @param departmentName
	 * @param date
	 */
	public CaseDTO(int id, int patientId,String patientFirstName,String patientLastName, String caseName, String patientType,
			String departmentName, String createdDate) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientFirstName=patientFirstName;
		this.patientLastName=patientLastName;
		this.caseName = caseName;
		this.patientType = patientType;
		this.departmentName = departmentName;
		this.createdDate = createdDate;
	}
	
	/**
	 * @param id
	 * @param patientId
	 * @param caseName
	 * @param patientType
	 * @param admittedDate
	 * @param departmentId
	 * @param departmentName
	 * @param height
	 * @param weight
	 * @param bmi
	 * @param hbCount
	 * @param description
	 * @param createdDate
	 * @param answerSetId
	 * @param globalId
	 * @param status
	 * @param actionName
	 * @param questionAnswerDTO
	 * @param error
	 * @param success
	 */
	public CaseDTO(int id, int patientId,String patientFirstName,String patientLastName, String caseName, String patientType,
			String admittedDate, int departmentId, String departmentName,
			float height, float weight, float bmi, float hbCount,
			String description, String createdDate, int answerSetId,
			int globalId, String status, String actionName,
			QuestionAnswerDTO questionAnswerDTO, ErrorDTO error, boolean success) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientFirstName = patientFirstName;
		this.patientLastName=patientLastName;
		this.caseName = caseName;
		this.patientType = patientType;
		this.admittedDate = admittedDate;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.height = height;
		this.weight = weight;
		this.bmi = bmi;
		this.hbCount = hbCount;
		this.description = description;
		this.createdDate = createdDate;
		this.answerSetId = answerSetId;
		this.globalId = globalId;
		this.status = status;
		this.actionName = actionName;
		this.questionAnswerDTO = questionAnswerDTO;
		this.error = error;
		this.success = success;
	}

	

	/**
	 * @return the questionAnswerDTO
	 */
	public QuestionAnswerDTO getQuestionAnswerDTO() {
		return questionAnswerDTO;
	}

	/**
	 * @param questionAnswerDTO the questionAnswerDTO to set
	 */
	public void setQuestionAnswerDTO(QuestionAnswerDTO questionAnswerDTO) {
		this.questionAnswerDTO = questionAnswerDTO;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	
	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	/**
	 * @return the caseName
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * @param caseName the caseName to set
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * @return the patientType
	 */
	public String getPatientType() {
		return patientType;
	}

	/**
	 * @param patientType the patientType to set
	 */
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	/**
	 * @return the admittedDate
	 */
	public String getAdmittedDate() {
		return admittedDate;
	}

	/**
	 * @param admittedDate the admittedDate to set
	 */
	public void setAdmittedDate(String admittedDate) {
		this.admittedDate = admittedDate;
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
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * @return the bmi
	 */
	public float getBmi() {
		return bmi;
	}

	/**
	 * @param bmi the bmi to set
	 */
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

	/**
	 * @return the hbCount
	 */
	public float getHbCount() {
		return hbCount;
	}

	/**
	 * @param hbCount the hbCount to set
	 */
	public void setHbCount(float hbCount) {
		this.hbCount = hbCount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
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

}




	