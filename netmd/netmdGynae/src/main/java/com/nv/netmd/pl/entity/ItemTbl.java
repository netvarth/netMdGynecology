package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;




/**
 * The persistent class for the item_tbl database table.
 * 
 */
@Entity
@Table(name="item_tbl")
public class ItemTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITEM_TBL_ID_GENERATOR",sequenceName="item_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEM_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	private String description;
	
	private String name;

	private float price;

	private Integer quantity=0;

	private String uid;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	//bi-directional many-to-one association to TaxTbl
	@ManyToOne
	@JoinColumn(name="tax_id")
	private TaxTbl taxTbl;

	public ItemTbl() {
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

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public TaxTbl getTaxTbl() {
		return this.taxTbl;
	}

	public void setTaxTbl(TaxTbl taxTbl) {
		this.taxTbl = taxTbl;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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