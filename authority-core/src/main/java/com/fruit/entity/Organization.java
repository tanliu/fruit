package com.fruit.entity;





/** 
 * 项目名称：ElecRecord
 * 类名称：Organization 
 * 类描述： 部位信息实体
 * 创建人：谭柳
 * 创建时间：2016年5月6日 下午12:51:56
 * 修改人：谭柳
 * 修改时间：2016年5月6日 下午12:51:56
 * 修改备注： 
 * @version 
 */ 
public class Organization  implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String orgId;  
     private String orgName;
     private String parentId;
     private String parentIds;
     private String address;
     private String postCode;
     private String contactMan;
     private String tell;
     private String fax;
     private String email;
     private String state;   //状态


//--------------------------constructor---------------------
    public Organization() {
    }

    
    public Organization(String orgName, String parentId, String parentIds, String address, String postCode, String contactMan, String tell, String fax, String email, String state) {
        this.orgName = orgName;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.address = address;
        this.postCode = postCode;
        this.contactMan = contactMan;
        this.tell = tell;
        this.fax = fax;
        this.email = email;
        this.state = state;
    }

   
//----------------------------------------------getter&setter--------------------------------------
    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentId() {
        return this.parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return this.parentIds;
    }
    
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getContactMan() {
        return this.contactMan;
    }
    
    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getTell() {
        return this.tell;
    }
    
    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
   








}