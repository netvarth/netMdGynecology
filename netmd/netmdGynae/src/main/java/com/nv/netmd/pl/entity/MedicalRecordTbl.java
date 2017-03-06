package com.nv.netmd.pl.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;



/**
 * The persistent class for the medical_record_tbl database table.
 * 
 */
@Entity
@Table(name="medical_record_tbl")
public class MedicalRecordTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MEDICAL_RECORD_TBL_ID_GENERATOR",sequenceName="medical_record_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEDICAL_RECORD_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="global_id")
	private Integer globalId;
	
	@Column(name="created_time")
	private Date createdTime;
	
	
	@Column(name="updated_time")
	private Date updatedTime;

	@Column(name="medical_record", length=2147483647)
	private String medicalRecord;
	
	@Enumerated(EnumType.STRING)
	@Column(length=100)
	private MedicalRecordTypeEnum type;

	@Enumerated(EnumType.STRING)
	@Column(length=2147483647)
	private StatusEnum status;
	
	//bi-directional many-to-one association to CaseTbl
    @ManyToOne
	@JoinColumn(name="case_id")
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

    public MedicalRecordTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
	}


	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	/**
	 * @return the globalId
	 */
	public Integer getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	public CaseTbl getCaseTbl() {
		return this.caseTbl;
	}

	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}
	
	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the type
	 */
	public MedicalRecordTypeEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MedicalRecordTypeEnum type) {
		this.type = type;
	}
	
}