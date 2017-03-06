package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the setting_list_tbl database table.
 * 
 */
@Entity
@Table(name="setting_list_tbl")
public class SettingListTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SETTING_LIST_TBL_ID_GENERATOR",sequenceName="setting_list_seq", allocationSize=1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SETTING_LIST_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="item_id")
	private Integer itemId;

	@Column(length=2147483647)
	private String value;

	//bi-directional many-to-one association to SettingTbl
	@ManyToOne
	@JoinColumn(name="group_id")
	private SettingTbl settingTbl;

	public SettingListTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SettingTbl getSettingTbl() {
		return this.settingTbl;
	}

	public void setSettingTbl(SettingTbl settingTbl) {
		this.settingTbl = settingTbl;
	}

}