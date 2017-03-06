package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the discount_tbl database table.
 * 
 */
@Entity
@Table(name="discount_tbl")
@NamedQuery(name="DiscountTbl.findAll", query="SELECT d FROM DiscountTbl d")
public class DiscountTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCOUNT_TBL_ID_GENERATOR",sequenceName="discount_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCOUNT_TBL_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="calculation_type")
	private CalculationTypeEnum calculationType;

	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name="discount_type")
	private DiscountTypeEnum discountType;

	@Column(name="discount_value")
	private float discountValue;

	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	//bi-directional many-to-one association to BillBedTbl
	@OneToMany(mappedBy="discountTbl")
	private List<BillBedTbl> billBedTbls;

	//bi-directional many-to-one association to BillDiscountTbl
	@OneToMany(mappedBy="discountTbl")
	private List<BillDiscountTbl> billDiscountTbls;

	//bi-directional many-to-one association to BillItemTbl
	@OneToMany(mappedBy="discountTbl")
	private List<BillItemTbl> billItemTbls;

	//bi-directional many-to-one association to BillSupportTbl
	@OneToMany(mappedBy="discountTbl")
	private List<BillSupportTbl> billSupportTbls;

	public DiscountTbl() {
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

	public List<BillBedTbl> getBillBedTbls() {
		return this.billBedTbls;
	}

	public void setBillBedTbls(List<BillBedTbl> billBedTbls) {
		this.billBedTbls = billBedTbls;
	}

	public BillBedTbl addBillBedTbl(BillBedTbl billBedTbl) {
		getBillBedTbls().add(billBedTbl);
		billBedTbl.setDiscountTbl(this);

		return billBedTbl;
	}

	public BillBedTbl removeBillBedTbl(BillBedTbl billBedTbl) {
		getBillBedTbls().remove(billBedTbl);
		billBedTbl.setDiscountTbl(null);

		return billBedTbl;
	}

	public List<BillDiscountTbl> getBillDiscountTbls() {
		return this.billDiscountTbls;
	}

	public void setBillDiscountTbls(List<BillDiscountTbl> billDiscountTbls) {
		this.billDiscountTbls = billDiscountTbls;
	}

	public BillDiscountTbl addBillDiscountTbl(BillDiscountTbl billDiscountTbl) {
		getBillDiscountTbls().add(billDiscountTbl);
		billDiscountTbl.setDiscountTbl(this);

		return billDiscountTbl;
	}

	public BillDiscountTbl removeBillDiscountTbl(BillDiscountTbl billDiscountTbl) {
		getBillDiscountTbls().remove(billDiscountTbl);
		billDiscountTbl.setDiscountTbl(null);

		return billDiscountTbl;
	}

	public List<BillItemTbl> getBillItemTbls() {
		return this.billItemTbls;
	}

	public void setBillItemTbls(List<BillItemTbl> billItemTbls) {
		this.billItemTbls = billItemTbls;
	}

	public BillItemTbl addBillItemTbl(BillItemTbl billItemTbl) {
		getBillItemTbls().add(billItemTbl);
		billItemTbl.setDiscountTbl(this);

		return billItemTbl;
	}

	public BillItemTbl removeBillItemTbl(BillItemTbl billItemTbl) {
		getBillItemTbls().remove(billItemTbl);
		billItemTbl.setDiscountTbl(null);

		return billItemTbl;
	}

	public List<BillSupportTbl> getBillSupportTbls() {
		return this.billSupportTbls;
	}

	public void setBillSupportTbls(List<BillSupportTbl> billSupportTbls) {
		this.billSupportTbls = billSupportTbls;
	}

	public BillSupportTbl addBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().add(billSupportTbl);
		billSupportTbl.setDiscountTbl(this);

		return billSupportTbl;
	}

	public BillSupportTbl removeBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().remove(billSupportTbl);
		billSupportTbl.setDiscountTbl(null);

		return billSupportTbl;
	}

	/**
	 * @return the discountValue
	 */
	public float getDiscountValue() {
		return discountValue;
	}

	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(float discountValue) {
		this.discountValue = discountValue;
	}

	/**
	 * @return the calculationType
	 */
	public CalculationTypeEnum getCalculationType() {
		return calculationType;
	}

	/**
	 * @param calculationType the calculationType to set
	 */
	public void setCalculationType(CalculationTypeEnum calculationType) {
		this.calculationType = calculationType;
	}

	/**
	 * @return the discountType
	 */
	public DiscountTypeEnum getDiscountType() {
		return discountType;
	}

	/**
	 * @param discountType the discountType to set
	 */
	public void setDiscountType(DiscountTypeEnum discountType) {
		this.discountType = discountType;
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