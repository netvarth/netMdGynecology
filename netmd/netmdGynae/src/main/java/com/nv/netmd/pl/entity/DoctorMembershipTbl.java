package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctor_membership_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_membership_tbl")
public class DoctorMembershipTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCTOR_MEMBERSHIP_TBL_ID_GENERATOR",sequenceName="doctor_membership_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_MEMBERSHIP_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String membership;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

    public DoctorMembershipTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMembership() {
		return this.membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}