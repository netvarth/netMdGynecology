package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the patient_tbl database table.
 * 
 */
@Entity
@Table(name="patient_tbl")
public class PatientTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PATIENT_TBL_ID_GENERATOR",sequenceName="patient_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=500)
	private String address;

	private Integer age;

	@Column(length=100)
	private String ailment;

	@Column(length=500)
	private String allergies;

	@Column(name="blood_group", length=100)
	private String bloodGroup;

	@Column(name="chronic_disease", length=500)
	private String chronicDisease;

	@Column(length=2147483647)
	private String diagnosis;

	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name="created_time")
	private Date createdTime;

	@Column(length=45)
	private String email;

	@Column(name="emergency_no", length=500)
	private String emergencyNo;

	@Column(name="family_history", length=500)
	private String familyHistory;

	@Column(name="first_name", length=45)
	private String firstName;

	@Enumerated(EnumType.STRING)
	@Column(length=25)
	private GenderEnum gender;

	@Column(name="global_id")
	private Integer globalId=0;

	@Column(length=100)
	private String height;

	@Column(name="last_name", length=45)
	private String lastName;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String phone;

	@Temporal(TemporalType.DATE)
	private Date dob;

	
	@Column(length=50)
	private String education;
	

	@Column(length=50)
	private String rh;
	
	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private StatusEnum status;

	@Column(length=100)
	private String weight;

	//bi-directional many-to-one association to CaseTbl
	@OneToMany(mappedBy="patientTbl")
	private Set<CaseTbl> caseTbls;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="patientTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="patientTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to PatientMedicalHistoryTbl
	@OneToMany(mappedBy="patientTbl")
	private Set<PatientMedicalHistoryTbl> patientMedicalHistoryTbls;

	//bi-directional many-to-one association to LoginTbl
    @ManyToOne
	@JoinColumn(name="login_table_id")
	private LoginTbl loginTbl;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="patientTbl")
	private Set<ResultTbl> resultTbls;

    public PatientTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAilment() {
		return this.ailment;
	}

	public void setAilment(String ailment) {
		this.ailment = ailment;
	}

	public String getAllergies() {
		return this.allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getChronicDisease() {
		return this.chronicDisease;
	}

	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyNo() {
		return this.emergencyNo;
	}

	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}

	public String getFamilyHistory() {
		return this.familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public Integer getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Set<CaseTbl> getCaseTbls() {
		return this.caseTbls;
	}

	public void setCaseTbls(Set<CaseTbl> caseTbls) {
		this.caseTbls = caseTbls;
	}
	
	public Set<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(Set<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}
	
	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}
	
	public Set<PatientMedicalHistoryTbl> getPatientMedicalHistoryTbls() {
		return this.patientMedicalHistoryTbls;
	}

	public void setPatientMedicalHistoryTbls(Set<PatientMedicalHistoryTbl> patientMedicalHistoryTbls) {
		this.patientMedicalHistoryTbls = patientMedicalHistoryTbls;
	}
	
	public LoginTbl getLoginTbl() {
		return this.loginTbl;
	}

	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}
	
	public Set<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(Set<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	/**
	 * @return the gender
	 */
	public GenderEnum getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	/**
	 * @return the status
	 */
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
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
	
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	public String getRh() {
		return this.rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}
	
}