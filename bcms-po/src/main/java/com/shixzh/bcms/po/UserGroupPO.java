package com.shixzh.bcms.po;

import java.util.List;

public class UserGroupPO {

	private List<String> permissionList;

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "UserGroupPO [permissionList=" + permissionList + "]";
	}

}
