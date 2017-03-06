package com.nv.netmd.pl.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question_tbl database table.
 * 
 */
@Entity
@Table(name="question_tbl")
public class QuestionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUESTION_TBL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTION_TBL_ID_GENERATOR")
	private Integer id;

	@Column(name="answer_type")
	private String answerType;

	private Boolean indexed;
	
	private Boolean mandatory;

	private String question;

	@Column(name="question_key")
	private String questionKey;

	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(mappedBy="questionTbl")
	private List<AnswerTbl> answerTbls;

	//bi-directional many-to-one association to DepartmentQuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="department_questionnaire_id")
	private DepartmentQuestionnaireTbl departmentQuestionnaireTbl;

	public QuestionTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerType() {
		return this.answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public Boolean getIndexed() {
		return this.indexed;
	}

	public void setIndexed(Boolean indexed) {
		this.indexed = indexed;
	}

	public Boolean getMandatory() {
		return this.mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionKey() {
		return this.questionKey;
	}

	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}

	public List<AnswerTbl> getAnswerTbls() {
		return this.answerTbls;
	}

	public void setAnswerTbls(List<AnswerTbl> answerTbls) {
		this.answerTbls = answerTbls;
	}

	public AnswerTbl addAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().add(answerTbl);
		answerTbl.setQuestionTbl(this);

		return answerTbl;
	}

	public AnswerTbl removeAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().remove(answerTbl);
		answerTbl.setQuestionTbl(null);

		return answerTbl;
	}

	public DepartmentQuestionnaireTbl getDepartmentQuestionnaireTbl() {
		return this.departmentQuestionnaireTbl;
	}

	public void setDepartmentQuestionnaireTbl(DepartmentQuestionnaireTbl departmentQuestionnaireTbl) {
		this.departmentQuestionnaireTbl = departmentQuestionnaireTbl;
	}

}