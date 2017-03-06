package com.nv.netmd.pl.entity;

 

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the netmd_branch_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_branch_tbl")
public class NetmdBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="NETMD_BRANCH_TBL_ID_GENERATOR",sequenceName="netmd_branch_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NETMD_BRANCH_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=45)
	private String address;

	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(length=45)
	private String email;

	@Column(name="global_id")
	private Integer globalId;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String phone;
	
	@Column(length=2147483647)
	private String logo;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to NetmdTbl
    @ManyToOne
	@JoinColumn(name="netmd_id")
	private NetmdTbl netmdTbl;

  //bi-directional many-to-one association to NetmdUserTbl
  	@OneToMany(mappedBy="netmdBranchTbl")
  	private Set<NetmdUserTbl> netmdUserTbls;

    public NetmdBranchTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public NetmdTbl getNetmdTbl() {
		return this.netmdTbl;
	}

	public void setNetmdTbl(NetmdTbl netmdTbl) {
		this.netmdTbl = netmdTbl;
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
	 * @return the netmdUserTbls
	 */
	public Set<NetmdUserTbl> getNetmdUserTbls() {
		return netmdUserTbls;
	}

	/**
	 * @param netmdUserTbls the netmdUserTbls to set
	 */
	public void setNetmdUserTbls(Set<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
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