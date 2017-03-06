package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the role_tbl database table.
 * 
 */
@Entity
@Table(name="role_tbl")
public class RoleTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLE_TBL_ID_GENERATOR",sequenceName="role_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=45)
	private String name;

	//bi-directional many-to-one association to RoleGroupPermTbl
	@OneToMany(mappedBy="roleTbl")
	private Set<RoleGroupPermTbl> roleGroupPermTbls;

    public RoleTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RoleGroupPermTbl> getRoleGroupPermTbls() {
		return this.roleGroupPermTbls;
	}

	public void setRoleGroupPermTbls(Set<RoleGroupPermTbl> roleGroupPermTbls) {
		this.roleGroupPermTbls = roleGroupPermTbls;
	}
	
}