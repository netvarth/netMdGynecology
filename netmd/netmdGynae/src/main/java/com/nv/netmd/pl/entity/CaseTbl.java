package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the case_tbl database table.
 * 
 */
@Entity
@Table(name="case_tbl")
public class CaseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASE_TBL_ID_GENERATOR",sequenceName="case_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="admitted_date")
	private Date admittedDate;

	@Column(name="bmi")
	private Float bmi;

	@Column(name="case_name", length=500)
	private String caseName;

	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name="global_id")
	private int globalId;

	@Column(length=2147483647)
	private String description;


	@Column(name="hb")
	private float hb;

	@Column(name="height")
	private float height;

	@Column(name="patient_type", length=2147483647)
	private String patientType;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private StatusEnum status;

	@Column(name="weight")
	private float weight;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentTbl departmentTbl;

	//bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;
	
	//bi-directional many-to-one association to CaseAnswerSetTbl
	@OneToMany(mappedBy="caseTbl")
	private List<CaseAnswerSetTbl> caseAnswerSetTbls;

	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(mappedBy="caseTbl")
	private List<AnswerTbl> anwserTbls; 
	
	public List<AnswerTbl> getAnwserTbls() {
		return anwserTbls;
	}

	public void setAnwserTbls(List<AnswerTbl> anwserTbls) {
		this.anwserTbls = anwserTbls;
	}

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="caseTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

	public CaseTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAdmittedDate() {
		return this.admittedDate;
	}

	public void setAdmittedDate(Date admittedDate) {
		this.admittedDate = admittedDate;
	}

	public float getBmi() {
		return this.bmi;
	}

	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getHb() {
		return this.hb;
	}

	public void setHb(float hb) {
		this.hb = hb;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}

	public Set<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(Set<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}
	public List<CaseAnswerSetTbl> getCaseAnswerSetTbls() {
		return this.caseAnswerSetTbls;
	}

	public void setCaseAnswerSetTbls(List<CaseAnswerSetTbl> caseAnswerSetTbls) {
		this.caseAnswerSetTbls = caseAnswerSetTbls;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}



}