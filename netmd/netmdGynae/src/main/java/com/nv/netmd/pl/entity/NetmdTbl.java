package com.nv.netmd.pl.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;



/**
 * The persistent class for the netmd_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_tbl")
public class NetmdTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NETMD_TBL_ID_GENERATOR",sequenceName="netmd_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NETMD_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(name="global_id")
	private Integer globalId;

	@Column(name="head_office_address", length=45)
	private String headOfficeAddress;

	@Column(name="head_office_email", length=45)
	private String headOfficeEmail;

	@Column(name="head_office_mobile", length=45)
	private String headOfficeMobile;

	@Column(name="head_office_name", length=45)
	private String headOfficeName;

	@Column(name="head_office_phone", length=45)
	private String headOfficePhone;

	//bi-directional many-to-one association to LoginTbl
    @ManyToOne
	@JoinColumn(name="login_id")
	private LoginTbl loginTbl;

	@Column(length=45)
	private String name;

	@Column(name="owner_address", length=45)
	private String ownerAddress;

	@Column(name="owner_email", length=45)
	private String ownerEmail;

	@Column(name="owner_first_name", length=45)
	private String ownerFirstName;

	@Column(name="owner_last_name", length=45)
	private String ownerLastName;

	@Column(name="owner_mobile", length=45)
	private String ownerMobile;

	@Column(name="owner_phone", length=45)
	private String ownerPhone;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;
 
	@Column(name="logo",length=2147483647)
	private String logo;

	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to NetmdBranchTbl
	@OneToMany(mappedBy="netmdTbl")
	private Set<NetmdBranchTbl> netmdBranchTbls;

    public NetmdTbl() {
    }

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the glodalId
	 */
	public Integer getGlodalId() {
		return globalId;
	}

	/**
	 * @param glodalId the glodalId to set
	 */
	public void setGlodalId(Integer globalId) {
		this.globalId = globalId;
	}

	/**
	 * @return the headOfficeAddress
	 */
	public String getHeadOfficeAddress() {
		return headOfficeAddress;
	}

	/**
	 * @param headOfficeAddress the headOfficeAddress to set
	 */
	public void setHeadOfficeAddress(String headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}

	/**
	 * @return the headOfficeEmail
	 */
	public String getHeadOfficeEmail() {
		return headOfficeEmail;
	}

	/**
	 * @param headOfficeEmail the headOfficeEmail to set
	 */
	public void setHeadOfficeEmail(String headOfficeEmail) {
		this.headOfficeEmail = headOfficeEmail;
	}

	/**
	 * @return the headOfficeMobile
	 */
	public String getHeadOfficeMobile() {
		return headOfficeMobile;
	}

	/**
	 * @param headOfficeMobile the headOfficeMobile to set
	 */
	public void setHeadOfficeMobile(String headOfficeMobile) {
		this.headOfficeMobile = headOfficeMobile;
	}

	/**
	 * @return the headOfficeName
	 */
	public String getHeadOfficeName() {
		return headOfficeName;
	}

	/**
	 * @param headOfficeName the headOfficeName to set
	 */
	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	/**
	 * @return the headOfficePhone
	 */
	public String getHeadOfficePhone() {
		return headOfficePhone;
	}

	/**
	 * @param headOfficePhone the headOfficePhone to set
	 */
	public void setHeadOfficePhone(String headOfficePhone) {
		this.headOfficePhone = headOfficePhone;
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
	 * @return the ownerAddress
	 */
	public String getOwnerAddress() {
		return ownerAddress;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @param ownerAddress the ownerAddress to set
	 */
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	/**
	 * @return the ownerEmail
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}

	/**
	 * @param ownerEmail the ownerEmail to set
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	/**
	 * @return the ownerFirstName
	 */
	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	/**
	 * @param ownerFirstName the ownerFirstName to set
	 */
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	/**
	 * @return the ownerLastName
	 */
	public String getOwnerLastName() {
		return ownerLastName;
	}

	/**
	 * @param ownerLastName the ownerLastName to set
	 */
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	/**
	 * @return the ownerMobile
	 */
	public String getOwnerMobile() {
		return ownerMobile;
	}

	/**
	 * @param ownerMobile the ownerMobile to set
	 */
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	/**
	 * @return the ownerPhone
	 */
	public String getOwnerPhone() {
		return ownerPhone;
	}

	/**
	 * @param ownerPhone the ownerPhone to set
	 */
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	
	/**
	 * @return the netmdBranchTbls
	 */
	public Set<NetmdBranchTbl> getNetmdBranchTbls() {
		return netmdBranchTbls;
	}

	/**
	 * @param netmdBranchTbls the netmdBranchTbls to set
	 */
	public void setNetmdBranchTbls(Set<NetmdBranchTbl> netmdBranchTbls) {
		this.netmdBranchTbls = netmdBranchTbls;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
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
	 * @return the loginTbl
	 */
	public LoginTbl getLoginTbl() {
		return loginTbl;
	}

	/**
	 * @param loginTbl the loginTbl to set
	 */
	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}


}