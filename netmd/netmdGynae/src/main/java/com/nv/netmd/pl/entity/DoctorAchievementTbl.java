package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctor_achievement_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_achievement_tbl")
public class DoctorAchievementTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCTOR_ACHIEVEMENT_TBL_ID_GENERATOR" ,sequenceName="doctor_achievement_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_ACHIEVEMENT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String achievement;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

    public DoctorAchievementTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAchievement() {
		return this.achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}