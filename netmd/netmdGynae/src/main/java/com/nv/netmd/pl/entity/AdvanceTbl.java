package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the advance_tbl database table.
 * 
 */
@Entity
@Table(name="advance_tbl")
public class AdvanceTbl implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="ADVANCE_TBL_ID_GENERATOR",sequenceName="advance_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADVANCE_TBL_ID_GENERATOR")

	private Integer id;
	@Column(name="advance_date")
	private Date advanceDate;

	private float amount;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	//bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

	public AdvanceTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAdvanceDate() {
		return this.advanceDate;
	}

	public void setAdvanceDate(Date advanceDate) {
		this.advanceDate = advanceDate;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}

}