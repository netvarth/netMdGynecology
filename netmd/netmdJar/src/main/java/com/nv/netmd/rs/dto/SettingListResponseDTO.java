package com.nv.netmd.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class SettingListResponseDTO {
	private List<SettingDetail> setting = new ArrayList<SettingDetail>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	
	public List<SettingDetail> getSetting() {
		return setting;
	}
	
	public void setSetting(List<SettingDetail> setting) {
		this.setting = setting;
	}
	
	public ErrorDTO getError() {
		return error;
	}
	
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}

}
