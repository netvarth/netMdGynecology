package com.nv.netmd.pl.entity;
import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the head_tbl database table.
 * 
 */
@Entity
@Table(name="head_tbl")
public class HeadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HEAD_TBL_ID_GENERATOR",sequenceName="head_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HEAD_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String description;

	@Column(name="head_name", length=2147483647)
	private String headName;

	@Enumerated(EnumType.STRING)
	@Column(name="head_type", length=2147483647)
	private HeadTypeEnum headType;

	@Column(name="parent_head_name", length=2147483647)
	private String parentHeadName;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	//bi-directional many-to-one association to ExpenseTbl
	@OneToMany(mappedBy="headTbl")
	private List<ExpenseTbl> expenseTbls;

	//bi-directional many-to-one association to HeadTbl
	@ManyToOne
	@JoinColumn(name="parent_head_id")
	private HeadTbl headTbl;

	//bi-directional many-to-one association to HeadTbl
	@OneToMany(mappedBy="headTbl")
	private List<HeadTbl> headTbls;

	public HeadTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeadName() {
		return this.headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public HeadTypeEnum getHeadType() {
		return this.headType;
	}

	public void setHeadType(HeadTypeEnum headType) {
		this.headType = headType;
	}

	public String getParentHeadName() {
		return this.parentHeadName;
	}

	public void setParentHeadName(String parentHeadName) {
		this.parentHeadName = parentHeadName;
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

	public List<ExpenseTbl> getExpenseTbls() {
		return this.expenseTbls;
	}

	public void setExpenseTbls(List<ExpenseTbl> expenseTbls) {
		this.expenseTbls = expenseTbls;
	}

	public ExpenseTbl addExpenseTbl(ExpenseTbl expenseTbl) {
		getExpenseTbls().add(expenseTbl);
		expenseTbl.setHeadTbl(this);

		return expenseTbl;
	}

	public ExpenseTbl removeExpenseTbl(ExpenseTbl expenseTbl) {
		getExpenseTbls().remove(expenseTbl);
		expenseTbl.setHeadTbl(null);

		return expenseTbl;
	}

	public HeadTbl getHeadTbl() {
		return this.headTbl;
	}

	public void setHeadTbl(HeadTbl headTbl) {
		this.headTbl = headTbl;
	}

	public List<HeadTbl> getHeadTbls() {
		return this.headTbls;
	}

	public void setHeadTbls(List<HeadTbl> headTbls) {
		this.headTbls = headTbls;
	}

	public HeadTbl addHeadTbl(HeadTbl headTbl) {
		getHeadTbls().add(headTbl);
		headTbl.setHeadTbl(this);

		return headTbl;
	}

	public HeadTbl removeHeadTbl(HeadTbl headTbl) {
		getHeadTbls().remove(headTbl);
		headTbl.setHeadTbl(null);

		return headTbl;
	}

}