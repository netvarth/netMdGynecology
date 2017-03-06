package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the expense_payment_tbl database table.
 * 
 */
@Entity
@Table(name="expense_payment_tbl")
public class ExpensePaymentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPENSE_PAYMENT_TBL_ID_GENERATOR",sequenceName="expense_pay_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_PAYMENT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="amount_paid", length=2147483647)
	private float amountPaid;

	@Column(name="payment_date")
	private Date paymentDate;

	//bi-directional many-to-one association to ExpenseTbl
	@ManyToOne
	@JoinColumn(name="expense_id")
	private ExpenseTbl expenseTbl;

	public ExpensePaymentTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public ExpenseTbl getExpenseTbl() {
		return this.expenseTbl;
	}

	public void setExpenseTbl(ExpenseTbl expenseTbl) {
		this.expenseTbl = expenseTbl;
	}

}