package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the patient_appointment_tbl database table.
 * 
 */
@Entity
@Table(name="patient_appointment_tbl")
public class PatientAppointmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PATIENT_APPOINTMENT_TBL_ID_GENERATOR",sequenceName="appointment_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_APPOINTMENT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="appointment_status", length=50)
	private AppointmentStatusEnum appointmentStatus;

    @Temporal( TemporalType.DATE)
	private Date date;
    
    @Column(name="created_time")
	private Date createdTime;

	@Column(name="global_id")
	private Integer globalId=0;

	@Column(name="starting_time")
	private Date startingTime;


	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private StatusEnum status;

	@Column(name="updated_time")
	private Date updatedTime;

	//bi-directional many-to-one association to DoctorScheduleTbl
    @ManyToOne
	@JoinColumn(name="schedule_id")
	private DoctorScheduleTbl doctorScheduleTbl;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

    public PatientAppointmentTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}


	public DoctorScheduleTbl getDoctorScheduleTbl() {
		return this.doctorScheduleTbl;
	}

	public void setDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		this.doctorScheduleTbl = doctorScheduleTbl;
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
	 * @return the startingTime
	 */
	public Date getStartingTime() {
		return startingTime;
	}

	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
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
	 * @return the appointmentStatus
	 */
	public AppointmentStatusEnum getAppointmentStatus() {
		return appointmentStatus;
	}

	/**
	 * @param appointmentStatus the appointmentStatus to set
	 */
	public void setAppointmentStatus(AppointmentStatusEnum appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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
	
}