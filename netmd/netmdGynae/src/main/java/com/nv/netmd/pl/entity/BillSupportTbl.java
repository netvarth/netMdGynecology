package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the bill_support_tbl database table.
 * 
 */
@Entity
@Table(name="bill_support_tbl")
@NamedQuery(name="BillSupportTbl.findAll", query="SELECT b FROM BillSupportTbl b")
public class BillSupportTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_SUPPORT_TBL_ID_GENERATOR",sequenceName="bill_support_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_SUPPORT_TBL_ID_GENERATOR")
	private Integer id;

	@Column(name="discount_value")
	private float discountValue;

	@Column(name="support_date")
	private Date supportDate;
	
	@Column(name="standard_value")
	private float standardValue;

	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id")
	private BillTbl billTbl;

	@Enumerated(EnumType.STRING)
	@Column(name="tax_calculation_type")
	private CalculationTypeEnum taxCalculationType;

	@Column(name="tax_rate")
	private float taxRate;

	private Integer quantity=0;
	
	//bi-directional many-to-one association to DiscountTbl
	@ManyToOne
	@JoinColumn(name="discount_id")
	private DiscountTbl discountTbl;

	//bi-directional many-to-one association to SupportTbl
	@ManyToOne
	@JoinColumn(name="support_id")
	private SupportTbl supportTbl;

	public BillSupportTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public SupportTbl getSupportTbl() {
		return this.supportTbl;
	}

	public void setSupportTbl(SupportTbl supportTbl) {
		this.supportTbl = supportTbl;
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
	 * @return the standardValue
	 */
	public float getStandardValue() {
		return standardValue;
	}

	/**
	 * @param standardValue the standardValue to set
	 */
	public void setStandardValue(float standardValue) {
		this.standardValue = standardValue;
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
	 * @return the supportDate
	 */
	public Date getSupportDate() {
		return supportDate;
	}

	/**
	 * @param supportDate the supportDate to set
	 */
	public void setSupportDate(Date supportDate) {
		this.supportDate = supportDate;
	}
	

}