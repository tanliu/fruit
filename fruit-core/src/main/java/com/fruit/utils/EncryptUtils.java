/**
 * 
 */
package com.fruit.utils;

/** 
 * 项目名称：ElecRecord
 * 类名称：EncryptUtils 
 * 类描述： 加密工具
 * 创建人：谭柳
 * 创建时间：2016年5月25日 下午4:25:41
 * 修改人：TanLiu 
 * 修改时间：2016年5月25日 下午4:25:41
 * 修改备注： 
 * @version 
 */
public class EncryptUtils {
	
	public static String MD5Encrypt(String str){
		MD5keyBean md5keyBean=new MD5keyBean();
		String encryptCode=md5keyBean.getkeyBeanofStr(str);
		return encryptCode;
	}

}
