package com.nv.netmd.pl.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the answer_tbl database table.
 * 
 */
@Entity
@Table(name="answer_tbl")
public class AnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANSWER_TBL_ID_GENERATOR" ,sequenceName="answer_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANSWER_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String answer;
	
	@Column(length=2147483647,name="index")
	private String indexKey;

	//bi-directional many-to-one association to CaseAnswerSetTbl
	@ManyToOne
	@JoinColumn(name="answer_set_id")
	private CaseAnswerSetTbl caseAnswerSetTbl;

	//bi-directional many-to-one association to CaseTbl
	@ManyToOne
	@JoinColumn(name="case_id")
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to QuestionTbl
	@ManyToOne
	@JoinColumn(name="question_id")
	private QuestionTbl questionTbl;

	public AnswerTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * @return the indexKey
	 */
	public String getIndexKey() {
		return indexKey;
	}

	/**
	 * @param indexKey the indexKey to set
	 */
	public void setIndexKey(String indexKey) {
		this.indexKey = indexKey;
	}

	public CaseAnswerSetTbl getCaseAnswerSetTbl() {
		return this.caseAnswerSetTbl;
	}

	public void setCaseAnswerSetTbl(CaseAnswerSetTbl caseAnswerSetTbl) {
		this.caseAnswerSetTbl = caseAnswerSetTbl;
	}

	public CaseTbl getCaseTbl() {
		return this.caseTbl;
	}

	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}

	public QuestionTbl getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(QuestionTbl questionTbl) {
		this.questionTbl = questionTbl;
	}

} 