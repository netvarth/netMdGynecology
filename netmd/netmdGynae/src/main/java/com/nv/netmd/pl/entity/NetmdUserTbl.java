package com.nv.netmd.pl.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;




/**
 * The persistent class for the netmd_user_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_user_tbl")
public class NetmdUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NETMD_USER_TBL_ID_GENERATOR",sequenceName="netmd_user_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NETMD_USER_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=45)
	private String address;

	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(length=45)
	private String email;

	@Column(name="first_name", length=100)
	private String firstName;

	@Column(name="global_id")
	private Integer globalId;

	@Column(name="last_name", length=45)
	private String lastName;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;

	@Column(name="update_date_time")
	private Date updateDateTime;

	@Column(name="user_type", length=50)
	private String userType;


	//bi-directional many-to-one association to LoginTbl
    @ManyToOne
	@JoinColumn(name="login_id")
	private LoginTbl loginTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
    @ManyToOne
	@JoinColumn(name="netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;
    public NetmdUserTbl() {
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the globalId
	 */
	public Integer getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

		
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
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
	 * @return the netmdBranchTbl
	 */
	public NetmdBranchTbl getNetmdBranchTbl() {
		return netmdBranchTbl;
	}

	/**
	 * @param netmdBranchTbl the netmdBranchTbl to set
	 */
	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
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