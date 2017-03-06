package com.nv.netmd.pl.entity;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bill_tbl database table.
 * 
 */
@Entity
@Table(name="bill_tbl")
@NamedQuery(name="BillTbl.findAll", query="SELECT b FROM BillTbl b")
public class BillTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BILL_TBL_ID_GENERATOR" ,sequenceName="bill_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_TBL_ID_GENERATOR")
	private Integer id;
    
	@Enumerated(EnumType.STRING)
	@Column(name="bill_status")
	private BillStatusEnum billStatus;
	
	@Column(name="created_date_time")
	private Date createdDateTime;


	@Column(name="created_on")
	private Date createdOn;

	@Column(name="updated_date_time")
	private Date updatedDateTime;

	private String currency;

	@Column(name="grand_total")
	private float grandTotal;

	private String note;

	@Column(name="paid_amount")
	private float paidAmount;

	@Column(name="bill_amount")
	private float billAmount;

	@Column(name="referral_id")
	private Integer referralId=0;

	@Column(name="referral_name", length=50)
	private String referralName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status", length=2147483647)
	private PayStatusEnum paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="origin")
	private OriginEnum origin;
	
	private String uid;

	@Column(name="global_id")
	private Integer globalId;
	
	//bi-directional many-to-one association to BillBedTbl
	@OneToMany(mappedBy="billTbl" ,cascade = CascadeType.ALL)
	private List<BillBedTbl> billBedTbls;

	//bi-directional many-to-one association to BillDiscountTbl
	@OneToMany(mappedBy="billTbl",cascade = CascadeType.ALL)
	private List<BillDiscountTbl> billDiscountTbls;

	//bi-directional many-to-one association to BillHistoryTbl
	@OneToMany(mappedBy="billTbl")
	private List<BillHistoryTbl> billHistoryTbls;

	//bi-directional many-to-one association to BillItemTbl
	@OneToMany(mappedBy="billTbl",cascade = CascadeType.ALL)
	private List<BillItemTbl> billItemTbls;

	//bi-directional many-to-one association to BillPaymentTbl
	@OneToMany(mappedBy="billTbl",cascade = CascadeType.ALL)
	private List<BillPaymentTbl> billPaymentTbls;

	//bi-directional many-to-one association to BillSupportTbl
	@OneToMany(mappedBy="billTbl" ,cascade = CascadeType.ALL)
	private List<BillSupportTbl> billSupportTbls;

	//bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

	public BillTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	


	/**
	 * @return the billStatus
	 */
	public BillStatusEnum getBillStatus() {
		return billStatus;
	}

	/**
	 * @param billStatus the billStatus to set
	 */
	public void setBillStatus(BillStatusEnum billStatus) {
		this.billStatus = billStatus;
	}

	


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	public Integer getReferralId() {
		return this.referralId;
	}

	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}


	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<BillBedTbl> getBillBedTbls() {
		return this.billBedTbls;
	}

	public void setBillBedTbls(List<BillBedTbl> billBedTbls) {
		this.billBedTbls = billBedTbls;
	}

	public BillBedTbl addBillBedTbl(BillBedTbl billBedTbl) {
		getBillBedTbls().add(billBedTbl);
		billBedTbl.setBillTbl(this);

		return billBedTbl;
	}

	public BillBedTbl removeBillBedTbl(BillBedTbl billBedTbl) {
		getBillBedTbls().remove(billBedTbl);
		billBedTbl.setBillTbl(null);

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
		billDiscountTbl.setBillTbl(this);

		return billDiscountTbl;
	}

	public BillDiscountTbl removeBillDiscountTbl(BillDiscountTbl billDiscountTbl) {
		getBillDiscountTbls().remove(billDiscountTbl);
		billDiscountTbl.setBillTbl(null);

		return billDiscountTbl;
	}

	public List<BillHistoryTbl> getBillHistoryTbls() {
		return this.billHistoryTbls;
	}

	public void setBillHistoryTbls(List<BillHistoryTbl> billHistoryTbls) {
		this.billHistoryTbls = billHistoryTbls;
	}

	public BillHistoryTbl addBillHistoryTbl(BillHistoryTbl billHistoryTbl) {
		getBillHistoryTbls().add(billHistoryTbl);
		billHistoryTbl.setBillTbl(this);

		return billHistoryTbl;
	}

	public BillHistoryTbl removeBillHistoryTbl(BillHistoryTbl billHistoryTbl) {
		getBillHistoryTbls().remove(billHistoryTbl);
		billHistoryTbl.setBillTbl(null);

		return billHistoryTbl;
	}

	public List<BillItemTbl> getBillItemTbls() {
		return this.billItemTbls;
	}

	public void setBillItemTbls(List<BillItemTbl> billItemTbls) {
		this.billItemTbls = billItemTbls;
	}

	public BillItemTbl addBillItemTbl(BillItemTbl billItemTbl) {
		getBillItemTbls().add(billItemTbl);
		billItemTbl.setBillTbl(this);

		return billItemTbl;
	}

	public BillItemTbl removeBillItemTbl(BillItemTbl billItemTbl) {
		getBillItemTbls().remove(billItemTbl);
		billItemTbl.setBillTbl(null);

		return billItemTbl;
	}

	public List<BillPaymentTbl> getBillPaymentTbls() {
		return this.billPaymentTbls;
	}

	public void setBillPaymentTbls(List<BillPaymentTbl> billPaymentTbls) {
		this.billPaymentTbls = billPaymentTbls;
	}

	public BillPaymentTbl addBillPaymentTbl(BillPaymentTbl billPaymentTbl) {
		getBillPaymentTbls().add(billPaymentTbl);
		billPaymentTbl.setBillTbl(this);

		return billPaymentTbl;
	}

	public BillPaymentTbl removeBillPaymentTbl(BillPaymentTbl billPaymentTbl) {
		getBillPaymentTbls().remove(billPaymentTbl);
		billPaymentTbl.setBillTbl(null);

		return billPaymentTbl;
	}

	public List<BillSupportTbl> getBillSupportTbls() {
		return this.billSupportTbls;
	}

	public void setBillSupportTbls(List<BillSupportTbl> billSupportTbls) {
		this.billSupportTbls = billSupportTbls;
	}

	public BillSupportTbl addBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().add(billSupportTbl);
		billSupportTbl.setBillTbl(this);

		return billSupportTbl;
	}

	public BillSupportTbl removeBillSupportTbl(BillSupportTbl billSupportTbl) {
		getBillSupportTbls().remove(billSupportTbl);
		billSupportTbl.setBillTbl(null);

		return billSupportTbl;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the grandTotal
	 */
	public float getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	/**
	 * @return the paidAmount
	 */
	public float getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}


	/**
	 * @return the paymentStatus
	 */
	public PayStatusEnum getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(PayStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the origin
	 */
	public OriginEnum getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(OriginEnum origin) {
		this.origin = origin;
	}

	/**
	 * @return the billAmount
	 */
	public float getBillAmount() {
		return billAmount;
	}

	/**
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * @return the referralName
	 */
	public String getReferralName() {
		return referralName;
	}

	/**
	 * @param referralName the referralName to set
	 */
	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}
	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public Integer getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}	

}