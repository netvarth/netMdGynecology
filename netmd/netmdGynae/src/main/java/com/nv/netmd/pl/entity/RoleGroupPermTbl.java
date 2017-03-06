package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_group_perm_tbl database table.
 * 
 */
@Entity
@Table(name="role_group_perm_tbl")
public class RoleGroupPermTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLE_GROUP_PERM_TBL_ID_GENERATOR" ,sequenceName="role_group_perm_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_GROUP_PERM_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="perm_group", length=45)
	private String permGroup;

	@Column(name="role_bit_mask")
	private Integer roleBitMask;

	//bi-directional many-to-one association to RoleTbl
    @ManyToOne
	@JoinColumn(name="role_id")
	private RoleTbl roleTbl;

    public RoleGroupPermTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermGroup() {
		return this.permGroup;
	}

	public void setPermGroup(String permGroup) {
		this.permGroup = permGroup;
	}

	public Integer getRoleBitMask() {
		return this.roleBitMask;
	}

	public void setRoleBitMask(Integer roleBitMask) {
		this.roleBitMask = roleBitMask;
	}

	public RoleTbl getRoleTbl() {
		return this.roleTbl;
	}

	public void setRoleTbl(RoleTbl roleTbl) {
		this.roleTbl = roleTbl;
	}
	
}