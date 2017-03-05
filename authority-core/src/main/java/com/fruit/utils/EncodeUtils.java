/**
 * 
 */
package com.fruit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/** 
 * 项目名称：ElecRecord
 * 类名称：EncodeUtils 
 * 类描述： 提交系统需要的各种编码转换方法的工具类
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午9:00:43
 * 修改人：谭柳
 * 修改时间：2016年4月26日 上午9:00:43
 * 修改备注： 
 * @version 
 */
public class EncodeUtils {
	
	/**
	 * 方法描述:把字符串编码转换成utf-8
	 * @param code 补编码的参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeUTF(String code) throws UnsupportedEncodingException{
		return URLDecoder.decode(code, "utf-8");
	}
}
