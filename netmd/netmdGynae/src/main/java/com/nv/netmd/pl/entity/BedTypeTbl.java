package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the bed_type_tbl database table.
 * 
 */
@Entity
@Table(name="bed_type_tbl")
public class BedTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BED_TYPE_TBL_ID_GENERATOR",sequenceName="bed_type_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BED_TYPE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	private Integer count=0;

	@Column(precision=131089)
	private float rent;

	@Column(length=50)
	private String type;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	//bi-directional many-to-one association to BedTbl
	@OneToMany(mappedBy="bedTypeTbl")
	private List<BedTbl> bedTbls;

	//bi-directional many-to-one association to TaxTbl
	@ManyToOne
	@JoinColumn(name="tax_id")
	private TaxTbl taxTbl;

	public BedTypeTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BedTbl> getBedTbls() {
		return this.bedTbls;
	}

	public void setBedTbls(List<BedTbl> bedTbls) {
		this.bedTbls = bedTbls;
	}

	public BedTbl addBedTbl(BedTbl bedTbl) {
		getBedTbls().add(bedTbl);
		bedTbl.setBedTypeTbl(this);

		return bedTbl;
	}

	public BedTbl removeBedTbl(BedTbl bedTbl) {
		getBedTbls().remove(bedTbl);
		bedTbl.setBedTypeTbl(null);

		return bedTbl;
	}

	/**
	 * @return the rent
	 */
	public float getRent() {
		return rent;
	}

	/**
	 * @param rent the rent to set
	 */
	public void setRent(float rent) {
		this.rent = rent;
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

	/**
	 * @return the taxTbl
	 */
	public TaxTbl getTaxTbl() {
		return taxTbl;
	}

	/**
	 * @param taxTbl the taxTbl to set
	 */
	public void setTaxTbl(TaxTbl taxTbl) {
		this.taxTbl = taxTbl;
	}


}