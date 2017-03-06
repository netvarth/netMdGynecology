package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the tax_tbl database table.
 * 
 */
@Entity
@Table(name="tax_tbl")
public class TaxTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYNC_TBL_ID_GENERATOR",sequenceName="tax_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYNC_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="calculation_type")
	private CalculationTypeEnum calculationType;

	private String description;

	private String name;

	@Column(name="tax_val")
	private float taxVal;

	//bi-directional many-to-one association to ItemTbl
	@OneToMany(mappedBy="taxTbl")
	private List<ItemTbl> itemTbls;

	//bi-directional many-to-one association to SupportTbl
	@OneToMany(mappedBy="taxTbl")
	private List<SupportTbl> supportTbls;

	//bi-directional many-to-one association to BedTypeTbl
	@OneToMany(mappedBy="taxTbl")
	private List<BedTypeTbl> bedTypeTbls;
	
	//bi-directional one-to-one association to TaxTbl
	@OneToOne
	@JoinColumn(name="id")
	private TaxTbl taxTbl1;

	//bi-directional one-to-one association to TaxTbl
	@OneToOne(mappedBy="taxTbl1")
	private TaxTbl taxTbl2;

	public TaxTbl() {
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


	public List<ItemTbl> getItemTbls() {
		return this.itemTbls;
	}

	public void setItemTbls(List<ItemTbl> itemTbls) {
		this.itemTbls = itemTbls;
	}

	public ItemTbl addItemTbl(ItemTbl itemTbl) {
		getItemTbls().add(itemTbl);
		itemTbl.setTaxTbl(this);

		return itemTbl;
	}

	public ItemTbl removeItemTbl(ItemTbl itemTbl) {
		getItemTbls().remove(itemTbl);
		itemTbl.setTaxTbl(null);

		return itemTbl;
	}

	public List<SupportTbl> getSupportTbls() {
		return this.supportTbls;
	}

	public void setSupportTbls(List<SupportTbl> supportTbls) {
		this.supportTbls = supportTbls;
	}

	public SupportTbl addSupportTbl(SupportTbl supportTbl) {
		getSupportTbls().add(supportTbl);
		supportTbl.setTaxTbl(this);

		return supportTbl;
	}

	public SupportTbl removeSupportTbl(SupportTbl supportTbl) {
		getSupportTbls().remove(supportTbl);
		supportTbl.setTaxTbl(null);

		return supportTbl;
	}

	public TaxTbl getTaxTbl1() {
		return this.taxTbl1;
	}

	public void setTaxTbl1(TaxTbl taxTbl1) {
		this.taxTbl1 = taxTbl1;
	}

	public TaxTbl getTaxTbl2() {
		return this.taxTbl2;
	}

	public void setTaxTbl2(TaxTbl taxTbl2) {
		this.taxTbl2 = taxTbl2;
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
	 * @return the taxVal
	 */
	public float getTaxVal() {
		return taxVal;
	}

	/**
	 * @param taxVal the taxVal to set
	 */
	public void setTaxVal(float taxVal) {
		this.taxVal = taxVal;
	}
	public List<BedTypeTbl> getBedTypeTbls() {
		return this.bedTypeTbls;
	}

	public BedTypeTbl addBedTypeTbl(BedTypeTbl bedTypeTbl) {
		getBedTypeTbls().add(bedTypeTbl);
		bedTypeTbl.setTaxTbl(this);

		return bedTypeTbl;
	}

	public BedTypeTbl removeBedTypeTbl(BedTypeTbl bedTypeTbl) {
		getBedTypeTbls().remove(bedTypeTbl);
		bedTypeTbl.setTaxTbl(null);

		return bedTypeTbl;
	}

	/**
	 * @param bedTypeTbls the bedTypeTbls to set
	 */
	public void setBedTypeTbls(List<BedTypeTbl> bedTypeTbls) {
		this.bedTypeTbls = bedTypeTbls;
	}
}