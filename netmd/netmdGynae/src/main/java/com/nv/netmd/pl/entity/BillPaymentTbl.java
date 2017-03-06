package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;


/**
 * The persistent class for the bill_payment_tbl database table.
 * 
 */
@Entity
@Table(name="bill_payment_tbl")
@NamedQuery(name="BillPaymentTbl.findAll", query="SELECT b FROM BillPaymentTbl b")
public class BillPaymentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_PAYMENT_TBL_ID_GENERATOR" ,sequenceName="bill_payment_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_PAYMENT_TBL_ID_GENERATOR")
	private Integer id;

	@Column(name="amount_paid")
	private float amountPaid;

	@Column(name="bill_no")
	private String billNo;

	private String description;

	@Column(name="mode_of_payment")
	private String modeOfPayment;

	@Column(name="payment_date")
	private Date paymentDate;

	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id")
	private BillTbl billTbl;

	public BillPaymentTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModeOfPayment() {
		return this.modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	
	public BillTbl getBillTbl() {
		return this.billTbl;
	}

	public void setBillTbl(BillTbl billTbl) {
		this.billTbl = billTbl;
	}

	/**
	 * @return the amountPaid
	 */
	public float getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}