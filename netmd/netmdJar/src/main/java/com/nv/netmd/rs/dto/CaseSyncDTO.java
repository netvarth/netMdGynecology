 /**
* CaseSyncDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 May 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.rs.dto;

public class CaseSyncDTO extends CaseDTO{
	
	private boolean syncStatus;
	private String antenatalDate;
	
	/**
	 * @param id
	 * @param patientId
	 * @param caseName
	 * @param patientType
	 * @param departmentName
	 * @param date
	 */
	public CaseSyncDTO(int id, int patientId,String patientFirstName,String patientLastName, String caseName, String patientType,
			String departmentName, String createdDate,boolean syncStatus,String antenatalDate) {
		
		this.setId(id);
		this.setPatientId(patientId);
		this.setPatientFirstName(patientFirstName);
		this.setPatientLastName(patientLastName);
		this.setCaseName(caseName);
		this.setPatientType(patientType);
		this.setDepartmentName(departmentName);
		this.setCreatedDate(createdDate);
		this.syncStatus=syncStatus;
		this.antenatalDate=antenatalDate;
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
	public CaseSyncDTO(int id, int patientId,String patientFirstName,String patientLastName, String caseName, String patientType,
			String admittedDate, int departmentId, String departmentName,
			float height, float weight, float bmi, float hbCount,
			String description, String createdDate, int answerSetId,
			int globalId, String status, String actionName,
			QuestionAnswerDTO questionAnswerDTO, ErrorDTO error, boolean success,boolean syncStatus, String antenatalDate ) {
		super();
		this.setId(id);
		this.setPatientId(patientId);
		this.setPatientFirstName(patientFirstName);
		this.setPatientLastName(patientLastName);
		this.setCaseName(caseName);
		this.setPatientType(patientType);
		this.setAdmittedDate(admittedDate);
		this.setDepartmentId(departmentId);
		this.setDepartmentName(departmentName);
		this.setHeight(height);
		this.setWeight(weight);
		this.setBmi(bmi);
		this.setHbCount(hbCount);
		this.setDescription(description);
		this.setCreatedDate(createdDate);
		this.setAnswerSetId(answerSetId);
		this.setGlobalId(globalId);
		this.setStatus(status);
		this.setActionName(actionName);
		this.setQuestionAnswerDTO(questionAnswerDTO);
		this.setError(error);
		this.setSuccess(success);
		this.syncStatus=syncStatus;
		this.antenatalDate=antenatalDate;
	}
	
	/**
	 * @return the syncStatus
	 */
	public boolean isSyncStatus() {
		return syncStatus;
	}

	/**
	 * @param syncStatus the syncStatus to set
	 */
	public void setSyncStatus(boolean syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getAntenatalDate() {
		return antenatalDate;
	}

	public void setAntenatalDate(String antenatalDate) {
		this.antenatalDate = antenatalDate;
	}

}
