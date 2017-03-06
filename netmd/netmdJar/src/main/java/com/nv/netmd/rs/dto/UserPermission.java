package com.nv.netmd.rs.dto;


public class UserPermission {

	
	private  String permissionGroup;
	private int bitmask;
	 
	
	
	public UserPermission(String permissionGroup, int bitmask) {
		super();
		this.permissionGroup = permissionGroup;
		this.bitmask = bitmask;
	}

	public boolean isPermitted(Permission permission){
		if (!permissionGroup.equalsIgnoreCase(permission.getPermissionGroup())){
			return false;
		}
		int hexaInt =permission.getHexaBit();
		String permissionString = Integer.toHexString(hexaInt);
		
		return ( (bitmask & hexaInt) == hexaInt );
	}

	public String getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(String permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public int getBitmask() {
		return bitmask;
	}

	public void setBitmask(int bitmask) {
		this.bitmask = bitmask;
	}
	
	
	
	
	
	




}
