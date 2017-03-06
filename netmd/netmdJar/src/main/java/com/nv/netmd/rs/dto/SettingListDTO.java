package com.nv.netmd.rs.dto;





public class SettingListDTO {
	private int uid;
	private int itemId;
	private String value;
	private String actionName;
//	public SettingListDTO(SettingListTbl settinglisttbl) {
//		
//		this.setValue(settinglisttbl.getValue());
//		this.setItemId(settinglisttbl.getItemId());
//		this.setUid(settinglisttbl.getId());
//	}
	public SettingListDTO() {
		
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
