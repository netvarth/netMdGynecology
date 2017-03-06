package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the sequence_tbl database table.
 * 
 */
@Entity
@Table(name="sequence_tbl")
public class SequenceTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQUENCE_TBL_ID_GENERATOR",sequenceName="seq_tbl_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="in_patient",nullable=false)
	private Integer inPatient;

	@Column(name="out_patient",nullable=false)
	private Integer outPatient;

	public SequenceTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInPatient() {
		return this.inPatient;
	}

	public void setInPatient(Integer inPatient) {
		this.inPatient = inPatient;
	}

	public Integer getOutPatient() {
		return this.outPatient;
	}

	public void setOutPatient(Integer outPatient) {
		this.outPatient = outPatient;
	}

}