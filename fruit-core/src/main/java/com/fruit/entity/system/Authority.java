package com.fruit.entity.system;


public class Authority implements java.io.Serializable {

	// Fields

	private String authorityId;
	private String parentId;
	private String parentIds;
	private String authorityName;
	private Integer authorityType;
	private String moduleName;
	private String url;
	private String operation;
	private String menuNo;
	private String memo;
	
	//authorityType权限类型-----权限类型 0-菜单 1-新增 2-修改 3-查询 4-删除 5-导出 6-导入 7-授权
	public final static Integer TYPE_MENU=0;
	public final static Integer TYPE_ADD=1;
	public final static Integer TYPE_ALTER=2;
	public final static Integer TYPE_QUERY=3;
	public final static Integer TYPE_DELETE=4;
	public final static Integer TYPE_EXPORT=5;
	public final static Integer TYPE_IMPORT=6;
	public final static Integer TYPE_GRANT=7;
	
	
	
	public Authority() {
		super();
	}
	public Authority(String authorityId, String parentId, String parentIds, String authorityName, Integer authorityType,
			String moduleName, String url, String operation, String menuNo, String memo) {
		super();
		this.authorityId = authorityId;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.authorityName = authorityName;
		this.authorityType = authorityType;
		this.moduleName = moduleName;
		this.url = url;
		this.operation = operation;
		this.menuNo = menuNo;
		this.memo = memo;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public Integer getAuthorityType() {
		return authorityType;
	}
	public void setAuthorityType(Integer authorityType) {
		this.authorityType = authorityType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	


}