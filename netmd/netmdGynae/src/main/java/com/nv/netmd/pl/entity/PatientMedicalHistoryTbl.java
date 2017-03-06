package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patient_medical_history_tbl database table.
 * 
 */
@Entity
@Table(name="patient_medical_history_tbl")
public class PatientMedicalHistoryTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PATIENT_MEDICAL_HISTORY_TBL_ID_GENERATOR",sequenceName="medical_history_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_MEDICAL_HISTORY_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="diagonised_age")
	private Integer diagonisedAge;

	@Column(name="\"isCured\"", length=200)
	private String isCured;

	@Column(name="medical_issue", length=200)
	private String medicalIssue;

	@Column(length=2147483647)
	private String medication;

	@Column(length=200)
	private String surgery;

	@Column(length=2147483647)
	private String tenure;

	@Column(length=200)
	private String treatment;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

    public PatientMedicalHistoryTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDiagonisedAge() {
		return this.diagonisedAge;
	}

	public void setDiagonisedAge(Integer diagonisedAge) {
		this.diagonisedAge = diagonisedAge;
	}

	public String getIsCured() {
		return this.isCured;
	}

	public void setIsCured(String isCured) {
		this.isCured = isCured;
	}

	public String getMedicalIssue() {
		return this.medicalIssue;
	}

	public void setMedicalIssue(String medicalIssue) {
		this.medicalIssue = medicalIssue;
	}

	public String getMedication() {
		return this.medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getSurgery() {
		return this.surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getTenure() {
		return this.tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getTreatment() {
		return this.treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}
	
}