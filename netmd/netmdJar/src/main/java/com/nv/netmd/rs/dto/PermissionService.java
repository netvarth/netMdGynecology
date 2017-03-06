package com.nv.netmd.rs.dto;

import java.util.List;

public interface PermissionService {

	List<Permission> getPermissions(String name);
	List<PermissionGroup>getPermissionGroups();
	List<UserPermission>getUserPermissions(int roleId);
	Permission getPermission(String url);
}
