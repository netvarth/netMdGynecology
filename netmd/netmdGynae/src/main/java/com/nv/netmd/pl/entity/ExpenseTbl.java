 package com.nv.netmd.pl.entity;
 
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the expense_tbl database table.
 * 
 */
@Entity
@Table(name="expense_tbl")
public class ExpenseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPENSE_TBL_ID_GENERATOR",sequenceName="expense_seq", allocationSize=1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(precision=131089)
	private float balance;
	
	@Column(name="created_on")
	private Date createdOn;

	@Column(length=2147483647)
	private String description;

	@Column(name="expense_name", length=2147483647)
	private String expenseName;

	@Column(name="head_name", length=2147483647)
	private String headName;
	
	@Column(length=2147483647)
	private String note;

	@Column(name="paid_amount", precision=131089)
	private float paidAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status", length=2147483647)
	private PayStatusEnum paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	@Column(name="total_amount", precision=131089)
	private float totalAmount;

	//bi-directional many-to-one association to HeadTbl
	@ManyToOne
	@JoinColumn(name="head_id")
	private HeadTbl headTbl;

	public ExpenseTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpenseName() {
		return this.expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public String getHeadName() {
		return this.headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public float getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public PayStatusEnum getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(PayStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the status
	 */
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public float getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public HeadTbl getHeadTbl() {
		return this.headTbl;
	}

	public void setHeadTbl(HeadTbl headTbl) {
		this.headTbl = headTbl;
	}

}