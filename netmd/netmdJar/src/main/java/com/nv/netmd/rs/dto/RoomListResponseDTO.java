/**
 * RoomListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 30, 2013
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
 * @author Sreeram T G
 */
public class RoomListResponseDTO {
	/**
	 * 
	 */
	public RoomListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private List<RoomDTO> room = new ArrayList<RoomDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param room
	 * @param error
	 * @param success
	 * @param count
	 */
	public RoomListResponseDTO(List<RoomDTO> room, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.room = room;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * @return the room
	 */
	public List<RoomDTO> getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(List<RoomDTO> room) {
		this.room = room;
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
