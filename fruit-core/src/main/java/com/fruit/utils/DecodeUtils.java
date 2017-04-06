package com.fruit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：DecodeUtils 
 * 类描述： 编码工具
 * 创建人：谭柳
 * 创建时间：2016年5月28日 下午7:29:23
 * 修改人：TanLiu 
 * 修改时间：2016年5月28日 下午7:29:23
 * 修改备注： 
 * @version 
 */
public class DecodeUtils {
	
	public static String decodeUTF(String code) throws UnsupportedEncodingException{
		return URLDecoder.decode(code, "utf-8");
	}

	public static List<String> getTerm(int behind, int next) {
		List<String> trems=new ArrayList<String>();
		Calendar calendar=Calendar.getInstance();
		int nowyear=calendar.get(Calendar.YEAR);
		for(int i=behind-1;i>-1;i--){
			trems.add((nowyear-i-1)+"-"+(nowyear-i));
		}
		for(int i=0;i<next;i++){
			trems.add((nowyear+i)+"-"+(nowyear+i+1));
			
		}
		
		return trems;
	}
	

}
