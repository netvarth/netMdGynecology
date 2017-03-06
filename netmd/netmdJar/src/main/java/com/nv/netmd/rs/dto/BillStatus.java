package com.nv.netmd.rs.dto;

public class BillStatus {

	private String uid;
	private String status;
	
	public BillStatus(){
		
	}

	public BillStatus(String uid, String status) {
		super();
		this.uid = uid;
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
