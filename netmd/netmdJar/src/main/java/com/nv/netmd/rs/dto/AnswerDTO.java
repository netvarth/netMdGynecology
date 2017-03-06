/**
 * AnswerDTO.java
 * @author Leo
 *
 * Version 1.0 Dec 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

/**
 *
 *
 * @author Leonora Louis
 */
public class AnswerDTO {
   private String questionKey;
   private String answer;
   private String questionIndex;
/**
 * 
 */
public AnswerDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @return the questionKey
 */
public String getQuestionKey() {
	return questionKey;
}
/**
 * @param questionKey the questionKey to set
 */
public void setQuestionKey(String questionKey) {
	this.questionKey = questionKey;
}
/**
 * @return the answer
 */
public String getAnswer() {
	return answer;
}
/**
 * @param answer the answer to set
 */
public void setAnswer(String answer) {
	this.answer = answer;
}
/**
 * @return the questionIndex
 */
public String getQuestionIndex() {
	return questionIndex;
}
/**
 * @param questionIndex the questionIndex to set
 */
public void setQuestionIndex(String questionIndex) {
	this.questionIndex = questionIndex;
}

   
   
}
