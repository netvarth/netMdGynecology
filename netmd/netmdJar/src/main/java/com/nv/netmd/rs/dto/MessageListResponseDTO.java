/**
* MessageListResponseDTO.java
* 
* @Author Sreeram
*
* Version 1.0 May 2, 2013
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
public class MessageListResponseDTO {
private List<MessageDTO>messageDTO=new ArrayList<MessageDTO>();
private Long count;
private ErrorDTO error;
private boolean success;
/**
 * 
 */
public MessageListResponseDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @return the messageDTO
 */
public List<MessageDTO> getMessageDTO() {
	return messageDTO;
}
/**
 * @param messageDTO the messageDTO to set
 */
public void setMessageDTO(List<MessageDTO> messageDTO) {
	this.messageDTO = messageDTO;
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
