package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class SettingDetail {
	private int uid;
	private String groupName;
	private List<SettingListDTO> setting = new ArrayList<SettingListDTO>();
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<SettingListDTO> getSetting() {
		return setting;
	}
	public void setSetting(List<SettingListDTO> setting) {
		this.setting = setting;
	}

	public SettingDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
