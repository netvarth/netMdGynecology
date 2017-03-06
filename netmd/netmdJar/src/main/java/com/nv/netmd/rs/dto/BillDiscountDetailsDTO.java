/**
 * BillDiscountDetailsDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 29-Aug-2013
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
public class BillDiscountDetailsDTO {
	private String billUid;
	private List<BillDiscountDTO>billDiscountList=new ArrayList<BillDiscountDTO>();
	private float billAmount;
	/**
	 * @param billDiscountList
	 * @param billAmount
	 */
	public BillDiscountDetailsDTO(List<BillDiscountDTO> billDiscountList,
			float billAmount) {
		super();
		this.billDiscountList = billDiscountList;
		this.billAmount = billAmount;
	}
	/**
	 * 
	 */
	public BillDiscountDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the billDiscountList
	 */
	public List<BillDiscountDTO> getBillDiscountList() {
		return billDiscountList;
	}
	/**
	 * @param billDiscountList the billDiscountList to set
	 */
	public void setBillDiscountList(List<BillDiscountDTO> billDiscountList) {
		this.billDiscountList = billDiscountList;
	}
	/**
	 * @return the billAmount
	 */
	public float getBillAmount() {
		return billAmount;
	}
	/**
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	/**
	 * @return the billUid
	 */
	public String getBillUid() {
		return billUid;
	}
	/**
	 * @param billUid the billUid to set
	 */
	public void setBillUid(String billUid) {
		this.billUid = billUid;
	}


}
