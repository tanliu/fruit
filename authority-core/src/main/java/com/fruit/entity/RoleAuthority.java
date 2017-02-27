package com.fruit.entity;

public class RoleAuthority implements java.io.Serializable {

	private String id;
	private String authorityId;
	private String roleId;

	public RoleAuthority() {

	}

	public RoleAuthority(String id, String authorityId, String roleId) {
		super();
		this.id = id;
		this.authorityId = authorityId;
		this.roleId = roleId;
	}

	public RoleAuthority(String authorityId, String roleId) {
		super();
		this.authorityId = authorityId;
		this.roleId = roleId;
	}

	// --------------------getter&&setter-------------------------------------
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}