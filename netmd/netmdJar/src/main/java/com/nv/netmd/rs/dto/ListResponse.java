/**
 * ListResponse.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Dec 09, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.dto;

/**
 *
 *
 * @author Nithesh Mohanan
 */
public class ListResponse {
	private Object list;
	private Long count;
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
	/**
	 * @return the list
	 */
	public Object getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(Object list) {
		this.list = list;
	}
	
}
