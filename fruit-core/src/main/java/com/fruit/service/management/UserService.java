package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.User;


/**为UserController服务的Service
 * @author 牵手无奈
 *
 */

public interface UserService extends BaseService<User> {

	
	/**获取数据库安装目录
	 * @return
	 */
	public String getDBInstallDir();

	public String[] getDbUsernameAndPasswd(String rootPath);
}
