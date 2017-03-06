package com.nv.netmd.security.permission.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nv.netmd.rs.dto.Permission;
import com.nv.netmd.rs.dto.PermissionGroup;
import com.nv.netmd.rs.dto.PermissionService;
import com.nv.netmd.rs.dto.UserPermission;
import com.nv.netmd.security.pl.dao.AuthenticationDao;



public class NetmdPermissions implements PermissionService{
	
	
	private  List<Class<Enum>> enumList;
	private List<PermissionGroup> permissionGroups;
	private Map<String,Permission>permissions;
	private AuthenticationDao authenticationDao;

	
	public void init() {
		List<PermissionGroup> groups= new ArrayList<PermissionGroup>();
		Map<String,Permission> totalPermissions = new HashMap<String,Permission>();
		  for ( Class<Enum> clazz  :enumList){
			  
			 PermissionGroup permissionGroup=new PermissionGroup();
			 List<Permission> permissionsperGroup = getEnumValues(clazz);
			 permissionGroup.setName(getGroupName(clazz));
			 permissionGroup.setPermissions(permissionsperGroup);
			
			 groups.add(permissionGroup);
			 
			 for (Permission permission:permissionsperGroup){
				 totalPermissions.put(permission.getUrl(), permission);
			 }
			 
		  }
		 this.permissions=totalPermissions; 
	     this.permissionGroups=groups;
	}
	
	public <E extends Enum <E>> List<Permission> getEnumValues(Class<E> elemType) {
		List<Permission> enumValList=new ArrayList<Permission>();
        for (E e : java.util.EnumSet.allOf(elemType)) {
           enumValList.add(((Permission)e)); 
       }
        return enumValList;
    }
	
	
	public<E extends Enum <E>> String getGroupName(Class<E> elemType) {
		
        for (E e : java.util.EnumSet.allOf(elemType)) {
           Permission permission =((Permission)e);
          return permission.getPermissionGroup();
       }
        return "";
    }


	@Override
	public List<PermissionGroup> getPermissionGroups() {
		
		return this.permissionGroups;
	}
	
    public List<Permission> getPermissions(String name) {
		List<Permission> permissions = new ArrayList<Permission>();
    	
    	for (PermissionGroup group :permissionGroups){
		      if (group.getName().equals(name)){
		    	  permissions= group.getPermissions();
		    	  break;
		      }
    	}
	return permissions;
    }

	@Override
	public List<UserPermission> getUserPermissions(int roleId) {
		List<UserPermission> permissions = new ArrayList<UserPermission>();
		permissions = authenticationDao.getUserPermissions(roleId);
	return permissions;
	}

	@Override
	public Permission getPermission(String url) {
		Permission permission =this.permissions.get(url);
		
		return permission;
	}

	public AuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	public List<Class<Enum>> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<Class<Enum>> enumList) {
		this.enumList = enumList;
	}
	
	
}
