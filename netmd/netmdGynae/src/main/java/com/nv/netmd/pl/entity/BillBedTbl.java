package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the bill_bed_tbl database table.
 * 
 */
@Entity
@Table(name="bill_bed_tbl")
@NamedQuery(name="BillBedTbl.findAll", query="SELECT b FROM BillBedTbl b")
public class BillBedTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_BED_TBL_ID_GENERATOR" ,sequenceName="bill_bed_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_BED_TBL_ID_GENERATOR")
	private Integer id;

	@Column(name="discount_value")
	private float discountValue;

	@Column(name="standard_rate")
	private float standardRate;

	//bi-directional many-to-one association to BedTbl
	@ManyToOne
	@JoinColumn(name="bed_id")
	private BedTbl bedTbl;

	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id")
	private BillTbl billTbl;


	private Integer quantity=0;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tax_calculation_type")
	private CalculationTypeEnum taxCalculationType;

	@Column(name="tax_rate", precision=131089)
	private float taxRate;
	
	//bi-directional many-to-one association to DiscountTbl
	@ManyToOne
	@JoinColumn(name="discount_id")
	private DiscountTbl discountTbl;

	public BillBedTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public BedTbl getBedTbl() {
		return this.bedTbl;
	}

	public void setBedTbl(BedTbl bedTbl) {
		this.bedTbl = bedTbl;
	}

	public BillTbl getBillTbl() {
		return this.billTbl;
	}

	public void setBillTbl(BillTbl billTbl) {
		this.billTbl = billTbl;
	}

	public DiscountTbl getDiscountTbl() {
		return this.discountTbl;
	}

	public void setDiscountTbl(DiscountTbl discountTbl) {
		this.discountTbl = discountTbl;
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
	 * @return the standardRate
	 */
	public float getStandardRate() {
		return standardRate;
	}

	/**
	 * @param standardRate the standardRate to set
	 */
	public void setStandardRate(float standardRate) {
		this.standardRate = standardRate;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the taxCalculationType
	 */
	public CalculationTypeEnum getTaxCalculationType() {
		return taxCalculationType;
	}

	/**
	 * @param taxCalculationType the taxCalculationType to set
	 */
	public void setTaxCalculationType(CalculationTypeEnum taxCalculationType) {
		this.taxCalculationType = taxCalculationType;
	}

	/**
	 * @return the taxRate
	 */
	public float getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}




}