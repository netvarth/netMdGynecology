/**
* CasePropertyEnum.java
* 
* @Author Nithesh
*
* Version 1.0 Dec 02, 2014
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.util.filter.core.Property;

/**
 *
 */
public enum CasePropertyEnum implements Property,EnumDisplay{
	
	firstName("firstName","firstName","com.nv.netmd.pl.entity.PatientTbl","patientTbl","","Patient Name"),
	caseNumber("caseNumber","id","com.nv.netmd.pl.entity.CaseTbl","","","Case Number"),
	department("department","name","com.nv.netmd.pl.entity.DepartmentTbl","departmentTbl","","Department"),
	syncStatus("syncStatus","syncStatus","com.nv.netmd.pl.entity.CaseAnswerSetTbl","caseAnswerSetTbls","","Sync Status"),
	createdTime("createdTime","createdTime","com.nv.netmd.pl.entity.CaseTbl","","","Created Date"),
	status("status","status","com.nv.netmd.pl.entity.CaseTbl","","","Status"),
	patientId("patientId","id","com.nv.netmd.pl.entity.PatientTbl","patientTbl","","Patient Id"),
	antenatalCreatedDate("antenatalCreatedDate","antenatalCreatedDate","com.nv.netmd.pl.entity.CaseAnswerSetTbl","caseAnswerSetTbls","","Delivery Date"),
	deliveryMonth("deliveryMonth","antenatalCreatedDate","com.nv.netmd.pl.entity.CaseAnswerSetTbl","caseAnswerSetTbls","","Delivery Month"),
	
	indexKey("indexKey","indexKey","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","",""),
	answer("answer","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","",""),
	newAnswer("newAnswer","intAnswer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","",""),
	
	bookedStatus("indexKey-bookedUnbooked-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Booked Status"),
	maternalAge("indexKey-age-answer-Integer","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Maternal Age"),
	maternalHeight("indexKey-height-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Maternal Height"),
	maternalWeight("indexKey-weight-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Maternal Weight"),
	bodymassIndex("indexKey-bodymassIndex-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Body Mass Index"),
	bloodgroup("indexKey-bloodgroup-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Blood Group"),
	parity("indexKey-parity-answer-Integer","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Parity"),
	previousCS("indexKey-previousCS-answer-Integer","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Previous CS"),
	multiplePregnancy("indexKey-multiplePregnancy-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Multiple Pregnancy"),
	help("indexKey-help-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Help"),
	eclampsia("indexKey-eclampsia-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Eclampsia"),
	typeOfDelivery("indexKey-typeOfDelivery-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Type Of Delivery"),
	deliveryType("indexKey-deliveryType-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls",""," Delivery Sub-Type"),
	deliveryName("indexKey-deliveryName-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Delivery Name"),
	babyPresentation("indexKey-babyPresentation_0-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Presentation"),
	episiotomy("indexKey-episiotomy-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Episiotomy"),
	perinealTear("indexKey-perinealTear-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Perineal Tear"),
	induction("indexKey-induction-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Induction"),
	extraOxytoxinUsed("indexKey-extraOxytoxinUsed-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Oxytoxin Used"),
	oxytoxinBolus("indexKey-oxytoxinBolus-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Oxytoxin Bolus"),
	otherOxytoxinUsed("indexKey-otherOxytoxinUsed-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Other Oxytoxin Used"),
	thirdStageDuration("indexKey-thirdStageDuration-answer-Integer","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Third Stage Duration"),
	bloodLoss("indexKey-bloodLoss-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Blood Loss"),
	bloodProduct("indexKey-bloodProduct-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Blood Product"),
	placentalWght("indexKey-placentalWght-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Placental Weight"),
	amtFluids("indexKey-amtFluids-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Amount Of Fluids"),
	isfourthStageMon("indexKey-isfourthStageMon-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Fourth Stage Monitoring"),
	maternalDeath("indexKey-maternalDeath-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Maternal Motality"),
	babyWeight("indexKey-babyWeight_0-answer-Float","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Baby Weight"),
	AntiBioticsUsed("indexKey-AntiBioticsUsed-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","AntiBiotics Used"),
	muscleRelaxants("indexKey-muscleRelaxants-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Muscle Relaxants"),
	fetalstillbirth("indexKey-fetalstillbirth_0-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Fetal Stillbirth"),
	fetalNICU("indexKey-fetalNICU_0-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Fetal NICU"),
	robsonclass("indexKey-robsonclass-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Robsson Class"),
	doctorIncharge("indexKey-doctorIncharge-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Doctor in charge"),
	conductedBy("indexKey-conductedBy-answer-String","answer","com.nv.netmd.pl.entity.AnswerTbl","anwserTbls","","Conducted By")
	;

	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	String parameterName;
	/**
	 * @param displayName
	 * @param fieldName
	 * @param entityName
	 * @param referenceName
	 * @param pathReferenceName
	 */
	private CasePropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName,String parameterName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
		this.parameterName = parameterName;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}
	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the pathReferenceName
	 */
	public String getPathReferenceName() {
		return pathReferenceName;
	}
	/**
	 * @param pathReferenceName the pathReferenceName to set
	 */
	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
	}
	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}
	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
