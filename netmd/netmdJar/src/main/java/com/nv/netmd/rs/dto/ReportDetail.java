/**
 * 
 */
package com.nv.netmd.rs.dto;


/**
 * @author Nithesh Mohanan
 *
 */
public class ReportDetail {
	private String date;
	private double amtPaid;
	private String billNo;
	private String fromDate;
	private String toDate;
	private String patientName;
	private float due;
	private float netAmount;
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the amtPaid
	 */
	public double getAmtPaid() {
		return amtPaid;
	}
	/**
	 * @param amtPaid the amtPaid to set
	 */
	public void setAmtPaid(double amtPaid) {
		this.amtPaid = amtPaid;
	}
	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}
	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}
	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	/**
	 * @return the due
	 */
	public float getDue() {
		return due;
	}
	/**
	 * @param due the due to set
	 */
	public void setDue(float due) {
		this.due = due;
	}
	/**
	 * @return the netAmount
	 */
	public float getNetAmount() {
		return netAmount;
	}
	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}
	
	
	/**
	 * @param amtPaid
	
	 */
	public ReportDetail(double amtPaid) {
		super();
		this.amtPaid = amtPaid;		
	}
	/**
	 * 
	 */
	public ReportDetail() {
		
	}

	
}
