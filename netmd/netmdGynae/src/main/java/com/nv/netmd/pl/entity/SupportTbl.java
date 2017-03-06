package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the service_tbl database table.
 * 
 */
@Entity
@Table(name="support_tbl")
public class SupportTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="SUPPORT_TBL_ID_GENERATOR",sequenceName="support_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPORT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	private String description;

	private String name;

	private float price;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", length=100)
	private StatusEnum status;

	private String uid;

	//bi-directional many-to-one association to BillSupportTbl
	@OneToMany(mappedBy="supportTbl")
	private List<BillSupportTbl> billSupportTbls;

	//bi-directional many-to-one association to TaxTbl
	@ManyToOne
	@JoinColumn(name="tax_id")
	private TaxTbl taxTbl;

	public SupportTbl() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<BillSupportTbl> getBillSupportTbls() {
		return this.billSupportTbls;
	}

	public void setBillSupportTbls(List<BillSupportTbl> billSupportTbls) {
		this.billSupportTbls = billSupportTbls;
	}

	public BillSupportTbl addBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().add(billSupportTbl);
		billSupportTbl.setSupportTbl(this);

		return billSupportTbl;
	}

	public BillSupportTbl removeBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().remove(billSupportTbl);
		billSupportTbl.setSupportTbl(null);

		return billSupportTbl;
	}

	public TaxTbl getTaxTbl() {
		return this.taxTbl;
	}

	public void setTaxTbl(TaxTbl taxTbl) {
		this.taxTbl = taxTbl;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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

}