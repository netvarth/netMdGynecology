/**
 * ItemListResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 14-Aug-2013
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
public class ItemListResponseDTO {
	private List<ItemDTO> itemList = new ArrayList<ItemDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	/**
	 * @param itemList
	 * @param error
	 * @param success
	 * @param count
	 */
	public ItemListResponseDTO(List<ItemDTO> itemList, ErrorDTO error,
			boolean success, Long count) {
		super();
		this.itemList = itemList;
		this.error = error;
		this.success = success;
		this.count = count;
	}
	/**
	 * 
	 */
	public ItemListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the itemList
	 */
	public List<ItemDTO> getItemList() {
		return itemList;
	}
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<ItemDTO> itemList) {
		this.itemList = itemList;
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
