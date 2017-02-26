package com.fruit.entity;

import java.util.Date;
/**这是上传图片的表，管理上传的图片
 * @author 牵手无奈 
 *
 */
public class UploadPicture{

	/**图片的ID，自己确定ID
	 * 
	 */
	private Integer id;
	
	/**图片的原来名字
	 * 
	 */
	private String oldName ;
	
	/**图片的新名字
	 * 
	 */
	private String newName;
	
	/**图片上传的日期
	 * 
	 */
	private Date createTime ;
	
	public UploadPicture(String oldName, String newName) {
		super();
		this.oldName = oldName;
		this.newName = newName;
		this.createTime =  new Date();
	}

	public UploadPicture() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
