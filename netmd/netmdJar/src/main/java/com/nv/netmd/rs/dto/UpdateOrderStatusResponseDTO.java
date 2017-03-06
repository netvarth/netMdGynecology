package com.nv.netmd.rs.dto;

public class UpdateOrderStatusResponseDTO {

	private boolean success;
	private ErrorDTO error;
	
	public UpdateOrderStatusResponseDTO(){
		
	}

	public UpdateOrderStatusResponseDTO(boolean success, ErrorDTO error) {
		super();
		this.success = success;
		this.error = error;
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
