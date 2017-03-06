package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;


/**
 * The persistent class for the bill_item_tbl database table.
 * 
 */
@Entity
@Table(name="bill_item_tbl")
@NamedQuery(name="BillItemTbl.findAll", query="SELECT b FROM BillItemTbl b")
public class BillItemTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_ITEM_TBL_ID_GENERATOR",sequenceName="bill_item_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_ITEM_TBL_ID_GENERATOR")
	private Integer id;

	@Column(name="discount_value")
	private float discountValue;

	@Column(name="standard_rate")
	private float standardRate;

	@Column(name="item_date")
	private Date itemDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tax_calculation_type")
	private CalculationTypeEnum taxCalculationType;

	@Column(name="tax_rate")
	private float taxRate;

	private Integer quantity=0;
	
	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id"  ,referencedColumnName = "id")
	private BillTbl billTbl;

	//bi-directional many-to-one association to DiscountTbl
	@ManyToOne
	@JoinColumn(name="discount_id")
	private DiscountTbl discountTbl;

	//bi-directional many-to-one association to ItemTbl
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemTbl itemTbl;

	public BillItemTbl() {
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

	public ItemTbl getItemTbl() {
		return this.itemTbl;
	}

	public void setItemTbl(ItemTbl itemTbl) {
		this.itemTbl = itemTbl;
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
	public Date getItemDate() {
		return this.itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

}