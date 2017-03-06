/**
 * RoomTypeListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 31, 2013
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
public class RoomTypeListResponseDTO {
	private List<RoomTypeDTO> roomType = new ArrayList<RoomTypeDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param roomType
	 * @param error
	 * @param success
	 * @param count
	 */
	public RoomTypeListResponseDTO(List<RoomTypeDTO> roomType, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.roomType = roomType;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * 
	 */
	public RoomTypeListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the roomType
	 */
	public List<RoomTypeDTO> getRoomType() {
		return roomType;
	}
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(List<RoomTypeDTO> roomType) {
		this.roomType = roomType;
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
