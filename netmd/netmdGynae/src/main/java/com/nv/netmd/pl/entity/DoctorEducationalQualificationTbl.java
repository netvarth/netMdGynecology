package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the doctor_educational_qualification_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_educational_qualification_tbl")
public class DoctorEducationalQualificationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCTOR_EDUCATIONAL_QUALIFICATION_TBL_ID_GENERATOR",sequenceName="doctor_edu_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_EDUCATIONAL_QUALIFICATION_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="educational_degree", length=145)
	private String educationalDegree;

	@Column(length=145)
	private String institution;

    @Temporal( TemporalType.DATE)
	@Column(name="passed_out_date")
	private Date passedOutDate;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

    public DoctorEducationalQualificationTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getEducationalDegree() {
		return this.educationalDegree;
	}

	public void setEducationalDegree(String educationalDegree) {
		this.educationalDegree = educationalDegree;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getPassedOutDate() {
		return this.passedOutDate;
	}

	public void setPassedOutDate(Date passedOutDate) {
		this.passedOutDate = passedOutDate;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}