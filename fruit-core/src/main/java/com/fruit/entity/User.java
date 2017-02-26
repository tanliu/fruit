package com.fruit.entity;

import java.util.Date;

/**这是管理员的表，与其它表都有关系
 * @author 牵手无奈 
 *
 */
public class User{
	
    /**管理员ID
     * 
     */
	private Integer id;
    
    /**用户密码，不能超过20个，不能空
     * 
     */
	private String password;
    
    
    /**用户登陆名，唯一，不能空，不能超过20字
     * 
     */
	private String acountName;
    
    /**用户昵称，在文章和其它地方展示，不能超过20个，不能空，要唯一 
     * 
     */
	private String nickName;
    
    /**用户等级，1为超级管理员，3为高级管理员，5为普通管理员
     * 
     */
	private Integer grade ;
    
    /**用户注册时间
     * 
     */
	private Date createTime;

	public User(String password, String acountName, String nickName,
			Integer grade) {
		super();
		this.password = password;
		this.acountName = acountName;
		this.nickName = nickName;
		this.grade = grade;
		this.createTime = new Date();
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAcountName() {
		return acountName;
	}

	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	
    
}