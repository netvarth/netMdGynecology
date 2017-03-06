package com.nv.netmd.pl.entity;

import java.io.Serializable;
import java.net.Proxy.Type;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the case_answer_set_tbl database table.
 * 
 */
@Entity
@Table(name="case_answer_set_tbl")
public class CaseAnswerSetTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASE_ANSWER_SET_TBL_ANSWERSETID_GENERATOR",sequenceName="case_answer_set_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASE_ANSWER_SET_TBL_ANSWERSETID_GENERATOR")
	@Column(name="answer_set_id", unique=true, nullable=false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="sync_status", length=2147483647)
	private SyncStatusEnum syncStatus;
	
	
	@Column(name="antenatal_created_date")
	private String antenatalCreatedDate;


	@Column(name="created_time")
	private Date createdTime;

	@Column(name="global_id")
	private Integer globalId;

	@Column(name="updated_time")
	private Date updatedTime;
	
	@Enumerated(EnumType.STRING)
	@Column(length=2147483647)
	private StatusEnum status;
	
	
	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(mappedBy="caseAnswerSetTbl")
	private List<AnswerTbl> answerTbls;

	//bi-directional many-to-one association to CaseTbl
	@ManyToOne
	@JoinColumn(name="case_id")
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DepartmentQuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="department_questionnaire_id")
	private DepartmentQuestionnaireTbl departmentQuestionnaireTbl;

	public CaseAnswerSetTbl() {
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}





	/**
	 * @return the antenatalCreatedDate
	 */
	public String getAntenatalCreatedDate() {
		return antenatalCreatedDate;
	}



	/**
	 * @param antenatalCreatedDate the antenatalCreatedDate to set
	 */
	public void setAntenatalCreatedDate(String antenatalCreatedDate) {
		this.antenatalCreatedDate = antenatalCreatedDate;
	}



	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date currentTime) {
		this.updatedTime = currentTime;
	}
	public SyncStatusEnum getSyncStatus() {
		return this.syncStatus;
	}

	public void setSyncStatus(SyncStatusEnum syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	public List<AnswerTbl> getAnswerTbls() {
		return this.answerTbls;
	}

	public void setAnswerTbls(List<AnswerTbl> answerTbls) {
		this.answerTbls = answerTbls;
	}

	public AnswerTbl addAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().add(answerTbl);
		answerTbl.setCaseAnswerSetTbl(this);

		return answerTbl;
	}

	public AnswerTbl removeAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().remove(answerTbl);
		answerTbl.setCaseAnswerSetTbl(null);

		return answerTbl;
	}

	public CaseTbl getCaseTbl() {
		return this.caseTbl;
	}

	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}

	public DepartmentQuestionnaireTbl getDepartmentQuestionnaireTbl() {
		return this.departmentQuestionnaireTbl;
	}

	public void setDepartmentQuestionnaireTbl(DepartmentQuestionnaireTbl departmentQuestionnaireTbl) {
		this.departmentQuestionnaireTbl = departmentQuestionnaireTbl;
	}
	

}