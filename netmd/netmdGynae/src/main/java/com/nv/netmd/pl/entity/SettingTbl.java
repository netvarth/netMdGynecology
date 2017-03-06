package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the setting_tbl database table.
 * 
 */
@Entity
@Table(name="setting_tbl")
public class SettingTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SETTING_TBL_ID_GENERATOR",sequenceName="setting_seq", allocationSize=1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SETTING_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="group_name", length=2147483647)
	private String groupName;

	//bi-directional many-to-one association to SettingListTbl
	@OneToMany(mappedBy="settingTbl")
	private List<SettingListTbl> settingListTbls;

	public SettingTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<SettingListTbl> getSettingListTbls() {
		return this.settingListTbls;
	}

	public void setSettingListTbls(List<SettingListTbl> settingListTbls) {
		this.settingListTbls = settingListTbls;
	}

	public SettingListTbl addSettingListTbl(SettingListTbl settingListTbl) {
		getSettingListTbls().add(settingListTbl);
		settingListTbl.setSettingTbl(this);

		return settingListTbl;
	}

	public SettingListTbl removeSettingListTbl(SettingListTbl settingListTbl) {
		getSettingListTbls().remove(settingListTbl);
		settingListTbl.setSettingTbl(null);

		return settingListTbl;
	}

}