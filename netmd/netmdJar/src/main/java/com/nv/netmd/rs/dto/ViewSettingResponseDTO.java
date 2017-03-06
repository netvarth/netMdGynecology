package com.nv.netmd.rs.dto;

public class ViewSettingResponseDTO {
	
	private SettingDTO setting;
	private boolean success;
	private ErrorDTO error;
	
	public ViewSettingResponseDTO() {
		
	}
 
	
	public ViewSettingResponseDTO(SettingDTO setting, boolean success,
			ErrorDTO error) {
		super();
		this.setting = setting;
		this.success = success;
		this.error = error;
	}
	
	public SettingDTO getSetting() {
		return setting;
	}
	
	public void setSetting(SettingDTO setting) {
		this.setting = setting;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public ErrorDTO getError() {
		return error;
	}
	
	public void setError(ErrorDTO error) {
		this.error = error;
	}


}
