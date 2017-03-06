package com.nv.netmd.pl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the result_tbl database table.
 * 
 */
@Entity
@Table(name="result_tbl")
public class ResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESULT_TBL_ID_GENERATOR",sequenceName="result_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESULT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	
	@Column(length=2147483647)
	private String result;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="lab_branch_name", length=45)
	private String labBranchName;

	@Column(name="lab_name", length=45)
	private String labName;

	@Column(name="order_date")
	private Date orderDate;

	@Column(name="order_uid", length=40)
	private String orderUid;


	@Column(name="global_id")
	private Integer globalId=0;
	
	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

    public ResultTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
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

	/**
	 * @return the labBranchName
	 */
	public String getLabBranchName() {
		return labBranchName;
	}

	/**
	 * @param labBranchName the labBranchName to set
	 */
	public void setLabBranchName(String labBranchName) {
		this.labBranchName = labBranchName;
	}

	/**
	 * @return the labName
	 */
	public String getLabName() {
		return labName;
	}

	/**
	 * @param labName the labName to set
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	

	/**
	 * @return the orderUid
	 */
	public String getOrderUid() {
		return orderUid;
	}

	/**
	 * @param orderUid the orderUid to set
	 */
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
}