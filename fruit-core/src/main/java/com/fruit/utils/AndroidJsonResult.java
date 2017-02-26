package com.fruit.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
/*
 * 用于android的json返回数据
 */
public class AndroidJsonResult {
	private Map<String, Object> result = new HashMap<>();
	
	public AndroidJsonResult(boolean success, int errcode, String errmsg, Object data){
		result.put("success", success);
		result.put("errmsg", errmsg);
		result.put("errcode", errcode);
		result.put("data", data);
	}
	
	public AndroidJsonResult() {
		super();
	}

	public void put(String key,Object value){
		result.put(key, value);
	}
	
	public Object get(String key){
		return result.get(key);
	}
	
	public String reset(boolean success, int errcode, String errmsg, Object data){
		result.put("success", success);
		result.put("errmsg", errmsg);
		result.put("errcode", errcode);
		result.put("data", data);
		return toString();
	}

	@Override
	public String toString() {
		String str = JSONArray.fromObject(result).toString();
		return str.substring(1,str.length()-1);
	}
	
}
