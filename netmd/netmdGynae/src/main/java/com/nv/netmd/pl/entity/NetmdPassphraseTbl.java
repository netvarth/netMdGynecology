package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netmd_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_passphrase_tbl")
public class NetmdPassphraseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NETMD_PASSPHRASE_TBL_ID_GENERATOR",sequenceName="netmd_passphrase_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NETMD_PASSPHRASE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="mac_id", length=100)
	private String macId;

	@Column(name="pass_phrase", length=100)
	private String passPhrase;

	@Column(name="primary_key")
	private Boolean primaryKey;

	//bi-directional many-to-one association to NetmdBranchTbl
    @ManyToOne
	@JoinColumn(name="netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdTbl
    @ManyToOne
	@JoinColumn(name="netmd_id")
	private NetmdTbl netmdTbl;

    public NetmdPassphraseTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMacId() {
		return this.macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getPassPhrase() {
		return this.passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public Boolean getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}
	
	public NetmdTbl getNetmdTbl() {
		return this.netmdTbl;
	}

	public void setNetmdTbl(NetmdTbl netmdTbl) {
		this.netmdTbl = netmdTbl;
	}
	
}