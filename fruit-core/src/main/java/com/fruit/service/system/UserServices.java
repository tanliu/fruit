package com.fruit.service.system;


import com.fruit.base.BaseService;
import com.fruit.entity.system.User;
import com.fruit.utils.PageUtils;

/**
 * 项目名称：ElecRecord
 * 类名称：UserServices 
 * 类描述： 用户管理的Service层接口
 * 创建人：谭柳
 * 创建时间：2016年5月24日 上午10:42:59
 * 修改人：TanLiu 
 * 修改时间：2016年5月24日 上午10:42:59
 * 修改备注： 
 * @version 
 */ 
public interface UserServices extends BaseService<User> {

   public PageUtils queryList(User user, int pageNO, int pageSize, String companyId);

    /**
     * 方法描述:修改密码
     * @param user 用户信息
     * @param confirmpwd 确认密码
     * @return
     */
    public boolean editorPwd(User user, String confirmpwd);

	/**
	 * 方法描述:保存用户
	 * @param user
	 * @param roleIds 
	 */
	public void saveUser(User user, String[] roleIds);

	/**
	 * 方法描述:修改用户
	 * @param user
	 * @param roleIds
	 */
	public void editorUser(User user, String[] roleIds);

	/**
	 * 方法描述:删除用户
	 * @param selectedRow
	 */
	public void deleteUser(String[] selectedRow);

	User getUserByEmployNO(String employNo);

	/**
	 * 方法描述:激活与关闭的转换
	 * @param user
	 * @return
	 */
	public String transform(User user);

	/**
	 * 方法描述:存在此帐号
	 * @param employNo
	 * @return
	 */
	public Boolean hasUser(String employNo);


	/**
	 * 方法描述:删除帐号
	 * @param selectedRow
	 */
	public void trueDeleteUser(String[] selectedRow);


	void unBindUser(String employNO);

	void bindUser(String employNo,String userType);
    
}
