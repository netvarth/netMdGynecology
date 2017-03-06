package com.nv.netmd.rs.dto;


public class BillStatusDTO {

	private String billstatus;
	private String note;
	private String uid;
	private boolean success;
	private ErrorDTO error;
	/**
	 * @param billstatus
	 * @param note
	 * @param uid
	 * @param success
	 * @param error
	 */
	private BillStatusDTO(String billstatus, String note, String uid,
			boolean success, ErrorDTO error) {
		super();
		this.billstatus = billstatus;
		this.note = note;
		this.uid = uid;
		this.success = success;
		this.error = error;
	}
	/**
	 * 
	 */
	private BillStatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the billstatus
	 */
	public String getBillstatus() {
		return billstatus;
	}
	/**
	 * @param billstatus the billstatus to set
	 */
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
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
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
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
}
