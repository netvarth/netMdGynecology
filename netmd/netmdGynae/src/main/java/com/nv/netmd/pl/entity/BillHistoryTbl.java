package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the bill_history_tbl database table.
 * 
 */
@Entity
@Table(name="bill_history_tbl")
@NamedQuery(name="BillHistoryTbl.findAll", query="SELECT b FROM BillHistoryTbl b")
public class BillHistoryTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_HISTORY_TBL_ID_GENERATOR",sequenceName="bill_history_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_HISTORY_TBL_ID_GENERATOR")
	private Integer id;

	private Date date;

	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id")
	private BillTbl billTbl;

	public BillHistoryTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public BillTbl getBillTbl() {
		return this.billTbl;
	}

	public void setBillTbl(BillTbl billTbl) {
		this.billTbl = billTbl;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}