 /**
* CommonSyncResponseDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Apr 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.rs.dto;

import java.util.List;

import com.nv.netmd.rs.dto.ErrorDTO;

/**
 * @author Nithesh Mohanan
 *
 */
public class CommonSyncResponseDTO {
	
	private List<SyncResponse> responses;
	
	
	
	/**
	 * @return the responses
	 */
	public List<SyncResponse> getResponses() {
		return responses;
	}


	/**
	 * @param responses the responses to set
	 */
	public void setResponses(List<SyncResponse> responses) {
		this.responses = responses;
	}

	
}
