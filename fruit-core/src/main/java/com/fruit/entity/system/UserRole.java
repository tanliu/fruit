package com.fruit.entity.system;


/** 
 * 项目名称：ElecRecord
 * 类名称：UserRole 
 * 类描述： 用户角色
 * 创建人：谭柳
 * 创建时间：2016年6月2日 下午4:39:28
 * 修改人：TanLiu 
 * 修改时间：2016年6月2日 下午4:39:28
 * 修改备注： 
 * @version 
 */ 
public class UserRole implements java.io.Serializable {


	private String id;
	private User user;
	private Role role;

	public UserRole() {
	}
	

	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}
	


	public UserRole(String id, User user, Role role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	



}