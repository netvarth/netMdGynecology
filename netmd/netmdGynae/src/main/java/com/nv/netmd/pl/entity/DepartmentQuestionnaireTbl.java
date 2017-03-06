package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department_questionnaire_tbl database table.
 * 
 */
@Entity
@Table(name="department_questionnaire_tbl")
public class DepartmentQuestionnaireTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTMENT_QUESTIONNAIRE_TBL_ID_GENERATOR",sequenceName="department_questionnaire_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENT_QUESTIONNAIRE_TBL_ID_GENERATOR")

	private Integer id;

	@Column(name="\"dQuestionnaire\"")
	private String dQuestionnaire;

	//bi-directional many-to-one association to CaseAnswerSetTbl
	@OneToMany(mappedBy="departmentQuestionnaireTbl")
	private List<CaseAnswerSetTbl> caseAnswerSetTbls;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentTbl departmentTbl;

	//bi-directional many-to-one association to QuestionTbl
	@OneToMany(mappedBy="departmentQuestionnaireTbl")
	private List<QuestionTbl> questionTbls;

	public DepartmentQuestionnaireTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDQuestionnaire() {
		return this.dQuestionnaire;
	}

	public void setDQuestionnaire(String dQuestionnaire) {
		this.dQuestionnaire = dQuestionnaire;
	}

	public List<CaseAnswerSetTbl> getCaseAnswerSetTbls() {
		return this.caseAnswerSetTbls;
	}

	public void setCaseAnswerSetTbls(List<CaseAnswerSetTbl> caseAnswerSetTbls) {
		this.caseAnswerSetTbls = caseAnswerSetTbls;
	}

	public CaseAnswerSetTbl addCaseAnswerSetTbl(CaseAnswerSetTbl caseAnswerSetTbl) {
		getCaseAnswerSetTbls().add(caseAnswerSetTbl);
		caseAnswerSetTbl.setDepartmentQuestionnaireTbl(this);

		return caseAnswerSetTbl;
	}

	public CaseAnswerSetTbl removeCaseAnswerSetTbl(CaseAnswerSetTbl caseAnswerSetTbl) {
		getCaseAnswerSetTbls().remove(caseAnswerSetTbl);
		caseAnswerSetTbl.setDepartmentQuestionnaireTbl(null);

		return caseAnswerSetTbl;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public List<QuestionTbl> getQuestionTbls() {
		return this.questionTbls;
	}

	public void setQuestionTbls(List<QuestionTbl> questionTbls) {
		this.questionTbls = questionTbls;
	}

	public QuestionTbl addQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().add(questionTbl);
		questionTbl.setDepartmentQuestionnaireTbl(this);

		return questionTbl;
	}

	public QuestionTbl removeQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().remove(questionTbl);
		questionTbl.setDepartmentQuestionnaireTbl(null);

		return questionTbl;
	}

}