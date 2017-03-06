package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the doctor_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_tbl")
public class DoctorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCTOR_TBL_ID_GENERATOR",sequenceName="doctor_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String address;

	@Column(name="consultation_interval")
	private Integer consultationInterval;

    @Temporal( TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Column(length=2147483647)
	private String designation;
	
	@Column(name="specialization", length=45)
	private String specialization;

	@Column(name="created_time")
	private Date createdTime;

	@Column(length=2147483647)
	private String email;

	@Column(name="first_name", length=2147483647)
	private String firstName;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private GenderEnum gender;

	@Column(name="global_id")
	private Integer globalId=0;

	@Column(name="last_name", length=45)
	private String lastName;

	@Column(length=100)
	private String mobile;

	@Column(length=100)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private StatusEnum status;

	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name="work_history", length=200)
	private String workHistory;

	@Column(name="working_places", length=200)
	private String workingPlaces;

	//bi-directional many-to-one association to DoctorAchievementTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorAchievementTbl> doctorAchievementTbls;

	//bi-directional many-to-one association to DoctorEducationalQualificationTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls;

	//bi-directional many-to-one association to DoctorExpertiseTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorExpertiseTbl> doctorExpertiseTbls;

	//bi-directional many-to-one association to DoctorMembershipTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorMembershipTbl> doctorMembershipTbls;

	//bi-directional many-to-one association to DoctorPracticeExperienceTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

	//bi-directional many-to-one association to LoginTbl
    @ManyToOne
	@JoinColumn(name="login_tbl_id")
	private LoginTbl loginTbl;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="doctorTbl")
	private Set<ResultTbl> resultTbls;

    public DoctorTbl() {
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

	public Integer getConsultationInterval() {
		return this.consultationInterval;
	}

	public void setConsultationInterval(Integer consultationInterval) {
		this.consultationInterval = consultationInterval;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	
	public Set<DoctorAchievementTbl> getDoctorAchievementTbls() {
		return this.doctorAchievementTbls;
	}

	public void setDoctorAchievementTbls(Set<DoctorAchievementTbl> doctorAchievementTbls) {
		this.doctorAchievementTbls = doctorAchievementTbls;
	}
	
	public Set<DoctorEducationalQualificationTbl> getDoctorEducationalQualificationTbls() {
		return this.doctorEducationalQualificationTbls;
	}

	public void setDoctorEducationalQualificationTbls(Set<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls) {
		this.doctorEducationalQualificationTbls = doctorEducationalQualificationTbls;
	}
	
	public Set<DoctorExpertiseTbl> getDoctorExpertiseTbls() {
		return this.doctorExpertiseTbls;
	}

	public void setDoctorExpertiseTbls(Set<DoctorExpertiseTbl> doctorExpertiseTbls) {
		this.doctorExpertiseTbls = doctorExpertiseTbls;
	}
	
	public Set<DoctorMembershipTbl> getDoctorMembershipTbls() {
		return this.doctorMembershipTbls;
	}

	public void setDoctorMembershipTbls(Set<DoctorMembershipTbl> doctorMembershipTbls) {
		this.doctorMembershipTbls = doctorMembershipTbls;
	}
	
	public Set<DoctorPracticeExperienceTbl> getDoctorPracticeExperienceTbls() {
		return this.doctorPracticeExperienceTbls;
	}

	public void setDoctorPracticeExperienceTbls(Set<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls) {
		this.doctorPracticeExperienceTbls = doctorPracticeExperienceTbls;
	}
	
	public Set<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(Set<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}
	
	public LoginTbl getLoginTbl() {
		return this.loginTbl;
	}

	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
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
	 * @return the workHistory
	 */
	public String getWorkHistory() {
		return workHistory;
	}

	/**
	 * @param workHistory the workHistory to set
	 */
	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	/**
	 * @return the workingPlaces
	 */
	public String getWorkingPlaces() {
		return workingPlaces;
	}

	/**
	 * @param workingPlaces the workingPlaces to set
	 */
	public void setWorkingPlaces(String workingPlaces) {
		this.workingPlaces = workingPlaces;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

		
}