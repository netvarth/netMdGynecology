package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the login_tbl database table.
 * 
 */
@Entity
@Table(name="login_tbl")
public class LoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOGIN_TBL_ID_GENERATOR" ,sequenceName="login_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOGIN_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="user_name", length=45)
	private String userName;
	
	@Column(length=2147483647)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_type", length=45)
	private UserTypeEnum userType;
	
	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private StatusEnum status;

	//bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy="loginTbl")
	private Set<DoctorTbl> doctorTbls;

	//bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy="loginTbl")
	private Set<PatientTbl> patientTbls;

    public LoginTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<DoctorTbl> getDoctorTbls() {
		return this.doctorTbls;
	}

	public void setDoctorTbls(Set<DoctorTbl> doctorTbls) {
		this.doctorTbls = doctorTbls;
	}
	
	public Set<PatientTbl> getPatientTbls() {
		return this.patientTbls;
	}

	public void setPatientTbls(Set<PatientTbl> patientTbls) {
		this.patientTbls = patientTbls;
	}

	/**
	 * @return the userType
	 */
	public UserTypeEnum getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}


}