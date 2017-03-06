package com.nv.netmd.rs.dto;

import java.util.List;

public class SettingDTO {
	private int id;
	private String name;
	private List<SettingListDTO> settingList;
	
	public int getId() {
		return id;
	}
	
	
	/**
	 * 
	 */
	public SettingDTO() {
		
	}

	/**
 * @param id
 * @param name
 * @param settingList
 */
public SettingDTO(int id, String name, List<SettingListDTO> settingList) {
	super();
	this.id = id;
	this.name = name;
	this.settingList = settingList;
}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SettingListDTO> getSettingList() {
		return settingList;
	}
	public void setSettingList(List<SettingListDTO> settingList) {
		this.settingList = settingList;
	}
	

}
