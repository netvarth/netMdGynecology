package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the doctor_schedule_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_schedule_tbl")
public class DoctorScheduleTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCTOR_SCHEDULE_TBL_ID_GENERATOR",sequenceName="doctor_schedule_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_SCHEDULE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

    @Temporal( TemporalType.DATE)
	private Date date;

	@Column(name="updated_time")
	private Date updatedTime;


	@Column(name="ending_time")
	private Date endingTime;

	@Column(name="global_id")
	private Integer globalId=0;

	@Column(name="\"netmd_Id\"")
	private Integer netmd_Id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="schedule_status", length=45)
	private ScheduleStatusEnum scheduleStatus;

	@Column(name="starting_time")
	private Date startingTime;
	
	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private StatusEnum status;
	
	@Column(name="created_time")
	private Date createdTime;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to SeriesTbl
    @ManyToOne
	@JoinColumn(name="series_id")
	private SeriesTbl seriesTbl;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="doctorScheduleTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

    public DoctorScheduleTbl() {
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

	public Integer getNetmd_Id() {
		return this.netmd_Id;
	}

	public void setNetmd_Id(Integer netmd_Id) {
		this.netmd_Id = netmd_Id;
	}
	
	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
	public SeriesTbl getSeriesTbl() {
		return this.seriesTbl;
	}

	public void setSeriesTbl(SeriesTbl seriesTbl) {
		this.seriesTbl = seriesTbl;
	}
	
	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	/**
	 * @return the scheduleStatus
	 */
	public ScheduleStatusEnum getScheduleStatus() {
		return scheduleStatus;
	}

	/**
	 * @param scheduleStatus the scheduleStatus to set
	 */
	public void setScheduleStatus(ScheduleStatusEnum scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	/**
	 * @return the endingTime
	 */
	public Date getEndingTime() {
		return endingTime;
	}

	/**
	 * @param endingTime the endingTime to set
	 */
	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
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
	
}