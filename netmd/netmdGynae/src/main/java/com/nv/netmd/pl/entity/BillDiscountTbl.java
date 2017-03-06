package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the bill_discount_tbl database table.
 * 
 */
@Entity
@Table(name="bill_discount_tbl")
@NamedQuery(name="BillDiscountTbl.findAll", query="SELECT b FROM BillDiscountTbl b")
public class BillDiscountTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_DISCOUNT_TBL_ID_GENERATOR",sequenceName="bill_discount_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_DISCOUNT_TBL_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="calculation_type")
	private CalculationTypeEnum calculationType;
	
	@Column(name="discount_amount")
	private float discountAmount;

	@Column(name="discount_value")
	private float discountValue;

	//bi-directional many-to-one association to BillTbl
	@ManyToOne
	@JoinColumn(name="bill_id")
	private BillTbl billTbl;

	//bi-directional many-to-one association to DiscountTbl
	@ManyToOne
	@JoinColumn(name="discount_id")
	private DiscountTbl discountTbl;

	public BillDiscountTbl() {
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
	 * @return the discountAmount
	 */
	public float getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

}