/**
 * 
 */
package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Luciya
 *
 */
public class ReportDTO {
	private float grandTotal;
	private float totalDue;
	private float totalDiscount;
	private float totalNetAmount;
	private float grandNetRate;
	private float otherCharges;
	private List<ReportDetail> reports= new ArrayList<ReportDetail>();
	private boolean success;
	private ErrorDTO error;
	
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
	/**
	 * 
	 */
	public ReportDTO() {
		
	}
	
	public ReportDTO(float grandTotal, float totalDue, float totalDiscount,
			float totalNetAmount, float grandNetRate, float otherCharges,
			List<ReportDetail> reports, boolean success, ErrorDTO error) {
		super();
		this.grandTotal = grandTotal;
		this.totalDue = totalDue;
		this.totalDiscount = totalDiscount;
		this.totalNetAmount = totalNetAmount;
		this.grandNetRate = grandNetRate;
		this.otherCharges = otherCharges;
		this.reports = reports;
		this.success = success;
		this.error = error;
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
	 * @return the reports
	 */
	public List<ReportDetail> getReports() {
		return reports;
	}
	/**
	 * @param reports the reports to set
	 */
	public void setReports(List<ReportDetail> reports) {
		this.reports = reports;
	}
	public float getTotalDue() {
		return totalDue;
	}
	public void setTotalDue(float totalDue) {
		this.totalDue = totalDue;
	}
	public float getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public float getTotalNetAmount() {
		return totalNetAmount;
	}
	public void setTotalNetAmount(float totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}
	public float getGrandNetRate() {
		return grandNetRate;
	}
	public void setGrandNetRate(float grandNetRate) {
		this.grandNetRate = grandNetRate;
	}
	public float getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(float otherCharges) {
		this.otherCharges = otherCharges;
	}
	
}
