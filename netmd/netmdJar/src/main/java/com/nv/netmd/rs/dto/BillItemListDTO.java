/**
 * BillItemListDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 05-Sep-2013
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
public class BillItemListDTO {
private String billUid;
private List<BillItemDTO> item=new ArrayList<BillItemDTO>();
private List<BillSupportDTO> support=new ArrayList<BillSupportDTO>();
private List<BillBedDTO> bed=new ArrayList<BillBedDTO>();
private List<BillDiscountDTO> discount=new ArrayList<BillDiscountDTO>();
private float grandTotal;
private float billAmount;
private String note;
/**
 * @param billUid
 * @param item
 * @param support
 * @param bed
 * @param note
 */
public BillItemListDTO(String billUid, List<BillItemDTO> item,
		List<BillSupportDTO> support, List<BillBedDTO> bed, String note) {
	super();
	this.billUid = billUid;
	this.item = item;
	this.support = support;
	this.bed = bed;
	this.note = note;
}
/**
 * 
 */
public BillItemListDTO() {
	super();
	// TODO Auto-generated constructor stub
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
/**
 * @return the item
 */
public List<BillItemDTO> getItem() {
	return item;
}
/**
 * @param item the item to set
 */
public void setItem(List<BillItemDTO> item) {
	this.item = item;
}
/**
 * @return the support
 */
public List<BillSupportDTO> getSupport() {
	return support;
}
/**
 * @param support the support to set
 */
public void setSupport(List<BillSupportDTO> support) {
	this.support = support;
}
/**
 * @return the bed
 */
public List<BillBedDTO> getBed() {
	return bed;
}
/**
 * @param bed the bed to set
 */
public void setBed(List<BillBedDTO> bed) {
	this.bed = bed;
}
/**
 * @return the note
 */
public String getNote() {
	return note;
}
/**
 * @param note the note to set
 */
public void setNote(String note) {
	this.note = note;
}
/**
 * @return the grandTotal
 */
public float getGrandTotal() {
	return grandTotal;
}
/**
 * @param grandTotal the grandTotal to set
 */
public void setGrandTotal(float grandTotal) {
	this.grandTotal = grandTotal;
}
/**
 * @return the discount
 */
public List<BillDiscountDTO> getDiscount() {
	return discount;
}
/**
 * @param discount the discount to set
 */
public void setDiscount(List<BillDiscountDTO> discount) {
	this.discount = discount;
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

}
